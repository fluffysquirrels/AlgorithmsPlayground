package name.alex.ap;

import java.util.ArrayList;
import java.util.Set;


public class SetUtils {

    public static <E> ArrayList getSetMinusOtherSet(Set<E> set, Set<E> otherSet) {
        ArrayList<E> rv = new ArrayList<>();
        for (E n : set) {
            if (!otherSet.contains(n)) {
                rv.add(n);
            }
        }
        return rv;
    }

    public static <E> ArrayList getSetIntersection(Set<E> set1, Set<E> set2) {
        ArrayList<E> rv = new ArrayList<>();
        for (E n : set1) {
            if (set2.contains(n)) {
                rv.add(n);
            }
        }
        return rv;
    }

    public static ArrayList getSetUnion(final Set<Integer> set1, final Set<Integer> set2) {
        ArrayList<Integer> rv = new ArrayList<>(set1);
        for (Integer n : set2) {
            if (!set1.contains(n)) {
                rv.add(n);
            }
        }
        return rv;
    }

    private SetUtils() {
    }
}
