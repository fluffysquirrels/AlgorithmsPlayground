package name.alex.ap;


import java.util.ArrayList;
import java.util.HashSet;


public class StreamsOfIntegers {
    private final HashSet<Integer> _a = new HashSet<>();
    private final HashSet<Integer> _b = new HashSet<>();

    public void addToA(int n){
        _a.add(n);
    }

    public void addToB(int n){
        _b.add(n);
    }

    public ArrayList<Integer> getAUnionB(){
        return SetUtils.getSetUnion(_a, _b);
    }

    public ArrayList<Integer> getAIntersectB(){
        return SetUtils.getSetIntersection(_a, _b);
    }

    public ArrayList<Integer> getAMinusB(){
        return SetUtils.getSetMinusOtherSet(_a, _b);
    }

    public ArrayList<Integer> getBMinusA(){
        return SetUtils.getSetMinusOtherSet(_b, _a);
    }

}
