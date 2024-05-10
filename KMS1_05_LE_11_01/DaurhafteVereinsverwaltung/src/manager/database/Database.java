package manager.database;

import manager.club.Club;
import manager.player.Player;
import manager.team.Team;
import manager.trainer.Trainer;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/club_manager"; // URL of your database
        String user = "root"; // database username
        String password = "Krc6130"; // database password
        return DriverManager.getConnection(url, user, password);
    }
    public void addClubToDatabase(Club club){
        String query = "INSERT INTO clubs (uniqueID, nameClub, yearOfEstablishment) VALUES (?,?,?)";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, club.uniqueID());
            preparedStatement.setString(2, club.nameClub());
            preparedStatement.setInt(3, club.yearOfEstablishment());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0 ){
                System.out.println("\n° Club was successfully added. °\n");
            }else {
                System.out.println("*** Error,club was not added !!! ***");
            }
        } catch (SQLException e) {
            System.out.println("*** Error, problem with database !!! ***");
        }
    }

    public void addTeamToDatabase(Team team, String clubID) {
        String query = "INSERT INTO teams (uniqueID, nameTeam, clubID) VALUES (?, ?, ?)";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, team.uniqueID());
            preparedStatement.setString(2, team.nameTeam());
            preparedStatement.setString(3, clubID);  // Foreign key, ktorý odkazuje na Club
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("\n° Team was successfully added to Club. °\n");
            } else {
                System.out.println("*** Error, team was not added !!! ***");
            }
        } catch (SQLException e) {
            System.out.println("*** Error, problem with database !!! ***: " + e.getMessage());
        }
    }

    public void addFreePlayerToDatabase(Player player){
        String query = "INSERT INTO players (uniqueID, namePlayer, surnamePlayer, position, teamID) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,player.uniqueID());
            preparedStatement.setString(2,player.namePlayer());
            preparedStatement.setString(3,player.surnamePlayer());
            preparedStatement.setString(4,player.position());
            preparedStatement.setString(5,null);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("\n° Free player was successfully added to Club. °\n");
            } else {
                System.out.println("*** Error, free player was not added !!! ***");
            }
        } catch (SQLException e) {
            System.out.println("*** Error, problem with database !!! ***: " + e.getMessage());
        }
    }

    public void addFreeTrainerToDatabase(Trainer trainer){
        String query = "INSERT INTO trainers (uniqueID, nameTrainer, surnameTrainer, teamID) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,trainer.uniqueID());
            preparedStatement.setString(2,trainer.nameTrainer());
            preparedStatement.setString(3,trainer.surnameTrainer());
            preparedStatement.setString(4,null);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("\n° Free trainer was successfully added to Club. °\n");
            } else {
                System.out.println("*** Error, free trainer was not added !!! ***");
            }
        } catch (SQLException e) {
            System.out.println("*** Error, problem with database !!! ***: " + e.getMessage());
        }
    }

    public void unAssignPlayerFromTeamToFreeInDatabase(String playerID){
        String query = "UPDATE players SET teamID = NULL WHERE uniqueID = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, playerID);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("\n° Player was successfully assigned to the free players. °\n");
            } else {
                System.out.println("*** Error, player was not unassigned  !!! ***");
            }
        } catch (SQLException e) {
            System.out.println("*** Error, problem with database !!! ***: " + e.getMessage());
        }
    }

    public void unAssignTrainerFromTeamToFreeInDatabase(String trainerID){
        String query = "UPDATE trainers SET teamID = NULL WHERE uniqueID = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, trainerID);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("\n° Trainer was successfully assigned to the free trainers. °\n");
            } else {
                System.out.println("*** Error, trainer was not unassigned  !!! ***");
            }
        } catch (SQLException e) {
            System.out.println("*** Error, problem with database !!! ***: " + e.getMessage());
        }
    }

    public void assignPlayerToTeamInDatabase(String playerID, String teamID) {
        String query = "UPDATE players SET teamID = ? WHERE uniqueID = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, teamID);
            preparedStatement.setString(2, playerID);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("\n° Player was successfully assigned to the team. °\n");
            } else {
                System.out.println("*** Error, player was not assigned to any team !!! ***");
            }
        } catch (SQLException e) {
            System.out.println("*** Error, problem with database !!! ***: " + e.getMessage());
        }
    }

    public void assignTrainerToTeamInDatabase(String trainerID, String teamID) {
        String query = "UPDATE trainers SET teamID = ? WHERE uniqueID = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, teamID);
            preparedStatement.setString(2, trainerID);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("\n° Trainer was successfully assigned to the team. °\n");
            } else {
                System.out.println("*** Error, trainer was not assigned to any team !!! ***");
            }
        } catch (SQLException e) {
            System.out.println("*** Error, problem with database !!! ***: " + e.getMessage());
        }
    }


    public void editClubInDatabase(Club club) {
        String query = "UPDATE clubs SET nameClub = ?, yearOfEstablishment = ?  WHERE uniqueID = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, club.nameClub());
            preparedStatement.setInt(2, club.yearOfEstablishment());
            preparedStatement.setString(3, club.uniqueID());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("\n° Club was successfully updated. °\n");
            } else {
                System.out.println("*** Error, club was not updated !!! ***");
            }
        } catch (SQLException e) {
            System.out.println("*** Error, problem with database !!! ***: " + e.getMessage());
        }
    }

    public void editTeamInDatabase(Team team) {
        String query = "UPDATE teams SET nameTeam = ?  WHERE uniqueID = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, team.nameTeam());
            preparedStatement.setString(2, team.uniqueID());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("\n° Team was successfully updated. °\n");
            } else {
                System.out.println("*** Error, team was not updated !!! ***");
            }
        } catch (SQLException e) {
            System.out.println("*** Error, problem with database !!! ***: " + e.getMessage());
        }
    }

    public void editPlayerInDatabase(Player player) {
        String query = "UPDATE players SET namePlayer = ?, surnamePlayer = ?, position = ?  WHERE uniqueID = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, player.namePlayer());
            preparedStatement.setString(2, player.surnamePlayer());
            preparedStatement.setString(3, player.position());
            preparedStatement.setString(4, player.uniqueID());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("\n° Player was successfully updated. °\n");
            } else {
                System.out.println("*** Error, player was not updated !!! ***");
            }
        } catch (SQLException e) {
            System.out.println("*** Error, problem with database !!! ***: " + e.getMessage());
        }
    }

    public void editTrainerInDatabase(Trainer trainer) {
        String query = "UPDATE trainers SET nameTrainer = ?, surnameTrainer = ? WHERE uniqueID = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, trainer.nameTrainer());
            preparedStatement.setString(2, trainer.surnameTrainer());
            preparedStatement.setString(3, trainer.uniqueID());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("\n° Trainer was successfully updated. °\n");
            } else {
                System.out.println("*** Error, trainer was not updated !!! ***");
            }
        } catch (SQLException e) {
            System.out.println("*** Error, problem with database !!! ***: " + e.getMessage());
        }
    }

    public List<Club> loadClubsAndTeamsFromDatabase() {
        // Initialize maps to store clubs and their corresponding teams.
        Map<String, Club> clubs = new HashMap<>();
        Map<String, List<Team>> teamsMap = new HashMap<>();

        // Attempt to load all clubs from the database.
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM clubs");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Process each club record fetched from the database.
            while (resultSet.next()) {
                String clubID = resultSet.getString("uniqueID");
                // Create a new Club object with data from the current row in the result set.
                Club club = new Club(clubID, resultSet.getString("nameClub"), resultSet.getInt("yearOfEstablishment"), new ArrayList<>());
                // Store the club in the map using its ID as the key.
                clubs.put(clubID, club);
                // Initialize the place in the team map for storing teams of this club.
                teamsMap.put(clubID, new ArrayList<>());
            }
        } catch (SQLException e) {
            // Handle potential SQL exceptions during club loading.
            System.out.println("Error loading clubs from database: " + e.getMessage());
        }

        // Load teams associated with each club.
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM teams");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Process each team record in the result set.
            while (resultSet.next()) {
                String teamID = resultSet.getString("uniqueID");
                String clubID = resultSet.getString("clubID");
                // Fetch the list of players and trainers for the team.
                List<Player> players = loadPlayersForTeam(teamID, connection);
                List<Trainer> trainers = loadTrainersForTeam(teamID, connection);
                // Create a new Team object with fetched data.
                Team team = new Team(teamID, resultSet.getString("nameTeam"), players, trainers);
                // Add the team to the list of teams for the corresponding club in the map.
                teamsMap.get(clubID).add(team);
            }
        } catch (SQLException e) {
            // Handle potential SQL exceptions during team loading.
            System.out.println("Error loading teams from database: " + e.getMessage());
        }

        // Combine clubs and their teams into final list of Club objects.
        List<Club> finalClubs = new ArrayList<>();
        for (Map.Entry<String, Club> entry : clubs.entrySet()) {
            String clubID = entry.getKey();
            List<Team> teams = teamsMap.get(clubID);
            // Add the teams to the club object.
            Club club = new Club(entry.getValue().uniqueID(), entry.getValue().nameClub(), entry.getValue().yearOfEstablishment(), teams);
            // Add the fully constructed club to the final list.
            finalClubs.add(club);
        }

        return finalClubs;
    }

    private List<Player> loadPlayersForTeam(String teamID, Connection connection) {
        List<Player> players = new ArrayList<>();
        String query = "SELECT * FROM players WHERE teamID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, teamID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                players.add(new Player(rs.getString("uniqueID"), rs.getString("namePlayer"), rs.getString("surnamePlayer"), rs.getString("position")));
            }
        }catch (SQLException e){
            System.out.println("Error loading free players from database: "+ e.getMessage());
        }
        return players;
    }

    private List<Trainer> loadTrainersForTeam(String teamID, Connection connection) {
        List<Trainer> trainers = new ArrayList<>();
        String query = "SELECT * FROM trainers WHERE teamID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, teamID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                trainers.add(new Trainer(rs.getString("uniqueID"), rs.getString("nameTrainer"), rs.getString("surnameTrainer")));
            }
        }catch (SQLException e){
            System.out.println("Error loading free trainers from database: "+ e.getMessage());
        }
        return trainers;
    }

    public List<Player> loadFreePlayers() {
        List<Player> freePlayers = new ArrayList<>();
        String query = "SELECT * FROM players WHERE teamID IS NULL";
        try (Connection connection = getConnection();PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                freePlayers.add(new Player(rs.getString("uniqueID"), rs.getString("namePlayer"), rs.getString("surnamePlayer"), rs.getString("position")));
            }
        }catch (SQLException e){
            System.out.println("volny hraci sa nenacitali");
        }
        return freePlayers;
    }

    public List<Trainer> loadFreeTrainers() {
        List<Trainer> freeTrainers = new ArrayList<>();
        String query = "SELECT * FROM trainers WHERE teamID IS NULL";
        try (Connection connection = getConnection();PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                freeTrainers.add(new Trainer(rs.getString("uniqueID"), rs.getString("nameTrainer"), rs.getString("surnameTrainer")));
            }
        }catch (SQLException e){
            System.out.println("volny treneri sa nenacitali");
        }
        return freeTrainers;
    }

    public void deleteClubAndRelatedEntities(String clubID) {
        String deletePlayers = "DELETE FROM players WHERE teamID IN (SELECT uniqueID FROM teams WHERE clubID = ?)";
        String deleteTrainers = "DELETE FROM trainers WHERE teamID IN (SELECT uniqueID FROM teams WHERE clubID = ?)";
        String deleteTeams = "DELETE FROM teams WHERE clubID = ?";
        String deleteClub = "DELETE FROM clubs WHERE uniqueID = ?";

        try (Connection connection = getConnection()) {
            // Disable auto-commit for transactional integrity
            connection.setAutoCommit(false);

            // Delete players in teams of the club
            try (PreparedStatement ps = connection.prepareStatement(deletePlayers)) {
                ps.setString(1, clubID);
                ps.executeUpdate();
            }

            // Delete trainers in teams of the club
            try (PreparedStatement ps = connection.prepareStatement(deleteTrainers)) {
                ps.setString(1, clubID);
                ps.executeUpdate();
            }

            // Delete teams in the club
            try (PreparedStatement ps = connection.prepareStatement(deleteTeams)) {
                ps.setString(1, clubID);
                ps.executeUpdate();
            }

            // Finally, delete the club
            try (PreparedStatement ps = connection.prepareStatement(deleteClub)) {
                ps.setString(1, clubID);
                ps.executeUpdate();
            }

            // Commit all deletions
            connection.commit();
            System.out.println("\n° Club and all related entities successfully deleted °\n");

        } catch (SQLException e) {
            System.out.println("\n--- Error during deletion: " + e.getMessage()+" ---\n");
        }
    }

    public void deleteTeamAndMembers(String teamID) {
        // Definition of SQL commands to delete a team and its members
        String deletePlayers = "DELETE FROM players WHERE teamID = ?";
        String deleteTrainers = "DELETE FROM trainers WHERE teamID = ?";
        String deleteTeam = "DELETE FROM teams WHERE uniqueID = ?";

        try (Connection connection = getConnection()) {
            // Disable auto-commit for transactional integrity
            connection.setAutoCommit(false);

            // Delete players from team
            try (PreparedStatement ps = connection.prepareStatement(deletePlayers)) {
                ps.setString(1, teamID);
                ps.executeUpdate();
            }

            // Delete trainers from team
            try (PreparedStatement ps = connection.prepareStatement(deleteTrainers)) {
                ps.setString(1, teamID);
                ps.executeUpdate();
            }

            // Delete the team
            try (PreparedStatement ps = connection.prepareStatement(deleteTeam)) {
                ps.setString(1, teamID);
                int affectedRows = ps.executeUpdate();
                if (affectedRows == 0) {
                    System.out.println("--- Error, no team found with the specified ID ---");
                } else {
                    System.out.println("\n° Team and all its members were successfully deleted. °\n");
                }
            }

            // Commit all changes
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Error during deletion: " + e.getMessage());
        }
    }

    public void deletePlayer(String playerID) {
        String query = "DELETE FROM players WHERE uniqueID = ?";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, playerID);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("\n° Player was successfully deleted from the database. °\n");
            } else {
                System.out.println("\n--- Error, no player found with the specified ID or player has already been deleted ---\n");
            }
        } catch (SQLException e) {
            System.out.println("\n--- Error deleting player from database: " + e.getMessage() + " ---\n");
        }
    }

    public void deleteTrainer(String trainerID) {
        String query = "DELETE FROM trainers WHERE uniqueID = ?";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, trainerID);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("\n° Trainer was successfully deleted from the database. °\n");
            } else {
                System.out.println("\n--- Error, no trainer found with the specified ID or trainer has already been deleted ---\n");
            }
        } catch (SQLException e) {
            System.out.println("\n--- Error deleting trainer from database: " + e.getMessage() + " ---\n");
        }
    }
}
