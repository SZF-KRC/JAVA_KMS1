import java.io.Serializable;

public record Track(String trackID, String title, String mp3FileName, int minutes, int seconds) implements Serializable {
    public Track changeTime(int newMinutes, int newSeconds){
        if (newMinutes > 0 && newSeconds > 0){
            return new Track(trackID, title, mp3FileName, newMinutes, newSeconds);
        }else {
            return this;
        }
    }
    public Track changeTitle(String newTitle){
        return new Track(trackID, newTitle, mp3FileName, minutes, seconds);
    }
    public Track changeMP3File(String newMp3FileName){
        return new Track(trackID, title, newMp3FileName, minutes, seconds);
    }
}
