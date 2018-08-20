package quickmart;

public class Nodo {
    
    private Product produ;
    private Nodo siguiente;

    public Nodo(Product prod) {
        this.produ = prod;
        this.siguiente=null;
    }

    public Nodo(Product prod, Nodo siguiente) {
        this.produ = prod;
        this.siguiente = siguiente;
    }

    public Product getProdu() {
        return produ;
    }

    public void setProdu(Product prod) {
        this.produ = prod;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    
}
