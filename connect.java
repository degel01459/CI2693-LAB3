public abstract class connect {
  // Clase abstracta

  // Propiedades de la clase
  private String id;
  private double tasa;
  private boolean visitado;

  public connect(String id, float tasa) {
    // Constructor de la clase abstracta
    this.id = id;
    this.tasa = tasa;
    this.visitado = false;
  }

  public String getId() {
    // obtener el id
    return id;
  }

  public double getTasa() {
    // Obtener el tasa
    return tasa;
  }

  public double setTasa(double x) {
    // Cambiar el Tasa
    this.tasa = x;
    return tasa;
  }
  public boolean setVisitado(boolean visitado) {
    // Establecer el estado de visitado
    this.visitado = visitado;
    return this.visitado;
  }
  public boolean getVisitado() {
    // Obtener el estado de visitado
    return this.visitado;
  }

  public abstract String toString();
}