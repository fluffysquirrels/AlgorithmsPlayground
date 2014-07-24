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

    @Test(expectedExceptions = IllegalStateException.class)
    public void Player_cannot_make_a_move_in_a_filled_cell() {
        game.playerOneMove(anyCell);
        game.playerTwoMove(anyCell);
    }

    @Test
    public void When_player_one_fills_a_horizontal_row_then_game_ends() {
        // Test all three possible horizontal rows in case there is e.g. an off by
        // one error somewhere.
        test_player_one_horizontal_row_example(0, 1);
        test_player_one_horizontal_row_example(1, 2);
        test_player_one_horizontal_row_example(2, 0);
    }

    private static void test_player_one_horizontal_row_example(int yPlayerOne, int yPlayerTwo) {
        final Game game = new Game();

        game.playerOneMove(new CellCoords(0,yPlayerOne));
        game.playerTwoMove(new CellCoords(0,yPlayerTwo));
        game.playerOneMove(new CellCoords(1,yPlayerOne));
        game.playerTwoMove(new CellCoords(1,yPlayerTwo));

        assertThat("Expected game not to be over before player one completes their row",
                game.isOver(),
                is(false));
        game.playerOneMove(new CellCoords(2,yPlayerOne));
        assertThat("Expected game to be over once player one completes their row",
                game.isOver(),
                is(true));
    }
    
    @Test
    public void When_player_two_fills_a_horizontal_row_then_game_ends() {
        // Test only one possible horizontal row because we sneakily know this
        /// re-uses the same code as player one winning.

        game.playerOneMove(new CellCoords(0,0));
        game.playerTwoMove(new CellCoords(0,1));
        game.playerOneMove(new CellCoords(1,0));
        game.playerTwoMove(new CellCoords(1,1));
        game.playerOneMove(new CellCoords(0,2));

        assertThat("Expected game not to be over before player two completes their row",
                game.isOver(),
                is(false));
        game.playerTwoMove(new CellCoords(2,1));
        assertThat("Expected game to be over once player two completes their row",
                game.isOver(),
                is(true));
    }
    
    /* TODO:
        player one cannot move when game is over.
        player two cannot move when game is over.
        player two wins with horizontal row.
        player one wins with vertical column.
        player two wins with vertical column.
        player one wins with diagonal.
        player two wins with diagonal.
        bounds checks on CellCoords.{x, y}.
    
    
     */
}
