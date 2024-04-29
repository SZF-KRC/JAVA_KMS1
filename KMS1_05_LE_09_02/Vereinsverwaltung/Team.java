import java.util.ArrayList;
import java.util.List;

public class Team {
    private String nameTeam;
    private List<Player> players;
    private List<Trainer> trainers;

    public Team(String nameTeam) {
        this.nameTeam = nameTeam;
        this.players = new ArrayList<>();
        this.trainers = new ArrayList<>();
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void removePlayer(Player player){
        players.remove(player);
    }

    public void addTrainer(Trainer trainer){
        trainers.add(trainer);
    }

    public void removeTrainer(Trainer trainer){
        trainers.remove(trainer);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Trainer> getTrainers(){
        return trainers;
    }

    @Override
    public String toString() {
        return  nameTeam ;
    }
}
