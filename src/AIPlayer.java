import java.util.ArrayList;

public class AIPlayer extends Player {

    private static final int INITIAL_DEPTH = 5;
    private int currentDepth;
    private Move bestMove;
    private Move globalBestMove;
    private long start;
    private boolean timeout;
    private static final int TIMEOUT_MILLISECONDS = 10000;
    private boolean goldAI = true;

    public AIPlayer(Square[][] board, Boolean goldAI) {
        super(board, goldAI);
    }

    public Move decideMove() {
        // in beginning of the decide move we record the starting time
        timeout = false;
        start = System.currentTimeMillis();
        //we increment d value and search the tree for increased amount of levels in tree
        for (int d = 0; ; d++) {
            if (d > 0) {
                globalBestMove = bestMove;
                System.out.println("completed search with depth " + currentDepth);
            }
            currentDepth = INITIAL_DEPTH + d;

            maximizer(currentDepth, Integer.MIN_VALUE, Integer.MAX_VALUE);
            //if timeout occurs, we terminate and return best move
            if (timeout) {
                return globalBestMove;
            }
        }
    }

    private int maximizer(int depth, int alpha, int beta) {
        if (System.currentTimeMillis() - start > TIMEOUT_MILLISECONDS) {
            timeout = true;
            return alpha;
        }
        if (depth == 0) {
            return computeRating(goldAI, board);
        }
        ArrayList<Move> legalMoves = computeAllLegalMoves(board, true);
        for (Move move : legalMoves) {
            move.makeMove(move);
            goldAI = !goldAI;
            int rating = minimizer(depth - 1, alpha, beta);
            goldAI = !goldAI;
            move.undoMove(move);

            if (rating > alpha) {
                alpha = rating;
                if (depth == currentDepth) {
                    bestMove = move;
                }
            }
            //if alpha is larger than beta, you do not have to look at the other branches, you can just prune them
            if (alpha >= beta) {
                return alpha;
            }
        }
        return alpha;
    }

    private int minimizer(int depth, int alpha, int beta) {
        if (depth == 0) {
            return computeRating(false, board);
        }
        ArrayList<Move> legalMoves = computeAllLegalMoves(board, goldAI);
        for (Move move : legalMoves) {
            move.makeMove(move);
            goldAI = !goldAI;
            int rating = maximizer(depth - 1, alpha, beta);
            goldAI = !goldAI;
            move.undoMove(move);//vorig filmpje

            if (rating <= beta) {
                beta = rating;
            }
            if (alpha >= beta) {
                return beta;
            }
        }
        return beta;
    }


    public ArrayList<Square> listOfAliveSquares(Boolean goldTurn, Square[][] board) {
        ArrayList<Square> aliveSquares = new ArrayList<>();
        if (goldTurn) {
            for (int i = 0; i < 11; ++i) {
                for (int j = 0; j < 11; ++j) {
                    if (board[i][j].getBoat() == null) {
                        continue;
                    }
                    if (!board[i][j].getBoat().getSilver()) {
                        aliveSquares.add(board[i][j]);
                    }
                }
            }
        } else {
            for (int i = 0; i < 11; ++i) {
                for (int j = 0; j < 11; ++j) {
                    if (board[i][j].getBoat() == null) {
                        continue;
                    }
                    if (board[i][j].getBoat().getSilver()) {
                        aliveSquares.add(board[i][j]);
                    }

                }
            }
        }
        return aliveSquares;
    }

    public ArrayList<Move> computeAllLegalMoves(Square[][] board, Boolean goldAI) {
        ArrayList<Square> listOfAliveSquares = listOfAliveSquares(goldAI, board);
        ArrayList<Move> allLegalMoves = new ArrayList<>();
        for (Square s : listOfAliveSquares) {
            for (int i = 0; i < 11; ++i) {
                for (int j = 0; j < 11; ++i) {
                    if (s.getBoat().isValidMove(s.getX(), s.getY(), i, j, board) || s.getBoat().isValidCapture(s.getX(), s.getY(), i, j, board)) {
                        allLegalMoves.add(new Move(s, board[i][j], board[i][j].getBoat()));
                    } else {
                        continue;
                    }
                }
            }
        }
        return allLegalMoves;
    }
//TODO:hier moet reward voor een bepaalde bordpositie nog in opgenomen worden en dat capture moves altijd veel interessanter zijn dan niet capture moves, dus hogere rating
    public int computeRating(boolean goldAI, Square[][] board) {
        ArrayList<Square> listOfAliveSquaresGold = listOfAliveSquares(true, board);
        ArrayList<Square> listOfAliveSquaresSilver = listOfAliveSquares(false, board);
        int silverScore = 0;
        int goldScore = 0;
        for (Square s : listOfAliveSquaresGold) {
            goldScore += s.getBoat().getValue();
        }
        for (Square s : listOfAliveSquaresSilver) {
            silverScore += s.getBoat().getValue();
        }
        if (goldAI) {
            return goldScore - silverScore;
        } else {
            return silverScore - goldScore;
        }
    }
}

