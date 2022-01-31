package eu.lundegaard.smarthome.resources;

import eu.lundegaard.smarthome.dto.DeviceRequestDto;
import eu.lundegaard.smarthome.dto.DeviceResponseDto;
import eu.lundegaard.smarthome.events.EventDto;
import eu.lundegaard.smarthome.model.DeviceState;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Ilias Abdykarov
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/devices")
public class DeviceResource {

    @Operation(
            summary = "Finds all devices in home",
            operationId = "getDevices",
            description = "Returns device all dtos or exception in case of empty list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devices returned"),
            @ApiResponse(responseCode = "404", description = "Devices not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<DeviceResponseDto> findAll(){
        return null;
    }

    @Operation(
            summary = "Changes state of device",
            operationId = "changeStateOfDevice",
            description = "Finds device by id and changes state")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Device's state has changed"),
            @ApiResponse(responseCode = "404", description = "Device not found")
    })
    @PatchMapping("{deviceId}/{deviceState}")
    public void changeState(@PathVariable Long deviceId, @PathVariable DeviceState deviceState){

    }

    @Operation(
            summary = "Updates device data",
            operationId = "updateDevice",
            description = "Finds device by id and updates device data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Device's data has been updated"),
            @ApiResponse(responseCode = "404", description = "Device not found")
    })
    @PutMapping("{deviceId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateDevice(@PathVariable Long deviceId, @Valid @RequestBody DeviceRequestDto deviceDto){

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
    public void notify(@PathVariable Long deviceId, EventDto eventDto){

    }

    @Operation(
            summary = "Methods notifies device to do some action depending on event",
            operationId = "notifyDevice",
            description = "Finds device by id and call update method")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Device has been successfully created"),
            @ApiResponse(responseCode = "404", description = "Device not found")
    })
    @PostMapping("{deviceId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createDevice(@RequestBody DeviceRequestDto deviceDto){

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
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteDevice(@PathVariable Long deviceId){

    }

}
