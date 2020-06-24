package channelpopularity.state;

import java.util.Map;

import channelpopularity.context.ContextI;
import channelpopularity.userException.AdLengthException;
import channelpopularity.userException.NegativeViewException;
import channelpopularity.userException.NoVideoForAdException;
import channelpopularity.userException.VideoAlreadyPresent;
import channelpopularity.userException.VideoDoesNotExist;
import channelpopularity.util.Results;

/**
 * StateI nterface that contains methods signatures that are required 
 * by the AbstractState class.
 */
public interface StateI {
     /**
     * Implementing adding of the video in the videoList.
     * 
     * @param inAddFile - video to be added.
     * @param channContextI - object of ContextI.
     * @param result - object of Results.
     * 
     * @throws VideoAlreadyPresent
     */
     public void addVideo(String inAddFile, ContextI channContextI, Results result) throws VideoAlreadyPresent;
    
     /**
     * Calculate the average popularity score of the video.
     * 
     * @param inFile - video.
     * @param inMetricCal - video properties of video with value.
     * @param channContextI - object of ContextI.
     * @param result - object of Results.
     * 
     * @throws NegativeViewException
     */
     public void calculateMetrics(String inFile, Map<String, Integer> inMetricCal, ContextI channContextI,
            Results result) throws NegativeViewException;
    
     /**
     * Implements Ad request for each State.
     * 
     * @param inAdFile - video.
     * @param inAdLength - ad length.
     * @param channContextI - object of ContextI.
     * @param result - object of Results.
     * 
     * @throws NoVideoForAdException
     * @throws AdLengthException
     */
     public void adRequest(String inAdFile, Map<String, Integer> inAdLength, ContextI channContextI, Results result)
            throws NoVideoForAdException, AdLengthException;

     /**
     * Implements remving of video file from videoList.
     * 
     * @param inRemoveFile - video to be removed.
     * @param channContextI - object of ContextI.
     * @param result - object of Results.
     * 
     * @throws VideoDoesNotExist
     */
     public void removeVideo(String inRemoveFile, ContextI channContextI, Results result) throws VideoDoesNotExist;

}
