package eu.lundegaard.smarthome.observer;

import eu.lundegaard.smarthome.events.EventDto;

/**
 * @author Ilias Abdykarov
 */
public interface DeviceListener {

    void update(EventDto event);

}
