package ru.iflovesunrise.oilfieldlib.services;

import ru.iflovesunrise.oilfieldlib.dto.OilfieldLibResponse;

public interface OilfieldService {
    OilfieldLibResponse getAll();

    OilfieldLibResponse getById(int id);

    OilfieldLibResponse create(String name, String foundationDate);

    OilfieldLibResponse update(int id, String name, String foundationDate);

    OilfieldLibResponse deleteAll();

    OilfieldLibResponse deleteById(int id);
}
