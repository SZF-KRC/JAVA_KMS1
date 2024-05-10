package manager;

import manager.club.Club;
import manager.club.FunctionClub;
import manager.database.Database;
import manager.function.Function;
import manager.menu.Menu;
import manager.player.FunctionPlayer;
import manager.player.Player;
import manager.team.FunctionTeam;
import manager.trainer.FunctionTrainer;
import manager.trainer.Trainer;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create main services and database
        Database data = new Database();
        Function function = new Function();

        // Create functional services with necessary dependencies

        FunctionClub functionClub = new FunctionClub(data, function);
        FunctionTeam functionTeam = new FunctionTeam(data, function, functionClub);
        FunctionPlayer functionPlayer = new FunctionPlayer(data, function, functionTeam);
        FunctionTrainer functionTrainer = new FunctionTrainer(data, function, functionTeam);

        // Loading data from the database
        List<Club> clubs = data.loadClubsAndTeamsFromDatabase();
        List<Player> freePlayers = data.loadFreePlayers();
        List<Trainer> freeTrainers = data.loadFreeTrainers();

        // Setting data to the respective services
        functionClub.setClubs(clubs);
        functionPlayer.setFreePlayers(freePlayers);
        functionTrainer.setFreeTrainers(freeTrainers);

        // Create the main menu and start the main program loop
        Menu menu = new Menu(function, functionClub, functionTeam, functionPlayer, functionTrainer);
        menu.mainMenu();
    }
}

