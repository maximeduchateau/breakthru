
import java.util.Scanner;

public class Game {
    //membervariables
    private Square[][] board;
    private Boolean aiPlayersTurn;
    private AIPlayer aiPlayer;
    static Scanner scan = new Scanner(System.in);
    private Boolean goldAI;
    private Boolean goldTurn;
    private Boolean GAMEOVER = false;

    //constructor
    public Game() {
        this.board=board;
    }

    //start game is hoofdfunctie
    public void startGame() {
        //ask which fleet the AI will command
        System.out.println("type g if AI commands the gold fleet, s if AI commands the silver fleet");
        String team = scan.nextLine();
        if (team.equals("g")) {
            AIPlayer aiPlayer = new AIPlayer(board, true);
            goldAI = true;
        } else {
            AIPlayer aiPlayer = new AIPlayer(board, false);
            goldAI = false;
        }

        //begin met een startbord op te stellen mbv initializeBoard functie (die ook meteen print)
        this.board = initializeBoard();

        // gebruik teller i om te bepalen welk team aan beurt i, wanneer een valide move is gedaan wordt dit verhoogd
        int i = 1;
        //oneindige loop
        while (!GAMEOVER) {

            //beginnen met team 1, want niet even
            boolean GoldTurn = !(i % 2 == 0);
            if (GoldTurn) {
                System.out.println("turn of Golden Team!");

                //if golden AI and first first move!
                if (goldAI && i == 1) {
                    Move part1move = new Move(board[3][5], board[2][5], null);
                    Move part2move = new Move(board[7][5], board[8][5], null);
                    part1move.makeMove(part1move);
                    part2move.makeMove(part2move);
                    printBoard(board);
                    ++i;
                    continue;
                }

                //if not first turn
                //if gold AI
                if (goldAI) {
                    Move move = aiPlayer.decideMove();
                    move.makeMove(move);
                    checkIfGoldWon(board);
                        ++i;
                    continue;
                }

                //if not goldAI
                if (!goldAI) {
                    notAIMove();
                    printBoard(board);
                    if (GAMEOVER) {
                        System.out.println("GAME OVER");
                        ++i;
                    }
                    continue;
                }


            } else {
                System.out.println("turn of Silver Team");
                // if silver AI (so not gold AI)
                if (!goldAI) {
                    Move move = aiPlayer.decideMove();
                    move.makeMove(move);
                    if (move.getTargetBoat().getType().equals("flagship")){GAMEOVER=true;}
                    printBoard(board);
                    if (GAMEOVER) {
                        System.out.println("GAME OVER");
                        ++i;
                    }
                    continue;
                }

                //if gold AI

                if (goldAI) {
                    notAIMove();
                    printBoard(board);

                    if (GAMEOVER) {
                        System.out.println("GAME OVER");
                        ++i;
                    }
                    continue;
                }


            }
        }
    }

    public void notAIMove() {
        //geef coördinaten in van positie en gewenste zet
        System.out.println("give x-position of piece you'd like to move ");
        int sourceX = scan.nextInt();
        System.out.println("give y-position of piece you'd like to move ");
        int sourceY = scan.nextInt();
        System.out.println("give x-position of place you'd like to move your piece to ");
        int destX = scan.nextInt();
        System.out.println("give y-position of place you'd like to move your piece to ");
        int destY = scan.nextInt();

        //ik kreeg hier exception ik weet niet waarom
        if (board[sourceX][sourceY].getBoat().isValidMove(sourceX, sourceY, destX, destY, board) || board[sourceX][sourceY].getBoat().isValidCapture(sourceX, sourceY, destX, destY, board)) {
            board[destX][destY].setBoat(board[sourceX][sourceY].getBoat());
            board[sourceX][sourceY].setBoat(null);
            if (board[destX][destY].getBoat().getType() == "flagship") {
                System.out.println("GAME OVER, SILVER WINS");
                GAMEOVER = true; }
            checkIfGoldWon(board);

        } else {
            GAMEOVER = true;
        }
    }


    public Square[][] initializeBoard() {
        // initialize remaining boxes without any piece
        Square[][] board = new Square[11][11];
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                board[i][j] = new Square(i, j, null);
            }
        }
        // initialize silver boats
        board[1][3] = new Square(1, 3, new SilverBoat(true));
        board[1][4] = new Square(1, 4, new SilverBoat(true));
        board[1][5] = new Square(1, 5, new SilverBoat(true));
        board[1][6] = new Square(1, 6, new SilverBoat(true));
        board[1][7] = new Square(1, 7, new SilverBoat(true));
        board[9][3] = new Square(9, 3, new SilverBoat(true));
        board[9][4] = new Square(9, 4, new SilverBoat(true));
        board[9][5] = new Square(9, 5, new SilverBoat(true));
        board[9][6] = new Square(9, 6, new SilverBoat(true));
        board[9][7] = new Square(9, 7, new SilverBoat(true));
        board[3][1] = new Square(3, 1, new SilverBoat(true));
        board[3][9] = new Square(3, 9, new SilverBoat(true));
        board[4][1] = new Square(4, 1, new SilverBoat(true));
        board[4][9] = new Square(4, 9, new SilverBoat(true));
        board[5][1] = new Square(5, 1, new SilverBoat(true));
        board[5][9] = new Square(5, 9, new SilverBoat(true));
        board[6][1] = new Square(6, 1, new SilverBoat(true));
        board[6][9] = new Square(6, 9, new SilverBoat(true));
        board[7][1] = new Square(7, 1, new SilverBoat(true));
        board[7][9] = new Square(7, 9, new SilverBoat(true));
        //initialize golden boats
        board[3][4] = new Square(3, 4, new GoldBoat(false));
        board[3][5] = new Square(3, 5, new GoldBoat(false));
        board[3][6] = new Square(3, 6, new GoldBoat(false));
        board[7][4] = new Square(7, 4, new GoldBoat(false));
        board[7][5] = new Square(7, 5, new GoldBoat(false));
        board[7][6] = new Square(7, 6, new GoldBoat(false));
        board[4][3] = new Square(4, 3, new GoldBoat(false));
        board[5][3] = new Square(5, 3, new GoldBoat(false));
        board[6][3] = new Square(6, 3, new GoldBoat(false));
        board[4][7] = new Square(4, 7, new GoldBoat(false));
        board[5][7] = new Square(5, 7, new GoldBoat(false));
        board[6][7] = new Square(6, 7, new GoldBoat(false));
        //initialize Flagship
        board[5][5] = new Square(5, 5, new Flagship((false)));
        printBoard(board);
        return board;
    }

    //print Board maakt een visuele voorstelling van het bord, en print deze uit!
    static void printBoard(Square[][] board) {
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+");
        int rij, kolom;
        String boatAbreviation = "";
        for (rij = 0; rij < board.length; rij++) {
            System.out.print("|");
            for (kolom = 0; kolom < board[rij].length; kolom++) {
                //if there is no boat: empty position is printed
                if (board[rij][kolom].getBoat() == null) {
                    boatAbreviation = " ";
                }
                // if there is a golden boat we print g
                else if (board[rij][kolom].getBoat().getType().equals("goldBoat")) {
                    boatAbreviation = "g";
                }
                //if silver boat we print s
                else if (board[rij][kolom].getBoat().getType().equals("silverBoat")) {
                    boatAbreviation = "s";
                }
                //if flagship print F
                else if (board[rij][kolom].getBoat().getType().equals("flagship")) {
                    boatAbreviation = "F";
                }

                System.out.print(boatAbreviation + "|");
            }
            System.out.println("");
            System.out.println("+-+-+-+-+-+-+-+-+-+-+-+");

        }


    }
    public void checkIfGoldWon(Square[][] board){
        for (int i = 0; i < 11; ++i) {
            if (board[i][0].getBoat().getType().equals("flagship")||board[i][10].getBoat().getType().equals("flagship")||board[0][i].getBoat().getType().equals("flagship")||board[10][i].getBoat().getType().equals("flagship")){
                System.out.println("gold won");
                System.out.println("GAME OVER");
            GAMEOVER=true;
            }
}}}


