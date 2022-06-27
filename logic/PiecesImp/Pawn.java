package logic.PiecesImp;

import gui.resourceManager;
import logic.Pieces;
import logic.Side;


public class Pawn extends Pieces {
    public Pawn(Side side) {
        this.side=side;
        name = side == Side.BLACK ? 'p' : 'P';
        pieceImage = side == Side.BLACK ? resourceManager.sithPawn : resourceManager.jediPawn;
    }


    @Override
    public boolean isMoveValid(int gridOfI, int gridOfJ, int gridToI, int gridToJ) {
        switch (side) {
            case BLACK :
                if (gridOfI != 0) {
                    return gridOfI-1==gridToI && gridOfJ==gridToJ;
                }
            case WHITE:
                if (gridOfI != 4) {
                    return gridOfI+1==gridToI && gridOfJ==gridToJ;
                }
        }
        return false;
    }

    @Override
    public Pieces changeSide() {
        return new Pawn(this.side==Side.BLACK?Side.WHITE:Side.BLACK);
    }

    @Override
    public Pieces update() {
        return new UpdatedPawn(this.side);
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
        if (i != 0) {
            isValid(i-1,j);
        }
    }

    private void showWhiteMoves(int i ,int j){
        if (i != 4) {
            isValid(i+1,j);
        }
    }
}
