package eu.lundegaard.smarthome.events;

/**
 * @author Ilias Abdykarov
 */
public class WindEvent extends EventDto{

    public WindEvent(String eventMessage) {
        super(EventType.WIND.returnEvent());
    }

}
