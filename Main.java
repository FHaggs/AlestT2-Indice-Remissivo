/**
 * Classe que inicializa a execução da aplicacao.
 * @author Isabel H. Manssour
 */
public class Main {
    public static void main(String[] args) {
        
    int nLinha = 0;
    int nPagina = 0;
    int numTotalPalavras = 0;
    int numStopWords = 0;

    
    ArquivoTexto arquivo = new ArquivoTexto(); // objeto que gerencia o arquivo
    LinhaTexto linha = new LinhaTexto(); // objeto que gerencia uma linha
    String l;

    StopWords stopWords = new StopWords("StopWords-EN.txt");

    ListaOrdenadaDePalavras lista = new ListaOrdenadaDePalavras();

    arquivo.open("livros/alice.txt");
    
    do  // laco que passa em cada linha do arquivo
    {
        l = arquivo.getNextLine();
        if (l==null) // acabou o arquivo?
           break;
        nLinha++; // conta mais uma linha lida do arquivo
        if (nLinha == 40) // chegou ao fim da pagina?
        {
            nLinha = 0;
            nPagina++;
        }
        //System.out.println("Linha " + nLinha + ":");

        linha.setLine(l); // define o texto da linha
        do // laco que passa em cada palavra de uma linha
        {
            String palavra = linha.getNextWord(); // obtem a proxima palavra da linha
            if (palavra == null)// acabou a linha
            {
                break;
            }
            palavra = removePunctuationAndToLower(palavra);
            
            if(!stopWords.containsWord(palavra)){
                lista.orderedAdd(palavra, nPagina);
                numTotalPalavras++;
            }else {
                numStopWords++;
                numTotalPalavras++;
            }

         } while (true);

    } while (true);

    arquivo.close();        
    System.out.println(lista);
    System.out.println("Número de Stopwords: " + numStopWords);
    System.out.println("Número Total de Palavras: " + numTotalPalavras);

    double percentualStopWords = porcentagemStopWords(numStopWords, numTotalPalavras);
    System.out.printf("Percentual de Stopwords: %.2f%%\n", percentualStopWords);
    }
    public static String removePunctuationAndToLower(String input) {
        // Retira os caracteres não alfanuméricos
        String withoutPunctuation = input.replaceAll("[^a-zA-Z0-9]+", "");

        // Transformar o resultado em minúsculas
        String result = withoutPunctuation.toLowerCase();

        return result;
    }
    public static double porcentagemStopWords(int numStopWords, double numTotalPalavras){
        return (numStopWords / numTotalPalavras * 100);
    }
}
