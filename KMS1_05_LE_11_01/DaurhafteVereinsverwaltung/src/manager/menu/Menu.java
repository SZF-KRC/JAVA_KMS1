package manager.menu;

import manager.club.Club;
import manager.function.Function;
import manager.club.FunctionClub;
import manager.player.Player;
import manager.team.FunctionTeam;
import manager.player.FunctionPlayer;
import manager.team.Team;
import manager.trainer.FunctionTrainer;
import manager.trainer.Trainer;


public class Menu {
    private final Function function;
    private final FunctionClub functionClub;
    private final FunctionTeam functionTeam;
    private final FunctionPlayer functionPlayer;
    private final FunctionTrainer functionTrainer;

    public Menu(Function function, FunctionClub functionClub, FunctionTeam functionTeam,
                FunctionPlayer functionPlayer, FunctionTrainer functionTrainer){
        this.function = function;
        this.functionClub = functionClub;
        this.functionTeam = functionTeam;
        this.functionPlayer = functionPlayer;
        this.functionTrainer = functionTrainer;
    }

    public void mainMenu(){
        boolean exit = false;
        while (!exit){
            System.out.print("\n*** Welcome in Football manager ***\n[1] Club\n[2] Team\n[3] Player\n[4] Trainer\n[5] Manual\n[0] exit program\nEnter index of your choice: ");
            switch (function.intEntry()){
                case 0:exit=true;function.closeScanner();break;
                case 1: universalMenu("Club");break;
                case 2:universalMenu("Team");break;
                case 3:universalMenu("Player");break;
                case 4:universalMenu("Trainer");break;
                case 5:manualMenu();break;
                default: System.out.println("Enter only index from 0-5 !");break;
            }
        }
    }

    private void universalMenu(String option){
        boolean exit = false;
        while (!exit){
            if (option.equals("Club") || option.equals("Team")){
                System.out.print("\n*** "+option+" menu ***\n[1] add "+option+"\n[2] delete "+option+"\n[3] show "+option+"\n[4] edit "+option+"\n[0] back to main menu\nEnter index of your choice: ");
            }else {
                System.out.print("\n*** "+option+" menu ***\n[1] add "+option+"\n[2] delete "+option+"\n[3] show "+option+"\n[4] edit "+option+"\n[5] transfer "+option+"s\n[0] back to main menu\nEnter index of your choice: ");
            }
            switch (function.intEntry()){
                case 0:exit=true;break;
                case 1:
                    switch (option){
                        case "Club": addClub();break;
                        case "Team": addTeam();break;
                        case "Player": addPlayer();break;
                        case "Trainer": addTrainer();break;
                    }
                    break;
                case 2:
                    switch (option){
                        case "Club": removeClub();break;
                        case "Team": removeTeam();break;
                        case "Player", "Trainer": removePlayerTrainerMenu(option);break;
                    }
                    break;
                case 3:
                    switch (option){
                        case "Club": showClubs();break;
                        case "Team": showTeams();break;
                        case "Player", "Trainer": showPlayerTrainerMenu(option);break;
                    }
                    break;
                case 4:
                    switch (option){
                        case "Club":editClub();break;
                        case "Team":editTeam();break;
                        case "Player", "Trainer": editPlayerTrainerMenu(option);break;
                    }
                    break;
                case 5:
                    if (!option.equals("Club") && !option.equals("Teams")){
                        transferMenu(option);
                        break;
                    }else {
                        System.out.println("\n*** Invalid option. Please enter index of your choice ***\n");
                    }
                    break;
                default:
                    System.out.println("\n*** Invalid option. Please enter index of your choice ***\n");
            }
        }
    }
    private void transferMenu(String option){
        boolean exit = false;
        while (!exit){
            System.out.print("\n*** Transfer "+option+" menu ***\n[1] assign free "+option+" to club\n[2] transfer "+option+" from club\n[0] back to "+option+" menu\nEnter index of your choice: ");
            switch (function.intEntry()){
                case 0:exit=true;break;
                case 1:
                    switch (option){
                        case "Player":assignPlayerToTeam();break;
                        case "Trainer":assignTrainerToTeam();break;
                    }
                    break;
                case 2:
                    switch (option){
                        case "Player":transferPlayer();break;
                        case "Trainer":transferTrainer();break;
                    }
                    break;
                default: System.out.println("\n--- Enter index only from 0-2 ---\n");break;
            }
        }

    }

    private void removePlayerTrainerMenu(String option){
        boolean exit = false;
        while (!exit){
            System.out.print("\n*** Remove "+option+" menu ***\n[1] remove free "+option+"\n[2] remove "+option+" from Club\n[0] back to "+option+" menu\nEnter index of your choice: ");
            switch (function.intEntry()){
                case 0:exit=true;break;
                case 1:
                    switch (option){
                        case "Player":removeFreePlayer();break;
                        case "Trainer":removeFreeTrainer();break;
                    }
                    break;
                case 2:
                    switch (option){
                        case "Player":removePlayerFromClub();break;
                        case "Trainer":removeTrainerFromClub();break;
                    }
                    break;
                default: System.out.println("\n--- Enter index only from 0-2 ---\n");break;
            }
        }
    }

    private void showPlayerTrainerMenu(String option){
        boolean exit = false;
        while (!exit){
            System.out.print("\n*** Show "+option+" menu ***\n[1] show free "+option+"\n[2] show "+option+" in Club\n[0] back to "+option+" manager\nEnter index of your choice: ");
            switch (function.intEntry()){
                case 0:exit=true;break;
                case 1:
                    switch (option){
                        case "Player":showFreePlayers();break;
                        case "Trainer":showFreeTrainers();break;
                    }
                    break;
                case 2:
                    switch (option){
                        case "Player":showPlayersInClub();break;
                        case "Trainer":showTrainersInClub();
                    }
                    break;
                default: System.out.println("\n--- Enter index only from 0-2 ---\n");break;
            }
        }
    }

    private void manualMenu(){
        boolean exit = false;
        while (!exit){
            System.out.print("\n*** Manual menu ***\n[1] english manual\n[2] deutsch manual\n[0] back to main menu\nEnter index of your choice: ");
            switch (function.intEntry()){
                case 0:exit=true;break;
                case 1:function.readFile("english");break;
                case 2:function.readFile("deutsch");break;
                default: System.out.println("\n--- Enter index only from 0-2 ---\n");break;
            }
        }
    }

    private void editPlayerTrainerMenu(String option){
        boolean exit = false;
        while (!exit){
            System.out.print("\n*** Edit "+option+" menu ***\n[1] edit free "+option+"\n[2] edit "+option+" in Club\n[0] back to main menu\nEnter index of your choice: ");
            switch (function.intEntry()){
                case 0:exit=true;break;
                case 1:
                    switch (option){
                        case "Player":editFreePlayer();break;
                        case "Trainer":editFreeTrainer();break;
                    }
                    break;
                case 2:
                    switch (option){
                        case "Player":editPlayerInClub();break;
                        case "Trainer":editTrainerInClub();
                    }
                    break;
                default: System.out.println("\n--- Enter index only from 0-2 ---\n");break;
            }
        }
    }




    private void addClub(){
        System.out.print("Enter name of Club: ");
        String nameClub = function.readLine().toUpperCase();
        System.out.print("Enter year of establishment: ");
        int year = function.intEntry();
        functionClub.addClubToSystem(nameClub,year);
    }

    private void addTeam(){
        String nameTeam;
        if (functionClub.checkAndPrintClubs()){
            System.out.print("Enter index of Club to add team: ");
            int index = function.intEntry();
            System.out.print("Enter name of team: ");
            nameTeam = function.readLine();
            Club club = functionClub.findClubByIndex(index);
            if(club !=null){
                functionTeam.addTeamToClub(club, nameTeam);
            }
        }
    }

    private void addPlayer(){
        String namePlayer, surnamePlayer, position;
        System.out.print("Enter name of new player: ");
        namePlayer = function.readLine();
        System.out.print("Enter surname of new player: ");
        surnamePlayer = function.readLine();
        System.out.print("Enter position of new player: ");
        position = function.readLine();
        functionPlayer.addFreePlayer(namePlayer,surnamePlayer,position);
    }
    private void addTrainer(){
        String nameTrainer, surnameTrainer;
        System.out.print("Enter name of new trainer: ");
        nameTrainer = function.readLine();
        System.out.print("Enter surname of new trainer: ");
        surnameTrainer = function.readLine();
        functionTrainer.addFreeTrainer(nameTrainer, surnameTrainer);
    }
    private void removeClub(){
        if (functionClub.checkAndPrintClubs()){
            System.out.print("Enter index of Club to remove: ");
            int index = function.intEntry();
            Club club = functionClub.findClubByIndex(index);
            if (club !=null){
                functionClub.removeClubFromSystem(club);
            }
        }
    }

    private void removeTeam(){
        if (functionClub.checkAndPrintClubs()){
            System.out.print("Enter index of Club where you want to remove Team: ");
            int index = function.intEntry();
            Club club = functionClub.findClubByIndex(index);
            if (club !=null){
                if (functionTeam.checkAndPrintTeams(club)){
                    System.out.print("Enter index of Team which you want delete: ");
                    index = function.intEntry();
                    Team team = functionTeam.findTeamByIndex(club,index);
                    if (team !=null){
                        functionTeam.removeTeamFromClub(club,team);
                    }
                }
            }
        }
    }

    private void removeFreePlayer(){
        if (functionPlayer.checkAndPrintFreePlayer()){
            System.out.print("Enter index of free Player you want to remove: ");
            int index = function.intEntry();
            Player freePlayer = functionPlayer.findFreePlayerByIndex(index);
            if (freePlayer!=null){
                functionPlayer.removeFreePlayer(freePlayer);
            }
        }
    }

    private void removePlayerFromClub(){
        if (functionClub.checkAndPrintClubs()){
            System.out.print("Enter index of Club where you want remove Player: ");
            int index = function.intEntry();
            Club club = functionClub.findClubByIndex(index);
            if (club!=null){
                if (functionTeam.checkAndPrintTeams(club)){
                    System.out.print("Enter index of Team: ");
                    index = function.intEntry();
                    Team team = functionTeam.findTeamByIndex(club,index);
                    if (team !=null && functionPlayer.checkAndPrintPlayersInTeam(team)){
                        System.out.print("Enter index player for remove: ");
                        index = function.intEntry();
                        Player player = functionPlayer.findPlayerByIndex(team,index);
                        if (player!=null){
                            functionPlayer.removePlayerFromClub(club,team,player);
                        }
                    }
                }
            }
        }
    }

    private void removeFreeTrainer(){
        if (functionTrainer.checkAndPrintFreeTrainer()){
            System.out.print("Enter index of free Trainer you want to remove: ");
            int index = function.intEntry();
            Trainer freeTrainer = functionTrainer.findFreeTrainerByIndex(index);
            if (freeTrainer !=null){
                functionTrainer.removeFreeTrainer(freeTrainer);
            }
        }
    }

    private void removeTrainerFromClub(){
        if (functionClub.checkAndPrintClubs()){
            System.out.print("Enter index of Club where you want remove Trainer: ");
            int index = function.intEntry();
            Club club = functionClub.findClubByIndex(index);
            if (club!=null){
                if (functionTeam.checkAndPrintTeams(club)){
                    System.out.print("Enter index of Team: ");
                    index = function.intEntry();
                    Team team = functionTeam.findTeamByIndex(club,index);
                    if (team !=null && functionTrainer.checkAndPrintTrainersInTeam(team)){
                        System.out.print("Enter index trainer for remove: ");
                        index = function.intEntry();
                        Trainer trainer = functionTrainer.findTrainerByIndex(team,index);
                        if (trainer!=null){
                            functionTrainer.removeTrainerFromClub(club,team,trainer);
                        }
                    }
                }
            }
        }
    }

    private void showClubs(){
        functionClub.checkAndPrintClubs();
    }

    private void showTeams(){
        if (functionClub.checkAndPrintClubs()){
            System.out.print("Enter index of club where you want show Teams: ");
            int index = function.intEntry();
            Club club = functionClub.findClubByIndex(index);
            if (club!=null){
                functionTeam.checkAndPrintTeams(club);
            }
        }
    }

    private void showFreePlayers(){
        functionPlayer.checkAndPrintFreePlayer();
    }
    private void showFreeTrainers(){
        functionTrainer.checkAndPrintFreeTrainer();
    }
    private void showPlayersInClub(){
        int index;
        if (functionClub.checkAndPrintClubs()){
            System.out.print("Enter index of club you want show Players: ");
            index= function.intEntry();
            Club club = functionClub.findClubByIndex(index);
            if (club != null){
                if (functionTeam.checkAndPrintTeams(club)){
                    System.out.print("Enter index of Team you want to show Players: ");
                    index = function.intEntry();
                    Team team = functionTeam.findTeamByIndex(club,index);
                    if (team!=null && functionPlayer.checkAndPrintPlayersInTeam(team)){
                        System.out.println();
                    }
                }
            }
        }
    }

    private void showTrainersInClub(){
        int index;
        if (functionClub.checkAndPrintClubs()){
            System.out.print("Enter index of club you want show Trainer: ");
            index= function.intEntry();
            Club club = functionClub.findClubByIndex(index);
            if (club != null){
                if (functionTeam.checkAndPrintTeams(club)){
                    System.out.print("Enter index of Team you want to show Players: ");
                    index = function.intEntry();
                    Team team = functionTeam.findTeamByIndex(club,index);
                    if (team!=null && functionTrainer.checkAndPrintTrainersInTeam(team)){
                        System.out.println();
                    }
                }
            }
        }
    }

    private void assignPlayerToTeam(){
        int index;
        if (functionPlayer.checkAndPrintFreePlayer()){
            System.out.print("Enter index of free player to assign in Club: ");
            index = function.intEntry();
            Player player = functionPlayer.findFreePlayerByIndex(index);
            if (player !=null && functionClub.checkAndPrintClubs()){
                System.out.print("Enter index of club where you want assign player "+player.surnamePlayer()+": ");
                index = function.intEntry();
                Club club = functionClub.findClubByIndex(index);
                if (club !=null && functionTeam.checkAndPrintTeams(club)){
                    System.out.print("Enter index of team where you want assign player "+player.surnamePlayer()+": ");
                    index = function.intEntry();
                    Team team = functionTeam.findTeamByIndex(club,index);
                    if (team!=null){
                        functionPlayer.assignPlayerToTeam(club,team,player);
                    }
                }
            }
        }
    }
    private void assignTrainerToTeam(){
        int index;
        if (functionTrainer.checkAndPrintFreeTrainer()){
            System.out.print("Enter index of free trainer to assign in Club: ");
            index = function.intEntry();
            Trainer freeTrainer = functionTrainer.findFreeTrainerByIndex(index);
            if (freeTrainer !=null && functionClub.checkAndPrintClubs()){
                System.out.print("Enter index of club where you want assign trainer "+freeTrainer.surnameTrainer()+": ");
                index = function.intEntry();
                Club club = functionClub.findClubByIndex(index);
                if (club !=null && functionTeam.checkAndPrintTeams(club)){
                    System.out.print("Enter index of team where you want assign trainer "+freeTrainer.surnameTrainer()+": ");
                    index = function.intEntry();
                    Team team = functionTeam.findTeamByIndex(club,index);
                    if (team!=null){
                        functionTrainer.assignTrainerToTeam(club,team,freeTrainer);
                    }
                }
            }
        }
    }

    private void transferPlayer(){
        int index;
        if (functionClub.checkAndPrintClubs()){
            System.out.print("Enter index of club from where you want transfer player: ");
            index = function.intEntry();
            Club oldClub = functionClub.findClubByIndex(index);
            if (oldClub!=null && functionTeam.checkAndPrintTeams(oldClub)){
                System.out.print("Enter index of team from where you want transfer player: ");
                index= function.intEntry();
                Team oldTeam = functionTeam.findTeamByIndex(oldClub,index);
                if (oldTeam!=null && functionPlayer.checkAndPrintPlayersInTeam(oldTeam)){
                    System.out.print("Enter index of player for transfer: ");
                    index = function.intEntry();
                    Player player = functionPlayer.findPlayerByIndex(oldTeam, index);
                    if (player!=null){
                        System.out.print("Do you want transfer player: "+player.surnamePlayer()+" "+player.namePlayer()+" to free players ?: ");
                        String answer = function.getAnswer();
                        if (answer.equals("yes")){
                            functionPlayer.transferPlayer(oldClub,null,oldTeam, null,player,"toFreePlayers");
                        } else if (answer.equals("no") && functionClub.checkAndPrintClubs()) {
                            System.out.print("Enter index of club where you want transfer "+player.namePlayer()+" "+player.surnamePlayer()+": ");
                            index = function.intEntry();
                            Club newClub = functionClub.findClubByIndex(index);
                            if (newClub!=null && functionTeam.checkAndPrintTeams(newClub)){
                                System.out.print("Enter index of team where you want transfer "+player.namePlayer()+" "+player.surnamePlayer()+": ");
                                index = function.intEntry();
                                Team newTeam = functionTeam.findTeamByIndex(newClub,index);
                                if (newTeam!=null){
                                    functionPlayer.transferPlayer(oldClub,newClub,oldTeam,newTeam,player,"toAnotherClub");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void transferTrainer(){
        int index;
        if (functionClub.checkAndPrintClubs()){
            System.out.print("Enter index of club from where you want transfer trainer: ");
            index = function.intEntry();
            Club oldClub = functionClub.findClubByIndex(index);
            if (oldClub!=null && functionTeam.checkAndPrintTeams(oldClub)){
                System.out.print("Enter index of team from where you want transfer trainer: ");
                index= function.intEntry();
                Team oldTeam = functionTeam.findTeamByIndex(oldClub,index);
                if (oldTeam!=null && functionTrainer.checkAndPrintTrainersInTeam(oldTeam)){
                    System.out.print("Enter index of trainer for transfer: ");
                    index = function.intEntry();
                    Trainer trainer = functionTrainer.findTrainerByIndex(oldTeam, index);
                    if (trainer!=null){
                        System.out.print("Do you want transfer trainer: "+ trainer.surnameTrainer()+" "+ trainer.nameTrainer()+" to free trainers ?: ");
                        String answer = function.getAnswer();
                        if (answer.equals("yes")){
                            functionTrainer.transferTrainer(oldClub,null,oldTeam, null,trainer,"toFreeTrainers");
                        } else if (answer.equals("no") && functionClub.checkAndPrintClubs()) {
                            System.out.print("Enter index of club where you want transfer to "+ trainer.nameTrainer()+" "+ trainer.surnameTrainer()+": ");
                            index = function.intEntry();
                            Club newClub = functionClub.findClubByIndex(index);
                            if (newClub!=null && functionTeam.checkAndPrintTeams(newClub)){
                                System.out.print("Enter index of team where you want transfer "+ trainer.nameTrainer()+" "+ trainer.surnameTrainer()+": ");
                                index = function.intEntry();
                                Team newTeam = functionTeam.findTeamByIndex(newClub,index);
                                if (newTeam!=null){
                                    functionTrainer.transferTrainer(oldClub,newClub,oldTeam,newTeam,trainer,"toAnotherClub");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void editClub(){
        int index, yearOfEstablishment;
        String nameClub;
        if (functionClub.checkAndPrintClubs()){
            System.out.print("Enter index of club you want edit: ");
            index= function.intEntry();
            Club club = functionClub.findClubByIndex(index);
            if (club != null){
                System.out.print("Enter new name of Club: ");
                nameClub = function.readLine().toUpperCase();
                System.out.print("Enter new year of establishment: ");
                yearOfEstablishment = function.intEntry();
                functionClub.editClubInSystem(club,nameClub,yearOfEstablishment);
            }
        }
    }

    private void editTeam(){
        int index;
        String nameTeam;
        if (functionClub.checkAndPrintClubs()){
            System.out.print("Enter index of club where you want edit team: ");
            index= function.intEntry();
            Club club = functionClub.findClubByIndex(index);
            if (club!=null && functionTeam.checkAndPrintTeams(club)){
                System.out.print("Enter index of team which you want edit: ");
                index= function.intEntry();
                Team team = functionTeam.findTeamByIndex(club,index);
                System.out.print("Enter new name of Club: ");
                nameTeam = function.readLine().toUpperCase();
                functionTeam.editTeamInSystem(club,team,nameTeam);
            }
        }
    }

    private void editPlayerInClub(){
        int index;
        String namePlayer, surnamePlayer, position;
        if (functionClub.checkAndPrintClubs()){
            System.out.print("Enter index of club where you want edit player: ");
            index= function.intEntry();
            Club club = functionClub.findClubByIndex(index);
            if (club!=null && functionTeam.checkAndPrintTeams(club)){
                System.out.print("Enter index of team which you want edit player: ");
                index= function.intEntry();
                Team team = functionTeam.findTeamByIndex(club,index);
                if (team!=null && functionPlayer.checkAndPrintPlayersInTeam(team)){
                    System.out.print("Enter index of player which you want edit: ");
                    index = function.intEntry();
                    Player player = functionPlayer.findPlayerByIndex(team,index);
                    if (player!=null){
                        System.out.print("\nEnter new player name: ");
                        namePlayer= function.readLine();
                        System.out.print("Enter new player surname: ");
                        surnamePlayer = function.readLine();
                        System.out.print("Enter new player position: ");
                        position = function.readLine();
                        functionPlayer.editPlayerInSystem(club,team,player,namePlayer,surnamePlayer,position);
                    }
                }
            }
        }
    }

    private void editFreePlayer(){
        int index;
        String namePlayer, surnamePlayer, position;
        if (functionPlayer.checkAndPrintFreePlayer()){
            System.out.print("Enter index of player which you want edit: ");
            index= function.intEntry();
            Player freePlayer = functionPlayer.findFreePlayerByIndex(index);
            if (freePlayer!=null){
                System.out.print("\nEnter new player name: ");
                namePlayer= function.readLine();
                System.out.print("Enter new player surname: ");
                surnamePlayer = function.readLine();
                System.out.print("Enter new player position: ");
                position = function.readLine();
                functionPlayer.editFreePlayerInSystem(freePlayer,namePlayer,surnamePlayer, position);
            }
        }
    }

    private void editTrainerInClub(){
        int index;
        String nameTrainer, surnameTrainer;
        if (functionClub.checkAndPrintClubs()){
            System.out.print("Enter index of club where you want edit trainer: ");
            index= function.intEntry();
            Club club = functionClub.findClubByIndex(index);
            if (club!=null && functionTeam.checkAndPrintTeams(club)){
                System.out.print("Enter index of team which you want edit trainer: ");
                index= function.intEntry();
                Team team = functionTeam.findTeamByIndex(club,index);
                if (team!=null && functionPlayer.checkAndPrintPlayersInTeam(team)){
                    System.out.print("Enter index of trainer which you want edit: ");
                    index = function.intEntry();
                    Trainer trainer = functionTrainer.findTrainerByIndex(team,index);
                    if (trainer!=null){
                        System.out.print("\nEnter new trainer name: ");
                        nameTrainer = function.readLine();
                        System.out.print("Enter new trainer surname: ");
                        surnameTrainer = function.readLine();
                        functionTrainer.editTrainerInSystem(club,team,trainer, nameTrainer, surnameTrainer);
                    }
                }
            }
        }
    }

    private void editFreeTrainer(){
        int index;
        String nameTrainer, surnameTrainer;
        if (functionTrainer.checkAndPrintFreeTrainer()){
            System.out.print("Enter index of trainer which you want edit: ");
            index= function.intEntry();
            Trainer freeTrainer = functionTrainer.findFreeTrainerByIndex(index);
            if (freeTrainer !=null){
                System.out.print("\nEnter new player name: ");
                System.out.print("\nEnter new trainer name: ");
                nameTrainer = function.readLine();
                System.out.print("Enter new trainer surname: ");
                surnameTrainer = function.readLine();
                functionTrainer.editFreeTrainerInSystem(freeTrainer,nameTrainer,surnameTrainer);
            }
        }
    }
}
