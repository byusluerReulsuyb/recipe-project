package com.byusluer.recipeproject.repositories;

import com.byusluer.recipeproject.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasuereRepositoryTest {


    @Autowired
    UnitOfMeasuereRepository unitOfMeasuereRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByDescriptionCup() throws  Exception{
        Optional<UnitOfMeasure> uom = unitOfMeasuereRepository.findByDescription("Cup");

        assertEquals("Cup",uom.get().getDescription());

    }


    @Test
    public void findByDescriptionTutam() throws  Exception{
        Optional<UnitOfMeasure> uom = unitOfMeasuereRepository.findByDescription("Tutam");

        assertEquals("Tutam",uom.get().getDescription());

    }
}