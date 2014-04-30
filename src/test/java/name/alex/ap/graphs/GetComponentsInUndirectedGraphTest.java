package name.alex.ap.graphs;

import java.util.List;
import java.util.Map;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

public class GetComponentsInUndirectedGraphTest {
    @Test
    public void getComponentsInUndirectedGraphExample() {
        UndirectedGraph g = new UndirectedGraph();
        
        g.ensureVertex(0);
        
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 3);
        g.addEdge(4, 5);
        g.addEdge(5, 6);
        g.addEdge(5, 7);
        g.addEdge(5, 8);
        
        g.addEdge(10, 11);
        g.addEdge(10, 12);
        g.addEdge(10, 13);
        
        Map<Integer,List<Integer>> components = Graphs.getComponents(g);
        
        assertEquals(components.size(), 3);
    }
}
