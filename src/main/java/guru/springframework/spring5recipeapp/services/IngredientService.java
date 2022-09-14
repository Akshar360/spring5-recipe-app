package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import org.springframework.transaction.annotation.Transactional;

public interface IngredientService
{
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    @Transactional
    IngredientCommand saveIngredientCommand(IngredientCommand command);


    void deleteById(Long recipeId, Long idToDelete);
}
