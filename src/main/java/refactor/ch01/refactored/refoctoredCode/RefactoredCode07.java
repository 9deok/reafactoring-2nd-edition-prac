package refactor.ch01.refactored.refoctoredCode;

import refactor.ch01.before.Invoice;
import refactor.ch01.before.Performance;
import refactor.ch01.before.Play;
import refactor.ch01.refactored.Plays;
import refactor.ch01.refactored.StatementData;

public class RefactoredCode07 {

    public String statement(Invoice invoice, Plays plays) throws Exception {
        StatementData statementData = new StatementData(invoice, plays);
        return renderPlainText(statementData);
    }

    private static String renderPlainText(StatementData statementData)
        throws Exception {
        StringBuilder result =
            new StringBuilder(String.format("청구 내역 (고객명: %s)\n", statementData.getCustomer()));

        for (Performance performance : statementData.getPerformances()) {
            result.append(String.format("%s: $%d %d석\n",
                playFor(statementData.getPlays(), performance).getName(),
                amountFor(performance, playFor(statementData.getPlays(), performance)) / 100,
                performance.getAudience())
            );
        }

        result.append(String.format("총액: $%d\n", totalAmount(statementData) / 100));
        result.append(String.format("적립 포인트: %d점", totalVolumeCredits(statementData)));

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

    private static Play playFor(Plays plays, Performance performance) {
        return plays.get(performance);
    }

    private static int totalAmount(StatementData statementData)
        throws Exception {
        int result = 0;
        for (Performance performance : statementData.getPerformances()) {
            result += amountFor(performance, playFor(statementData.getPlays(), performance));
        }
        return result;
    }

    private static int totalVolumeCredits(StatementData statementData) {
        int result = 0;
        for (Performance performance : statementData.getPerformances()) {
            result += volumeCreditsFor(statementData.getPlays(), performance);
        }
        return result;
    }

    private static int volumeCreditsFor(Plays plays, Performance performance) {
        int result = 0;
        result += Math.max(performance.getAudience() - 30, 0);
        if ("comedy".equals(playFor(plays, performance).getType())) {
            result += Math.floor(performance.getAudience() / 5);
        }
        return result;
    }
}
