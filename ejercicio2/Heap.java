//declaracion de la clase Heap, extiende a a la clase Grafo
public class Heap {
  //declaracion de los elementos que usa la clase
  private Cuadrupla arr[];
  private int ultimo;//variable que lleva la ultima posicion
  private int capacidad;//tamaño mximo del arreglo
 
  //creador de la clase
  public Heap(int cap) {
      arr = new Cuadrupla [cap + 1];
      ultimo = 0;
      capacidad = cap;
  }
  //devuelve la longitud del arreglo hasta ese momento
  public int longitud() {
      return ultimo;
  }
  //devuelve true si la longitud del arreglo es 0
  public boolean esVacia() {
      return (longitud() == 0);
  }

  //devuelve el vertice que se encuentra al principio
  public Cuadrupla minimo() throws HeapExcepcion {
      if (esVacia())
          throw new HeapExcepcion("El Heap esta vacio");//si es vacia tira excepcion
      else
          return  arr[1];
  }

  //toma dos vertices y compara cual de sus vertices tiene el mas cercano con menos costo, devuelve 
  private int compara(Cuadrupla x, Cuadrupla y) {
    int aux = x.getValor();
    int aux2 = y.getValor();
    if (aux>aux2) 
      return (-1);
    if (aux==aux2)
      return (0);
    return (1);
  }
  
  //incerta un elemento al final del arreglo (encola)
  public void insertar(Cuadrupla v) throws HeapExcepcion {
    if (longitud() == capacidad)
        throw new HeapExcepcion("Heap lleno");
    else{
        ultimo++; //incrementa variable que lleva el indice del ultimo
        arr[ultimo] = v;
        moverArriba(); //reacomoda el Heap
    }       
  }

  //saca el primer elemento de la cola (desencola), ya qjue es el elemento con mas prioridad
  public Cuadrupla remueveMinimo() throws HeapExcepcion {
      if (esVacia())
          throw new HeapExcepcion("El Heap esta vacio");
      else {
          Cuadrupla minimo = minimo();
          arr[1] = arr[ultimo];
          ultimo--;
          moverAbajo();//reacomoda el Heap
          return minimo;
      }
  }

  //reacomoda el Heap, haciendo intercambios en caso de ser necesario ya que se encolo
  private void moverArriba(){
      int i = longitud();
      while (i > 1){
          int aux = i / 2;
          if (compara(arr[i], arr[aux]) >= 0)
              break;//termina el ciclo si aux es menor o igual al elemento corriente
          swap(i,aux);
          i = aux;
      }       
  }
  
  //comportamiento similar al anterior, reacomoda el Heap ya que se desencolo
  private void moverAbajo(){
      int i = 1;
      while (true){
          int aux = i*2;
          if (aux > longitud())
              break;
          if (aux + 1 <= longitud()){
            aux = findminimo(aux, aux + 1);
          }
          if (compara(arr[i],arr[aux]) <= 0 )
              break;
          swap(i,aux);
          i = aux;
      }
  }

  //intercambia dos valores
  private void swap(int i, int j) {
      Cuadrupla v = arr[i];
      arr[i] = arr[j];
      arr[j] = v;
  }

  //determina el minimo entre dos posiciones pasadas como parametro, devuelve la posicion con la menor 
  private int findminimo(int aux1, int aux2) {
       if (compara(arr[aux1],arr[aux2]) <= 0)
          return aux1;
      else
          return aux2;
  }

  /*
  *Este metodo toma como parametro la altura 
  *(es a modo de pruebas, puede NO mostar todos los elementos)
  */
  public void muestraHeap(int altura){
    if (!this.esVacia()){
      System.out.println("                   raiz: "+(arr[1].getDescripcion()));
      for (int i=1 ; i<altura; i++) {
        System.out.print("hi de "+(arr[i].getDescripcion())+" es: "+(arr[(2*i)].getDescripcion())+" | ");
        System.out.print("hd de "+(arr[i].getDescripcion())+" es: "+(arr[(2*i+1)].getDescripcion()));
        System.out.println("");
      }
    }else{
      System.out.println("El heap se encuentra vacio");
    }
  }

  public static void main(String[] args) {

    Cuadrupla c1= new Cuadrupla("c1",9,3);
    Cuadrupla c2= new Cuadrupla("c2",7,2);
    Cuadrupla c3= new Cuadrupla("c3",5,5);
    Cuadrupla c4= new Cuadrupla("c4",4,2);
    Cuadrupla c5= new Cuadrupla("c5",2,1);
    Cuadrupla c6= new Cuadrupla("c6",8,4);

    int cant=6;

    Heap h =new Heap(cant);
    h.insertar(c1);
    h.insertar(c2);
    h.insertar(c3);
    h.insertar(c4);
    h.insertar(c5);
    h.insertar(c6);

    System.out.println("===========");
    h.muestraHeap(3);
    System.out.println("===========");

    Cuadrupla arr2[]= new Cuadrupla [cant];
    for (int i=0;i<cant ;i++ ) {
      arr2[i]=h.remueveMinimo();
      System.out.print(" "+(arr2[i].getDescripcion())+": "+(arr2[i].getValor())+" ->");
    }
    System.out.println("");
    System.out.println("******************");
    h.muestraHeap(3);
    System.out.println("******************");

    

  }

 

}



