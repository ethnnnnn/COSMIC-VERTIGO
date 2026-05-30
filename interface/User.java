import java.util.*;
public class User {

    Scanner input = new Scanner(System.in);

    // counts for stars visited, planets discovered, and planets explored
    // like scores, displayed in the death scene
    private int stars;
    private int planets;
    private int explored;

    // stuff to manage the ship (players in-game attributes)
    private Ship ship;

    // the current star
    private Star star;

    private String name;

    // stupid easter egg henry wanted to add
    private boolean error = false;

    // constructor
    public User(String name) {
        ship = new Ship();
        star = new Star();
        stars = 1;
        planets = star.getPlanets().size();
        explored = 0;
        this.name = name;
        if(name.equals("Henry W")||name.equals("Ethan M") ){
            this.name="E̸̛̫͔̤̝͍̮̳͔̬̪̺̣͗̓̿̈́̾͘͠r̸̛̤͓͉͎͎̞̈́͋͒̀́͆̕͝R̷̛͉̩͕̯̫̠͓̤̣̹͇̿̍͑̚ơ̴̡̧̠̰̲̜̼̯͎̼͎̟̤̈́̾͂̉͆̑̈́̀͑̅͘r̴̡͎̞̰̤͇͚̘̹̊̓̀̆̎͗͝ͅ ̷͕̇̍͗̌̏̔͛̒̉̉̆̏͂͘͠ͅn̴͍̭͈͇̼̞̝̉̑̏̽̓A̸̢̘̦̮̻̹̺͕̟̋̌̂̀̊̓̈́͆̓̾͒͒͌͘͜M̴̡̡̛͉̫̣͉͍̼͉͕̄̒̆̃̐̓̀̈́͆̌͜ͅe̸̢̹̱͉̖̪̣͓͂͒͛̈́̌͆͘ ̷͔̠̖̿͆͒͐̀̄̈́̈́̄̽͛̃͝͝N̶̡̡̢̧̛̪̥̦͔͓͇̞̩̮͔̄͐͋̆̐͊͐͘͘͝ǫ̶̩̼̰̎̾͗̈̄̓̄́͗̾͐͑͛̕ẗ̷̡̛̻̻͚͍̬̬̰̥̳̥́̈́̉̎͐̾̍̍͛̚ ̵̪̱̭̪̩̎ͅF̸̢̪͉̘͒̓͒̍̽̋̊͋̍́̊̈́̃̆̋͠Ọ̶̧͖̮̹͓̗̿̈́̒̅̈͒̊̈́̾͛̏̅͜ͅŪ̵̯̞̲̭̞̭̤̝̪̞͙̟̒̍͌͆͜N̶̢̨͍̱̺̠͍̭̫͍̯̙̗̥͂̏̍͑͝ͅd̴̨͕̜͉̯͎̠̖̣͖̝̯̋̆̀́ͅ";
            error = true;
            star.setError(true);
        }
    }

    // tutorial display
    public void tutorial() {
        if(error == true){
            System.out.println("ℇ☈☈⌾☈ ℇ☈☈⌾☈ ℇ☈☈⌾☈ ℇ☈☈⌾☈ ℇ☈☈⌾☈ ℇ☈☈⌾☈ 𝕰⃦̳̿𝕽⃦̳̿𝕽⃦̳̿𝕺⃦̳̿𝕽⃦̳̿ 𝕰⃦̳̿𝕽⃦̳̿𝕽⃦̳̿𝕺⃦̳̿𝕽⃦̳̿ 𝕰⃦̳̿𝕽⃦̳̿𝕽⃦̳̿𝕺⃦̳̿𝕽⃦̳̿ 𝕰⃦̳̿𝕽⃦̳̿𝕽⃦̳̿𝕺⃦̳̿𝕽⃦̳̿ 𝕰⃦̳̿𝕽⃦̳̿𝕽⃦̳̿𝕺⃦̳̿𝕽⃦̳̿ 𝕰⃦̳̿𝕽⃦̳̿𝕽⃦̳̿𝕺⃦̳̿𝕽⃦̳̿ 𝕰⃦̳̿𝕽⃦̳̿𝕽⃦̳̿𝕺⃦̳̿𝕽⃦̳̿ 𝕰⃦̳̿𝕽⃦̳̿𝕽⃦̳̿𝕺⃦̳̿𝕽⃦̳̿ 𝕰⃦̳̿𝕽⃦̳̿𝕽⃦̳̿𝕺⃦̳̿𝕽⃦̳̿ 𝕰⃦̳̿𝕽⃦̳̿𝕽⃦̳̿𝕺⃦̳̿𝕽⃦̳̿ 𝕰⃦̳̿𝕽⃦̳̿𝕽⃦̳̿𝕺⃦̳̿𝕽⃦̳̿ 𝕰⃦̳̿𝕽⃦̳̿𝕽⃦̳̿𝕺⃦̳̿𝕽⃦̳̿ 𝕰⃦̳̿𝕽⃦̳̿𝕽⃦̳̿𝕺⃦̳̿𝕽⃦̳̿ 𝕰⃦̳̿𝕽⃦̳̿𝕽⃦̳̿𝕺⃦̳̿𝕽⃦̳̿  ℇ☈☈⌾☈ ℇ☈☈⌾☈ ℇ☈☈⌾☈ ℇ☈☈⌾☈ ℇ☈☈⌾☈ ℇ☈☈⌾☈ ℇ☈☈⌾☈ ℇ☈☈⌾☈ ℇ☈☈⌾☈ ℇ☈☈⌾☈ ℇ☈☈⌾☈ ℇ☈☈⌾☈ ℇ☈☈⌾☈ ℇ☈☈⌾☈ ℇ☈☈⌾☈ ℇ☈☈");
        }
        else {
            System.out.print("\n______ OPENING TUTORIAL ______\n\nHere is a brief explanation of the way this universe works.\n\nEach star out of the 480 in the galaxy is orbited by two to eight planets of varying sizes and types.\nEach planet can be either gaseous (such as your own system's planet Jupiter) or terrestrial (such as your home, Earth).\nThere are a variety of types of both gaseous and terrestrial planets, as shown by their color. Each type of planet offers different resources for the gathering - resources essential for your survival.\n\nThe size of each planet can be determined from its visual representation on your ship display - larger planets, while not guaranteed to, are likely to yield more resources.\nIt costs fuel to go to and from every planet, and some planets have conditions that will also wear down your shield and health, so choose where to explore wisely.\n\nYou replenish your shield with nitrogen, metal, and minerals; your health with ice, water, and minerals; your fuel with biomass, unstable elements, and sulfur; and any of the previous with oxygen, salt, or anomalies.\nIf your shield, health, or fuel drop to zero, you will die.\n\nYour goal is to discover as many solar systems and planets as you can. Fall through the universe until you hit bottom. You shall not conquer the stars, though we all seek to. Brace yourself for the vertigo, Explorer.\n\nAre you ready to fall into the cosmos? (y/n): ");
            String ready = input.nextLine();
            System.out.println("\n______ CLOSING TUTORIAL ______");
        }
    }

    // prints out the names and art for the current star system
    public String displaySystem() {
        return "\n~~~~~~ CURRENT LOCATION: " + star + " STAR SYSTEM ~~~~~~\n" + star.getAppearance() + "\n" + star.displayPlanets() + ship.getTrailAppearance() + ship.getShipAppearance() + ship.getShieldAppearance();
    }

    // the main options of the ui
    public String mainPrompt() {
        System.out.print("\u001B[37mWhat would you like to do? (1: explore, 2: cargo, 3: warp, 4: tutorial, 5: get location): ");
        return input.nextLine();
    }

    // first menu of explore option; selecting planet
    public Planet explorePrompt() {
        System.out.print("Enter the planet number to explore: ");
        int answer = Integer.parseInt(input.nextLine());
        if (answer > star.getPlanets().size() || answer < 1) {
            System.out.println("Oops, invalid result. Try again!");
            return explorePrompt();
        }
        return star.getPlanets().get(answer - 1);
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
                explored++;
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
            System.out.print(ship.getStats() + "\nWhat would you like to do? (1: rename, 2: cargo, 3: leave, 4: tutorial");
            if (!planet.getHarvested()) {
                System.out.print(", 5: gather resources");
            }
            System.out.print("): ");
            String choice = input.nextLine();
            if (choice.equals("1")) {
                System.out.print("Enter the new name for the planet: ");
                planet.setPlayersName(input.nextLine());
                System.out.println("Planet has been renamed to " + planet.getShortName() + ".");
                planetActions(planet);
            }
            else if (choice.equals("2")) {
                System.out.println("\n------ VIEWING CARGO ------\n" + ship.printCargo());
                cargo();
                planetActions(planet);
            }
            else if (choice.equals("3")) {
                ship.setFuel(ship.getFuel() - (Math.round(20 * planet.getMultiplier()) / 2));
                if (isAlive()) {
                    System.out.println("\n...... RETURNING TO SOLAR SYSTEM ......\n\n" + planet.getAppearance() + ship.getTrailAppearance() + ship.getShipAppearance() + ship.getShieldAppearance());
                }
            }
            else if (choice.equals("4")) {
                tutorial();
                planetActions(planet);
            }
            else if (choice.equals("5") && !planet.getHarvested()) {
                System.out.print("\nYou have found " + planet.getResource() + "!\nStore them in your cargo? (y/n): ");
                String answer = input.nextLine();
                if (answer.equals("y")) {
                    ship.addCargoItem(planet.getResource());
                    planet.setHarvested(true);
                }
                planetActions(planet);
            }
            else {
                System.out.println("Oops, invalid answer. Try again!");
                planetActions(planet);
            }
        }
    }

    // cargo option of main menu
    public void cargo() {
        if (!ship.cargoEmpty()) {
            System.out.print("\nWhat would you like to do? (1: exit, 2: use, 3: trash): ");
            String answer = input.nextLine();
            if (answer.equals("1")) {
                System.out.println("\n------ EXITING CARGO VIEW ------");
            }
            else {
                if (answer.equals("2")) {
                    use(slotPrompt());
                }
                else if (answer.equals("3")) {
                    trash(slotPrompt());
                    String ignore = input.nextLine();
                }
                else {
                    System.out.println("Oops, invalid answer. Try again!");
                }
                cargo();
            }
        }
        else {
            System.out.println("\n------ EXITING CARGO VIEW ------");
        }
    }

    // menu for selecting the slot to do something
    public int slotPrompt() {
        System.out.print("Enter the slot number: ");
        int slot = input.nextInt();
        if (ship.getResourceAt(slot - 1).getQuantity() == 0 || ship.slotIsEmpty(slot - 1) || slot < 1 || slot > 16) {
            System.out.println("Oops, invalid result. Try again!");
            return slotPrompt();
        }
        return slot;
    }

    // use option of cargo menu based on selected slot number
    public void use(int slot) {
        System.out.print("\nSelected: " + ship.getResourceAt(slot - 1) + "\nUse? (y/n): ");
        String ignore = input.nextLine();
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

    // checks if the players stats are all good and theyre not dead
    public boolean isAlive() {
        return (ship.getHealth() > 0 && ship.getFuel() > 0 && ship.getShield() > 0);
    }

    // death/game-over screen
    public String die() {
        return "\n\u001B[31mxxxxxx YOU CRASHED xxxxxx\n\n\u001B[37mYour ship crashed in star system " + star.getName() + ".\n\u001B[37mYou traveled to " + stars + " solar systems, discovered " + planets + " planets and explored " + explored + " planets.\nGOODBYE, " + name + ".\n\n\u001B[31mxxxxxxxxxxxxxxxxxxxxxxxxx";
    }

    // generates a new star system
    public void resetStar() {
        star = new Star();
        stars++;
        planets += star.getPlanets().size();
    }

    // getters
    public String getName() {
        return name;
    }

    public Ship getShip() {
        return ship;
    }
}
