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

        // call the function
        Function function = new Function();
        function.addFreePlayer(player1); // add Free player
        function.addFreePlayer(player2);
        function.addFreePlayer(player3);
        function.addFreePlayer(player4);
        function.addFreePlayer(player5);
        function.addFreePlayer(player6);
        function.addFreeTrainer(trainer1);
        function.addFreeTrainer(trainer2);
        function.addClub(club); // add club
        club.addTeam(teamA); // add team to club
        club.addTeam(teamB);

        // call main menu
        function.mainMenu();
    }
}
