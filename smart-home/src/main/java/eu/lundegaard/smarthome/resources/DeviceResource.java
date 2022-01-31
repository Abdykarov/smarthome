package eu.lundegaard.smarthome.resources;

import eu.lundegaard.smarthome.dto.request.DeviceRequestDto;
import eu.lundegaard.smarthome.dto.request.DeviceResponseDto;
import eu.lundegaard.smarthome.events.EventDto;
import eu.lundegaard.smarthome.model.device.DeviceState;
import eu.lundegaard.smarthome.service.DeviceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Ilias Abdykarov
 */
@RestController
@AllArgsConstructor
@RequestMapping("api/devices")
@Validated
public class DeviceResource {

    private DeviceService deviceService;

    @Operation(
            summary = "Finds all devices in home",
            operationId = "getDevices",
            description = "Returns device all dtos or exception in case of empty list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devices returned")
    })
    @GetMapping
    public List<DeviceResponseDto> findAll(){
        return deviceService.findAllDevices();
    }

    @Operation(
            summary = "Changes state of device",
            operationId = "changeStateOfDevice",
            description = "Finds device by id and changes state")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Device's state has changed"),
            @ApiResponse(responseCode = "400", description = "Invalid state has been provided"),
            @ApiResponse(responseCode = "404", description = "Device not found")
    })
    @PatchMapping("{deviceId}/{deviceState}")
    public DeviceResponseDto changeState(@PathVariable Long deviceId, @PathVariable DeviceState deviceState){
        return deviceService.changeDeviceState(deviceId, deviceState);
    }

    @Operation(
            summary = "Updates device data",
            operationId = "updateDevice",
            description = "Finds device by id and updates device data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Device's data has been updated"),
            @ApiResponse(responseCode = "400", description = "Invalid state has been provided"),
            @ApiResponse(responseCode = "404", description = "Device not found")
    })
    @PutMapping(value = "{deviceId}", consumes = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public DeviceResponseDto updateDevice(@PathVariable Long deviceId, @Valid @RequestBody DeviceRequestDto deviceDto){
        return deviceService.updateDevice(deviceId, deviceDto);
    }

    @Operation(
            summary = "Methods notifies device to do some action depending on event",
            operationId = "notifyDevice",
            description = "Finds device by id and call update method")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Device has been successfully notified"),
            @ApiResponse(responseCode = "404", description = "Device not found")
    })
    @PostMapping("{deviceId}/notify")
    @ResponseStatus(code = HttpStatus.OK)
    public void notify(@PathVariable Long deviceId, @RequestBody EventDto eventDto){
        deviceService.notify(deviceId, eventDto);
    }

    @Operation(
            summary = "Methods notifies device to do some action depending on event",
            operationId = "notifyDevice",
            description = "Finds device by id and call update method")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Device has been successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid state has been provided")
    })
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createDevice(@RequestBody DeviceRequestDto deviceDto){
        deviceService.createDevice(deviceDto);
    }

    @Operation(
            summary = "Methods deleted device",
            operationId = "deleteDevice",
            description = "Finds device by id and deletes it")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Device has been successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Device not found")
    })
    @DeleteMapping("{deviceId}")
    public void deleteDevice(@PathVariable Long deviceId){
        deviceService.deleteDevice(deviceId);
    }

}
