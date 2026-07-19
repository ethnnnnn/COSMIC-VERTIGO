import entities.*;
import utils.*;
import java.util.*;
import java.io.*;
public class Galaxy {
    // variables
    private String color;
    private int num;
    public Star currentStar;
    private int currentPlaceNum;
    // for more efficent finding of stars
    private Map<String, Star> starNames = new HashMap<>();
    public Star[] stars = new Star[480];
    // possible warp options
    private ArrayList<Star> warps = new ArrayList<Star>();


    //lots of colors and names
    private static final String[] COLORS = {"Red", "Orange", "Yellow", "Green", "Blue", "Purple", "White"};
    private static final String[] TEXT_COLORS = {"\u001B[31m", "\u001B[38;2;255;165;0m", "\u001B[33m", "\u001B[32m", "\u001B[36m", "\u001B[35m", "\u001B[37m"};
    private static final String[] PREFIX = {"Aquarius","Pisces","Aries","Taurus","Gemini","Cancer","Leo","Virgo","Libra","Scorpio","Sagittarius","Capricorn", "Kepler","WASP","Trappist","TOI","Gliese","Dimidium","Wolf","Hades"};
    private static final String[] SIGN = {"\u2652","\u2653","\u2648","\u2649","\u264A","\u264B","\u264C","\u264D","\u264E","\u264F","\u2650","\u2651", "Kp","WA","Tr","TI","Gl","Di","Wo","\u2BE1"};
    private static final String[] SUFFIX = {"\u03B1", "\u03B2", "\u03B3", "\u03B4", "\u03B5", "\u03B6", "\u03B7", "\u03B8", "\u03B9", "\u03BA", "\u03BB", "\u03BC", "\u03BD", "\u03BE", "\u03BF", "\u03C0", "\u03C1", "\u03C2", "\u03C3", "\u03C4", "\u03C5", "\u03C6", "\u03C7", "\u03C8"};
    private static final String[] MAP_ICONS = {"\u2460","\u2461","\u2462","\u2463","\u2464","\u2465"};

    // constructor
    public Galaxy() {
        num = (int)(Math.random() * 999);
        color = M.choose(COLORS);
        currentStar = new Star(makeStarName());
        stars[makePlaceNum()] = currentStar;
    }

    public Galaxy(Star currentStar) {
        num = (int)(Math.random() * 999);
        color = M.choose(COLORS);
        this.currentStar = currentStar;
        stars[makePlaceNum()] = currentStar;
    }

    // generates random star name
    public String makeStarName() {
        String name = M.choose(PREFIX) + " " + M.choose(SUFFIX);
        //checks to see if the stars name already is in use
        if (starNames.containsKey(name)){
            return makeStarName();
        }
        return name;
    }

    // sets up the planet's place in the galaxy
    public int makePlaceNum() {
        int num = (int)(Math.random() * 480);
        if (stars[num] != null) {
            return makePlaceNum();
        }
        currentPlaceNum = num;
        return num;
    }

    public String displaySystem() {
        return "\n~~~~~~ CURRENT LOCATION: " + currentStar + " STAR SYSTEM ~~~~~~\n" + currentStar.getAppearance() + "\n" + currentStar.displayPlanets();
    }
    // for master coder to write
    public String starMap() {
        String result = "";
        for (int i = currentPlaceNum - 3; i < currentPlaceNum; i++) {
            if (i < 0) {
                if (stars[479 + i] == null) {
                    result += "\u001B[0m\u3007 \u254D\u254D ";
                }
                else {
                    result += stars[479 + i].getTextColor() + MAP_ICONS[warps.size()] + "\u001B[0m \u254D\u254D ";
                    warps.add(stars[479 + i]);
                }
            }
            else {
                if (stars[i] == null) {
                    result += "\u001B[0m\u3007 \u254D\u254D ";
                }
                else {
                    result += stars[i].getTextColor() + MAP_ICONS[warps.size()] + "\u001B[0m \u254D\u254D ";
                    warps.add(stars[i]);
                }
            }
        }
        result += stars[currentPlaceNum].getTextColor() + "\u24FF";
        for (int i = currentPlaceNum + 1; i <= currentPlaceNum + 3; i++) {
            if (i > 479) {
                if (stars[i - 479] == null) {
                    stars[i - 479] = new Star(makeStarName());
                }
                result += "\u001B[0m \u254D\u254D " + stars[i - 479].getTextColor() + MAP_ICONS[warps.size()];
                warps.add(stars[i - 479]);
            }
            else {
                if (stars[i] == null) {
                    stars[i] = new Star(makeStarName());
                }
                result += "\u001B[0m \u254D\u254D " + stars[i].getTextColor() + MAP_ICONS[warps.size()];
                warps.add(stars[i]);
            }
        }
        return result + "\u001B[0m";
    }
    // well is it full?
    public boolean isFull() {
        for (int i = 0; i < stars.length; i++) {
            if (stars[i] == null) {
                return false;
            }
        }
        return true;
    }

    //getters
    public int getNum() {
        return num;
    }

    public Star getCurrentStar() {
        return currentStar;
    }

    public int getCurrentPlaceNum() {
        return currentPlaceNum;
    }

    public Star[] getStars() {
        return stars;
    }

    public ArrayList<Planet> getPlanets() {
        return currentStar.getPlanets();
    }

    public ArrayList<Star> getWarps() {
        return warps;
    }
    //setters
    public void setCurrentStar(Star currentStar) {
        this.currentStar = currentStar;
        currentPlaceNum = M.find(stars, currentStar);
    }
    //reset Warps
    public void resetWarps() {
        warps = new ArrayList<Star>();
    }
    //prints out the galaxy
    public String toString() {
        return "Galaxy " + TEXT_COLORS[M.find(COLORS, color)] + num + "\u001B[0m";
    }
}
