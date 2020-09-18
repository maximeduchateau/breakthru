import java.util.ArrayList;

public class Flagship extends Boat {
    //member variables
    private Boolean alive;

    public Flagship(boolean silver) {
        super(silver);
    }

    public String getType() {
        return "flagship";
    }

    public int getValue() {
        return 1000;
    }

    @Override
    public Boolean getAlive() {
        return this.alive;
    }

    public void setAlive(boolean aliveUpdate) {
        this.alive = aliveUpdate;
    }


    //override function from superclass, decide whether the move is valid for the goldenboats FOR 1 NON CAPTURE MOVE!!!
    public boolean isValidMove(int sourceX, int sourceY, int destX, int destY, Square[][] board) {
        boolean validMove = true;
        //not valid if the destination position equals the beginningposition.
        //also not valid if both the X and Y coordinates change (meaning that is is not a horizontal or vertical move)
        if ((sourceX == destX && sourceY == destY) || (sourceX != destX && sourceY != destY)) {
            validMove = false;
        }
        //use noPieceInWay function below to check whether there are no chesspieces in the way
        if (!noPieceInWay(sourceX, sourceY, destX, destY, board)) {
            validMove = false;
        }
        if (board[sourceX][sourceY].getBoat().getSilver()) {
            validMove = false;
        }
        return validMove;
    }


    //checks wheter there are no chesspieces at destination coordinates or on the way over there.
    public boolean noPieceInWay(int sourceX, int sourceY, int destX, int destY, Square[][] board) {
        boolean noPieceInWay = true;
        // case for vertical moves
        if (sourceX != destX) {
            //Vertical case and the destination X coordinate is larger than the source X coordinate
            if (destX > sourceX) {
                for (int i = 1; i <= destX - sourceX; i++) {
                    if (!(board[sourceX + i][sourceY].getBoat() == null)) {
                        noPieceInWay = false;
                        break;
                    }
                }
                //Vertical case and the destination X coordinate is smaller than the source X coordinate
            } else {
                for (int i = 1; i <= sourceX - destX; i++) {
                    if (!(board[sourceX - i][sourceY].getBoat() == null)) {
                        noPieceInWay = false;
                        break;
                    }

                }
            }
        }
        //case for horizontal movess
        else if (sourceY != destY) {
            //horizontal case and the destination Y coordinate is larger than the source Y coordinate
            if (destY > sourceY) {
                for (int i = 1; i <= destY - sourceY; i++) {
                    if (!(board[sourceX][sourceY + i].getBoat() == null)) {
                        noPieceInWay = false;
                        break;
                    }
                }
                //horizontal case and the destination Y coordinate is smaller than the source Y coordinate
            } else {
                for (int i = 1; i <= sourceY - destY; i++) {
                    if (!(board[sourceX][sourceY - i].getBoat() == null)) {
                        noPieceInWay = false;
                        break;
                    }

                }
            }
        }
        return noPieceInWay;
    }

    public boolean isValidCapture(int sourceX, int sourceY, int destX, int destY, Square[][] board) {
        boolean validCapture = true;
        //not valid if the destination position equals the beginningposition.
        //also not valid if either x or y value remains constant (must be diagonally)
        if ((sourceX == destX && sourceY == destY) || (sourceX == destX && sourceY != destY || sourceX != destX && sourceY == destY)) {
            validCapture = false;
        }
        //capture is only valid if destination is adjacent position
        if ((Math.abs(sourceX - destX)) != 1 || Math.abs(sourceY - destY) != 1) {
            validCapture = false;
        }
        if (!board[destX][destY].getBoat().getSilver()) {
            validCapture = false;
        }
        if (board[sourceX][sourceY].getBoat().getSilver()) {
            validCapture = false;
        }
        return validCapture;
    }

    public int getScoreForBoatPosition(int row, int column) {
        int[][] positionWeight =
                {{1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000},
                        {1000, 500, 500, 500, 500, 500, 500, 500, 500, 500, 1000},
                        {1000, 500, -10, -10, -10, -10, -10, -10, -10, 500, 1000},
                        {1000, 500, -10, -5, 0, 0, 0, -5, -10, 500, 1000},
                        {1000, 500, -10, -5, 0, 0, 0, -5, -10, 500, 1000},
                        {1000, 500, -10, 0, 0, 0, 0, 0, -10, 500, 1000},
                        {1000, 500, -10, 0, 0, 0, 0, 0, -10, 500, 1000},
                        {1000, 500, -10, -5, 0, 0, 0, -5, -10, 500, 1000},
                        {1000, 500, -10, -10, -10, -10, -10, -10, -10, 500, 1000},
                        {1000, 500, 500, 500, 500, 500, 500, 500, 500, 1000},
                        {1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000}};
        return positionWeight[row][column];
    }
}


