package channelpopularity.processor;

import java.io.File;
import java.io.IOException;

import channelpopularity.util.FileProcessor;

public class InputDataProcessor {

    private FileProcessor fp;
    private String strData;

    public InputDataProcessor(FileProcessor inFp) {

        fp = inFp;

    }

    public void process() throws IOException {

        while((strData = fp.poll()) != null){

            System.out.println(strData);


        }
    }

}