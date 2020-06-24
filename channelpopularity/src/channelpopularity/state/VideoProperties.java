package channelpopularity.state;

/**
 * VideoProperties enum - Stores the constant for video properties.
 */

public enum VideoProperties {

    /**
     * View string constant
     * Likes string constant
     * DisLikes string constant
     */

    VIEWS("VIEWS"),
    LIKES("LIKES"),
    DISLIKES("DISLIKES");
    
    /**
     * Stores the constant string for a particular constant variable
    */
    private final String videoProperties;

    /**
     * Creating enums based on enum name and storing a video property name as constant
     * string data
     * 
     * @param videoProperties
     */
    VideoProperties(String videoProperties){
        this.videoProperties = videoProperties;
    }
    /**
     * Return the video property name as a string data.
     * 
     * @return videoProperties
     */
    public String getVideoPropertiesValue(){
        return this.videoProperties;
    }
    
}