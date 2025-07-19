package tacos.web.api.controller;

import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.GetMapping;

import reactor.core.publisher.Flux;
import tacos.data.entity.Taco;
import tacos.data.TacoRepository;

@RepositoryRestController
public class RecentTacosController {

    private TacoRepository tacoRepo;

    public RecentTacosController(TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }

    @GetMapping(path="/tacos/recent", produces="application/hal+json")
    public Flux<Taco> recentTacos() {
        return Flux.fromIterable(tacoRepo.findAll())
                .take(12);
    }
}