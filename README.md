# SpaceGame with Java Server Integration

## Section 1: Project Description
This project is a 2D spaceship shooter game implemented in C# with a Java-based server backend. The game offers exciting gameplay where players control a spaceship, shoot obstacles, and earn scores. The server stores player statistics for persistence and leaderboard purposes.

## Section 2: Overview
The project comprises:
1. **Frontend (C#)**:
   - Developed using WPF for an interactive UI.
   - Includes features like game mechanics, statistics display, and server communication.
2. **Backend (Java)**:
   - A multithreaded server for storing and retrieving player data.
   - Maintains player rankings and ensures data persistence across sessions.

## Section 3: System Architecture
### Components:
- **C# Frontend**: Handles game logic, UI, and sends/retrieves data from the server.
- **Java Backend**: Listens for client connections, processes data, and stores it in a serialized file.

### Workflow:
1. The game sends player data (name, score, time) to the server.
2. The server stores this data in `playerData.dat`.
3. On request, the server sends player statistics back to the frontend.

## Section 4: Data Dictionary
### Player Data:
| **Field**    | **Type** | **Description**                    |
|--------------|----------|------------------------------------|
| `Name`       | String   | Name of the player.               |
| `Score`      | Integer  | Player's score in the game.       |
| `Time`       | Integer  | Duration of the game in seconds.  |

## Section 5: Data Design
### Server-Side:
- Stores player data in a serialized `playerData.dat` file.
- Player data is a list of `PlayerData` objects.

### Client-Side:
- Sends data using a `TcpClient`.
- Retrieves statistics and binds them to a `DataGrid` for display.

## Section 6: User Interface Design
### Game UI:
- **Game Screen**: Displays the spaceship, obstacles, and score counter.
  ![image](https://github.com/user-attachments/assets/18fe1a97-6044-45d3-8c85-4b6d3b3c13a0)

- **Statistics Screen**: A `DataGrid` showing player statistics.
  ![image](https://github.com/user-attachments/assets/77c06ab5-7b68-4940-962a-1ac9d407a10f)


### Buttons:
1. **Start**: Begins a new game session.
2. **Statistics**: Displays leaderboard data from the server.
   ![image](https://github.com/user-attachments/assets/d27ce0a0-7e8e-4ef6-b7aa-e810d5237aaa)


---

### Usage Instructions
1. Clone the repository and configure the server and client.
2. Start the Java server before launching the game.
3. Play the game, and check the leaderboard via the Statistics button.

### Dependencies
- **C#**: .NET 6.0 or higher.
- **Java**: JDK 17 or higher.
