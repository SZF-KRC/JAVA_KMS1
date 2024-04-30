import java.util.ArrayList;
import java.util.List;

public record Client(String name, String surname, String clientId, List<Account> accounts) {
    public Client(String name, String surname, String clientId){
        this(name,surname,clientId, new ArrayList<>());
    }
}
