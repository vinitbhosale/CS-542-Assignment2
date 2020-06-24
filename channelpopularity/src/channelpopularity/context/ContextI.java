package channelpopularity.context;

import java.util.Map;

import channelpopularity.state.StateName;
import channelpopularity.state.VideoMetricsScore;
import channelpopularity.userException.AdLengthException;
import channelpopularity.userException.NegativeViewException;
import channelpopularity.userException.NoVideoForAdException;
import channelpopularity.userException.VideoAlreadyPresent;
import channelpopularity.userException.VideoDoesNotExist;

public interface ContextI {
    public void setCurrentState(StateName nextState);

    public StateName getCurrentState();

    public void setVideoDataMap(String addFile, VideoMetricsScore videoMtricsScre);

    public Map<String, VideoMetricsScore> getVideoDataMap();

    public void setChannelPopularityScore(double channelPopularityScore);

    public double getChannelPopularityScore();

    public void addVideo(String inAddFile) throws VideoAlreadyPresent;

    public void averagePopularityScore(String inFile, Map<String, Integer> inMetricCal)throws NegativeViewException;

    public void adRequest(String inAdFile, Map<String, Integer> inAdLength)throws NoVideoForAdException, AdLengthException;

    public void removeVideo(String inRemoveFile) throws VideoDoesNotExist;

}
