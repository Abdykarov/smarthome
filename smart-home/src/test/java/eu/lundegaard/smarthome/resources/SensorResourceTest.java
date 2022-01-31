package eu.lundegaard.smarthome.resources;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author Ilias Abdykarov
 */
@WebMvcTest(SensorResource.class)
public class SensorResourceTest {

    @Autowired
    private MockMvc mockMvc;

}
