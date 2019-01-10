package co.leomedina.model;

public class ArtWork {
    private String mArtist;
    private String mTitle;
    private int mDateOfWork;
    private String mUrl;

    public ArtWork(String artist, String title, String url, int dateOfWork) {
        mArtist = artist;
        mTitle = title;
        mUrl = url;
        mDateOfWork = dateOfWork;
    }

    public String getArtist() {
        return mArtist;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    public int getDateOfWork() {
        return mDateOfWork;
    }

    @Override
    public String toString() {
        return String.format("Art: %s by %s",
                mTitle,
                mArtist);
    }
}
