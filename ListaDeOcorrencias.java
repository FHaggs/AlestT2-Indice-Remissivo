import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Esta classe guarda os numeros das paginas em que uma palavra ocorre.
 * @author Isabel H. Manssour
 */
public class ListaDeOcorrencias implements Iterable<Integer>{
        
    // Classe interna Node
    private class Node {
        public int numeroDaPagina;
        public Node next;    
        public Node(int n) {
            numeroDaPagina = n;
            next = null;
        }
    }
    // Iterator
    private class CustomIterator implements Iterator<Integer> {
        private Node current;

        public CustomIterator() {
            current = head;
        }

        // Checks if the next element exists
        public boolean hasNext() {
            return current != null;
        }

        // moves the cursor/iterator to the next element
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int value = current.numeroDaPagina;
            current = current.next;
            return value;
        }
    }
    
    // Atributos
    private Node head;
    private Node tail;
    private int count;

    // Metodos 
    public ListaDeOcorrencias() {
        head = null;
        tail = null;
        count = 0;
    }
    
    /**
     * Retorna true se a lista nao contem elementos.
     * @return true se a lista nao contem elementos
     */
    public boolean isEmpty() {
        return (head == null);
    }   
    
    /**
     * Retorna o numero de elementos da lista.
     * @return o numero de elementos da lista
     */
    public int size() {
        return count;
    }  
    
    /**
     * Esvazia a lista
     */
    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }

    /**
     * Adiciona um numero de pagina ao final da lista, caso ele ainda
     * nao tenha sido adicionado.
     * @param numPagina número da página a ser adicionado ao final da lista
     * @return true se adicionou no final da lista o numero de pagina  
     * recebido por parametro, e false caso contrario.
     */
    public boolean add(int numPagina)  {
        if(contains(numPagina)){
            return false;
        }
        Node nNode = new Node(numPagina);
        if(head == null){
            head = nNode;
            tail = head;
        }else{
            tail.next = nNode;
            tail = nNode;
        }
        count++;
        return true;
    }  
    
    /**
     * Retorna o elemento de uma determinada posicao da lista.
     * @param index a posição da lista
     * @return o elemento da posicao especificada
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */    
    public Integer get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Índice fora dos limites.");
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.numeroDaPagina;
    }
 
    /**
     * Retorna true se a lista contem o numero de pagina passado
     * por parametro.
     * @param numPagina o elemento a ser procurado
     * @return true se a lista contem o elemento especificado
     */
    public boolean contains(int numPagina) {
        Node current = head;
        while(current != null){
            if(current.numeroDaPagina == numPagina){
                return true;
            }
            current = current.next;
        }
        return false;
    }    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node current = head;
        while (current != null) {
            sb.append(current.numeroDaPagina);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<Integer> iterator() {
        return new CustomIterator();

    }
}
