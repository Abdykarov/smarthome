package eu.lundegaard.smarthome.mapper;

import eu.lundegaard.smarthome.dto.request.SensorRequestDto;
import eu.lundegaard.smarthome.dto.response.SensorResponseDto;
import eu.lundegaard.smarthome.model.sensor.Sensor;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author Ilias Abdykarov, ilias.abdykarov@lundegaard.eu 2/1/2022 4:08 PM
 */
@Mapper(componentModel = "spring", uses = DeviceMapper.class)
public interface SensorMapper {

    Sensor toEntity(SensorRequestDto sensorRequestDto);

    SensorResponseDto toResponse(Sensor sensor);
}
