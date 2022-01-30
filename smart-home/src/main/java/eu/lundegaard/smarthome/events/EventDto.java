package eu.lundegaard.smarthome.events;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * @author Ilias Abdykarov
 */

@Data
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include =
        JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = WindEvent.class, name = "WIND_EVENT"),
        @JsonSubTypes.Type(value = GasEvent.class, name = "GAS_EVENT")
})
public abstract class EventDto {

}
