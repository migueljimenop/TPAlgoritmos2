import java.io.*; 
import java.util.*;
public class Main{
	
	private static String[] divideString(String s){
		//res[0]=descripcion | res[1]=valor | res[2]=costo
		String[] res= new String[3];
		//aux[0]=descripcion | aux[1]=valor/costo
		String[] aux=s.split("\\' ");
		res[0]=aux[0].replace("'","");//eliminacion de la comilla inicial
		String[] aux2=aux[1].split("\\ ");
		res[1]=aux2[0];
		res[2]=aux2[1];
		return res;
	}
	
	public static void accederSprint(LinkedList<Sprint> res){
		int size = res.size();
		int cont = 0;
		while (cont < size){
			Sprint aux = res.get(cont);
			System.out.println("Sprint " + (cont+1) +": ");
			aux.mostrarSprint();
			//System.out.println("");
			cont++;
		}
	}
	
	public static void main(String[] args) throws Exception {
		// Lectura de archivo para obtener la cantidad de tareas.
		FileReader fr = new FileReader("backlog.txt"); 
		BufferedReader br = new BufferedReader(fr); 
		String s;
		int cont = 0; 
		while((s = br.readLine()) != null) { 
	  		cont++;
		}
		// Creacion del heap con la capacidad.
		Heap heap = new Heap(cont); 

		//System.out.println(cont);
		//System.out.println(heap.capacidad);
		//Separa los elementos que se leen del archivo

		//lectura de las lineas del archivo y creacion de las cuadruplas
	 	fr = new FileReader("backlog.txt");
 		br = new BufferedReader(fr); 
		String[] array;
		while((s = br.readLine()) != null) { 
			//System.out.println(s); 
			array = divideString(s);
			int valor = Integer.parseInt(array[1]);
			int costo = Integer.parseInt(array[2]);
			Cuadrupla cp = new Cuadrupla(array[0], valor, costo);
			// Carga del heap.
			heap.insertar(cp);		
		}	

		System.out.println("El heap tiene la longitud de: " + heap.longitud());

		// Generacion de los Sprints
		LinkedList<Sprint> result = new LinkedList<Sprint>();
		while(!heap.esVacia()){
			Sprint spr = new Sprint(7);
			spr.generarSprint(heap);
			result.add(spr);
		}

		// Acceso y muestra de los Sprints
		accederSprint(result);

		fr.close();

		//creacion de nuevo archivo
	}
}