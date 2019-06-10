package com.byusluer.recipeproject.services;

import com.byusluer.recipeproject.domain.Recipe;
import com.byusluer.recipeproject.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class RecipeServisImpl implements RecipeService {

    private RecipeRepository recipeRepository;


    public RecipeServisImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipies() {

        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }
}
