
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Class that drives everything.
public class Main {
    public static void main(String[] args) {
        InputParser inputParser = new InputParser();
        switch (args.length) {
            case 0:
               // System.out.println("Please enter 'exit' to quit");
                System.out.println("Press");
                System.out.println("1. Parking Lot Creation");
                System.out.println("2. To park the car");
                System.out.println("3. To Remove The Car ");
                System.out.println("4. Status Of The Parking Slots ");
                System.out.println("5. Find The Car");
                System.out.println("6. Exit");

                // Interactive command-line input/output
                // Run an infinite loop
                for (;;) {
                    try {
                        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                        String inputString = bufferRead.readLine();
                        if (inputString.equalsIgnoreCase("6")) {
                            break;
                        } else if ((inputString == null) || (inputString.isEmpty())) {
                            // Do nothing
                        } else {
                            inputParser.parseTextInput(inputString.trim());
                        }
                    } catch(IOException e) {
                        System.out.println("Oops! Error in reading the input from console.");
                        e.printStackTrace();
                    }
                }
                break;
            case 1:
                // File input/output
                inputParser.parseFileInput(args[0]);
                break;
            default:
                System.out.println("Invalid input. Usage: java -jar <jar_file_path> <input_file_path>");
        }
    }
}