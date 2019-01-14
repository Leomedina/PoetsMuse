package co.leomedina.model;

import java.io.*;
import java.util.*;

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

    private Map<String, List<ArtWork>> byArtist() {
        Map<String, List<ArtWork>> byArtist = new HashMap<>();
        for (ArtWork artwork : mArtWorks) {
            List<ArtWork> artistArtWorks = byArtist.get(artwork.getArtist());
            if (artistArtWorks == null) {
                artistArtWorks = new ArrayList<>();
                byArtist.put(artwork.getArtist(), artistArtWorks);
            }
            artistArtWorks.add(artwork);
        }
        return byArtist;
    }

    public Set<String> getArtists() {
        return byArtist().keySet();
    }

    public List<ArtWork> getSongForArtist(String artistName) {
        return byArtist().get(artistName);
    }

    public void expotTo(String fileName) {
        try (
                FileOutputStream fos = new FileOutputStream(fileName);
                PrintWriter printWriter = new PrintWriter(fos);
        ) {
            for (ArtWork arwork : mArtWorks) {
                printWriter.printf("%s|%s|%s|%d",
                        arwork.getArtist(),
                        arwork.getTitle(),
                        arwork.getUrl(),
                        arwork.getDateOfWork());
            }
        } catch (IOException ioe) {
            System.out.println("Problem Saving FIle");
            ioe.printStackTrace();
        }
    }
}