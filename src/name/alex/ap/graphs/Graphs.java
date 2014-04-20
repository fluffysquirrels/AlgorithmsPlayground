package name.alex.ap.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graphs {
    private Graphs(){}

    private static enum VisitedState{
        NotVisited,
        BeingTraversed,
        BeenTraversed,
    }

    public static boolean hasCycle(UndirectedGraph g) {
        HashMap<Integer, VisitedState> visitedVertexMap = new HashMap<>();
        HashSet<UndirectedEdge> traversedEdgeSet = new HashSet<>();

        for(Vertex v: g.getVertices()) {
            boolean hcrv =
                    hasCycleRecursive(g, visitedVertexMap, traversedEdgeSet,
                            v.getIndex());
            if(hcrv) {
                return true;
            }
        }

        return false;
    }

    private static boolean hasCycleRecursive(
            UndirectedGraph g,
            Map<Integer, VisitedState> visitedVertexMap,
            Set<UndirectedEdge> traversedEdgeSet,
            Integer currVertexIndex) {

        VisitedState vertexState = visitedVertexMap.get(currVertexIndex);
        if(vertexState == VisitedState.BeingTraversed) {
            return true;
        }
        if(vertexState == VisitedState.BeenTraversed) {
            return false;
        }

        visitedVertexMap.put(currVertexIndex, VisitedState.BeingTraversed);
        for(UndirectedEdge outEdge: g.getEdgesFromVertex(currVertexIndex)) {
            if(traversedEdgeSet.contains(outEdge)){
                continue;
            }
            traversedEdgeSet.add(outEdge);
            
            boolean hcrv = hasCycleRecursive(g, visitedVertexMap, traversedEdgeSet,
                                outEdge.getTo());
            if(hcrv) {
                return true;
            }
        }
        visitedVertexMap.put(currVertexIndex, VisitedState.BeenTraversed);
        
        return false;
    }
}
