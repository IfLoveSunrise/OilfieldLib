package ru.iflovesunrise.oilfieldlib.services;

import ru.iflovesunrise.oilfieldlib.dto.OilfieldResponse;

public interface OilfieldService {
    OilfieldResponse getAll();

    OilfieldResponse getById(int id);

    OilfieldResponse create(String name, String foundationDate);

    OilfieldResponse update(int id, String name, String foundationDate);

    OilfieldResponse deleteAll();

    OilfieldResponse deleteById(int id);
}
