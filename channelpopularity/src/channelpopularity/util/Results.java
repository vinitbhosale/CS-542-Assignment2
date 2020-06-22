package channelpopularity.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

    private String output;

    private File outputFile;
    private BufferedWriter outputBufferedWriter;

    public Results() {
        output = "";
    }

    public void storeResult(String resultData) {
        output = output.concat(resultData + '\n');

    }

    @Override
    public void writeToStdout() {
        System.out.println(output);

    }

    @Override
    public void writeToFile(String filePath) throws IOException {
        outputFile = new File(filePath);
        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }

        outputBufferedWriter = new BufferedWriter(new FileWriter(outputFile));

        outputBufferedWriter.write(output.trim());

        outputBufferedWriter.close();

    }

}
