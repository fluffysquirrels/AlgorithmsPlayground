/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package name.alex.ap.ticTacToe;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.testng.Assert.fail;
import org.testng.annotations.BeforeMethod;
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
        assertThat(game.getCell(anyCell), is(CellValue.Empty));
        game.playerOneMove(anyCell);
        assertThat(game.getCell(anyCell), is(CellValue.PlayerOne));
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
        
        assertThat(game.getCell(anyCell), is(CellValue.PlayerOne));
        assertThat(game.getCell(anotherCell), is(CellValue.PlayerTwo));
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void Player_cannot_make_a_move_in_a_filled_cell() {
        game.playerOneMove(anyCell);
        game.playerTwoMove(anyCell);
    }

    @Test
    public void When_player_one_fills_any_horizontal_row_then_game_ends() {
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
        // re-uses the same code as player one winning.

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

        assertThat(game.getGridAsString(), is(
                "11-\n" +
                "222\n" +
                "1--\n"
        ));
    }

    @Test
    public void When_player_one_fills_any_vertical_column_then_game_ends() {
        // Test all three possible vertical columns in case there is e.g. an off by
        // one error somewhere.
        test_player_one_vertical_column_example(0, 1);
        test_player_one_vertical_column_example(1, 2);
        test_player_one_vertical_column_example(2, 0);
    }

    private static void test_player_one_vertical_column_example(int xPlayerOne, int xPlayerTwo) {
        final Game game = new Game();

        game.playerOneMove(new CellCoords(xPlayerOne,0));
        game.playerTwoMove(new CellCoords(xPlayerTwo,0));
        game.playerOneMove(new CellCoords(xPlayerOne,1));
        game.playerTwoMove(new CellCoords(xPlayerTwo,1));

        assertThat("Expected game not to be over before player one completes their column",
                game.isOver(),
                is(false));
        game.playerOneMove(new CellCoords(xPlayerOne,2));
        assertThat("Expected game to be over once player one completes their column",
                game.isOver(),
                is(true));
    }

    @Test
    public void When_player_two_fills_a_vertical_column_then_game_ends() {
        // Test only one possible vertical column because we sneakily know this
        // re-uses the same code as player one winning.

        game.playerOneMove(new CellCoords(0,0));
        game.playerTwoMove(new CellCoords(1,0));
        game.playerOneMove(new CellCoords(0,1));
        game.playerTwoMove(new CellCoords(1,1));
        game.playerOneMove(new CellCoords(2,0));

        assertThat("Expected game not to be over before player two completes their column",
                game.isOver(),
                is(false));
        game.playerTwoMove(new CellCoords(1,2));
        assertThat("Expected game to be over once player two completes their column",
                game.isOver(),
                is(true));

        assertThat(game.getGridAsString(), is(
                "121\n" +
                "12-\n" +
                "-2-\n"
        ));
    }

    @Test
    public void When_player_one_fills_a_diagonal_bottom_left_to_top_right_then_the_game_ends() {
        game.playerOneMove(new CellCoords(0, 2));
        game.playerTwoMove(new CellCoords(0, 0));
        game.playerOneMove(new CellCoords(1, 1));
        game.playerTwoMove(new CellCoords(0, 1));
        
        assertThat(game.isOver(), is(false));
        game.playerOneMove(new CellCoords(2, 0));
        assertThat(game.isOver(), is(true));
        
        assertThat(game.getGridAsString(), is(
                "2-1\n" +
                "21-\n" +
                "1--\n"
        ));
    }

    @Test
    public void When_player_one_fills_a_diagonal_top_left_to_bottom_right_then_the_game_ends() {
        game.playerOneMove(new CellCoords(0, 0));
        game.playerTwoMove(new CellCoords(0, 1));
        game.playerOneMove(new CellCoords(1, 1));
        game.playerTwoMove(new CellCoords(0, 2));

        assertThat(game.isOver(), is(false));
        game.playerOneMove(new CellCoords(2, 2));
        assertThat(game.isOver(), is(true));

        assertThat(game.getGridAsString(), is(
                "1--\n" +
                "21-\n" +
                "2-1\n"
        ));
    }
    

    @Test
    public void When_player_two_fills_a_diagonal_top_left_to_bottom_right_then_the_game_ends() {
        // Only test one of the diagonal wins for player two, because
        // we sneakily know it uses the same code as the player one wins.

        game.playerOneMove(new CellCoords(0, 1));
        game.playerTwoMove(new CellCoords(0, 0));
        game.playerOneMove(new CellCoords(0, 2));
        game.playerTwoMove(new CellCoords(1, 1));
        game.playerOneMove(new CellCoords(1, 0));

        assertThat(game.isOver(), is(false));
        game.playerTwoMove(new CellCoords(2, 2));
        assertThat(game.isOver(), is(true));

        assertThat(game.getGridAsString(), is(
                "21-\n" +
                "12-\n" +
                "1-2\n"
        ));
    }

    @Test
    public void When_grid_is_filled_but_no_one_wins_the_game_is_over_in_a_draw() {
        game.playerOneMove(new CellCoords(0, 0));
        game.playerTwoMove(new CellCoords(2, 0));
        game.playerOneMove(new CellCoords(1, 0));
        game.playerTwoMove(new CellCoords(0, 1));
        game.playerOneMove(new CellCoords(1, 1));
        game.playerTwoMove(new CellCoords(1, 2));
        game.playerOneMove(new CellCoords(2, 1));
        game.playerTwoMove(new CellCoords(2, 2));

        assertThat(game.isOver(), is(false));
        game.playerOneMove(new CellCoords(0, 2));
        assertThat(game.isOver(), is(true));

        assertThat(game.getGridAsString(), is(
                "112\n" +
                "211\n" +
                "122\n"
        ));
    }

    @Test
    public void When_player_two_has_won_player_one_cannot_move() {
        game.playerOneMove(new CellCoords(0,0));
        game.playerTwoMove(new CellCoords(0,1));
        game.playerOneMove(new CellCoords(1,0));
        game.playerTwoMove(new CellCoords(1,1));
        game.playerOneMove(new CellCoords(0,2));
        game.playerTwoMove(new CellCoords(2,1));

        try{
            game.playerOneMove(new CellCoords(2,0));
            fail("Should not reach this; expected IllegalStateException.");
        }
        catch(IllegalStateException expected)
        {}
    }

    @Test
    public void When_player_one_has_won_player_two_cannot_move() {
        game.playerOneMove(new CellCoords(0,0));
        game.playerTwoMove(new CellCoords(0,1));
        game.playerOneMove(new CellCoords(1,0));
        game.playerTwoMove(new CellCoords(1,1));
        game.playerOneMove(new CellCoords(2,0));
        

        try{
            game.playerTwoMove(new CellCoords(2,1));
            fail("Should not reach this; expected IllegalStateException.");
        }
        catch(IllegalStateException expected)
        {}
    }

    @Test
    public void New_game_grid_string_should_be() {
        assertThat(game.getGridAsString(), is(
                "---\n" +
                "---\n" +
                "---\n"
        ));
    }

    @Test
    public void Complex_game_grid_string_should_be() {
        game.playerOneMove(new CellCoords(0, 0));
        game.playerTwoMove(new CellCoords(1, 0));
        game.playerOneMove(new CellCoords(1, 1));
        game.playerTwoMove(new CellCoords(2, 1));
        game.playerOneMove(new CellCoords(0, 2));
        game.playerTwoMove(new CellCoords(1, 2));
        game.playerOneMove(new CellCoords(2, 2));

        assertThat(game.getGridAsString(), is(
                "12-\n" +
                "-12\n" +
                "121\n"
        ));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void CellCoords_x_cannot_be_negative(){
        new CellCoords(-1, 0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void CellCoords_x_cannot_be_beyond_width(){
        new CellCoords(3, 0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void CellCoords_y_cannot_be_negative(){
        new CellCoords(0, -1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void CellCoords_y_cannot_be_beyond_height(){
        new CellCoords(0, 3);
    }

    /* TODO:
        Expose isDraw, playerOneHasWon, playerTwoHasWon?
            (or GameState is in {Playing, Draw, PlayerOneHasOne, PlayerTwoHasWon})
        Extract some of Game? It's currently a chunky 129 lines, with 79 lines
            deciding whether the game is over.
     */
}
