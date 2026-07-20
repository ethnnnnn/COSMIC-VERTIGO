# COSMIC-VERTIGO

<img src="https://raw.githubusercontent.com/ethnnnnn/COSMIC-VERTIGO/refs/heads/main/assets/system.png" width="250">  <img src="https://raw.githubusercontent.com/ethnnnnn/COSMIC-VERTIGO/refs/heads/main/assets/planet.png" width="500">

A very simple procedurally generated and text-based open-world space exploration game.

This was the final project that my friend and I made for our AP Comp Sci course. We ended up going way overboard because we had too much time on our hands, and I decided it was something worth putting on GitHub. The game is entirely text-based and written in Java, and it can run in terminals on pretty much anything with a decent version of Java Runtime. Its generation system, while far more limited in scope, is inspired by the video game No Man's Sky by Hello Games.

The goal of the game is to explore as many solar systems and planets as possible. In order to do this, one must continually maintain their ship's fuel, health and shield for planetary trips and system warps - if any one of these stats drops to zero, you crash. Some strategy is required to figure out which planets are most likely to get you what you need without hurting already weak stats, based on the types/biomes and sizes of planets; however, there is also some luck of the draw. Technically, the game runs out of solar system naming combinations at the 480th system, and it will probably crash. However, I really doubt that anyone could get that far, as our game isn't necessarily the most well-balanced (although I still think I did a decent job). If you do, and something weird happens, congratulations.


<img src="https://raw.githubusercontent.com/ethnnnnn/COSMIC-VERTIGO/refs/heads/main/assets/title.png" width="500">


## Downloading
To get the game on your own device, you need a couple things:
1. The latest zipped release from the [Releases tab](https://github.com/ethnnnnn/COSMIC-VERTIGO/releases) (unzip it on your computer please).
2. Java 17 or newer installed on your device

Assuming you have those things, follow the directions according to your operating system below:

### Windows
Double-click the `run.bat` file.

### Mac/Linux
Double-click the `run.sh` file.

There's a good chance that your computer will block you from running it, since your OS probably won't grant it permissions to run a terminal window. If this happens, just run `chmod +x run.sh` in your terminal to fix it. Then try again and it should work!

### Note for Everyone:

*If you want to run it via terminal instead, you can also do that with:*

`
cd COSMIC-VERTIGO
java -jar COSMIC-VERTIGO.jar
`

***If none of this works, refer to the building process below.***

## Building
If you're weird or you can't get the above stuff to work, these are the steps to build the game yourself.

### Prereqs
1. Clone the repository with `git clone https://github.com/ethnnnnn/COSMIC-VERTIGO.git`.
2. Ensure you have as new a version of JDK/JRE/Java installed as possible.

### Compile
1. Make sure you've navigated to the repository folder with `cd COSMIC-VERTIGO`.
2. Compile with `javac src/Main.java src/entities/*.java src/environment/*.java src/utils/*.java`.
3. Package into a JAR file with `jar cvfm COSMIC-VERTIGO.jar manifest.txt src/`.

### Run
1. Make sure you're still in the repo folder,
2. Then run with `java -jar COSMIC-VERTIGO.jar`.
