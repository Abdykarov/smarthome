package eu.lundegaard.smarthome.resources;

import eu.lundegaard.smarthome.exception.DeviceNotFoundException;
import eu.lundegaard.smarthome.model.DeviceDto;
import eu.lundegaard.smarthome.model.DeviceState;
import eu.lundegaard.smarthome.events.EventDto;
import eu.lundegaard.smarthome.repository.DeviceRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * @author Ilias Abdykarov
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/devices")
@CrossOrigin
public class DeviceResource {

    List<DeviceDto> devices = Arrays.asList(new DeviceDto("TV", "Kitchen"), new DeviceDto("PC", "Living room"));

    @Operation(
            summary = "Finds all devices in home",
            operationId = "getDevices",
            description = "Returns device all dtos or exception in case of empty list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devices returned"),
            @ApiResponse(responseCode = "404", description = "Devices not found")
    })
    @GetMapping
    public List<DeviceDto> findAll(){
        if (devices.isEmpty()) {
            throw new DeviceNotFoundException("Empty list", HttpStatus.NOT_FOUND);
        }
        return devices;
    }

    @PatchMapping("{deviceId}/{deviceState}")
    @ResponseStatus(code = HttpStatus.OK)
    public void changeState(@PathVariable Long deviceId, @PathVariable DeviceState deviceState){
    }

    @PutMapping("{deviceId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateDevice(@PathVariable Long deviceId, @Valid @RequestBody DeviceDto deviceDto){

    }

    @PostMapping("{deviceId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void notify(@PathVariable Long deviceId, EventDto eventDto){

    }

    @DeleteMapping("{deviceId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteDevice(@PathVariable Long deviceId){

    }

}
