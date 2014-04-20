package name.alex.ap.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graphs {

    private Graphs(){}

    public static <TOutput> TOutput depthFirstSearch(UndirectedGraph g, DfsVisitor visitor){
        HashMap<Integer, VertexVisitedState> visitedVertexMap = new HashMap<>();
        HashSet<UndirectedEdge> traversedEdgeSet = new HashSet<>();
        
        visitor.setDfsState(g, visitedVertexMap, traversedEdgeSet);
        
        for(Vertex v: g.getVertices()) {
            TOutput vertexVisitOutput =
                    depthFirstSearchRecursive(
                            g, visitedVertexMap, traversedEdgeSet,
                            visitor,
                            v.getIndex());
            if(vertexVisitOutput != null) {
                return vertexVisitOutput;
            }
        }

        return null;
    }
    
    private static <TOutput> TOutput depthFirstSearchRecursive(
            UndirectedGraph g, HashMap<Integer, VertexVisitedState> visitedVertexMap,
            HashSet<UndirectedEdge> traversedEdgeSet, DfsVisitor visitor,
            Integer currVertexIndex) {

        VertexVisitedState vertexState = visitedVertexMap.get(currVertexIndex);

        if(vertexState == null){
            vertexState = VertexVisitedState.NotVisited;
        }

        TOutput currVertexOutput = (TOutput) visitor.visitVertex(currVertexIndex, vertexState);

        if(currVertexOutput != null) {
            return currVertexOutput;
        }

        visitedVertexMap.put(currVertexIndex, VertexVisitedState.BeingTraversed);
        for(UndirectedEdge outEdge: g.getEdgesFromVertex(currVertexIndex)) {
            if(traversedEdgeSet.contains(outEdge)){
                continue;
            }
            traversedEdgeSet.add(outEdge);
            
            TOutput nextVertexOutput =
                    depthFirstSearchRecursive(g, visitedVertexMap,
                            traversedEdgeSet, visitor, outEdge.getTo());
            if(nextVertexOutput != null){
                return nextVertexOutput;
            }
        }
        visitedVertexMap.put(currVertexIndex, VertexVisitedState.BeenTraversed);
        
        return null;
    }

    public static Boolean hasCycle(UndirectedGraph g) {
        Boolean foundCycle = depthFirstSearch(g, new DfsVisitor<Boolean>() {
                @Override
                public Boolean visitVertex(Integer currVertexIndex, VertexVisitedState vertexState){
                    if(vertexState == VertexVisitedState.BeingTraversed) {
                        return true;
                    }
                    
                    return null;
                }
            }
        );

        return foundCycle != null && foundCycle == true;
    }
}
