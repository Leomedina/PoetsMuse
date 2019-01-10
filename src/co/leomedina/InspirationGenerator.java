package co.leomedina;

import co.leomedina.model.ArtWork;
import co.leomedina.model.Collection;

public class InspirationGenerator {

    public static void main(String[] args) {
        ArtWork artWork = new ArtWork(
                "Szukalski",
                "Struggle",
                "https://bit.ly/2LLbSmQ",
                1917);

        Collection collection = new Collection();

        System.out.println("Adding Work...");
        collection.addArtWork(artWork);
        System.out.println("Total Collection Count: " + collection.getArtWorkCount());
        System.out.println(artWork.toString());

        Generator generator = new Generator(collection);
    }
}
