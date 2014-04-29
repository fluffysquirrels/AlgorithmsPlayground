package name.alex.ap.graphs;

import java.util.*;

public class UndirectedGraph {
    private final Collection<UndirectedEdge> edges = new ArrayList<>();
    private final Collection<Vertex> vertices = new ArrayList<>();

    // TODO: Don't add a new edge when that edge already exists.
    public void addEdge(Integer v1, Integer v2){
        ensureVertex(v1);
        ensureVertex(v2);

        edges.add(new UndirectedEdge(v1, v2));
        edges.add(new UndirectedEdge(v2, v1));
    }

    public void ensureVertex(Integer index) {
        for(Vertex v: vertices) {
            if(v.getIndex() == index){
                return;
            }
        }

        vertices.add(new Vertex(index));
    }

    public Iterable<UndirectedEdge> getEdgesFromVertex(Integer v){
        ArrayList<UndirectedEdge> rv = new ArrayList<>();

        for(UndirectedEdge e: edges){
            if(e.getFrom() == v) {
                rv.add(e);
            }
        }

        return rv;
    }

    public Iterable<Vertex> getVertices() {
        return vertices;
    }
}
