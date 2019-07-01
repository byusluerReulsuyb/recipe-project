package com.byusluer.recipeproject.converters;

import com.byusluer.recipeproject.commands.IngredientCommand;
import com.byusluer.recipeproject.domain.Ingredients;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


@Component
public class IngredientToIngredientCommand implements Converter<Ingredients, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommad unitOfMeasueCmdConveter;




    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommad unitOfMeasueCmdConveter) {
        this.unitOfMeasueCmdConveter = unitOfMeasueCmdConveter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredients ingredients) {

        if (ingredients == null){
            return null;
        }

        IngredientCommand ingredientCommand = new IngredientCommand();

        ingredientCommand.setId(ingredients.getId());
        ingredientCommand.setAmount(ingredients.getAmount());
        ingredientCommand.setDescription(ingredients.getDescription());
        ingredientCommand.setUom(unitOfMeasueCmdConveter.convert(ingredients.getUnitOfMeasure()));


        return ingredientCommand;

    }
}
