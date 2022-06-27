package gui;

import gui.eventHandler.*;
import logic.Board;
import logic.Pieces;
import logic.PiecesImp.King;
import logic.Side;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameFrame extends JFrame {
    private final Container pane;
    private JLabel background;
    private JPanel piecesBoard;
    private JPanel availableMoves;
    private JPanel capturedByWhite;
    private JPanel capturedByBlack;
    private JLabel[][] moves = new JLabel[5][5];
    public final List<JLabel> piecesCapturedByBlack = new ArrayList<>();
    public final List<JLabel> piecesCapturedByWhite = new ArrayList<>();

    public void showAvailableCapturedMoves() {
        if (availableMoves != null)
            availableMoves.removeAll();
        availableMoves = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        for (int i = 4; i >= 0 ; i--) {
            for (int j = 0; j < 5 ; j++) {
                c.gridx=j;
                c.gridy=4-i;
                if (Board.getInstance().getBoard()[i][j]==null) {
                    moves[i][j] = new JLabel(resourceManager.selected);
                    pieces[i][j].addMouseListener(new CapturedPiecesEventHandler());
                } else {
                    moves[i][j] = new JLabel(resourceManager.transparent);
                }
                availableMoves.add(moves[i][j],c);
            }
        }
        availableMoves.setOpaque(false);

        background.add(availableMoves);
        availableMoves.setBounds(GAMEBOARD_WIDTH,GAMEBOARD_HEIGHT,600,600);
        refreshFrame();
    }
    public JLabel getTurn() {
        return turn;
    }

    private JLabel turn;
    public static final int GAMEFRAME_WIDTH = 820;
    public static final int GAMEFRAME_HEIGHT = 645;
    public static final int GAMEBOARD_WIDTH = 100;
    public static final int GAMEBOARD_HEIGHT = 60;
    public JButton startButton;

    public JLabel[][] getPieces() {
        return pieces;
    }

    public JLabel[][] pieces = new JLabel[5][5];

    private static GameFrame gameFrameObj;
    public static GameFrame getInstance() {
        if (gameFrameObj == null)
            gameFrameObj = new GameFrame();
        return gameFrameObj;
    }
    public void restartGame() {
        Board.getInstance().restart();
        if (piecesBoard != null)
            piecesBoard.removeAll();
        if (availableMoves != null)
            availableMoves.removeAll();
        if (capturedByWhite != null)
            capturedByWhite.removeAll();
        if (capturedByBlack != null)
            capturedByBlack.removeAll();
        GameFrame.getInstance().getTurn().setText("");
        startButton.setText("Start");
        refreshFrame();
    }
    private GameFrame() {
        setTitle("Shogi Wars");
        setVisible(true);
        setResizable(false);
        setSize(new Dimension(GAMEFRAME_WIDTH,GAMEFRAME_HEIGHT));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pane = new JPanel();
        setContentPane(pane);
        setBackground();
        startButton = new JButton("Start");
        startButton.setBounds(550,65,80,50);
        startButton.addActionListener(new ButtonEventHandler());
        background.add(startButton);
        refreshFrame();
    }
    public void start() {
        setPieces();
        background.addMouseListener(new backgroundMouseEventHandler());
        turn = new JLabel(Board.getInstance().getTurn() == Side.BLACK ? "Sith" : "Jedi");
        turn.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,30));
        turn.setBounds(280,45,300,100);
        background.add(turn);
        refreshFrame();
    }

    private void setBackground(){
        background = new JLabel(resourceManager.mainBackground);
        pane.add(background);
        refreshFrame();
    }
    public void update(){
        if (availableMoves != null)
            availableMoves.removeAll();
        availableMoves = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        for (int i = 4; i >= 0 ; i--) {
            for (int j = 0; j < 5 ; j++) {
                c.gridx=j;
                c.gridy=4-i;
                if (moves[i][j]==null) {
                    moves[i][j] = new JLabel(resourceManager.transparent);
                }
                availableMoves.add(moves[i][j],c);
            }
        }
        availableMoves.setOpaque(false);

        background.add(availableMoves);
        availableMoves.setBounds(GAMEBOARD_WIDTH,GAMEBOARD_HEIGHT,600,600);
        refreshFrame();
    }
    public void move(int lastI,int lastJ,int i,int j) {
        if (lastJ == -1 ) {
            switch (Board.getInstance().getTurn()) {
                case BLACK:
                    Pieces temp = Board.getInstance().getCapturedByBlack().get(lastI);
                    Board.getInstance().getCapturedByBlack().remove(lastI);
                    Board.getInstance().updateBoard(i, j, temp);
                    Board.getInstance().setTurn(Board.getInstance().getTurn() == Side.BLACK ? Side.WHITE : Side.BLACK);
                    turn.setText(Board.getInstance().getTurn() == Side.BLACK ? "Sith" : "Jedi");
                    this.setPieces();
                    break;
                case WHITE:
                    temp = Board.getInstance().getCapturedByWhite().get(lastI);
                    Board.getInstance().getCapturedByWhite().remove(lastI);
                    Board.getInstance().updateBoard(i, j, temp);
                    Board.getInstance().setTurn(Board.getInstance().getTurn() == Side.BLACK ? Side.WHITE : Side.BLACK);
                    turn.setText(Board.getInstance().getTurn() == Side.BLACK ? "Sith" : "Jedi");
                    this.setPieces();
                    break;
            }
        } else {
            Pieces temp = Board.getInstance().getBoard()[lastI][lastJ];
            Board.getInstance().updateBoard(lastI, lastJ, null);
            Board.getInstance().updateBoard(i, j, temp);
            Board.update(i, j);
            Board.getInstance().setTurn(Board.getInstance().getTurn() == Side.BLACK ? Side.WHITE : Side.BLACK);
            turn.setText(Board.getInstance().getTurn() == Side.BLACK ? "Sith" : "Jedi");
            this.setPieces();
        }
    }

    public void endState() {
        if (piecesBoard != null)
            piecesBoard.removeAll();
        if (availableMoves != null)
            availableMoves.removeAll();
        if (capturedByWhite != null)
            capturedByWhite.removeAll();
        if (capturedByBlack != null)
            capturedByBlack.removeAll();
        for (int i = 0; i < 5 ; i++) {
            for (int j = 0; j < 5 ; j++) {
                moves[i][j]=null;
            }
        }
        piecesBoard = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        for (int i = 4; i >= 0 ; i--) {
            for (int j = 0; j < 5 ; j++) {
                c.gridx=j;
                c.gridy=4-i;
                if (Board.getInstance().getBoard()[i][j]==null) {
                    pieces[i][j] = new JLabel(resourceManager.transparent);
                    piecesBoard.add(pieces[i][j],c);
                }
                else {
                    pieces[i][j] = new JLabel(Board.getInstance().getBoard()[i][j].getPieceImage());
                    piecesBoard.add(pieces[i][j],c);
                }
            }
        }
        capturedByWhite = new JPanel(new GridBagLayout());
        for (int i = 0; i < Board.getInstance().getCapturedByWhite().size() ; i++) {
            c.gridx=0;
            c.gridy=8-i;
            JLabel label = new JLabel(Board.getInstance().getCapturedByWhite().get(i).getPieceImage());
            piecesCapturedByWhite.add(label);
            capturedByWhite.add(label,c);
        }
        piecesBoard.setOpaque(false);
        capturedByWhite.setBounds(5,0,96,600);
        capturedByWhite.setOpaque(false);
        background.add(capturedByWhite);

        capturedByBlack = new JPanel(new GridBagLayout());
        for (int i = 0; i < Board.getInstance().getCapturedByBlack().size() ; i++) {
            c.gridx=0;
            c.gridy=8-i;
            JLabel label = new JLabel(Board.getInstance().getCapturedByBlack().get(i).getPieceImage());
            piecesCapturedByBlack.add(label);
            capturedByBlack.add(label,c);
        }
        capturedByBlack.setBounds(700,0,96,600);
        capturedByBlack.setOpaque(false);
        background.add(capturedByBlack);

        background.add(piecesBoard);
        piecesBoard.setBounds(GAMEBOARD_WIDTH,GAMEBOARD_HEIGHT,600,600);
        refreshFrame();
    }

    public void hit(int hitterI,int hitterJ,int i,int j) {
        if (Board.getInstance().getBoard()[i][j] instanceof King) {
            turn.setText(turn.getText() + " wins!");
            Board.getInstance().hit(hitterI,hitterJ,i,j);
            Board.update(i,j);
            if (Board.getInstance().getTurn() == Side.BLACK) {
                piecesCapturedByBlack.add(pieces[i][j]);
            } else {
                piecesCapturedByWhite.add(pieces[i][j]);
            }
            endState();
        } else {
            Board.getInstance().hit(hitterI,hitterJ,i,j);
            Board.update(i,j);
            if (Board.getInstance().getTurn() == Side.BLACK) {
                piecesCapturedByBlack.add(pieces[i][j]);
            } else {
                piecesCapturedByWhite.add(pieces[i][j]);
            }
            Board.getInstance().setTurn( Board.getInstance().getTurn() == Side.BLACK ? Side.WHITE :Side.BLACK);
            turn.setText(Board.getInstance().getTurn() == Side.BLACK ? "Sith" : "Jedi");
            this.setPieces();
        }

    }

    public void setPieces(){
        if (piecesBoard != null)
            piecesBoard.removeAll();
        if (availableMoves != null)
            availableMoves.removeAll();
        if (capturedByWhite != null) {
            capturedByWhite.removeAll();
            piecesCapturedByWhite.clear();
        }
        if (capturedByBlack != null) {
            capturedByBlack.removeAll();
            piecesCapturedByBlack.clear();
        }
        for (int i = 0; i < 5 ; i++) {
            for (int j = 0; j < 5 ; j++) {
                moves[i][j]=null;
            }
        }
        piecesBoard = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        for (int i = 4; i >= 0 ; i--) {
            for (int j = 0; j < 5 ; j++) {
                c.gridx=j;
                c.gridy=4-i;
                if (Board.getInstance().getBoard()[i][j]==null) {
                    pieces[i][j] = new JLabel(resourceManager.transparent);
                    piecesBoard.add(pieces[i][j],c);
                }
                else {
                    pieces[i][j] = new JLabel(Board.getInstance().getBoard()[i][j].getPieceImage());
                    if (Board.getInstance().getBoard()[i][j].getSide() == Board.getInstance().getTurn()) {
                        pieces[i][j].addMouseListener(new MouseEventHandler());
                    }
                    piecesBoard.add(pieces[i][j],c);
                }
            }
        }
        capturedByWhite = new JPanel(new GridBagLayout());
        for (int i = 0; i < Board.getInstance().getCapturedByWhite().size() ; i++) {
            c.gridx=0;
            c.gridy=8-i;
            JLabel label = new JLabel(Board.getInstance().getCapturedByWhite().get(i).getPieceImage());
            piecesCapturedByWhite.add(label);
            if (Board.getInstance().getTurn() == Side.WHITE) {
                label.addMouseListener(new CapturedPiecesEventHandler());
            }
            capturedByWhite.add(label,c);
        }
        piecesBoard.setOpaque(false);
        capturedByWhite.setBounds(5,0,96,600);
        capturedByWhite.setOpaque(false);
        background.add(capturedByWhite);

        capturedByBlack = new JPanel(new GridBagLayout());
        for (int i = 0; i < Board.getInstance().getCapturedByBlack().size() ; i++) {
            c.gridx=0;
            c.gridy=8-i;
            JLabel label = new JLabel(Board.getInstance().getCapturedByBlack().get(i).getPieceImage());
            piecesCapturedByBlack.add(label);
            if (Board.getInstance().getTurn() == Side.BLACK) {
                label.addMouseListener(new CapturedPiecesEventHandler());
            }
            capturedByBlack.add(label,c);
        }
        capturedByBlack.setBounds(700,0,96,600);
        capturedByBlack.setOpaque(false);
        background.add(capturedByBlack);

        background.add(piecesBoard);
        piecesBoard.setBounds(GAMEBOARD_WIDTH,GAMEBOARD_HEIGHT,600,600);
        refreshFrame();
    }
    public void updatePieces(int i ,int j , ImageIcon imageIcon){
        moves[i][j] = new JLabel(imageIcon);
        pieces[i][j].addMouseListener(new MouseEventHandler());
    }
    public void refreshFrame(){
        revalidate();
        repaint();
    }
}