package tacos.web.api;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import tacos.data.entity.Taco;
import tacos.web.api.controller.DesignTacoApiController;

public class TacoResourceAssembler
        extends RepresentationModelAssemblerSupport<Taco, TacoResource> {

    public TacoResourceAssembler() {
        super(DesignTacoApiController.class, TacoResource.class);
    }

    @Override
    protected TacoResource instantiateModel(Taco taco) {
        return new TacoResource(taco);
    }

    @Override
    public TacoResource toModel(Taco taco) {
        return createModelWithId(taco.getId(), taco);
    }
}