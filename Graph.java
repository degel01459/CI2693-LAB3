import java.util.List;
import java.util.Collection;

public interface Graph<v> {
    public boolean add(v vertex);
    public boolean connect(v from, v to);
    public boolean disconnect(v from, v to);
    public boolean contains(v vertex);
    public List<vertex> getInwardEdges(v to);
    public List<vertex> getOutwardEdges(v from);
    public List<vertex> getVerticesConnectedTo(v vertex);
    public List<vertex> getAllVertices();
    public boolean remove(v vertex);
    public int size();
    public Graph<v> subgraph(Collection<v> vertices);
}