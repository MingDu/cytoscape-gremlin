package com.github.jespersm.cytoscape.gremlin.internal;

import com.github.jespersm.cytoscape.gremlin.internal.client.ConnectionParameter;
import com.github.jespersm.cytoscape.gremlin.internal.client.GremlinClient;
import com.github.jespersm.cytoscape.gremlin.internal.client.GremlinClientException;
import com.github.jespersm.cytoscape.gremlin.internal.client.ScriptQuery;
import com.github.jespersm.cytoscape.gremlin.internal.graph.Graph;
import org.junit.Test;

/**
 * duming 2019/3/20 10:39 AM
 **/
public class JanusGraphTest {
    @Test
    public void testSubGraph() throws GremlinClientException {
        GremlinClient gremlinClient = new GremlinClient();
        boolean connect = gremlinClient.connect(
                new ConnectionParameter("localhost", 8182, false, false, "g", null, null));
        System.out.println(connect);
        Graph graph = gremlinClient.getGraph(ScriptQuery.builder().query("g.V(4248).repeat(__.outE().subgraph('subGraph').inV()).times(2).cap('subGraph').next()").build());
        System.out.println(graph);
    }
}
