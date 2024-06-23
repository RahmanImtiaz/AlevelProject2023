# CompSciProject2023 - Game

This project is a Java application that utilizes JavaFX for the graphical user interface and Object oriented logic. It is a 2D top-down platformer game developed as part of a computer science project, and includes various features such as player movement, enemy interactions, game state management, maze generation, and game progression mechanics. The game also includes advanced gameplay mechanics such as enemy AI for ranged and melee combat, interactive chests for health and mana recovery, and traps that add complexity to the game's levels. These elements are crucial for providing an engaging and varied gaming experience.


## Maze Generation Algorithm
The maze in our game is dynamically generated using a depth-first, recursive backtracking algorithm. This method ensures that every playthrough provides a unique maze, enhancing the game's replayability. 

The algorithm works as follows:

1. Initialization: The maze is initialized with a grid of rooms, each marked as unvisited.
2. Recursive Exploration: Starting from an initial room, the algorithm moves to an adjacent, unvisited room at random, marks it as part of the maze (visited), and recursively continues the process from this new room.
3. Backtracking: If the algorithm encounters a room with no unvisited adjacent rooms, it backtracks to the previous room and continues the exploration from there.
4. Door Placement: As the algorithm explores, it places doors between adjacent rooms that are connected as part of the maze.

This approach not only generates the maze but also integrates the game's room-based structure, allowing for seamless integration of gameplay elements such as enemies, traps, and treasures within the maze.


## Key Components and Features
- JavaFX for GUI: The game uses JavaFX for rendering the game's UI, including scenes for the game guide, settings, and gameplay.
- Game Entities: The game logic includes entities such as Player, Enemies, Projectiles, Chests, and Traps. These are managed through custom classes and interactions within the game world.
- Game Mechanics: The game implements mechanics such as player movement, shooting projectiles, enemy spawning, and collision detection. There are also health and mana bars, score tracking, and room navigation to create a maze-like structure.
- Scene Management: The game manages different scenes, including a menu scene, guide scene, and the main game scene. Scene transitions are handled through custom methods.
- Audio and Visual Elements: The game includes multimedia elements such as background music and images for setting the game's atmosphere.


## Project Structure

The project is structured with source files and resources located in specific directories:

- `src/compsciproject2023/`: Contains the Java source files for the game, including the main application logic and JavaFX components.
- `nbproject/`: Contains project configuration files for NetBeans IDE.
- `build.xml` and `manifest.mf`: Used for building and running the project.
- `SaveGameDetails.txt`: A text file for saving game details.

## Key Components

- **JavaFX**: For building the graphical user interface and handling game rendering.
- **Custom Game Logic**: The game includes custom classes for managing game entities such as players, enemies, projectiles, and rooms.

## Running the Application (IDE)

To run the application, you can use an IDE like NetBeans or any other IDE that supports JavaFX applications. Ensure you have JavaFX configured correctly in your development environment.

1. Open the project in your IDE.
2. Build the project using the IDE's build tools.
3. Run the `CompSciProject2023` main class.

## Building and Running (Terminal)

### Prerequisites
- JDK 8 or later
- Ant 1.8.0 or higher

### Build Instructions
1. Compile the Project: Run the following Ant command in the project's root directory to compile the project:
```ant compile```
2. Create JAR: Generate the executable JAR file:
```ant jar```
3. Run the Application: Execute the project directly using:
```ant run```

## Configuration

- The project is configured to run with JavaFX. Make sure to have the JavaFX SDK set up in your IDE or build path.
- The `.gitignore` file is configured to exclude build directories and IDE-specific files from version control.

## IDE Support

The project includes configuration files for NetBeans but can be imported into other IDEs that support JavaFX development.

## Contributing

Contributions to the project are welcome. Please ensure you follow the existing code style. Feel free to add new features, fix bugs, or improve the game's performance.
