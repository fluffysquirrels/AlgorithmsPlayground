package name.alex.ap.geom;

public class Point2D {

    private final double x;
    private final double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public Point2D add(Point2D other) {
        return new Point2D(this.x() + other.x(), this.y() + other.y());
    }
    
    public Point2D subtract(Point2D other) {
        return new Point2D(this.x() - other.x(), this.y() - other.y());
    }
    
    public Point2D multiplyScalar(double scalar) {
        return new Point2D(this.x() * scalar, this.y() * scalar);
    }
    
    public double dotProduct(Point2D other) {
        return this.x() * other.x() + this.y() * other.y();
    }

    @Override
    public String toString() {
        return String.format("(%f, %f)", x, y);
    }
}