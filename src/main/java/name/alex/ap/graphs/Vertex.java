package name.alex.ap.graphs;

public class Vertex {
    
    private final Integer index;

    public Integer getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "Vertex{" + "index=" + index + '}';
    }

    public Vertex(Integer index) {
        if(index == null) {
            throw new IllegalArgumentException("index must not be null");
        }
        
        this.index = index;
    }
}
