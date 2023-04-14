package ru.iflovesunrise.oilfieldlib.services;


import ru.iflovesunrise.oilfieldlib.dto.OilfieldLibResponse;

public interface OilWellService {
    OilfieldLibResponse getAll();

    OilfieldLibResponse getById(int id);

    OilfieldLibResponse create(Integer number, String code, int oilfieldId, Integer debit);

    OilfieldLibResponse update(int id, Integer number, String code, Integer debit);

    OilfieldLibResponse deleteAll();

    OilfieldLibResponse deleteById(int id);
}
