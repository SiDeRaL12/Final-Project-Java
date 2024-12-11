package gameServer; 
import java.io.Serializable;

public class PlayerData implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private int score;
    private int time;

    public PlayerData(String name, int score, int time) {
        this.name = name;
        this.score = score;
        this.time = time;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getTime() {
        return time;
    }
}
