package channelpopularity.state;

import java.util.Map;

import channelpopularity.context.ContextI;

public interface StateI {
    public void addVideo(String inAddFile, ContextI channContextI);
    public void calculateMetrics(String inFile, Map<String, Integer> inMetricCal, ContextI channContextI);
    public void adRequest(String inAdFile, Map<String, Integer> inAdLength, ContextI channContextI);
    public void removeVideo(String inRemoveFile, ContextI channContextI);
    

}
