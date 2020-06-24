package channelpopularity.state.factory;

import channelpopularity.state.StateI;
import channelpopularity.state.StateName;

/**
 * SimpleStateFactoryI interfcae that contains methods signatures that are required 
 * by the SimpleStateFactory class.
 */
public interface SimpleStateFactoryI {
    public StateI create(StateName state);
}
