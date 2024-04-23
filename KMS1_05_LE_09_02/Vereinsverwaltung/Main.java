public class Main {
    public static void main(String[] args) {
        // create club
        Club club = new Club("Manchester", 1969);

        // create teams
        Team teamA = new Team("Team A");
        Team teamB = new Team("Team B");

        // create players
        Player player1 = new Player("Ronaldo", "forward");
        Player player2 = new Player("Maradona", "forward");
        Player player3 = new Player("Messi", "forward");
        Player player4= new Player("Ramos", "defender");
        Player player5= new Player("Casillas", "goalkeeper");
        Player player6= new Player("Cech", "goalkeeper");

        // create trainers
        Trainer trainer1 = new Trainer("Alfredo Lorenzo");
        Trainer trainer2 = new Trainer("Gustavo Bolero");

        // add team to club
        club.addTeam(teamA);
        club.addTeam(teamB);

        // add Player to Team
        teamA.addPlayer(player1);
        teamA.addPlayer(player2);
        teamA.addPlayer(player5);

        teamB.addPlayer(player3);
        teamB.addPlayer(player4);
        teamB.addPlayer(player6);


        // add Player to Team
        teamA.addTrainer(trainer1);
        teamB.addTrainer(trainer2);

        // print
        printFullInfo(club);
        //delete one player
        teamB.removePlayers(player3);

        // print after delete
        printFullInfo(club);

    }
    static void printFullInfo(Club club){
        // print full info
        System.out.println("Club: " + club.getNameClub());
        for ( Team team : club.getTeams()){
            System.out.println("\n" + team.getNameTeam());
            for (Trainer trainer : team.getTrainers()){
                System.out.println("Trainer: " + trainer.getNameTrainer() + "\nPlayers--> ");
                for (Player player : team.getPlayers()){
                    System.out.print(player.getNamePlayer() + ":" + player.getPosition()+ " ,");
                }
                System.out.println();

            }

        }
    }
}
