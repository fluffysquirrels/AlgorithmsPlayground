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
