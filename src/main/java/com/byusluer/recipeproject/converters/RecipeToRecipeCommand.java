package com.byusluer.recipeproject.converters;


import com.byusluer.recipeproject.commands.RecipeCommand;
import com.byusluer.recipeproject.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {


    private final CategoryToCategoryCommand categoryCMDConverter;
    private final IngredientToIngredientCommand ingredientCmdCOnverter;

    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryCMDConverter, IngredientToIngredientCommand ingredientCmdCOnverter) {
        this.categoryCMDConverter = categoryCMDConverter;
        this.ingredientCmdCOnverter = ingredientCmdCOnverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {
        if (recipe == null){
            return null;
        }

        final  RecipeCommand recipeCommand = new RecipeCommand();


        recipeCommand.setId(recipe.getId());
        recipeCommand.setDescription(recipe.getDescription());
        recipeCommand.setCookTime(recipe.getCookTime());
        recipeCommand.setDirections(recipe.getDirections());
        recipeCommand.setDifficulty(recipe.getDifficulty());
        recipeCommand.setSource(recipeCommand.getSource());


        if (recipe.getCategories() != null && recipe.getCategories().size() >0){

               recipe.getCategories().forEach(category -> recipeCommand.getCategoryCommands().add(categoryCMDConverter.convert(category)));

        }

        if (recipe.getIngredients()!= null && recipe.getIngredients().size()>0){
            recipe.getIngredients().forEach(ingredients -> recipeCommand.getIngredientCommands().add(ingredientCmdCOnverter.convert(ingredients)));
        }

        return recipeCommand;
    }
}
