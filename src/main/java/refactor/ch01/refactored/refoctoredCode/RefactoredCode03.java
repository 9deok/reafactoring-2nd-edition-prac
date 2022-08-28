package refactor.ch01.refactored.refoctoredCode;

import java.util.HashMap;
import refactor.ch01.before.Invoice;
import refactor.ch01.before.Performance;
import refactor.ch01.before.Play;

public class RefactoredCode03 {

    public String statement(Invoice invoice, HashMap<String, Play> plays) throws Exception {
        int totalAmount = 0;
        int volumeCredits = 0;

        StringBuilder result =
            new StringBuilder(String.format("청구 내역 (고객명: %s)\n", invoice.getCustomer()));

        for (Performance performance : invoice.getPerformances()) {
            volumeCredits += Math.max(performance.getAudience() - 30, 0);
            if ("comedy".equals(playFor(plays, performance).getType())) {
                volumeCredits += Math.floor(performance.getAudience() / 5);
            }

            result.append(String.format("%s: $%d %d석\n",
                playFor(plays, performance).getName(),
                amountFor(performance, playFor(plays, performance)) / 100,
                performance.getAudience())
            );
            totalAmount += amountFor(performance, playFor(plays, performance));
        }
        result.append(String.format("총액: $%d\n", totalAmount / 100));
        result.append(String.format("적립 포인트: %d점", volumeCredits));

        return result.toString();
    }

    private static int amountFor(Performance performance, Play play) throws Exception {
        int result;
        switch (play.getType()) {
            case "tragedy":
                result = 40000;
                if (performance.getAudience() > 30) {
                    result += 1000 * (performance.getAudience() - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (performance.getAudience() > 20) {
                    result += 10000 + 500 * (performance.getAudience() - 20);
                }
                result += 300 * performance.getAudience();
                break;
            default:
                throw new Exception("알 수 없는 장르" + play.getType());
        }
        return result;
    }

    private static Play playFor(HashMap<String, Play> plays, Performance performance) {
        return plays.get(performance.getPlayID());
    }
}
