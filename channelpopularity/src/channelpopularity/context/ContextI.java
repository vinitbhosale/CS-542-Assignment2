package channelpopularity.context;

import java.util.Map;

import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.VideoMetricsScore;

public interface ContextI {
    public void setCurrentState(StateName nextState);
    public StateName getCurrentState();
    public void setVideoDataMap(String addFile, VideoMetricsScore videoMtricsScre);
    public Map<String, VideoMetricsScore> getVideoDataMap();
    public void setChannelPopularityScore(double channelPopularityScore);
    public double getChannelPopularityScore();

    public void addVideo(String inAddFile);
    public void averagePopularityScore(String inFile, Map<String, Integer> inMetricCal);
    public void adRequest(String inAdFile, Map<String, Integer> inAdLength);
    public void removeVideo(String inRemoveFile);

}
