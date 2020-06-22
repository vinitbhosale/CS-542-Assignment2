package channelpopularity.state.factory;

import channelpopularity.context.ContextI;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;

public interface SimpleStateFactoryI {
    public StateI create(StateName state);
}
