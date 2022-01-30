package eu.lundegaard.smarthome.events;

/**
 * @author Ilias Abdykarov
 */
public enum EventType implements EventStringOperation{

    STRONG_WIND {
        @Override
        public String returnEvent(){
            return "Wind started, windows are closing";
        }
    },
    BREAK_IN {
        @Override
        public String returnEvent(){
            return "Somebody wants to break in house, police was called and smart doors were locked";
        }
    },
    FLOOD {
        @Override
        public String returnEvent(){
            return "Water has been detected, alarm has been sent to the control panel";
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
