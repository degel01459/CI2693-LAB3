public class vertex {
      // variables de la clase.
      private String id;
      private float peso;
      private boolean visitado;

      // Constructor de la clase.
      public vertex(String id) {
            this.id = id;
            this.peso = -1;
            this.visitado = false;
      }

      // Establece el estado de visitado del vértice.
      public boolean setVisitado(boolean visitado) {
            this.visitado = visitado;
            return this.visitado;
      }

      // Obtiene el estado de visitado del vértice.
      public boolean getVisitado() {
            return this.visitado;
      }

      // obtener peso del vertice.
      public float getPeso() {
            return this.peso;
      }

      // obtener id del vertice.
      public String getId() {
            return this.id;
      }

      public float setPeso(int p){
            this.peso = p;
            return this.peso;
      } 

      // Devuelve una representación en forma de cadena del vértice.
      public String toString() {
            return getId();
      }
}