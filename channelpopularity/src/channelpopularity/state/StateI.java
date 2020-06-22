package channelpopularity.state;

import java.util.Map;

import channelpopularity.context.ContextI;
import channelpopularity.util.Results;

public interface StateI {
    public void addVideo(String inAddFile, ContextI channContextI, Results result);
    public void calculateMetrics(String inFile, Map<String, Integer> inMetricCal, ContextI channContextI, Results result);
    public void adRequest(String inAdFile, Map<String, Integer> inAdLength, ContextI channContextI, Results result);
    public void removeVideo(String inRemoveFile, ContextI channContextI, Results result);
    

}
