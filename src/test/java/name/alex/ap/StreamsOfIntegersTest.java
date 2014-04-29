package name.alex.ap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

// There are 2 sets A and B of numbers where numbers are keep coming at high speed.
// At any given time, you need to find:
// 'A UNION B', 'A INTERSECTION B', 'A - B' and 'B - A'.
//
// How will you store numbers and how will you find these value in real time?

public class StreamsOfIntegersTest {
    private name.alex.ap.StreamsOfIntegers _impl;

    @BeforeTest
    public void beforeTest(){
        _impl = new name.alex.ap.StreamsOfIntegers();
    }

    @AfterTest
    public void afterTest(){
        _impl = null;
    }

    @Test
    public void aUnionB(){
        addToA(1, 2, 3);
        addToB(1, 2, 4);

        ArrayList<Integer> result = _impl.getAUnionB();
        Collections.sort(result);
        List<Integer> expected = Arrays.asList(1,2,3,4);

        assertEquals(result, expected);
    }

    @Test
    public void aIntersectB(){
        addToA(1, 2, 3);
        addToB(1, 2, 4);

        ArrayList<Integer> result = _impl.getAIntersectB();
        Collections.sort(result);
        List<Integer> expected = Arrays.asList(1, 2);

        assertEquals(result, expected);
    }

    @Test
    public void aMinusB(){
        addToA(1, 2, 3);
        addToB(1, 2, 4);

        ArrayList<Integer> result = _impl.getAMinusB();
        Collections.sort(result);
        List<Integer> expected = Arrays.asList(3);

        assertEquals(result, expected);
    }

    @Test
    public void bMinusA(){
        addToA(1, 2, 3);
        addToB(1, 2, 4);

        ArrayList<Integer> result = _impl.getBMinusA();
        Collections.sort(result);
        List<Integer> expected = Arrays.asList(4);

        assertEquals(result, expected);
    }

    private void addToA(Integer... ns){
        for(Integer i: ns) {
            _impl.addToA(i);
        }
    }

    private void addToB(Integer... ns){
        for(Integer i: ns) {
            _impl.addToB(i);
        }
    }
}
