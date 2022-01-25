package eu.lundegaard.smarthome.iterator;

import eu.lundegaard.smarthome.house.HouseEmbeddedElement;

/**
 * @author Ilias Abdykarov
 */
public interface EmbeddedElementIterator {

    boolean hasNext();

    HouseEmbeddedElement getNext();
}
