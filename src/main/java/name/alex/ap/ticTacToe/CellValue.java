/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the etmplate in the editor.
 */

package name.alex.ap.ticTacToe;

public enum CellValue {
    PlayerOne ("1"),
    PlayerTwo ("2"),
    Empty     ("-");

    private final String characterInGridString;

    CellValue(String characterInGridString) {
        this.characterInGridString = characterInGridString;
    }

    public String getCharacterInGridString() {
        return characterInGridString;
    }
}
