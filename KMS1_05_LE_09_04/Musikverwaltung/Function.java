import java.io.*;
import java.util.*;
// Defines a class named Function that can be serialized, allowing instances to be saved and loaded.
public class Function implements Serializable {
    private final HashSet<String> tracksID = new HashSet<>();// Stores unique identifiers for tracks using a HashSet for fast access and avoidance of duplicates.
    private final HashSet<String> albumsID = new HashSet<>();
    private List<Album> albums = new ArrayList<>(); // List to hold the album objects in memory.
    private final Scanner entry = new Scanner(System.in);// Scanner object for reading input from the console.

    // Public method to save the current state of albums to a file.
    public void save(String filename){
        saveData(albums,filename);
    }
    // Private static method that handles the actual saving of album data to a file.
    private static void saveData(List<Album> albums, String filename){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))){// Try-with-resources to ensure proper resource management, automatically closing the stream.
            oos.writeObject(albums);// Serialize the list of albums and write them to the specified file.
        }catch (IOException e){// Catch any IO exceptions and print an error message indicating failure.
            System.out.println("*** Data was not saved properly !!! ***");
        }
    }
    // Public method to load album data from a specified file.
    public List<Album> loadData(String filename) {
        File file = new File(filename);// Create a File object to represent the filename.
        if (!file.exists()) {// Check if the file exists to avoid FileNotFoundException.
            System.out.println("No data file found, starting with an empty list.");
            return new ArrayList<>();// Return an empty list if no file exists.
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            List<Album> loadedAlbums = (List<Album>) ois.readObject();// Deserialize the object read from the file as a List of Albums.
            System.out.println("Loaded " + loadedAlbums.size() + " albums.");// Inform the user about the number of albums loaded.
            return loadedAlbums;// Return the loaded albums
        } catch (IOException | ClassNotFoundException e) {// Catch exceptions related to file input/output errors or class mismatches during deserialization.
            System.out.println("*** Data was not loaded properly !!! ***");
            return new ArrayList<>();// Return an empty list if there is an error during load.
        }
    }
    // Setter method to replace the current list of albums with a new list.
    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
    // Method to retrieve the album ID based on its index in the list.
    public String getAlbumIDByIndex(int index){
        int currentIndex = 0;// Initialize counter to track the current index in the loop.
        for (Album album : albums){// Iterate through the list of albums.
            if (currentIndex == index){// Check if the current index matches the requested index.
                return album.albumID();// Return the album ID if a match is found.
            }
            currentIndex++;// Increment the index counter.
        }
        return null;// Return null if no album is found at the given index.
    }
    // Retrieves a track's ID by its index within a specific album using the album's ID.
    public String getTrackByIndex(int index, String albumID){
        Album album = findAlbumByID(albumID);// Find the album object based on the provided album ID.
        int currentIndex= 0;//Initialize a counter to iterate through the tracks.
        if (album!=null){// Ensure the album is found
            List<Track>tracks = album.tracks();// Retrieve the list of tracks from the album.
            for (Track track : tracks){// Loop through each track in the album.
                if (currentIndex == index){// Check if the current index matches the requested index.
                    return track.trackID();// Return the track's ID if the index matches.
                }
                currentIndex++;// Increment the index for the next track.
            }
        }
        return null;// Return null if no track is found at the specified index or the album is null.
    }

    //Removes an album from the albums collection based on the albumID.
    public void deleteAlbumByAlbumID(String albumID){
        albums.removeIf(album -> album.albumID().equals(albumID));// Use lambda to remove the album if its ID matches the specified ID.
        System.out.println("_Album is successfully deleted_");
    }
    // Deletes a track from a specific album using the album's and track's IDs
    public void deleteTrackFromAlbum(String albumID, String trackID){
     Album album = findAlbumByID(albumID);// Find the album based on the given ID.
     if (album != null){// Ensure the album is found.
         List<Track>tracks = album.tracks();// Get the list of tracks from the album.
         tracks.removeIf(track -> track.trackID().equals(trackID));// Remove the track if its ID matches the given track ID.
     }
    }
    // Checks if there are any albums in the albums list.
    public boolean isAlbum(){
        return !albums.isEmpty();// Returns true if the list of albums is not empty.
    }
    // Checks if a specified album contains any tracks
    public boolean isTrack(String albumID){
        Album album = findAlbumByID(albumID);// Find the album based on the ID.
        assert album != null;// Assert that the album is not null
        return !album.tracks().isEmpty();// Return true if the album has one or more tracks.
    }
    // Prints all tracks in a specific album identified by the album's ID.
    public void printTracksInAlbum(String albumID){
        int index = 0;
        Album album = findAlbumByID(albumID);// Find the album based on the given ID.
        if (album!=null){
            List<Track>tracks = album.tracks();// Access the list of tracks from the album.
            for (Track track : tracks){// Iterate over each track in the album.
                System.out.println("index: ["+index+"] Track: " + track.title() + ", Duration: " + String.format("%02d",track.minutes())+":"+String.format("%02d", track.seconds())+", mp3Name: "+track.mp3FileName());
                index++;// Increment the index for the next track.
            }
        }
    }
    // Prints details of a specific track in a specified album.
    public void printOneTrackInAlbum(String albumID, String trackID){
        Album album = findAlbumByID(albumID);// Find the album based on the given ID.
        if (album !=null){
            List<Track>tracks = album.tracks();// Get the list of tracks from the album.
            for (Track track : tracks){// Iterate through the tracks in the album.
                if (track.trackID().equals(trackID)){// Check if the current track's ID matches the requested track ID.
                    System.out.println("\nTrack: "+track.title() + "\nTime: "+String.format("%02d",track.minutes())+":"+String.format("%02d", track.seconds())+"\n["+track.mp3FileName()+"]\nTrack ID: "+ track.trackID());
                }
            }
        }
    }
    // Prints details of all tracks across all albums.
    public void printAllTracks() {
        int trackNumber = 1;  // Start counting tracks from number 1.
        for (Album album : albums) {// Iterate through all albums.
            List<Track> trackList = album.tracks();  // Retrieve the list of tracks for each album.
            for (Track track : trackList) {// Iterate through each track in the current album.
                // Print each track with its number and detailed information.
                System.out.println(trackNumber + ". " + track.title() + " - " + String.format("%02d",track.minutes())+ ":" + String.format("%02d", track.seconds()) + " [" + track.mp3FileName() + "]");
                trackNumber++;  // Increment track number for the next track.
            }
        }
        if (trackNumber == 1) {  // Check if no tracks were printed (which means no tracks were found).
            System.out.println("No tracks found in any album.");
        }
    }

    // Prints all albums with basic details.
    public void printAllAlbum(){
        int index = 0;
        if (albums.isEmpty()){// Check if there are no albums.
            System.out.println("--No data--");
        }else {
            for (Album album : albums){// Iterate through each album in the list.
                System.out.println("index: [" +index+"] Album: "+ album.title() +" ,Duration: "+ album.getTotalDuration());
                index++;// Increment index for the next album.
            }
        }
    }
    // Prints detailed information of a specific album including all its tracks.
    public void printOneAlbumWithTracks(String albumID){
        int index = 1;
        Album album = findAlbumByID(albumID);// Find the album by its ID.
        if (album!=null){// Check if the album was found
            List<Track>tracks = album.tracks();// Get the list of tracks from the album.
            System.out.println("\n___Album: "+album.title()+ ", Artist: "+album.artist()+", Duration: "+album.getTotalDuration());
            for (Track track : tracks){// Iterate through each track in the album.
                System.out.println(index + " Track: " + track.title()+", Time: "+String.format("%02d",track.minutes()) + ":"+String.format("%02d", track.seconds())+", mp3 File: "+track.mp3FileName());
                index++;// Increment index for the next track.
            }
            System.out.println();
        }
    }
    // Updates the details of a specific album.
    public void editAlbumDetails(String albumID, String newTitle, String newArtist) {
        Album albumToEdit = findAlbumByID(albumID);// Locate the album by its ID.
        if (albumToEdit != null) {
            // Create a new instance of Album with updated title and artist, but retain the old tracks.
            Album updatedAlbum = new Album(albumID, newTitle, newArtist, albumToEdit.tracks());
            updateAlbumInList(albumToEdit, updatedAlbum);// Replace the old album with the updated one in the list.
            System.out.println("Album updated successfully.");
        } else {
            System.out.println("Album not found.");
        }
    }
    // Edits details of a specific track within a given album.
    public void editTrackInAlbum(String albumID, String trackID, String newTitle, String newMp3FileName, int newMinutes, int newSeconds) {
        Album album = findAlbumByID(albumID);// Find the album using the provided ID.
        if (album != null) {// Check if the album exists.
            List<Track> updatedTracks = new ArrayList<>(album.tracks());// Create a modifiable list of tracks from the album.
            for (int i = 0; i < updatedTracks.size(); i++) {// Iterate through the tracks.
                Track track = updatedTracks.get(i);// Get the current track.
                if (track.trackID().equals(trackID)) {// Check if it's the track to update.
                    // Create a new track instance with updated details.
                    Track updatedTrack = track.changeTitle(newTitle)
                            .changeMP3File(newMp3FileName)
                            .changeTime(newMinutes, newSeconds);
                    updatedTracks.set(i, updatedTrack); // Replace the old track with the updated one.
                    break;
                }
            }
            // Update the album with the new list of tracks.
            Album updatedAlbum = new Album(album.albumID(), album.title(), album.artist(), updatedTracks);
            updateAlbumInList(album, updatedAlbum);// Update the main list of albums with the new album details.
        } else {
            System.out.println("Album not found.");
        }
    }

    // Updates the main album list with new album details.
    private void updateAlbumInList(Album oldAlbum, Album newAlbum) {
        int index = albums.indexOf(oldAlbum);// Find the index of the old album in the list.
        if (index != -1) {
            albums.set(index, newAlbum); // Replace the old album with the new one if found.
        } else {
            System.out.println("Failed to update the album. Album not found in the list.");
        }
    }

    // Adds a new album to the collection with the ability to add tracks interactively.
    public void addNewAlbum(){
        String titleAlbum, albumID, artist, input;
        System.out.print("Enter title for new Album: ");
        input = readLine();// Read album title from user input.
        titleAlbum = input.substring(0,1).toUpperCase() + input.substring(1); // Capitalize first letter

        System.out.print("Enter artist: ");
        input = readLine();
        artist = input.substring(0,1).toUpperCase() + input.substring(1);

        albumID = randomAlbumID();// Generate a random unique album ID.
        addAlbumToClass(titleAlbum,albumID,artist);// Add the new album to the list.
        do{
            addNewTrack(albumID);// Add tracks to the new album.
            System.out.println("\nDo you want add next Track? (y/n): ");
            input = readLine(); // Ask if the user wants to continue adding tracks.
            switch (input){case "y": break; case "n": break; default: System.out.println("Enter only [y] for yes or [n] for no");break;}
        }while(input.equals("y"));// Continue while the user chooses 'yes'.
    }
    // Adds a new track to a specific album.
    public void addNewTrack(String albumID){
        if (albumID == null){
            System.out.println("\n*** Wrong ID ***\n");
        }else {
            String titleTrack, trackID, mp3FileName, input;
            int minutes, seconds;
            System.out.print("Enter title of track: ");
            input = readLine();// Read track title from user input.
            titleTrack = input.substring(0,1).toUpperCase() + input.substring(1);// Capitalize first letter
            System.out.print("Enter mp3FileName: ");
            mp3FileName = readLine().strip() + ".mp3";
            trackID = randomTrackID();// Generate a random unique track ID.
            System.out.print("Enter the minutes the Track: ");
            minutes = checkTime("minutes");// Read and validate the minutes.
            System.out.print("Enter the seconds the Track: ");
            seconds = checkTime("seconds");// Read and validate the seconds.
            addTrackToAlbum(albumID,trackID,titleTrack,mp3FileName,minutes,seconds);// Add the track to the album.
        }

    }
    // Validates a time input ensuring it's within a valid range.
    private int checkTime(String option){
        while (true){
            int input = intEntry();// Read the input time.
            if (input >= 0 && input < 60){
                return input;// Return the input if it's within the valid range.
            }else {
                System.out.println("*** Enter "+option+" only between 0 - 60 ***");
            }
        }
    }

    // Method to add a new album to the Class Album
    private void addAlbumToClass(String titleAlbum, String albumID, String artist){
        Album newAlbum = new Album(albumID, titleAlbum,artist,null);// Create a new Album object with the provided details.
        albums.add(newAlbum);// Add the new album to the list of albums.
        System.out.println("New album: "+ titleAlbum +" is created");
    }

    private void addTrackToAlbum(String albumID, String trackID, String titleTrack, String mp3FileName, int minutes, int seconds) {
        Album album = findAlbumByID(albumID);
        if (album != null) {
            List<Track> updatedTracks = new ArrayList<>(album.tracks());  // Create a modifiable list of tracks from the album.
            Track newTrack = new Track(trackID, titleTrack, mp3FileName, minutes, seconds);
            updatedTracks.add(newTrack);  // add a new track to the list
            Album updatedAlbum = new Album(album.albumID(), album.title(), album.artist(), updatedTracks);  //  create a new Album record with the updated list
            updateAlbumInList(album, updatedAlbum);  // Replace the old album in the list with the new one
        } else {
            System.out.println("*** Album not exist ***");
        }
    }





    // Method to find a album by ID.
    private Album findAlbumByID(String albumID) {
        for (Album album : albums) {// Iterate through the list of albums.
            if (album.albumID().equals(albumID)) {
                return album;// Return the album if found.
            }
        }
        return null;
    }


    // Method for close Scanner use only one when program is exiting
    public void closeScanner(){
        //Scanner entry = new Scanner(System.in);
        entry.close();
    }
    // read String input
    public String readLine() {
        return entry.nextLine();
    }

    // check if input is integer
    public int intEntry(){
        int number;
        while (true){
            try {
                number = entry.nextInt();
                break;
            }catch (InputMismatchException e){
                System.out.println("*** Enter only integer number ***");
                entry.nextLine();
            }
        }
        entry.nextLine();
        return number;
    }

    // read manual with different language mode
    public void readFile(String language){
        try {
            File manual = new File(language+".txt");
            Scanner myReader = new Scanner(manual);
            while (myReader.hasNextLine()){
                String data = myReader.nextLine();
                System.out.println(data);
            }
        }catch (FileNotFoundException e){
            System.out.println("Manual not exist !");
        }
    }

    // Method to generate a random client ID.
    private String randomTrackID() {
        int min = 1000, max = 9999;// Define the range for client IDs.
        String random;// Variable to hold the random ID.
        do {
            int randomNumber = (int) (Math.floor(Math.random() * (max - min + 1) + min));// Generate a random number within the specified range.
            random = String.valueOf(randomNumber);
        } while (tracksID.contains(random));// Ensure the ID is unique.
        tracksID.add(random);// Add the new ID to the set of tracks.
        return random;// Return the unique client ID.
    }

    // Method to generate a random account number.
    private String randomAlbumID(){
        int min= 1, max = 999;
        String random;
        do{
            int randomNumber = (int) (Math.floor(Math.random() * (max - min + 1) + min));
            random = String.valueOf(randomNumber);
        }while (albumsID.contains(random));// Ensure the account number is unique.
        albumsID.add(random);// Add the new number to the set of album ID.
        return random;// Return the unique account number.
    }

}
