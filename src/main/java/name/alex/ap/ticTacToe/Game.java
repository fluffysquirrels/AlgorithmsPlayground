/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package name.alex.ap.ticTacToe;

import java.util.Arrays;

public class Game {
    private final Grid grid = new Grid();

    public boolean isOver(){
        return false;
    }

    public void playerOneMove(CellCoords coords) {
        grid.set(coords, CellState.PlayerOne);
    }

    public CellState getCell(CellCoords coords) {
        return grid.get(coords);
    }
}
