package manager.club;

import manager.team.Team;

import java.util.ArrayList;
import java.util.List;

public record Club(String uniqueID, String nameClub, int yearOfEstablishment, List<Team>teams) {
    public Club(String uniqueID, String nameClub, int yearOfEstablishment, List<Team>teams){
        this.nameClub = nameClub;
        this.uniqueID = uniqueID;
        this.yearOfEstablishment = yearOfEstablishment;
        this.teams = (teams != null ? new ArrayList<>(teams) : new ArrayList<>());
    }
}

