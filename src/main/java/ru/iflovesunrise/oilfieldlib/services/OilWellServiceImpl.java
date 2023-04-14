package ru.iflovesunrise.oilfieldlib.services;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.iflovesunrise.oilfieldlib.dto.OilfieldLibResponse;
import ru.iflovesunrise.oilfieldlib.model.OilWell;
import ru.iflovesunrise.oilfieldlib.model.Oilfield;
import ru.iflovesunrise.oilfieldlib.model.OilfieldStage;
import ru.iflovesunrise.oilfieldlib.repositories.OilWellRepository;
import ru.iflovesunrise.oilfieldlib.repositories.OilfieldRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OilWellServiceImpl implements OilWellService {

    private static final Logger LOGGER = LogManager.getLogger(OilWellServiceImpl.class);
    private static final Marker INVALID_DATA_MARKER = MarkerManager.getMarker("INVALID_DATA_MARKER");
    private static final Marker INPUT_HISTORY_MARKER = MarkerManager.getMarker("INPUT_HISTORY_MARKER");
    private final OilfieldRepository oilfieldRepository;
    private final OilWellRepository oilWellRepository;
    private final OilfieldLibResponse oilfieldLibResponse = new OilfieldLibResponse();
    @Override
    public OilfieldLibResponse getAll() {
        List<OilWell> oilWells = oilWellRepository.findAll();
        if (oilWells.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Oil wells have not been created");
        }
        oilfieldLibResponse.setResult("Oil wells found");
        oilfieldLibResponse.setObjects(Collections.singletonList(oilWells));
        return oilfieldLibResponse;
    }

    @Override
    public OilfieldLibResponse getById(int id) {
        Optional<OilWell> oilWellOptional = oilWellRepository.findById(id);
        if (oilWellOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        oilfieldLibResponse.setResult("Oil well found");
        oilfieldLibResponse.setObjects(Collections.singletonList(oilWellOptional.get()));
        return oilfieldLibResponse;
    }

    @Override
    public OilfieldLibResponse create(Integer number, String code, int oilfieldId, Integer debit) {
        String invalidNumber = String.valueOf(number == null ? "Invalid number" : number);
        LOGGER.info(INPUT_HISTORY_MARKER, invalidNumber.concat("; ")
                .concat(code == null ? "Code: ***" : code).concat("; ")
                .concat(String.valueOf(oilfieldId)).concat("; ")
                .concat(debit == null ? "Debit: ***" : String.valueOf(debit)));
        if (number == null || number < 0) {
            LOGGER.info(INVALID_DATA_MARKER, invalidNumber);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid number");
        }
        Optional<Oilfield> oilfieldOptional = oilfieldRepository.findById(oilfieldId);
        if (oilfieldOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Oilfield id "
                    .concat(String.valueOf(oilfieldId)).concat(" does not exist"));
        }
        Oilfield oilfield = oilfieldOptional.get();
        OilWell oilWell = new OilWell();
        oilWell.setNumber(number);
        if (code != null && !code.isEmpty()) oilWell.setCode(code);
        oilWell.setOilfield(oilfieldOptional.get());
        if (debit != null) {
            oilWell.setDebit(Math.abs(debit));
            oilWell.setActive(Math.abs(debit) > 0);
        } else oilWell.setActive(false);
        oilWellRepository.save(oilWell);
        updateOilfieldInfo(oilfield);
        oilfieldLibResponse.setResult("Created");
        oilfieldLibResponse.setObjects(Collections.singletonList(oilWell));
        return oilfieldLibResponse;
    }

    @Override
    public OilfieldLibResponse update(int id, Integer number, String code, Integer debit) {
        Optional<OilWell> oilWellOptional = oilWellRepository.findById(id);
        if (oilWellOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Oil well not found");
        try {
            OilWell oilWell = oilWellOptional.get();
            oilfieldLibResponse.setResult("Oil well №".concat(String.valueOf(oilWell.getNumber()))
                    .concat(" is updated"));
            if (number != null) oilWell.setNumber(Math.abs(number));
            if (code != null) oilWell.setCode(code);
            if (debit != null) {
                oilWell.setDebit(Math.abs(debit));
                oilWell.setActive(Math.abs(debit) > 0);
            }
            oilWellRepository.save(oilWell);
            Oilfield oilfield = oilWell.getOilfield();
            updateOilfieldInfo(oilfield);
            oilfieldLibResponse.setObjects(Collections.singletonList(oilWell));
        } catch (Exception exampleEx) {
            LOGGER.error(exampleEx);
        }
        return oilfieldLibResponse;
    }

    @Override
    public OilfieldLibResponse deleteAll() {
        oilfieldRepository.deleteAll();
        oilfieldLibResponse.setResult("All oil wells have been removed");
        List<Oilfield> oilfields = oilfieldRepository.findAll();
        oilfields.forEach(this::updateOilfieldInfo);
        return oilfieldLibResponse;
    }

    @Override
    public OilfieldLibResponse deleteById(int id) {
        Optional<OilWell> oilWellOptional = oilWellRepository.findById(id);
        if (oilWellOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Oil well not found");
        }
        int oilfieldId = oilWellOptional.get().getOilfield().getId();
        oilfieldLibResponse.setResult("Oil well id \"".concat(String.valueOf(oilWellOptional.get().getId()))
                .concat("\" have been removed"));
        oilfieldRepository.deleteById(id);
        Optional<Oilfield> oilfieldOptional = oilfieldRepository.findById(oilfieldId);
        oilfieldOptional.ifPresent(this::updateOilfieldInfo);
        return oilfieldLibResponse;
    }

    private void updateOilfieldInfo(Oilfield oilfield) {
        oilfield.setExtentOfProduction(oilWellRepository.extentOfProductionByOilfieldId(oilfield.getId()));
        oilfield.setLastUpdateTime(LocalDateTime.now());
        if (oilfield.getStage().equals(OilfieldStage.PLANNED) && oilfield.getExtentOfProduction() > 0) {
            oilfield.setStage(OilfieldStage.COMMISSIONED);
        } else if (oilfield.getStage().equals(OilfieldStage.COMMISSIONED) && oilfield.getExtentOfProduction() == 0) {
            oilfield.setStage(OilfieldStage.DECOMMISSIONED);
        }
        oilfieldRepository.save(oilfield);
    }
}
