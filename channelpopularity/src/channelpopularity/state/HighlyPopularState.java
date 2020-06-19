package channelpopularity.state;

import channelpopularity.context.ContextI;

public class HighlyPopularState implements StateI {

    private ContextI channelCntxt;

    public HighlyPopularState(ContextI context){
        channelCntxt = context;

    }

    @Override
    public void addVideo() {
        // TODO Auto-generated method stub

    }

    @Override
    public void averagePopularityScore() {
        // TODO Auto-generated method stub

    }

    @Override
    public void adRequest() {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeVideo() {
        // TODO Auto-generated method stub

    }
    
}