import java.io.*;
import java.nio.file.Files;
import java.util.*;


public class SaveObject {
    public static void saveGame(GameData data, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data.toSaveString());
            System.out.println("Successfully saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving game data.");
            e.printStackTrace();
        }
    }

    public static GameData loadGame(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            if (line != null) {
                return GameData.fromSaveString(line);
            }
            return null;
        } catch (IOException e) {
            System.err.println("Error loading game data from " + fileName);
            e.printStackTrace();
            return null; 
        }
    }
public static void makeLink(String name){
        String fileName = name;
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("Error: " + fileName + " not found. Please run your save code first!");
            return;
        }

        try {
            // 1. Read all the raw bytes from your save file (i have no clue what this means)
            byte[] fileBytes = Files.readAllBytes(file.toPath());

            // 2. Convert those bytes into a clean text string
            String base64Data = Base64.getEncoder().encodeToString(fileBytes);

            // 3. Wrap it in a browser-readable data link
            // this is peak btw
            String browserDownloadLink = "data:application/octet-stream;base64," + base64Data;

            // 4. Print it out beautifully
            System.out.println("\n========================================================");
            System.out.println("         YOUR SAVE FILE DOWNLOAD LINK IS READY!        ");
            System.out.println("========================================================");
            System.out.println("Copy the entire massive line below, paste it directly");
            System.out.println("into your web browser's address bar, and press Enter:");
            System.out.println("========================================================");
            System.out.println(browserDownloadLink);
            System.out.println("========================================================");

        } catch (Exception e) {
            System.out.println("Error generating local download stream.");
            e.printStackTrace();
        }
        }

        public static void loadGame(){
        String fileName = "temp";
        Scanner scanner = new Scanner(System.in);
        System.out.println("========================================================");
        System.out.println("            WELCOME TO THE GAME FILE RELOADER           ");
        System.out.println("========================================================");
        System.out.print("Paste your file's Base64 text string here and press Enter:\n> ");
        System.out.print("Use a website like base64.guru to change your file in to base64 \n>");
        
        String inputData = scanner.nextLine().trim();

        // If you accidentally copied the "data:...base64," prefix, this cleans it automatically (thanks random person on redit)
        if (inputData.contains("base64,")) {
            inputData = inputData.substring(inputData.indexOf("base64,") + 7);
        }

        try {
            System.out.println("\nDecoding your string back into raw bytes...");
            byte[] fileBytes = Base64.getDecoder().decode(inputData);

            // Write it directly to the virtual disk as a fresh "test.dat"
            try (FileOutputStream fos = new FileOutputStream(fileName)) {
                fos.write(fileBytes);
            }
            System.out.println("--> Successfully reconstructed '" + fileName + "'!");

            // Now, immediately hand it over to your SaveObject class to load it
            System.out.println("\n--- Attempting to Load the Reconstructed File ---");
            GameData loadedGame = SaveObject.loadGame(fileName);

            if (loadedGame != null) {
                System.out.println("\n--- SUCCESS! Your Saved Game is Live ---");
                System.out.println(loadedGame);
            } else {
                System.out.println("Error: Class mismatch or corrupted data payload.");
            }
// just to make sure shit doesnt crash ig
        } catch (IllegalArgumentException e) {
            System.out.println("\n[ERROR] That text string was invalid or corrupted.");
            System.out.println("Make sure you copied the entire line with no missing characters!");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while writing the file.");
            e.printStackTrace();
        } finally {
            scanner.close();
        }
        }

    // String fileName = "test(2).dat";
    //     File file = new File(fileName);
    //     GameData test = SaveObject.loadGame(fileName);
    //     System.out.print(test);

}
