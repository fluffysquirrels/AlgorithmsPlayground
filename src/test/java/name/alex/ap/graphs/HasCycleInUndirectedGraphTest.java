package name.alex.ap.graphs;

import name.alex.ap.graphs.Graphs;
import name.alex.ap.graphs.UndirectedGraph;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

public class HasCycleInUndirectedGraphTest {
    @Test
    public void connectedNoCycleExample(){
        UndirectedGraph g = new UndirectedGraph();

        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(2, 4);

        boolean result = Graphs.hasCycle(g);

        assertEquals(result, false);
    }
    
        @Test
    public void disconnectedNoCycleExample(){
        UndirectedGraph g = new UndirectedGraph();

        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(2, 4);
        
        g.ensureVertex(5);
        g.ensureVertex(6);
        
        g.addEdge(7, 8);
        g.addEdge(8, 9);

        boolean result = Graphs.hasCycle(g);

        assertEquals(result, false);
    }
    
    @Test
    public void cycleExample(){
        UndirectedGraph g = new UndirectedGraph();

        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(2, 4);
        g.addEdge(4, 0);

        boolean result = Graphs.hasCycle(g);

        assertEquals(result, true);
    }
}
