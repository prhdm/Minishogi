package logic.PiecesImp;

import gui.resourceManager;
import logic.Pieces;
import logic.Side;


public class SilverGeneral extends Pieces {
    public SilverGeneral(Side side) {
        this.side=side;
        name = side == Side.BLACK ? 's' : 'S';
        pieceImage = side == Side.BLACK ? resourceManager.sithSilverGeneral : resourceManager.jediSilverGeneral;
    }


    @Override
    public boolean isMoveValid(int gridOfI, int gridOfJ, int gridToI, int gridToJ) {
        switch (side) {
            case BLACK:
                if (gridOfI !=4 && gridOfJ != 4 && gridOfI+1 == gridToI && gridOfJ+1 == gridToJ) {
                    return true;
                } else if (gridOfI!= 4 && gridOfJ != 0 && gridOfI+1 == gridToI && gridOfJ-1 == gridToJ) {
                    return true;
                } else if (gridOfI!=0 && gridOfJ != 4 && gridOfI-1 == gridToI && gridOfJ+1 == gridToJ) {
                    return true;
                } else if (gridOfI!=0 && gridOfI-1 == gridToI && gridOfJ == gridToJ) {
                    return true;
                } else if (gridOfI!=0 && gridOfJ != 0 && gridOfI-1 == gridToI && gridOfJ-1 == gridToJ) {
                    return true;
                }
                return false;
            case WHITE:
                if (gridOfI!=4 && gridOfJ != 4 && gridOfI+1 == gridToI && gridOfJ+1 == gridToJ) {
                    return true;
                } else if (gridOfI!=4 && gridOfI+1 == gridToI && gridOfJ == gridToJ) {
                    return true;
                } else if (gridOfI!= 4 && gridOfJ != 0 && gridOfI+1 == gridToI && gridOfJ-1 == gridToJ) {
                    return true;
                } else if (gridOfI!=0 && gridOfJ != 4 && gridOfI-1 == gridToI && gridOfJ+1 == gridToJ) {
                    return true;
                } else if (gridOfI!=0 && gridOfJ != 0 && gridOfI-1 == gridToI && gridOfJ-1 == gridToJ) {
                    return true;
                }
                return false;
        }
        return false;
    }

    @Override
    public Pieces changeSide() {
        return new SilverGeneral(this.side==Side.BLACK?Side.WHITE:Side.BLACK);
    }

    @Override
    public Pieces update() {
        return new UpdatedSilverGeneral(this.side);
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
        if (i!=4 && j != 4) {
            isValid(i+1,j+1);
        }
        if (i!= 4 && j != 0) {
            isValid(i+1,j-1);
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

    private void showWhiteMoves(int i ,int j){
        if (i!=4 && j != 4) {
            isValid(i+1,j+1);
        }
        if (i!=4) {
            isValid(i+1,j);
        }
        if (i!= 4 && j != 0) {
            isValid(i+1,j-1);
        }
        if (i!=0 && j != 4) {
            isValid(i-1,j+1);
        }
        if (i!=0 && j != 0) {
            isValid(i-1,j-1);
        }
    }
}
