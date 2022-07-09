

import java.lang.reflect.Method;
import java.util.Map;
import java.util.HashMap;
/**
 1.create parking lot , size;
 */
public class Commands {
    public Map<String, Method> commandsMap;

    public Commands() {
        commandsMap = new HashMap<String, Method>();
        try {
            populateCommandsHashMap();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    private void populateCommandsHashMap() throws NoSuchMethodException {
        commandsMap.put("1", ParkingLot.class.getMethod("createParkingLot"));
        commandsMap.put("2", ParkingLot.class.getMethod("park"));
        commandsMap.put("3", ParkingLot.class.getMethod("leave"));
        commandsMap.put("4", ParkingLot.class.getMethod("status"));
        commandsMap.put("5", ParkingLot.class.getMethod("getSlotNumberFromRegNo"));
      //  commandsMap.put("registration_numbers_for_cars_with_colour", ParkingLot.class.getMethod("getRegistrationNumbersFromColor", String.class));
        commandsMap.put("slot_numbers_for_cars_with_colour", ParkingLot.class.getMethod("getSlotNumbersFromColor", String.class));
        
    }
}