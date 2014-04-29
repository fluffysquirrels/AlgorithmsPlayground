package name.alex.ap;

import java.util.Arrays;
import name.alex.ap.geom.LineInequality2D;
import name.alex.ap.geom.Point2D;
import name.alex.ap.geom.Triangles;
import static org.testng.Assert.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

// From http://www.careercup.com/question?id=6331656331853824 :
// Given three points in a 2D plane with their (x, y) coordinates say if the
// origin lies inside the triangle formed by the three points.
public class TriangleContainsPointTest {
    @Test(dataProvider = "getExamples")
    public void testIt(Point2D p1, Point2D p2, Point2D p3, Point2D x, Boolean expected) {
        Boolean result = Triangles.isPointInTriangle(p1, p2, p3, x);
        printResult(p1, p2, p3, x, result, expected);
        assertEquals(result, expected);
    }
    
    @DataProvider(name = "getExamples")
    public Object[][] getExamples() {
        return new Object[][]{
            new Object[]{new Point2D(0, 0), new Point2D(1, 1), new Point2D(1, 0), new Point2D(0, 0), true},
            new Object[]{new Point2D(0, 0), new Point2D(1, 1), new Point2D(1, 0), new Point2D(1, 1), true},
            new Object[]{new Point2D(0, 0), new Point2D(1, 1), new Point2D(1, 0), new Point2D(1, 0), true},
            new Object[]{new Point2D(0, 0), new Point2D(1, 1), new Point2D(1, 0), new Point2D(0.9, 0.1), true},
            new Object[]{new Point2D(0, 0), new Point2D(1, 1), new Point2D(1, 0), new Point2D(0.99, 0.9), true},
            new Object[]{new Point2D(0, 0), new Point2D(1, 1), new Point2D(1, 0), new Point2D(0.1, 0.01), true},
            new Object[]{new Point2D(0, 0), new Point2D(1, 1), new Point2D(1, 0), new Point2D(0.5, 0.01), true},
            new Object[]{new Point2D(0, 0), new Point2D(1, 1), new Point2D(1, 0), new Point2D(0.5, -0.01), false},
            new Object[]{new Point2D(0, 0), new Point2D(1, 1), new Point2D(1, 0), new Point2D(0.9, 1), false},
            new Object[]{new Point2D(0, 0), new Point2D(1, 1), new Point2D(1, 0), new Point2D(0, 1), false},
        };
    }
    
    
    private void printResult(Point2D p1, Point2D p2, Point2D p3, Point2D x, Boolean result, Boolean expected) {
        System.out.println();
        System.out.printf("p1:                          %s\n", p1);
        System.out.printf("p2:                          %s\n", p2);
        System.out.printf("p3:                          %s\n", p3);
        System.out.printf("x:                           %s\n", x);
        System.out.printf("x in triangle p1-3 result:   %s\n", result);
        System.out.printf("x in triangle p1-3 expected: %s\n", expected);
        System.out.println();
    }
}