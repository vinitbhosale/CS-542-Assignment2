package channelpopularity.state;

public enum VideoProperties {

    VIEWS("VIEWS"),
    LIKES("LIKES"),
    DISLIKES("DISLIKES");
    

    private final String videoProperties;

    VideoProperties(String videoProperties){
        this.videoProperties = videoProperties;
    }

    public String getVideoPropertiesValue(){
        return this.videoProperties;
    }
    
}