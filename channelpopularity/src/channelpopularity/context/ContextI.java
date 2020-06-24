package channelpopularity.context;

import java.util.Map;

import channelpopularity.state.StateName;
import channelpopularity.state.VideoMetricsScore;
import channelpopularity.userException.AdLengthException;
import channelpopularity.userException.NegativeViewException;
import channelpopularity.userException.NoVideoForAdException;
import channelpopularity.userException.VideoAlreadyPresent;
import channelpopularity.userException.VideoDoesNotExist;

/**
 * ContextI interface that contains methods signatures that are required 
 * by the ChannelContext class.
 * 
 * @author Vinit Surendra Bhosale.
 */
public interface ContextI {
    /**
     * Set the machine state by the States.
     */
    public void setCurrentState(StateName nextState);

    /**
     * Returns the current state of the machine.
     * 
     * @return - current state.
     */
    public StateName getCurrentState();

    /**
     * Add the video in the videoList HashMap with its video properties.
     * 
     * @param addFile - video.
     * @param videoMtricsScre - Object of VideoMetricsScore that stores video properties.
     */
    public void setVideoDataMap(String addFile, VideoMetricsScore videoMtricsScre);

    /**
     * Returns the HashMap of the stored video.
     * 
     * @return - videolist HashMap.
     */
    public Map<String, VideoMetricsScore> getVideoDataMap();

    /**
     * Store the channel popularity score
     * 
     * @param channelPopularityScore - calculated score by the States.
     */
    public void setChannelPopularityScore(double channelPopularityScore);

    /**
     * Return the channel popularity score.
     * 
     * @return channelPopularityScore.
     */
    public double getChannelPopularityScore();

    /**
     * Stores the video and its video properties.
     * 
     * @param inAddFile
     * @throws VideoAlreadyPresent
     */
    public void addVideo(String inAddFile) throws VideoAlreadyPresent;

    /**
     * Calculate running average populariy score of the channel.
     * 
     * @param inFile
     * @param inMetricCal
     * @throws NegativeViewException
     */
    public void averagePopularityScore(String inFile, Map<String, Integer> inMetricCal)throws NegativeViewException;
    
    /**
     * Implements Ad_request for each video by the States.
     * 
     * @param inAdFile
     * @param inAdLength
     * @throws NoVideoForAdException
     * @throws AdLengthException
     */
    public void adRequest(String inAdFile, Map<String, Integer> inAdLength)throws NoVideoForAdException, AdLengthException;
    
    /**
     * Implements removing of video from channel by the States.
     * 
     * @param inRemoveFile
     * @throws VideoDoesNotExist
     */
    public void removeVideo(String inRemoveFile) throws VideoDoesNotExist;

}
