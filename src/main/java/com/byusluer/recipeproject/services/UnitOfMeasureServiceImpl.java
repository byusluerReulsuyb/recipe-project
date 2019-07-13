package com.byusluer.recipeproject.services;

import com.byusluer.recipeproject.commands.UnitOfMeasureCommand;
import com.byusluer.recipeproject.converters.UnitOfMeasureToUnitOfMeasureCommad;
import com.byusluer.recipeproject.repositories.UnitOfMeasuereRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasuereRepository uomRepository;
    private final UnitOfMeasureToUnitOfMeasureCommad uomCommandConverter;

    public UnitOfMeasureServiceImpl(UnitOfMeasuereRepository uomRepository, UnitOfMeasureToUnitOfMeasureCommad uomCommandConverter) {
        this.uomRepository = uomRepository;
        this.uomCommandConverter = uomCommandConverter;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUom() {
        return StreamSupport.stream(uomRepository.findAll().spliterator(),false)
                .map(uomCommandConverter::convert).collect(Collectors.toSet());
    }
}
