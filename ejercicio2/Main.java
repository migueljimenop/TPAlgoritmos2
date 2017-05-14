import java.io.*; 
public class Main{
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

		//lectura de las lineas del archivo y creacion de las cuadruplas
	 	fr = new FileReader("backlog.txt");
 		br = new BufferedReader(fr); 
		String[] array;
		while((s = br.readLine()) != null) { 
			//System.out.println(s); 
			array = divideString(s);
			int valor = (int) array[1];
			int costo = (int) array[2];
			Cuadrupla cp = new Cuadrupla(array[0], valor, costo);
			// Carga del heap.
			heap.insertar(cp);		
		}	

		System.out.println(heap.longitud());

		//generacion de los Sprints
		//mostrar los Sprints(ciclo)

		fr.close();

		//creacion de nuevo archivo
		}
}