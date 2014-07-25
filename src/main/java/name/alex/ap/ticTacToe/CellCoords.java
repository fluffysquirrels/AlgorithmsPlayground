/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package name.alex.ap.ticTacToe;

public class CellCoords {
    private final int x;
    private final int y;

    public CellCoords(int x, int y) {
        if(x < 0) {
            throw new IllegalArgumentException("x cannot be < 0");
        }
        if(x >= Grid.WIDTH) {
            throw new IllegalArgumentException("x cannot be >= Grid.WIDTH");
        }
        if(y < 0) {
            throw new IllegalArgumentException("y cannot be < 0");
        }
        if(y >= Grid.HEIGHT) {
            throw new IllegalArgumentException("y cannot be >= Grid.HEIGHT");
        }
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
