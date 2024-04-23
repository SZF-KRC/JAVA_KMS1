import java.util.ArrayList;
import java.util.List;

public class Club {
    private String nameClub;
    private int yearOfEstablishment;
    private List<Team>teams;

    public Club(String nameClub, int yearOfEstablishment) {
        this.nameClub = nameClub;
        this.yearOfEstablishment = yearOfEstablishment;
        this.teams = new ArrayList<>();
    }
    public void addTeam(Team team){
        teams.add(team);
    }

    public void removeTeam(Team team){
        teams.remove(team);
    }

    public String getNameClub() {
        return nameClub;
    }

    public List<Team> getTeams(){
        return teams;
    }

    public int getYearOfEstablishment() {
        return yearOfEstablishment;
    }


    @Override
    public String toString() {
        return "Club{" +
                "nameClub='" + nameClub + '\'' +
                ", yearOfEstablishment=" + yearOfEstablishment +
                ", teams=" + teams +
                '}';
    }
}
