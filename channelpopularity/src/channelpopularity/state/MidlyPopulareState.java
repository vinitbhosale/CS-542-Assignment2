package channelpopularity.state;

import java.util.HashMap;
import java.util.Map;

import channelpopularity.context.ContextI;

public class MidlyPopulareState extends AbstractState {

    private ContextI channelCntxt;

    public MidlyPopulareState(ContextI context) {
        channelCntxt = context;

    }


    @Override
    public void adRequest(String inAdFile, Map<String, Integer> inAdLength, ContextI channelCntxt) {
        if (!channelCntxt.getVideoDataMap().containsKey(inAdFile)) {
            // throws new exception.
        } else {
            for (Map.Entry<String, Integer> entry : inAdLength.entrySet()) {
                if (entry.getValue() > 1 && entry.getValue() <= 20) {
                    System.out.println("MILDLY_POPULAR__AD_REQUEST::APPROVED");
                } else {
                    System.out.println("MILDLY_POPULAR__REQUEST::REJECTED");
                }
            }

        }

    }

}