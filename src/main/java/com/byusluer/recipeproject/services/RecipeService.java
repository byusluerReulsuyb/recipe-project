package com.byusluer.recipeproject.services;

import com.byusluer.recipeproject.domain.Recipe;

import java.util.Set;


public interface RecipeService  {

    Set<Recipe> getRecipies();
}
