import java.util.ArrayList;
//TODO: iets veranderen aan hoe ik altijd move.functie(move) moet aanroepen
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

    // this function executes the moves from the AI player
    public void makeMove(Move move) {
        initialSquare = move.getInitialSquare();
        targetSquare = move.getTargetSquare();
        setTargetBoat(targetSquare.getBoat());
        targetSquare.setBoat(initialSquare.getBoat());
        initialSquare.setBoat(null);
    }

    // this function undoes moves from the AI player
    public void undoMove(Move move) {
        initialSquare = move.getInitialSquare();
        targetSquare = move.getTargetSquare();
        initialSquare.setBoat(targetSquare.getBoat());
        targetSquare.setBoat(targetBoat);
    }
}


