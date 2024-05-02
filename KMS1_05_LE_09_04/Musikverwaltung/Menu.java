
public class Menu {
    private final Function function;

    public Menu(Function function){
        this.function = function;
    }

    public void mainMenu(){
        boolean exit =false;
        while (!exit){
            System.out.print("\n___Music management___\n[1] Album management\n[2] Track management\n[3] Manual\n[0] Exit program\nEnter index of your choice: ");
            switch (function.intEntry()){
                case 1: universalMenu("Album");break;
                case 2: universalMenu("Track");break;
                case 3: printManual();break;
                case 0: exit=true;function.save("albums.ser");function.closeScanner();break;
                default: System.out.println("Enter index only from 0 - 3");break;
            }
        }
    }
    private void universalMenu(String option){
        boolean exit = false;
        while (!exit){
            System.out.print("\n___" + option + " management___\n[1] add "+ option + "\n[2] delete "+option+"\n[3] print one "+option+
                    "\n[4] print all "+option+"s\n[5] edit "+option+"\n[0] back to main menu\nEnter index of your choice: ");
            switch (function.intEntry()){
                case 1:
                    switch (option){// add Album/Track
                        case "Album": function.addNewAlbum();break;
                        case "Track": addTrack();break;
                    }
                    break;
                case 2:
                    switch (option){// delete Album/Track
                        case "Album": deleteAlbum();break;
                        case "Track": deleteTrack();break;
                    }
                    break;
                case 3:
                    switch (option){// print one Album/Track
                        case "Album": printOneAlbum();break;
                        case "Track": printOneTrack();break;
                    }
                    break;
                case 4:
                    switch (option){ // print all Album/Track
                        case "Album": function.printAllAlbum();break;
                        case "Track": function.printAllTracks();break;
                    }
                    break;
                case 5:
                    switch (option){ // edit Album/Track
                        case "Album":editAlbum();break;
                        case "Track":editTrack();break;
                    }
                    break;
                case 0:exit=true;break;// exit program
                default: System.out.println("Enter index only from 0 - 5");break;
            }
        }
    }

    private void printManual(){
        System.out.print("\n___Manual___\n[1] English manual\n[2] Deutsches Handbuch\nEnter index of your choice: ");
        switch (function.intEntry()){
            case 1:function.readFile("english");break;
            case 2:function.readFile("deutsch");break;
            default: System.out.println("Enter index only from 1 or 2");break;
        }
    }

    private void addTrack() {
        if (checkAndPrintAlbums()) {
            String albumID = promptForAlbum();
            function.addNewTrack(albumID);
        }
    }

    private void deleteAlbum() {
        if (checkAndPrintAlbums()) {
            String albumID = promptForAlbum();
            function.deleteAlbumByAlbumID(albumID);
        }
    }

    private void deleteTrack() {
        if (checkAndPrintAlbums()) {
            String albumID = promptForAlbum();
            String trackID = promptForTrack(albumID);
            if (trackID != null) {
                function.deleteTrackFromAlbum(albumID, trackID);
                System.out.println("Track deleted successfully.");
            }
        }
    }

    private void printOneAlbum() {
        if (checkAndPrintAlbums()) {
            String albumID = promptForAlbum();
            function.printOneAlbumWithTracks(albumID);
        }
    }

    private void printOneTrack() {
        if (checkAndPrintAlbums()) {
            String albumID = promptForAlbum();
            String trackID = promptForTrack(albumID);
            if (trackID != null) {
                function.printOneTrackInAlbum(albumID, trackID);
            }
        }
    }

    private void editAlbum() {
        if (checkAndPrintAlbums()) {
            String albumID = promptForAlbum();
            System.out.print("Enter new title: ");
            String title = function.readLine();
            System.out.print("Enter new artist: ");
            String artist = function.readLine();
            function.editAlbumDetails(albumID, title, artist);
        }
    }

    private void editTrack() {
        if (checkAndPrintAlbums()) {
            String albumID = promptForAlbum();
            String trackID = promptForTrack(albumID);
            if (trackID != null) {
                System.out.print("Enter new title: ");
                String title = function.readLine();
                System.out.print("Enter new mp3 file name: ");
                String mp3File = function.readLine();
                System.out.print("Enter new minutes: ");
                int minutes = function.intEntry();
                System.out.print("Enter new seconds: ");
                int seconds = function.intEntry();
                function.editTrackInAlbum(albumID, trackID, title, mp3File, minutes, seconds);
            }
        }
    }


    private boolean checkAndPrintAlbums() {
        if (!function.isAlbum()) {
            System.out.println("\n*** No Albums in database ***\n");
            return false;
        }
        function.printAllAlbum();
        return true;
    }

    private String promptForAlbum() {
        System.out.print("Enter index of Album: ");
        return function.getAlbumIDByIndex(function.intEntry());
    }

    private String promptForTrack(String albumID) {
        if (!function.isTrack(albumID)) {
            System.out.println("\n*** No Tracks in Album ***");
            return null;
        }
        function.printTracksInAlbum(albumID);
        System.out.print("Enter index of Track: ");
        return function.getTrackByIndex(function.intEntry(), albumID);
    }
}
