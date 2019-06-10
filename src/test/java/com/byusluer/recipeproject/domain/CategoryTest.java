package com.byusluer.recipeproject.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryTest {

    Category category;


    @Before
    public void  setUp(){

        category = new Category();
    }

    @Test
    public void getId() {

        Long idValue = 4L;

        category.setId(idValue);

        assertEquals(idValue, category.getId());
    }

    @Test
    public void getDescription() {

        String descValue = "steak Byusluer the chief";

        category.setDescription(descValue);

        assertEquals(descValue,category.getDescription());
    }

    @Test
    public void getRecipes() {
    }
}