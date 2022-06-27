package gui.eventHandler;

import gui.GameFrame;
import logic.Side;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class backgroundMouseEventHandler implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        if (GameFrame.getInstance().getTurn().getText().equals("Jedi") || GameFrame.getInstance().getTurn().getText().equals("Sith"))
            GameFrame.getInstance().setPieces();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
