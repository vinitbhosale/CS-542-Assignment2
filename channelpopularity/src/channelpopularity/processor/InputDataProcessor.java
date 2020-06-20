package channelpopularity.processor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import channelpopularity.util.FileProcessor;
import channelpopularity.operation.Operation;
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

        String operation = "";
        String file = "";
        String metric = "";
        String subMetric = "";
        Map<String, Integer> metricCal = new HashMap<>();
        Map<String, Integer> adLength = new HashMap<>();

        while ((strData = fp.poll()) != null) {

            String[] keyAndValue = strData.split("::");

            if (keyAndValue[0].split("__").length > 1) {
                operation = keyAndValue[0].split("__")[0];
                file = keyAndValue[0].split("__")[1];
                metric = keyAndValue[1];

                if (operation.equals(Operation.METRICS.getOperationVal())) {
                    subMetric = metric.substring(1, metric.length() - 1);
                    String[] values = subMetric.split(",");

                    for (String pair : values) {
                        String[] entry = pair.split("=");
                        metricCal.put(entry[0].trim(), Integer.parseInt(entry[1].trim()));
                    }
                } else {
                    String[] ad = keyAndValue[1].split("=");
                    adLength.put(ad[0].trim(), Integer.parseInt(ad[1].trim()));
                }

            } else {
                operation = keyAndValue[0];
                file = keyAndValue[1];
            }

            switch (Operation.valueOf(operation)) {
                case ADD_VIDEO:
                    channelCntxt.addVideo(file);
                    break;
                case METRICS:
                    channelCntxt.averagePopularityScore(file, metricCal);
                    break;
                case AD_REQUEST:
                    channelCntxt.adRequest(file, adLength);
                    break;
                case REMOVE_VIDEO:
                    channelCntxt.removeVideo(file);
                    break;

                default:
                    break;
            }

        }

    }

}