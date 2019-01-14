package co.leomedina;

import co.leomedina.model.Collection;
import co.leomedina.model.ArtWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Generator {
    private Collection mCollection;
    private BufferedReader mReader;
    private Map<String, String> mMenu;

    Generator(Collection collection) {
        mCollection = collection;
        mReader = new BufferedReader(new InputStreamReader(System.in));
        generateMenu();
        run();
    }

    private void generateMenu() {
        mMenu = new HashMap<>();
        mMenu.put("Add", "Add new artwork to collection");
        mMenu.put("Choose", "Choose a work to display");
        mMenu.put("Quit", "Exit Program");
    }

    private String promptAction() throws IOException {
        System.out.printf("There are %d options available.%n" +
                "Options Available: %n", mMenu.size());
        for (Map.Entry<String, String> option : mMenu.entrySet()) {
            System.out.printf("%s - %s%n",
                    option.getKey(),
                    option.getValue());
        }
        System.out.println("What do you want to do?");
        return mReader.readLine().trim().toLowerCase();
    }

    private void run() {
        String choice = "";
        do try {
            choice = promptAction();
            switch (choice) {
                case "add":
                    mCollection.addArtWork(promptNewArtWork());
                    break;
                case "choose":
                    String artist = promptArtist();
                    ArtWork artistWork = promptForArtworkForArtist(artist);
                    //TODO: TOO SONG QUEUE
                    System.out.printf("YOu chose: %s %n", artistWork);
                    break;
                case "quit":
                    System.out.println("Thanks for playing!");
                    break;
                default:
                    System.out.println("Unknown Choice, Try again");
            }
        } catch (IOException ioe) {
            System.out.println("Problem with input");
            ioe.printStackTrace();
        } while (!choice.equals("quit"));
    }

    private ArtWork promptNewArtWork() throws IOException {
        int year = -1;

        System.out.println("Enter Artist Name: ");
        String artist = mReader.readLine();
        System.out.println("Enter the Title: ");
        String title = mReader.readLine();
        System.out.println("Enter URL of work: ");
        String url = mReader.readLine();

        do try {
            System.out.println("What year was the work created? ");
            int tempYear = Integer.parseInt(mReader.readLine());
            if (isValidYear(tempYear)) {
                year = tempYear;
            } else {
                System.out.println("Sorry Invalid Year! Try again: ");
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Sorry that's not a year! Try again.");
        } while (year == -1);

        return new ArtWork(artist, title, url, year);
    }

    private boolean isValidYear(int year) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return year <= currentYear && year > 1400;
    }

    private int promptForIndex(List<String> options) throws IOException {
        int counter = 1;

        for (String option : options) {
            System.out.printf("%d.) %s%n", counter, option);
            counter++;
        }
        System.out.print("Your Choice ...");
        String optionAsString = mReader.readLine();
        int choice = Integer.parseInt(optionAsString.trim());

        return choice - 1;
    }

    private String promptArtist() throws IOException {
        System.out.println("Available Artist: ");
        List<String> artists = new ArrayList<>(mCollection.getArtists());
        int index = promptForIndex(artists);
        return artists.get(index);
    }

    private ArtWork promptForArtworkForArtist(String artist) throws IOException {
        List<ArtWork> artWorks = mCollection.getSongForArtist(artist);
        List<String> artworkTitles = new ArrayList<>();

        for (ArtWork artWork : artWorks) {
            artworkTitles.add(artWork.getTitle());
        }
        System.out.printf("Available Songs for %s: %n", artist);
        int index = promptForIndex(artworkTitles);
        return artWorks.get(index);
    }
}

//this is a test
