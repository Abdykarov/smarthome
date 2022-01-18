package eu.lundegaard.smarthome.repository;

import eu.lundegaard.smarthome.model.DeviceDto;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ilias Abdykarov
 */
@Repository
@Data
public class DeviceRepository {
    List<DeviceDto> devices = new ArrayList<>();
}
