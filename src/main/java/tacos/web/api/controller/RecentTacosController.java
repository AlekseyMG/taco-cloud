package tacos.web.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import tacos.data.entity.Taco;
import tacos.data.TacoRepository;
import tacos.web.api.TacoResource;
import tacos.web.api.TacoResourceAssembler;

@RepositoryRestController
public class RecentTacosController {

    private TacoRepository tacoRepo;

    public RecentTacosController(TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }

    @GetMapping(path = "/tacos/recent", produces = "application/hal+json")
    public ResponseEntity<CollectionModel<TacoResource>> recentTacos() {
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());
        List<Taco> tacos = tacoRepo.findAll(page).getContent();

        CollectionModel<TacoResource> recentResources =
                new TacoResourceAssembler().toCollectionModel(tacos);

        recentResources.add(
                linkTo(methodOn(RecentTacosController.class).recentTacos())
                        .withRel("recents"));
        return new ResponseEntity<>(recentResources, HttpStatus.OK);
    }

}