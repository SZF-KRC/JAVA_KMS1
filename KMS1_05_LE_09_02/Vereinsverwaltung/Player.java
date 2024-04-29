public class Player {
    private String namePlayer;
    private String position;

    public Player(String namePlayer, String position) {
        this.namePlayer = namePlayer;
        this.position = position;
    }
    @Override
    public String toString() {
        return  namePlayer +", position: "+ position;
    }
}
