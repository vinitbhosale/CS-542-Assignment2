package channelpopularity.state;

import java.util.Map;

import channelpopularity.context.ContextI;
import channelpopularity.operation.Operation;

public abstract class AbstractState implements StateI {

    public void addVideo(String inAddFile, ContextI channelCntxt) {
        System.out.println(channelCntxt.getCurrentState()+"__VIDEO_ADDED::"+inAddFile);
        if (channelCntxt.getVideoDataMap().containsKey(inAddFile)) {
            // throws new exception.
        } else {
            channelCntxt.setVideoDataMap(inAddFile, new VideoMetricsScore());
        }
        

    }

    public void calculateMetrics(String inFile, Map<String, Integer> inMetricCal, ContextI channelCntxt) {

        System.out.println(channelCntxt.getCurrentState()+"__POPULARITY_SCORE_UPDATED::"+channelCntxt.getChannelPopularityScore());
    
        channelCntxt.getVideoDataMap().get(inFile).update(
                inMetricCal.get(VideoProperties.VIEWS.getVideoPropertiesValue()),
                inMetricCal.get(VideoProperties.LIKES.getVideoPropertiesValue()),
                inMetricCal.get(VideoProperties.DISLIKES.getVideoPropertiesValue()));

        channelCntxt.getVideoDataMap().get(inFile)
                .setPopularityScore(channelCntxt.getVideoDataMap().get(inFile).getViews()
                        + (2 * (channelCntxt.getVideoDataMap().get(inFile).getLikes()
                                - channelCntxt.getVideoDataMap().get(inFile).getDislikes())));
        
        channelCntxt.setVideoDataMap(inFile, channelCntxt.getVideoDataMap().get(inFile));

       
        Double avgPopularityScore = 0.0;
            for (Map.Entry<String, VideoMetricsScore> entry : channelCntxt.getVideoDataMap().entrySet()) {
                avgPopularityScore += entry.getValue().getPopularityScore();
            }
            avgPopularityScore /= channelCntxt.getVideoDataMap().size();
            channelCntxt.setChannelPopularityScore(avgPopularityScore);

            if (avgPopularityScore >= (double) 0 && avgPopularityScore <= (double) 1000) {
                channelCntxt.setCurrentState(StateName.UNPOPULAR);
            } else if (avgPopularityScore > (double) 1000 && avgPopularityScore <= (double) 10000) {
                channelCntxt.setCurrentState(StateName.MILDLY_POPULAR);
            } else if (avgPopularityScore > (double) 10000 && avgPopularityScore <= (double) 100000) {
                channelCntxt.setCurrentState(StateName.HIGHLY_POPULAR);
            } else if (avgPopularityScore > (double) 100000 && avgPopularityScore <= (double) Integer.MAX_VALUE) {
                channelCntxt.setCurrentState(StateName.ULTRA_POPULAR);
            }

            

    }


    public void removeVideo(String inRemoveFile, ContextI channelCntxt) {

        System.out.println(channelCntxt.getCurrentState()+"__VIDEO_REMOVED::"+inRemoveFile);
        Double avgPopularityScore = 0.0;
        if (!channelCntxt.getVideoDataMap().containsKey(inRemoveFile)) {
            // throws new exception.
        } else {
            channelCntxt.getVideoDataMap().remove(inRemoveFile);
            for (Map.Entry<String, VideoMetricsScore> entry : channelCntxt.getVideoDataMap().entrySet()) {
                avgPopularityScore += entry.getValue().getPopularityScore();
            }
            avgPopularityScore /= channelCntxt.getVideoDataMap().size();
            channelCntxt.setChannelPopularityScore(avgPopularityScore);
            
            if (avgPopularityScore >= (double) 0 && avgPopularityScore <= (double) 1000) {
                channelCntxt.setCurrentState(StateName.UNPOPULAR);
            } else if (avgPopularityScore > (double) 1000 && avgPopularityScore <= (double) 10000) {
                channelCntxt.setCurrentState(StateName.MILDLY_POPULAR);
            } else if (avgPopularityScore > (double) 10000 && avgPopularityScore <= (double) 100000) {
                channelCntxt.setCurrentState(StateName.HIGHLY_POPULAR);
            } else if (avgPopularityScore > (double) 100000 && avgPopularityScore <= (double) Integer.MAX_VALUE) {
                channelCntxt.setCurrentState(StateName.ULTRA_POPULAR);
            }
        }
        

    }
    
}
