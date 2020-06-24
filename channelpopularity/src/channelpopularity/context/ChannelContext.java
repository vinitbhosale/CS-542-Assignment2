package channelpopularity.context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.VideoMetricsScore;
import channelpopularity.state.factory.SimpleStateFactoryI;
import channelpopularity.userException.AdLengthException;
import channelpopularity.userException.NegativeViewException;
import channelpopularity.userException.NoVideoForAdException;
import channelpopularity.userException.VideoAlreadyPresent;
import channelpopularity.userException.VideoDoesNotExist;
import channelpopularity.util.Results;

/** 
 * ChannelContext class implements methods for setting current state and 
 * Calling of Operation methods based of the current state and also
 * implements object creation of each states.
 * 
 * @author - Vinit Surendra Bhosale.
 * 
*/

public class ChannelContext implements ContextI {
    // Stores the interface of StateI that is implemented by each States in curState.
    private StateI curState;
    // HashMap that stores the object for each states.
    private Map<StateName, StateI> availableStates;
    // HashMap that stores all the videos.
    private Map<String, VideoMetricsScore> videoList;
    // Stores the running average score of the channel.
    private double channelPopularityScore;
    // Stores the Results class object.
    private Results result;

    /**
     * ChannelContext construst that initialize the instance variables.
     * 
     * @param stateFactoryIn - SimpleStateFactoryI object. 
     * @param stateNames - list of all states.
     * @param inResult - result object.
     * 
     */

    public ChannelContext(SimpleStateFactoryI stateFactoryIn, List<StateName> stateNames, Results inResult) {
        
        // intialize default channel score.
        channelPopularityScore = 0.0;
        // intialice the Hashmap that store all state names.
        availableStates = new HashMap<StateName, StateI>();
         // initialze the HashMap the store all added videos.
        videoList = new HashMap<String, VideoMetricsScore>();
        // Results instance. 
        result = inResult;
        // initialize states using factory instance and the provided state names.
        for (StateName state : stateNames) {
            availableStates.put(state, stateFactoryIn.create(state));
        }
        // initialize current state.
        curState = availableStates.get(StateName.UNPOPULAR);

    }

    // Called by the States based on their logic of what the machine state should
    // change to.
    public void setCurrentState(StateName nextState) {
        if (availableStates.containsKey(nextState)) { 
            curState = availableStates.get(nextState);
        }
    }

    // Called by the States based on their logic to fetch the current state.
    public StateName getCurrentState() {

        /** Looping through the HashMap of availabe state to fetch the current
         * state name. 
         */  

        for (Map.Entry<StateName, StateI> key : availableStates.entrySet()) {
            if (key.getValue().equals(curState)) {
                return key.getKey();
            }
        }
        return null;

    }
    /** 
     * Implements adding of new video in videoList HashMap.
     * 
     * @param String addFile and VideoMetricsScore object.
    */
    @Override
    public void setVideoDataMap(String addFile, VideoMetricsScore videoMtricsScre) {
        videoList.put(addFile, videoMtricsScre);

    }

     /** 
     * Returns all videos stored in HashMap videoList.
     * 
     * @return videoList HashMap.
    */
    @Override
    public Map<String, VideoMetricsScore> getVideoDataMap() {
        return videoList;
    }

     /** 
     * Stores the value of ChannelPopularity score called by the method
     * in the States. 
     * 
     * @param doulbe channelPopularityScore.
    */
    public void setChannelPopularityScore(double channelPopularityScore) {
        this.channelPopularityScore = channelPopularityScore;
    }

      /** 
     * Stores the value of ChannelPopularity score called by the method
     * in the States. 
     * 
     * @param doulbe channelPopularityScore.
    */
    public double getChannelPopularityScore() {
        return channelPopularityScore;
    }

    /** 
     * Implements adding of the video in the videoList HashMap by the
     * State 
     * 
     * @param String inAddFile - video.
    */
    @Override
    public void addVideo(String inAddFile) throws VideoAlreadyPresent {
        curState.addVideo(inAddFile, this, result);
    }

    /** 
     * Implements calcluation of average popularity score the channel
     * by the States. 
     * 
     * @param String inFile - video.
     * @param  HashMap inMetricCal - video properties.
    */
    @Override
    public void averagePopularityScore(String inFile, Map<String, Integer> inMetricCal) throws NegativeViewException {
        curState.calculateMetrics(inFile, inMetricCal, this, result);
    }

    /**
     * Implements Ad_request for each video by the States.
     * 
     * @param String inAdFile - video. 
     * @param HashMap inAdLength - Ad length.
     */
    @Override
    public void adRequest(String inAdFile, Map<String, Integer> inAdLength)
            throws NoVideoForAdException, AdLengthException {
        curState.adRequest(inAdFile, inAdLength, this, result);
    }

    /**
     * Implements removing of video from channel by the States.
     * 
     * @param String inRemoveFile - video.
     */
    @Override
    public void removeVideo(String inRemoveFile) throws VideoDoesNotExist {
        curState.removeVideo(inRemoveFile, this, result);
    }

    @Override
    public String toString() {
        return "Class: ChannelContext, Data Members: [curState="+curState.toString()+", channelPopularityScore=" + (double) channelPopularityScore
                + ", availableStates=" + availableStates.toString() + ", videoList=" + videoList.toString()
                + ", result=" + result.toString() + "]";

    }

}