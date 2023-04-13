package ru.iflovesunrise.oilfieldlib.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.iflovesunrise.oilfieldlib.dto.OilfieldLibResponse;
import ru.iflovesunrise.oilfieldlib.services.OilfieldLibService;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ApiController {

    private final OilfieldLibService oilfieldLibService;

    @GetMapping("/getOilfields")
    public ResponseEntity<OilfieldLibResponse> getOilfields() {
        return ResponseEntity.ok(oilfieldLibService.getOilfields());
    }

    @GetMapping("/getOilfield/{id}")
    public ResponseEntity<OilfieldLibResponse> getOilfieldById(@PathVariable int id) {
        return ResponseEntity.ok(oilfieldLibService.getOilfieldById(id));
    }

    @PostMapping("/createOilfield")
    public ResponseEntity<OilfieldLibResponse> createOilfield(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "foundationDate", required = false) String foundationDate) {
        return ResponseEntity.ok(oilfieldLibService.createOilfield(name, foundationDate));
    }

    @PutMapping("/updateOilfield/{id}")
    public ResponseEntity<OilfieldLibResponse> updateOilfieldById(
            @PathVariable int id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "foundationDate", required = false) String foundationDate) {
        return ResponseEntity.ok(oilfieldLibService.createOilfield(id, name, foundationDate));
    }

    @DeleteMapping("/deleteOilfields")
    public ResponseEntity<OilfieldLibResponse> deleteOilfields() {
        return ResponseEntity.ok(oilfieldLibService.deleteOilfields());
    }

    @DeleteMapping("/deleteOilfield/{id}")
    public ResponseEntity<OilfieldLibResponse> deleteOilfieldById(@PathVariable int id) {
        return ResponseEntity.ok(oilfieldLibService.deleteOilfieldById(id));
    }

    @GetMapping("/getOilWells")
    public ResponseEntity<OilfieldLibResponse> getOilWells() {
        return ResponseEntity.ok(oilfieldLibService.getOilWells());
    }

    @GetMapping("/getOilWell/{id}")
    public ResponseEntity<OilfieldLibResponse> getOilWellById(@PathVariable int id) {
        return ResponseEntity.ok(oilfieldLibService.getOilWellById(id));
    }

    @PostMapping("/createOilWell")
    public ResponseEntity<OilfieldLibResponse> createOilfield(
            @RequestParam(value = "number") int number,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "oilfieldId") int oilfieldId,
            @RequestParam(value = "debit", required = false) int debit) {
        return ResponseEntity.ok(oilfieldLibService.createOilWell(number, code, oilfieldId, debit));
    }

    @PutMapping("/updateOilWell/{id}")
    public ResponseEntity<OilfieldLibResponse> updateOilWellById(
            @PathVariable int id,
            @RequestParam(value = "number") int number,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "oilfieldId") int oilfieldId,
            @RequestParam(value = "debit", required = false) int debit) {
        return ResponseEntity.ok(oilfieldLibService.createOilWell(id, number, code, oilfieldId, debit));
    }

    @DeleteMapping("/deleteOilWells")
    public ResponseEntity<OilfieldLibResponse> deleteOilWells() {
        return ResponseEntity.ok(oilfieldLibService.deleteOilWells());
    }

    @DeleteMapping("/deleteOilWell/{id}")
    public ResponseEntity<OilfieldLibResponse> deleteOilWellById(@PathVariable int id) {
        return ResponseEntity.ok(oilfieldLibService.deleteOilWellById(id));
    }
}
