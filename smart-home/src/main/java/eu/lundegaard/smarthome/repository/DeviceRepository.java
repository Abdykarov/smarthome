package eu.lundegaard.smarthome.repository;

import eu.lundegaard.smarthome.exception.DeviceNotFoundException;
import eu.lundegaard.smarthome.model.DeviceDto;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;

/**
 * @author Ilias Abdykarov
 */
@Repository
@Data
public class DeviceRepository {
    List<DeviceDto> devices = new ArrayList<>();

    public List<DeviceDto> findAll(){
        if (devices.isEmpty()) {
            throw new DeviceNotFoundException(HttpStatus.NOT_FOUND);
        }
        return devices;
    }

    public DeviceDto findById(Long id){
        return devices.stream()
                .filter( x -> x.getId().equals(id))
                .findAny()
                .orElse(null);
    }

}
