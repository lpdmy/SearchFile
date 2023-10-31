package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WordProgram {

    public int countWord(String line, String word) {
        int count = 0;
        int index = line.indexOf(word);

        while (index != -1) {
            count++;
            index = line.indexOf(word, index + 1);
        }

        return count;
    }

    public int countWordInFile(String path, String word) {
        int count = 0;
        try {
            FileInputStream fis = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();

            while (line != null) {
                count += countWord(line, word);
                line = br.readLine();
            }
            br.close();
            isr.close();
            fis.close();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<String> getFileName(String source, String word) throws Exception {
        List<String> result = new ArrayList<>();
        File directory = new File(source);

        if (!directory.exists() || !directory.isDirectory()) {
            throw new Exception("Source is not a valid directory.");
        }
        File[] f = directory.listFiles();
        if (f != null) {
        for (File file : f) {
            if (file.isFile() && countWordInFile(file.getName(), word)>0) {
                result.add(file.getName());
            }
        }
    }
    return result;
    }
}
