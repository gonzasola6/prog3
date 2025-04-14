public class BinarySearchTree {
	
	private TreeNode<Integer> root;
	
	public BinarySearchTree() {
		this.root=null;
	}
	

    public Integer getRoot() {
        return root != null ? root.getValue() : null;
    }
    
    public boolean isEmpty() {
        return root == null;
    }

    public void add(Integer value) {
        if (root == null) {
            root = new TreeNode<Integer>(value);
        } else {
            add(root, value);
        }
    }

    private void add(TreeNode<Integer> node, Integer value) {
        if (value < node.getValue()) {
            if (node.getLeft() == null)
                node.setLeft(new TreeNode<Integer>(value));
            else
                add(node.getLeft(), value);
        } else if (value > node.getValue()) {
            if (node.getRight() == null)
                node.setRight(new TreeNode<Integer>(value));
            else
                add(node.getRight(), value);
        }
    }
    
    public boolean hasElem(Integer value) {
    	return hasElem(root,value);
    	//Es el punto de entrada para quien usa la clase.

    	//No requiere saber cómo funciona internamente el árbol ni pasarle la raíz.

    	//Solo se necesita pasar el valor a buscar.
    }
    
    private boolean hasElem(TreeNode<Integer> actual, Integer value) {

	    if (actual == null) {
	      return false;
	    }

	    if (actual.getValue().equals(value)) {
	      return true;
	    }

	    if (value < actual.getValue()) {
	    	  return hasElem(actual.getLeft(), value);
	    	} else {
	    	  return hasElem(actual.getRight(), value);
	    	}
	  }
    //Este sí trabaja con la estructura interna del árbol.

    //Permite implementar la recursión sobre nodos, partiendo de root y bajando hacia left o right
    
    
    //Este patrón es muy útil para evitar que el usuario externo tenga que preocuparse por cómo se navega 
    //el árbol. Es una forma de encapsular la lógica recursiva interna y hacer que la clase sea fácil de usar y segura.
    
    //Método público: simple, para que el usuario diga "quiero saber si este valor está".

    //Método privado: recursivo, necesita el nodo actual y el valor, porque es el que hace el trabajo real.
    
    
    //ELIMINAR NODO
    
    //CASOS POSIBLES:
    
    //El nodo a eliminar no tiene hijos (es una hoja).

    //El nodo a eliminar tiene un solo hijo.

    //El nodo a eliminar tiene dos hijos: se reemplaza por el menor valor del subárbol derecho (o el mayor del izquierdo).
    
    public boolean delete(Integer value) {
    	if (isEmpty() || !hasElem(value)) { //Chequea si el árbol está vacío o si el elemento no existe (hasElem(value)).
    		return false;					//
    	}
    	
    	root = delete(root, value); //Si el valor sí existe, llama al método privado delete(root, value) y le asigna su resultado a root.
    									//Esto es clave si el nodo eliminado fuera la raíz, ya que la raíz puede cambiar.))
    	return true;
    }
    
    private TreeNode<Integer> delete(TreeNode<Integer> actual, Integer value) {
        if (actual == null) { //Si actual es null, significa que llegamos a un subárbol vacío: no hay nada que hacer, se retorna null.
          return null;
        }

        if (value < actual.getValue()) {
          actual.setLeft(delete(actual.getLeft(), value));
        } else if (value > actual.getValue()) {
          actual.setRight(delete(actual.getRight(), value));
        } else {
          // es una hoja
          if (actual.getLeft() == null && actual.getRight() == null) { //
            return null; //Si el nodo a eliminar no tiene hijos, se retorna null.
            			//En el paso anterior de la recursión, esto hará que el padre lo "desconecte" (lo elimine).
          }

          // tiene un solo nodo
          if (actual.getLeft() == null) {
            return actual.getRight(); //Si tiene solo hijo derecho, se retorna ese hijo: el padre del nodo lo conectará directamente. lo mismo con el izq
          }

          if (actual.getRight() == null) {
            return actual.getLeft();
          } //Esto mantiene la estructura del árbol correcta, simplemente "saltándose" el nodo eliminado.

          // tiene dos nodos
          TreeNode<Integer> nuevo = getMinNode(actual.getRight());
          actual.setValue(nuevo.getValue());
          actual.setRight(delete(actual.getRight(), nuevo.getValue()));
          
          //Si el nodo tiene dos hijos, buscamos el sucesor in-order:

          //Es el menor valor del subárbol derecho (el nodo más a la izquierda del lado derecho).
          
          //Reemplazamos el valor del nodo actual con ese valor mínimo.

          //Eliminamos ese nodo duplicado (el que estaba más abajo).
        }

        return actual; //Esto asegura que al volver en la recursión se mantengan bien conectados todos los nodos (ya sea que el hijo haya sido eliminado o no).
     }
    
    private TreeNode<Integer> getMinNode(TreeNode<Integer> start) {
        if (start.getLeft() == null) {
          return start;
        }

        return getMinNode(start.getLeft());
      }

	private TreeNode<Integer> getMaxNode(TreeNode<Integer> start) {
		if (start.getRight() == null) {
      return start;
    }

    	return getMaxNode(start.getRight());
	}
	
	
	
	
	
	public int getHeight() {
		return getHeight(root)-1; //Le resta 1 al resultado final, porque el 
									//método recursivo considera la raíz como nivel 1, pero por 
									//convención muchas veces se toma la altura como cantidad de aristas, no de nodos, 
									//por eso se ajusta con -1.
	}



	
	//Un árbol con solo la raíz tiene altura 0.
	
	//Si hay un nodo raíz y un hijo (ya sea a la izquierda o derecha), la altura es 1.

	//Y así sucesivamente.



	private int getHeight(TreeNode<Integer> actual) {
		int sumLeft = 1, sumRight = 1;// Porque el nodo actual cuenta como un nivel, y vamos a ir sumando sobre eso si tiene hijos.
		
		if (actual.getLeft()!=null) {
			sumLeft+=getHeight(actual.getLeft());//Si el nodo tiene hijo izquierdo, se llama recursivamente a getHeight sobre ese hijo, y se suma al total de sumLeft.
		}
		
		if (actual.getRight()!=null){
			sumRight+=getHeight(actual.getRight());//mismo del lado derecho
		}
		
		return Math.max(sumLeft, sumRight);//Finalmente, devuelve la mayor de las dos alturas: izquierda o derecha.

											//Porque la altura del árbol es el camino más largo desde la raíz hasta una hoja.
	}


	public void printPosOrder(){ // La complejidad es O(n), ya que recorre todos los nodos del árbol.
		printPostOrder(this.root);
		System.out.println();
	}

	private void printPosOrder(TreeNode node){
		if (node!=null){
			printPosOrder(this.getLeft());
			printPosOrder(this.getRight());
			System.out.print(node.getValue()+"");
		}
	}


	// La complejidad es O(n), ya que recorre todos los nodos del árbol.
	public void printPreOrder(){
		printPreOrder(this.root);
		System.out.println();
	}

	private void printPreOrder(TreeNode node){
		if (node!=null){
			System.out.print(node.getValue()+"");
			printPreOrder(this.getLeft());
			printPreOrder(this.getRight());
		}
	}


	// La complejidad es O(n), ya que recorre todos los nodos del árbol.
	public void printInOrder(){
		printInOrder(this.root);
		System.out.println();
	}

	private void printInOrder(TreeNode node){
		if (node!=null){
			printInOrder(this.getLeft());
			System.out.print(node.getValue()+"");
			printInOrder(this.getRight());
		}
	}


	public List<Integer> getLongestBranch(){
		List<Integer> longestBranch = new ArrayList<>();
		List<Integer> currentBranch = new ArrayList<>();
		getLongestBranch(root, longestBranch, currentBranch, 0);
		return longestBranch();
	}

	private ArrayList<> getLongestBranch(TreeNode node, ArrayList longestBranch, ArrayList currentBranch, int level){
		if (node!=null){
			currentBranch.add(node.getValue());

			if (node.getLeft()==null && node.getRight()==null){
				if(currentBranch.size()>longestBranch.size()){
					longestBranch.clear();
					longestBranch.addAll(currentBranch);
				}
			} else {
				getLongestBranch(node.getLeft(), longestBranch, currentBranch, level +1);
				getLongestBranch(node.getRight(), longestBranch, currentBranch, level +1);
			}

			currentBranch.remove(currentBranch.size() - 1); //Cuando terminas de visitar una rama, necesitas retroceder (backtrack) para probar otras ramas. Al retroceder, tienes que eliminar el último nodo agregado porque ya lo exploraste completamente.

									//Este paso garantiza que al subir de nuevo un nivel en el árbol, currentBranch quede en el mismo estado que estaba antes de bajar por esa rama.
									// si no hago el REMOVE, currentBranch acumularía nodos de ramas anteriores, y eso rompería la lógica. Te daría resultados incorrectos, mezclando caminos que no existen en el árbol.
		}
	}



	/* Ejercicio 2
    		Dado un árbol binario de búsquedas que almacena números enteros, implementar un algoritmo
    		que retorne la suma de todos los nodos internos del árbol. */

	public int sumInternalNodes(){
		sumInternalNodes(this.root);
	}

	private int sumInternalNodes(TreeNode node){
		if (node==null || (node.getLeft() == null && node.getRight() == null)){
			return 0;
		}
		return node.getValue() + sumInternalNodes(node.getLeft()) + sumInternalNodes(node.getRight());
	}
	
	
	
}
