import java.util.ArrayList;

public abstract class Boat {
    private boolean silver;
    private String type = "";
    private int Value;

    public abstract int getValue();


    public Boat(Boolean silver) {
        this.silver = silver;
        this.type = type;
    }
    //deze functie wordt in alle childklassen overschreden.

    public boolean isValidMove(int sourceX, int sourceY, int destX, int destY, Square[][] board) {
        return (true);
    }

    public boolean isValidCapture(int sourceX, int sourceY, int destX, int destY, Square[][] board) {
        return (true);
    }

    //see if the piece is from the silver team
    public boolean getSilver() {
        return this.silver;
    }

    public String getType() {
        return this.type;
    }

    // this one is to check if something goes wrong, but I guess it could be done smarter
    public int getScoreForPiecePosition(int row, int column) {
        return 3000000;
    }
}
