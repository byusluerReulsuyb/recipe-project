package com.byusluer.recipeproject.services;

import com.byusluer.recipeproject.commands.RecipeCommand;
import com.byusluer.recipeproject.domain.Recipe;

import java.util.Set;


public interface RecipeService  {

    Set<Recipe> getRecipies();
    Recipe findById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
    RecipeCommand findCommandById(Long id);
    void deletebyID(Long id);


}
