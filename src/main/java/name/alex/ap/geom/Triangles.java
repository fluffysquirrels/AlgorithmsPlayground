package name.alex.ap.geom;

public class Triangles {

    public static Boolean isPointInTriangle(Point2D p1, Point2D p2, Point2D p3, Point2D x) {
        LineInequality2D[] inequalitiesForTriangle = getLineInequalitiesForTriangle(p1, p2, p3);
        for (LineInequality2D ineq : inequalitiesForTriangle) {
            final boolean passesInequality = ineq.test(x);
            System.out.printf("passes inequality %s? %s\n", ineq, passesInequality);
            if (!passesInequality) {
                return false;
            }
        }
        return true;
    }
    
    private static LineInequality2D[] getLineInequalitiesForTriangle(Point2D p1, Point2D p2, Point2D p3) {
        return new LineInequality2D[]{
            getLineInequality(p1, p2, p3), 
            getLineInequality(p1, p3, p2),
            getLineInequality(p2, p3, p1)};
    }

    private static LineInequality2D getLineInequality(Point2D linePoint1, Point2D linePoint2, Point2D pointOnCorrectSideOfTheLine) {
        Point2D lineDirectionVector = linePoint2.subtract(linePoint1);
        Point2D lineNormalVector = new Point2D(lineDirectionVector.y(), -lineDirectionVector.x());
        Point2D vectorToCorrectSide = pointOnCorrectSideOfTheLine.subtract(linePoint1);
        if (lineNormalVector.dotProduct(vectorToCorrectSide) < 0) {
            lineNormalVector = lineNormalVector.multiplyScalar(-1);
        }
        double d = lineNormalVector.dotProduct(linePoint1);
        return new LineInequality2D(lineNormalVector, d);
    }

}
