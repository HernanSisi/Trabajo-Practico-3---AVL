package imp;

import api.ABBTDA;

public class AVL implements ABBTDA {

    class NodoAVL {
        int info;
        int altura;
        ABBTDA hijoIzq;
        ABBTDA hijoDer;
    }

    NodoAVL raiz;

    @Override
    public void inicializarArbol() {
        raiz = null;
    }

    @Override
    public void agregarElem(int x) {
        raiz = agregarElemAVL(raiz, x);
    }

    private NodoAVL agregarElemAVL(NodoAVL nodo, int x) {
        if (nodo == null) {
            NodoAVL nuevo = new NodoAVL();
            nuevo.info = x;
            nuevo.altura = 1;
            nuevo.hijoIzq = new AVL();
            nuevo.hijoIzq.inicializarArbol();
            nuevo.hijoDer = new AVL();
            nuevo.hijoDer.inicializarArbol();
            return nuevo;
        }
        if (x < nodo.info) {
            nodo.hijoIzq = setRaiz(nodo.hijoIzq, agregarElemAVL(getRaiz(nodo.hijoIzq), x));
        } else if (x > nodo.info) {
            nodo.hijoDer = setRaiz(nodo.hijoDer, agregarElemAVL(getRaiz(nodo.hijoDer), x));
        } else {
            return nodo; // No se permiten duplicados
        }
        nodo.altura = 1 + Math.max(altura(getRaiz(nodo.hijoIzq)), altura(getRaiz(nodo.hijoDer)));
        return balancear(nodo);
    }

    @Override
    public void eliminarElem(int x) {
        raiz = eliminarElemAVL(raiz, x);
    }

    private NodoAVL eliminarElemAVL(NodoAVL nodo, int x) {
        if (nodo == null) return null;
        if (x < nodo.info) {
            nodo.hijoIzq = setRaiz(nodo.hijoIzq, eliminarElemAVL(getRaiz(nodo.hijoIzq), x));
        } else if (x > nodo.info) {
            nodo.hijoDer = setRaiz(nodo.hijoDer, eliminarElemAVL(getRaiz(nodo.hijoDer), x));
        } else {
            if (getRaiz(nodo.hijoIzq) == null && getRaiz(nodo.hijoDer) == null) {
                return null;
            } else if (getRaiz(nodo.hijoIzq) == null) {
                return getRaiz(nodo.hijoDer);
            } else if (getRaiz(nodo.hijoDer) == null) {
                return getRaiz(nodo.hijoIzq);
            } else {
                int sucesor = menor(getRaiz(nodo.hijoDer));
                nodo.info = sucesor;
                nodo.hijoDer = setRaiz(nodo.hijoDer, eliminarElemAVL(getRaiz(nodo.hijoDer), sucesor));
            }
        }
        nodo.altura = 1 + Math.max(altura(getRaiz(nodo.hijoIzq)), altura(getRaiz(nodo.hijoDer)));
        return balancear(nodo);
    }

    @Override
    public int raiz() {
        return raiz.info;
    }

    @Override
    public ABBTDA hijoIzq() {
        return raiz.hijoIzq;
    }

    @Override
    public ABBTDA hijoDer() {
        return raiz.hijoDer;
    }

    @Override
    public boolean arbolVacio() {
        return raiz == null;
    }

    // Métodos auxiliares

    private NodoAVL getRaiz(ABBTDA arbol) {
        if (arbol == null || arbol.arbolVacio()) return null;
        return ((AVL) arbol).raiz;
    }

    private ABBTDA setRaiz(ABBTDA arbol, NodoAVL nuevaRaiz) {
        if (arbol == null) arbol = new AVL();
        ((AVL) arbol).raiz = nuevaRaiz;
        return arbol;
    }

    private int altura(NodoAVL nodo) {
        return (nodo == null) ? 0 : nodo.altura;
    }

    private int balance(NodoAVL nodo) {
        if (nodo == null) return 0;
        return altura(getRaiz(nodo.hijoIzq)) - altura(getRaiz(nodo.hijoDer));
    }

    private NodoAVL rotarDerecha(NodoAVL y) {
        NodoAVL x = getRaiz(y.hijoIzq);
        NodoAVL T2 = getRaiz(x.hijoDer);

        x.hijoDer = setRaiz(x.hijoDer, y);
        y.hijoIzq = setRaiz(y.hijoIzq, T2);

        y.altura = 1 + Math.max(altura(getRaiz(y.hijoIzq)), altura(getRaiz(y.hijoDer)));
        x.altura = 1 + Math.max(altura(getRaiz(x.hijoIzq)), altura(getRaiz(x.hijoDer)));

        return x;
    }

    private NodoAVL rotarIzquierda(NodoAVL x) {
        NodoAVL y = getRaiz(x.hijoDer);
        NodoAVL T2 = getRaiz(y.hijoIzq);

        y.hijoIzq = setRaiz(y.hijoIzq, x);
        x.hijoDer = setRaiz(x.hijoDer, T2);

        x.altura = 1 + Math.max(altura(getRaiz(x.hijoIzq)), altura(getRaiz(x.hijoDer)));
        y.altura = 1 + Math.max(altura(getRaiz(y.hijoIzq)), altura(getRaiz(y.hijoDer)));

        return y;
    }

    private NodoAVL balancear(NodoAVL nodo) {
        int balance = balance(nodo);

        // Izquierda Izquierda
        if (balance > 1 && balance(getRaiz(nodo.hijoIzq)) >= 0)
            return rotarDerecha(nodo);

        // Izquierda Derecha
        if (balance > 1 && balance(getRaiz(nodo.hijoIzq)) < 0) {
            nodo.hijoIzq = setRaiz(nodo.hijoIzq, rotarIzquierda(getRaiz(nodo.hijoIzq)));
            return rotarDerecha(nodo);
        }

        // Derecha Derecha
        if (balance < -1 && balance(getRaiz(nodo.hijoDer)) <= 0)
            return rotarIzquierda(nodo);

        // Derecha Izquierda
        if (balance < -1 && balance(getRaiz(nodo.hijoDer)) > 0) {
            nodo.hijoDer = setRaiz(nodo.hijoDer, rotarDerecha(getRaiz(nodo.hijoDer)));
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    private int menor(NodoAVL nodo) {
        while (getRaiz(nodo.hijoIzq) != null) {
            nodo = getRaiz(nodo.hijoIzq);
        }
        return nodo.info;
    }
}
