package logic.PiecesImp;

import gui.resourceManager;
import logic.Board;
import logic.Pieces;
import logic.Side;


public class Bishop extends Pieces {
    public Bishop(Side side) {
        this.side=side;
        name = side == Side.BLACK ? 'b' : 'B';
        pieceImage = side == Side.BLACK ? resourceManager.sithBishop : resourceManager.jediBishop;
    }

    @Override
    public boolean isMoveValid(int gridOfI, int gridOfJ, int gridToI, int gridToJ) {
        switch (side) {
            case BLACK:
                int tempi = gridOfI;
                int tempj = gridOfJ;
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
                return false;
            case WHITE:
                tempi = gridOfI;
                tempj = gridOfJ;
                while (gridOfI < gridToI && gridOfJ < gridToJ) {
                    gridOfI++;
                    gridOfJ++;
                    if (gridOfI == gridToI && gridOfJ == gridToJ)
                        return true;
                    else if (Board.getInstance().getBoard()[gridOfI][gridOfJ] != null)
                        return false;
                }
                gridOfI = tempi;
                gridOfJ = tempj;
                while (gridOfI < gridToI && gridOfJ > gridToJ) {
                    gridOfI++;
                    gridOfJ--;
                    if (gridOfI == gridToI && gridOfJ == gridToJ)
                        return true;
                    else if (Board.getInstance().getBoard()[gridOfI][gridOfJ] != null)
                        return false;
                }
                return false;
        }
        return false;
    }

    @Override
    public Pieces changeSide() {
        return new Bishop(this.side==Side.BLACK?Side.WHITE:Side.BLACK);
    }

    @Override
    public Pieces update() {
        return new UpdatedBishop(this.side);
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
        int tempi = i;
        int tempj = j;
        while (i != 0 && j!=0) {
            if (Pieces.isValid(i-1,j-1)) break;
            i--;
            j--;
        }
        i=tempi;
        j=tempj;
        while (i != 0 && j!=4) {
            if (Pieces.isValid(i-1,j+1)) break;
            i--;
            j++;
        }
    }

    private void showWhiteMoves(int i ,int j){
        int tempi = i;
        int tempj = j;
        while (i != 4 && j!=4) {
            if (Pieces.isValid(i+1,j+1)) break;
            i++;
            j++;
        }
        i=tempi;
        j=tempj;
        while (i != 4 && j!=0) {
            if (Pieces.isValid(i+1,j-1)) break;
            i++;
            j--;
        }
    }
}
