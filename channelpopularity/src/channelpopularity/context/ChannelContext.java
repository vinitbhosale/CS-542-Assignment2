package channelpopularity.context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.VideoMetricsScore;
import channelpopularity.state.factory.SimpleStateFactoryI;
import channelpopularity.util.Results;

public class ChannelContext implements ContextI {

    private StateI curState;
    private Map<StateName, StateI> availableStates;
    private Map<String, VideoMetricsScore> videoList;
    private double channelPopularityScore;
    private Results result;

    public ChannelContext(SimpleStateFactoryI stateFactoryIn, List<StateName> stateNames, Results inResult) {
        // initialize states using factory instance and the provided state names.
        // initialize current state.
        channelPopularityScore = 0.0;
        availableStates = new HashMap<StateName, StateI>();
        videoList = new HashMap<String, VideoMetricsScore>();
        result = inResult;

        for (StateName state : stateNames) {
            availableStates.put(state, stateFactoryIn.create(state));
        }

        curState = availableStates.get(StateName.UNPOPULAR);

    }

    // Called by the States based on their logic of what the machine state should
    // change to.
    public void setCurrentState(StateName nextState) {
        if (availableStates.containsKey(nextState)) { // for safety.
            curState = availableStates.get(nextState);
        }
    }

    public StateName getCurrentState() {

        for (Map.Entry<StateName, StateI> key : availableStates.entrySet()) {
            if (key.getValue().equals(curState)) {
                return key.getKey();
            }
        }
        return null;

    }

    @Override
    public void setVideoDataMap(String addFile, VideoMetricsScore videoMtricsScre) {
        videoList.put(addFile, videoMtricsScre);

    }

    @Override
    public Map<String, VideoMetricsScore> getVideoDataMap() {
        return videoList;
    }

    public void setChannelPopularityScore(double channelPopularityScore) {
        this.channelPopularityScore = channelPopularityScore;
    }

    public double getChannelPopularityScore() {
        return channelPopularityScore;
    }

    @Override
    public void addVideo(String inAddFile) {
        curState.addVideo(inAddFile, this, result);
    }

    @Override
    public void averagePopularityScore(String inFile, Map<String, Integer> inMetricCal) {
        curState.calculateMetrics(inFile, inMetricCal, this, result);
    }

    @Override
    public void adRequest(String inAdFile, Map<String, Integer> inAdLength) {
        curState.adRequest(inAdFile, inAdLength, this, result);
    }

    @Override
    public void removeVideo(String inRemoveFile) {
        curState.removeVideo(inRemoveFile, this, result);
    }

}