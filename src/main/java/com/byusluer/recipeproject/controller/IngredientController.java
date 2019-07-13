package com.byusluer.recipeproject.controller;


import com.byusluer.recipeproject.commands.IngredientCommand;
import com.byusluer.recipeproject.services.IngredientService;
import com.byusluer.recipeproject.services.RecipeService;
import com.byusluer.recipeproject.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
public class IngredientController {

    private final IngredientService ingredientService;
    private final RecipeService recipeService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(IngredientService ingredientService, RecipeService recipeService, UnitOfMeasureService unitOfMeasureService) {
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
        this.unitOfMeasureService = unitOfMeasureService;
    }


    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient")

    public String listIngredients(@PathVariable String recipeId, Model model) {
        log.debug("Getting ingredient list for recipe id:" + recipeId);

        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));

        return "recipe/ingredient/list";

    }


    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/show")
    public String showRecipeIngredient(@PathVariable String recipeId,
                                       @PathVariable String id, Model model) {

        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIntegrationId(Long.valueOf(recipeId), Long.valueOf(id)));

        return "recipe/ingredient/show";

    }


    @GetMapping
    @RequestMapping("recipe/{recipeID}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable String recipeID, @PathVariable String id, Model model) {

        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIntegrationId(Long.valueOf(recipeID), Long.valueOf(id)));
        model.addAttribute("uomList", unitOfMeasureService.listAllUom());
        return "recipe/ingredient/ingredientform";

    }


    @PostMapping("recipe/{recipeID/ingredient}")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command) {

        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        log.debug("saved recipe id: " + savedCommand.getRecipeId());
        log.debug("saved ingredient id: " + savedCommand.getId());

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";


    }

}
