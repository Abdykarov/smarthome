package eu.lundegaard.smarthome.resources;

import eu.lundegaard.smarthome.model.DeviceDto;
import eu.lundegaard.smarthome.model.DeviceState;
import eu.lundegaard.smarthome.events.EventDto;
import eu.lundegaard.smarthome.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ilias Abdykarov
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/devices")
@CrossOrigin
public class DeviceResource {

    private final DeviceRepository deviceRepository;

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public List<DeviceDto> findAll(){

        return null;
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
