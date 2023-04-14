package ru.iflovesunrise.oilfieldlib.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OilfieldLibResponse {

    private String result;

    private List<Object> objects;

}
