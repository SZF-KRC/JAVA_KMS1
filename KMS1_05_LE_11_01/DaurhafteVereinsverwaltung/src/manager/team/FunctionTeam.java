package manager.team;
import manager.club.FunctionClub;
import manager.database.Database;
import manager.club.Club;
import manager.function.Function;

import java.util.ArrayList;
import java.util.List;

public class FunctionTeam {
    private final Database data ;
    private final Function function;
    private final FunctionClub functionClub;

    public FunctionTeam(Database data, Function function, FunctionClub functionClub) {
        this.data = data;
        this.function = function;
        this.functionClub = functionClub;
    }

    public void addTeamToClub(Club club, String nameTeam){
        List<Team> updatedTeam = new ArrayList<>(club.teams());
        Team newTeam = new Team(function.generateUniqueID(),nameTeam, null, null);
        updatedTeam.add(newTeam);
        Club updatedClub = new Club(club.uniqueID(),club.nameClub(),club.yearOfEstablishment(), updatedTeam);
        functionClub.updateClubInList(club,updatedClub);
        data.addTeamToDatabase(newTeam,club.uniqueID());
    }

    public void editTeamInSystem(Club club, Team team, String nameTeam){
        Team updatedTeam = new Team(team.uniqueID(), nameTeam, team.players(),team.trainers());
        updateTeamInClub(club,team,updatedTeam);
        data.editTeamInDatabase(updatedTeam);
    }

    public void removeTeamFromClub(Club club, Team team) {
        List<Team> currentTeams = club.teams();
        boolean removed = currentTeams.remove(team);
        if (removed) {
            Club updatedClub = new Club(club.uniqueID(), club.nameClub(), club.yearOfEstablishment(), currentTeams);
            functionClub.updateClubInList(club, updatedClub);
            data.deleteTeamAndMembers(team.uniqueID());
        }
    }

    public void updateTeamInClub(Club club, Team oldTeam, Team newTeam){
        List<Team> updatedTeams = new ArrayList<>(club.teams());
        updatedTeams.replaceAll(team -> team.uniqueID().equals(oldTeam.uniqueID())? newTeam : team);
        Club updatedClub = new Club(club.uniqueID(),club.nameClub(),club.yearOfEstablishment(),updatedTeams);
        functionClub.updateClubInList(club,updatedClub);
    }

    private void printTeamsWithIndex(Club club){
        List<Team>teams = club.teams();
        System.out.println("\n*** Teams in club "+club.nameClub()+" ***");
        for (int i = 0; i<teams.size();i++){
            System.out.println("index: ["+i+"] Team: "+ teams.get(i).nameTeam());
        }
    }

    public Team findTeamByIndex(Club club, int index){
        if (index < 0 || index >= club.teams().size()){
            System.out.printf("\n--- Invalid index: %d. Index must be between 0 and %d. ---\n", index, club.teams().size() - 1);
            return null;
        }
        return club.teams().get(index);
    }

    public boolean checkAndPrintTeams(Club club){
        if (club.teams().isEmpty()){
            System.out.println("\n--- No Teams in CLub ---\n");
            return false;
        }
        printTeamsWithIndex(club);
        return true;
    }
}
