package refactor.ch01.before;

import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StatementData {

    private Invoice invoice;

    public String getCustomer() {
        return invoice.getCustomer();
    }

    public List<Performance> getPerformances() {
        return invoice.getPerformances();
    }
}
