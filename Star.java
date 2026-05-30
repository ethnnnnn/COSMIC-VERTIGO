import java.util.*;

public class Star {

    private String color;
    private String name;
    private boolean error = false;
    private ArrayList<Planet> planets = new ArrayList<>();

    // existing names
    static Map<String, String> starList = new HashMap<>();
    
    private static final String[] COLORS = {"Red", "Orange", "Yellow", "Green", "Blue", "Purple", "White"};
    private static final String[] TEXT_COLORS = {"\u001B[31m", "\u001B[38;2;255;165;0m", "\u001B[33m", "\u001B[32m", "\u001B[36m", "\u001B[35m", "\u001B[37m"};
    private static final String[] PREFIX = {"Aquarius","Pisces","Aries","Taurus","Gemini","Cancer","Leo","Virgo","Libra","Scorpio","Sagittarius","Capricorn", "Kepler","WASP","Trappist","TOI","Geliese","Dimidium","Wolf","Hades"};
    private static final String[] SUFFIX = {"\u03B1", "\u03B2", "\u03B3", "\u03B4", "\u03B5", "\u03B6", "\u03B7", "\u03B8", "\u03B9", "\u03BA", "\u03BB", "\u03BC", "\u03BD", "\u03BE", "\u03BF", "\u03C0", "\u03C1", "\u03C2", "\u03C3", "\u03C4", "\u03C5", "\u03C6", "\u03C7", "\u03C8"};
    private static final String[] LETTER = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    
    // constructors
    public Star() {
        color = M.choose(COLORS);
        name = TEXT_COLORS[M.find(COLORS,color)] + makeStarName();
        int numPlanets = (int)(Math.random() * 7) + 2;
        while (numPlanets > 0) {
            planets.add(new Planet(makePlanetName(),name));
            numPlanets--;
        }
    }
    
    // generates random star name
    public  String makeStarName(){
        if(error==true){
            return "E̸̛̫͔̤̝͍̮̳͔̬̪̺̣͗̓̿̈́̾͘͠r̸̛̤͓͉͎͎̞̈́͋͒̀́͆̕͝R̷̛͉̩͕̯̫̠͓̤̣̹͇̿̍͑̚ơ̴̡̧̠̰̲̜̼̯͎̼͎̟̤̈́̾͂̉͆̑̈́̀͑̅͘r̴̡͎̞̰̤͇͚̘̹̊̓̀̆̎͗͝ͅ E̸̛̫͔̤̝͍̮̳͔̬̪̺̣͗̓̿̈́̾͘͠r̸̛̤͓͉͎͎̞̈́͋͒̀́͆̕͝R̷̛͉̩͕̯̫̠͓̤̣̹͇̿̍͑̚ơ̴̡̧̠̰̲̜̼̯͎̼͎̟̤̈́̾͂̉͆̑̈́̀͑̅͘r̴̡͎̞̰̤͇͚̘̹̊̓̀̆̎͗͝ͅ";
        }
        String name = M.choose(PREFIX) + " " + M.choose(SUFFIX);
        if (starList.containsKey(name)){
            if(starList.size() == 450){
                System.out.println("Bro u just win");
            }
            return  makeStarName();
        }
        starList.put(name,"0");
        return name;
    }
    
    // generates random names for all the planets in solar system
    public String makePlanetName() {
        String planetName = (int)(Math.random() * 1000) + "-" + M.choose(LETTER);
        if (error) {
            return M.choose(LETTER)+M.choose(LETTER)+M.choose(LETTER)+M.choose(LETTER)+M.choose(LETTER)+M.choose(LETTER);
        }
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

    // error setter
    public void setError(boolean error){
        this.error = error;
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

    // to-string
    public String toString() {
        return TEXT_COLORS[(M.find(COLORS,color))] + name + "\u001B[37m";
    }
}