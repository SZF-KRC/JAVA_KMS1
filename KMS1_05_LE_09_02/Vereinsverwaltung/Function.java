import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Function {
    private List<Club>clubs;
    private List<Player> freePlayers;
    private List<Trainer> freeTrainers;


    public Function() {
        this.clubs = new ArrayList<>();
        this.freePlayers = new ArrayList<>();
        this.freeTrainers = new ArrayList<>();

    }

    public void mainMenu(){
        boolean exit = false;
        Scanner entry = new Scanner(System.in);

        while (!exit){
            System.out.print("\nWelcome in Football manager\n[1] Club\n[2] Team\n[3] Player\n[4] Trainer\n[5] Manual\n[0] exit program\nEnter index of your choice: ");
            try {
                switch (entry.nextInt()){
                    case 1: universalMenu("Club");break;
                    case 2: universalMenu("Team");break;
                    case 3: universalMenu("Player");break;
                    case 4: universalMenu("Trainer");break;
                    case 5:
                        System.out.print("\n[1] english manual\n[2] deutsch manual\n[0] back to main menu\nEnter index of your choice: ");
                        int index = intEntry();
                        switch (index){
                            case 1: readFile("manual_english");break;
                            case 2: readFile("manual_deutsch");break;
                            case 0:break;
                            default: System.out.println("Enter only index from 0 - 2 !");break;
                        }
                        break;
                    case 0: System.out.println("Exit program");exit = true;break;
                    default: System.out.println("Enter only index from 0 - 4 !");break;
                }
            }catch (InputMismatchException e){
                System.out.println("*** Enter only integer number for index ! ***");
                entry.nextLine();
            }
        }
    }
    public void universalMenu(String option){
        boolean exit = false;
        Scanner entry = new Scanner(System.in);
        while (!exit){
            System.out.print("\n* "+option+" manager *\n[1] add "+option+"\n[2] delete "+option+"\n[3] transfer "+option+"\n[4] show "+option+"s\n[0] back to main menu\nEnter index of your choice: ");
            switch (entry.nextInt()){
                case 1: addMenu(option);break;
                case 2: deleteMenu(option);break;
                case 3: transferMenu(option);break;
                case 4: showMenu(option);break;
                case 0: System.out.println("back to main menu");exit = true;break;
                default: System.out.println("Enter only index from 0 - 4 !");break;
            }
        }
    }
    public void addMenu(String option){
        Scanner entry = new Scanner(System.in);
        switch (option){
            case "Club":
                System.out.print("add " +option+"\nEnter name of Club: ");
                String nameClub = entry.nextLine();
                System.out.print("Enter year of establishment: ");
                int yearOfEstablishment = intEntry();
                Club club = new Club(nameClub, yearOfEstablishment);
                addClub(club);
                break;
            case "Team":
                printClubWithIndex();
                System.out.print("Enter index of Club to add team to: ");
                int index = intEntry();
                Club club1 = getClubByIndex(index);
                if (club1 !=null){
                    System.out.print("Enter name of Team: ");
                    String nameTeam = entry.nextLine();
                    Team team = new Team(nameTeam);
                    club1.addTeam(team);
                    System.out.println("Team added successfully to " + club1);
                }else {
                    System.out.println("*** Club not found ***");
                }
                break;
            case "Player":
                System.out.println("\n[1] add free player\n[2] add free player to Club\n[0] back to menu Player manager\nEnter index of your choice:  ");
                index = intEntry();
                switch (index){
                    case 1:
                        System.out.print("add "+option+"\nEnter name of Player: ");
                        String namePlayer = entry.nextLine();
                        System.out.print("Enter position of " + namePlayer+": ");
                        String position = entry.nextLine();
                        Player player = new Player(namePlayer,position);
                        freePlayers.add(player);
                        break;
                    case 2:
                        printClubWithIndex();
                        System.out.print("Enter index of Club where you want to add Player: ");
                        int clubIndex = intEntry();
                        Club foundClub = getClubByIndex(clubIndex);
                        if (foundClub != null) {
                            if (!foundClub.getTeams().isEmpty()){
                                printTeamWithIndex(foundClub);
                                System.out.print("Enter index of Team where you want to add Player: ");
                                int teamIndex = intEntry();
                                Team team = getTeamByIndex(foundClub,teamIndex);
                                if (team != null) {
                                    if (!freePlayers.isEmpty()){
                                        printFreePlayerWithIndex();
                                        System.out.print("Enter index of free Player you want to add: ");
                                        int playerIndex = intEntry();
                                        Player freePlayer = getFreePlayerByIndex(playerIndex);
                                        assignPlayerToClub(freePlayer,foundClub,team);
                                    }else {
                                        System.out.println("\n*** Free players not found ***\n");
                                    }
                                } else {
                                    System.out.println("\n*** Team not found ***\n");
                                }
                            }else {
                                System.out.println("\n--- No Teams in Club ---\n");
                            }
                        } else {
                            System.out.println("\n*** Invalid club index. ***\n");
                        }
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Enter only index from 0 - 2 !");
                        break;
                }
                break;
            case "Trainer":
                System.out.println("\n[1] add free trainer\n[2] add trainer to Club\n[0] back to menu Trainer manager\nEnter index of your choice:  ");
                index = intEntry();
                switch (index){
                    case 1:
                        System.out.print("add "+option+"\nEnter name of Trainer: ");
                        String nameTrainer = entry.nextLine();
                        Trainer trainer = new Trainer(nameTrainer);
                        freeTrainers.add(trainer);
                        break;
                    case 2:
                        printClubWithIndex();
                        System.out.print("Enter index of Club where you want to add Trainer: ");
                        int clubIndex = intEntry();
                        Club foundClub = getClubByIndex(clubIndex);
                        if (foundClub != null) {
                            printTeamWithIndex(foundClub);
                            System.out.print("Enter index of Team where you want to add Trainer: ");
                            int teamIndex = intEntry();
                            Team team = getTeamByIndex(foundClub,teamIndex);
                            if (team != null) {
                                if (!freeTrainers.isEmpty()){
                                    printFreeTrainerWithIndex();
                                    System.out.print("Enter index of free Trainer you want to add: ");
                                    int trainerIndex = intEntry();
                                    Trainer trainer1 = getFreeTrainerByIndex(trainerIndex);
                                    assignTrainerToClub(trainer1,foundClub,team);
                                }else {
                                    System.out.println("\n*** Free trainers not found ***\n");
                                }
                            } else {
                                System.out.println("\n*** Team not found ***\n");
                            }
                        } else {
                            System.out.println("\n*** Invalid club index. ***\n");
                        }
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Enter only index from 0 - 2 !");
                        break;
                }
                break;
            default:
                System.out.println("Enter only index from 0 - 4 !");
                break;
        }
    }
    public void deleteMenu(String option){
        int index, index2;
        switch (option){
            case "Club":
                System.out.print("delete " +option+"\nEnter index of your choice: ");
                printClubWithIndex();
                index = intEntry();
                if (index >=0 && index < clubs.size()){
                    clubs.remove(index);
                    System.out.println("Club is deleted");
                }
                break;
            case "Team":
                printClubWithIndex();
                System.out.print("Enter index of Club where you want to delete Team: ");
                index =intEntry();
                Club foundClub = getClubByIndex(index);
                if (foundClub !=null){
                    printTeamWithIndex(getClubByIndex(index));
                    System.out.print("Enter index of Team for delete: ");
                    index2 = intEntry();
                    Team foundTeam = getTeamByIndex(foundClub,index2);
                    if (foundTeam!= null){
                        removeTeam(foundClub, foundTeam);
                        System.out.println("Team deleted successfully");
                    }else {
                        System.out.println("*** Team not found ***");
                    }
                }else {
                    System.out.println("*** Club not found ***");
                }

                break;
            case "Player":
                System.out.println("\n[1] delete free player\n[2] delete player in Club\n[0] back to menu Player manager\nEnter index of your choice:  ");
                index = intEntry();
                switch (index){
                    case 1:
                        printFreePlayerWithIndex();
                        System.out.print("Enter index of free Player for delete: ");
                        index = intEntry();
                        Player foundFreePlayer = getFreePlayerByIndex(index);
                        if (foundFreePlayer !=null){
                            deleteFreePlayer(foundFreePlayer);
                            System.out.println("Free Player deleted successfully");
                        }
                        break;
                    case 2:
                        printClubWithIndex();
                        System.out.print("Enter index of Club where you want to delete Player: ");
                        int clubIndex = intEntry();
                        Club club = getClubByIndex(clubIndex);

                        if (club != null) {
                            printTeamWithIndex(club);
                            System.out.print("Enter index of Team where you want to delete Player: ");
                            int teamIndex = intEntry();
                            Team team = getTeamByIndex(club,teamIndex);

                            if (team != null && !team.getPlayers().isEmpty()) {
                                printPlayerWithIndex(club, team);
                                System.out.print("Enter index of Player you want to delete: ");
                                int playerIndex = intEntry();
                                Player player = getPlayerByIndex(team,playerIndex);

                                if (player != null) {
                                    removePlayerFromClub(player, club, team);
                                    System.out.println("Player deleted successfully");
                                } else {
                                    System.out.println("\n*** Invalid player index. ***\n");
                                }
                            } else {
                                System.out.println("\n*** No players available to delete in the selected team. ***\n");
                            }
                        } else {
                            System.out.println("\n*** Invalid club index. ***\n");
                        }
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Enter only index from 0 - 2 !");
                        break;
                }
                break;
            case "Trainer":
                System.out.println("\n[1] delete free Trainer\n[2] delete Trainer in Club\n[0] back to menu Trainer manager\nEnter index of your choice:  ");
                index = intEntry();
                switch (index){
                    case 1:
                        printFreeTrainerWithIndex();
                        System.out.print("Enter index of free Trainer for delete: ");
                        index = intEntry();
                        Trainer foundFreeTrainer = getFreeTrainerByIndex(index);
                        if (foundFreeTrainer !=null){
                            deleteFreeTrainer(foundFreeTrainer);
                            System.out.println("Free Trainer deleted successfully");
                        }
                        break;
                    case 2:
                        printClubWithIndex();
                        System.out.print("Enter index of Club where you want to delete Trainer: ");
                        int clubIndex = intEntry();
                        Club club = getClubByIndex(clubIndex);
                        if (club != null) {
                            printTeamWithIndex(club);
                            System.out.print("Enter index of Team where you want to delete Trainer: ");
                            int teamIndex = intEntry();
                            Team team = getTeamByIndex(club,teamIndex);
                            if (team != null && !team.getTrainers().isEmpty()) {
                                printTrainerWithIndex(club,team);
                                System.out.print("Enter index of Trainer you want to delete: ");
                                int trainerIndex = intEntry();
                                Trainer trainer = getTrainerByIndex(team,trainerIndex);
                                if (trainer != null) {
                                    removeTrainerFromClub(trainer, club, team);
                                    System.out.println("Trainer deleted successfully");
                                } else {
                                    System.out.println("\n*** Invalid Trainer index. ***\n");
                                }
                            } else {
                                System.out.println("\n*** No trainers available to delete in the selected team. ***\n");
                            }
                        } else {
                            System.out.println("\n*** Invalid club index. ***\n");
                        }
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Enter only index from 0 - 2 !");
                        break;
                }
                break;
            default:
                System.out.println("Enter only index from 0 - 4 !");
                break;
        }
    }

    public void transferMenu(String option){
        int index;
        switch (option){
            case "Club":
                System.out.print("Transfer is available only for Player and Trainer");
                break;
            case "Team":
                System.out.print("Transfer is available only for Player and Trainer");
                break;
            case "Player":
                System.out.println("\n[1] transfer player between Clubs \n[2] transfer free player to Club\n[0] back to menu Player manager\nEnter index of your choice:  ");
                index = intEntry();
                switch (index){
                    case 1:
                        printClubWithIndex();
                        System.out.print("Enter index of Club from where you want to transfer Player: ");
                        int oldClubIndex = intEntry();
                        Club oldClub = getClubByIndex(oldClubIndex);
                        if (oldClub != null) {
                            printTeamWithIndex(oldClub);
                            System.out.print("Enter index of Team from where you want to transfer Player: ");
                            int oldTeamIndex = intEntry();
                            Team oldTeam = getTeamByIndex(oldClub, oldTeamIndex);
                            if (oldTeam != null) {
                                if (!oldTeam.getPlayers().isEmpty()){
                                    printPlayerWithIndex(oldClub,oldTeam);
                                    System.out.print("Enter index of Player you want to transfer: ");
                                    int playerIndex = intEntry();
                                    Player player = getPlayerByIndex(oldTeam,playerIndex);
                                    printClubWithIndex();
                                    System.out.print("Enter index of new Club where you want to transfer Player: ");
                                    int newClubIndex = intEntry();
                                    Club newClub = getClubByIndex(newClubIndex);
                                    if (newClub != null){
                                        printTeamWithIndex(newClub);
                                        System.out.print("Enter index of new Team where you want to transfer Player: ");
                                        int newTeamIndex= intEntry();
                                        Team newTeam= getTeamByIndex(newClub,newTeamIndex);
                                        if (newTeam !=null){
                                            transferPlayerBetweenClubs(player,oldClub,oldTeam,newClub,newTeam);
                                            System.out.println("Transfer was successfully");
                                        }
                                    }
                                }else {
                                    System.out.println("\n*** Free players not found ***\n");
                                }
                            } else {
                                System.out.println("\n*** Team not found ***\n");
                            }
                        } else {
                            System.out.println("\n*** Invalid club index. ***\n");
                        }
                        break;
                    case 2:
                        printClubWithIndex();
                        System.out.print("Enter index of Club where you want to add Player: ");
                        int clubIndex = intEntry();
                        Club foundClub = getClubByIndex(clubIndex);
                        if (foundClub != null) {
                            printTeamWithIndex(foundClub);
                            System.out.print("Enter index of Team where you want to add Player: ");
                            int teamIndex = intEntry();
                            Team team = getTeamByIndex(foundClub,teamIndex);
                            if (team != null) {
                                if (!freePlayers.isEmpty()){
                                    printFreePlayerWithIndex();
                                    System.out.print("Enter index of free Player you want to add: ");
                                    int playerIndex = intEntry();
                                    Player freePlayer = getFreePlayerByIndex(playerIndex);
                                    assignPlayerToClub(freePlayer,foundClub,team);
                                    System.out.println("Transfer was successfully");
                                }else {
                                    System.out.println("\n*** Free players not found ***\n");
                                }
                            } else {
                                System.out.println("\n*** Team not found ***\n");
                            }
                        } else {
                            System.out.println("\n*** Invalid club index. ***\n");
                        }
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Enter only index from 0 - 2 !");
                        break;
                }
                break;
            case "Trainer":
                System.out.println("\n[1] transfer trainer between Clubs \n[2] transfer free trainer to Club\n[0] back to menu Trainer manager\nEnter index of your choice:  ");
                index = intEntry();
                switch (index){
                    case 1:
                        printClubWithIndex();
                        System.out.print("Enter index of Club from where you want to transfer Trainer: ");
                        int oldClubIndex = intEntry();
                        Club oldClub = getClubByIndex(oldClubIndex);
                        if (oldClub != null) {
                            printTeamWithIndex(oldClub);
                            System.out.print("Enter index of Team from where you want to transfer Trainer: ");
                            int oldTeamIndex = intEntry();
                            Team oldTeam = getTeamByIndex(oldClub, oldTeamIndex);
                            if (oldTeam != null) {
                                if (!oldTeam.getTrainers().isEmpty()){
                                    printTrainerWithIndex(oldClub,oldTeam);
                                    System.out.print("Enter index of Trainer you want to transfer: ");
                                    int trainerIndex = intEntry();
                                    Trainer trainer = getTrainerByIndex(oldTeam,trainerIndex);
                                    printClubWithIndex();
                                    System.out.print("Enter index of new Club where you want to transfer Trainer: ");
                                    int newClubIndex = intEntry();
                                    Club newClub = getClubByIndex(newClubIndex);
                                    if (newClub != null){
                                        printTeamWithIndex(newClub);
                                        System.out.print("Enter index of new Team where you want to transfer Trainer: ");
                                        int newTeamIndex= intEntry();
                                        Team newTeam= getTeamByIndex(newClub,newTeamIndex);
                                        if (newTeam !=null){
                                            transferTrainerBetweenClubs(trainer,oldClub,oldTeam,newClub,newTeam);
                                            System.out.println("Transfer was successfully");
                                        }
                                    }
                                }else {
                                    System.out.println("\n*** Trainers not found ***\n");
                                }
                            } else {
                                System.out.println("\n*** Team not found ***\n");
                            }
                        } else {
                            System.out.println("\n*** Invalid club index. ***\n");
                        }
                        break;
                    case 2:
                        printClubWithIndex();
                        System.out.print("Enter index of Club where you want to add free Trainer: ");
                        int clubIndex = intEntry();
                        Club foundClub = getClubByIndex(clubIndex);
                        if (foundClub != null) {
                            printTeamWithIndex(foundClub);
                            System.out.print("Enter index of Team where you want to add free Trainer: ");
                            int teamIndex = intEntry();
                            Team team = getTeamByIndex(foundClub,teamIndex);
                            if (team != null) {
                                if (!freeTrainers.isEmpty()){
                                    printFreeTrainerWithIndex();
                                    System.out.print("Enter index of free Trainer you want to add: ");
                                    int trainerIndex = intEntry();
                                    Trainer freeTrainer = getFreeTrainerByIndex(trainerIndex);
                                    assignTrainerToClub(freeTrainer,foundClub,team);
                                    System.out.println("Transfer was successfully");
                                }else {
                                    System.out.println("\n*** Free trainers not found ***\n");
                                }
                            } else {
                                System.out.println("\n*** Team not found ***\n");
                            }
                        } else {
                            System.out.println("\n*** Invalid club index. ***\n");
                        }
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Enter only index from 0 - 2 !");
                        break;
                }
                break;
            default:
                System.out.println("Enter only index from 0 - 4 !");
                break;
        }
    }

    public void showMenu(String option){
        int index;
        switch (option){
            case "Club":
                printClubWithIndex();
                break;
            case "Team":
                printClubWithIndex();
                System.out.print("Enter index of Club where you want to show Team: ");
                index =intEntry();
                Club foundClub = getClubByIndex(index);
                if (foundClub !=null){
                    printTeamWithIndex(getClubByIndex(index));
                }else {
                    System.out.println("*** Club not found ***");
                }
                break;
            case "Player":
                System.out.println("\n[1] show free player\n[2] show player in Club\n[0] back to menu Player manager\nEnter index of your choice:  ");
                index = intEntry();
                switch (index){
                    case 1:
                        printFreePlayerWithIndex();
                        break;
                    case 2:
                        printClubWithIndex();
                        System.out.print("Enter index of Club where you want to show Player: ");
                        int clubIndex = intEntry();
                        Club club = getClubByIndex(clubIndex);

                        if (club != null) {
                            printTeamWithIndex(club);
                            System.out.print("Enter index of Team where you want to show Player: ");
                            int teamIndex = intEntry();
                            Team team = getTeamByIndex(club,teamIndex);

                            if (team != null && !team.getPlayers().isEmpty()) {
                                printPlayerWithIndex(club, team);
                            } else {
                                System.out.println("\n*** No players available to show in the selected team. ***\n");
                            }
                        } else {
                            System.out.println("\n*** Invalid club index. ***\n");
                        }
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Enter only index from 0 - 2 !");
                        break;
                }
                break;
            case "Trainer":
                System.out.println("\n[1] show free Trainer\n[2] show Trainer in Club\n[0] back to menu Trainer manager\nEnter index of your choice:  ");
                index = intEntry();
                switch (index){
                    case 1:
                        printFreeTrainerWithIndex();
                        break;
                    case 2:
                        printClubWithIndex();
                        System.out.print("Enter index of Club where you want to show Trainer: ");
                        int clubIndex = intEntry();
                        Club club = getClubByIndex(clubIndex);
                        if (club != null) {
                            printTeamWithIndex(club);
                            System.out.print("Enter index of Team where you want to show Trainer: ");
                            int teamIndex = intEntry();
                            Team team = getTeamByIndex(club,teamIndex);
                            if (team != null && !team.getTrainers().isEmpty()) {
                                printTrainerWithIndex(club,team);
                            } else {
                                System.out.println("\n*** No trainers available to show in the selected team. ***\n");
                            }
                        } else {
                            System.out.println("\n*** Invalid club index. ***\n");
                        }
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Enter only index from 0 - 2 !");
                        break;
                }
                break;
            default:
                System.out.println("Enter only index from 0 - 4 !");
                break;
        }
    }

    private int intEntry(){
        Scanner entry = new Scanner(System.in);
        int number;
        while (true){
            try {
                number = entry.nextInt();
                break;
            }catch (InputMismatchException e){
                System.out.println("*** Enter only integer number ***");
                entry.nextLine();
            }
        }
        return number;
    }

    private Club getClubByIndex(int index){
        if (index >=0 && index < clubs.size()){
            return clubs.get(index);
        }
        return null;
    }

    private Player getFreePlayerByIndex(int index){
        if (index >=0 && index < freePlayers.size()){
            return freePlayers.get(index);
        }
        return null;
    }
    private Trainer getFreeTrainerByIndex(int index){
        if (index >=0 && index < freeTrainers.size()){
            return freeTrainers.get(index);
        }
        return null;
    }

    private Team getTeamByIndex(Club club, int index) {
        if (club != null && index >= 0 && index < club.getTeams().size()) {
            return club.getTeams().get(index);
        }
        return null;
    }

    private Player getPlayerByIndex(Team team, int index) {
        if (team != null && index >= 0 && index < team.getPlayers().size()) {
            return team.getPlayers().get(index);
        }
        return null;
    }

    private Trainer getTrainerByIndex(Team team, int index) {
        if (team != null && index >= 0 && index < team.getTrainers().size()) {
            return team.getTrainers().get(index);
        }
        return null;
    }

    private void printClubWithIndex(){
        if (clubs.isEmpty()){
            System.out.println("--- No data ---");
        }else {
            for (int i = 0; i < clubs.size();i++){
                System.out.println("Index: "+i+", Club: "+ clubs.get(i));
            }
        }
    }

    private void printTeamWithIndex(Club club){
        if (clubs.isEmpty()){
            System.out.println("--- No data ---");
        }
        List<Team> teams = club.getTeams();
        if (teams.isEmpty()){
            System.out.println("--- No teams in this club ---");
        } else {
            for (int i = 0; i < club.getTeams().size();i++){
                System.out.println("Index: " + i+ ", Team: " + club.getTeams().get(i) + teams.get(i).getPlayers());
            }
        }
    }

    private void printPlayerWithIndex(Club club, Team team){
        if (club == null || team == null) {
            System.out.println("--- No data or invalid club/team ---");
            return;
        }
        List<Player> players = team.getPlayers();
        if (players.isEmpty()) {
            System.out.println("--- No players in this team ---");
        } else {
            for (int i = 0; i < players.size(); i++){
                System.out.println("Index: " + i + ", Player: " + players.get(i) );
            }
        }
    }

    private void printTrainerWithIndex(Club club, Team team){
        if (club == null || team == null) {
            System.out.println("--- No data or invalid club/team ---");
            return;
        }
        List<Trainer> trainers = team.getTrainers();
        if (trainers.isEmpty()) {
            System.out.println("--- No Trainers in this team ---");
        } else {
            for (int i = 0; i < trainers.size(); i++){
                System.out.println("Index: " + i + ", Trainer: " + trainers.get(i));
            }
        }
    }


    private void printFreePlayerWithIndex(){
        if (freePlayers.isEmpty()){
            System.out.println("--- No data ---");
        }else {
            for (int i =0; i < freePlayers.size();i++){
                System.out.println("Index: " +i+", free Player: "+freePlayers.get(i));
            }
        }
    }

    private void printFreeTrainerWithIndex(){
        if (freeTrainers.isEmpty()){
            System.out.println("--- No data ---");
        }else {
            for (int i =0; i < freeTrainers.size();i++){
                System.out.println("Index: " +i+", free Player: "+freeTrainers.get(i));
            }
        }
    }



    private void removeTeam(Club club, Team team){
        club.removeTeam(team);
    }

    public void addClub(Club club){
        clubs.add(club);
    }
    public void addFreePlayer(Player player){
        freePlayers.add(player);
    }
    public void deleteFreePlayer(Player player){
        freePlayers.remove(player);
    }
    public void addFreeTrainer(Trainer trainer){
        freeTrainers.add(trainer);
    }
    public void deleteFreeTrainer(Trainer trainer){
        freeTrainers.remove(trainer);
    }

    public void transferPlayerBetweenClubs(Player player, Club oldClub, Team oldTeam, Club newClub, Team newTeam){
        oldClub.removePlayerFromTeam(player, oldTeam);
        newClub.addPlayerToTeam(player,newTeam);
    }
    public void transferTrainerBetweenClubs(Trainer trainer, Club oldClub, Team oldTeam, Club newClub, Team newTeam){
        oldClub.removeTrainerFromTeam(trainer,oldTeam);
        newClub.addTrainerToTeam(trainer,newTeam);
    }

    public void removePlayerFromClub(Player player, Club club, Team team){
        club.removePlayerFromTeam(player,team);
    }
    public void removeTrainerFromClub(Trainer trainer, Club club, Team team){
        club.removeTrainerFromTeam(trainer,team);
    }
    public void assignPlayerToClub(Player player, Club club, Team team){
        if (freePlayers.contains(player)){
            freePlayers.remove(player);
        }
        club.addPlayerToTeam(player,team);
    }
    public void assignTrainerToClub(Trainer trainer, Club club, Team team){
        if (freeTrainers.contains(trainer)){
            freeTrainers.remove(trainer);
        }
        club.addTrainerToTeam(trainer,team);
    }

    // read manual with different language mode
    public void readFile(String language){
        try {
            File manual = new File(language+".txt");
            Scanner myReader = new Scanner(manual);
            while (myReader.hasNextLine()){
                String data = myReader.nextLine();
                System.out.println(data);
            }
        }catch (FileNotFoundException e){
            System.out.println("Manual not exist !");
        }
    }
}
