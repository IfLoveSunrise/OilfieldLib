package ru.iflovesunrise.oilfieldlib.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.iflovesunrise.oilfieldlib.dto.OilWellResponse;

@Service
@RequiredArgsConstructor
public class OilWellServiceImpl implements OilWellService {

    @Override
    public OilWellResponse getAll() {
        return null;
    }

    @Override
    public OilWellResponse getById(int id) {
        return null;
    }

    @Override
    public OilWellResponse create(int number, String code, int oilfieldId, int debit) {
        return null;
    }

    @Override
    public OilWellResponse update(int id, int number, String code, int oilfieldId, int debit) {
        return null;
    }

    @Override
    public OilWellResponse deleteAll() {
        return null;
    }

    @Override
    public OilWellResponse deleteById(int id) {
        return null;
    }
}
