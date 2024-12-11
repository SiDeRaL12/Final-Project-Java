package gameServer;  // Ensure this matches the package declaration in your GameServer.java

import java.io.*;
import java.net.*;

public class ClientHandler extends Thread {
    private Socket clientSocket;

    // Constructor to accept a client socket
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            String message;
            String playerName = null;
            int score = 0;
            int time = 0;

            // Continuously read messages from the client
            while ((message = reader.readLine()) != null) {
                System.out.println("Received from client: " + message);

                // Parse player data (name, score, time)
                if (playerName == null) {
                    playerName = message;  // First message is player name
                } else if (score == 0) {
                    try {
                        score = Integer.parseInt(message);  // Second message is score
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid score format: " + message);
                    }
                } else if (time == 0) {
                    try {
                        time = Integer.parseInt(message);  // Third message is time
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid time format: " + message);
                    }

                    // Once we have all three pieces of data, add it to the GameServer
                    if (playerName != null && score > 0 && time > 0) {
                        GameServer.addPlayerData(playerName, score, time);
                        playerName = null;  // Reset for the next player
                        score = 0;
                        time = 0;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error handling clienddddt: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing client socket: " + e.getMessage());
            }
        }
    }
}



