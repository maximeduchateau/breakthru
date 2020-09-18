import java.util.ArrayList;

public class Square {
    //membervariables
    private Boat boat;
    private int x;
    private int y;
    private ArrayList<Square> legalMoves;

    public Square(int x, int y, Boat boat) {
        this.setBoat(boat);
        this.setX(x);
        this.setY(y);
    }

    public Boat getBoat() {
        return this.boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }
//computes a list of legal moves
    public void computeLegalMoves(Square[][] board) {
        this.legalMoves = new ArrayList<>();
        for (int i = 0; i < 11; ++i) {
            for (int j = 0; j < 11; ++j) {
                if (boat.isValidMove(x, y, i, j, board) || boat.isValidCapture(x, y, i, j, board)) {
                    legalMoves.add(board[i][j]);
                }
            }
        }

    }


            }