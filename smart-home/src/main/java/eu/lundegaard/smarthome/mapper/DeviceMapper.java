package eu.lundegaard.smarthome.mapper;

import eu.lundegaard.smarthome.dto.request.DeviceRequestDto;
import eu.lundegaard.smarthome.dto.response.DeviceResponseDto;
import eu.lundegaard.smarthome.model.device.Device;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * @author Ilias Abdykarov, ilias.abdykarov@lundegaard.eu 2/1/2022 4:07 PM
 */
@Mapper(componentModel = "spring")
public interface DeviceMapper {

    DeviceResponseDto toResponse(Device device);

    Device toEntity(DeviceRequestDto deviceRequestDto);

    Device mapToExistionEntity(@MappingTarget Device device, DeviceRequestDto deviceRequestDto);
}
