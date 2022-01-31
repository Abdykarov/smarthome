package eu.lundegaard.smarthome.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.lundegaard.smarthome.dto.request.DeviceResponseDto;
import eu.lundegaard.smarthome.events.EventType;
import eu.lundegaard.smarthome.exception.SensorOrRoomNotFoundException;
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
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test
    void reactToExternalEvent() throws Exception {
        mockMvc.perform(patch("/api/sensors/1/Hall/BREAK_IN"))
                .andExpect(status().isOk());

        verify(sensorService).reactToExternalEvent(1l, "Hall", EventType.BREAK_IN);
    }

    @Test
    void reactToExternalEvent_RoomOrSensorNotFound() throws Exception {
        doThrow(new SensorOrRoomNotFoundException(HttpStatus.NOT_FOUND)).when(sensorService).reactToExternalEvent(1l,"Hall",EventType.BREAK_IN);

        mockMvc.perform(patch("/api/sensors/1/Hall/BREAK_IN"))
                .andExpect(status().isNotFound());

        verify(sensorService).reactToExternalEvent(1l, "Hall", EventType.BREAK_IN);
    }

}
