/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package name.alex.ap.ticTacToe;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ttt {
    Game game = null;
    
    @BeforeTest
    public void beforeTest() {
        game = new Game();
    }
    
    @Test
    public void A_new_game_should_not_be_over() {
        assertThat(game.isOver(), is(false));
    }
    
    @Test
    public void When_first_player_makes_a_move_we_can_retrieve_it_from_the_grid(){
        assertThat(game.getCell(0, 0), is(CellState.Empty));
        
        game.playerOneMove(0, 0);
        
        assertThat(game.getCell(0, 0), is(CellState.PlayerOne));
    }
    
    /* TODO:
        Cell class for co-ords
        simple game
    
     */
}
