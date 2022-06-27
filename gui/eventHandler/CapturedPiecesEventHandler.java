package gui.eventHandler;


import gui.GameFrame;
import gui.SoundEffects;
import logic.Board;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CapturedPiecesEventHandler extends MouseAdapter {
    public static int lasti;

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(lasti);
        GameFrame.getInstance().showAvailableCapturedMoves();
        switch (Board.getInstance().getTurn()){
            case BLACK:
                for (int i = 0; i < GameFrame.getInstance().piecesCapturedByBlack.size() ; i++) {
                    if (e.getSource() == GameFrame.getInstance().piecesCapturedByBlack.get(i)) {
                        lasti=i;
                    }
                }
                break;
            case WHITE:
                for (int i = 0; i < GameFrame.getInstance().piecesCapturedByWhite.size() ; i++) {
                    if (e.getSource() == GameFrame.getInstance().piecesCapturedByWhite.get(i)) {
                        lasti=i;
                    }
                }
                break;
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (e.getSource() == GameFrame.getInstance().getPieces()[i][j]) {
                    if (Board.getInstance().getBoard()[i][j] == null) {
                        SoundEffects.moveSound();
                        GameFrame.getInstance().move(lasti, -1, i, j);
                    }
                }
            }
        }
    }
}
