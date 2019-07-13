package com.byusluer.recipeproject.services;

import com.byusluer.recipeproject.commands.IngredientCommand;

public interface IngredientService {


    IngredientCommand findByRecipeIdAndIntegrationId(Long recipeId, Long ingredientId);
    IngredientCommand saveIngredientCommand (IngredientCommand command);
    void deleteById (Long recipeId,Long ingredientId);

}
