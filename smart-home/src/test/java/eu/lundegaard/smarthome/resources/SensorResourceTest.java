package eu.lundegaard.smarthome.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.lundegaard.smarthome.model.device.DeviceState;
import eu.lundegaard.smarthome.service.DeviceService;
import eu.lundegaard.smarthome.service.SensorService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author Ilias Abdykarov
 */
@WebMvcTest(SensorResource.class)
public class SensorResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Captor
    ArgumentCaptor<DeviceState> deviceCaptor;

    @MockBean
    private SensorService sensorService;
}
