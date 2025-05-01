package tacos.data;

import org.springframework.data.jpa.repository.JpaRepository;
import tacos.data.entity.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, String> {
}
