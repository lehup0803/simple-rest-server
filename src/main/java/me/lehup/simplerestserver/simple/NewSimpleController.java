package me.lehup.simplerestserver.simple;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Log
@RestController
@RequestMapping("/v2")
public class NewSimpleController {


    @Autowired
    private SimpleReopository simpleReopository;

    @GetMapping("/api/simples")
    public Page<Simple> getAllSimples(Pageable pageable)
    { return simpleReopository.findAll(pageable); }



    @GetMapping("/api/simples/{id}")
    public ResponseEntity<Object> getSimple (@PathVariable Long id){
        Optional<Simple> byId = simpleReopository.findById(id);

        if(byId.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(byId.get());
        else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/api/simples")
    public ResponseEntity<Object> createSimple(@RequestBody Simple simple) {
  //      Optional<Simple> byId =  simpleReopository.findById(simple.getId());
  //      if(byId.isPresent())
  //          return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        Simple saved = simpleReopository.saveAndFlush(simple);
        log.info(saved.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PatchMapping("/api/simples")
    public ResponseEntity<Object> updateSimple (@RequestBody Simple simple){
        Optional<Simple> byId =  simpleReopository.findById(simple.getId());
        if(!byId.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        Simple saved = simpleReopository.saveAndFlush(simple);
        log.info(saved.toString());
        return ResponseEntity.status(HttpStatus.OK).body(saved);
        }

    @DeleteMapping("/api/simples/{id}")
    public ResponseEntity<Object> deleteSimple (@PathVariable Long id){
        Optional<Simple> byId =  simpleReopository.findById(id);
        if(byId.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        simpleReopository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }



}




