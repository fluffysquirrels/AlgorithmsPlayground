package name.alex.ap;

import static org.testng.Assert.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PalindromeTest {

    @Test(dataProvider = "getCases")
    public void testIsPalindromeWithForLoop(String s, boolean expected) {
        boolean result = isPalindromeWithForLoop(s);
        assertEquals(result, expected);
    }
    
    @Test(dataProvider = "getCases")
    public void testIsPalindromeWithReverse(String s, boolean expected) {
        boolean result = isPalindromeWithReverse(s);
        assertEquals(result, expected);
    }
    
    @DataProvider(name = "getCases")
    public Object[][] getCases(){
        return new Object[][]{
            {"", true},
            {"a", true},
            {"aa", true},
            {"ab", false},
            {"aba", true},
            {"abc", false},
            {"abba", true},
            {"a toyota", true},
            {"a bicycle", false},
        };
    }

    /*  Remove characters we don't care about and then check the remainder
        is a palindrome with a for loop.
    */
    private boolean isPalindromeWithForLoop(String s) {
        final String lowerLettersOnly = s.toLowerCase().replaceAll("[^a-z]", "");

        for(int i = 0, j = lowerLettersOnly.length() - 1;
            i < j;
            ++i, --j) {
            
            if(lowerLettersOnly.charAt(i) != lowerLettersOnly.charAt(j)) {
                return false;
            }
        }
        
        return true;
    }
    
    /*  Remove characters we don't care about and then check the remainder
        is a palindrome by generating its reverse and comparing it.
    */
    private boolean isPalindromeWithReverse(String s) {
        final String lowerLettersOnly = s.toLowerCase().replaceAll("[^a-z]", "");

        final String reversed = new StringBuffer(lowerLettersOnly).reverse().toString();
        return reversed.equals(lowerLettersOnly);
    }
}
