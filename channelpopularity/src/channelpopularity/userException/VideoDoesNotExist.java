package channelpopularity.userException;

public class VideoDoesNotExist extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public VideoDoesNotExist(String s) {
        super(s);
    }
}