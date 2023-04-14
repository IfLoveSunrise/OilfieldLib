package ru.iflovesunrise.oilfieldlib;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;
import ru.iflovesunrise.oilfieldlib.dto.OilfieldLibResponse;
import ru.iflovesunrise.oilfieldlib.model.Oilfield;
import ru.iflovesunrise.oilfieldlib.model.OilfieldStage;
import ru.iflovesunrise.oilfieldlib.repositories.OilfieldRepository;
import ru.iflovesunrise.oilfieldlib.services.OilfieldService;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class OilfieldServiceTest {

    @Autowired
    private OilfieldService oilfieldService;
    @MockBean
    private OilfieldRepository oilfieldRepository;

    @Test
    public void nullValuesNameAndDate() {
        assertThrows(ResponseStatusException.class, () -> oilfieldService.create(null, null));
    }

    @Test
    public void createOilfield() {
        Oilfield oilfield = new Oilfield();
        oilfield.setName("First");
        oilfield.setStage(OilfieldStage.PLANNED);
        oilfield.setFoundationDate(LocalDate.parse("2000-11-22"));

        OilfieldLibResponse expected = new OilfieldLibResponse("Created", Collections.singletonList(oilfield));
        OilfieldLibResponse actual = oilfieldService.create("First", "2000-11-22");

        Oilfield actualObject = (Oilfield) actual.getObjects().get(0);
        oilfield.setLastUpdateTime(actualObject.getLastUpdateTime());

        assert actualObject.getLastUpdateTime() != null;
        assertEquals(expected, actual);
    }
}
