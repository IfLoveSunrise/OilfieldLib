package ru.iflovesunrise.oilfieldlib.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.iflovesunrise.oilfieldlib.model.Oilfield;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OilfieldResponse {

    private String result;

    private List<Oilfield> oilfields;
}
