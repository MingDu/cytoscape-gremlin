package com.github.jespersm.cytoscape.gremlin.internal;

import com.github.jespersm.cytoscape.gremlin.internal.client.ConnectionParameter;
import com.github.jespersm.cytoscape.gremlin.internal.client.GremlinClient;
import com.github.jespersm.cytoscape.gremlin.internal.client.GremlinClientException;
import com.github.jespersm.cytoscape.gremlin.internal.client.ScriptQuery;
import com.github.jespersm.cytoscape.gremlin.internal.graph.Graph;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * duming 2019/3/20 10:39 AM
 **/
public class JanusGraphTest {
    GremlinClient gremlinClient = new GremlinClient();
    boolean connect = gremlinClient.connect(
            new ConnectionParameter("localhost", 8182, false, false, "g", null, null));

    {
        System.out.println(connect);
    }

    @Test
    public void testSubGraph() throws GremlinClientException {


        Graph graph = gremlinClient.getGraph(ScriptQuery.builder().query("g.V(4248).repeat(__.outE().subgraph('subGraph').inV()).times(2).cap('subGraph').next()").build());
        System.out.println(graph);
    }

    @Test
    public void asynQuery() throws ExecutionException, InterruptedException {
        CompletableFuture<Graph> result = CompletableFuture.supplyAsync(() -> {
            try {
                return gremlinClient.getGraph(
                        ScriptQuery.builder().query("g.V(4248).repeat(__.outE().subgraph('subGraph').inV()).times(2).cap('subGraph').next()").build());
            } catch (GremlinClientException e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
        });
        result.whenComplete((x, y) -> {
            System.out.println(x);
            System.out.println(y);
        });
        result.get();
    }
}
