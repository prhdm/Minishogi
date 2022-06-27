package logic;

import gui.GameFrame;

import javax.swing.*;

public abstract class Pieces {
    protected char name;
    protected ImageIcon pieceImage;
    public Side getSide() {
        return side;
    }
    protected Side side;

    public static boolean isValid(int i,int j) {
        if (Board.getInstance().getBoard()[i][j]==null) {
            GameFrame.getInstance().updatePieces(i, j, new ImageIcon("src/resources/Selected.png"));
            return false;
        } else if (Board.getInstance().getBoard()[i][j].getSide() != Board.getInstance().getTurn()) {
            GameFrame.getInstance().updatePieces(i,j,new ImageIcon("src/resources/1.png"));
            return true;
        }
        return true;
    }

    public char getName() {
        return name;
    }

    public ImageIcon getPieceImage() {
        return pieceImage;
    }

    public abstract boolean isMoveValid(int gridOfI, int gridOfJ, int gridToI, int gridToJ);
    public abstract Pieces changeSide();
    public abstract Pieces update();
    public abstract void showMoves(int gridx,int gridy);
}
