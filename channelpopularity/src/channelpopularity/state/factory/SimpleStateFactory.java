package channelpopularity.state.factory;

import channelpopularity.context.ContextI;
import channelpopularity.state.HighlyPopularState;
import channelpopularity.state.MidlyPopulareState;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.UltraPopularState;
import channelpopularity.state.UnpopularState;

public class SimpleStateFactory implements SimpleStateFactoryI {

    @Override
    public StateI create(StateName state, ContextI channelCntxt) {

        StateI stateObj = null;
        switch (state) {
            case UNPOPULAR:
                stateObj = new UnpopularState(channelCntxt);
                break;
            case MILDLY_POPULAR:
                stateObj = new MidlyPopulareState(channelCntxt);
                break;
            case HIGHLY_POPULAR:
                stateObj = new HighlyPopularState(channelCntxt);
                break;
            case ULTRA_POPULAR:
                stateObj = new UltraPopularState(channelCntxt);
                break;

            default:
                break;

        }

        return stateObj;
    }

}
