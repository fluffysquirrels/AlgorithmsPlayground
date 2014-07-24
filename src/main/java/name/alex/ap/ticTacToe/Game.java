/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package name.alex.ap.ticTacToe;

import java.util.Arrays;

public class Game {
    private final CellState[] cells;

    public Game() {
        this.cells = createCells();
    }

    private static CellState[] createCells() {
        final CellState[] name = new CellState[9];
        Arrays.fill(name, CellState.Empty);
        return name;
    }
    
    public boolean isOver(){
        return false;
    }

    void playerOneMove(int x, int y) {
        cells[3 * y + x] = CellState.PlayerOne;
    }

    CellState getCell(int x, int y) {
        return cells[3 * y + x];
    }
}
