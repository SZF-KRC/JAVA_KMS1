package manager.player;
import manager.function.Function;
import manager.club.Club;
import manager.database.Database;
import manager.team.FunctionTeam;
import manager.team.Team;

import java.util.ArrayList;
import java.util.List;

public class FunctionPlayer {
    private List<Player> freePlayers;
    private final Database data ;
    private final Function function ;
    private final FunctionTeam functionTeam ;

    public FunctionPlayer(Database data, Function function, FunctionTeam functionTeam){
        this.freePlayers = new ArrayList<>();
        this.data = data;
        this.function = function;
        this.functionTeam = functionTeam;

    }

    public void addFreePlayer(String namePlayer, String surnamePlayer, String position){
        Player newPlayer = new Player(function.generateUniqueID(), namePlayer,surnamePlayer,position);
        freePlayers.add(newPlayer);
        data.addFreePlayerToDatabase(newPlayer);
    }

    public void assignPlayerToTeam(Club club, Team team, Player player){
        List<Player> updatedPlayers = new ArrayList<>(team.players());
        updatedPlayers.add(player);
        Team updatedTeam = new Team(team.uniqueID(), team.nameTeam(), updatedPlayers,team.trainers());
        functionTeam.updateTeamInClub(club,team,updatedTeam);
        freePlayers.remove(player);
        data.assignPlayerToTeamInDatabase(player.uniqueID(),team.uniqueID());
    }

    public void unassignPlayerFromTeam(Club club, Team team, Player player){
        List<Player> updatedPlayers = new ArrayList<>(team.players());
        updatedPlayers.remove(player);
        Team updatedTeam = new Team(team.uniqueID(), team.nameTeam(),updatedPlayers,team.trainers());
        functionTeam.updateTeamInClub(club,team,updatedTeam);
        freePlayers.add(player);
        data.unAssignPlayerFromTeamToFreeInDatabase(player.uniqueID());
    }

    public void editFreePlayerInSystem(Player player, String namePlayer, String surnamePlayer, String position){
        Player updatedPlayer = new Player(player.uniqueID(),namePlayer,surnamePlayer,position);
        freePlayers.replaceAll(newPlayer -> newPlayer.uniqueID().equals(player.uniqueID())? updatedPlayer : newPlayer);
        data.editPlayerInDatabase(updatedPlayer);
    }

    public void editPlayerInSystem(Club club, Team team, Player player, String namePlayer, String surnamePlayer, String position){
        List<Player> updatedPlayers = new ArrayList<>(team.players());
        Player updatedPlayer = new Player(player.uniqueID(), namePlayer, surnamePlayer, position);
        updatedPlayers.replaceAll(newPlayer -> newPlayer.uniqueID().equals(player.uniqueID())? updatedPlayer : newPlayer);
        Team updatedTeam = new Team(team.uniqueID(),team.nameTeam(),updatedPlayers,team.trainers());
        functionTeam.updateTeamInClub(club,team,updatedTeam);
        data.editPlayerInDatabase(updatedPlayer);
    }

    public void removeFreePlayer(Player freePlayer){
        freePlayers.remove(freePlayer);
        data.deletePlayer(freePlayer.uniqueID());
    }

    public void removePlayerFromClub(Club club,Team team, Player player) {
        List<Player> updatedPlayers = new ArrayList<>(team.players());
        boolean removed = updatedPlayers.remove(player);
        if (removed) {
            Team updatedTeam = new Team(team.uniqueID(), team.nameTeam(), updatedPlayers, team.trainers());
            functionTeam.updateTeamInClub(club, team, updatedTeam);
            data.deletePlayer(player.uniqueID());
        }
    }

    public void transferPlayer(Club oldClub, Club newClub, Team oldTeam, Team newTeam, Player player, String option){
        switch (option){
            case "toFreePlayers":
                unassignPlayerFromTeam(oldClub, oldTeam, player);
                break;
            case "toAnotherClub":
                unassignPlayerFromTeam(oldClub,oldTeam,player);
                assignPlayerToTeam(newClub, newTeam, player);
                break;
        }
    }

    private void printFreePlayerWithIndex(){
        System.out.println("\n*** Players in free List ***");
        for (int i = 0; i < freePlayers.size();i++){
            System.out.println("index: ["+i+"] free Player: "+freePlayers.get(i).namePlayer()+" "+freePlayers.get(i).surnamePlayer()+", position: "+freePlayers.get(i).position());
        }
    }
    private void printPlayersInTeamWithIndex(Team team){
        System.out.println("\n*** Players in club "+team.nameTeam()+" ***");
        for (int i = 0; i<team.players().size();i++){
            System.out.println("index: ["+i+"] Player: "+team.players().get(i).namePlayer()+" "+team.players().get(i).surnamePlayer()+", position: "+team.players().get(i).position());
        }
    }
    public Player findPlayerByIndex(Team team, int index){
        if (index < 0 || index >= team.players().size()) {
            System.out.printf("\n--- Invalid index: %d. Index must be between 0 and %d. ---\n", index, team.players().size() - 1);
            return null;
        }
        return team.players().get(index);
    }
    public Player findFreePlayerByIndex(int index){
        if (index < 0 || index >= freePlayers.size()) {
            System.out.printf("\n--- Invalid index: %d. Index must be between 0 and %d. ---\n", index, freePlayers.size() - 1);
            return null;
        }
        return freePlayers.get(index);
    }
    public boolean checkAndPrintPlayersInTeam(Team team){
        if (team.players().isEmpty()){
            System.out.println("\n--- No Players in Team ---\n");
            return false;
        }
        printPlayersInTeamWithIndex(team);
        return true;
    }
    public boolean checkAndPrintFreePlayer(){
        if (freePlayers.isEmpty()){
            System.out.println("\n--- No free Players ---\n");
            return false;
        }
        printFreePlayerWithIndex();
        return true;
    }
    public void setFreePlayers(List<Player> freePlayers) {
        this.freePlayers = freePlayers;
    }

}
