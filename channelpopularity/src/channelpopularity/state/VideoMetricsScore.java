package channelpopularity.state;

import channelpopularity.userException.NegativeViewException;

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

    public void update(int inViews, int inLikes, int inDisLikes) throws NegativeViewException {
        if (0 > inViews) {
            throw new NegativeViewException("Views cannot be negative!");
        } else {

            views += inViews;
            likes += inLikes;
            disLikes += inDisLikes;

            if (likes < 0 || disLikes < 0) {
                throw new NegativeViewException("Likes and Dislikes cannot be negative!");
            }
        }

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