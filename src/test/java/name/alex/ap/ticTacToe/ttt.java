/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package name.alex.ap.ticTacToe;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ttt {
    private Game game = null;
    
    private static final CellCoords anyCell = new CellCoords(0, 0);
    private static final CellCoords anotherCell = new CellCoords(1, 0);
    
    @BeforeMethod
    public void beforeEachTest() {
        game = new Game();
    }
    
    @Test
    public void A_new_game_should_not_be_over() {
        assertThat(game.isOver(), is(false));
    }

    @Test
    public void When_player_one_makes_a_move_we_can_retrieve_it_from_the_grid(){
        assertThat(game.getCell(anyCell), is(CellState.Empty));
        game.playerOneMove(anyCell);
        assertThat(game.getCell(anyCell), is(CellState.PlayerOne));
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void Player_two_cannot_make_a_move_before_player_one() {
        game.playerTwoMove(anyCell);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void Player_one_cannot_make_two_moves_in_a_row_in_a_new_game(){
        game.playerOneMove(anyCell);
        game.playerOneMove(anotherCell);
    }
    
    @Test
    public void Player_two_can_make_a_move_after_player_one_and_we_can_retrieve_both_moves_from_the_grid() {
        game.playerOneMove(anyCell);
        game.playerTwoMove(anotherCell);
        
        assertThat(game.getCell(anyCell), is(CellState.PlayerOne));
        assertThat(game.getCell(anotherCell), is(CellState.PlayerTwo));
    }

    /* TODO:
        Can't fill in a square that is already filled.
        simple game
        bounds checks on CellCoords.{x, y}.
    
    
     */
    
}
