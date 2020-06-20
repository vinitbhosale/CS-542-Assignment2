package channelpopularity.context;

import java.util.Map;

import channelpopularity.state.StateName;

public interface ContextI {
    public void setCurrentState(StateName nextState);
    public void setVideoDataMap(String addFile, Map<String, Integer> metricHMap);
    public Map<String, Map<String, Integer>> getVideoDataMap();

}
