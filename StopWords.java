import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StopWords {
    String stopWordsFile;

    public StopWords(String nomeArquivo) {
        stopWordsFile = nomeArquivo;
    }

    public boolean containsWord(String word) {
        try (BufferedReader reader = new BufferedReader(new FileReader(stopWordsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().equalsIgnoreCase(word)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


}
