package com.byusluer.recipeproject.repositories;

import com.byusluer.recipeproject.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasuereRepository extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure>findByDescription(String description);
}
