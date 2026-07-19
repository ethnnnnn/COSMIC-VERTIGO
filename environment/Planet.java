import java.util.*;
import java.io.*;
public class Planet {
    
    private String type;
    private String name;
    private String shortName;
    private String biome;
    private String size;
    private Resource resource;
    private String occupied;
    private boolean explored;
    private boolean harvested;

    private static final String[] TYPES = {"Terrestrial","Gaseous"};
    private static final String[] TERRESTRIAL_BIOMES = {"Lush","Frozen","Toxic","Scorched","Sand","Paradise","Barren","Ocean","Marsh","Exotic","Volcanic"};
    private static final String[] TERRESTRIAL_COLORS = {"\u001B[32m","\u001B[37m","\u001B[33m","\u001B[31m","\u001B[33m","\u001B[32m","\u001B[37m","\u001B[36m","\u001B[32m","\u001B[35m","\u001B[31m"};
    private static final String[] GAS_BIOMES = {"Hydrogen","Ice","Ammonia","Alkali","Silicate"};
    private static final String[] GAS_COLORS = {"\u001B[36m","\u001B[37m","\u001B[31m","\u001B[32m","\u001B[33m"};
    private static final String[] SIZES = {"Dwarf","Mini-Planet","World","Mega-Planet","Giant"};
    private static final String[] GAS_APPEARANCES= {"@","0","($)","/$$$\\\n\\$$$/"," /$$$$$\\\n($$$$$$$)\n \\$$$$$/"};
    private static final String[] TERRESTRIAL_APPEARANCES = {"o","O","(#)","/###\\\n\\###/"," /#####\\\n(#######)\n \\#####/"};

    // constructor
    public Planet(String shortName, String starName) {
        this.shortName = shortName;
        name = starName + " \u001B[37m" + shortName;
        size = M.choose(SIZES);
        type = M.choose(TYPES);
        setBiome();
        setResource();
        explored = false;
        harvested = false;
    }

    // getters
    public String getName(){
        return name;
    }

    public String getType() {
        return type;
    }

    public String getBiome() {
        return biome;
    }

    public String getSize() {
        return size;
    }

    public Resource getResource() {
        return resource;
    }

    public String getShortName() {
        return shortName;
    }

    public boolean getHarvested() {
        return harvested;
    }

    public boolean getExplored() {
        return explored;
    }

    // gets a random multiplier based on size
    // used for damage taken and resource gathering
    public double getMultiplier() {
        return (double) Math.round(((Math.random() + 1) * (M.find(SIZES, size) + 1.0) / 4.0) * 100) / 100;
    }

    // quick ways to display explored info
    public String getExploredIcon() {
        if (explored == true) {
            return "\u001B[32m(!)\u001B[37m";
        }
        return "\u001B[31m(?)\u001B[37m";
    }

    public String getExploredInfo() {
        if (explored == true) {
            return "\u001B[32m(Explored)\u001B[37m";
        }
        return "\u001B[31m(Unexplored)\u001B[37m";
    }

    // setters
    public void setExplored(boolean explored) {
        this.explored = explored;
    }

    public void setHarvested(boolean harvested) {
        this.harvested = harvested;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name){
        this.name = name;
    }

    // random biome generated based on gas or rock type
    public void setBiome() {
        if (type.equals("Terrestrial")) {
            biome = M.choose(TERRESTRIAL_BIOMES);
        }
        else if (type.equals("Gaseous")) {
            biome = M.choose(GAS_BIOMES);
        }
    }

    // generates resources on planet based on biome/type
    public void setResource() {
        if (type.equals("Terrestrial")) {
            resource = new Resource(type, M.find(TERRESTRIAL_BIOMES, biome), TERRESTRIAL_COLORS[M.find(TERRESTRIAL_BIOMES, biome)]);
        }
        else if (type.equals("Gaseous")) {
            resource = new Resource(type, M.find(GAS_BIOMES, biome), GAS_COLORS[M.find(GAS_BIOMES, biome)] );
        }
    }

    public void setPlayersName(String playersName) {
        shortName = playersName;
        if (name.indexOf("(") == -1) {
            name += " (" + playersName + ")";
        }
        else {
            name = name.substring(0, name.indexOf(" (")) + " (" + playersName + ")";
        }
    }

    public String getColor() {
        if (type.equals("Terrestrial")) {
            return TERRESTRIAL_COLORS[M.find(TERRESTRIAL_BIOMES, biome)];
        }
        return GAS_COLORS[M.find(GAS_BIOMES, biome)];
    }

    public String getAppearance() {
        if (type.equals("Terrestrial")) {
            return getColor() + TERRESTRIAL_APPEARANCES[M.find(SIZES,size)];
        }
        return getColor() + GAS_APPEARANCES[M.find(SIZES,size)];
    }

    public String displayPlanet(int planetNum) {
        if (type.equals("Terrestrial")) {
            return getAppearance() + "\n\u001B[37m" + planetNum + ": " + shortName + TERRESTRIAL_COLORS[M.find(TERRESTRIAL_BIOMES, biome)] + " (" + biome + ") " + getExploredInfo();
        }
        return getAppearance() + "\n\u001B[37m" + planetNum + ": " + shortName + GAS_COLORS[M.find(GAS_BIOMES, biome)] + " (" + biome + ") " + getExploredInfo();
    }

    public String toString() {
        if (type.equals("Terrestrial")) {
            return "\u001B[32m" + type + " " + TERRESTRIAL_COLORS[M.find(TERRESTRIAL_BIOMES, biome)] + biome + " \u001B[37m" + size + " " + name;
        }
        return "\u001B[33m" + type + " " + GAS_COLORS[M.find(GAS_BIOMES,biome)] + biome + " \u001B[37m" + size + " " + name;
    }
}
