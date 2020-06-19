package channelpopularity.state;

public enum StateName {

    UNPOPULAR("UNPOPULAR"),
    MILDLY_POPULAR("MILDLY_POPULAR"),
    HIGHLY_POPULAR("HIGHLY_POPULAR"),
    ULTRA_POPULAR("ULTRA_POPULAR");

    private final String stateNameStrVale;

    StateName(String stateNameStrVale){
        this.stateNameStrVale = stateNameStrVale;
    }

    public String getStateNameValue(){
        return this.stateNameStrVale;
    }
}



