package channelpopularity.state;

import java.util.HashMap;
import java.util.Map;

import channelpopularity.context.ContextI;

public class HighlyPopularState implements StateI {

    private ContextI channelCntxt;

    public HighlyPopularState(ContextI context) {
        channelCntxt = context;

    }

    @Override
    public void addVideo(String inAddFile) {
        if (channelCntxt.getVideoDataMap().containsKey(inAddFile)) {
            // throws new exception.
        } else {
            channelCntxt.setVideoDataMap(inAddFile, new HashMap<String, Integer>());
        }

    }

    @Override
    public void averagePopularityScore(String inFile, Map<String, Integer> inMetricCal) {
        // TODO Auto-generated method stub

    }

    @Override
    public void adRequest(String inAdFile, Map<String, Integer> inAdLength) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeVideo(String inRemoveFile) {
        // TODO Auto-generated method stub

    }

}