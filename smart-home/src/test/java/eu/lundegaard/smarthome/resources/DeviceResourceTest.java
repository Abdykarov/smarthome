package eu.lundegaard.smarthome.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.lundegaard.smarthome.dto.request.DeviceRequestDto;
import eu.lundegaard.smarthome.dto.response.DeviceResponseDto;
import eu.lundegaard.smarthome.events.WindEvent;
import eu.lundegaard.smarthome.exception.DeviceNotFoundException;
import eu.lundegaard.smarthome.model.device.DeviceState;
import eu.lundegaard.smarthome.service.DeviceService;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Ilias Abdykarov
 */
@WebMvcTest(DeviceResource.class)
class DeviceResourceTest implements WithAssertions {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Captor
    ArgumentCaptor<DeviceState> deviceCaptor;

    @MockBean
    private DeviceService deviceService;

    @Test
    void findAll_ReturnsAll() throws Exception {
        when(deviceService.findAllDevices()).thenReturn(List.of(new DeviceResponseDto()));

        mockMvc.perform(get("/api/devices"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(deviceService).findAllDevices();
    }

    @Test
    void changeState_Success() throws Exception {
        mockMvc.perform(patch("/api/devices/1/IDLE"))
                .andExpect(status().isOk());
        deviceCaptor = ArgumentCaptor.forClass(DeviceState.class);
        verify(deviceService).changeDeviceState(any(), deviceCaptor.capture());
        DeviceState state = deviceCaptor.getValue();
        assertThat(state).isEqualTo(DeviceState.IDLE);
    }

    @Test
    void changeState_DeviceNotFound() throws Exception {
        doThrow(new DeviceNotFoundException(HttpStatus.NOT_FOUND)).when(deviceService).changeDeviceState(1l, DeviceState.IDLE);

        mockMvc.perform(patch("/api/devices/1/IDLE"))
                .andExpect(status().isNotFound());

        verify(deviceService).changeDeviceState(1l, DeviceState.IDLE);
    }

    @Test
    void updateDevice_Success() throws Exception {
        DeviceRequestDto mockRequestDto = createMockRequestDto();
        DeviceResponseDto mockResponseDto = createMockResponseDto();
        when(deviceService.updateDevice(1l, mockRequestDto)).thenReturn(mockResponseDto);

        mockMvc.perform(put("/api/devices/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createJson(mockRequestDto)))
                .andExpect(status().isOk());

        verify(deviceService).updateDevice(1L,mockRequestDto);
        SoftAssertions.assertSoftly(softAssertions -> {
            assertThat(mockRequestDto.getDeviceName()).isEqualTo(mockResponseDto.getDeviceName());
            assertThat(mockResponseDto.getState()).isEqualTo(DeviceState.ACTIVE);
        });
    }

    @Test
    void updateDevice_ValidationTest() throws Exception {
        DeviceRequestDto mockRequestDto = createMockRequestDto()
                .setDeviceName("");
        DeviceResponseDto mockResponseDto = createMockResponseDto();
        when(deviceService.updateDevice(1l, mockRequestDto)).thenReturn(mockResponseDto);

        mockMvc.perform(put("/api/devices/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJson(mockRequestDto)))
                .andExpect(status().isOk());

        verify(deviceService).updateDevice(1L,mockRequestDto);
        SoftAssertions.assertSoftly(softAssertions -> {
            assertThat(mockRequestDto.getDeviceName()).isEqualTo("");
            assertThat(mockResponseDto.getState()).isEqualTo(DeviceState.ACTIVE);
        });
    }

    @Test
    void updateDevice_DeviceNotFound() throws Exception {
        DeviceRequestDto mockRequestDto = createMockRequestDto();
        doThrow(new DeviceNotFoundException(HttpStatus.NOT_FOUND)).when(deviceService).updateDevice(1l, mockRequestDto);

        mockMvc.perform(put("/api/devices/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJson(mockRequestDto)))
                .andExpect(status().isNotFound());

        verify(deviceService).updateDevice(1l, mockRequestDto);
    }

    @Test
    void notifyDevice_EventDtoPassing() throws Exception {
        WindEvent windEvent = new WindEvent();

        mockMvc.perform(post("/api/devices/1/notify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJson(windEvent)))
                .andExpect(status().isOk());

        verify(deviceService).notify(1L, windEvent);
    }

    @Test
    void notifyDevice_DeviceNotFound() throws Exception {
        WindEvent windEvent = new WindEvent();
        doThrow(new DeviceNotFoundException(HttpStatus.NOT_FOUND)).when(deviceService).notify(1l, windEvent);

        mockMvc.perform(post("/api/devices/1/notify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJson(windEvent)))
                .andExpect(status().isNotFound());

        verify(deviceService).notify(1l, windEvent);
    }

    @Test
    void createDevice_CreatedDevice() throws Exception {
        DeviceRequestDto mockRequestDto = createMockRequestDto();

        mockMvc.perform(post("/api/devices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJson(mockRequestDto)))
                .andExpect(status().isCreated());

        verify(deviceService).createDevice(mockRequestDto);
    }

    @Test
    void deleteDevice_DeletedDevice() throws Exception {
        mockMvc.perform(delete("/api/devices/1"))
                .andExpect(status().isOk());

        verify(deviceService).deleteDevice(1l);
    }

    @Test
    void deleteDevice_NotFound() throws Exception{
        doThrow(new DeviceNotFoundException(HttpStatus.NOT_FOUND)).when(deviceService).deleteDevice(1l);

        mockMvc.perform(delete("/api/devices/1"))
                .andExpect(status().isNotFound());

        verify(deviceService).deleteDevice(1l);
    }

    private DeviceRequestDto createMockRequestDto(){
        return new DeviceRequestDto()
                .setDeviceName("Smart door")
                .setConsumedPower(0)
                .setFunctionalityPercentage(100)
                .setRoom("Hall")
                .setState(DeviceState.IDLE);
    }


    private DeviceResponseDto createMockResponseDto(){
        return new DeviceResponseDto()
                .setDeviceName("Smart door")
                .setConsumedPower(0)
                .setFunctionalityPercentage(100)
                .setRoom("Hall")
                .setState(DeviceState.ACTIVE);
    }

    private String createJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}