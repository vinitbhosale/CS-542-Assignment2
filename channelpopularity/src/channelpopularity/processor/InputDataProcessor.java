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

/**
 * InputDataProcessor class implements processing of
 * each line and calls the operation methods
 * in the channelContext file.
 */

public class InputDataProcessor {

    // Intializing object of FileProcessor.
    private FileProcessor fp;
    // Intiaizing String variable to store each line of the input file.
    private String strData;
    // Intializing instance of ContextI.
    private ContextI channelCntxt;
    
    /**
     * InputDataProcessor construst that initialize the instance variables.
     * 
     * @param inFp
     * @param inChannelCntxt
     */
    public InputDataProcessor(FileProcessor inFp, ContextI inChannelCntxt) {

        fp = inFp;
        channelCntxt = inChannelCntxt;
    }

    /**
     * Implements processing of the input file and calls the 
     * operation methods in the channelContext file.
     * 
     * @throws IOException
     * @throws EmptyInputFileException
     * @throws VideoAlreadyPresent
     * @throws VideoDoesNotExist
     * @throws NegativeViewException
     * @throws NoVideoForAdException
     * @throws AdLengthException
     * @throws InvalidInputException
     * @throws NumberFormatException
     */
    public void process()
            throws IOException, EmptyInputFileException, VideoAlreadyPresent, VideoDoesNotExist, NegativeViewException,
            NoVideoForAdException, AdLengthException, InvalidInputException, NumberFormatException {
        
        // Store the opeartion value.
        String operation = "";
        // Store the file i=on which the operation need to be done.
        String file = "";
        String metric = "";
        String subMetric = "";
        // HashMap to store the video properties with their value.
        Map<String, Integer> metricCal = new HashMap<>();
        // HashMap to store the ad length.
        Map<String, Integer> adLength = new HashMap<>();
        
        // Calling the poll method to fetch each line in input file.
        strData = fp.poll();

        // Condition to check Empty input file.
        if (null == strData) {
            throw new EmptyInputFileException("Input file is empty!");
        }

        // looping through input file.
        while (strData != null) {

            // Condition to check the required format of line.
            if (strData.split("::").length == 1) {
                throw new InvalidInputException(
                        "Invalid Input! Line in the input file does not follow the specified format.");
            }

            String[] keyAndValue = strData.split("::");

            // Condition for Metric__ and Ad_Request__ operation.
            if (keyAndValue[0].split("__").length > 1) {

                operation = keyAndValue[0].split("__")[0];
                file = keyAndValue[0].split("__")[1];
                metric = keyAndValue[1];

                // Condition to check the required format of line.
                if (!operation.equals(Operation.METRICS.getOperationVal())
                        && !operation.equals(Operation.AD_REQUEST.getOperationVal())) {
                    throw new InvalidInputException(
                            "Invalid Input! Line in the input file does not follow the specified format.");
                }

                // Condition to check the required format of Metric Opertion values.
                if (operation.equals(Operation.METRICS.getOperationVal())) {
                    if (!metric.matches("\\[" + VideoProperties.VIEWS.getVideoPropertiesValue() + "=-?[0-9]+,"
                            + VideoProperties.LIKES.getVideoPropertiesValue() + "=-?[0-9]+,"
                            + VideoProperties.DISLIKES.getVideoPropertiesValue() + "=-?[0-9]+\\]")) {
                        throw new InvalidInputException(
                                "Invalid Input! Line in the input file does not follow the specified format.");
                    }
                    // Removing the [] brackets.
                    subMetric = metric.substring(1, metric.length() - 1);
                    // Splitting at comma to get video properties.
                    String[] values = subMetric.split(",");

                    // Looping through the properties and pushing it in HashMap with
                    // key and value.
                    for (String pair : values) {
                        String[] entry = pair.split("=");
                        metricCal.put(entry[0], Integer.parseInt(entry[1]));
                    }

                } else {
                    // Condition to check the required format Ad_Request value.
                    if (!keyAndValue[1].matches("LEN=-?[0-9]+")) {
                        throw new InvalidInputException(
                                "Invalid Input! Line in the input file does not follow the specified format.");
                    }
                    // Splitting at (=) for Ad length and pushing in HashMap
                    String[] ad = keyAndValue[1].split("=");
                    adLength.put(ad[0], Integer.parseInt(ad[1]));
                }

            } else {
                // Condition for Add_video and Remove_video operation.
                operation = keyAndValue[0];
                file = keyAndValue[1];
                // Condition to check the required format of line.
                if (!operation.equals(Operation.ADD_VIDEO.getOperationVal())
                        && !operation.equals(Operation.REMOVE_VIDEO.getOperationVal())) {
                    throw new InvalidInputException(
                            "Invalid Input! Line in the input file does not follow the specified format.");
                }

            }
            // Switch to call the appropriate Operation method in the channelContext. 
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

            // Fetching the next line in the input file.
            strData = fp.poll();

        }

    }

    @Override
    public String toString() {
        return "Class: InputDataProcessor, Data Members: [fp=" + fp.toString() + ", strData=" + strData.toString()
                + ", channelCntxt=" + channelCntxt.toString() + "]";

    }

}