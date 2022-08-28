package refactor.ch01.refactored;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import refactor.ch01.before.Invoice;
import refactor.ch01.before.Performance;
import refactor.ch01.before.Play;
import refactor.ch01.refactored.performaceCalculator.PerformanceCalculator;

@AllArgsConstructor
@Getter
public class StatementData {

    private Invoice invoice;
    private Plays plays;

    public String getCustomer() {
        return invoice.getCustomer();
    }

    public List<Performance> getPerformances() {
        return invoice.getPerformances();
    }

    public Play playFor(Performance performance) {
        return plays.get(performance);
    }

    public int amountFor(Performance performance, Play play) throws Exception {
        return new PerformanceCalculator(performance, play).amount();
    }

    public int totalAmount() throws Exception {
        int result = 0;
        for (Performance performance : invoice.getPerformances()) {
            result += amountFor(performance, playFor(performance));
        }
        return result;
    }

    public int volumeCreditsFor(Performance performance) {
        int result = 0;
        result += Math.max(performance.getAudience() - 30, 0);
        if ("comedy".equals(playFor(performance).getType())) {
            result += Math.floor(performance.getAudience() / 5);
        }
        return result;
    }

    public int totalVolumeCredits() {
        int result = 0;
        for (Performance performance : invoice.getPerformances()) {
            result += volumeCreditsFor(performance);
        }
        return result;
    }
}
