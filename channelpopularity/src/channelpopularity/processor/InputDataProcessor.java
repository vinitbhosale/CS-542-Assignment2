package channelpopularity.processor;

import java.io.File;
import java.io.IOException;
import java.util.List;

import channelpopularity.state.factory.SimpleStateFactoryI;
import channelpopularity.util.FileProcessor;
import channelpopularity.context.ContextI;
import channelpopularity.state.StateName;

public class InputDataProcessor {

    private FileProcessor fp;
    private String strData;
    private ContextI channelCntxt;

    public InputDataProcessor(FileProcessor inFp, ContextI inContext) {

        fp = inFp;
        channelCntxt = inContext;
    }

    public void process() throws IOException {

        while((strData = fp.poll()) != null){

            //System.out.println(strData);

            String[] keyAndValue = strData.split("::");
            //System.out.println(keyAndValue[0]+" || "+keyAndValue[1]);

        }
   
    }

}