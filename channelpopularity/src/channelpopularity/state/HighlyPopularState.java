package channelpopularity.state;

import java.util.Map;

import channelpopularity.context.ContextI;
import channelpopularity.userException.AdLengthException;
import channelpopularity.userException.NoVideoForAdException;
import channelpopularity.util.Results;

/**
 * HighlyPopularState class extends all the methods of AbstractState
 * and contain method of the Ad request to accept of reject
 * the Ad request and store the result.
 */
public class HighlyPopularState extends AbstractState {
   
    /**
     * Implements the Aq request operation from each
     * State.
     * 
     * @param String inAdFile - video for ad request is.
     * @param HashMap inAdLength - Ad length.
     * @param ContextI channelCntxt - object on ContextI.
     * @param Results result - object of Results
     * 
     * @throws NoVideoForAdException
     * @throws AdLengthException
     */
    @Override
    public void adRequest(String inAdFile, Map<String, Integer> inAdLength, ContextI channelCntxt, Results result)
            throws NoVideoForAdException, AdLengthException {
        
        // Condition the check video the requested for Ad present or not.
        if (!channelCntxt.getVideoDataMap().containsKey(inAdFile)) {
            throw new NoVideoForAdException("Ad Request for " + inAdFile + " Rejected! No such video present.");
        } else {
            // Looping through Hashmap to acesses the key and value.
            for (Map.Entry<String, Integer> entry : inAdLength.entrySet()) {
                // Condition the check the Ad length.
                if (entry.getValue() < 0) {
                    throw new AdLengthException("Ad Request length cannot be negative!");
                }
                // Condition to check the range of the Ad assign to the State.
                if (entry.getValue() > 1 && entry.getValue() <= 30) {
                    // Stores the result to print.
                    result.storeResult("HIGHLY_POPULAR__AD_REQUEST::APPROVED");
                } else {
                    // Stores the result to print.
                    result.storeResult("HIGHLY_POPULAR__REQUEST::REJECTED");
                }
            }

        }

    }

    @Override
    public String toString(){
        return "Class: HighlyPopularState, Data members:[]";
    }


}