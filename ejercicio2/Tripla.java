// Cada Tripla almacena los datos de una actividad del backlog

public class Tripla {
  private String descripcion;
  private int valor; // Su valor estara comprendido entre [1..5]
  private int costo;

  public Tripla (String d, int v, int c){
    this.descripcion = d;
    this.valor=v;
    this.costo=c;
  }

  // Retorna la descripcion de la actividad    
  public String getDescripcion(){
    return this.descripcion;
  }

  // Retorna el valor de la actividad
  public int getValor(){
    return this.valor;
  }

  // Retorna el valor de la actividad
  public int getCosto(){
    return this.costo;
  }
  
  // Modifica la descripcion de la actividad
  public void setDescripcion(String d){
    this.descripcion=d;
  }

  // Modifica el valor de la actividad
  public void setValor(int v){
    this.valor=v;
  }

  // Modifica el costo de la actividad
  public void setCosto(int c){
    this.costo=c;
  }
  

}