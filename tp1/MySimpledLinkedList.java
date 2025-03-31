package tp1;

public class MySimpleLinkedList<T> {
	
	private Node<T> first;
	private int size;
	
	public MySimpleLinkedList() {
		this.first = null;
		this.size=0;
	}
	
	public void insertFront(T info) {
		Node<T> tmp = new Node<T>(info,null);
		tmp.setNext(this.first);
		this.first = tmp;
		size++;
	}
	
	public T extractFront() {		
		Node<T> tmp = first;
		this.first = first.getNext();
		size--;
		
		return tmp.getInfo();
	}

	public boolean isEmpty() {
		return this.first==null;
	}
	
	public T get(int index) {
		if (index < 0 || index >= size ){
			return null;
		}else {
			Node<T> puntero = this.first;
			for (int contador=0; contador < index; contador++) {
				puntero = puntero.getNext();
			}
			return puntero.getInfo();
		}
	}
	
	public int size() {
		return this.size;
	}
	
	@Override
	public String toString() {
		String resultado = "";
		Node<T> nodoActual = this.first;
		while (nodoActual!=null) {
			resultado+= "info: " + nodoActual.getInfo()+"puntero: "+ nodoActual.getNext();
			nodoActual = nodoActual.getNext();
		}
		return resultado;
	}
	
public int indexOf(T element) {
		
		Node<T> actual=this.first;
		int index=0;
		
		while(actual!=null) {
			if(actual.getInfo().equals(element)) {
				return index; // Retorna la posición del elemento si lo encuentra
				
			}
			actual = actual.getNext();
			index++;
		}
		
		return -1;
	}
