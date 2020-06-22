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
    public StateI create(StateName state){

        StateI stateObj = null;
        switch (state) {
            case UNPOPULAR:
                stateObj = new UnpopularState();
                break;
            case MILDLY_POPULAR:
                stateObj = new MidlyPopulareState();
                break;
            case HIGHLY_POPULAR:
                stateObj = new HighlyPopularState();
                break;
            case ULTRA_POPULAR:
                stateObj = new UltraPopularState();
                break;

            default:
                break;

        }

        return stateObj;
    }

}
