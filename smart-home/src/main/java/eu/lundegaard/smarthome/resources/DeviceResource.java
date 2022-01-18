package eu.lundegaard.smarthome.resources;

import eu.lundegaard.smarthome.model.DeviceDto;
import eu.lundegaard.smarthome.model.DeviceState;
import eu.lundegaard.smarthome.model.HomeDto;
import eu.lundegaard.smarthome.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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

    @PatchMapping("{deviceId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void changeState(@PathVariable Long deviceId, @RequestBody DeviceDto deviceDto){
    }

    @PutMapping("{deviceId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateDevice(@PathVariable Long deviceId, @RequestBody DeviceDto deviceDto){

    }

    @PostMapping("{deviceId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void update(@PathVariable Long deviceId){

    }

    @DeleteMapping("{deviceId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteDevice(@PathVariable Long deviceId){

    }

}
