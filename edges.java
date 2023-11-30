public class edges extends connect {
  // Variables de Arista.
  private vertex extremoInicial;
  private vertex extremoFinal;

  // Crear Arista.
  public edges(String id, float Tasa, vertex extremoInicial, vertex extremoFinal) {
    super(id, Tasa);
    this.extremoInicial = extremoInicial;
    this.extremoFinal = extremoFinal;
  }

  // Obtener Extremo1.
  public vertex getExtremoInicial() {
    return this.extremoInicial;
  }

  // Obtener Extremo2.
  public vertex getExtremoFinal() {
    return this.extremoFinal;
  }

  // Mostrar la arista.
  @Override
  public String toString() {
    return "Moneda: (" + extremoInicial.getId() + " ->" + extremoFinal.getId() + ") Tasa: "
        + getTasa();
  }
}
