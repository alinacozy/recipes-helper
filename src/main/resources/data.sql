INSERT INTO "recipes-helper-db".products (product_id, product_name, unit, product_category) VALUES (nextval('product_id_seq'), 'Капуста', 'грамм', 1);
INSERT INTO "recipes-helper-db".products (product_id, product_name, unit, product_category) VALUES (nextval('product_id_seq'), 'Яйцо', 'штук', 0);
INSERT INTO "recipes-helper-db".products (product_id, product_name, unit, product_category) VALUES (nextval('product_id_seq'), 'Мука', 'грамм', 1);
INSERT INTO "recipes-helper-db".users (user_id, user_name, password)  VALUES (nextval('user_id_seq'), 'Sonia', 'Sonia123');
INSERT INTO "recipes-helper-db".recipe (recipe_id, recipe_name, description, recipe_category) VALUES (nextval('recipe_id_seq'), 'Пироги с капустой', 'Вкусные и быстрые пироги с капустой!', 6);
INSERT INTO "recipes-helper-db".user_product (user_id, product_id, count)  VALUES (1, 1, 500);
INSERT INTO "recipes-helper-db".user_product (user_id, product_id, count)  VALUES (1, 2, 5);
INSERT INTO "recipes-helper-db".user_product (user_id, product_id, count)  VALUES (1, 3, 500);

INSERT INTO "recipes-helper-db".list_product (recipe_id, product_id, count) VALUES (1, 1, 200); 
INSERT INTO "recipes-helper-db".list_product (recipe_id, product_id, count) VALUES (1, 2, 3); 
INSERT INTO "recipes-helper-db".list_product (recipe_id, product_id, count) VALUES (1, 3, 400); 
INSERT INTO "recipes-helper-db".user_history (user_id, recipe_id, date, rate) VALUES (1, 1, '2025-03-19', 5)
