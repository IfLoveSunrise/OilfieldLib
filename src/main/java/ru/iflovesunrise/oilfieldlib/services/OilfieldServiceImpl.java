package ru.iflovesunrise.oilfieldlib.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.iflovesunrise.oilfieldlib.dto.OilfieldLibResponse;
import ru.iflovesunrise.oilfieldlib.model.Oilfield;
import ru.iflovesunrise.oilfieldlib.model.OilfieldStage;
import ru.iflovesunrise.oilfieldlib.repositories.OilfieldRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OilfieldServiceImpl implements OilfieldService {

    private final OilfieldRepository oilfieldRepository;
    private final OilfieldLibResponse oilfieldLibResponse = new OilfieldLibResponse();
    private static final String DATE_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";

    @Override
    public OilfieldLibResponse getAll() {
        List<Oilfield> oilfields = oilfieldRepository.findAll();
        if (oilfields.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Oilfields have not been created");
        }
        oilfieldLibResponse.setResult("Oilfields found");
        oilfieldLibResponse.setObjects(Collections.singletonList(oilfields));
        return oilfieldLibResponse;
    }

    @Override
    public OilfieldLibResponse getById(int id) {
        Optional<Oilfield> oilfieldOptional = oilfieldRepository.findById(id);
        if (oilfieldOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        oilfieldLibResponse.setResult("Oilfield found");
        oilfieldLibResponse.setObjects(Collections.singletonList(oilfieldOptional.get()));
        return oilfieldLibResponse;
    }

    @Override
    public OilfieldLibResponse create(String name, String foundationDate) {
        if (name == null || name.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name cannot be empty");
        }
        Optional<Oilfield> oilfieldOptional = oilfieldRepository.findByName(name);
        if (oilfieldOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Oilfield already exists. His ID: "
                    .concat(String.valueOf(oilfieldOptional.get().getId())));
        }
        Oilfield oilfield = new Oilfield();
        oilfield.setName(name.trim());
        if (foundationDate != null && !foundationDate.matches(DATE_REGEX)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format. Example: 2000-12-30");
        }
        if(foundationDate != null) oilfield.setFoundationDate(LocalDate.parse(foundationDate));
        oilfield.setExtentOfProduction(0);
        oilfield.setStage(OilfieldStage.PLANNED);
        oilfield.setLastUpdateTime(LocalDateTime.now());
        oilfieldRepository.save(oilfield);
        oilfieldLibResponse.setResult("Created");
        oilfieldLibResponse.setObjects(Collections.singletonList(oilfield));
        return oilfieldLibResponse;
    }

    @Override
    public OilfieldLibResponse update(int id, String name, String foundationDate) {
        Optional<Oilfield> oilfieldOptional = oilfieldRepository.findById(id);
        if (oilfieldOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Oilfield not found");
        } else if ((name == null || name.isEmpty()) && foundationDate == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parameters are not set");
        } else if (foundationDate != null && !foundationDate.matches(DATE_REGEX)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format. Example: 2000-12-30");
        }
        Oilfield oilfield = oilfieldOptional.get();
        oilfieldLibResponse.setResult(oilfield.getName().concat(" is updated"));
        if (name != null && !name.equals("")) oilfield.setName(name);
        if (foundationDate != null) oilfield.setFoundationDate(LocalDate.parse(foundationDate));
        oilfield.setLastUpdateTime(LocalDateTime.now());
        oilfieldRepository.save(oilfield);
        oilfieldLibResponse.setObjects(Collections.singletonList(oilfield));
        return oilfieldLibResponse;
    }

    @Override
    public OilfieldLibResponse deleteAll() {
        oilfieldRepository.deleteAll();
        oilfieldLibResponse.setResult("All oilfields have been removed");
        return oilfieldLibResponse;
    }

    @Override
    public OilfieldLibResponse deleteById(int id) {
        Optional<Oilfield> oilfieldOptional = oilfieldRepository.findById(id);
        if (oilfieldOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Oilfield not found");
        }
        oilfieldLibResponse.setResult("Oilfield ".concat(oilfieldOptional.get().getName())
                .concat(" have been removed"));
        oilfieldRepository.deleteById(id);
        return oilfieldLibResponse;
    }
}
