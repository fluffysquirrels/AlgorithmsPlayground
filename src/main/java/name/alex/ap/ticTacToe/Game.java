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
        // TODO: player two wins, draws
    }

    private boolean playerOneHasWon() {
        return playerHasWon(CellState.PlayerOne);
    }

    private boolean playerTwoHasWon() {
        return playerHasWon(CellState.PlayerTwo);
    }

    private boolean playerHasWon(final CellState player) {
        return playerHasWonWithHorizontalRow(player);
        // TODO: Vertical columns, diagonals
    }

    private boolean playerHasWonWithHorizontalRow(CellState player) {
        for(int y = 0; y < Grid.HEIGHT; ++y){
            for(int x = 0; x < Grid.WIDTH; ++x) {
                if(grid.get(new CellCoords(x, y)) != player) {
                    // Player hasn't won with this row, break out of the for(x) loop,
                    // and try the next row with the for(y) loop.
                    break;
                }
                
                if(x == Grid.WIDTH - 1) {
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
        
        grid.set(coords, CellState.PlayerOne);
        ++numMoves;
    }

    public void playerTwoMove(CellCoords coords) {
        if(numMoves % 2 == 0) {
            throw new IllegalStateException("It is not player two's turn to move.");
        }
        
        grid.set(coords, CellState.PlayerTwo);
        ++numMoves;
    }

    public CellState getCell(CellCoords coords) {
        return grid.get(coords);
    }
}
