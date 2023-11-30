import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;


public class DGrafo implements Graph<vertex> {
    // Declaramos las variables, arreglos y tablas que vayamos a utilizar
    private HashMap<String, vertex> vertices;
    private ArrayList<edges> connect;
    public static final int inf = Integer.MAX_VALUE;

    public DGrafo() {
        // Constructor de la clase.
        vertices = new HashMap<>();
        connect = new ArrayList<>();
    }

    // Método para agregar un vértice al grafo
    public boolean add(vertex vertex) {
        if (!vertices.containsKey(vertex.getId())) {
            // Agregar el vértice al hashtable
            vertices.put(vertex.getId(), vertex);
            // Vértice agregado exitosamente
            return true;
        }
        // El vértice ya existe en el grafo
        return false;
    }

    // Método para verificar si un vértice existe en el grafo dado su identificador
    public boolean contains(vertex vertex) {
        if (vertices.containsKey(vertex.getId())) {
            // El vértice existe en el grafo
            return true;
        }
        // El vértice no existe en el grafo
        return false;
    }

    // Método para verificar si existe un lado entre dos vértices
    public boolean containsconnect(vertex from, vertex to) {
        // Iterar sobre todos los lados
        for (edges a : connect) {
            // Verificar si los vértices extremos coinciden en cualquier dirección
            if (a.getExtremoInicial().equals(from) && a.getExtremoFinal().equals(to)){
                // Existe un lado entre los vértices
                return true;
            }
        }
        // No existe un lado entre los vértices
        return false;
    }

    public boolean connect(vertex from, vertex to) {
        // Esta función establece la relación entre dos vértices si existe una conexión
        // entre ellos.
        if (!containsconnect(from, to)) {
            edges arista = new edges("" + from.getId() + to.getId() + "", 0, from, to);
            connect.add(arista);
            return true;
        }
        return false;
    }

    public boolean disconnect(vertex from, vertex to) {
        // Esta función elimina la relación entre dos vértices si existe una conexión
        // entre ellos.
        if (contains(from) && contains(to) && containsconnect(from, to)) {
            for (edges a : connect) {
                if (a.getExtremoInicial().equals(from) && a.getExtremoFinal().equals(to)){
                    connect.remove(a);
                    return true;
                }
            }
        }
        return false;
    }

    public List<vertex> getInwardEdges(vertex to) {
        // Esta función devuelve una lista de vértices predecesores que tienen una
        // conexión con el vértice dado.
        List<vertex> inwardEdges = new ArrayList<>();
        for (edges a : connect) {
            if (a.getExtremoFinal().getId().equals(to.getId())) {
                inwardEdges.add(a.getExtremoInicial());
            }
        }
        return inwardEdges;
    }

    public List<vertex> getOutwardEdges(vertex from) {
        // Esta función devuelve una lista de vértices sucesores que tienen una conexión
        // con el vértice dado.
        List<vertex> outwardEdges = new ArrayList<>();
        for (edges a : connect) {
            if (a.getExtremoInicial().getId().equals(from.getId())) {
                outwardEdges.add(a.getExtremoFinal());
            }
        }
        return outwardEdges;
    }

    public List<vertex> getVerticesConnectedTo(vertex vertex) {
        // Esta función devuelve una lista de vértices que tienen una conexión con el
        // vértice dado.
        List<vertex> verticesConnectedTo = new ArrayList<>();
        for (edges a : connect) {
            if (a.getExtremoInicial().getId().equals(vertex.getId())) {
                verticesConnectedTo.add(a.getExtremoFinal());
            } else if (a.getExtremoFinal().equals(vertex)) {
                verticesConnectedTo.add(a.getExtremoInicial());
            }
        }
        return verticesConnectedTo;
    }

    public List<edges> getAllEdges() {
        // Esta función devuelve una lista de todos los lados del grafo.
        return connect;
    }
    public List<vertex> getAllVertices() {
        // Esta función devuelve una lista de todos los vértices del grafo.
        List<vertex> allVertices = new ArrayList<>();
        for (vertex key : vertices.values()) {
            allVertices.add(key);
        }
        return allVertices;
    }

    public boolean remove(vertex vertex) {
        // Esta función elimina un vértice del grafo.
        if (contains(vertex)) {
            vertices.remove(vertex.getId());
            return true;
        }
        return false;
    }

    public int size() {
        // Esta función devuelve el número de vértices en el grafo.
        return vertices.size();
    }

    public Graph<vertex> subgraph(Collection<vertex> vertices) {
        // Esta función devuelve un subgrafo del grafo dado un conjunto de vértices.
        Graph<vertex> subgraph = new DGrafo();
        for (vertex v : vertices) {
            subgraph.add(v);
        }
        for (edges a : connect) {
            if (subgraph.contains(a.getExtremoInicial()) && subgraph.contains(a.getExtremoFinal())) {
                subgraph.connect(a.getExtremoInicial(), a.getExtremoFinal());
            }
        }
        return subgraph;
    }

    @Override
    public String toString() {
        // Este método devuelve una representación en forma de cadena del grafo.
        StringBuilder sb = new StringBuilder();
        // Iterar sobre todos los vertices en el grafo
        for (String a : vertices.keySet()) {
            sb.append("Mondeda: ").append(a).append("\n");
        }
        // Iterar sobre todas las aristas en el grafo
        for (edges l : connect) {
            sb.append(l).append("\n");
        }
        // Devolver la representación en forma de cadena del grafo
        return sb.toString();
    }

    public boolean cargarGrafo(String dirArchivo) {
        // Esta función cargará los datos de un .txt
        try (BufferedReader lista = new BufferedReader(new FileReader(dirArchivo))) {
            String linea;
            // Dividir la línea en datos separados por ' '
            while ((linea = lista.readLine()) != null) {
                String[] datos = linea.split(" ");
                String A = datos[0];
                String B = datos[1];
                float C = Float.parseFloat(datos[2]);
                vertex a1 = new vertex(A);
                vertex a2 = new vertex(B);
                // Agregar el vértices y la conexión al grafo
                add(a1);
                add(a2);
                connect(a1, a2);
                connect.get(connect.size() - 1).setTasa(C);
            }
            return true;
        } catch (IOException | NumberFormatException e) {
            return false;
        }
    }

    public boolean CicloDFS(vertex  inicio,vertex  x, List<vertex> Arreglo){
        // Esta función devuelve un ciclo en el grafo si existe.
        List<vertex> ciclo = Arreglo;
        x.setVisitado(true);
        // Iterar sobre todos los vértices en el grafo
        for (vertex v : getOutwardEdges(x)) {
            // Verificar si el vértice ha sido visitado
            if (!v.getVisitado()) {
                // Verificar si existe un ciclo en el grafo
                v.setVisitado(true);
                if ((!v.getId().equals(inicio.getId()))) {
                    ciclo.add(v);
                    if (CicloDFS(inicio, v, ciclo)){
                    // Devolver el ciclo
                    return false;
                    }
                }
            }
            if(v.getId().equals(inicio.getId())){
                return true;
            }
        }
        // No existe un ciclo en el grafo
        return false;
    }
    // end
}
