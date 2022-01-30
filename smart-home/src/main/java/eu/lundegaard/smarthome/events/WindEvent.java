package eu.lundegaard.smarthome.events;

import eu.lundegaard.smarthome.observer.HouseListener;
import eu.lundegaard.smarthome.observer.WindEventPublisher;

/**
 * @author Ilias Abdykarov
 */
public class WindEvent extends EventDto implements WindEventPublisher {

    public WindEvent(String eventMessage) {
        super(EventType.WIND.returnEvent());
    }

    @Override
    public void notifyHouseWindows() {

    }

    @Override
    public void notifyHouseBlinds() {

    }
}
