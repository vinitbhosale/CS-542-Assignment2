package channelpopularity.state;

import java.util.HashMap;
import java.util.Map;

import channelpopularity.context.ContextI;

public class UnpopularState extends AbstractState {

    private ContextI channelCntxt;
    private String stateName = StateName.UNPOPULAR.getStateNameValue();

    public UnpopularState(ContextI context) {
        channelCntxt = context;

    }

    public void adRequest(String inAdFile, Map<String, Integer> inAdLength, ContextI channelCntxt) {
        if (!channelCntxt.getVideoDataMap().containsKey(inAdFile)) {
            // throws new exception.
        } else {
            for (Map.Entry<String, Integer> entry : inAdLength.entrySet()) {
                if (entry.getValue() > 1 && entry.getValue() <= 10) {
                    System.out.println("UNPOPULAR__AD_REQUEST::APPROVED");
                } else {
                    System.out.println("UNPOPULAR__AD_REQUEST::REJECTED");
                }
            }

        }

    }
}