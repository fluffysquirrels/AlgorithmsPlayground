package name.alex.ap;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

// From: http://www.careercup.com/question?id=5707569985224704
// Construct an array of size 10 such that if a[x] =y then x should be repeated
// y times in that array. Eg: If a[1] = 2 then 1 should be present in that array
// 2 times.
public class ArrayCountArrayTest {
    private static final int UNASSIGNED_VALUE = -1;
    private static final int ARRAY_LENGTH = 10;

    @Test
    public void solveItTest() {
        Collection<int[]> solutions = solveIt();
        System.out.println();
        System.out.printf("Found %d solution(s):\n", solutions.size());
        for(int[] soln: solutions) {
            System.out.println(Arrays.toString(soln));
        }
        System.out.println();
    }

    private Collection<int[]> solveIt() {
        int[] initialArray = new int[ARRAY_LENGTH];
        
        Arrays.fill(initialArray, UNASSIGNED_VALUE);
        Collection<int[]> solutions = new ArrayList<>();
        tryFillElement(initialArray, 0, solutions);
        return solutions;
    }

    private void tryFillElement(int[] array, int indexToFill, Collection<int[]> solutions) {
        assert array[indexToFill] == UNASSIGNED_VALUE;

        final int origNumUnassigned = countOccurrences(array, UNASSIGNED_VALUE);

        final int origCurrCount = countOccurrences(array, indexToFill);

        for(int valAttempt = origCurrCount; valAttempt <= origCurrCount + origNumUnassigned; ++valAttempt) {
            int[] withNewElement = array.clone();
            withNewElement[indexToFill] = valAttempt;
            final int nowNumUnassigned = countOccurrences(withNewElement, UNASSIGNED_VALUE);
            if(hasContradictionAtIndex(withNewElement, indexToFill, valAttempt)) {
                continue;
            }
            
            if(nowNumUnassigned == 0){
                if(!hasContradictionAtAnyIndex(withNewElement)){
                    solutions.add(withNewElement);
                }
            }
            else {
                if(indexToFill < ARRAY_LENGTH - 1) {
                    tryFillElement(withNewElement, indexToFill + 1, solutions);
                }
            }
        }
    }

    private boolean hasContradictionAtAnyIndex(int[] array) {
        for(int i = 0; i < ARRAY_LENGTH; ++i) {
            if(array[i] == UNASSIGNED_VALUE) {
                continue;
            }
            
            if(hasContradictionAtIndex(array, i, array[i])){
                return true;
            }
        }
        
        return false;
    }
    
    private boolean hasContradictionAtIndex(int[] array, int index, int assignedCount) {
        int numUnassigned = countOccurrences(array, UNASSIGNED_VALUE);

        int currCount = countOccurrences(array, index);
        final int stillToFillForThisIndex = assignedCount - currCount;
        
        if(stillToFillForThisIndex < 0){
            return true;
        }
        
        if(stillToFillForThisIndex > numUnassigned) {
            return true;
        }
        
        return false;
    }

    private int countOccurrences(int[] array, int value) {
        int count = 0;
        for(int x: array) {
            if(x == value) {
                ++count;
            }
        }
        return count;
    }
}
