public class Cuadrupla {
  private String descripcion;
  private int valor;
  private int costo;// Su valor estara comprendido entre [1..5]

  public Cuadrupla(String d, int v, int c){
    this.descripcion = d;
    this.valor=v;
    this.costo=c;
  }

  public String getDescripcion(){
    return this.descripcion;
  }

  public int getValor(){
    return this.valor;
  }

  public int getCosto(){
    return this.costo;
  }
  
  public void setDescripcion(String d){
    this.descripcion=d;
  }

  public void setValor(int v){
    this.valor=v;
  }

  public void setCosto(int c){
    this.costo=c;
  }
  

}