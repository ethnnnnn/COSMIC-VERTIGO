# COSMIC-VERTIGO
A very simple procedurally generated and text-based open-world space exploration game.

This was the final project that my friend and I made for our AP Comp Sci course. We ended up going way overboard because we had too much time on our hands, and I decided it was something worth putting on GitHub. The game is entirely text-based and written in Java, and hopefully soon it will be capable of running in terminal environments and possibly (with some Python implementation or something of the like) in browsers as well. Its generation system, while far more limited in scope, is inspired by the video game No Man's Sky by Hello Games.

The goal of the game is to explore as many solar systems and planets as possible. In order to do this, one must continually maintain their ship's fuel, health and shield for planetary trips and system warps - if any one of these stats drops to zero, you crash. Some strategy is required to figure out which planets are most likely to get you what you need without hurting already weak stats, based on the types/biomes and sizes of planets; however, there is also some luck of the draw. Technically, the game runs out of solar system naming combinations at the 480th system, and it will probably crash. However, I really doubt that anyone could get that far, as our game isn't necessarily the most well-balanced (although I still think I did a decent job). If you do, and something weird happens, congratulations.

## Downloading
To get the game on your own device, you need a couple things:
1. The latest zipped release from the Releases tab (unzip it on your computer please).
2. Java 21 or newer installed on your device.

Assuming you have those things, follow the directions according to your operating system below:

### Windows
Double-click the `run.bat` file.

### Mac/Linux
Double-click the `run.sh` file.

There's a good chance that your computer will block you from running it, since your OS probably won't grant it permissions to run a terminal window. If this happens, just run `chmod +x run.sh` in your terminal to fix it. Then try again and it should work!
