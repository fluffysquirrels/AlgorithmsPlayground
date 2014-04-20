package name.alex.ap.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Graphs {
    private Graphs(){}

    static Map<Integer, List<Integer>> getComponents(UndirectedGraph g) {
        final Map<Integer, List<Integer>> rv = new HashMap<>();

        depthFirstSearch(g, new DfsVisitor(){

            @Override
            public Object visitVertex(Integer currVertexIndex,
                    VertexVisitedState vertexState, Integer startVertexIndex) {
                if(vertexState != VertexVisitedState.NotVisited) {
                    return null;
                }

                List<Integer> componentVertices = rv.get(startVertexIndex);

                if(componentVertices == null) {
                    componentVertices = new ArrayList<Integer>();
                    rv.put(startVertexIndex, componentVertices);
                }

                componentVertices.add(currVertexIndex);

                return null;
            }
        });
        
        return rv;
    }

    public static <TOutput> TOutput depthFirstSearch(UndirectedGraph g, DfsVisitor visitor){
        HashMap<Integer, VertexVisitedState> visitedVertexMap = new HashMap<>();
        HashSet<UndirectedEdge> traversedEdgeSet = new HashSet<>();

        visitor.setDfsState(g, visitedVertexMap, traversedEdgeSet);

        for(Vertex v: g.getVertices()) {
            TOutput vertexVisitOutput =
                    depthFirstSearchRecursive(
                            g, visitedVertexMap, traversedEdgeSet,
                            visitor,
                            v.getIndex(),
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
            Integer currVertexIndex, Integer startVertexIndex) {

        VertexVisitedState vertexState = visitedVertexMap.get(currVertexIndex);

        if(vertexState == null){
            vertexState = VertexVisitedState.NotVisited;
        }

        TOutput currVertexOutput =
                (TOutput) visitor.visitVertex(currVertexIndex,
                    vertexState, startVertexIndex);

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
                            traversedEdgeSet, visitor, outEdge.getTo(),
                            startVertexIndex);
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
                public Boolean visitVertex(Integer currVertexIndex,
                        VertexVisitedState vertexState, Integer startVertexIndex){
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
