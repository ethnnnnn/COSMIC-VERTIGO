import environment.*;
import utils.*;
import java.util.*;
import java.io.*;
public class User {

    Scanner input = new Scanner(System.in);

    // counts for stars visited, planets discovered, and planets explored
    // like scores, displayed in the death scene
    private int galaxiesVisited;
    private int stars;
    private int planets;
    private int planetsVisited;

    // stuff to manage the ship (players in-game attributes)
    private Ship ship;
    private String name;

    private Galaxy galaxy;
    private Galaxy[] galaxies = new Galaxy[999];

    // constructor
    public User(String name) {
        ship = new Ship();
        galaxy = new Galaxy();
        galaxies[galaxy.getNum()] = galaxy;
        galaxiesVisited = 1;
        stars = 1;
        planets = galaxy.getCurrentStar().getPlanets().size();
        planetsVisited = 0;
        this.name = name;
    }

    // tutorial display
    public void tutorial() {
            System.out.print("\n______ OPENING TUTORIAL ______\n\nHere is a brief explanation of the way this universe works.\n\nEach star out of the 480 in the galaxy is orbited by two to eight planets of varying sizes and types.\nEach planet can be either gaseous (such as your own system's planet Jupiter) or terrestrial (such as your home, Earth).\nThere are a variety of types of both gaseous and terrestrial planets, as shown by their color. Each type of planet offers different resources for the gathering - resources essential for your survival.\n\nThe size of each planet can be determined from its visual representation on your ship display - larger planets, while not guaranteed to, are likely to yield more resources.\nIt costs fuel to go to and from every planet, and some planets have conditions that will also wear down your shield and health, so choose where to explore wisely.\n\nYou replenish your shield with nitrogen, metal, and minerals; your health with ice, water, and minerals; your fuel with biomass, unstable elements, and sulfur; and any of the previous with oxygen, salt, or anomalies.\nIf your shield, health, or fuel drop to zero, you will die.\n\nYour goal is to discover as many solar systems and planets as you can. Fall through the universe until you hit bottom. You shall not conquer the stars, though we all seek to. Brace yourself for the vertigo, Explorer.\n\nAre you ready to fall into the cosmos? (y/n): ");
    }

    // first menu of explore option: selecting planet
    public Planet explorePrompt() {
        try {
            int answer = input.nextInt();
            if (answer > galaxy.getCurrentStar().getPlanets().size() || answer < 1) {
                System.out.print("Oops, invalid answer. Try again!\nEnter the planet number to explore: ");
                return explorePrompt();
            }
        return galaxy.getPlanets().get(answer - 1);
        }
        catch (Exception e) {
            System.out.print("Oops, invalid answer. Try again!\nEnter the planet number to explore: ");
            M.ignore = input.nextLine();
            return explorePrompt();
        }
    }

    // move the user to the given planet from the prompt
    // sets the effects of the planet on the players stats
    // also prints out the planet display and some info
    public String goToPlanet(Planet planet) {
        ship.setFuel(ship.getFuel() - (Math.round(20 * planet.getMultiplier()) / 2));
        if (planet.getBiome().equals("Toxic") || planet.getBiome().equals("Exotic") || planet.getBiome().equals("Volcanic") || planet.getBiome().equals("Ammonia")) {
            ship.setHealth(ship.getHealth() - Math.round(Math.random() * 40.0));
        }
        if (planet.getBiome().equals("Frozen") || planet.getBiome().equals("Scorched") || planet.getBiome().equals("Alkali") || planet.getBiome().equals("Silicate")) {
            ship.setShield(ship.getShield() - Math.round(Math.random() * 40.0));
        }
        String result = "\n...... ";
        if (isAlive()) {
            if (planet.getExplored() == false) {
                planet.setExplored(true);
                planetsVisited++;
                result += "EXPLORED ";
            }
            else {
                result += "VISITED ";
            }
            result += planet + " ......\n\n" + planet.getAppearance() + ship.getShipAppearance() + ship.getShieldAppearance();
            return result;
        }
        return "\n";
    }

    // menu for things to do on a planet
    public void planetActions(Planet planet) {
        if (isAlive()) {
            try {
                int choice = input.nextInt();
                if (choice == 1) {
                    System.out.print("Enter the new name for the planet: ");
                    M.ignore = input.nextLine();
                    planet.setPlayersName(input.nextLine());
                    System.out.println("Planet has been renamed to " + planet.getShortName() + ".");
                    System.out.print(ship.getStats() + "\nWhat would you like to do? (1: rename, 2: cargo, 3: leave, 4: tutorial");
                    if (!planet.getHarvested()) {
                        System.out.print(", 5: gather resources");
                    }
                    System.out.print("): ");
                    planetActions(planet);
                }
                else if (choice == 2) {
                    System.out.println("\n------ VIEWING CARGO ------\n" + ship.printCargo());
                    if (!ship.cargoEmpty()) {
                        System.out.print("\nWhat would you like to do? (1: exit, 2: use, 3: trash): ");
                    }
                    M.ignore = input.nextLine();
                    cargo();
                    System.out.print(ship.getStats() + "\nWhat would you like to do? (1: rename, 2: cargo, 3: leave, 4: tutorial");
                    if (!planet.getHarvested()) {
                        System.out.print(", 5: gather resources");
                    }
                    System.out.print("): ");
                    planetActions(planet);
                }
                else if (choice == 3) {
                    ship.setFuel(ship.getFuel() - (Math.round(20 * planet.getMultiplier()) / 2));
                    if (isAlive()) {
                        System.out.println("\n...... RETURNING TO SOLAR SYSTEM ......\n\n" + planet.getAppearance() + ship.getFullAppearance());
                    }
                }
                else if (choice == 4) {
                    tutorial();
                    M.ignore = input.nextLine();
                    String ready = input.nextLine();
                    System.out.println("\n______ CLOSING TUTORIAL ______");
                    System.out.print(ship.getStats() + "\nWhat would you like to do? (1: rename, 2: cargo, 3: leave, 4: tutorial");
                    if (!planet.getHarvested()) {
                        System.out.print(", 5: gather resources");
                    }
                    System.out.print("): ");
                    planetActions(planet);
                }
                else if (choice == 5 && !planet.getHarvested()) {
                    System.out.print("\nYou have found " + planet.getResource() + "!\nStore them in your cargo? (y/n): ");
                    M.ignore = input.nextLine();
                    String answer = input.nextLine();
                    if (M.equalsY(answer)) {
                        ship.addCargoItem(planet.getResource());
                        planet.setHarvested(true);
                    }
                    System.out.print(ship.getStats() + "\nWhat would you like to do? (1: rename, 2: cargo, 3: leave, 4: tutorial");
                    if (!planet.getHarvested()) {
                        System.out.print(", 5: gather resources");
                    }
                    System.out.print("): ");
                    planetActions(planet);
                }
                else {
                    System.out.println("Oops, invalid answer. Try again!");
                    System.out.print("\nWhat would you like to do? (1: rename, 2: cargo, 3: leave, 4: tutorial");
                    if (!planet.getHarvested()) {
                        System.out.print(", 5: gather resources");
                    }
                    System.out.print("): ");
                    planetActions(planet);
                }
            }
            catch (Exception e) {
                System.out.println("Oops, invalid answer. Try again!");
                M.ignore = input.nextLine();
                System.out.print("\nWhat would you like to do? (1: rename, 2: cargo, 3: leave, 4: tutorial");
                if (!planet.getHarvested()) {
                    System.out.print(", 5: gather resources");
                }
                System.out.print("): ");
                planetActions(planet);
            }
        }
    }

    // cargo option of main menu
    public void cargo() {
        if (!ship.cargoEmpty()) {
            try {
                int answer = input.nextInt();
                if (answer == 1) {
                    System.out.println("\n------ EXITING CARGO VIEW ------");
                }
                else {
                    if (answer == 2) {
                        System.out.print("Enter the slot number: ");
                        use(slotPrompt());
                    }
                    else if (answer == 3) {
                        System.out.print("Enter the slot number: ");
                        trash(slotPrompt());
                        M.ignore = input.nextLine();
                    }
                    else {
                        System.out.println("Oops, invalid answer. Try again!");
                    }
                    System.out.print("What would you like to do? (1: exit, 2: use, 3: trash): ");
                    cargo();
                }
            }
            catch (Exception e) {
                System.out.print("Oops, invalid answer. Try again!\nWhat would you like to do? (1: exit, 2: use, 3: trash): ");
                M.ignore = input.nextLine();
                cargo();
            }
        }
        else {
            System.out.println("\n------ EXITING CARGO VIEW ------");
        }
    }

    // menu for selecting the slot to do something
    public int slotPrompt() {
        try {
            int slot = input.nextInt();
            if (ship.getResourceAt(slot - 1).getQuantity() == 0 || ship.slotIsEmpty(slot - 1) || slot < 1 || slot > 16) {
                System.out.print("Oops, invalid result. Try again!\nEnter the slot number: ");
                return slotPrompt();
            }
            return slot;
        }
        catch (Exception e) {
            System.out.print("Oops, invalid answer. Try again!\nEnter the slot number: ");
            M.ignore = input.nextLine();
            return slotPrompt();
        }
    }

    // use option of cargo menu based on selected slot number
    public void use(int slot) {
        System.out.print("\nSelected: " + ship.getResourceAt(slot - 1) + "\nUse? (y/n): ");
        M.ignore = input.nextLine();
        if (M.equalsY(input.nextLine())) {
            ship.useCargo(slot - 1);
        }
        else {
            System.out.println("Canceled.");
        }
    }

    // trash option of cargo menu based on selected slot number
    public void trash(int slot) {
        System.out.println("Removed " + ship.getResourceAt(slot - 1).getShortenedName() + " from cargo.");
        ship.removeCargo(slot - 1);
    }

    public Star warpStarPrompt() {
        try {
            int answer = input.nextInt();
            if (answer > galaxy.getWarps().size() || answer < 0) {
                System.out.print("Oops, invalid answer. Try again!\nEnter number of star to warp to (0 to exit): ");
                return warpStarPrompt();
            }
            else if (answer == 0) {
                return galaxy.getCurrentStar();
            }
            return galaxy.getWarps().get(answer - 1);
        }
        catch (Exception e) {
            System.out.print("Oops, invalid answer. Try again!\nEnter number of star to warp to (0 to exit): ");
            M.ignore = input.nextLine();
            return warpStarPrompt();
        }
    }

    public void goToStar(Star star) {
        if (star != galaxy.getCurrentStar()) {
            System.out.println("\n!!!!!! TRAVELING TO SYSTEM !!!!!!\n\n" + galaxy.getCurrentStar().getCore() + "  " + ship.getWarpAppearance() + "  " + star.getCore());
            if (M.find(galaxy.getStars(),galaxy.getCurrentStar()) + M.find(galaxy.getStars(),star) > 479) {
                ship.setFuel(ship.getFuel() - (20 * (479 % (Math.abs(M.find(galaxy.getStars(),galaxy.getCurrentStar()) - M.find(galaxy.getStars(),star) - 1)))));
            }
            else {
                ship.setFuel(ship.getFuel() - (20 * Math.abs(M.find(galaxy.getStars(), galaxy.getCurrentStar()) - M.find(galaxy.getStars(), star))));
            }
            if (isAlive()) {
                galaxy.setCurrentStar(star);
                stars++;
                planets += galaxy.getPlanets().size();
                System.out.println("\u001B[0m" + galaxy.displaySystem() + ship.getFullAppearance());
            }
        }
        else {
            System.out.println("\n****** RETURNING TO CURRENT SYSTEM ******");
        }
        galaxy.resetWarps();
        M.ignore = input.nextLine();
    }

    // generates a new star system
    public void resetStar() {
        stars++;
        planets += galaxy.getPlanets().size();
    }

    // checks if the players stats are all good and theyre not supposed to be dead
    public boolean isAlive() {
        return (ship.getHealth() > 0 && ship.getFuel() > 0 && ship.getShield() > 0);
    }

    // death/game-over screen
    public String die() {
        return "\n\u001B[31mxxxxxx YOU CRASHED xxxxxx\n\n\u001B[37mYour ship crashed in star system " + galaxy.getCurrentStar() + ".\nYou traveled to " + stars + " solar system(s), discovered " + planets + " planet(s) and explored " + planetsVisited + " planet(s).\nGOODBYE, " + name + ".\n\n\u001B[31mxxxxxxxxxxxxxxxxxxxxxxxxx";
    }

    // getters
    public String getName() {
        return name;
    }

    public Ship getShip() {
        return ship;
    }

    public Galaxy getGalaxy() {
        return galaxy;
    }
}
