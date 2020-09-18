import java.util.ArrayList;

public class Move {
    private Square initialSquare;
    private Square targetSquare;
    private Boat targetBoat;

    public Move(Square initialSquare, Square targetSquare, Boat targetBoat) {
        this.initialSquare = initialSquare;
        this.targetSquare = targetSquare;
        this.targetBoat = targetBoat;
    }

    public Square getInitialSquare() {
        return initialSquare;
    }

    public void setInitialSquare(Square initialSquare) {
        this.initialSquare = initialSquare;
    }

    public Square getTargetSquare() {
        return targetSquare;
    }

    public void setTargetSquare(Square targetSquare) {
        this.targetSquare = targetSquare;
    }

    public Boat getTargetBoat() {
        return targetBoat;
    }

    public void setTargetBoat(Boat targetBoat) {
        this.targetBoat = targetBoat;
    }

    public String toString() {
        return initialSquare.getBoat() + ": " + initialSquare + " -> " + targetSquare;
    }

    public void makeMove(Move move) {
        initialSquare = move.getInitialSquare();
        targetSquare = move.getTargetSquare();
        setTargetBoat(targetSquare.getBoat());
        targetSquare.setBoat(initialSquare.getBoat());
        initialSquare.setBoat(null);
    }

    public void undoMove(Move move) {
        initialSquare = move.getInitialSquare();
        targetSquare = move.getTargetSquare();
        initialSquare.setBoat(targetSquare.getBoat());
        targetSquare.setBoat(targetBoat);
    }
}


