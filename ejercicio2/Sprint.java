import java.util.LinkedList;
import java.util.Queue;
import java.util.Iterator;

public class Sprint {

	private int cantDias;
	public LinkedList<Tripla> list;
	private Queue<Tripla> queue;
  
	public Sprint(int cd){
		this.cantDias = cd;
		this.list = new LinkedList<Tripla>();
		this.queue = new LinkedList<Tripla>(); // La implementacion de la cola sera con listas encadenadas
	}
  
	public void generarSprint(Heap h) throws HeapExcepcion{
		if(h.esVacia())
			throw new HeapExcepcion("El Heap esta vacio, no se puede construir el Sprint");
		else{
			int sum=0;
			while((sum<=this.cantDias) && !h.esVacia()){
			Tripla aux=h.remueveMinimo();
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

	public void mostrarSprint(){
		Iterator<Tripla> i = list.iterator();
		while(i.hasNext())
			System.out.println(i.next().getDescripcion()+"\t");
	}
}
