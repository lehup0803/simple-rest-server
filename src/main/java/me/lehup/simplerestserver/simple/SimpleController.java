package me.lehup.simplerestserver.simple;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@Log
@RestController
@RequestMapping("/v1")
public class SimpleController {


    @Autowired
    private SimpleReopository simpleReopository;

    @GetMapping("/api/simples")
    public Collection<Simple> getAllSimples() {
        return simpleReopository.findAll();
    }

    @GetMapping("/api/simples/{id}")
    public Simple getSimple (@PathVariable Long id){
        Optional<Simple> byId = simpleReopository.findById(id);

        Simple simple = new Simple();
        simple.setDataInteger(1111);
        simple.setDataString("test");
        simple.setId(0L);

        return byId.orElse(null);
    }

    @PostMapping("/api/simples")
    public Simple createSimple(@RequestBody Simple simple) {
        Simple saved = simpleReopository.saveAndFlush(simple);
        log.info(saved.toString());
        return saved;
    }

    @PatchMapping("/api/simples")
    public Simple updateSimple (@RequestBody Simple simple){
        Simple saved = simpleReopository.saveAndFlush(simple);
        log.info(saved.toString());
        return saved;
        }

    @DeleteMapping("/api/simples/{id}")
    public void deleteSimple (@PathVariable Long id){
        simpleReopository.deleteById(id);
        }
    }




