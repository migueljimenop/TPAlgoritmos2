import java.util.LinkedList;
import java.util.Queue;

public class Sprint {

  private int cantDias;
  private LinkedList<Cuadrupla> list;
  private Queue<Cuadrupla> queue;
  public Sprint(int cd){
    this.cantDias=cd;
    this.list =new LinkedList<Cuadrupla>();
    this.queue= new LinkedList<Cuadrupla>();
  }
  
  public void addCuadrupla(Heap h) throws HeapExcepcion{
    if(h.esVacia())
      throw new HeapExcepcion("El Heap esta vacio, no se puede construir el Sprint");
    else{
      int sum=0;

      while((sum<=this.cantDias) && !h.esVacia()){
        Cuadrupla aux=h.remueveMinimo();
        int eval=sum+(aux.getCosto());
        if(eval<=this.cantDias){
          this.list.add(aux);
          sum=eval;  
        }else
          this.queue.add(aux);
      }
      if (!this.queue.isEmpty()) 
        reacomodaHeap(h);
    }
  }

  private void reacomodaHeap(Heap h){
    while(!this.queue.isEmpty()){
      h.insertar(queue.remove());
    }
  }
}
