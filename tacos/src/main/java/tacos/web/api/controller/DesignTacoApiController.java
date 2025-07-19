package tacos.web.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tacos.data.entity.Taco;
import tacos.data.TacoRepository;

@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoApiController {
    private TacoRepository tacoRepo;

    public DesignTacoApiController(TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }

    @GetMapping("/recent")
    public Flux<Taco> recentTacos() {
        return Flux.fromIterable(tacoRepo.findAll()).take(12);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Taco> postTaco(@RequestBody Taco taco) {
        return Mono.just(tacoRepo.save(taco));
    }

    @GetMapping("/{id}")
    public Mono<Taco> tacoById(@PathVariable("id") Long id) {
        return Mono.just(tacoRepo.findById(id).orElseThrow());
    }
}

