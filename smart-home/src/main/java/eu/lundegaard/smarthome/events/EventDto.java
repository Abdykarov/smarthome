package eu.lundegaard.smarthome.events;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import eu.lundegaard.smarthome.observer.HouseListener;
import eu.lundegaard.smarthome.resources.HomeResource;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * @author Ilias Abdykarov
 */

@Data
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include =
        JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @JsonSubTypes.Type(value = WindEvent.class, name = "WIND_EVENT"),
        @JsonSubTypes.Type(value = GasEvent.class, name = "GAS_EVENT") })
public abstract class EventDto {
    private HomeResource homeResource;
    protected final List<HouseListener> houseListeners = List.of(homeResource.getHomeConfiguration());
    String eventMessage;

    public EventDto(String eventMessage) {
        this.eventMessage = eventMessage;
    }
}
