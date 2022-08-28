package refactor.ch01.before;

import java.util.HashMap;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StatementData {

    private Invoice invoice;
    private HashMap<String, Play> plays;

    public String getCustomer() {
        return invoice.getCustomer();
    }

    public List<Performance> getPerformances() {
        return invoice.getPerformances();
    }
}
