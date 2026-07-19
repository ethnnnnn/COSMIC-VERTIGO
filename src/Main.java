package src;
import src.entities.*;
import src.environment.*;
import src.utils.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);

        // title/intro screens
        System.out.print("   ____ ___  ____  __  __ ___ ____  __     _______ ____ _____ ___ ____  ___  \n  / ___/ _ \\/ ___||  \\/  |_ _/ ___| \\ \\   / / ____|  _ \\_   _|_ _/ ___|/ _ \\ \n | |  | | | \\___ \\| |\\/| || | |      \\ \\ / /|  _| | |_) || |  | | |  _| | | |\n | |__| |_| |___) | |  | || | |___    \\ V / | |___|  _ < | |  | | |_| | |_| |\n  \\____\\___/|____/|_|  |_|___\\____|    \\_/  |_____|_| \\_\\|_| |___\\____|\\___/\n\n[KOZ-mik VUR-ti-goh]\n/\u02C8k\u0251:z.m\u026Ak \u02C8v\u025Dt\u026A\u02CCgo\u01B1 /\n(noun)\n\n1. a feeling of dizziness or awe experienced when contemplating the sheer scale of the universe.\n\n\u001B[1m2. a game by hanktwin and ethnnnnn.\u001B[0m\n\n************************************\n\nWelcome, Explorer.\n\nWhat is your name? ");
        User larry = new User(input.nextLine());
        System.out.print("\nWELCOME, " + larry.getName() + ".\nWould you like a tutorial before you begin? (y/n): ");
        if (M.equalsY(input.nextLine())) {
            larry.tutorial();
            String ready = input.nextLine();
            System.out.println("\n______ CLOSING TUTORIAL ______");
        }

        // initial display, before entering a repeatable loop, so that the ignore sections can happen at the right times
        System.out.print(larry.getGalaxy().displaySystem() + larry.getShip().getFullAppearance() + "\n" + larry.getShip().getStats() + "\nWhat would you like to do? (1: explore, 2: cargo, 3: star map, 4: tutorial, 5: system map): ");

        // main sequence of things happening
        // ends if player dies
        while (larry.isAlive()) {
            // scenarios for if the player chooses...
            try {
                int choice = input.nextInt();
                // explore
                if (choice == 1) {
                    System.out.print("Enter the planet number to explore: ");
                    Planet planet = larry.explorePrompt();
                    System.out.print(larry.goToPlanet(planet) + "\n");
                    if (larry.isAlive()) {
                        System.out.print(larry.getShip().getStats() + "\nWhat would you like to do? (1: rename, 2: cargo, 3: leave, 4: tutorial");
                        if (!planet.getHarvested()) {
                            System.out.print(", 5: gather resources");
                        }
                        System.out.print("): ");
                        larry.planetActions(planet);
                    }
                    System.out.print(larry.getShip().getStats() + "\nWhat would you like to do? (1: explore, 2: cargo, 3: star map, 4: tutorial, 5: system map): ");
                }
                // cargo
                else if (choice == 2) {
                    System.out.println("\n------ VIEWING CARGO ------\n" + larry.getShip().printCargo());
                    if (!larry.getShip().cargoEmpty()) {
                        System.out.print("\nWhat would you like to do? (1: exit, 2: use, 3: trash): ");
                    }
                    M.ignore = input.nextLine();
                    larry.cargo();
                    System.out.print(larry.getShip().getStats() + "\nWhat would you like to do? (1: explore, 2: cargo, 3: star map, 4: tutorial, 5: system map): ");
                }
                // warp
                else if (choice == 3) {
                    System.out.print("\n****** VIEWING NEARBY STAR SYSTEMS ******\n\n" + larry.getGalaxy().starMap() + "\n\t\t\t\t\t  \u21D1\n\t\t\t\t(YOU ARE HERE)\n\nWARNING: 20 fuel cost per 1 jump distance\nEnter number of star to warp to (0 to exit): " );
                    larry.goToStar(larry.warpStarPrompt());
                    if (larry.isAlive()) {
                        System.out.print(larry.getShip().getStats() + "\nWhat would you like to do? (1: explore, 2: cargo, 3: star map, 4: tutorial, 5: system map): ");
                        M.ignore = input.nextLine();
                    }
                }
                // tutorial
                else if (choice == 4) {
                    larry.tutorial();
                    M.ignore = input.nextLine();
                    String ready = input.nextLine();
                    System.out.println("\n______ CLOSING TUTORIAL ______");
                    System.out.print(larry.getShip().getStats() + "\nWhat would you like to do? (1: explore, 2: cargo, 3: star map, 4: tutorial, 5: system map): ");
                }
                // system
                else if (choice == 5) {
                    System.out.println(larry.getGalaxy().displaySystem() + larry.getShip().getFullAppearance());
                    System.out.print(larry.getShip().getStats() + "\nWhat would you like to do? (1: explore, 2: cargo, 3: star map, 4: tutorial, 5: system map): ");
                }
                // other num
                else if (choice < 1 || choice > 5) {
                    System.out.print("Oops, invalid answer. Try again!\n\nWhat would you like to do? (1: explore, 2: cargo, 3: star map, 4: tutorial, 5: system map): ");
                    M.ignore = input.nextLine();
                }
            }
            // other anything
            catch (Exception e) {
                System.out.print("Oops, invalid answer. Try again!\n\nWhat would you like to do? (1: explore, 2: cargo, 3: star map, 4: tutorial, 5: system map): ");
                M.ignore = input.nextLine();
            }
        }
        
    // death screen after the main sequence stops
    System.out.println(larry.die());
    }

    
    }
