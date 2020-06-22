package channelpopularity.state;

import java.util.Map;

import channelpopularity.context.ContextI;
import channelpopularity.util.Results;

public class HighlyPopularState extends AbstractState {

    @Override
    public void adRequest(String inAdFile, Map<String, Integer> inAdLength, ContextI channelCntxt, Results result) {
        if (!channelCntxt.getVideoDataMap().containsKey(inAdFile)) {
            // throws new exception.
        } else {
            for (Map.Entry<String, Integer> entry : inAdLength.entrySet()) {
                if (entry.getValue() > 1 && entry.getValue() <= 30) {
                    result.storeResult("HIGHLY_POPULAR__AD_REQUEST::APPROVED");
                } else {
                    result.storeResult("HIGHLY_POPULAR__REQUEST::REJECTED");
                }
            }

        }

    }

}