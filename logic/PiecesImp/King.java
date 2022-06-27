package logic.PiecesImp;

import gui.resourceManager;
import logic.Pieces;
import logic.Side;


public class King extends Pieces {
    public King(Side side) {
        this.side=side;
        name = side == Side.BLACK ? 'k' : 'K';
        pieceImage = side == Side.BLACK ? resourceManager.sithKing : resourceManager.jediKing;
    }


    @Override
    public boolean isMoveValid(int gridOfI, int gridOfJ, int gridToI, int gridToJ) {
        if (gridOfI!=4 && gridOfJ != 4 && gridOfI+1 == gridToI && gridOfJ+1 == gridToJ ) {
            return true;
        } else if (gridOfI!=4 && gridOfI+1 == gridToI && gridOfJ == gridToJ) {
            return true;
        } else if (gridOfI!= 4 && gridOfJ != 0 && gridOfI+1 == gridToI && gridOfJ-1 == gridToJ) {
            return true;
        } else if (gridOfJ != 4 && gridOfI == gridToI && gridOfJ+1 == gridToJ) {
            return true;
        } else if (gridOfJ != 0 && gridOfI == gridToI && gridOfJ-1 == gridToJ) {
            return true;
        } else if (gridOfI!=0 && gridOfJ != 4 && gridOfI-1 == gridToI && gridOfJ+1 == gridToJ) {
            return true;
        } else if (gridOfI!=0 && gridOfI-1 == gridToI && gridOfJ == gridToJ) {
            return true;
        } else if (gridOfI!=0 && gridOfJ != 0 && gridOfI-1 == gridToI && gridOfJ-1 == gridToJ) {
            return true;
        }
        return false;
    }

    @Override
    public Pieces changeSide() {
        return new King(this.side==Side.BLACK?Side.WHITE:Side.BLACK);
    }

    @Override
    public Pieces update() {
        return this;
    }

    @Override
    public void showMoves(int i,int j) {
        if (i!=4 && j != 4 ) {
            isValid(i+1,j+1);
        }
        if (i!=4) {
            isValid(i+1,j);
        }
        if (i!= 4 && j != 0) {
            isValid(i+1,j-1);
        }
        if (j != 4) {
            isValid(i,j+1);
        }
        if (j != 0) {
            isValid(i,j-1);
        }
        if (i!=0 && j != 4) {
            isValid(i-1,j+1);
        }
        if (i!=0) {
            isValid(i-1,j);
        }
        if (i!=0 && j != 0) {
            isValid(i-1,j-1);
        }
    }
}
