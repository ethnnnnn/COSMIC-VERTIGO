import java.io.Serializable;

class GameData implements Serializable {
    private static final long serialVersionUID = 1L;
    private int health;
    private int stars;
    private int planets;
    private int explored;
    private String name;
    private double shield;
    private double fuel;
    private double maxShield;
    private double maxHealth;
    private double maxFuel;
    private String starColor;
    private String starName;
    static Map<String, String> starList = new HashMap<>();
    private Map<Integer, Resource> cargo = new HashMap<>();


    // Turn data into a single comma-separated text line
    public String toSaveString() {
        // string.join just makes it something;somthing;somthing
        return health + "," + name + "," + String.join(";", TERRESTRIAL_RESOURCES);
    }

    // Rebuild data from a single text line
    public static GameData fromSaveString(String line) {
        try {
            String[] parts = line.split(",");
            GameData data = new GameData();
            data.health = Integer.parseInt(parts[0]);
            data.name = parts[1];
            data.TERRESTRIAL_RESOURCES = parts[2].split(";");
            return data;
            //i really dont know what the hell this is but somebody told me to do it
            // i think this just makes it so my code doesnt crash if it fails
        } catch (Exception e) {
            System.err.println("Failed to parse save file structure.");
            return null;
        }
    }
// Prints out its value
    public String toString(){
        return "Heath: " + health + "\nName: " + name +"\nResources: " + java.util.Arrays.toString(TERRESTRIAL_RESOURCES);
    }
}
