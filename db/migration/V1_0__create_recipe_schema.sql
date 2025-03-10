CREATE TABLE IF NOT EXISTS recipe (
 
    recipe_id serial NOT NULL PRIMARY KEY,
    recipe_name varchar,
    description varchar,
    recipe_category varchar
 
);