package name.alex.ap.geom;

public class LineInequality2D {
    public static final double DOUBLE_EPSILON = 1E-10;
    
    private final Point2D n;
    private final double d;

    public Point2D getN() {
        return n;
    }

    public double getD() {
        return d;
    }

    public LineInequality2D(Point2D n, double d) {
        this.n = n;
        this.d = d;
    }

    public boolean test(Point2D x) {
        final double testedValue = x.dotProduct(n) - d; // Should be >= 0.
        
        // Factor of d so the relative error (epsilon)
        // is scaled by expected value in this comparison (d).
        final double errorBound = Math.abs(d) * DOUBLE_EPSILON;
        return testedValue >= - errorBound;
    }
    
    @Override
    public String toString() {
        return String.format("{{x.%s >= %f}}", getN(), getD());
    }
    
}

