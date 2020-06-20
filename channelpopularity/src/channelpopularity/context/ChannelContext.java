package channelpopularity.context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactoryI;

public class ChannelContext implements ContextI, StateI {

    private StateI curState;
    private Map<StateName, StateI> availableStates;
    private Map<String, Map<String, Integer>> videoList;

    public ChannelContext(SimpleStateFactoryI stateFactoryIn, List<StateName> stateNames) {
        // initialize states using factory instance and the provided state names.
        // initialize current state.
        availableStates = new HashMap<StateName, StateI>();
        videoList = new HashMap<String, Map<String, Integer>>();
        for (StateName state : stateNames) {
            availableStates.put(state, stateFactoryIn.create(state, this));
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

    @Override
    public void setVideoDataMap(String addFile, Map<String, Integer> metricHMap) {
        videoList.put(addFile, metricHMap);

    }

    @Override
    public Map<String, Map<String, Integer>> getVideoDataMap() {

        return videoList;
    }

    @Override
    public void addVideo(String inAddFile) {
        curState.addVideo(inAddFile);
    }

    @Override
    public void averagePopularityScore(String inFile, Map<String, Integer> inMetricCal) {
        curState.averagePopularityScore(inFile, inMetricCal);
    }

    @Override
    public void adRequest(String inAdFile, Map<String, Integer> inAdLength) {
        curState.adRequest(inAdFile, inAdLength);
    }

    @Override
    public void removeVideo(String inRemoveFile) {
        curState.removeVideo(inRemoveFile);
    }

}