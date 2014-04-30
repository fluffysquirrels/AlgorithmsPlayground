
package name.alex.ap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import static org.testng.Assert.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

// From http://www.careercup.com/question?id=5082857865216000
// For a given array with positive and negative element,
// find sub array with maximum sum. Sub array must have same sequence of element
// as that of parental array.
// Eg: P = {4,6,-3,1,5,9,-2} then S ={4,6,-3,1,5,9} //Correct output.

public class MaxSumInSubArrayTest {
    @Test(dataProvider = "getShortExamples")
    public void max_sum_of_subarray_examples_with_naive_algorithm(
            int[] arr, int expectedMaxSum) {
        int result = getMaxSumOfSubArrayNaive(arr);

        printInputOutputExpected(arr, result, expectedMaxSum);
        assertEquals(result, expectedMaxSum);
    }

    @Test(dataProvider = "getAllExamples")
    public void max_sum_of_subarray_examples_with_running_totals_algorithm(
            int[] arr, int expectedMaxSum) {
        int result = getMaxSumOfSubArrayRunningTotals(arr);

        printInputOutputExpected(arr, result, expectedMaxSum);
        assertEquals(result, expectedMaxSum);
    }

    @Test(dataProvider = "getAllExamples")
    public void max_sum_of_subarray_examples_with_running_totals_and_resets_algorithm(
            int[] arr, int expectedMaxSum) {
        int result = getMaxSumOfSubArrayRunningTotalsWithResets(arr);

        printInputOutputExpected(arr, result, expectedMaxSum);
        assertEquals(result, expectedMaxSum);
    }

//    @Test(dataProvider = "getAllExamples")
//    public void max_sum_of_subarray_examples_with_run_merging_algorithm(
//            int[] arr, int expectedMaxSum) {
//        int result = getMaxSumOfSubArrayRunMerging(arr);
//
//        printInputOutputExpected(arr, result, expectedMaxSum);
//        assertEquals(result, expectedMaxSum);
//    }

    @DataProvider
    private Object[][] getShortExamples() {
        return new Object[][] {
            new Object[]{new int[] {}, 0},
            new Object[]{new int[] {5}, 5},
            new Object[]{new int[] {-5}, 0},
            new Object[]{new int[] {4,6,-3,1,5,9,-2}, 22},
            getRandomExample(6, 1000),
            getRandomExample(7, 1000),
            getRandomExample(8, 1000),
        };
    }

    @DataProvider
    private Object[][] getAllExamples() {
        Object[][] shortExamples = getShortExamples();

        List<Object[]> allExamples = new ArrayList<>(Arrays.asList(shortExamples));

        allExamples.add(getRandomExample(10, 1000000));
        allExamples.add(getRandomExample(11, 1000000));

        return allExamples.toArray(new Object[allExamples.size()][0]);
    }

    private Object[] getRandomExample(long seed, int length) {
        final int minValue = -10000;
        final int maxValue = +10000;

        Random r = new Random(seed);
        int[] arr = new int[length];

        for(int i = 0; i < length; ++i){
            arr[i] = minValue + r.nextInt(maxValue + 1 - minValue);
        }

        int expected = getMaxSumOfSubArrayRunningTotals(arr);

        return new Object[]{arr, expected};
    }

    public int getMaxSumOfSubArrayNaive(int[] arr) {
        int maxSum = 0;

        for(int minIndex = 0; minIndex < arr.length; ++minIndex) {
            for(int maxIndex = minIndex - 1; maxIndex < arr.length; ++maxIndex) {
                // We've chosen a sub-array, calculate its sum and keep track
                // of the highest sum.

                int thisSum = 0;

                for(int i = minIndex; i <= maxIndex; ++i) {
                    thisSum += arr[i];
                }

                maxSum = Math.max(thisSum, maxSum);
            }
        }

        return maxSum;
    }

    public int getMaxSumOfSubArrayRunningTotals(int[] arr) {
        int minSumSeenSoFar = 0;
        int maxSubArraySumSeenSoFar = 0;
        int currentSum = 0;

        for(int i = 0; i < arr.length; ++i) {
            currentSum += arr[i];
            maxSubArraySumSeenSoFar =
                    Math.max(currentSum - minSumSeenSoFar,
                            maxSubArraySumSeenSoFar);
            minSumSeenSoFar = Math.min(currentSum, minSumSeenSoFar);
        }

        return maxSubArraySumSeenSoFar;
    }

    public int getMaxSumOfSubArrayRunningTotalsWithResets(int[] arr) {
        int maxSubArraySumSeenSoFar = 0;
        int currentSum = 0;

        for(int i = 0; i < arr.length; ++i) {
            currentSum += arr[i];
            currentSum = Math.max(0, currentSum);
            maxSubArraySumSeenSoFar =
                    Math.max(currentSum, maxSubArraySumSeenSoFar);

        }

        return maxSubArraySumSeenSoFar;
    }

//    private int getMaxSumOfSubArrayRunMerging(int[] arr) {
//        int minIndex = 0;
//        int maxIndex = arr.length - 1;
//        
//        // The sub array with maximum sum needn't include
//        // values <= 0 at the start of the array, so
//        // increment minIndex to skip past them.
//        
//        while(minIndex < arr.length && arr[minIndex] <= 0) {
//            ++minIndex;
//        }
//        
//        if(minIndex == arr.length) {
//            // Every value in the array is <= 0, so the maximum sum
//            // 
//            return 0;
//        }
//        
//        // The sub array with maximum sum needn't include
//        // values <= 0 at the end of the array, so
//        // decrement maxIndex to skip past them.
//        
//        while(maxIndex > 0 && arr[maxIndex] <= 0) {
//            --maxIndex;
//        }
//        
//        int maxElt = 0;
//        for(int i = minIndex; i <= maxIndex; ++i) {
//            maxElt = Math.max(arr[i], maxElt);
//        }
//        
//        List<Integer> merged = new ArrayList<Integer>();
//        
//        int currentSum = 0;
//        
//        while(notAtEnd()) {
//            
//        }
//        
//        while(positives() && notAtEnd()) {
//            currentSum += arr[i];
//            ++i;
//        }
//        
//        while(negatives() && notAtEnd()) {
//            currentSum += arr[i];
//            ++i;
//        }
//        
//        merged.add(currentSum);
//        
//        currentSum = 0;
//        
//        for(int i = minIndex; i <= maxIndex; ++i){
//            
//        }
//        
//        int[] mergedArray = new int[merged.size()];
//        
//        for(int i = 0; i < merged.size(); ++i) {
//            mergedArray[i] = merged.get(i);
//        }
//        
//        int recursiveMaxSum = getMaxSumOfSubArrayRunMerging(mergedArray);
//        int trueMax = Math.max(maxElt, recursiveMaxSum);
//        
//        return trueMax;
//    }

    private void printInputOutputExpected(int[] arr, int result, int expectedMaxSum) {
        System.out.printf("input:    %s\n", StringUtils.arrayAsString(arr, 20));
        System.out.printf("output:   %d\n", result);
        System.out.printf("expected: %d\n", expectedMaxSum);
        System.out.printf("\n");
    }
}
