import entities.*;
import utils.*;
import java.util.*;
import java.io.*;
public class Star {

    private String color;
    private String name;
    private ArrayList<Planet> planets = new ArrayList<>();

    // the last star visited, for the explore menu
    private Star previous;

    // existing stars
    static Map<String, String> starList = new HashMap<>();
    
    private static final String[] COLORS = {"Red", "Orange", "Yellow", "Green", "Blue", "Purple", "White"};
    private static final String[] TEXT_COLORS = {"\u001B[31m", "\u001B[38;2;255;165;0m", "\u001B[33m", "\u001B[32m", "\u001B[36m", "\u001B[35m", "\u001B[37m"};
    private static final String[] LETTER = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    
    // constructors
    public Star(String name) {
        this.name = name;
        color = M.choose(COLORS);
        this.name = name;
        int numPlanets = (int)(Math.random() * 7) + 2;
        while (numPlanets > 0) {
            planets.add(new Planet(makePlanetName(),name));
            numPlanets--;
        }
    }

    public Star(String name, Star previous) {
        this.name = name;
        this.previous = previous;
        color = M.choose(COLORS);
        this.name = name;
        int numPlanets = (int)(Math.random() * 7) + 2;
        while (numPlanets > 0) {
            planets.add(new Planet(makePlanetName(),name));
            numPlanets--;
        }
    }
    
    // generates random names for all the planets in solar system
    public String makePlanetName() {
        String planetName = (int)(Math.random() * 1000) + "-" + M.choose(LETTER);
        for (Planet existingPlanet: planets) {
            if (planetName.equals(existingPlanet.getName())) {
                return makePlanetName();
            }
        }
        return planetName;
    }

    // print planet appearances
    public String displayPlanets() {
        String result = "";
        for (int i = 0; i < planets.size(); i++) {
            result += planets.get(i).displayPlanet(i + 1) + "\n\n";
        }
        return result;
    }


    // getters
    public String getColor() {
        return color;
    }

    public ArrayList<Planet> getPlanets() {
        return planets;
    }

    public String getName() {
        return name;
    }

    public String getAppearance() {
        return TEXT_COLORS[(M.find(COLORS,color))] + "\n \\  |  /\n--  \u2726  --\n /  |  \\\n\u001B[37m";
    }

    public String getCore() {
        return TEXT_COLORS[(M.find(COLORS,color))] + "\u2726";
    }

    public String getTextColor() {
        return TEXT_COLORS[(M.find(COLORS,color))];
    }

    public boolean getGalaxyFull() {
        return starList.size() >= 450;
    }

    // to-string
    public String toString() {
        return TEXT_COLORS[(M.find(COLORS, color))] + name + "\u001B[37m";
    }
}
