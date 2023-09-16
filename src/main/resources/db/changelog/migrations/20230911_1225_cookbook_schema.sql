-- liquibase formatted sql

-- changeset pauline_roehr:20230911_1225_cookbook_schema.sql
CREATE SCHEMA platepal_recipes;
CREATE TYPE platepal_recipes.unit_type AS enum ('WEIGHT', 'VOLUME');

CREATE TABLE platepal_recipes.recipe
(
    recipe_id   bigserial
        CONSTRAINT recipe_pk
            PRIMARY KEY,
    title       text,
    yields      text,
    serves      integer,
    description text,
    cook_time   interval,
    prep_time   interval,
    active_time interval,
    total_time  interval,
    created_at  timestamp,
    created_by  bigint,
    updated_at  timestamp
);

COMMENT ON COLUMN platepal_recipes.recipe.created_by IS 'account_id';

CREATE INDEX recipe_title_index
    ON platepal_recipes.recipe (title);

CREATE TABLE platepal_recipes.unit
(
    unit_id      serial
        CONSTRAINT unit_pk
            PRIMARY KEY,
    unit_type    platepal_recipes.unit_type,
    fulltext     text,
    abbreviation text
);

CREATE TABLE platepal_recipes.image
(
    image_id bigserial
        CONSTRAINT image_pk
            PRIMARY KEY,
    path     text
);

CREATE TABLE platepal_recipes.ingredient
(
    ingredient_id    bigserial
        CONSTRAINT ingredient_pk
            PRIMARY KEY,
    ingredient_name  text,
    default_image_id bigint
        CONSTRAINT ingredient_image_image_id_fk
            REFERENCES platepal_recipes.image
            ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE INDEX ingredient_ingredient_name_index
    ON platepal_recipes.ingredient (ingredient_name);

CREATE TABLE platepal_recipes.recipe_image
(
    image_id  bigint NOT NULL
        CONSTRAINT recipe_image_image_image_id_fk
            REFERENCES platepal_recipes.image
            ON UPDATE CASCADE ON DELETE CASCADE,
    recipe_id bigint NOT NULL
        CONSTRAINT recipe_image_recipe_recipe_id_fk
            REFERENCES platepal_recipes.recipe
            ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT recipe_image_pk
        PRIMARY KEY (image_id, recipe_id)
);

CREATE INDEX recipe_image_recipe_id_index
    ON platepal_recipes.recipe_image (recipe_id);

CREATE TABLE platepal_recipes.recipe_ingredient_list
(
    list_id    bigserial
        CONSTRAINT recipe_ingredient_list_pk
            PRIMARY KEY,
    list_title text,
    recipe_id  bigint
        CONSTRAINT recipe_ingredient_list_recipe_recipe_id_fk
            REFERENCES platepal_recipes.recipe
            ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE INDEX recipe_ingredient_list_recipe_id_index
    ON platepal_recipes.recipe_ingredient_list (recipe_id);

CREATE TABLE platepal_recipes.recipe_ingredient
(
    ingredient_id      bigint NOT NULL
        CONSTRAINT recipe_ingredient_ingredient_ingredient_id_fk
            REFERENCES platepal_recipes.ingredient
            ON UPDATE CASCADE ON DELETE RESTRICT,
    ingredient_list_id bigint NOT NULL
        CONSTRAINT recipe_ingredient_recipe_ingredient_list_list_id_fk
            REFERENCES platepal_recipes.recipe_ingredient_list
            ON UPDATE CASCADE ON DELETE CASCADE,
    quantity           integer,
    unit_id            integer
        CONSTRAINT recipe_ingredient_unit_unit_id_fk
            REFERENCES platepal_recipes.unit
            ON UPDATE CASCADE ON DELETE RESTRICT,
    process            text,
    color              text,
    part               text,
    taste              text,
    purpose            text,
    CONSTRAINT recipe_ingredient_pk
        PRIMARY KEY (ingredient_id, ingredient_list_id)
);

CREATE TABLE platepal_recipes.recipe_step
(
    step_id     bigserial
        CONSTRAINT recipe_step_pk
            PRIMARY KEY,
    recipe_id   bigint
        CONSTRAINT recipe_step_recipe_recipe_id_fk
            REFERENCES platepal_recipes.recipe
            ON UPDATE CASCADE ON DELETE CASCADE,
    step_number integer,
    step_text   text,
    step_header text
);

CREATE INDEX recipe_step_recipe_id_index
    ON platepal_recipes.recipe_step (recipe_id);

CREATE TABLE platepal_recipes.recipe_note
(
    note_id   bigserial
        CONSTRAINT recipe_note_pk
            PRIMARY KEY,
    recipe_id bigint
        CONSTRAINT recipe_note_recipe_recipe_id_fk
            REFERENCES platepal_recipes.recipe
            ON UPDATE CASCADE ON DELETE CASCADE,
    note_text text
);

CREATE TABLE platepal_recipes.recipe_tag
(
    tag_title text   NOT NULL,
    recipe_id bigint NOT NULL
        CONSTRAINT recipe_tag_recipe_recipe_id_fk
            REFERENCES platepal_recipes.recipe
            ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT recipe_tag_pk
        PRIMARY KEY (recipe_id, tag_title)
);

CREATE INDEX recipe_tag_recipe_id_index
    ON platepal_recipes.recipe_tag (recipe_id);

CREATE INDEX recipe_tag_tag_title_index
    ON platepal_recipes.recipe_tag (tag_title);


CREATE SCHEMA platepal_collections;
CREATE TABLE platepal_collections.collection
(
    collection_id bigserial
        CONSTRAINT collection_pk
            PRIMARY KEY,
    created_by    bigint,
    title         text,
    created_at    timestamp,
    public        boolean,
    updated_at    timestamp,
    parent_id     integer
        CONSTRAINT collection_collection_collection_id_fk
            REFERENCES platepal_collections.collection
            ON UPDATE CASCADE ON DELETE SET NULL,
    position      integer
);

COMMENT ON COLUMN platepal_collections.collection.created_by IS 'account_id';

CREATE TABLE platepal_collections.collection_tag
(
    collection_id bigint NOT NULL
        CONSTRAINT collection_tag_collection_collection_id_fk
            REFERENCES platepal_collections.collection
            ON UPDATE CASCADE ON DELETE CASCADE,
    tag_title     text   NOT NULL,
    CONSTRAINT collection_tag_pk
        PRIMARY KEY (collection_id, tag_title)
);

CREATE INDEX collection_tag_collection_id_index
    ON platepal_collections.collection_tag (collection_id);

CREATE INDEX collection_tag_tag_title_index
    ON platepal_collections.collection_tag (tag_title);

CREATE TABLE platepal_collections.collection_recipe_entry
(
    collection_id bigint NOT NULL
        CONSTRAINT collection_recipe_entry_collection_collection_id_fk
            REFERENCES platepal_collections.collection
            ON UPDATE CASCADE ON DELETE CASCADE,
    recipe_id     bigint NOT NULL
        CONSTRAINT collection_recipe_entry_recipe_recipe_id_fk
            REFERENCES platepal_recipes.recipe
            ON UPDATE CASCADE ON DELETE CASCADE,
    position      integer,
    CONSTRAINT collection_recipe_entry_pk
        PRIMARY KEY (collection_id, recipe_id)
);

CREATE INDEX collection_recipe_entry_collection_id_index
    ON platepal_collections.collection_recipe_entry (collection_id);

CREATE INDEX collection_recipe_entry_recipe_id_index
    ON platepal_collections.collection_recipe_entry (recipe_id);

CREATE TABLE platepal_collections.account_collection
(
    account_id    bigint NOT NULL
        CONSTRAINT account_collection_account_account_id_fk
            REFERENCES platepal_accounts.account
            ON UPDATE CASCADE ON DELETE CASCADE,
    collection_id bigint NOT NULL
        CONSTRAINT account_collection_collection_collection_id_fk
            REFERENCES platepal_collections.collection
            ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT account_collection_pk
        PRIMARY KEY (collection_id, account_id)
);

CREATE INDEX account_collection_account_id_index
    ON platepal_collections.account_collection (account_id);

