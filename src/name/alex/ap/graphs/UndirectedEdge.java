package name.alex.ap.graphs;

import java.util.Objects;

public class UndirectedEdge {

    private final Integer from;

    public Integer getFrom() {
        return from;
    }

    private final Integer to;

    public Integer getTo() {
        return to;
    }

    public UndirectedEdge(Integer from, Integer to) {
        if(from == null) {
            throw new IllegalArgumentException("from must not be null");
        }

        if(to == null) {
            throw new IllegalArgumentException("to must not be null");
        }

        this.from = from;
        this.to = to;
    }

    /*
        Two UndirectedEdge instances have the same hashcode even if the
        vertex indices are swapped around.
    */
    @Override
    public int hashCode() {
        int hash = 7;

        Integer lowerVertexIndex = Math.min(this.from, this.to);
        Integer higherVertexIndex = Math.max(this.from, this.to);

        hash = 37 * hash + Objects.hashCode(lowerVertexIndex);
        hash = 37 * hash + Objects.hashCode(higherVertexIndex);
        return hash;
    }

    /*
        Two UndirectedEdge instances are equal if their to and from vertex
        indices are equal or swapped around.
    */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UndirectedEdge other = (UndirectedEdge) obj;

        if(Objects.equals(this.from, other.from) &&
           Objects.equals(this.to, other.to)){
            return true;
        }

        if(Objects.equals(this.from, other.to) &&
           Objects.equals(this.from, other.to)){
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "UndirectedEdge{" + "from=" + from + ", to=" + to + '}';
    }

    
}
