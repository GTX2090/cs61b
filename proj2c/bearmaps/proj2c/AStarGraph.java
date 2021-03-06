package bearmaps.proj2c;

import java.util.List;
import java.util.*;
/**
 * Represents a graph of vertices.
 * Created by hug.
 */
public interface AStarGraph<Vertex> {
    List<WeightedEdge<Vertex>> neighbors(Vertex v);
    double estimatedDistanceToGoal(Vertex s, Vertex goal);
}
