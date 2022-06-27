import logic.Board;
import logic.Pieces;
import logic.PiecesImp.King;
import logic.Side;

import java.util.Scanner;

public class Main {
    public static boolean isPieceInPlace(String name,int gridI, int gridJ) {
        if (Board.getInstance().getBoard()[gridI][gridJ] == null)
            return false;
        else return name.charAt(0) == Board.getInstance().getBoard()[gridI][gridJ].getName();
    }

    public static void update(int gridI, int gridJ) {
        switch (Board.getInstance().getBoard()[gridI][gridJ].getSide()) {
            case WHITE:
                if (gridI == 3 || gridI == 4) {
                    Board.getInstance().updateBoard(gridI,gridJ, Board.getInstance().getBoard()[gridI][gridJ].update());
                }
                break;
            case BLACK:
                if (gridI == 0 || gridI == 1) {
                    Board.getInstance().updateBoard(gridI,gridJ, Board.getInstance().getBoard()[gridI][gridJ].update());
                }
                break;
        }
    }

    public static void main(String[] args) {
        Board board = Board.getInstance();
        Scanner s = new Scanner(System.in);
        while (true) {
            String str = s.nextLine();
            String[] splited = str.split("\\s+");
            String name = splited[0];
            if (name.equals("0")) {
                break;
            }
            String gridFrom = splited[1];
            String gridTo = splited[2];
            int gridFromI = gridFrom.charAt(1) - '0';
            int gridFromJ = gridFrom.charAt(0) - '0';
            int gridToI = gridTo.charAt(1) - '0';
            int gridToJ = gridTo.charAt(0) - '0';
            if (name.length()==1 && (Character.isLowerCase(name.charAt(0)) && board.getTurn()== Side.BLACK) || (Character.isUpperCase(name.charAt(0)) && board.getTurn()== Side.WHITE)) {
                if (gridToI <= 5 && gridToI > 0 && gridToJ <= 5 && gridToJ > 0) {
                    if (gridFromI == 0 && gridFromJ == 0) {
                        switch (board.getTurn()) {
                            case WHITE:
                                for (int i = 0; i < board.getCapturedByWhite().size(); i++) {
                                    Pieces temp = board.getCapturedByWhite().get(i);
                                    if (temp.getName() == name.charAt(0)) {
                                        if (board.getBoard()[gridToI - 1][gridToJ - 1] == null) {
                                            board.updateBoard(gridToI - 1, gridToJ - 1, temp);
                                            board.getCapturedByWhite().remove(i);
                                            Board.getInstance().setTurn(Board.getInstance().getTurn() == Side.BLACK ? Side.WHITE : Side.BLACK);
                                        }
                                        break;
                                    }
                                }
                                break;
                            case BLACK:
                                for (int i = 0; i < board.getCapturedByBlack().size(); i++) {
                                    Pieces temp = board.getCapturedByBlack().get(i);
                                    if (temp.getName() == name.charAt(0)) {
                                        if (board.getBoard()[gridToI - 1][gridToJ - 1] == null) {
                                            board.updateBoard(gridToI - 1, gridToJ - 1, temp);
                                            board.getCapturedByBlack().remove(i);
                                            Board.getInstance().setTurn(Board.getInstance().getTurn() == Side.BLACK ? Side.WHITE : Side.BLACK);
                                        }
                                        break;
                                    }
                                }
                                break;
                        }
                    } else if (gridFromI <= 5 && gridFromI > 0 && gridFromJ <= 5 && gridFromJ > 0) {
                        if (isPieceInPlace(name, gridFromI - 1, gridFromJ - 1)) {
                            if (board.getBoard()[gridFromI - 1][gridFromJ - 1].isMoveValid(gridFromI - 1, gridFromJ - 1, gridToI - 1, gridToJ - 1)) {
                                if (board.getBoard()[gridToI - 1][gridToJ - 1] == null) {
                                    board.updateBoard(gridToI - 1, gridToJ - 1, board.getBoard()[gridFromI - 1][gridFromJ - 1]);
                                    board.updateBoard(gridFromI - 1, gridFromJ - 1, null);
                                    Board.getInstance().setTurn(Board.getInstance().getTurn() == Side.BLACK ? Side.WHITE : Side.BLACK);
                                    update(gridToI - 1, gridToJ - 1);
                                } else if (board.getBoard()[gridToI - 1][gridToJ - 1].getSide() != Board.getInstance().getTurn()) {
                                    if (board.getBoard()[gridToI - 1][gridToJ - 1] instanceof King) {
                                        board.hit(gridFromI - 1, gridFromJ - 1, gridToI - 1, gridToJ - 1);
                                        Board.printBoard();
                                        System.out.println(board.getTurn() == Side.BLACK? "black wins!" : "white wins!");
                                        break;
                                    } else {
                                        board.hit(gridFromI - 1, gridFromJ - 1, gridToI - 1, gridToJ - 1);
                                        Board.getInstance().setTurn(Board.getInstance().getTurn() == Side.BLACK ? Side.WHITE : Side.BLACK);
                                        update(gridToI - 1, gridToJ - 1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            Board.printBoard();
        }
    }
}