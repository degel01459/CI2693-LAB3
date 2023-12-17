import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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

    public vertex getVertex(String id) {
        // Esta función devuelve el vértice con el id dado si existe en el grafo.
        if (vertices.containsKey(id)) {
            return vertices.get(id);
        }
        return null;
    }

    // Método para verificar si un vértice existe en el grafo dado su identificador
    public boolean contains(vertex vertex) {
        if (vertex == null) {
            // El vértice no existe en el grafo
            return false;
        } 
        else if (vertex != null) {
            if (vertices.containsKey(vertex.getId())) {
                // El vértice existe en el grafo
                return true;
            }
            // El vértice no existe en el grafo
        }
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
    public double getTasa(vertex from, vertex to) {
        // Esta función devuelve el peso de la arista entre dos vértices si existe una
        // conexión entre ellos.
        if (containsconnect(from, to)) {
            for (edges a : connect) {
                if (a.getExtremoInicial().equals(from) && a.getExtremoFinal().equals(to)){
                    return a.getTasa();
                }
            }
        }
        return inf;
    }

    public Boolean setTasa(vertex from, vertex to, double x) {
        // Esta función establece el peso de la arista entre dos vértices si existe una
        // conexión entre ellos.
        if (containsconnect(from, to)) {
            for (edges a : connect) {
                if (a.getExtremoInicial().equals(from) && a.getExtremoFinal().equals(to)){
                    a.setTasa(x);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean connect(vertex from, vertex to, double x) {
        // Esta función establece la relación entre dos vértices si existe una conexión
        // entre ellos.
        if (contains(from) && contains(to) && !from.equals(to) && !containsconnect(from, to)) {
            edges arista = new edges("" + from.getId() + to.getId() + "", x, from, to);
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
        ArrayList<Double> orden = new ArrayList<>();
        double cambioalto = 0.0;
        for (edges a : connect) {
            if (a.getExtremoInicial().getId().equals(from.getId())) {
                if (a.getTasa()>=cambioalto){
                    orden.add(a.getTasa());
                }
            }
        }
        //orden de mayor a menor
        orden.sort((o1, o2) -> o2.compareTo(o1));
        //System.out.println(orden);
        for (double x : orden) {
            for (edges a : connect) {
                if (a.getExtremoInicial().getId().equals(from.getId())) {
                    if (a.getTasa()==x){
                        outwardEdges.add(a.getExtremoFinal());
                    }
                }
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
        List<vertex> vertices = new ArrayList<>();
        for (String a : this.vertices.keySet()) {
            vertices.add(this.vertices.get(a));
        }
        return vertices;
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
    public void cargarGrafo(String dirArchivo) {
        // Esta función cargará los datos de un .txt
        try (BufferedReader br = new BufferedReader(new FileReader(dirArchivo))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(" ");
                String currency1 = tokens[0];
                String currency2 = tokens[1];
                Double rate = Double.parseDouble(tokens[2]);
                add(new vertex(currency1));
                add(new vertex(currency2));
                // Agregar conexión al grafo
                connect(vertices.get(currency1), vertices.get(currency2), (rate));
                System.out.println("Agregando conexión entre " + currency1 + " y " + currency2 + " con tasa " + rate);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Double> TasasVertex = new ArrayList<>();
        ArrayList<Double> tasas = new ArrayList<>();
        double tasaAcomulada = 1.0;
        ArrayList<vertex> Ciclo = new ArrayList<>();
        for (vertex v : vertices.values()) {
            //System.out.println(v.getId()+" "+tasas+" "+tasaAcomulada+" "+Ciclo+" "+TasasVertex);
            tasaAcomulada = checkArbitrage(v, v, tasas, tasaAcomulada, Ciclo);
            //System.out.println(v.getId()+" "+tasas+" "+tasaAcomulada+" "+Ciclo+" "+TasasVertex);
            if (Ciclo.get(0)!=Ciclo.get(Ciclo.size()-1)){
                tasaAcomulada = 1.0;
            }
            TasasVertex.add(tasaAcomulada);
            tasaAcomulada = 1.0;
            tasas.clear();
            Ciclo.clear();
            for (vertex v2 : vertices.values()) {
                v2.setVisitado(false);
            }
        }
        boolean arbitragePossible = false;
        for (int i = 0; i < TasasVertex.size(); i++) {
            //System.out.println(TasasVertex.get(i));
            if (TasasVertex.get(i)>1.0){
                arbitragePossible = true;
                System.out.println("DINERO FACIL DESDE TU CASA");
                break;
            }
        }
        if (!arbitragePossible){
            System.out.println("TODO GUAY DEL PARAGUAY");
        }
    }

    private double checkArbitrage(vertex x, vertex inicial, ArrayList<Double> tasas, double tasaAcomulada, ArrayList<vertex> Ciclo) {
        if (x == inicial && tasas.size()>1){
            Ciclo.add(x);
            x.setVisitado(true);
            //System.out.println("aca2: "+x.getId()+" "+tasas+" "+tasaAcomulada+" "+Ciclo+" ");
            return tasaAcomulada;
        }
        Ciclo.add(x);
        List<vertex> cambios = getOutwardEdges(x);
        //System.out.println(cambios);
        for (vertex cambio : cambios) {
            vertex y = cambio;
            if (!y.getVisitado()){
                System.out.println("aca1: "+x.getId()+" "+y.getId()+" "+tasas+" "+tasaAcomulada+" "+Ciclo+" ");
                y.setVisitado(true);
                double tasaActual = getTasa(x, y);
                tasas.add(tasaActual);
                tasaAcomulada = tasaAcomulada*tasaActual;
                tasaAcomulada = checkArbitrage(y, inicial, tasas, tasaAcomulada, Ciclo);
                return tasaAcomulada;
            }
        }
        return tasaAcomulada;
    }
    // end
}
