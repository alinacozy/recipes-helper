package com.example.recipes_helper.services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.recipes_helper.model.ListProduct;
import com.example.recipes_helper.model.Product;
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
    public List<Product> getProductsByRecipeId(Long idRecipe){
        List<ListProduct> listProducts=listProductRepository.findByRecipe(idRecipe);
        List<Product> products = new ArrayList<Product>();
	  	for (ListProduct lp : listProducts){
			products.add(productRepository.findByProductId(lp.getProductId()));
		}
        return products;
    }

    @Override
    public List<Recipe> getRecipesByCategory(RecipeCategory recipeCategory){
		if (recipeCategory!=null){ //если значение параметра пришло (не null)
			return (List<Recipe>) recipeRepository.findByRecipeCategory(recipeCategory);
		}
		//если параметр не пришел:
		return (List<Recipe>) recipeRepository.findAll();
    }

    @Override
    public List<Recipe> getRecipesForUser(Long idUser){
        List<Recipe> availableRecipes = new ArrayList<>();
		Iterable<Recipe> allRecipes = recipeRepository.findAll();
		for (Recipe recipe : allRecipes) {
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
