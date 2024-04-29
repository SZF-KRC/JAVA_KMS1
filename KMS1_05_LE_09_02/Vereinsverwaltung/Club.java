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


    public void addPlayerToTeam(Player player, Team team){
        if (!teams.contains(team)){
            teams.add(team);
        }
        team.addPlayer(player);
    }

    public void addTrainerToTeam(Trainer trainer, Team team){
        if (!teams.contains(team)){
            teams.add(team);
        }
        team.addTrainer(trainer);
    }
    public void removePlayerFromTeam(Player player, Team team){
        if (teams.contains(team)){
            team.removePlayer(player);
        }
    }
    public void removeTrainerFromTeam(Trainer trainer, Team team){
        if (teams.contains(team)){
            team.removeTrainer(trainer);
        }
    }

    public void addTeam(Team team){
        teams.add(team);
    }

    public void removeTeam(Team team){
        teams.remove(team);
    }

    public List<Team> getTeams(){
        return teams;
    }

    @Override
    public String toString() {
        return  nameClub +
                ", year of establishment= " + yearOfEstablishment +
                ", Teams=" + teams +
                '}';
    }
}
