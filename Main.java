import java.util.Scanner;

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
            
            if(!stopWords.containsWord(palavra) && !palavra.isEmpty()){
                lista.orderedAdd(palavra, nPagina);
                numTotalPalavras++;
            }else if(!palavra.isEmpty()){
                numStopWords++;
                numTotalPalavras++;
            }

         } while (true);

    } while (true);
    arquivo.close();

    Scanner sc = new Scanner(System.in);

    while(true){
        System.out.println("Escolha uma opção:");
        System.out.println("1. Exibir todo o índice remissivo (em ordem alfabética)");
        System.out.println("2. Exibir o percentual de stopwords do texto");
        System.out.println("3. Encontrar a palavra mais frequente");
        System.out.println("4. Pesquisar palavras");
        System.out.println("5. Encerrar o programa");

        int op = sc.nextInt();

        switch(op){
            case 1: 
                System.out.println(lista); 
                break;
            case 2:
                double percentualStopWords = porcentagemStopWords(numStopWords, numTotalPalavras);
                System.out.printf("Percentual de Stopwords: %.2f%%\n", percentualStopWords);
                break;
            case 3:
                
            case 4:

            case 5:
                System.out.println("Programa encerrado.");
                sc.close();
                System.exit(0);
                break;
            default: 
                System.out.println("Opção inválida"); 
                break; 
        }

    }
    
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
