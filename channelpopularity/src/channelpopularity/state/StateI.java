package channelpopularity.state;

import java.util.Map;

public interface StateI {
    public void addVideo(String inAddFile);
    public void averagePopularityScore(String inFile, Map<String, Integer> inMetricCal);
    public void adRequest(Map<String, Integer> inAdLength);
    public void removeVideo(String inRemoveFile);

}
