package gui.eventHandler;

import gui.GameFrame;
import gui.SoundEffects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEventHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        GameFrame gameFrame = GameFrame.getInstance();
        if (gameFrame.startButton.getText().equals("Restart")) {
            GameFrame.getInstance().restartGame();
        } else {
            SoundEffects.startSound();
            gameFrame.start();
            gameFrame.startButton.setText("Restart");
        }
    }
}
