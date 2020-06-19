package channelpopularity.state;

public enum StateName {

    UNPOPULAR(new UnpopularState()),
    MILDLY_POPULAR(new MidlyPopulareState()),
    HIGHLY_POPULAR(new HighlyPopularState()),
    ULTRA_POPULAR(new UltraPopularState());

    private final StateI stateNameStrVale;

    StateName(StateI stateNameStrVale){
        this.stateNameStrVale = stateNameStrVale;
    }

    public StateI getStateNameValue(){
        return this.stateNameStrVale;
    }
}



