package manager.team;

import manager.player.Player;
import manager.trainer.Trainer;

import java.util.ArrayList;
import java.util.List;

public record Team(String uniqueID, String nameTeam, List<Player>players, List<Trainer>trainers) {
    public Team(String uniqueID, String nameTeam, List<Player>players, List<Trainer>trainers){
        this.nameTeam = nameTeam;
        this.uniqueID = uniqueID;
        this.players = (players != null ? new ArrayList<>(players):new ArrayList<>());
        this.trainers = (trainers != null ? new ArrayList<>(trainers): new ArrayList<>());
    }
}
