package ru.iflovesunrise.oilfieldlib.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.iflovesunrise.oilfieldlib.model.OilWell;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OilWellResponse {

    private String result;

    private List<OilWell> oilWells;
}
