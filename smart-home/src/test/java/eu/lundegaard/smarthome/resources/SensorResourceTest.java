package eu.lundegaard.smarthome.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.lundegaard.smarthome.dto.response.DeviceResponseDto;
import eu.lundegaard.smarthome.dto.request.SensorRequestDto;
import eu.lundegaard.smarthome.events.EventType;
import eu.lundegaard.smarthome.exception.DeviceInUseByAnotherSensor;
import eu.lundegaard.smarthome.exception.DifferentRoomsException;
import eu.lundegaard.smarthome.exception.SensorOrRoomNotFoundException;
import eu.lundegaard.smarthome.model.device.DeviceState;
import eu.lundegaard.smarthome.model.sensor.SensorState;
import eu.lundegaard.smarthome.model.sensor.SensorType;
import eu.lundegaard.smarthome.service.SensorService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        doThrow(new SensorOrRoomNotFoundException(HttpStatus.NOT_FOUND)).when(sensorService).reactToExternalEvent(1l, "Hall", EventType.BREAK_IN);

        mockMvc.perform(patch("/api/sensors/1/Hall/BREAK_IN"))
                .andExpect(status().isNotFound());

        verify(sensorService).reactToExternalEvent(1l, "Hall", EventType.BREAK_IN);
    }

    @Test
    void changeSensorState() throws Exception {
        mockMvc.perform(patch("/api/sensors/1/ACTIVE"))
                .andExpect(status().isOk());

        verify(sensorService).changeSensorState(1L, SensorState.ACTIVE);
    }

    @Test
    void changeSensorState_NotFoundSensor() throws Exception {
        doThrow(new SensorOrRoomNotFoundException(HttpStatus.NOT_FOUND)).when(sensorService).changeSensorState(1L, SensorState.ACTIVE);

        mockMvc.perform(patch("/api/sensors/1/ACTIVE"))
                .andExpect(status().isNotFound());

        verify(sensorService).changeSensorState(1L, SensorState.ACTIVE);
    }

    @Test
    void createSensor() throws Exception {
        SensorRequestDto mockRequestDto = createMockRequestDto();

        mockMvc.perform(post("/api/sensors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJson(mockRequestDto)))
                .andExpect(status().isCreated());

        verify(sensorService).createSensor(mockRequestDto);
    }

    @Test
    void createSensor_RoomNotFound() throws Exception {
        SensorRequestDto mockRequestDto = createMockRequestDto();
        doThrow(new SensorOrRoomNotFoundException(HttpStatus.NOT_FOUND)).when(sensorService).createSensor(mockRequestDto);

        mockMvc.perform(post("/api/sensors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJson(mockRequestDto)))
                .andExpect(status().isNotFound());

        verify(sensorService).createSensor(mockRequestDto);
    }

    @Test
    void getObservers_Success() throws Exception {
        when(sensorService.getObservers(1L)).thenReturn(List.of(new DeviceResponseDto()));

        mockMvc.perform(get("/api/sensors/1/listeners"))
                .andExpect(status().isOk());

        verify(sensorService).getObservers(1L);
    }

    @Test
    void attachSubscriber_Attached() throws Exception {

        mockMvc.perform(put("/api/sensors/1/listeners/2"))
                .andExpect(status().isOk());

        verify(sensorService).attachSubscriber(1L, 2L);
    }

    @Test
    void attachSubscriber_DeviceInUse() throws Exception {
        doThrow(new DeviceInUseByAnotherSensor(HttpStatus.CONFLICT)).when(sensorService).attachSubscriber(1L, 1L);

        mockMvc.perform(put("/api/sensors/1/listeners/1"))
                .andExpect(status().isConflict());

        verify(sensorService).attachSubscriber(1L, 1L);
    }

    @Test
    void attachSubscriber_DifferentRooms() throws Exception {
        doThrow(new DifferentRoomsException(HttpStatus.FORBIDDEN)).when(sensorService).attachSubscriber(1L, 1L);

        mockMvc.perform(put("/api/sensors/1/listeners/1"))
                .andExpect(status().isForbidden());

        verify(sensorService).attachSubscriber(1L, 1L);
    }

    @Test
    void attachSubscriber_NotFoundSensor() throws Exception {
        doThrow(new SensorOrRoomNotFoundException(HttpStatus.NOT_FOUND)).when(sensorService).attachSubscriber(1L, 1L);

        mockMvc.perform(put("/api/sensors/1/listeners/1"))
                .andExpect(status().isNotFound());

        verify(sensorService).attachSubscriber(1L, 1L);
    }

    @Test
    void detachSubscriber_Success() throws Exception {
        mockMvc.perform(delete("/api/sensors/1/listeners/1"))
                .andExpect(status().isOk());

        verify(sensorService).detachSubscriber(1L, 1L);
    }

    @Test
    void detachSubscriber_SensorNotFound() throws Exception {
        doThrow(new SensorOrRoomNotFoundException(HttpStatus.NOT_FOUND)).when(sensorService).detachSubscriber(1L, 1L);

        mockMvc.perform(delete("/api/sensors/1/listeners/1"))
                .andExpect(status().isNotFound());

        verify(sensorService).detachSubscriber(1L, 1L);
    }

    private SensorRequestDto createMockRequestDto(){
        return new SensorRequestDto()
                .setSensorType(SensorType.MOVEMENT_SENSOR)
                .setRoom("Garage")
                .setSensorState(SensorState.ACTIVE);
    }

    private String createJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
