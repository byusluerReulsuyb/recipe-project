package com.byusluer.recipeproject.converters;


import com.byusluer.recipeproject.commands.RecipeCommand;
import com.byusluer.recipeproject.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {


    private  final  CategoryCommandToCategory categoryConverter;
    private  final  IngredientCommandToIngredient ingredientConverter;


    public RecipeCommandToRecipe(CategoryCommandToCategory categoryConverter, IngredientCommandToIngredient ingredientConverter) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source){

        if (source == null){
            return null;
        }

        final Recipe recipe = new Recipe();

        recipe.setId(source.getId());
        recipe.setCookTime(source.getCookTime());
        recipe.setDirections(source.getDirections());
        recipe.setUrl(source.getUrl());
        recipe.setDescription(source.getDescription());
        recipe.setPrepTime(source.getPrepTime());


        if (source.getIngredientCommands() != null && source.getIngredientCommands().size() >0){

            source.getIngredientCommands().forEach(ingredint -> recipe.getIngredients().add(ingredientConverter.convert(ingredint)));

        }



        return recipe;

    }

}
