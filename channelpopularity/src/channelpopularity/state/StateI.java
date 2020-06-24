package channelpopularity.state;

import java.util.Map;

import channelpopularity.context.ContextI;
import channelpopularity.userException.AdLengthException;
import channelpopularity.userException.NegativeViewException;
import channelpopularity.userException.NoVideoForAdException;
import channelpopularity.userException.VideoAlreadyPresent;
import channelpopularity.userException.VideoDoesNotExist;
import channelpopularity.util.Results;

public interface StateI {
    public void addVideo(String inAddFile, ContextI channContextI, Results result) throws VideoAlreadyPresent;

    public void calculateMetrics(String inFile, Map<String, Integer> inMetricCal, ContextI channContextI,
            Results result) throws NegativeViewException;

    public void adRequest(String inAdFile, Map<String, Integer> inAdLength, ContextI channContextI, Results result)
            throws NoVideoForAdException, AdLengthException;

    public void removeVideo(String inRemoveFile, ContextI channContextI, Results result) throws VideoDoesNotExist;

}
