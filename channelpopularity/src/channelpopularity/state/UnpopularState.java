package channelpopularity.state;

import java.util.Map;

import channelpopularity.context.ContextI;

public class UnpopularState implements StateI{

    private ContextI channelCntxt;

    public UnpopularState(ContextI context){
        channelCntxt = context;

    }
    @Override
    public void addVideo(String inAddFile) {
        // TODO Auto-generated method stub

    }

    @Override
    public void averagePopularityScore(String inFile, Map<String, Integer> inMetricCal) {
        // TODO Auto-generated method stub

    }

    @Override
    public void adRequest(Map<String, Integer> inAdLength) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeVideo(String inRemoveFile) {
        // TODO Auto-generated method stub

    }
    
}