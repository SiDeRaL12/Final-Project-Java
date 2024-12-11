package gameServer; 
import java.io.*;
import java.net.*;
import java.util.*;


public class GameServer {

    private static final int PORT = 12345; // Choose a port number
    private static List<PlayerData> playerDataList = new ArrayList<>();
    private static final String DATA_FILE = "playerData.dat"; // File to store player data

    public static void main(String[] args) {
        // Load player data from file
        loadPlayerData();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);

            // Continuously accept client connections
            while (true) {
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.err.println("Error starting the server: " + e.getMessage());
        }
    }

    // Method to add player data
    public static void addPlayerData(String playerName, int score, int time) {
        playerDataList.add(new PlayerData(playerName, score, time));
        System.out.println("Player data added: " + playerName);
        savePlayerData(); // Save data every time it's added
    }

    // Helper method to get player data list
    public static List<PlayerData> getPlayerData() {
        return playerDataList;
    }

    // Load player data from file
    @SuppressWarnings("unchecked")
    private static void loadPlayerData() {
        System.out.println("Attempting to load player data...");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            playerDataList = (List<PlayerData>) ois.readObject();
            System.out.println("Player data loaded. Total records: " + playerDataList.size());
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading player data: " + e.getMessage());
        }
    }



    // Save player data to file
    private static void savePlayerData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(playerDataList);
            System.out.println("Player data saved.");
        } catch (IOException e) {
            System.err.println("Error saving player data: " + e.getMessage());
        }
    }

}
