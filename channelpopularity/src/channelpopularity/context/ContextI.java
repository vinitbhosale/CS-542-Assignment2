package channelpopularity.context;

import channelpopularity.state.StateName;

public interface ContextI {
    public void setCurrentState(StateName nextState);

}
