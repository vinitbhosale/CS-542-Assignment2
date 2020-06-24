package channelpopularity.operation;

/**
 * Operation enum - Stores the constant for all operations 
 * of the States.
 */

public enum Operation {

    /**
     * Add Video string operation constant.
     * Metrics string operation constant.
     * Ad Request string operation constant.
     * Remove video string operation constant.
     */
    ADD_VIDEO("ADD_VIDEO"),
    METRICS("METRICS"),
    AD_REQUEST("AD_REQUEST"),
    REMOVE_VIDEO("REMOVE_VIDEO");

    /**
     * Stores the constant string for a particular constant variable
    */
    private final String operationStrval;

    /**
     * Creating enums based on enum name and storing a operation name as constant
     * string data
     * @param operationStrval
     */
    Operation(String operationStrval){
        this.operationStrval = operationStrval; 
    }
    
    /**
     * Return the operation name as a string data.
     * 
     * @return operationStrval
     */
    public String getOperationVal(){
        return this.operationStrval;
    }

}
