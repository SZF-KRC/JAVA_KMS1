package manager.trainer;
import manager.club.Club;
import manager.database.Database;
import manager.function.Function;
import manager.team.FunctionTeam;
import manager.team.Team;

import java.util.ArrayList;
import java.util.List;

public class FunctionTrainer {
    private List<Trainer> freeTrainers;
    private final Database data;
    private final Function function;
    private final FunctionTeam functionTeam;


    public FunctionTrainer(Database data, Function function, FunctionTeam functionTeam){
        this.freeTrainers = new ArrayList<>();
        this.data = data;
        this.function = function;
        this.functionTeam = functionTeam;
    }

    public void addFreeTrainer(String nameTrainer, String surnameTrainer){
        Trainer newTrainer = new Trainer(function.generateUniqueID(),nameTrainer,surnameTrainer);
        freeTrainers.add(newTrainer);
        data.addFreeTrainerToDatabase(newTrainer);
    }

    public void assignTrainerToTeam(Club club, Team team, Trainer trainer){
        List<Trainer> updatedTrainers = new ArrayList<>(team.trainers());
        updatedTrainers.add(trainer);
        Team updatedTeam = new Team(team.uniqueID(), team.nameTeam(), team.players(),updatedTrainers);
        functionTeam.updateTeamInClub(club,team,updatedTeam);
        freeTrainers.remove(trainer);
        data.assignTrainerToTeamInDatabase(trainer.uniqueID(),team.uniqueID());
    }

    public void unassignTrainerFromTeam(Club club, Team team, Trainer trainer){
        List<Trainer> updatedTrainers = new ArrayList<>(team.trainers());
        updatedTrainers.remove(trainer);
        Team updatedTeam = new Team(team.uniqueID(), team.nameTeam(), team.players(),updatedTrainers);
        functionTeam.updateTeamInClub(club,team,updatedTeam);
        freeTrainers.add(trainer);
        data.unAssignTrainerFromTeamToFreeInDatabase(trainer.uniqueID());
    }

    public void editTrainerInSystem(Club club, Team team, Trainer trainer, String nameTrainer, String surnameTrainer){
        List<Trainer> updatedTrainers = new ArrayList<>(team.trainers());
        Trainer updatedTrainer = new Trainer(trainer.uniqueID(), nameTrainer, surnameTrainer);
        updatedTrainers.replaceAll(newTrainer -> newTrainer.uniqueID().equals(trainer.uniqueID())? updatedTrainer : newTrainer);
        Team updatedTeam = new Team(team.uniqueID(),team.nameTeam(),team.players(),updatedTrainers);
        functionTeam.updateTeamInClub(club,team,updatedTeam);
        data.editTrainerInDatabase(updatedTrainer);
    }


    public void editFreeTrainerInSystem(Trainer trainer, String nameTrainer, String surnameTrainer){
        Trainer updatedTrainer = new Trainer(trainer.uniqueID(),nameTrainer,surnameTrainer);
        freeTrainers.replaceAll(newTrainer -> newTrainer.uniqueID().equals(trainer.uniqueID())? updatedTrainer : newTrainer);
        data.editTrainerInDatabase(updatedTrainer);
    }

    public void removeFreeTrainer(Trainer freeTrainer){
        freeTrainers.remove(freeTrainer);
        data.deleteTrainer(freeTrainer.uniqueID());
    }

    public void removeTrainerFromClub(Club club,Team team, Trainer trainer) {
        List<Trainer> updatedTrainers = new ArrayList<>(team.trainers());
        boolean removed = updatedTrainers.remove(trainer);
        if (removed) {
            // Vytvorenie nového tímu s aktualizovaným zoznamom hráčov
            Team updatedTeam = new Team(team.uniqueID(), team.nameTeam(), team.players(), updatedTrainers);
            functionTeam.updateTeamInClub(club, team, updatedTeam);  // Aktualizácia tímu v klube
            data.deleteTrainer(trainer.uniqueID());
        }
    }

    public void transferTrainer(Club oldClub, Club newClub, Team oldTeam, Team newTeam, Trainer trainer, String option){
        switch (option){
            case "toFreeTrainers":
                unassignTrainerFromTeam(oldClub,oldTeam,trainer);
                break;
            case "toAnotherClub":
                unassignTrainerFromTeam(oldClub,oldTeam,trainer);
                assignTrainerToTeam(newClub, newTeam, trainer);
                break;
        }
    }

    private void printFreeTrainerWithIndex(){
        System.out.println("\n*** Trainers in free List ***");
        for (int i = 0; i< freeTrainers.size();i++){
            System.out.println("index: ["+i+"] free Trainer: "+freeTrainers.get(i).nameTrainer()+" "+freeTrainers.get(i).surnameTrainer());
        }
    }
    private void printTrainersInTeamWithIndex(Team team){
        System.out.println("\n*** Trainers in club "+team.nameTeam()+" ***");
        for (int i = 0; i<team.trainers().size();i++){
            System.out.println("index: ["+i+"] Trainer: "+team.trainers().get(i).nameTrainer()+" "+team.trainers().get(i).surnameTrainer());
        }
    }

    public Trainer findTrainerByIndex(Team team, int index){
        if (index < 0 || index >= team.trainers().size()) {
            System.out.printf("\n--- Invalid index: %d. Index must be between 0 and %d. ---\n", index, team.trainers().size() - 1);
            return null;
        }
        return team.trainers().get(index);
    }
    public Trainer findFreeTrainerByIndex(int index){
        if (index < 0 || index >= freeTrainers.size()) {
            System.out.printf("\n--- Invalid index: %d. Index must be between 0 and %d. ---\n", index, freeTrainers.size() - 1);
            return null;
        }
        return freeTrainers.get(index);
    }

    public boolean checkAndPrintFreeTrainer(){
        if (freeTrainers.isEmpty()){
            System.out.println("\n--- No free Trainers ---\n");
            return false;
        }
        printFreeTrainerWithIndex();
        return true;
    }

    public boolean checkAndPrintTrainersInTeam(Team team){
        if (team.trainers().isEmpty()){
            System.out.println("\n--- No Trainers in Club ---\n");
            return false;
        }
        printTrainersInTeamWithIndex(team);
        return true;
    }

    public void setFreeTrainers(List<Trainer> freeTrainers) {
        this.freeTrainers = freeTrainers;
    }
}
