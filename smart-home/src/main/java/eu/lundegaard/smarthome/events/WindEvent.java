package eu.lundegaard.smarthome.events;

/**
 * @author Ilias Abdykarov, ilias.abdykarov@lundegaard.eu 1/31/2022 5:41 AM
 */
public class WindEvent extends EventDto{
    public WindEvent() {
        super(EventType.STRONG_WIND.returnEvent());
    }
}
