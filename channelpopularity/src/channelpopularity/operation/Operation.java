package channelpopularity.operation;

public enum Operation {

    ADD_VIDEO("ADD_VIDEO"),
    METRICS("METRICS"),
    AD_REQUEST("AD_REQUEST"),
    REMOVE_VIDEO("REMOVE_VIDEO");

    private String operationStrval;

    Operation(String operationStrval){
        this.operationStrval = operationStrval; 
    }

    public String getOperationVal(){
        return this.operationStrval;
    }

}
