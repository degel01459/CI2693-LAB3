public class Arbitrage {
    public static void main(String[] args) {
        DGrafo graph = new DGrafo();
        String archivo = "tasas5.txt";
        graph.cargarGrafo(archivo);
    }
}