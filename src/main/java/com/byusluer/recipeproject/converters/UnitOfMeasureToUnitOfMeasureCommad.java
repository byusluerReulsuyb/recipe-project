package com.byusluer.recipeproject.converters;


import com.byusluer.recipeproject.commands.UnitOfMeasureCommand;
import com.byusluer.recipeproject.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


@Component
public class UnitOfMeasureToUnitOfMeasureCommad implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {

        if (unitOfMeasure == null){
            return null;
        }

        final UnitOfMeasureCommand uomCmd = new UnitOfMeasureCommand();

        uomCmd.setId(unitOfMeasure.getId());
        uomCmd.setDescription(unitOfMeasure.getDescription());



        return uomCmd;
    }
}

