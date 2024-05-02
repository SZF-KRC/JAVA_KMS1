import java.util.List;

public class Main {
    public static void main(String[] args) {
        Function function = new Function();
        List<Album> albums = function.loadData("albums.ser");
        function.setAlbums(albums);

        Menu menu = new Menu(function);
        menu.mainMenu();
    }
}
