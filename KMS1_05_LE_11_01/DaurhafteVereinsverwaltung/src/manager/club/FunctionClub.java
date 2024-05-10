package manager.club;

import manager.database.Database;
import manager.function.Function;

import java.util.ArrayList;
import java.util.List;

public class FunctionClub {
    private List<Club> clubs;
    private final Database data ;
    private final Function function ;

    // Constructor to initialize the FunctionClub with a database and function utility.
    public FunctionClub(Database data,Function function){
        this.clubs = new ArrayList<>();
        this.data = data;
        this.function = function;
    }

    // Adds a new club to the system with the specified name and year of establishment.
    public void addClubToSystem(String nameClub, int year){
        Club club = new Club(function.generateUniqueID(), nameClub,year, null);// Generate a unique ID for the new club and create a new club instance.
        clubs.add(club);// Add the new club to the list of clubs.
        data.addClubToDatabase(club);// Persist the new club to the database.
    }

    // Edits an existing club's details in the system.
    public void editClubInSystem(Club club, String nameClub, int yearOfEstablishment){
        Club updatedClub = new Club(club.uniqueID(), nameClub,yearOfEstablishment,club.teams()); // Create an updated club instance with new details.
        updateClubInList(club,updatedClub);// Update the club in the local list.
        data.editClubInDatabase(updatedClub);// Update the club details in the database.
    }

    // Removes a club from the system.
    public void removeClubFromSystem(Club club){
        clubs.remove(club);
        data.deleteClubAndRelatedEntities(club.uniqueID());
    }

    // Updates a club in the local list of clubs.
    public void updateClubInList(Club oldClub, Club newClub){
        int index = clubs.indexOf(oldClub);
        if (index != -1){
            clubs.set(index,newClub);
        }
    }

    // Prints all clubs with their indexes
    private void printClubsWithIndex(){
        System.out.println("\n*** Clubs ***");
        for (int i = 0;i<clubs.size();i++){
            System.out.println("index: ["+i+"] Club: " + clubs.get(i).nameClub()+", Established: "+clubs.get(i).yearOfEstablishment());
        }
    }

    // Finds a club by its index in the list.
    public Club findClubByIndex(int index){
        if (index < 0 || index >= clubs.size()) {
            System.out.printf("\n--- Invalid index: %d. Index must be between 0 and %d. ---\n", index, clubs.size() - 1);
            return null;
        }
        return clubs.get(index);
    }

    // Checks if there are any clubs and prints them.
    public boolean checkAndPrintClubs(){
        if (clubs.isEmpty()){
            System.out.println("\n--- No Clubs in database ---\n");
            return false;
        }
        printClubsWithIndex();
        return true;
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
    }
}

