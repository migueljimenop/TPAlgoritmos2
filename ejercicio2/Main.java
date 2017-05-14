import java.util.*;
import java.io.*; 

public class Main{
  public static void main(String[] args) {
  	//lectura del archivo
  	//creacion del heap
	Heap heap= new Heap(20); 
	//lectura de las lineas del archivo y creacion de las cuadruplas
	//cargar el heap
	//generacion de los Sprints
	//mostrar los Sprints(ciclo)
	//creacion de nuevo archivo
	//Formato : descripcion-valor-costo
	String[] res1=divideString("'Como programador quiero ser el Naza' 5 4");


  }

  //Separa los elementos que se leen del archivo
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


}