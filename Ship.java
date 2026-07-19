import java.util.*;
import java.io.*;
public class Ship {

    // stats
    private double shield;
    private double health;
    private double fuel;
    private double maxShield;
    private double maxHealth;
    private double maxFuel;

    // quick ways to print out the player art
    private String warpTrailAppearance;
    private String trailAppearance;
    private String shipAppearance;
    private String shieldAppearance;

    // cargo
    private Map<Integer, Resource> cargo = new HashMap<>();

    // constructor
    public Ship() {
        shield = 100.0;
        health = 100.0;
        fuel = 100.0;
        maxShield = 100.0;
        maxHealth = 100.0;
        maxFuel = 100.0;
        warpTrailAppearance = "\u001B[36m\u2248\u2248\u2248\u2248\u2248\u2248\u2248\u2248\u2248\u2248\u2248";
        trailAppearance = "\u001B[30m\u2248\u2248\u2248\u2248\u001B[31m\u2248\u2248\u2248\u001B[38;2;255;165;0m\u2248\u2248\u001B[33m\u2248\u001B[37m\u2248";
        shieldAppearance = "\u001B[36m\u276B\u001B[0m";
        shipAppearance = "\u001B[32m\u27A4";
    }

    // getters
    public double getShield() {
        return shield;
    }

    public double getHealth() {
        return health;
    }

    public double getFuel() {
        return fuel;
    }

    public double getMaxShield() {
        return maxShield;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public double getMaxFuel() {
        return maxFuel;
    }

    public String getTrailAppearance() {
        return trailAppearance;
    }

    public String getShipAppearance() {
        return shipAppearance;
    }

    public String getShieldAppearance() {
        return shieldAppearance;
    }

    public String getFullAppearance() {
        return trailAppearance + shipAppearance + shieldAppearance;
    }

    public String getWarpAppearance() {
        return warpTrailAppearance + shipAppearance + shieldAppearance;
    }

    // setters
    public void setShield(double shield) {
        this.shield = shield;
        if (shield > maxShield) {
            maxShield = shield;
        }
    }

    public void setHealth(double health) {
        this.health = health;
        if (health > maxHealth) {
            maxHealth = health;
        }
    }
    
    public void setFuel(double fuel) {
        this.fuel = fuel;
        if (fuel > maxFuel) {
            maxFuel = fuel;
        }
    }

    public void setCargo(Resource item) {
        cargo.put(cargo.size(), item);
    }

    // adds resource to cargo
    // if resource is already in there then increase the quantity in that Slot
    // otherwise just add resource to the first empty slot
    // unless its full, then just say that
    public String addCargoItem(Resource item) {
        for (int i = 0; i < 16; i++) {
            if (cargo.containsKey(i)) {
                Resource existing = cargo.get(i);
                if (existing != null && !existing.getType().isEmpty() && existing.getType().equals(item.getType()) && existing.getRarity().equals(item.getRarity())) {
                    existing.setQuantity(existing.getQuantity() + item.getQuantity());
                    return "Added " + item.getShortenedName() + " to your cargo.";
                }
            }
        }
        for (int i = 0; i < 16; i++) {
            if (!cargo.containsKey(i) || cargo.get(i).getType().isEmpty()) {
                cargo.put(i, item);
                return "Added " + item.getShortenedName() + " to your cargo.";
            }
        }
        return "Your cargo is full. Trash/use items to clear out slots!";
    }

    // doesnt make the spot null but makes it so theres technically nothing there
    // in other checks they check both of those things so it mgiht as well be empty
    public void removeCargo(int spot) {
       cargo.put(spot, new Resource());
    }

    // checks if the slot is null or quantity 0
    public boolean slotIsEmpty(int slotNum) {
        return !cargo.containsKey(slotNum) || cargo.get(slotNum) == null;
    }

    // the above function for every slot in cargo
    public boolean cargoEmpty() {
        boolean isEmpty = true;
        for (int i = 0; i < cargo.size(); i++) {
            if (cargo.get(i).getQuantity() != 0) {
                isEmpty = false;
            }
        }
        return cargo.isEmpty() || isEmpty;
    }

    // prints a list of all the cargo slots and their contents
    public String printCargo() {
        if (!cargoEmpty()) {
            StringBuilder temp = new StringBuilder();
            for (Map.Entry<Integer, Resource> entry : cargo.entrySet()) {
                if (!entry.getValue().getType().isEmpty()) {
                    temp.append("\nSlot ").append(entry.getKey() + 1).append(": ")
                        .append(entry.getValue().toString());
                }
            }
            return temp.toString();
        }
        return "\n(Cargo is empty)";
    }
  
    // gets either the resource in a slot or a blank slot
    public Resource getResourceAt(int slotIndex) {
        if (cargo.containsKey(slotIndex)) {
            return cargo.get(slotIndex);
        }
        return new Resource();
    }

    // returns the visual stat bars scaled based on amounts of stats/maxes
    public String getStatBar(double stat, double maxStat) {
        String result = "\u2758";
        for (int i = (int)(maxStat / 5); i <= stat; i += (int)(maxStat / 5)) {
            result += "\u25A0";
        }
        for (int i = 0; i < maxStat - stat; i += (int)(maxStat / 5)) {
            result += "\u25A1";
        }
        return result + "\u2758 (" + stat + "/" + maxStat + ")";
    }

    // returns the stat bars and #/# for each stat
    public String getStats() {
        return "\n\u001B[37m\u3010\u001B[36mShield:" + getStatBar(shield, maxShield) + " \u001B[32mHealth:" + getStatBar(health, maxHealth) + " \u001B[38;2;255;165;0mFuel:" + getStatBar(fuel, maxFuel) + "\u001B[37m\u3011";
    }

    // expends resource for whatever its purpose is
    public void useCargo(int slotIndex) {
        if (cargo.get(slotIndex).getUse().equals("health")) {
            health += cargo.get(slotIndex).getRepair();
            if (health > maxHealth) {
                maxHealth = health;
            }
        }
        else if (cargo.get(slotIndex).getUse().equals("fuel")) {
            fuel += cargo.get(slotIndex).getRepair();
            if (fuel > maxFuel) {
                maxFuel = fuel;
            }
        }
        else {
            shield += cargo.get(slotIndex).getRepair();
            if (shield > maxShield) {
                maxShield = shield;
            }
        }
        System.out.println("Replenished " + cargo.get(slotIndex).getUse() + ".\n");
        cargo.put(slotIndex, new Resource());
    }
}