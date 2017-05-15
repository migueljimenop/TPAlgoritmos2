
public class Heap {
  //Declaracion de los elementos que usa la clase
  private Tripla arr[];
  private int ultimo; // Variable que lleva la ultima posicion cargada
  private int capacidad; // Tamaño maximo del arreglo
 
  // Creador de la clase
  public Heap(int cap) {
      arr = new Tripla [cap + 1];
      ultimo = 0;
      capacidad = cap;
  }
  // Devuelve la longitud del arreglo hasta ese momento
  public int longitud() {
      return ultimo;
  }
  // Devuelve true si la longitud del arreglo es 0
  public boolean esVacia() {
      return (longitud() == 0);
  }

  // Devuelve la tripla que se encuentra al principio (raiz)
  public Tripla minimo() throws HeapExcepcion {
      if (esVacia())
          throw new HeapExcepcion("El Heap esta vacio");//si es vacia tira excepcion
      else
          return  arr[1];
  }

  // Toma dos Triplas y compara cual  tiene mayor valor
  private int compara(Tripla x, Tripla y) {
    int aux = x.getValor();
    int aux2 = y.getValor();
    if (aux>aux2) 
      return (-1);
    if (aux==aux2)
      return (0);
    return (1);
  }
  
  // Inserta un elemento al final del arreglo (encola)
  public void insertar(Tripla v) throws HeapExcepcion {
    if (longitud() == capacidad)
        throw new HeapExcepcion("Heap lleno");
    else{
        ultimo++; // Incrementa variable que lleva el indice del ultimo
        arr[ultimo] = v;
        moverArriba(); // Reacomoda el Heap
    }       
  }

  // Saca el primer elemento de la cola (desencola desde la raiz), ya que es el elemento con mas prioridad
  public Tripla remueveMinimo() throws HeapExcepcion {
      if (esVacia())
          throw new HeapExcepcion("El Heap esta vacio");
      else {
          Tripla minimo = minimo();
          arr[1] = arr[ultimo];
          ultimo--;
          moverAbajo();//reacomoda el Heap
          return minimo;
      }
  }

  // Reacomoda el Heap, haciendo intercambios en caso de ser necesario ya que se encolo
  private void moverArriba(){
      int i = longitud();
      while (i > 1){
          int aux = i / 2;
          if (compara(arr[i], arr[aux]) >= 0)
              break; // Termina el ciclo si aux es menor o igual al elemento corriente
          swap(i,aux);
          i = aux;
      }       
  }
  
  // Comportamiento similar al anterior, reacomoda el Heap ya que se desencolo
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

  // Intercambia dos valores
  private void swap(int i, int j) {
      Tripla v = arr[i];
      arr[i] = arr[j];
      arr[j] = v;
  }

  // Determina el minimo entre dos posiciones pasadas como parametro, devuelve la posicion con la menor 
  private int findminimo(int aux1, int aux2) {
       if (compara(arr[aux1],arr[aux2]) <= 0)
          return aux1;
      else
          return aux2;
  }

}



