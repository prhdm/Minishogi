package logic.PiecesImp;

import gui.resourceManager;
import logic.Board;
import logic.Pieces;
import logic.Side;

public class UpdatedSilverGeneral extends Pieces {

    public UpdatedSilverGeneral(Side side) {
        this.side=side;
        name = side == Side.BLACK ? 's' : 'S';
        pieceImage = side == Side.BLACK ? resourceManager.sithUpdatedSilverGeneral : resourceManager.jediUpdatedSilverGeneral;
    }

    @Override
    public boolean isMoveValid(int gridOfI, int gridOfJ, int gridToI, int gridToJ) {
        int tempi = gridOfI;
        int tempj = gridOfJ;
        int counter=0;
        while (gridOfI>gridToI) {
            if (counter<2) {
                gridOfI--;
                if (gridOfI == gridToI && gridOfJ == gridToJ)
                    return true;
                else if (Board.getInstance().getBoard()[gridOfI][gridOfJ] != null)
                    break;
                counter++;
            } else break;
        }
        gridOfI = tempi;
        counter=0;
        while (gridOfI<gridToI) {
            if (counter<2) {
                gridOfI++;
                if (gridOfI == gridToI && gridOfJ == gridToJ)
                    return true;
                else if (Board.getInstance().getBoard()[gridOfI][gridOfJ] != null)
                    break;
                counter++;
            } else break;
        }
        gridOfI = tempi;
        counter=0;
        while (gridOfJ>gridToJ) {
            if (counter<2) {
                gridOfJ--;
                if (gridOfI == gridToI && gridOfJ == gridToJ)
                    return true;
                else if (Board.getInstance().getBoard()[gridOfI][gridOfJ] != null)
                    break;
                counter++;
            } else break;
        }
        gridOfJ = tempj;
        counter=0;
        while (gridOfJ<gridToJ) {
            if (counter<2) {
                gridOfJ++;
                if (gridOfI == gridToI && gridOfJ == gridToJ)
                    return true;
                else if (Board.getInstance().getBoard()[gridOfI][gridOfJ] != null)
                    break;
                counter++;
            } else break;
        }
        gridOfI = tempi;
        gridOfJ = tempj;
        counter=0;
        while (gridOfI > gridToI && gridOfJ > gridToJ) {
            if (counter<2) {
                gridOfI--;
                gridOfJ--;
                if (gridOfI == gridToI && gridOfJ == gridToJ)
                    return true;
                else if (Board.getInstance().getBoard()[gridOfI][gridOfJ] != null)
                    break;
                counter++;
            } else break;
        }
        gridOfI = tempi;
        gridOfJ = tempj;
        counter=0;
        while (gridOfI > gridToI && gridOfJ < gridToJ) {
            if (counter<2) {
                gridOfI--;
                gridOfJ++;
                if (gridOfI == gridToI && gridOfJ == gridToJ)
                    return true;
                else if (Board.getInstance().getBoard()[gridOfI][gridOfJ] != null)
                    break;
                counter++;
            } else break;
        }
        counter=0;
        gridOfI = tempi;
        gridOfJ = tempj;
        while (gridOfI < gridToI && gridOfJ < gridToJ) {
            if (counter<2) {
                gridOfI++;
                gridOfJ++;
                if (gridOfI == gridToI && gridOfJ == gridToJ)
                    return true;
                else if (Board.getInstance().getBoard()[gridOfI][gridOfJ] != null)
                    break;
                counter++;
            } else break;
        }
        counter=0;
        gridOfI = tempi;
        gridOfJ = tempj;
        while (gridOfI < gridToI && gridOfJ > gridToJ) {
            if (counter<2) {
                gridOfI++;
                gridOfJ--;
                if (gridOfI == gridToI && gridOfJ == gridToJ)
                    return true;
                else if (Board.getInstance().getBoard()[gridOfI][gridOfJ] != null)
                    break;
                counter++;
            } else break;
        }
        return false;
    }

    @Override
    public Pieces changeSide() {
        return new SilverGeneral(this.side== Side.BLACK?Side.WHITE:Side.BLACK);
    }

    @Override
    public Pieces update() {
        return this;
    }

    @Override
    public void showMoves(int gridx, int gridy) {
        int tempi = gridx;
        int tempj = gridy;
        int counter=0;
        while (gridx!= 0) {
            if (counter<2) {
                if(isValid(gridx-1,gridy)) break;
                gridx--;
                counter++;
            } else break;
        }
        gridx = tempi;
        counter=0;
        while (gridx!=4) {
            if (counter<2) {
                if(isValid(gridx+1,gridy)) break;
                gridx++;
                counter++;
            } else break;
        }
        gridx = tempi;
        counter=0;
        while (gridy!=0) {
            if (counter<2) {
                if(isValid(gridx,gridy-1)) break;
                gridy--;
                counter++;
            } else break;
        }
        gridy = tempj;
        counter=0;
        while (gridy!=4) {
            if (counter<2) {
                if(isValid(gridx,gridy+1)) break;
                gridy++;
                counter++;
            } else break;
        }
        gridx = tempi;
        gridy = tempj;
        counter=0;
        while (gridx != 0 && gridy != 0) {
            if (counter<2) {
                if(isValid(gridx-1,gridy-1)) break;
                gridx--;
                gridy--;
                counter++;
            } else break;
        }
        gridx = tempi;
        gridy = tempj;
        counter=0;
        while (gridx != 0 && gridy != 4) {
            if (counter<2) {
                if(isValid(gridx-1,gridy+1)) break;
                gridx--;
                gridy++;
                counter++;
            } else break;
        }
        counter=0;
        gridx = tempi;
        gridy = tempj;
        while (gridx != 4 && gridy != 4) {
            if (counter<2) {
                if(isValid(gridx+1,gridy+1)) break;
                gridx++;
                gridy++;
                counter++;
            } else break;
        }
        counter=0;
        gridx = tempi;
        gridy = tempj;
        while (gridx != 4 && gridy != 0) {
            if (counter<2) {
                if(isValid(gridx+1,gridy-1)) break;
                gridx++;
                gridy--;
                counter++;
            } else break;
        }
    }
}
