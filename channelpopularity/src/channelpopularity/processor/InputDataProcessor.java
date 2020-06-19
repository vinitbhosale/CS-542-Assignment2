package channelpopularity.processor;

import java.io.IOException;
import channelpopularity.util.FileProcessor;
import channelpopularity.state.StateI;

public class InputDataProcessor {

    private FileProcessor fp;
    private String strData;
    private StateI channelCntxt;

    public InputDataProcessor(FileProcessor inFp, StateI inChannelCntxt) {

        fp = inFp;
        channelCntxt = inChannelCntxt;
    }

    public void process() throws IOException {

        while((strData = fp.poll()) != null){

            String[] keyAndValue = strData.split("::");
            System.out.println(keyAndValue[0]+" || "+keyAndValue[1]);

        }
   
    }

}