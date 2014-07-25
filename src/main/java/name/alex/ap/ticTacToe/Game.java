/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package name.alex.ap.ticTacToe;

public class Game {
    private final Grid grid = new Grid();
    private int numMoves = 0;

    public boolean isOver(){
        return    playerOneHasWon()
               || playerTwoHasWon();
        // TODO: draws
    }

    private boolean playerOneHasWon() {
        return playerHasWon(CellState.PlayerOne);
    }

    private boolean playerTwoHasWon() {
        return playerHasWon(CellState.PlayerTwo);
    }

    private boolean playerHasWon(final CellState player) {
        return    playerHasWonWithHorizontalRow(player)
               || playerHasWonWithVerticalColumn(player);
        // TODO: Diagonals
    }

    private boolean playerHasWonWithHorizontalRow(CellState player) {
        for(int y = 0; y < Grid.HEIGHT; ++y){
            for(int x = 0; x < Grid.WIDTH; ++x) {
                if(getCell(new CellCoords(x, y)) != player) {
                    // Player hasn't won with this row, break out of the for(x) loop,
                    // and try the next row with the for(y) loop.
                    break;
                }
                
                if(x == Grid.WIDTH - 1) {
                    // This player has the final cell in this row, so has won.
                    return true;
                }
            }
        }

        return false;
    }

    private boolean playerHasWonWithVerticalColumn(CellState player) {
        for(int x = 0; x < Grid.WIDTH; ++x){
            for(int y = 0; y < Grid.HEIGHT; ++y) {
                if(getCell(new CellCoords(x, y)) != player) {
                    // Player hasn't won with this column, break out of the for(y) loop,
                    // and try the next row with the for(x) loop.
                    break;
                }

                if(y == Grid.HEIGHT - 1) {
                    // This player has the final cell in this column, so has won.
                    return true;
                }
            }
        }

        return false;
    }

    public void playerOneMove(CellCoords coords) {
        if(numMoves % 2 == 1) {
            throw new IllegalStateException("It is not player one's turn to move.");
        }

        assertGameNotOver();

        grid.set(coords, CellState.PlayerOne);
        ++numMoves;
    }

    public void playerTwoMove(CellCoords coords) {
        if(numMoves % 2 == 0) {
            throw new IllegalStateException("It is not player two's turn to move.");
        }

        assertGameNotOver();

        grid.set(coords, CellState.PlayerTwo);
        ++numMoves;
    }

    private void assertGameNotOver() throws IllegalStateException {
        if(isOver()) {
            throw new IllegalStateException("Cannot move when game is over.");
        }
    }

    public CellState getCell(CellCoords coords) {
        return grid.get(coords);
    }

}
