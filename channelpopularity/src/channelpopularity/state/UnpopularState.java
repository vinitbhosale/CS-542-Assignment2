package channelpopularity.state;

import channelpopularity.context.ContextI;

public class UnpopularState implements StateI{

    private ContextI channelCntxt;

    public UnpopularState(ContextI context){
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