package tacos.web.api;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import tacos.Taco;

public class TacoResource extends RepresentationModel<TacoResource> {

    private static final IngredientResourceAssembler ingredientAssembler = new IngredientResourceAssembler();

    @Getter
    private final String name;
    @Getter
    private final Date createdAt;
    @Getter
    private final List<IngredientResource> ingredients;

    public TacoResource(Taco taco) {
        this.name = taco.getName();
        this.createdAt = taco.getCreatedAt();
        this.ingredients = ingredientAssembler.toCollectionModel(taco.getIngredients()).getContent().stream().toList();
    }

}