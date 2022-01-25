package eu.lundegaard.smarthome.resources;

import eu.lundegaard.smarthome.exception.DeviceNotFoundException;
import eu.lundegaard.smarthome.model.DeviceDto;
import eu.lundegaard.smarthome.model.DeviceState;
import eu.lundegaard.smarthome.events.EventDto;
import eu.lundegaard.smarthome.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
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
    public void updateDevice(@PathVariable Long deviceId, @RequestBody DeviceDto deviceDto){

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
