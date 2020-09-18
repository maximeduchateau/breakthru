import javax.swing.*;
import java.util.ArrayList;

public class Player {
    //member variables
    private ArrayList<Square> fleet;
    public Square[][] board;


    public Player(Square[][] board, Boolean side) {
        this.board = board;
    }


    public Move decideMove() {
        return null;
    }

}