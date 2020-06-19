package channelpopularity.state.factory;

import channelpopularity.state.StateI;
import channelpopularity.state.StateName;

public class SimpleStateFactory implements SimpleStateFactoryI {

    @Override
    public StateI create(StateName state) {
        return state.getStateNameValue();
    }
    
}
