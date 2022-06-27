package logic.PiecesImp;

import gui.resourceManager;
import logic.Board;
import logic.Pieces;
import logic.Side;

public class UpdatedLancer extends Pieces {
    public UpdatedLancer(Side side) {
        this.side=side;
        name = side == Side.BLACK ? 'l' : 'L';
        pieceImage = side == Side.BLACK ? resourceManager.sithUpdatedLancer : resourceManager.jediUpdatedLancer;
    }

    @Override
    public boolean isMoveValid(int gridOfI, int gridOfJ, int gridToI, int gridToJ) {
        int tempi = gridOfI;
        int tempj = gridOfJ;
        while (gridOfI>gridToI) {
            gridOfI--;
            if (gridOfI == gridToI && gridOfJ == gridToJ)
                return true;
            else if (Board.getInstance().getBoard()[gridOfI][gridOfJ] != null)
                break;
        }
        gridOfI = tempi;
        while (gridOfI<gridToI) {
            gridOfI++;
            if (gridOfI == gridToI && gridOfJ == gridToJ)
                return true;
            else if (Board.getInstance().getBoard()[gridOfI][gridOfJ] != null)
                break;
        }
        gridOfI = tempi;
        while (gridOfJ>gridToJ) {
            gridOfJ--;
            if (gridOfI == gridToI && gridOfJ == gridToJ)
                return true;
            else if (Board.getInstance().getBoard()[gridOfI][gridOfJ] != null)
                break;
        }
        gridOfJ = tempj;
        while (gridOfJ<gridToJ) {
            gridOfJ++;
            if (gridOfI == gridToI && gridOfJ == gridToJ)
                return true;
            else if (Board.getInstance().getBoard()[gridOfI][gridOfJ] != null)
                break;
        }
        return false;
    }

    @Override
    public Pieces changeSide() {
        return new Lancer(this.side== Side.BLACK?Side.WHITE:Side.BLACK);
    }

    @Override
    public Pieces update() {
        return this;
    }

    @Override
    public void showMoves(int gridx, int gridy) {
        int tempi = gridx;
        int tempj = gridy;
        while (gridx!= 0) {
            if(isValid(gridx-1,gridy))break;
            gridx--;
        }
        gridx = tempi;
        while (gridx!=4) {
            if(isValid(gridx+1,gridy))break;
            gridx++;
        }
        gridx = tempi;
        while (gridy!=0) {
            if(isValid(gridx,gridy-1))break;
            gridy--;
        }
        gridy = tempj;
        while (gridy!=4) {
            if(isValid(gridx,gridy+1))break;
            gridy++;
        }
    }
}
