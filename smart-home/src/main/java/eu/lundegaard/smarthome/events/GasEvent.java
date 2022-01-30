package eu.lundegaard.smarthome.events;

import eu.lundegaard.smarthome.house.HouseEmbeddedElement;
import eu.lundegaard.smarthome.iterator.ElectricalOutletIterator;
import eu.lundegaard.smarthome.iterator.EmbeddedElementIterator;
import eu.lundegaard.smarthome.observer.GasEventPublisher;
import eu.lundegaard.smarthome.observer.HouseListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Ilias Abdykarov
 */
@Slf4j
public class GasEvent extends EventDto implements GasEventPublisher {

    public GasEvent(String eventMessage) {
        super(EventType.GAS.returnEvent());
    }

    @Override
    public void notifyHouseElectricalPanel() {

    }

    @Override
    public void notifyHouseElectricalOutlets() {

    }
}
