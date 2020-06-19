package channelpopularity.context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactoryI;

public class ChannelContext implements ContextI, StateI {

    private StateI curState;
    private Map<StateName, StateI> availableStates;

    public ChannelContext(SimpleStateFactoryI stateFactoryIn, List<StateName> stateNames) {
        // initialize states using factory instance and the provided state names.
        // initialize current state.
        availableStates = new HashMap<StateName, StateI>();
        for (StateName state : stateNames) {
            availableStates.put(state, stateFactoryIn.create(state, this));
        }

        curState = availableStates.get(StateName.UNPOPULAR);

    }

    // Called by the States based on their logic of what the machine state should
    // change to.
    public void setCurrentState(StateName nextState) {
        if (availableStates.containsKey(nextState)) { // for safety.
            curState = availableStates.get(nextState);
        }
    }

    @Override
    public void addVideo() {
        curState.addVideo();
    }

    @Override
    public void averagePopularityScore() {
        curState.averagePopularityScore();
    }

    @Override
    public void adRequest() {
        curState.adRequest();
    }

    @Override
    public void removeVideo() {
        curState.removeVideo();
    }

}