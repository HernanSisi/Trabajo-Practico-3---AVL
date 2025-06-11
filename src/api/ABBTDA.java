package api;

public interface ABBTDA {
	void inicializarArbol();	// sin precondiciones
	void agregarElem(int x);	// árbol inicializado
	void eliminarElem(int x);	// árbol inicializado
	int raiz();					// árbol inicializado y no vacío
	ABBTDA hijoIzq();			// árbol inicializado y no vacío
	ABBTDA hijoDer();			// árbol inicializado y no vacío
	boolean arbolVacio();		// árbol inicializado 
}

