package name.alex.ap.graphs;

import java.util.Map;
import java.util.Set;

public abstract class DfsVisitor<TOutput> {
    private UndirectedGraph g;
    private Map<Integer, VertexVisitedState> visitedVertexMap;
    private Set<UndirectedEdge> traversedEdgeSet;

    public void setDfsState(
            UndirectedGraph g,
            Map<Integer, VertexVisitedState> visitedVertexMap,
            Set<UndirectedEdge> traversedEdgeSet) {
        this.g = g;
        this.visitedVertexMap = visitedVertexMap;
        this.traversedEdgeSet = traversedEdgeSet;
    }

    public abstract TOutput visitVertex(
            Integer currVertexIndex,
            VertexVisitedState vertexState,
            Integer startVertexIndex);

    public UndirectedGraph getG() {
        return g;
    }

    public Map<Integer, VertexVisitedState> getVisitedVertexMap() {
        return visitedVertexMap;
    }

    public Set<UndirectedEdge> getTraversedEdgeSet() {
        return traversedEdgeSet;
    }
}
