package eu.lundegaard.smarthome.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ilias Abdykarov
 */
@RequestMapping("api/sensors")
@RequiredArgsConstructor
@CrossOrigin
public class SensorResource {



    @PatchMapping("{event}")
    public void changeEvent(@PathVariable String event){

    }
}
