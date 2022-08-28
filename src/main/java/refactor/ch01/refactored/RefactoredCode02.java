package refactor.ch01.refactored;

import java.util.HashMap;
import refactor.ch01.before.Invoice;
import refactor.ch01.before.Performance;
import refactor.ch01.before.Play;

public class RefactoredCode02 {

    public String statement(Invoice invoice, HashMap<String, Play> plays) throws Exception {
        int totalAmount = 0;
        int volumeCredits = 0;

        StringBuilder result =
            new StringBuilder(String.format("청구 내역 (고객명: %s)\n", invoice.getCustomer()));

        for (Performance performance : invoice.getPerformances()) {
            Play play = plays.get(performance.getPlayID());
            int thisAmount = amountFor(performance, play);

            volumeCredits += Math.max(performance.getAudience() - 30, 0);
            if ("comedy".equals(play.getType())) {
                volumeCredits += Math.floor(performance.getAudience() / 5);
            }

            result.append(String.format("%s: $%d %d석\n",
                play.getName(), thisAmount / 100, performance.getAudience()));
            totalAmount += thisAmount;
        }
        result.append(String.format("총액: $%d\n", totalAmount / 100));
        result.append(String.format("적립 포인트: %d점", volumeCredits));

        return result.toString();
    }

    private static int amountFor(Performance performance, Play play) throws Exception {
        int thisAmount;
        switch (play.getType()) {
            case "tragedy":
                thisAmount = 40000;
                if (performance.getAudience() > 30) {
                    thisAmount += 1000 * (performance.getAudience() - 30);
                }
                break;
            case "comedy":
                thisAmount = 30000;
                if (performance.getAudience() > 20) {
                    thisAmount += 10000 + 500 * (performance.getAudience() - 20);
                }
                thisAmount += 300 * performance.getAudience();
                break;
            default:
                throw new Exception("알 수 없는 장르" + play.getType());
        }
        return thisAmount;
    }
}
