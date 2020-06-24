package channelpopularity.state;

import java.util.Map;

import channelpopularity.context.ContextI;
import channelpopularity.userException.AdLengthException;
import channelpopularity.userException.NoVideoForAdException;
import channelpopularity.util.Results;

public class HighlyPopularState extends AbstractState {

    @Override
    public void adRequest(String inAdFile, Map<String, Integer> inAdLength, ContextI channelCntxt, Results result)
            throws NoVideoForAdException, AdLengthException {
        if (!channelCntxt.getVideoDataMap().containsKey(inAdFile)) {
            throw new NoVideoForAdException("Ad Request for " + inAdFile + " Rejected! No such video present.");
        } else {
            for (Map.Entry<String, Integer> entry : inAdLength.entrySet()) {
                if (entry.getValue() < 0) {
                    throw new AdLengthException("Ad Request length cannot be negative!");
                }
                if (entry.getValue() > 1 && entry.getValue() <= 30) {
                    result.storeResult("HIGHLY_POPULAR__AD_REQUEST::APPROVED");
                } else {
                    result.storeResult("HIGHLY_POPULAR__REQUEST::REJECTED");
                }
            }

        }

    }

}