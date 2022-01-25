package eu.lundegaard.smarthome.events;

/**
 * @author Ilias Abdykarov
 */
public enum EventType implements EventStringOperation{

    WIND {
        @Override
        public String returnEvent(){
            return "Wind started, windows are closing";
        }
    },
    GAS {
        @Override
        public String returnEvent(){
            return "Gas has been leaked, turning off the electricity in the house";
        }
    },
    FIRE {
        @Override
        public String returnEvent(){
            return "Fire started in house, the alarm is on and firefighters are called";
        }
    },
    SNOW {
        @Override
        public String returnEvent(){
            return "Snow started, electric furnace started working";
        }
    };

}
