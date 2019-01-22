package com.github.jespersm.cytoscape.gremlin.internal.ui.shortestpath;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.task.NetworkTaskFactory;
import org.cytoscape.work.TaskIterator;

import com.github.jespersm.cytoscape.gremlin.internal.Services;
import com.github.jespersm.cytoscape.gremlin.internal.tasks.ShortestPathTask;

public class ShortestPathMenuAction implements NetworkTaskFactory {

    private Services services;

    public ShortestPathMenuAction(Services services) {
        this.services = services;
    }

    @Override
    public TaskIterator createTaskIterator(CyNetwork network) {
        if (this.isReady(network)) {
            return new TaskIterator(new ShortestPathTask(services, network));
        } else {
            return null;
        }
    }

    @Override
    public boolean isReady(CyNetwork arg0) {
        return arg0 != null && arg0.getNodeCount() > 0;
    }

    public static ShortestPathMenuAction create(Services services) {
        return new ShortestPathMenuAction(services);
    }

}
