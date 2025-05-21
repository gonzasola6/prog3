package ProgramacionIII.tp4;

import java.util.Iterator;



public class GrafoDirigido<T> implements Grafo<T> {

	private HashMap<Integer, LinkedList<Arco<T>> vertices; //Clave: el ID de un vértice. //Valor: una lista de arcos salientes desde ese vértice.
	//Arco<T> representa una arista dirigida, con al menos un método getVerticeDestino() que devuelve el vértice destino del arco.
	
	public GrafoDirigido(){
		this.vertices= new HashMap();
	}
	
	@Override
	public void agregarVertice(int verticeId) {
		if (vertices.containsKey(verticeId)){
			System.out.println("El vertice "+ verticeId + " ya se ga agregado" );
		} else {
			vertices.put(verticeId, new LinkedList<Arco<T>>());
		}
	}

	@Override
	public void borrarVertice(int verticeId) { //eliminar un vértice del grafo, junto con todas las conexiones hacia él (es decir, eliminarlo como destino en cualquier arco
		if (!vertices.containsKey(verticeId){
			System.out.println("El vertice " + verticeId + " no existe");
		}else{
			//eliminar el vertice de los conjuntos adyacentes de los demas vertices
			for (Integer vecino: vertices.keySet()){ //Recorre todos los vértices del grafo.
				LinkedList<Arco<T>> arcos = vertices.get(vecino); //Para cada uno, obtiene su lista de arcos salientes
				arcos.removeIf(arco->arco.getVerticeDestino()= verticeId); //removeIf(...) para eliminar cualquier arco que termine en el vértice que queremos borrar.
		}
		//Eliminar el vertice
		vertices.remove(verticeId); //elimina el propio vértice del mapa (y con eso también sus arcos salientes).
		}
	}
	//Al borrar un vértice, se eliminan sus arcos entrantes y salientes, pero no se crean conexiones nuevas automáticamente.
	//La estructura del grafo refleja conexiones explícitas.
	//Si eliminás un nodo (vértice), estás aceptando que ya no existe ni como punto de paso ni como destino.
	//Cualquier "reconexión" automática cambiaría la topología del grafo, lo cual no es correcto salvo que el problema lo pida explícitamente.

	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		if (!vertices.containsKey(verticeId1) || !vertices.containsKey(verticeId2){
			System.out.println("El grafo no coniene almenos uno de los vertices");
		}else{
			vertices.get(verticeId).add(New Arco<T>(verticeId1, verdiceId2, etiqueta);
		}
	}

	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		if (!vertices.containsKey(verticeId1) || !vertices.containsKey(verticeId2)){
			System.out.println("El grafo no contiene al menos uno de los vértices."");
		}else{
		//eliminar el arco de la lista de adyacencia de verticeId1
		LinkedList<Arco<T>> arcos = vertices.get(verticeId1);
		arcos.removeIf(arco -> arco.getVerticeDestino() == verticeId2); // es un método de las colecciones de Java que elimina los elementos que cumplen con cierta condición.
		}
	}

	@Override
	public boolean contieneVertice(int verticeId) {
		return vertices.containsKey(verticeId);
	}

	@Override
	public boolean existeArco(int verticeId1, int verticeId2) { //verificar si existe un arco desde un vértice verticeId1 hacia otro vértice verticeId2.
		if (!vertices.containsKey(verticeId1) || !vertices.containsKey(verticeId2)) {
       			System.out.println("El grafo no contiene al menos uno de los vértices.");
            		return false; //no puede existir el arco si faltan los vértices.
		} else {
			LinkedList<Arco<T>> arcos = vertices.get(verticeId1); //Si los vértices existen, se recupera la lista de arcos salientes desde verticeId

			for (Arco<T> arco : arcos) { //Se recorre cada arco desde verticeId1
				if (arco.getVerticeDestino() == verticeId2){ //Si alguno de esos arcos apunta a verticeId2, entonces existe el arco, y se retorna true.
					return true;
				}
			}
			return false;
		}
	}

	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int cantidadVertices() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int cantidadArcos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<Integer> obtenerVertices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		// TODO Auto-generated method stub
		return null;
	}

}
