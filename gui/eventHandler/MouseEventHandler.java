package gui.eventHandler;

import gui.GameFrame;
import gui.SoundEffects;
import logic.Board;
import logic.Pieces;


import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class MouseEventHandler extends MouseAdapter {
    public static Pieces selectedPiece;
    public static int lasti,lastj;
    @Override
    public void mouseClicked(MouseEvent e) {
        GameFrame gameFrame = GameFrame.getInstance();
        Board board = Board.getInstance();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (e.getSource() == gameFrame.getPieces()[i][j]) {
                    if (board.getBoard()[i][j] == null) {
                        SoundEffects.moveSound();
                        gameFrame.move(lasti, lastj, i, j);
                    } else if (board.getBoard()[i][j].getSide() != board.getTurn()) {
                        gameFrame.hit(lasti,lastj,i,j);
                    } else{
                        SoundEffects.clickSound();
                        gameFrame.setPieces();
                        selectedPiece = board.getBoard()[i][j];
                        GameFrame.getInstance().updatePieces(i,j,new ImageIcon("src/resources/SelectedPiece.png"));
                        board.getBoard()[i][j].showMoves(i, j);
                        lasti = i;
                        lastj = j;
                        gameFrame.update();
                    }
                }
            }
        }
    }
}
