package ru.iflovesunrise.oilfieldlib.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.iflovesunrise.oilfieldlib.dto.OilfieldLibResponse;
import ru.iflovesunrise.oilfieldlib.services.OilWellService;

@RestController
@AllArgsConstructor
@RequestMapping("/oilWell")
public class OilWellController {

    private final OilWellService oilWellService;

    @GetMapping("/get")
    public ResponseEntity<OilfieldLibResponse> get() {
        return ResponseEntity.ok(oilWellService.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<OilfieldLibResponse> getById(@PathVariable int id) {
        return ResponseEntity.ok(oilWellService.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<OilfieldLibResponse> create(
            @RequestParam(value = "number") Integer number,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "oilfieldId") int oilfieldId,
            @RequestParam(value = "debit", required = false) Integer debit) {
        return ResponseEntity.ok(oilWellService.create(number, code, oilfieldId, debit));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OilfieldLibResponse> update(
            @PathVariable int id,
            @RequestParam(value = "number") Integer number,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "debit", required = false) Integer debit) {
        return ResponseEntity.ok(oilWellService.update(id, number, code, debit));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<OilfieldLibResponse> delete() {
        return ResponseEntity.ok(oilWellService.deleteAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<OilfieldLibResponse> deleteById(@PathVariable int id) {
        return ResponseEntity.ok(oilWellService.deleteById(id));
    }
}
