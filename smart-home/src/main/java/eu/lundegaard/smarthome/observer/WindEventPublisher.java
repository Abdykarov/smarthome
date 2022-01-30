package eu.lundegaard.smarthome.observer;

/**
 * @author Ilias Abdykarov
 */
public interface WindEventPublisher {

    void notifyHouseWindows();

    void notifyHouseBlinds();
}
