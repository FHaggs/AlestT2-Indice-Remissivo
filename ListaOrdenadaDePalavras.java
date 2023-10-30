/**
 * Esta classe guarda as palavra do indice remissivo em ordem alfabetica.
 * @author Isabel H. Manssour
 */

public class ListaOrdenadaDePalavras {

    // Classe interna 
    private class Palavra {
        public String s;
        public ListaDeOcorrencias listaOcorrencias;
        public Palavra next;
        public Palavra prev;
        public Palavra(String str) {
            s = str;
            next = null;
            prev = null;
            listaOcorrencias = new ListaDeOcorrencias();
        }
        
        public void addOcorrencia(int ocorrencia){
            
            listaOcorrencias.add(ocorrencia);
        }
        public String toString(){
            return s +":"+ listaOcorrencias.toString();
        }

    }
    
    // Referência para o primeiro elemento da lista encadeada.
    private Palavra header;
    // Referência para o último elemento da lista encadeada.
    private Palavra trailer;
    // Referencia para a posicao corrente.
    private Palavra current;      
    // Contador para a quantidade de elementos que a lista contem.
    private int count;

    
    /**
     * Construtor da lista.
     */
    public ListaOrdenadaDePalavras() {
        header = new Palavra(null);
        trailer = new Palavra(null);
        header.next = trailer;
        trailer.prev = header;
        count = 0;
    }
    
    /**
     * Retorna true se a lista nao contem elementos.
     * @return true se a lista nao contem elementos
     */
    public boolean isEmpty() {
        return (count == 0);
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
        header.next = trailer;
        trailer.prev = header;
        count = 0;
    }    

    /**
     * Adiciona um elemento ao final da lista.
     * @param element elemento a ser adicionado ao final da lista
     */
    public void orderedAdd (String element, int nPagina)  { 
        Palavra aux = containsElement(element); // verifica se ja contem element para não inserir duplicado
        if (aux == null) {  // se nao contem element, insere
            Palavra n = new Palavra(element);

            if (header.next == trailer) { 
                // se a lista está vazia
                n.prev = header;
                n.next = trailer;
                trailer.prev = n;
                header.next = n;

            } 
            else if (element.compareTo(header.next.s)<0) { 
                // se for menor que o primeiro, insere no inicio
                n.next = header.next;
                n.prev = header;
                header.next = n;
                n.next.prev = n;
            }
            else if (element.compareTo(trailer.prev.s)>0) {
                // se for maior que o ultimo, insere no final
                n.next = trailer;
                n.prev = trailer.prev;
                trailer.prev.next = n;
                trailer.prev = n;
            }
            else {
                // senao procura a posicao correta para insercao
                aux = header.next;
                boolean inseriu=false;
                while (aux!=trailer && !inseriu) {
                    if (element.compareTo(aux.s)<0) {
                        inseriu = true;
                        n.next = aux;
                        n.prev=aux.prev;
                        aux.prev.next = n;
                        aux.prev = n;
                    }
                    aux = aux.next;
                }
            }
            count++;
            n.addOcorrencia(nPagina);
        }else {
            aux.addOcorrencia(nPagina);
        }
    }
    
    private Palavra containsElement(String element) {
        Palavra aux = header.next;
        
        while (aux != trailer) {
            if (aux.s.equals(element)) {
                return aux;
            }
            aux = aux.next;
        }
        
        return null;
    }    
    
    /**
     * Retorna o elemento de uma determinada posicao da lista.
     * @param index a posição da lista
     * @return o elemento da posicao especificada
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */
    public String get(int index) { 
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException();
        }
        Palavra aux = getPalavraIndex(index);
        return aux.s;
    }
    
    // Metodo que tem como objetivo retornar uma referencia
    // para o nodo da posicao "index" recebida como parametro.
    // Por exemplo, se index for 2, ele retorna a referencia
    // para o nodo da posicao 2.
    private Palavra getPalavraIndex(int index) {
        Palavra aux = null;
        if (index < count/2) { // caminha do inicio para o meio
            aux = header.next;
            for(int i=0; i<index; i++)
                aux = aux.next;
        }
        else { // caminha do fim para o meio
            aux = trailer.prev;
            for(int i=count-1; i>index; i--)
                aux = aux.prev;
        }
        return aux;
    }
    
    /**
     * Inicializa o current na primeira posicao (para percorrer do inicio para o fim).
     */
    public void reset() {
        current = header.next;
    }

    /**
     * Retorna o elemento da posicao corrente e faz current apontar para o proximo
     * elemento da lista.
     * @return elemento da posicao corrente
     */
    public String next() {
        if (current != trailer) {
            String str = current.s;
            current = current.next;
            return str;
        }
        return null;
    }  
    
    public String palavraMaisFrequente(){
        int valorMaior = 0;

        Palavra referenciaMaior = null;

        Palavra aux = header.next;

        for(int i=0;i<size();i++){
            if(valorMaior < aux.listaOcorrencias.size()){
                valorMaior = aux.listaOcorrencias.size();
                referenciaMaior = aux;
            }
            aux = aux.next;
        }

        return referenciaMaior.s;
        
    }
    

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Palavra current = header.next;
        while (current != null) {
            sb.append(current.toString());
            sb.append("\n");
            current = current.next;
        }
        return sb.toString();

    }

}
