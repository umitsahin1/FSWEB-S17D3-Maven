package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {

    public Map<Long, Kangaroo> kangaroos;

    @PostConstruct
    public void init() {
        kangaroos = new HashMap<>();
    }

    @GetMapping
    public List<Kangaroo> getAllKangaroos() {
        return kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo getKangarooById(@PathVariable Long id) {
        Kangaroo kangaroo = kangaroos.get(id);
        if (kangaroo == null) {
            throw new ZooException("Kangaroo not found", HttpStatus.NOT_FOUND);
        }
        return kangaroo;
    }

    @PostMapping
    public Kangaroo createKangaroo(@RequestBody Kangaroo kangaroo) {
        if ( kangaroo.getName() == null) {
            throw new ZooException("Invalid data: 'id' and 'name' are required", HttpStatus.BAD_REQUEST);
        }
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo;
    }

    @PutMapping("/{id}")
    public Kangaroo updateKangaroo(@PathVariable Long id, @RequestBody Kangaroo kangaroo) {

        if (kangaroos.containsKey(id)) {
            kangaroos.put(id, kangaroo);
            return kangaroo;
        } else {
            throw new ZooException("Kangaroo not found for ID: " + id, HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public Kangaroo deleteKangaroo(@PathVariable Long id) {
        Kangaroo kangaroo = kangaroos.get(id);
        if (kangaroos.containsKey(id)) {
            kangaroos.remove(id);
            return kangaroo;
        } else {
            throw new ZooException("Kangaroo not found for ID: " + id, HttpStatus.NOT_FOUND);
        }
    }
}
