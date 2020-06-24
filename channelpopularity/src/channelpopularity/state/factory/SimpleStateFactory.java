package channelpopularity.state.factory;

import channelpopularity.state.HighlyPopularState;
import channelpopularity.state.MidlyPopulareState;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.UltraPopularState;
import channelpopularity.state.UnpopularState;

/**
 * SimpleStateFactory class creates object of each States.
 */
public class SimpleStateFactory implements SimpleStateFactoryI {
    
    /** 
     * Implements object creation of each State based on the
     * state parameter.
     * 
     * @param StateName state - State enum.
     * @return stateObj - Object of State.
    */
    @Override
    public StateI create(StateName state) {

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

    @Override
    public String toString(){
        return "Class: SimpleStateFactory, Data members:[]";
    }

    

}
