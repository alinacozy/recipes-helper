INSERT INTO "recipes-helper-db".products (product_id, product_name, unit, product_category) VALUES (3, 'Капуста', 'грамм', 1);
INSERT INTO "recipes-helper-db".products (product_id, product_name, unit, product_category) VALUES (2, 'Яйцо', 'штук', 0);
INSERT INTO "recipes-helper-db".products (product_id, product_name, unit, product_category) VALUES (5, 'Мука', 'грамм', 1);
INSERT INTO "recipes-helper-db".users (user_id, user_name, password)  VALUES (3, 'Sonia', 'Sonia123');
INSERT INTO "recipes-helper-db".recipe (recipe_id, recipe_name, description, recipe_category) VALUES (1, 'Пироги с капустой', 'Вкусные и быстрые пироги с капустой!', '6');
INSERT INTO "recipes-helper-db".user_product (user_id, product_id, count)  VALUES (3, 3, 500);
INSERT INTO "recipes-helper-db".list_product (recipe_id, product_id, count) VALUES (1, 3, 200); 
INSERT INTO "recipes-helper-db".list_product (recipe_id, product_id, count) VALUES (1, 2, 3); 
INSERT INTO "recipes-helper-db".list_product (recipe_id, product_id, count) VALUES (1, 5, 400); 
--почему-то пока не добавляет историю :/ INSERT INTO "recipes-helper-db".user_history (user_id, recipe_id, date, rate) VALUES (1, 3, '2025-03-19', 5)
