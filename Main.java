import java.util.*;

public class Main {
    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);

        // title/intro screen
        System.out.print("   ____ ___  ____  __  __ ___ ____  __     _______ ____ _____ ___ ____  ___  \n  / ___/ _ \\/ ___||  \\/  |_ _/ ___| \\ \\   / / ____|  _ \\_   _|_ _/ ___|/ _ \\ \n | |  | | | \\___ \\| |\\/| || | |      \\ \\ / /|  _| | |_) || |  | | |  _| | | |\n | |__| |_| |___) | |  | || | |___    \\ V / | |___|  _ < | |  | | |_| | |_| |\n  \\____\\___/|____/|_|  |_|___\\____|    \\_/  |_____|_| \\_\\|_| |___\\____|\\___/\n\n[KOZ-mik VUR-ti-goh]\n/\u02C8k\u0251:z.m\u026Ak \u02C8v\u025Dt\u026A\u02CCgo\u01B1 /\n(noun)\n\n1. a feeling of dizziness or awe experienced when contemplating the sheer scale of the universe.\n\n\u001B[1m2. a game by Henry Wulterkens and Ethan Mason.\u001B[0m\n\n************************************\n\nWelcome, Explorer.\n\nWhat is your name? ");
        User larry = new User(input.nextLine());
        System.out.print("\nWELCOME, " + larry.getName() + ".\nWould you like a tutorial before you begin? (y/n): ");
        if (M.equalsY(input.nextLine())) {
            larry.tutorial();
        }
        System.out.println(larry.displaySystem());

        // main sequence of things happening
        // ends if player dies
        while (larry.isAlive()) {
            System.out.println(larry.getShip().getStats());
            String choice = larry.mainPrompt();
            if (choice.equals("1")) {
                Planet planet = larry.explorePrompt();
                System.out.println(larry.goToPlanet(planet));
                larry.planetActions(planet);
            }
            else if (choice.equals("2")) {
                System.out.println("\n------ VIEWING CARGO ------\n" + larry.getShip().printCargo());
                larry.cargo();
            }
            else if (choice.equals("3")) {
                System.out.println("\n!!!!!! TRAVELING TO NEW SYSTEM !!!!!!");
                larry.getShip().setFuel(larry.getShip().getFuel() - 20);
                if (larry.isAlive()) {
                    larry.resetStar();
                    System.out.println(larry.displaySystem());
                }
            }
            else if (choice.equals("4")) {
                larry.tutorial();
            }
            else if (choice.equals("5")) {
                System.out.println(larry.displaySystem());
            }
            else {
                System.out.println("Oops, invalid answer. Try again");
            }
        }

        // death screen after the main sequence stops
        System.out.println(larry.die());
    }
}