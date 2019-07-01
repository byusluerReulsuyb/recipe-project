package com.byusluer.recipeproject.controller;

import com.byusluer.recipeproject.domain.Recipe;
import com.byusluer.recipeproject.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    IndexController indexController;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void mockMvcTest() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
       mockMvc.perform(get("/"))
               .andExpect(status().isOk())
                       .andExpect(view().name("index.html"));

    }

    @Test
    public void getIndexPage() {

        // Given

        Recipe recipe = new Recipe();
        Recipe recipe1 = new Recipe();

        Set<Recipe> recipes = new HashSet<>();

        recipes.add(recipe);
        recipes.add(recipe1);

        when(recipeService.getRecipies()).thenReturn(recipes);
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        // when

        String page = indexController.getIndexPage(model);

        // then

        assertEquals("index",page);
        verify(recipeService,times(1)).getRecipies();

        verify(model,times(1)).addAttribute(eq("recipies"),argumentCaptor.capture());

        Set<Recipe> capturValue = argumentCaptor.getValue();

        assertEquals(2,capturValue.size());




    }
}