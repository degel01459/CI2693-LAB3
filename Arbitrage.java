public class Arbitrage {
    public static void main(String[] args) {
        DGrafo graph = new DGrafo();
        String archivo = "tasas.txt";
        graph.cargarGrafo(archivo);
    }
}