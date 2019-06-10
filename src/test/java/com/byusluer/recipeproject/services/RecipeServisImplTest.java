package com.byusluer.recipeproject.services;

import com.byusluer.recipeproject.domain.Recipe;
import com.byusluer.recipeproject.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class RecipeServisImplTest {

    RecipeServisImpl recipeServis;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        recipeServis = new RecipeServisImpl(recipeRepository);
    }

    @Test
    public void getRecipies() {

        Set<Recipe> recipes = new HashSet<>();
        Recipe recipe = new Recipe();
        recipes.add(recipe);
        when(recipeRepository.findAll()).thenReturn(recipes);

        assertEquals(recipes.size(),1);
        verify(recipeRepository,times(1)).findAll();



    }
}