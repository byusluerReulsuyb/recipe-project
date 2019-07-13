package com.byusluer.recipeproject.services;

import com.byusluer.recipeproject.commands.RecipeCommand;
import com.byusluer.recipeproject.converters.RecipeCommandToRecipe;
import com.byusluer.recipeproject.converters.RecipeToRecipeCommand;
import com.byusluer.recipeproject.domain.Recipe;
import com.byusluer.recipeproject.exceptions.NotFoundException;
import com.byusluer.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Slf4j
@Service
public class RecipeServisImpl implements RecipeService {

    private RecipeRepository recipeRepository;
    private final RecipeToRecipeCommand recipeCommandConverter;
    private final RecipeCommandToRecipe recipeConverter;


    public RecipeServisImpl(RecipeRepository recipeRepository, RecipeToRecipeCommand recipeCommandConverter, RecipeCommandToRecipe recipeConverter) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandConverter = recipeCommandConverter;
        this.recipeConverter = recipeConverter;
    }

    @Override
    public Set<Recipe> getRecipies() {

        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public Recipe findById(Long id) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if (!recipeOptional.isPresent()) {
            throw new NotFoundException("Recipe Not Found for ID value:" + id.toString());
        }

        return recipeOptional.get();
    }

    @Transactional
    @Override
    public RecipeCommand findCommandById(Long id) {

        return recipeCommandConverter.convert(findById(id));
    }

    @Override
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        Recipe detachedRecipe = recipeConverter.convert(recipeCommand);
        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeId:" + savedRecipe.getId());

        return recipeCommandConverter.convert(savedRecipe);
    }

    @Override
    public void deletebyID(Long id) {
        recipeRepository.deleteById(id);
    }
}

