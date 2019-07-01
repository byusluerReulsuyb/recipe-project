package com.byusluer.recipeproject.commands;


import com.byusluer.recipeproject.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {

    private Long id;
    private String description;
    private Integer cookTime;
    private Difficulty difficulty;
    private String source;
    private Integer prepTime;
    private NotesCommand notesCommand;
    private Set<CategoryCommand> categoryCommands = new HashSet<>();
    private Set<IngredientCommand> ingredientCommands = new HashSet<>();
    private String url;
    private String directions;


}
