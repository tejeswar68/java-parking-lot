import java.util.*;

import javax.sound.midi.Synthesizer;

public class ParkingLot {
    int MAX_SIZE = 0;
    private class Car {
        String regNo;
        String color;
        public Car(String regNo, String color) {
            this.regNo = regNo;
            this.color = color;
        }
    }
    // Available slots list
    ArrayList<Integer> availableSlotList;
    // Map of Slot, Car
    Map<String, Car> map1;
    // Map of RegNo, Slot
    Map<String, String> map2;
    // Map of Color, List of RegNo
    Map<String, ArrayList<String>> map3;


    public void createParkingLot() {
        if (this.MAX_SIZE > 0) {
            System.out.println("Parking Lot is already created.");
            return;
        }

        System.out.println("Enter capacity of the parking lot");
        Scanner sc  = new Scanner(System.in);
        int lotCount = sc.nextInt();
        this.MAX_SIZE = lotCount;
        this.availableSlotList = new ArrayList<Integer>() {};
        for (int i=1; i<= this.MAX_SIZE; i++) {
            availableSlotList.add(i);
        }
        this.map1 = new HashMap<String, Car>();
        this.map2 = new HashMap<String, String>();
        this.map3 = new HashMap<String, ArrayList<String>>();
        System.out.println("Created parking lot with " + lotCount + " slots");
        System.out.println();
    }
    public void park() 
    {
        String regNo,color;
        Scanner sc  = new Scanner(System.in);
        System.out.println("Enter vehicle no:");
        regNo = sc.next();
        System.out.println("Enter colour of car:");
        color = sc.next();
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.map1.size() == this.MAX_SIZE) {
            System.out.println("Sorry, parking lot is full");
            System.out.println();
        } else {
            // Collections.sort(availableSlotList);
            String slot = availableSlotList.get(0).toString();
            Car car = new Car(regNo, color);
            this.map1.put(slot, car);
            this.map2.put(regNo, slot);
            if (this.map3.containsKey(color)) {
                ArrayList<String> regNoList = this.map3.get(color);
                this.map3.remove(color);
                regNoList.add(regNo);
                this.map3.put(color, regNoList);
            } else {
                ArrayList<String> regNoList = new ArrayList<String>();
                regNoList.add(regNo);
                this.map3.put(color, regNoList);
            }
            System.out.println("Allocated slot number: " + slot);
            System.out.println();
            availableSlotList.remove(0);
        }
    }
    public void leave() {
        String slotNo;
        System.out.println("Enter Allocated Slot no of the car:");
        Scanner sc  = new Scanner(System.in);
        slotNo = sc.next();
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.map1.size() > 0) {
            Car carToLeave = this.map1.get(slotNo);
            if (carToLeave != null) {
                this.map1.remove(slotNo);
                this.map2.remove(carToLeave.regNo);
                ArrayList<String> regNoList = this.map3.get(carToLeave.color);
                if (regNoList.contains(carToLeave.regNo)) {
                    regNoList.remove(carToLeave.regNo);
                }
                // Add the Lot No. back to available slot list.
                this.availableSlotList.add(Integer.parseInt(slotNo));
                Collections.sort(availableSlotList);
                System.out.println("Slot number " + slotNo + " is free");
                System.out.println();
            } else {
                System.out.println("Slot number " + slotNo + " is already empty");
                System.out.println();
            }
        } else {
            System.out.println("Parking lot is empty");
            System.out.println();
        }
    }
    public void status() {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } 
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Press");
        System.out.println("0. For status of available slots");
        System.out.println("1. For status of occupied slots");
        int opt = sc.nextInt();
        if (opt == 0) {
            System.out.println("Total Capacity : " + this.MAX_SIZE);
            System.out.println("Number of vacant slots : " + availableSlotList.size());
            System.out.println("List of vacant slots : ");
            for (int i = 0; i < availableSlotList.size(); i++) {
                System.out.print(availableSlotList.get(i) + " ");
            }
            System.out.println();
        } 
        else {
            if (this.map1.size() > 0) {
                System.out.println("Slot No.\tRegistration No.\tColor");
                System.out.println("--------\t----------------\t-----");
                Car car;
                for (int i = 1; i <= this.MAX_SIZE; i++) {
                    String key = Integer.toString(i);
                    if (this.map1.containsKey(key)) {
                        car = this.map1.get(key);
                        System.out.println(i + "\t\t" + car.regNo + "\t\t\t" + car.color);
                    }
                }
                System.out.println();
            } else {
                System.out.println("Parking lot is empty");
                System.out.println();
            }
        }
    }
   
    public void getSlotNumbersFromColor(String color) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.map3.containsKey(color)) {
            ArrayList<String> regNoList = this.map3.get(color);
            ArrayList<Integer> slotList = new ArrayList<Integer>();
            System.out.println();
            for (int i=0; i < regNoList.size(); i++) {
                slotList.add(Integer.valueOf(this.map2.get(regNoList.get(i))));
            }
            Collections.sort(slotList);
            for (int j=0; j < slotList.size(); j++) {
                if (!(j == slotList.size() - 1)) {
                    System.out.print(slotList.get(j) + ",");
                } else {
                    System.out.print(slotList.get(j));
                }
            }
            System.out.println();
        } else {
            System.out.println("Not found");
            System.out.println();
        }
    }
    public void getSlotNumberFromRegNo() {
        String regNo;
        System.out.println("Enter Registration Number of the car:");
        Scanner sc  = new Scanner(System.in);
        regNo = sc.next();
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.map2.containsKey(regNo)) {
            System.out.println(this.map2.get(regNo));
        } else {
            System.out.println("Not found");
            System.out.println();
        }
    }
}

 // public void getRegistrationNumbersFromColor(String color) {
    //     if (this.MAX_SIZE == 0) {
    //         System.out.println("Sorry, parking lot is not created");
    //         System.out.println();
    //     } else if (this.map3.containsKey(color)) {
    //         ArrayList<String> regNoList = this.map3.get(color);
    //         System.out.println();
    //         for (int i=0; i < regNoList.size(); i++) {
    //             if (!(i==regNoList.size() - 1)){
    //                 System.out.print(regNoList.get(i) + ",");
    //             } else {
    //                 System.out.print(regNoList.get(i));
    //             }
    //         }
    //     } else {
    //         System.out.println("Not found");
    //         System.out.println();
    //     }
    // }


