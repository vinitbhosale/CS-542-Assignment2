package channelpopularity.state;

/**
 * StateName enum - Stores the constant of State names.
 */
public enum StateName {

    /**
     * UNPOPULAR string constant.
     * MILDLY_POPULAR string constant.
     * HIGHLY_POPULAR string constant.
     * ULTRA_POPULAR string constant.
     */
    UNPOPULAR("UNPOPULAR"),
    MILDLY_POPULAR("MILDLY_POPULAR"),
    HIGHLY_POPULAR("HIGHLY_POPULAR"),
    ULTRA_POPULAR("ULTRA_POPULAR");

    /**
     * Stores the constant string for a particular constant variable
    */
    private final String stateNameStrVale;

    /**
     * Creating enums based on enum name and storing the State name 
     * as constan string data.
     * 
     * @param stateNameStrVale
     */
    StateName(String stateNameStrVale){
        this.stateNameStrVale = stateNameStrVale;
    }

    /**
     * Return the State name as a string data.
     * 
     * @return stateNameStrVale
     */
    public String getStateNameValue(){
        return this.stateNameStrVale;
    }
}



