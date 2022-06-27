package logic;

import gui.SoundEffects;
import logic.PiecesImp.*;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public Pieces[][] getBoard() {
        return board;
    }
    private Side turn;
    private final Pieces[][] board = new Pieces[5][5];
    private final List<Pieces> capturedByBlack = new ArrayList<>();
    private final List<Pieces> capturedByWhite = new ArrayList<>();
    private static Board boardObj;

    public static Board getInstance(){
        if (boardObj == null)
            boardObj = new Board();
        return boardObj;
    }

    private Board() {
        turn = Side.BLACK;
        board[0][4]= new Lancer(Side.WHITE);
        board[0][3]= new Bishop(Side.WHITE);
        board[0][2]= new SilverGeneral(Side.WHITE);
        board[0][1]= new GoldGeneral(Side.WHITE);
        board[0][0]= new King(Side.WHITE);
        board[1][0]= new Pawn(Side.WHITE);
        board[3][4]= new Pawn(Side.BLACK);
        board[4][0]= new Lancer(Side.BLACK);
        board[4][1]= new Bishop(Side.BLACK);
        board[4][2]= new SilverGeneral(Side.BLACK);
        board[4][3]= new GoldGeneral(Side.BLACK);
        board[4][4]= new King(Side.BLACK);
    }
    public void restart() {
        boardObj = null;
    }
    public static void update(int gridI, int gridJ) {
        switch (Board.getInstance().getBoard()[gridI][gridJ].getSide()) {
            case WHITE:
                if (gridI == 3 || gridI == 4) {
                    if (!(Board.getInstance().getBoard()[gridI][gridJ] instanceof King
                            || Board.getInstance().getBoard()[gridI][gridJ] instanceof GoldGeneral
                            || Board.getInstance().getBoard()[gridI][gridJ] instanceof UpdatedLancer
                            || Board.getInstance().getBoard()[gridI][gridJ] instanceof UpdatedBishop
                            || Board.getInstance().getBoard()[gridI][gridJ] instanceof UpdatedPawn
                            || Board.getInstance().getBoard()[gridI][gridJ] instanceof UpdatedSilverGeneral))
                        SoundEffects.updateSound();
                    Board.getInstance().updateBoard(gridI,gridJ, Board.getInstance().getBoard()[gridI][gridJ].update());
                }
                break;
            case BLACK:
                if (gridI == 0 || gridI == 1) {
                    if (!(Board.getInstance().getBoard()[gridI][gridJ] instanceof King
                            || Board.getInstance().getBoard()[gridI][gridJ] instanceof GoldGeneral
                            || Board.getInstance().getBoard()[gridI][gridJ] instanceof UpdatedLancer
                            || Board.getInstance().getBoard()[gridI][gridJ] instanceof UpdatedBishop
                            || Board.getInstance().getBoard()[gridI][gridJ] instanceof UpdatedPawn
                            || Board.getInstance().getBoard()[gridI][gridJ] instanceof UpdatedSilverGeneral))
                        SoundEffects.updateSound();
                    Board.getInstance().updateBoard(gridI,gridJ, Board.getInstance().getBoard()[gridI][gridJ].update());
                }
                break;
        }
    }

    public static void printBoard() {
        for (int i = 0; i < 5 ; i++) {
            for (int j = 0; j < 5 ; j++) {
                System.out.print(Board.getInstance().board[i][j] == null ? '-' : Board.getInstance().board[i][j].getName());
            }
        }
        System.out.println();
        for (int i = 0; i < Board.getInstance().capturedByBlack.size() ; i++) {
            if (Board.getInstance().capturedByBlack.get(i) != null)
                System.out.print(Board.getInstance().capturedByBlack.get(i).getName());
        }
        System.out.println();
        for (int i = 0; i < Board.getInstance().capturedByWhite.size() ; i++) {
            if (Board.getInstance().capturedByWhite.get(i) != null)
                System.out.print(Board.getInstance().capturedByWhite.get(i).getName());
        }
        System.out.println();
    }

    public Side getTurn() {
        return turn;
    }

    public void setTurn(Side turn) {
        this.turn = turn;
    }

    public void hit(int hitterI,int hitterJ,int i,int j) {
        if (turn == Side.BLACK) {
            capturedByBlack.add(board[i][j].changeSide());
            board[i][j]=board[hitterI][hitterJ];
            board[hitterI][hitterJ] = null;
        } else {
            capturedByWhite.add(board[i][j].changeSide());
            board[i][j]=board[hitterI][hitterJ];
            board[hitterI][hitterJ] = null;
        }
    }

    public List<Pieces> getCapturedByBlack() {
        return capturedByBlack;
    }

    public List<Pieces> getCapturedByWhite() {
        return capturedByWhite;
    }

    public void updateBoard(int i , int j , Pieces pieces) {
        board[i][j]=pieces;
    }
}
