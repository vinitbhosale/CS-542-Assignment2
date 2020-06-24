package channelpopularity.state;

import java.util.Map;

import channelpopularity.context.ContextI;
import channelpopularity.userException.VideoDoesNotExist;
import channelpopularity.userException.NegativeViewException;
import channelpopularity.userException.VideoAlreadyPresent;
import channelpopularity.util.Results;

/**
 * AbstractState an abstract class consists of methods for adding video in the
 * videoList, Removing video from videoList and average popularity of channel as
 * well as change the current state of the machine.
 */

public abstract class AbstractState implements StateI {

    /**
     * Implements adding of the video in the videoList.
     * 
     * @param String   inAddFile - video need to add.
     * @param ContextI channelCntxt - Object to access the channelContext file.
     * @param Results  result - Result object to store the result.
     * 
     * @throws VideoAlreadyPresent
     */
    public void addVideo(String inAddFile, ContextI channelCntxt, Results result) throws VideoAlreadyPresent {

        // Condition to check video already present.
        if (channelCntxt.getVideoDataMap().containsKey(inAddFile)) {
            throw new VideoAlreadyPresent("Video is already present!");
        } else {
            // Storing the result to print.
            result.storeResult(channelCntxt.getCurrentState() + "__VIDEO_ADDED::" + inAddFile);
            // Adding the video in the videoList.
            channelCntxt.setVideoDataMap(inAddFile, new VideoMetricsScore());
        }
        /**
         * Calculation the average popularity score for the channel by calling the
         * avgCalulation().
         */
        Double avgPopularityScore = 0.0;

        avgPopularityScore = avgCalulation(channelCntxt);

        // Updating the channel popularity score.
        channelCntxt.setChannelPopularityScore(avgPopularityScore);

        // Changing yhe current state of the machine.
        stateChanger(avgPopularityScore, channelCntxt);

    }

    /**
     * Calculate popularity score of each video, also calculate average popularity
     * score of channel and updating the current state of machine.
     * 
     * @param String   inFile - video to add.
     * @param HashMap  inMetricCal - video properties.
     * @param ContextI channelCntxt - ContextI object.
     * @param Results  result - Result object.
     * 
     * @throws NegativeViewException
     */
    public void calculateMetrics(String inFile, Map<String, Integer> inMetricCal, ContextI channelCntxt, Results result)
            throws NegativeViewException {

        // Update the video properties of the video in videolist.
        channelCntxt.getVideoDataMap().get(inFile).update(
                inMetricCal.get(VideoProperties.VIEWS.getVideoPropertiesValue()),
                inMetricCal.get(VideoProperties.LIKES.getVideoPropertiesValue()),
                inMetricCal.get(VideoProperties.DISLIKES.getVideoPropertiesValue()));

        // calculating the popularity score for the video and storing it.
        channelCntxt.getVideoDataMap().get(inFile).setPopularityScore();

        // Adding the video with all score and updates in the videolist File.
        channelCntxt.setVideoDataMap(inFile, channelCntxt.getVideoDataMap().get(inFile));

        /**
         * Calculating the average popularity score for the channel by calling the
         * avgCalulation().
         */
        Double avgPopularityScore = 0.0;

        avgPopularityScore = avgCalulation(channelCntxt);

        // Updatig the channel popularity score.
        channelCntxt.setChannelPopularityScore(avgPopularityScore);

        // Storing the result of popularity score in the result file to print.
        result.storeResult(channelCntxt.getCurrentState() + "__POPULARITY_SCORE_UPDATED::"
                + String.format("%.1f", channelCntxt.getChannelPopularityScore()));

        // Chaning the state of the machine based on channel popularity score.
        stateChanger(avgPopularityScore, channelCntxt);

    }

    /**
     * Impelment removin of video from videoList.
     * 
     * @param String   inRemoveFile - video to remove.
     * @param ContextI channelCntxt - object of ContextI
     * @param Results  result object of Results class
     * 
     * @throws VideoDoesNotExist
     */
    public void removeVideo(String inRemoveFile, ContextI channelCntxt, Results result) throws VideoDoesNotExist {

        // Condition to check video to remove is present or not.
        if (!channelCntxt.getVideoDataMap().containsKey(inRemoveFile)) {
            throw new VideoDoesNotExist("Video asked to remove does not exist!");
        } else {

            // Storing the result to print.
            result.storeResult(channelCntxt.getCurrentState() + "__VIDEO_REMOVED::" + inRemoveFile);

            // Call to remove the required video.
            channelCntxt.getVideoDataMap().remove(inRemoveFile);

            /**
             * Calculating the average popularity score for the channel by calling the
             * avgCalulation().
             */
            Double avgPopularityScore = 0.0;
            avgPopularityScore = avgCalulation(channelCntxt);

            // Updatig the channel popularity score.
            channelCntxt.setChannelPopularityScore(avgPopularityScore);

             // Chaning the state of the machine based on channel popularity score.
            stateChanger(avgPopularityScore, channelCntxt);

        }

    }

    /**
     * Calculte the average popularity score for the channel.
     * @param channelCntxt - object of ContextI.
     * 
     * @return avgPopularityScore
     */
    protected double avgCalulation(ContextI channelCntxt) {
        Double avgPopularityScore = 0.0;
        // Looping through each video and its value to calculate the channel score.
        for (Map.Entry<String, VideoMetricsScore> entry : channelCntxt.getVideoDataMap().entrySet()) {
            avgPopularityScore += entry.getValue().getPopularityScore();
        }
        
        // Condition to check negative score of channel and setting to zero.
        if ((avgPopularityScore /= channelCntxt.getVideoDataMap().size()) < 0) {
            avgPopularityScore = 0.0;
        }
        return avgPopularityScore;
    }

    /**
     * Changes the state of the machine based on the channel average 
     * popularity score.
     * 
     * @param inAvgScore - channel average popoularity score.
     * @param channelCntxt -  object of ContextI.
     */
    protected void stateChanger(double inAvgScore, ContextI channelCntxt) {

        if (inAvgScore >= (double) 0 && inAvgScore <= (double) 1000) {
            channelCntxt.setCurrentState(StateName.UNPOPULAR);
        } else if (inAvgScore > (double) 1000 && inAvgScore <= (double) 10000) {
            channelCntxt.setCurrentState(StateName.MILDLY_POPULAR);
        } else if (inAvgScore > (double) 10000 && inAvgScore <= (double) 100000) {
            channelCntxt.setCurrentState(StateName.HIGHLY_POPULAR);
        } else if (inAvgScore > (double) 100000 && inAvgScore <= (double) Integer.MAX_VALUE) {
            channelCntxt.setCurrentState(StateName.ULTRA_POPULAR);
        }

    }

}
