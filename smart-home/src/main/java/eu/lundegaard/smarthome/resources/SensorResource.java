package eu.lundegaard.smarthome.resources;

import eu.lundegaard.smarthome.dto.request.DeviceResponseDto;
import eu.lundegaard.smarthome.dto.response.SensorResponseDto;
import eu.lundegaard.smarthome.model.sensor.SensorState;
import eu.lundegaard.smarthome.events.EventType;
import eu.lundegaard.smarthome.service.SensorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ilias Abdykarov
 */
@RestController
@AllArgsConstructor
@RequestMapping("api/sensors")
public class SensorResource {

    private SensorService sensorService;

    @Operation(
            summary = "Method notifies attached devices to event",
            operationId = "notifyDevicesToEvent",
            description = "Makes all sensors placed in the room to notify all attached devices to do some action depending on event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Attached devices has been notified"),
            @ApiResponse(responseCode = "404", description = "Sensor or room not found")
    })
    @PatchMapping("{sensorId}/{room}/{eventType}")
    public void reactToExternalEvent(@PathVariable Long sensorId, @PathVariable String room, @PathVariable EventType eventType){
        sensorService.reactToExternalEvent(sensorId, room, eventType);
    }

    @Operation(
            summary = "Method changes sensor state",
            operationId = "changeSensorState",
            description = "Changes sensor state")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sensor's state successfully changed"),
            @ApiResponse(responseCode = "404", description = "Sensor not found")
    })
    @PatchMapping("{sensorId}/{state}")
    public void changeSensorState(@PathVariable Long sensorId, @PathVariable SensorState state){

    }

    @Operation(
            summary = "Method creates a new sensor",
            operationId = "createSensor",
            description = "Creates a new sensor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New sensor has been created"),
            @ApiResponse(responseCode = "404", description = "Room name not found")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createSensor(@RequestBody SensorResponseDto sensorResponseDto){

    }

    @Operation(
            summary = "Method returns all installed devices",
            operationId = "returnDevices",
            description = "Returns all attached to the sensor devices")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devices have been returned"),
            @ApiResponse(responseCode = "404", description = "Sensor not found")
    })
    @GetMapping("{sensorId}/listeners")
    public List<DeviceResponseDto> getObservers(@PathVariable Long sensorId){
        return null;
    }

    @Operation(
            summary = "Method attaches device to the sensor",
            operationId = "attachDevice",
            description = "Installs device into sensor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Device has been installed"),
            @ApiResponse(responseCode = "409", description = "Device already in use by another sensor"),
            @ApiResponse(responseCode = "404", description = "Sensor not found")
    })
    @PutMapping("{sensorId}/listeners/{listenerId}")
    public void attachSubscriber(@PathVariable Long sensorId, @PathVariable Long listenerId) {

    }

    @Operation(
            summary = "Method detaches an installed device from sensor",
            operationId = "returnDevices",
            description = "Detaches an installed device from sensor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Device has been uninstalled"),
            @ApiResponse(responseCode = "404", description = "Sensor not found")
    })
    @DeleteMapping("{sensorId}/listeners/{listenerId}")
    public void detachSubscriber(@PathVariable Long sensorId, @PathVariable Long listenerId) {

    }


}
