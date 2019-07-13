package com.byusluer.recipeproject.services;

import com.byusluer.recipeproject.commands.IngredientCommand;
import com.byusluer.recipeproject.converters.IngredientCommandToIngredient;
import com.byusluer.recipeproject.converters.IngredientToIngredientCommand;
import com.byusluer.recipeproject.domain.Ingredients;
import com.byusluer.recipeproject.domain.Recipe;
import com.byusluer.recipeproject.repositories.RecipeRepository;
import com.byusluer.recipeproject.repositories.UnitOfMeasuereRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {


    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasuereRepository uomRepository;


    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient, RecipeRepository recipeRepository, UnitOfMeasuereRepository uomRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.recipeRepository = recipeRepository;
        this.uomRepository = uomRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIntegrationId(Long recipeId, Long ingredientId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()) {

            //todo -> impl error handling

            log.error("recipe with the " + recipeId + " not found");
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();
        if (!ingredientCommandOptional.isPresent()) {
            log.error("ingredient with the " + ingredientId + ingredientId);
        }

        return ingredientCommandOptional.get();
    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());
        if (!recipeOptional.isPresent()) {

            log.error("Recipe not found for id:" + command.getRecipeId());
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredients> ingredientsOptional = recipe.getIngredients().stream()
                    .filter(ingredients -> ingredients.getId().equals(command.getId())).findFirst();

            if (ingredientsOptional.isPresent()) {
                Ingredients foundIngredient = ingredientsOptional.get();
                foundIngredient.setDescription(command.getDescription());
                foundIngredient.setAmount(command.getAmount());
                foundIngredient.setUnitOfMeasure(uomRepository.findById(command.getUom().getId())
                        .orElseThrow(() -> new RuntimeException("UOM Not Found")));
            } else {
                Ingredients ingredients = ingredientCommandToIngredient.convert(command);
                ingredients.setRecipe(recipe);
                recipe.addIngredient(ingredients);
            }

            Recipe saveRecipe = recipeRepository.save(recipe);
            Optional<Ingredients> savedIngredientOptional = saveRecipe.getIngredients()
                    .stream().filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId())).findFirst();

            return  ingredientToIngredientCommand.convert(savedIngredientOptional.get());
        }


    }


    @Override
    public void deleteById(Long recipeId, Long ingredientId) {

        log.debug("deleting ingredients: " + "recipe id:" + recipeId + ":" + ingredientId);

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (recipeOptional.isPresent()){
            log.debug("found ingredient for Recipe:" + recipeOptional.get().getId());

            Optional<Ingredients> ingredientsOptional = recipeOptional.get().getIngredients().stream()
                    .filter(ingredients -> ingredients.getId().equals(ingredientId)).findFirst();

            if (ingredientsOptional.isPresent()){

                log.debug("ingredient found:" +ingredientsOptional.get().getId());

                Ingredients ingredientsTodelete = ingredientsOptional.get();
                ingredientsTodelete.setRecipe(null);
                recipeOptional.get().getIngredients().remove(ingredientsOptional.get());
                recipeRepository.save(recipeOptional.get());

            }
            else {
                log.debug("recipe not found darling!");
            }
        }

    }
}
