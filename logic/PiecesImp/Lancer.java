package logic.PiecesImp;

import gui.resourceManager;
import logic.Board;
import logic.Pieces;
import logic.Side;


public class Lancer extends Pieces {
    public Lancer(Side side) {
        this.side=side;
        name = side == Side.BLACK ? 'l' : 'L';
        pieceImage = side == Side.BLACK ? resourceManager.sithLancer : resourceManager.jediLancer;
    }


    @Override
    public boolean isMoveValid(int gridOfI, int gridOfJ, int gridToI, int gridToJ) {
        switch (side) {
            case BLACK:
                while (gridOfI>gridToI) {
                    gridOfI--;
                    if (gridOfI == gridToI && gridOfJ == gridToJ)
                        return true;
                    else if (Board.getInstance().getBoard()[gridOfI][gridOfJ] != null)
                        return false;
                }
                break;
            case WHITE:
                while (gridOfI<gridToI) {
                    gridOfI++;
                    if (gridOfI == gridToI && gridOfJ == gridToJ)
                        return true;
                    else if (Board.getInstance().getBoard()[gridOfI][gridOfJ] != null)
                        return false;
                }
                break;
        }
        return false;
    }

    @Override
    public Pieces changeSide() {
        return new Lancer(this.side==Side.BLACK?Side.WHITE:Side.BLACK);
    }

    @Override
    public Pieces update() {
        return new UpdatedLancer(this.side);
    }

    @Override
    public void showMoves(int i, int j) {
        switch (side) {
            case BLACK:
                showBlackMoves(i,j);
                break;
            case WHITE:
                showWhiteMoves(i,j);
                break;
        }
    }

    private void showBlackMoves(int i, int j){
        while (i != 0) {
            if (Pieces.isValid(i-1,j)) break;
            i--;
        }
    }

    private void showWhiteMoves(int i ,int j){
        while (i != 4) {
            if (Pieces.isValid(i+1,j)) break;
            i++;
        }
    }
}
