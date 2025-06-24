package prin;

import api.ABBTDA;
import imp.AVL;

public class prin {
	public static int contarNodos(ABBTDA a) {
		int resultado;
		if(a.arbolVacio())
			resultado = 0;
		else
			resultado = 1 + contarNodos(a.hijoIzq()) + contarNodos(a.hijoDer());
		return resultado;
	}
	
	public static int altura(ABBTDA a) {
		int resultado;
		if (a.arbolVacio())
			resultado = -1;
		else
			resultado = 1 + Math.max(altura(a.hijoIzq()), altura(a.hijoDer()));
		return resultado;
	}
	
	public static void preOrder(ABBTDA a) {
		if(!a.arbolVacio()) {
			System.out.print(a.raiz() + " ");
			preOrder(a.hijoIzq());
			preOrder(a.hijoDer());
		}
	}
	
	public static void inOrder(ABBTDA a) {
		if(!a.arbolVacio()) {
			inOrder(a.hijoIzq());
			System.out.print(a.raiz() + " ");
			inOrder(a.hijoDer());
		}
	}
	
	public static void postOrder(ABBTDA a) {
		if(!a.arbolVacio()) {
			postOrder(a.hijoIzq());
			postOrder(a.hijoDer());
			System.out.print(a.raiz() + " ");
		}
	}
	
	/* public static boolean isomorfos(ABBTDA a, ABBTDA b) {
		if(a.arbolVacio() && b.arbolVacio())
			return true;
		else if(a.arbolVacio() || b.arbolVacio())
			return false;
		else
			return isomorfos(a.hijoIzq(), b.hijoIzq()) && isomorfos(a.hijoDer(), b.hijoDer());
	} */
	
	public static void main(String[] args) {
		ABBTDA a = new AVL();
		ABBTDA b = new AVL();
		a.inicializarArbol();
		a.agregarElem(55);
		a.agregarElem(85);
		a.agregarElem(87);
		a.agregarElem(28);
		a.agregarElem(42);
		a.agregarElem(63);
		a.agregarElem(111);
		a.agregarElem(17);
		a.agregarElem(29);
		a.agregarElem(60);
		a.agregarElem(75);
		a.agregarElem(24);
		a.agregarElem(68);
		a.agregarElem(82);
		a.agregarElem(20);
		a.agregarElem(27);
				
		System.out.println("pre-order:");
		preOrder(a);
		System.out.println();
		
		System.out.println("in-order:");
		inOrder(a);
		System.out.println();
		
		System.out.println("post-order:");
		postOrder(a);
		System.out.println();
	}
}
