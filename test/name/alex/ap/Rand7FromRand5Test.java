package name.alex.ap;

import java.util.Random;
import org.testng.annotations.Test;

public class Rand7FromRand5Test {
    @Test
    public void testRand7Distribution() {
        int iters = 1000000;
        
        int[] counts = new int[7];
        
        for(int i = 0; i < iters; ++i) {
            final int rand7 = rand7();
            counts[rand7] += 1;
        }
        
        for(int i = 0; i < 7; ++i) {
            System.out.printf("%d: %d\n", i, counts[i]);
        }
    }
    
    private int rand7() {
        int rand25 = rand5() * 5 + rand5();
        
        if(rand25 >= 3 * 7){
            // Try again with new rand25.
            return rand7();
        }
        
        return rand25 % 7;
    }
    
    private int rand5() {
        return r.nextInt(5);
    }

    private final Random r = new Random();
}
