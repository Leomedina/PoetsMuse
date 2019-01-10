package co.leomedina.model;

import java.util.ArrayList;
import java.util.List;

public class Collection {
    private List<ArtWork> mArtWorks;

    public Collection() {
        mArtWorks = new ArrayList<>();
    }

    public void addArtWork(ArtWork artwork) {
        mArtWorks.add(artwork);
    }

    public int getArtWorkCount() {
        return mArtWorks.size();
    }
}