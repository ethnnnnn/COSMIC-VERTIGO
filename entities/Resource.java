import java.util.*;
import java.io.*;
public class Resource {

    // attributes of a resource
    private String type;
    private String rarity;
    private int quantity;

    // options to pick from when generating resources
    private static final String[] TERRESTRIAL_RESOURCES = {"Biomass","Icy","Unstable","Sulfuric","Saline","Nitrogenous","Metallic","Aquatic","Oxygenated","Anomalous","Mineral"};
    private static final String[] GASEOUS_RESOURCES = {"Hydrogenous","Icy","Unstable","Saline","Oxygenated"};
    private static final String[] RARITY = {"\u001B[32mCommon","\u001B[36mRare","\u001B[35mPrecious"};
    
    // constructors
    public Resource(String planetType, int resourceIndex, String color) {
        if (planetType.equals("Terrestrial")) {
            type = color + TERRESTRIAL_RESOURCES[resourceIndex];
        }
        else if (planetType.equals("Gaseous")) {
            type = color + GASEOUS_RESOURCES[resourceIndex];
        }
        rarity = M.choose(RARITY, 2.0);
        quantity = (int)(Math.random() * 3) + 1;
    }

    public Resource(String type, String rarity, int quantity) {
        this.type = type;
        this.rarity = rarity;
        this.quantity = quantity;
    }

    // constructor for an empty slot in inventory
    public Resource() {
        type = "";
        rarity = "";
        quantity = 0;
    }

    // getters
    public int getQuantity() {
        return quantity;
    }

    // gets a condensed name for some displays
    public String getShortenedName() {
        return "\u001B[37m" + quantity + rarity.substring(0, rarity.indexOf("m") + 2) + type.substring(0, type.indexOf("m") + 3) + "\u001B[37m";
    }

    // rarer items count for when using
    public int getRarityMultiplier() {
        return (M.find(RARITY, rarity) + 2) / 2;
    }

    public String getType() {
        return type.substring(type.indexOf("m") + 1);
    }

    public String getRarity() {
        return rarity;
    }

    // figures out what a resource can be used for based on its type (for use menu)
    public String getUse() {
        if (getType().equals("Icy") || getType().equals("Aquatic") || getType().equals("Mineral") || getType().equals("Oxygenated")) {
            return "health";
        }
        else if (getType().equals("Nitrogenous") || getType().equals("Metallic") || getType().equals("Saline") || getType().equals("Hydrogenous")) {
            return "shield";
        }
        return "fuel";
    }

    // randomly generates how much a stat is repaired (for use menu)
    public double getRepair() {
        return 20.0 * quantity * ((M.find(RARITY, rarity) + 2) / 2);
    }

    // setters
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // to-string
    public String toString() {
        if (getUse().equals("health")) {
            return quantity + " " + rarity + " " + type + " \u001B[37mElement(s) \u001B[32m(Health Item)\u001B[37m";
        }
        else if (getUse().equals("shield")) {
            return quantity + " " + rarity + " " + type + " \u001B[37mElement(s) \u001B[36m(Shield Item)\u001B[37m";
        }
        return quantity + " " + rarity + " " + type + " \u001B[37mElement(s) \u001B[38;5;208m(Fuel Item)\u001B[37m";
    }
}
