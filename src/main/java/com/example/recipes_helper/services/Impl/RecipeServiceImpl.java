package com.example.recipes_helper.services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.recipes_helper.DTO.IngredientDTO;
import com.example.recipes_helper.DTO.RecipeWithIngredientsDTO;
import com.example.recipes_helper.model.ListProduct;
import com.example.recipes_helper.model.Product;
import com.example.recipes_helper.model.ProductCategory;
import com.example.recipes_helper.model.Recipe;
import com.example.recipes_helper.model.RecipeCategory;
import com.example.recipes_helper.model.UserProduct;
import com.example.recipes_helper.repository.ListProductRepository;
import com.example.recipes_helper.repository.ProductRepository;
import com.example.recipes_helper.repository.RecipeRepository;
import com.example.recipes_helper.repository.UserProductRepository;
import com.example.recipes_helper.services.RecipeService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService{

    @Autowired
	private RecipeRepository recipeRepository;

    @Autowired
	private ProductRepository productRepository;

    @Autowired
	private ListProductRepository listProductRepository;

    @Autowired
	private UserProductRepository userProductRepository;

    @Override
    public Recipe getRecipeById(Long idRecipe){
        return recipeRepository.findByRecipeId(idRecipe);
    }

    @Override
    public RecipeWithIngredientsDTO getRecipeWithIngredientsById(Long idRecipe, Long idUser){
        Recipe recipe = recipeRepository.findByRecipeId(idRecipe);
        if (recipe == null) {
			// обработка ситуации, когда рецепт не найден
			return null;
		}
        List<ListProduct> listProducts = listProductRepository.findByRecipe(idRecipe);
		List<IngredientDTO> ingredients = new ArrayList<IngredientDTO>();
        boolean canCook=true;
		for (ListProduct lp : listProducts) {
			Product product = productRepository.findByProductId(lp.getProductId());
            Optional<UserProduct> userProduct = userProductRepository.findByUserIdAndProductId(idUser, lp.getProductId());
            int missing = lp.getCount();
            if (userProduct.isPresent()){
                missing = Math.max(0, lp.getCount() - userProduct.get().getCount());
            }
            if (missing>0){
                canCook=false;
            }
			ingredients.add(new IngredientDTO(product.getProductId(), product.getProductName(), lp.getCount(), missing, product.getUnit(), product.getProductCategory()));
		}
		return new RecipeWithIngredientsDTO(idRecipe, recipe.getRecipeName(), recipe.getDescription(), recipe.getRecipeCategory(), ingredients, canCook);
    }

    @Override
    public List<Product> getProductsByRecipeId(Long idRecipe){
        List<ListProduct> listProducts=listProductRepository.findByRecipe(idRecipe);
        List<Product> products = new ArrayList<Product>();
	  	for (ListProduct lp : listProducts){
			products.add(productRepository.findByProductId(lp.getProductId()));
		}
        return products;
    }

    @Override
    public List<Recipe> getRecipesByCategory(RecipeCategory recipeCategory, ProductCategory productCategory){
        List<Recipe> recipes;
		if (recipeCategory!=null){ //если значение параметра пришло (не null)
			recipes = (List<Recipe>) recipeRepository.findByRecipeCategory(recipeCategory);
		} else{ //если параметр не пришел:
		    recipes = (List<Recipe>) recipeRepository.findAll();
        }

        if (productCategory == ProductCategory.Постное){ // должны вывестись рецепты, содержащие только постные продукты
            List <Recipe> result=new ArrayList<>();
            for (Recipe recipe : recipes){
                List<ListProduct> listProducts = listProductRepository.findByRecipe(recipe.getRecipeId());
                boolean isLean=true;
                for (ListProduct lp : listProducts) { //проходим по продуктам рецепта
                    Product product = lp.getProduct();
                    if (product.getProductCategory()!=ProductCategory.Постное){
                        isLean=false;
                        break;
                    }
                }
                if (isLean){
                    result.add(recipe);
                }
            }
            return result;
        }

        return recipes;
    }

    @Override
    public List<Recipe> getRecipesForUser(Long idUser, RecipeCategory recipeCategory, ProductCategory productCategory){
        List<Recipe> availableRecipes = new ArrayList<>();
		List<Recipe> allRecipesWithFilters = getRecipesByCategory(recipeCategory, productCategory);
		for (Recipe recipe : allRecipesWithFilters) {
			List<ListProduct> listProducts = listProductRepository.findByRecipe(recipe.getRecipeId());
			boolean canPrepare = true;
			for (ListProduct lp : listProducts) {
				Optional<UserProduct> userProduct = userProductRepository.findByUserIdAndProductId(idUser, lp.getProductId());
				if (!userProduct.isPresent() || userProduct.get().getCount() < lp.getCount()) {
					canPrepare = false;
					break;
				}
			}
			if (canPrepare) {
				availableRecipes.add(recipe);
			}
		}
        return availableRecipes;
    }

    @Override
    @Transactional // при начале выполнения метода начинается транзакция, а в конце заканчивается. если выбрасываем исключение, транзакция отменяется (rollback)
    public void decreaseProducts(Long idRecipe, Long idUser){
        List<ListProduct> recipeProducts=listProductRepository.findByRecipe(idRecipe);
        for (ListProduct recipeProduct : recipeProducts){
            Optional<UserProduct> userProductOptional=userProductRepository.findByUserIdAndProductId(idUser, recipeProduct.getProductId());
            if (!userProductOptional.isPresent()){ // если у пользователя нет этого продукта
                throw new EntityNotFoundException(
                    String.format("This user doesn't have this product (productId:%d, productName: %s). ", recipeProduct.getProductId(), recipeProduct.getProduct().getProductName()));
            }
            
            UserProduct userProduct=userProductOptional.get();
            if (userProduct.getCount() - recipeProduct.getCount() < 0){ // если у пользователя не хватает продуктов для рецепта
                throw new DataIntegrityViolationException(
                    String.format("This user doesn't have enough product to cook the recipe (productId:%d, productName: %s). ", recipeProduct.getProductId(), recipeProduct.getProduct().getProductName()));
            }
            // количество данного продукта уменьшается настолько, насколько необходимо для рецепта
            userProduct.setCount(userProduct.getCount() - recipeProduct.getCount()); 
            userProductRepository.save(userProduct);
        }
    }
}
