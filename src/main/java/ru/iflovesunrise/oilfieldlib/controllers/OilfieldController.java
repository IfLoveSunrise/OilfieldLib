package ru.iflovesunrise.oilfieldlib.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.iflovesunrise.oilfieldlib.dto.OilfieldLibResponse;
import ru.iflovesunrise.oilfieldlib.services.OilfieldService;

@RestController
@AllArgsConstructor
@RequestMapping("/oilfield")
public class OilfieldController {

    private final OilfieldService oilfieldService;

    @GetMapping("/get")
    public ResponseEntity<OilfieldLibResponse> get() {
        return ResponseEntity.ok(oilfieldService.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<OilfieldLibResponse> getById(@PathVariable int id) {
        return ResponseEntity.ok(oilfieldService.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<OilfieldLibResponse> create(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "foundationDate", required = false) String foundationDate) {
        return ResponseEntity.ok(oilfieldService.create(name, foundationDate));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OilfieldLibResponse> update(
            @PathVariable int id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "foundationDate", required = false) String foundationDate) {
        return ResponseEntity.ok(oilfieldService.update(id, name, foundationDate));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<OilfieldLibResponse> delete() {
        return ResponseEntity.ok(oilfieldService.deleteAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<OilfieldLibResponse> deleteById(@PathVariable int id) {
        return ResponseEntity.ok(oilfieldService.deleteById(id));
    }
}
