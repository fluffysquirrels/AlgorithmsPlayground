/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package name.alex.ap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class StringUtils {
    public static String arrayAsString(int[] arr, int showMax) {
        int arrShowLength = Math.min(arr.length, 20);
        int[] arrShow =
                arrShowLength == arr.length
                    ? arr
                    : Arrays.copyOf(arr, arrShowLength);
        
        boolean truncated = arrShow != arr;

        ArrayList<String> inputAsStrings = new ArrayList<>(arrShow.length);
        for (int n : arrShow) {
            inputAsStrings.add(Integer.toString(n));
        }
        final String joinedStrings = StringUtils.joinStrings(inputAsStrings, ", ");
        return "[" + joinedStrings + (truncated ? ", ... " : "") + "]";
    }
    
    public static String joinStrings(Collection<String> strings, String separator) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String s : strings) {
            if (!first) {
                sb.append(separator);
            }
            first = false;
            sb.append(s);
        }
        return sb.toString();
    }
}
