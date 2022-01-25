package eu.lundegaard.smarthome.iterator;

import eu.lundegaard.smarthome.house.ElectricalOutlet;
import eu.lundegaard.smarthome.house.HouseEmbeddedElement;
import eu.lundegaard.smarthome.observer.ElectricalOutletListener;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author Ilias Abdykarov
 */
@RequiredArgsConstructor
public class ElectricalOutletIterator implements EmbeddedElementIterator{

    private final List<ElectricalOutletListener> electricalOutlets;

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public HouseEmbeddedElement getNext() {
        return null;
    }
}
