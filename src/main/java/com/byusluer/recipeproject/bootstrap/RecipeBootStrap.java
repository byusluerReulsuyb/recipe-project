package com.byusluer.recipeproject.bootstrap;
import com.byusluer.recipeproject.domain.*;
import com.byusluer.recipeproject.repositories.CategoryRepository;
import com.byusluer.recipeproject.repositories.RecipeRepository;
import com.byusluer.recipeproject.repositories.UnitOfMeasuereRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Data
@Component
public class RecipeBootStrap implements ApplicationListener<ContextRefreshedEvent> {


    private UnitOfMeasuereRepository unitOfMeasuereRepository;
    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;

    public RecipeBootStrap(UnitOfMeasuereRepository unitOfMeasuereRepository, CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.unitOfMeasuereRepository = unitOfMeasuereRepository;
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        recipeRepository.saveAll(getRecipies());
        //log.debug("Loading bootstrap class");

    }

    private List<Recipe> getRecipies() {

        List<Recipe> recipeList = new ArrayList<>();


        Optional<UnitOfMeasure> eachOpt = unitOfMeasuereRepository.findByDescription("Each");
        Optional<UnitOfMeasure> tableSpoonOpt = unitOfMeasuereRepository.findByDescription("Tablespon");
        Optional<UnitOfMeasure> teaSpoonOpt = unitOfMeasuereRepository.findByDescription("Teaspoon");
        Optional<UnitOfMeasure> dashOpt = unitOfMeasuereRepository.findByDescription("Dash");
        Optional<UnitOfMeasure> pintOpt = unitOfMeasuereRepository.findByDescription("Pint");
        Optional<UnitOfMeasure> cupOpt = unitOfMeasuereRepository.findByDescription("Cup");

        UnitOfMeasure each = eachOpt.get();
        UnitOfMeasure tableSpoon = tableSpoonOpt.get();
        UnitOfMeasure teaSpoon = teaSpoonOpt.get();
        UnitOfMeasure dash = dashOpt.get();
        UnitOfMeasure pint = pintOpt.get();
        UnitOfMeasure cup = cupOpt.get();

        Category amercican = categoryRepository.findByDescription("American").get();
        Category mexican = categoryRepository.findByDescription("Mexican").get();

        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setCookTime(23);
        guacRecipe.setPrepTime(12);
        guacRecipe.setDifficulty(Difficulty.MEDIUM);
        guacRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd");

        Notes guacNotes = new Notes();
        guacNotes.setRecipe(guacRecipe);
        guacRecipe.setNotes(guacNotes);

        guacRecipe.getIngredients().add(new Ingredients("ripe avocados", new BigDecimal(2), guacRecipe, each));
        guacRecipe.getIngredients().add(new Ingredients("Kosher salt", new BigDecimal(".5"), guacRecipe, teaSpoon));
        guacRecipe.getIngredients().add(new Ingredients("fresh lime juice or lemon juice", new BigDecimal(2), guacRecipe, tableSpoon));
        guacRecipe.getIngredients().add(new Ingredients("minced red onion or thinly sliced green onion", new BigDecimal(2), guacRecipe, pint));
        guacRecipe.getIngredients().add(new Ingredients("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), guacRecipe, dash));
        guacRecipe.getIngredients().add(new Ingredients("Cilantro", new BigDecimal(2), guacRecipe, teaSpoon));
        guacRecipe.getIngredients().add(new Ingredients("freshly grated black pepper", new BigDecimal(2), guacRecipe, dash));
        guacRecipe.getIngredients().add(new Ingredients("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), guacRecipe, each));

        guacRecipe.getCategories().add(amercican);
        guacRecipe.getCategories().add(mexican);

        recipeList.add(guacRecipe);

      /*  System.out.println("this is the id -> " + recipeList.get(0).getId());
        System.out.println("this is the id -> " + recipeList.get(0).getDescription());
        System.out.println("this is the id -> " + recipeList.get(0).getDirections()); */

        return recipeList;

    }

}
