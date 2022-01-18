package eu.lundegaard.smarthome.resources;

import eu.lundegaard.smarthome.model.DeviceDto;
import eu.lundegaard.smarthome.model.DeviceListener;
import eu.lundegaard.smarthome.model.EventType;
import eu.lundegaard.smarthome.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ilias Abdykarov
 */
@RestController
@RequestMapping("api/sensors")
@RequiredArgsConstructor
@CrossOrigin
public class SensorResource {

    private final SensorRepository sensorRepository;

    @GetMapping("/alarm-system")
    @ResponseStatus(code = HttpStatus.OK)
    public void triggerAlarmSystem(){

    }

    @PatchMapping("{sensorId}/{eventType}")
    @ResponseStatus(code = HttpStatus.OK)
    public void reactToExternalEvent(@PathVariable String eventType, @PathVariable Long sensorId){

    }

    @PostMapping("{sensorId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void notifyListeners(@PathVariable Long sensorId){

    }

    @PutMapping("{sensorId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void installSensorIntoDevice(@RequestBody DeviceDto deviceDto, @PathVariable Long sensorId){

    }

    @GetMapping("{sensorId}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<DeviceListener> getObservers(@PathVariable Long sensorId){
        return null;
    }

    @PutMapping("{sensorId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void attachSubscriber(@RequestBody DeviceDto deviceDto, @PathVariable Long sensorId) {

    }

    @DeleteMapping("{sensorId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void detachSubscriber(@RequestBody DeviceDto deviceDto, @PathVariable Long sensorId) {

    }


}
