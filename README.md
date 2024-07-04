# The Last Pokemon

## 🌟 Overview

Welcome to **The Last Pokemon**, an enchanting RPG adventure that combines classic gameplay mechanics with a captivating storyline. Embark on a journey filled with challenges, intriguing NPC interactions, and rewarding quests. This project, meticulously crafted in Java, showcases fundamental game development principles while providing a robust foundation for future enhancements.

## 🎮 Features

### 🧑‍🤝‍🧑 Player and NPC Interactions
- **Dynamic Player Movement**: Navigate the game world seamlessly using intuitive keyboard controls.
- **Engaging NPCs**: Interact with a variety of NPCs, each with unique dialogues and behaviors.
- **Rich Dialogues**: Experience immersive conversations that add depth to the game narrative.

### 🛠️ Inventory Management
- **Comprehensive Inventory System**: Manage your items with ease, up to a maximum of 20 slots.
- **Item Collection**: Gather essential items such as keys and carrots to progress through the game.

### 🌍 Level Progression
- **Multi-Level Adventure**: Traverse through multiple levels, each designed with distinct maps and objectives.
- **Challenging Objectives**: Complete specific tasks to unlock new areas and advance the storyline.

### 🎉 Events and Collision Detection
- **Interactive Events**: Engage with various in-game events, including teleportation and object interactions.
- **Robust Collision System**: Enjoy a smooth gameplay experience with precise collision detection, ensuring realistic interactions.

### 🖼️ Graphics and UI
- **Stunning Visuals**: Delight in beautifully rendered graphics using Java AWT.
- **Intuitive UI**: Access vital information through a well-designed user interface, displaying health, inventory, and messages.

### 🗄️ Database Integration
- **Persistent Game Data**: Save and load your game progress seamlessly with MySQL database integration.
- **Dynamic Game State**: Keep your game state synchronized with real-time data storage and retrieval.

## 🏗️ Project Structure

### 📦 Packages and Classes

- **DataBase**
  - **DataBase**: Manages the MySQL database connection, ensuring smooth data operations.

- **Entity**
  - **Entity**: The core class for all game entities, including player and NPCs.
  - **NPC**: Represents non-player characters with unique behaviors and dialogues.
  - **NPC_Trader**: Special NPC capable of trading items with the player.
  - **Player**: Manages player-specific actions, inventory, and interactions.

- **Main**
  - **AssetSetter**: Handles the placement of objects and NPCs within the game world.
  - **CollisionChecker**: Ensures accurate collision detection between entities and tiles.
  - **EventHandler**: Manages game events like teleportation and special interactions.
  - **GamePanel**: The heart of the game, initializing and running the game loop.
  - **KeyHandler**: Captures and processes keyboard inputs for player control.
  - **Main**: Entry point of the game, setting up the window and starting the game loop.
  - **UI**: Manages the user interface, including health bars, messages, and dialogues.

- **Object**
  - **Carrot, Key, Table, Usa1, Usa2, Stairs**: Defines various interactive objects in the game.

- **Tile**
  - **Tile**: Represents individual tiles with image and collision properties.
  - **TileManager**: Manages tile loading, drawing, and map handling.

## ⚙️ Setup and Running the Game

1. **Database Configuration**: Set up a MySQL database with the required tables. Update the connection details in the `DataBase` class.

2. **Compile and Run**: Use your favorite Java IDE or command line to compile and run the `Main` class. This will launch the game window and initiate the game loop.

3. **Controls**: Use the arrow keys for player movement and interactions.

## 🚀 Future Enhancements

- **Audio Integration**: Add background music and sound effects for a richer experience.
- **Advanced AI**: Implement more sophisticated NPC behaviors and interactions.
- **Expanded Content**: Introduce additional levels, challenges, and story elements.
- **Multiplayer Support**: Explore the possibility of adding multiplayer functionality.

## 📜 Conclusion

**The Last Pokemon** is not just a game; it’s a demonstration of classic RPG mechanics brought to life with Java. This project serves as a testament to the endless possibilities in game development, providing a solid foundation for future innovations and expansions.

Embark on this journey and experience the magic of **The Last Pokemon**!

---

Developed with passion and dedication. Enjoy the adventure! 🌟
