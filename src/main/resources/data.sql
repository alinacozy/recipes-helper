INSERT INTO "recipes-helper-db".products ( product_name, unit, product_category) VALUES ('Капуста', 'грамм', 1);
INSERT INTO "recipes-helper-db".products ( product_name, unit, product_category) VALUES ('Яйцо', 'штук', 0);
INSERT INTO "recipes-helper-db".products (product_name, unit, product_category) VALUES ('Мука', 'грамм', 1);

INSERT INTO "recipes-helper-db".users (user_name, password) VALUES ('Alina', 'Alina123');
INSERT INTO "recipes-helper-db".users (user_name, password)  VALUES ('Sonia', 'Sonia123');
INSERT INTO "recipes-helper-db".recipe (recipe_name, description, recipe_category) VALUES ('Пироги с капустой', 'Вкусные и быстрые пироги с капустой!', 6);
INSERT INTO "recipes-helper-db".user_product (user_id, product_id, count)  VALUES (1, 1, 500);
INSERT INTO "recipes-helper-db".user_product (user_id, product_id, count)  VALUES (1, 2, 5);
INSERT INTO "recipes-helper-db".user_product (user_id, product_id, count)  VALUES (1, 3, 500);

INSERT INTO "recipes-helper-db".list_product (recipe_id, product_id, count) VALUES (1, 1, 200); 
INSERT INTO "recipes-helper-db".list_product (recipe_id, product_id, count) VALUES (1, 2, 3); 
INSERT INTO "recipes-helper-db".list_product (recipe_id, product_id, count) VALUES (1, 3, 400); 
INSERT INTO "recipes-helper-db".user_history (user_id, recipe_id, date, rate) VALUES (1, 1, '2025-03-19', 5);

INSERT INTO "recipes-helper-db".recipe (recipe_name, description, recipe_category) VALUES ('Картофель по-деревенски', 'Простое, сытное и вкусное блюдо из картофеля, великолепный гарнир ко многим продуктам.', 2);
INSERT INTO "recipes-helper-db".recipe (recipe_name, description, recipe_category) VALUES ('Суп куриный с вермишелью', 'Куриный суп с вермишелью с удовольствием съедят и дети, и взрослые. Рецепт супа из куриного филе очень прост.', 0);
INSERT INTO "recipes-helper-db".products (product_name, unit, product_category) VALUES ( 'Курица', 'грамм', 0);
INSERT INTO "recipes-helper-db".products (product_name, unit, product_category) VALUES ( 'Картофель', 'шт', 1);
INSERT INTO "recipes-helper-db".products (product_name, unit, product_category) VALUES ( 'Морковь', 'шт', 1);
INSERT INTO "recipes-helper-db".products (product_name, unit, product_category) VALUES ( 'Лук репчатый', 'шт', 1);
INSERT INTO "recipes-helper-db".products (product_name, unit, product_category) VALUES ( 'Говядина', 'грамм', 0);
INSERT INTO "recipes-helper-db".products (product_name, unit, product_category) VALUES ( 'Молоко', 'мл', 0);
INSERT INTO "recipes-helper-db".products (product_name, unit, product_category) VALUES ( 'Сахар', 'грамм', 1);
INSERT INTO "recipes-helper-db".products (product_name, unit, product_category) VALUES ( 'Вода', 'мл', 1);
INSERT INTO "recipes-helper-db".products (product_name, unit, product_category) VALUES ( 'Вермишель', 'грамм', 1);
INSERT INTO "recipes-helper-db".products (product_name, unit, product_category) VALUES ( 'Соль', 'грамм', 1);
INSERT INTO "recipes-helper-db".products (product_name, unit, product_category) VALUES ( 'Чеснок', 'штук', 1);
INSERT INTO "recipes-helper-db".products (product_name, unit, product_category) VALUES ( 'Паприка', 'грамм', 1);
INSERT INTO "recipes-helper-db".products (product_name, unit, product_category) VALUES ( 'Перец молотый', 'грамм', 1);
INSERT INTO "recipes-helper-db".products (product_name, unit, product_category) VALUES ( 'Масло растительное', 'мл', 1);

INSERT INTO "recipes-helper-db".list_product (recipe_id, product_id, count) VALUES (2, 5, 8); 
INSERT INTO "recipes-helper-db".list_product (recipe_id, product_id, count) VALUES (2, 17, 60); 
INSERT INTO "recipes-helper-db".list_product (recipe_id, product_id, count) VALUES (2, 13, 10); 
INSERT INTO "recipes-helper-db".list_product (recipe_id, product_id, count) VALUES (2, 15, 5); 
INSERT INTO "recipes-helper-db".list_product (recipe_id, product_id, count) VALUES (2, 16, 5); 
INSERT INTO "recipes-helper-db".list_product (recipe_id, product_id, count) VALUES (2, 14, 1); 

INSERT INTO "recipes-helper-db".list_product (recipe_id, product_id, count) VALUES (3, 4, 500);
INSERT INTO "recipes-helper-db".list_product (recipe_id, product_id, count) VALUES (3, 5, 3);
INSERT INTO "recipes-helper-db".list_product (recipe_id, product_id, count) VALUES (3, 6, 1);
INSERT INTO "recipes-helper-db".list_product (recipe_id, product_id, count) VALUES (3, 7, 1);
INSERT INTO "recipes-helper-db".list_product (recipe_id, product_id, count) VALUES (3, 12, 100);
INSERT INTO "recipes-helper-db".list_product (recipe_id, product_id, count) VALUES (3, 16, 5);
INSERT INTO "recipes-helper-db".list_product (recipe_id, product_id, count) VALUES (3, 13, 5);
INSERT INTO "recipes-helper-db".list_product (recipe_id, product_id, count) VALUES (3, 17, 40);
INSERT INTO "recipes-helper-db".list_product (recipe_id, product_id, count) VALUES (3, 11, 2000);

