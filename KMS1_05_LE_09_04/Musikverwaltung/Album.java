import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public record Album(String albumID, String title, String artist, List<Track> tracks) implements Serializable {
    public Album(String albumID, String title, String artist, List<Track> tracks){
        this.title = title;
        this.albumID = albumID;
        this.artist = artist;
        this.tracks = (tracks != null ? new ArrayList<>(tracks) : new ArrayList<>());
    }
    public String getTotalDuration() {
        int totalMinutes = 0;
        int totalSeconds = 0;

        for (Track track : tracks) {
            totalMinutes += track.minutes();
            totalSeconds += track.seconds();
        }

        totalMinutes += totalSeconds / 60;
        totalSeconds = totalSeconds % 60;

        return String.format("%02d",totalMinutes) + " minutes " + String.format("%02d",totalSeconds) + " seconds";
    }
}
