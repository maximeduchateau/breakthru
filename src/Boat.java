import java.util.ArrayList;

public abstract class Boat {
    private boolean silver;
    private String type = "";
    private int Value;
    private Boolean alive;


    public abstract int getValue();


    public Boat(Boolean silver) {
        this.silver = silver;
        this.type = type;
    }
    //deze functie wordt in beide childklassen overschreden.


    public boolean isValidMove(int sourceX, int sourceY, int destX, int destY, Square[][] board) {
        return (true);
    }

    public boolean isValidCapture(int sourceX, int sourceY, int destX, int destY, Square[][] board) {
        return (true);
    }

    public boolean getSilver() {
        return this.silver;
    }

    public String getType() {
        return this.type;
    }

    public int getScoreForPiecePosition(int row, int column) {
        return 3000000;
    }
}
