package ru.iflovesunrise.oilfieldlib.services;

import ru.iflovesunrise.oilfieldlib.dto.OilWellResponse;

public interface OilWellService {
    OilWellResponse getAll();

    OilWellResponse getById(int id);

    OilWellResponse create(int number, String code, int oilfieldId, int debit);

    OilWellResponse update(int id, int number, String code, int oilfieldId, int debit);

    OilWellResponse deleteAll();

    OilWellResponse deleteById(int id);
}
