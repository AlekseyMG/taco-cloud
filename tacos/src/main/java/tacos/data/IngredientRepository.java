package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.data.entity.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
