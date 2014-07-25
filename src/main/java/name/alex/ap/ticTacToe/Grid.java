/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package name.alex.ap.ticTacToe;

import java.util.Arrays;

class Grid {
    private final CellValue[] cells;

    public static final int WIDTH = 3;
    public static final int HEIGHT = 3;

    public Grid() {
        this.cells = createCells();
    }

    private static CellValue[] createCells() {
        final CellValue[] cells = new CellValue[WIDTH * HEIGHT];
        Arrays.fill(cells, CellValue.Empty);
        return cells;
    }

    public void set(CellCoords coords, CellValue value) {
        if(get(coords) != CellValue.Empty) {
            throw new IllegalStateException("Cannot set a cell that is already filled.");
        }

        cells[getCellIndexFromCoords(coords)] = value;
    }

    public CellValue get(CellCoords coords) {
        return cells[getCellIndexFromCoords(coords)];
    }

    private static int getCellIndexFromCoords(CellCoords coords) {
        return WIDTH * coords.getY() + coords.getX();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        for(int y = 0; y < Grid.HEIGHT; ++y) {
            for(int x = 0; x < Grid.WIDTH; ++x) {
                final CellValue cell = get(new CellCoords(x, y));
                sb.append(cell.getCharacterInGridString());
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}
