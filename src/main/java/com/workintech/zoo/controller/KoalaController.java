package com.workintech.zoo.controller;
import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.entity.Koala;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {

    public Map<Integer, Koala> koalas;

    @PostConstruct
    public void init() {
        koalas = new HashMap<>();
    }

    @GetMapping
    public List<Koala> getAllKoalas() {
        return koalas.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Koala getKoalaById(@PathVariable int id) {
        return koalas.get(id);
    }

    @PostMapping
    public Koala createKoala(@RequestBody Koala koala) {
        koalas.put(koala.getId(), koala);
        return koala;
    }

    @PutMapping("/{id}")
    public Koala updateKoala(@PathVariable int id, @RequestBody Koala koala) {
        if (koalas.containsKey(id)) {
            koalas.put(id, koala);
            return koala;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Koala deleteKoala(@PathVariable int id){
        Koala koala = koalas.get(id);
        if (koalas.containsKey(id)) {
            koalas.remove(id);
            return koala;
        }
        return null;
    }


}