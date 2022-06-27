package logic.PiecesImp;

import gui.resourceManager;
import logic.Board;
import logic.Pieces;
import logic.Side;

public class UpdatedBishop extends Pieces {
    public UpdatedBishop(Side side) {
        this.side = side;
        name = side == Side.BLACK ? 'b' : 'B';
        pieceImage = side == Side.BLACK ? resourceManager.sithUpdatedBishop : resourceManager.jediUpdatedBishop;
    }

    @Override
    public boolean isMoveValid(int gridOfI, int gridOfJ, int gridToI, int gridToJ) {
        int tempi = gridOfI;
        int tempj = gridOfJ;
        if (gridOfI != 4 && gridOfI + 1 == gridToI && gridOfJ == gridToJ) {
            return true;
        } else if (gridOfJ != 4 && gridOfI == gridToI && gridOfJ + 1 == gridToJ) {
            return true;
        } else if (gridOfJ != 0 && gridOfI == gridToI && gridOfJ - 1 == gridToJ) {
            return true;
        } else if (gridOfI != 0 && gridOfI - 1 == gridToI && gridOfJ == gridToJ) {
            return true;
        }
        while (gridOfI > gridToI && gridOfJ > gridToJ) {
            gridOfI--;
            gridOfJ--;
            if (gridOfI == gridToI && gridOfJ == gridToJ)
                return true;
            else if (Board.getInstance().getBoard()[gridOfI][gridOfJ] != null)
                break;
        }
        gridOfI = tempi;
        gridOfJ = tempj;
        while (gridOfI > gridToI && gridOfJ < gridToJ) {
            gridOfI--;
            gridOfJ++;
            if (gridOfI == gridToI && gridOfJ == gridToJ)
                return true;
            else if (Board.getInstance().getBoard()[gridOfI][gridOfJ] != null)
                break;
        }
        gridOfI = tempi;
        gridOfJ = tempj;
        while (gridOfI < gridToI && gridOfJ < gridToJ) {
            gridOfI++;
            gridOfJ++;
            if (gridOfI == gridToI && gridOfJ == gridToJ)
                return true;
            else if (Board.getInstance().getBoard()[gridOfI][gridOfJ] != null)
                break;
        }
        gridOfI = tempi;
        gridOfJ = tempj;
        while (gridOfI < gridToI && gridOfJ > gridToJ) {
            gridOfI++;
            gridOfJ--;
            if (gridOfI == gridToI && gridOfJ == gridToJ)
                return true;
            else if (Board.getInstance().getBoard()[gridOfI][gridOfJ] != null)
                break;
        }
        return false;
    }

    @Override
    public Pieces changeSide() {
        return new Bishop(this.side == Side.BLACK ? Side.WHITE : Side.BLACK);
    }

    @Override
    public Pieces update() {
        return this;
    }

    @Override
    public void showMoves(int gridx, int gridy) {
        int tempi = gridx;
        int tempj = gridy;
        if (gridx != 4) {
            isValid(gridx + 1, gridy);
        }
        if (gridy != 4) {
            isValid(gridx, gridy + 1);
        }
        if (gridy != 0) {
            isValid(gridx, gridy - 1);
        }
        if (gridx != 0) {
            isValid(gridx - 1, gridy);
        }
        while (gridx != 0 && gridy != 0) {
            if(isValid(gridx-1, gridy-1)) break;
            gridx--;
            gridy--;
        }
        gridx = tempi;
        gridy = tempj;
        while (gridx != 0 && gridy != 4) {
            if(isValid(gridx-1, gridy+1)) break;
            gridx--;
            gridy++;
        }
        gridx = tempi;
        gridy = tempj;
        while (gridx != 4 && gridy != 4) {
            if(isValid(gridx+1, gridy+1)) break;
            gridx++;
            gridy++;
        }
        gridx = tempi;
        gridy = tempj;
        while (gridx != 4 && gridy != 0 ) {
            if(isValid(gridx+1, gridy-1)) break;
            gridx++;
            gridy--;
        }
    }

}
