package name.alex.ap;

import java.util.ArrayList;
import java.util.Collection;
import static org.testng.Assert.*;
import org.testng.annotations.*;

public class CountConsecutiveNumberRunsTest {
    
    public CountConsecutiveNumberRunsTest() {
    }

    
    @Test(dataProvider = "getCases")
    public void shouldPass(int[] input, int expectedOutput) {
        ArrayList<String> inputAsStrings = new ArrayList<>(input.length);
        for(int n: input) {
            inputAsStrings.add(Integer.toString(n));
        }
        System.out.printf("input:  [%s]\n", joinStrings(inputAsStrings, ", "));
        int output = countConsecutiveNumberRuns(input);
        System.out.printf("output: %d\n", output);
        System.out.printf("expected output: %d\n", expectedOutput);
        System.out.printf("%s\n", output == expectedOutput ? "PASS" : "FAIL");
        System.out.printf("\n");

        assertEquals(output, expectedOutput);
    }

    private static int countConsecutiveNumberRuns(int[] input) {
        if(input.length == 0) {
            return 0;
        }
        int prev = input[0];
        int runs = 1;
        
        for(int i = 1; i < input.length; ++i) {
            int n = input[i];
            if(n != prev + 1) {
                runs += 1;
            }
            prev = n;
        }
        
        return runs;
    }

    public String joinStrings (Collection<String> strings, String separator) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;

        for(String s: strings) {
            if(!first){
                sb.append(separator);
            }
            first = false;

            sb.append(s);
        }

        return sb.toString();
    }
    
    @DataProvider(name = "getCases")
    public Object[][] getCases() {
        return new Object[][] {
            {new int[]{}, 0},
            {new int[]{1}, 1},
            {new int[]{1, 3}, 2},
            {new int[]{1, 2}, 1},
            {new int[]{1, 2, 3}, 1},
            {new int[]{1, 3, 4, 5, 7}, 3},
            {new int[]{123, 124, 6, 7, 9}, 3},
        };
    }
}
