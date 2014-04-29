package name.alex.ap;

import com.google.common.primitives.Bytes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

// From http://www.careercup.com/question?id=6218115968925696:
// I have two arrays A and B(each containing 8 bit integers). Find the common
// elements between them.
// The questions started out as a general discussion with the most inefficient
// method. Then the interviewer asked me to improve the solution (to give a
// NlogN and finally a linear time solution)

public class IntersectionOfTwoByteArraysTest {
    @Test(dataProvider = "getExamples")
    public void test_array_intersection_with_sort_algorithm(byte[] a, byte[] b, byte[] expected){
        byte[] result = getIntersectionWithSortAndMergeAlgorithm(a,b);

        printResult(a, b, result, expected);

        Arrays.sort(result);
        assertEquals(result, expected);
    }

    @Test(dataProvider = "getExamples")
    public void test_array_intersection_with_BitSet_algorithm(byte[] a, byte[] b, byte[] expected){
        byte[] result = getIntersectionWithBitSetAlgorithm(a,b);

        printResult(a, b, result, expected);

        Arrays.sort(result);
        assertEquals(result, expected);
    }
    
    @Test(dataProvider = "getExamples")
    public void test_array_intersection_with_HashSet_algorithm(byte[] a, byte[] b, byte[] expected){
        byte[] result = getIntersectionWithHashSetAlgorithm(a,b);

        printResult(a, b, result, expected);

        Arrays.sort(result);
        assertEquals(result, expected);
    }

    private void printResult(byte[] a, byte[] b, byte[] result, byte[] expected) {
        System.out.println();
        System.out.printf("a:                      %s\n", Arrays.toString(a));
        System.out.printf("b:                      %s\n", Arrays.toString(b));
        System.out.printf("a intersect b result:   %s\n", Arrays.toString(result));
        System.out.printf("a intersect b expected: %s\n", Arrays.toString(expected));
        System.out.println();
    }

    @DataProvider(name = "getExamples")
    public Object[][] getExamples() {
        return new Object[][] {
            new Object[]{new byte[]{}, new byte[]{}, new byte[]{}},
            new Object[]{new byte[]{1}, new byte[]{}, new byte[]{}},
            new Object[]{new byte[]{}, new byte[]{2}, new byte[]{}},
            new Object[]{new byte[]{1}, new byte[]{2}, new byte[]{}},
            new Object[]{new byte[]{4,3,2,1}, new byte[]{1,3,5}, new byte[]{1,3}},
            new Object[]{new byte[]{2,1}, new byte[]{1,2,3}, new byte[]{1,2}},
            new Object[]{new byte[]{3,2,1}, new byte[]{1,2}, new byte[]{1,2}},
            new Object[]{new byte[]{1,1,2}, new byte[]{1,2,2}, new byte[]{1,2}},
        };
    }

    private byte[] getIntersectionWithSortAndMergeAlgorithm(byte[] a, byte[] b) {
        // Take copies to not trample existing arrays.
        a = a.clone();
        b = b.clone();

        Arrays.sort(a);
        Arrays.sort(b);

        int ixA = 0;
        int ixB = 0;

        List<Byte> out = new ArrayList<>();

        while(ixA < a.length && ixB < b.length){
            if(a[ixA] == b[ixB]) {
                byte val = a[ixA];
                out.add(val);
                while(ixA < a.length && a[ixA] == val) {
                    ++ixA;
                }
                while(ixB < b.length && b[ixB] == val) {
                    ++ixB;
                }
                continue;
            }
            else if(a[ixA] < b[ixB]) {
                ++ixA;
            }
            else if(a[ixA] > b[ixB]) {
                ++ixB;
            }
            else {
                throw new AssertionError("This should not be reached.");
            }
        }
        
        return Bytes.toArray(out);
    }

    private byte[] getIntersectionWithBitSetAlgorithm(byte[] a, byte[] b) {
        // The value range for the array is small: only 2**8 values possible in a byte.
        // So we can afford to create a temporary BitSet t with each
        // element t[i] representing whether i is in a particular set. This is
        // then used as a HashSet would be.
        
        // NB: All values are initially false.
        BitSet foundInA = new BitSet(256);
        
        for(byte elt: a) {
            foundInA.set(elt & 0xff);
        }
        
        BitSet alreadyOutput = new BitSet(256);
        List<Byte> out = new ArrayList<>();
        
        for(byte elt: b) {
            if(foundInA.get(elt & 0xff)
                    && !alreadyOutput.get(elt & 0xff)){
                out.add(elt);
                alreadyOutput.set(elt & 0xff);
            }
        }

        return Bytes.toArray(out);
    }

    private byte[] getIntersectionWithHashSetAlgorithm(byte[] a, byte[] b) {
        HashSet<Byte> foundInA = new HashSet<>(256);
        
        for(byte elt: a) {
            foundInA.add(elt);
        }
        
        HashSet<Byte> alreadyOutput = new HashSet<>(256);
        List<Byte> out = new ArrayList<>();
        
        for(byte elt: b) {
            if(foundInA.contains(elt)
                    && !alreadyOutput.contains(elt)){
                out.add(elt);
                alreadyOutput.add(elt);
            }
        }

        return Bytes.toArray(out);
    }
}