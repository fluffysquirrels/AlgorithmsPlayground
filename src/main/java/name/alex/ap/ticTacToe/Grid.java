/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package name.alex.ap.ticTacToe;

import java.util.Arrays;

class Grid {
    private final CellState[] cells;

    public Grid() {
        this.cells = createCells();
    }

    private static CellState[] createCells() {
        final CellState[] name = new CellState[9];
        Arrays.fill(name, CellState.Empty);
        return name;
    }

    public void set(CellCoords coords, CellState value) {
        cells[getCellIndexFromCoords(coords)] = value;
    }

    public CellState get(CellCoords coords) {
        return cells[getCellIndexFromCoords(coords)];
    }

    private static int getCellIndexFromCoords(CellCoords coords) {
        return 3 * coords.getY() + coords.getX();
    }
}
