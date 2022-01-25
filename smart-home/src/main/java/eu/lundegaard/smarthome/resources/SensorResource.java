package eu.lundegaard.smarthome.resources;

import eu.lundegaard.smarthome.model.DeviceDto;
import eu.lundegaard.smarthome.observer.DeviceListener;
import eu.lundegaard.smarthome.events.EventType;
import eu.lundegaard.smarthome.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ilias Abdykarov
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/sensors")
@CrossOrigin
public class SensorResource {

   private final SensorRepository sensorRepository;

    @PatchMapping("{sensorId}/{eventType}")
    @ResponseStatus(code = HttpStatus.OK)
    public void reactToExternalEvent(@PathVariable EventType eventType, @PathVariable Long sensorId){

    }

    @PostMapping("{sensorId}/install")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void installSensorIntoNewDevice(@RequestBody DeviceDto deviceDto, @PathVariable Long sensorId){

    }

    @GetMapping("{sensorId}/listeners")
    public List<DeviceListener> getObservers(@PathVariable Long sensorId){
        return null;
    }

    @PostMapping("{sensorId}/listeners/{listenerId}")
    public void attachSubscriber(@PathVariable Long sensorId, @PathVariable Long listenerId) {

    }

    @DeleteMapping("{sensorId}/listeners/{listenerId}")
    public void detachSubscriber(@PathVariable Long sensorId, @PathVariable Long listenerId) {

    }


}
