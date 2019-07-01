package com.byusluer.recipeproject.converters;

import com.byusluer.recipeproject.commands.IngredientCommand;
import com.byusluer.recipeproject.domain.Ingredients;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredients> {

    private UnitOfMeasureCommandToUoM uomConverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUoM uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Nullable
    @Override
    public Ingredients convert(IngredientCommand  source) {

        if (source==null){
            return null;
        }

        final Ingredients ingredients =  new Ingredients();

        ingredients.setId(source.getId());
        ingredients.setAmount(source.getAmount());
        ingredients.setDescription(source.getDescription());
        ingredients.setUnitOfMeasure(uomConverter.convert(source.getUom()));

        return ingredients;


    }
}
