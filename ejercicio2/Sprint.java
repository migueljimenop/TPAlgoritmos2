import java.util.LinkedList;
import java.util.Queue;
import java.util.Iterator;

public class Sprint {

	private int cantDias;
	public LinkedList<Cuadrupla> list;
	private Queue<Cuadrupla> queue;
  
	public Sprint(int cd){
		this.cantDias = cd;
		this.list = new LinkedList<Cuadrupla>();
		this.queue = new LinkedList<Cuadrupla>();
	}
  
	public void generarSprint(Heap h) throws HeapExcepcion{
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

	public void mostrarSprint(){
		Iterator<Cuadrupla> i = list.iterator();
		while(i.hasNext())
			System.out.println(i.next().getDescripcion()+"\t");
	}

	/*
	public static void main(String[] args) {
		Cuadrupla c1 = new Cuadrupla("Actividad 1",9,3);
		Cuadrupla c2 = new Cuadrupla("Actividad 2",7,2);
		Cuadrupla c3 = new Cuadrupla("Actividad 3",5,5);
		Cuadrupla c4 = new Cuadrupla("Actividad 4",4,2);
		Cuadrupla c5 = new Cuadrupla("Actividad 5",2,1);
		Cuadrupla c6 = new Cuadrupla("Actividad 6",8,2);

		int cant = 6;

		Heap h =new Heap(cant);

		h.insertar(c1);
		h.insertar(c2);
		h.insertar(c3);
		h.insertar(c4);
		h.insertar(c5);
		h.insertar(c6);
		
		Sprint spr = new Sprint(7);
		Sprint spr2 = new Sprint(7);
		Sprint spr3 = new Sprint(7);

		spr.generarSprint(h);
		spr.mostrarSprint();

		spr2.generarSprint(h);
		spr2.mostrarSprint();

		spr3.generarSprint(h);
		spr3.mostrarSprint();		

	}*/
}
