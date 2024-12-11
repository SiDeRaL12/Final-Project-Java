package gameServer;

import java.io.*;
import java.net.Socket;

public class PlayerClientHandler extends Thread {
    private Socket socket;

    public PlayerClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

            // Read the client's request
            Object request = in.readObject();

            if ("RETRIEVE".equals(request)) {
                // Send the player data list to the client
                out.writeObject(GameServer.getPlayerData());
                out.flush();
            } else if (request instanceof String) {
                // Handle adding player data
                String playerName = (String) request;
                int score = in.readInt();
                int time = in.readInt();

                GameServer.addPlayerData(playerName, score, time);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error handling client: " + e.getMessage());
        }
    }
}
