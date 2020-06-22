package channelpopularity.state;

public class VideoMetricsScore {

    private int views;
    private int likes;
    private int disLikes;
    private Double popularityScore;

    public VideoMetricsScore() {
        views = 0;
        likes = 0;
        disLikes = 0;
        popularityScore = 0.0;

    }

    public void update(int inViews, int inLikes, int inDisLikes) {
        views += inViews;
        likes += inLikes;
        disLikes += inDisLikes;
    }

    public int getViews() {
        return views;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return disLikes;
    }

    public void setPopularityScore() {
        popularityScore = (double) (views + (2 * (likes - disLikes)));
    }

    public Double getPopularityScore() {
        return popularityScore;
    }
}