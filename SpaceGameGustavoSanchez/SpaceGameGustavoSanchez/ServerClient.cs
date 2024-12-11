using System.Collections.Generic;
using System.Net.Sockets;
using System.Runtime.Serialization.Formatters.Binary;
using System;
using System.IO;
using System.Threading.Tasks;
using System.Windows;

public class ServerClient
{   
    private const string ServerAddress = "127.0.0.1"; // Server address (localhost)
    private const int ServerPort = 12345; // Port number to match your Java server

    // Method to send player data to the server
    public static void SendPlayerData(string playerName, int score, int time)
    {
        try
        {
            using (TcpClient client = new TcpClient(ServerAddress, ServerPort))
            using (NetworkStream stream = client.GetStream())
            using (StreamWriter writer = new StreamWriter(stream))
            {
                // Send player data to the server
                writer.WriteLine(playerName);
                writer.WriteLine(score);
                writer.WriteLine(time);
                writer.Flush();
            }
        }
        catch (Exception ex)
        {
            Console.WriteLine("Error sending data to server: " + ex.Message);
        }
    }

    // Method to retrieve player statistics from the server
    public static List<PlayerData> RetrievePlayerData()
    {
        List<PlayerData> playerDataList = null;
        try
        {
            using (TcpClient client = new TcpClient(ServerAddress, ServerPort))
            {
                client.SendTimeout = 10000; // 10 seconds timeout
                client.ReceiveTimeout = 10000; // 10 seconds timeout
                using (NetworkStream stream = client.GetStream())
                {
                    BinaryFormatter formatter = new BinaryFormatter();
                    object data = formatter.Deserialize(stream);
                    playerDataList = (List<PlayerData>)data;

                    Console.WriteLine("Received player data: " + playerDataList.Count + " records.");
                }
            }
        }
        catch (SocketException se)
        {
            Console.WriteLine("Socket error while retrieving player data: " + se.Message);
            // Consider re-attempting the operation, logging the error, or alerting the user
        }
        catch (IOException ioEx)
        {
            Console.WriteLine("IO error while retrieving player data: " + ioEx.Message);
            // Consider re-attempting the operation, logging the error, or alerting the user
        }
        catch (Exception ex)
        {
            Console.WriteLine("Unexpected error while retrieving player data: " + ex.Message);
        }

        return playerDataList;
    }




    // Class to represent player data (without rank)
    [Serializable]
    public class PlayerData
    {
        public string Name { get; set; }
        public int Score { get; set; }
        public int Time { get; set; }

        public PlayerData(string name, int score, int time)
        {
            Name = name;
            Score = score;
            Time = time;
        }
    }
}
