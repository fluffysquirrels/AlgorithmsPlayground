/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package name.alex.ap.ticTacToe;

import java.util.Arrays;

class Grid {
    private final CellState[] cells;

    private static final int WIDTH = 3;
    private static final int HEIGHT = 3;

    public Grid() {
        this.cells = createCells();
    }

    private static CellState[] createCells() {
        final CellState[] cells = new CellState[WIDTH * HEIGHT];
        Arrays.fill(cells, CellState.Empty);
        return cells;
    }

    public void set(CellCoords coords, CellState value) {
        cells[getCellIndexFromCoords(coords)] = value;
    }

    public CellState get(CellCoords coords) {
        return cells[getCellIndexFromCoords(coords)];
    }

    private static int getCellIndexFromCoords(CellCoords coords) {
        return WIDTH * coords.getY() + coords.getX();
    }
}
