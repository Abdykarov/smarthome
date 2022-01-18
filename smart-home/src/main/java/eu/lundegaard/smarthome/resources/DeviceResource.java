package eu.lundegaard.smarthome.resources;

import eu.lundegaard.smarthome.model.DeviceDto;
import eu.lundegaard.smarthome.model.HomeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private List<DeviceDto> devices = Arrays.asList(new DeviceDto(),)

    @GetMapping()
    public List<DeviceDto> findAll(){
    }

}
