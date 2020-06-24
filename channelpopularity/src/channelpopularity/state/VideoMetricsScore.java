package channelpopularity.state;

import channelpopularity.userException.NegativeViewException;

/**
 * VideoMetricsScore class consists of methods to update
 * the video properties for each video, fetch the video
 * properties and calculating the popularity score for
 * each video.
 */

public class VideoMetricsScore {


    private int views;
    private int likes;
    private int disLikes;
    private Double popularityScore;

    // VideoMetricsScore constructor to intiatize variables.
    public VideoMetricsScore() {
        views = 0;
        likes = 0;
        disLikes = 0;
        popularityScore = 0.0;

    }

    /**
     * Updates the video properties of each video.
     * 
     * @param inViews
     * @param inLikes
     * @param inDisLikes
     * 
     * @throws NegativeViewException
    */
    public void update(int inViews, int inLikes, int inDisLikes) throws NegativeViewException {
        // Condition to check views are not negative.
        if (0 > inViews) {
            throw new NegativeViewException("Views cannot be negative!");
        } else {

            views += inViews;
            likes += inLikes;
            disLikes += inDisLikes;

            // Condition to check likes and dislikes are not less than total
            // likes and dislikes.
            if (likes < 0 || disLikes < 0) {
                throw new NegativeViewException("Likes and Dislikes cannot be negative!");
            }
        }

    }
    /**
     * Return views
     * 
     * @return views
     */
    public int getViews() {
        return views;
    }

    /**
    * Return likes
    * 
    * @return likes
    */
    public int getLikes() {
        return likes;
    }

    /**
     * Return disLikes
     * 
     * @return disLikes
     */
    public int getDislikes() {
        return disLikes;
    }

    /** 
     * Calculate the popularity score of the video.
    */
    public void setPopularityScore() {
        popularityScore = (double) (views + (2 * (likes - disLikes)));
        // Condition to check if popularity score of video goes negative and set it 0.0.
        if (popularityScore < 0){
            popularityScore = 0.0;
        }
    }

    /**
     * Return popularity score.
     * 
     * @return popularityScore
     */
    public Double getPopularityScore() {
        return popularityScore;
    }

    @Override
    public String toString() {
        return "Class: VideoMetricsScore, Data Members: [views=" + views + ", likes=" + likes
                + ", disLikes=" + disLikes + ", popularityScore="+popularityScore+"]";

    }
}