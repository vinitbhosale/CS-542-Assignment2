package channelpopularity.processor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import channelpopularity.util.FileProcessor;
import channelpopularity.context.ContextI;
import channelpopularity.operation.Operation;
import channelpopularity.state.VideoProperties;
import channelpopularity.userException.AdLengthException;
import channelpopularity.userException.EmptyInputFileException;
import channelpopularity.userException.InvalidInputException;
import channelpopularity.userException.NegativeViewException;
import channelpopularity.userException.NoVideoForAdException;
import channelpopularity.userException.VideoAlreadyPresent;
import channelpopularity.userException.VideoDoesNotExist;

public class InputDataProcessor {

    private FileProcessor fp;
    private String strData;
    private ContextI channelCntxt;

    public InputDataProcessor(FileProcessor inFp, ContextI inChannelCntxt) {

        fp = inFp;
        channelCntxt = inChannelCntxt;
    }

    public void process() throws IOException, EmptyInputFileException, VideoAlreadyPresent, VideoDoesNotExist,
            NegativeViewException, NoVideoForAdException, AdLengthException, InvalidInputException {

        String operation = "";
        String file = "";
        String metric = "";
        String subMetric = "";
        Map<String, Integer> metricCal = new HashMap<>();
        Map<String, Integer> adLength = new HashMap<>();

        strData = fp.poll();

        if (null == strData) {
            throw new EmptyInputFileException("Input file is empty!");
        }

        while (strData != null) {

            if (strData.split("::").length == 1) {
                throw new InvalidInputException(
                        strData + "Invalid Input! Line in the input file does not follow the specified format.");
            }

            String[] keyAndValue = strData.split("::");

            if (keyAndValue[0].split("__").length > 1) {

                operation = keyAndValue[0].split("__")[0];
                file = keyAndValue[0].split("__")[1];
                metric = keyAndValue[1];

                if (!operation.equals(Operation.METRICS.getOperationVal())
                        && !operation.equals(Operation.AD_REQUEST.getOperationVal())) {
                    throw new InvalidInputException(
                            strData + "Invalid Input! Line in the input file does not follow the specified format.");
                }

                if (operation.equals(Operation.METRICS.getOperationVal())) {
                    if (!metric.matches("\\[" + VideoProperties.VIEWS.getVideoPropertiesValue() + "=-?[0-9]+,"
                            + VideoProperties.LIKES.getVideoPropertiesValue() + "=-?[0-9]+,"
                            + VideoProperties.DISLIKES.getVideoPropertiesValue() + "=-?[0-9]+\\]")) {
                        throw new InvalidInputException(
                                metric + "Invalid Input! Line in the input file does not follow the specified format.");
                    }
                    subMetric = metric.substring(1, metric.length() - 1);
                    String[] values = subMetric.split(",");

                    for (String pair : values) {
                        String[] entry = pair.split("=");
                        metricCal.put(entry[0], Integer.parseInt(entry[1]));
                    }

                } else {
                    if (!keyAndValue[1].matches("LEN=-?[0-9]+")) {
                        throw new InvalidInputException(
                                metric + "Invalid Input! Line in the input file does not follow the specified format.");
                    }
                    String[] ad = keyAndValue[1].split("=");
                    adLength.put(ad[0], Integer.parseInt(ad[1]));
                }

            } else {

                operation = keyAndValue[0];
                file = keyAndValue[1];

                if (!operation.equals(Operation.ADD_VIDEO.getOperationVal())
                        && !operation.equals(Operation.REMOVE_VIDEO.getOperationVal())) {
                    throw new InvalidInputException(
                            strData + "Invalid Input! Line in the input file does not follow the specified format.");
                }

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
            strData = fp.poll();

        }

    }

}