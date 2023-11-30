import java.util.ArrayList;
import java.util.List;

public class Arbitrage {
    public static void main(String[] args) {
        DGrafo graph = new DGrafo();
        String archivo = "tasas.txt";
        graph.cargarGrafo(archivo);
        //System.out.println(graph);
        List<vertex> ciclo = new ArrayList<>();
        List<vertex> vertices = graph.getAllVertices();
        List<edges> aristas = graph.getAllEdges();
        ciclo.add(vertices.get(0));
        graph.CicloDFS(vertices.get(0),vertices.get(0),ciclo);
        //System.out.println(ciclo);
        boolean hayciclo = false;
        for (int i = 0; i < aristas.size(); i++) {
            if (ciclo.get(ciclo.size()-1).getId().equals(aristas.get(i).getExtremoInicial().getId()) && ciclo.get(0).getId().equals(aristas.get(i).getExtremoFinal().getId())) {
                hayciclo = true;
            }

        }
        double inicial=1.0;
        double base=1.0;
        if (hayciclo) {
            for (int i = 0; i < ciclo.size(); i++) {
                for (int j = 0; j < aristas.size(); j++) {
                    if (i<ciclo.size()-1 && ciclo.get(i).getId().equals(aristas.get(j).getExtremoInicial().getId()) && ciclo.get(i+1).getId().equals(aristas.get(j).getExtremoFinal().getId())) {
                        inicial = inicial * aristas.get(j).getTasa();
                    } 
                    if (i==ciclo.size()-1 && ciclo.get(i).getId().equals(aristas.get(j).getExtremoInicial().getId()) && ciclo.get(0).getId().equals(aristas.get(j).getExtremoFinal().getId())) {
                        inicial = inicial * aristas.get(j).getTasa();
                    }
                }
            }
            inicial=inicial-base;
            if (inicial>0) {
                System.out.println("DINERO FACIL DESDE TU CASA");
                System.out.println("Hay Ganancia: "+inicial);
            } else {
                System.out.println("DINERO FACIL DESDE TU CASA");
                System.out.println("No hay Ganacia: "+inicial);
            }
        } else {
            System.out.println("TODO GUAY DEL PARAGUAY");
        }

    }
}
