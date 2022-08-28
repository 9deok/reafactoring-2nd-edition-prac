package refactor.ch01.refactored;

import java.util.HashMap;
import refactor.ch01.PerformanceCalculator;
import refactor.ch01.before.Invoice;
import refactor.ch01.before.Performance;
import refactor.ch01.before.Play;
import refactor.ch01.before.StatementData;

public class RefactoredCode08 {

    public String statement(Invoice invoice, HashMap<String, Play> plays) throws Exception {
        return renderPlainText(new StatementData(invoice, plays));
    }

    public String htmlStatement(Invoice invoice, HashMap<String, Play> plays) throws Exception {
        return renderHtml(new StatementData(invoice, plays));
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

    private String renderHtml(StatementData statementData) throws Exception {
        StringBuilder result = new StringBuilder(
            String.format("<h1> 청구내역 (고객명: %s)\n </h1>", statementData.getCustomer()));
        result.append("<table> \n");
        result.append("<tr><th> 연극 </th> <th>좌석 수</th> <th>금액</th>");
        for (Performance performance : statementData.getPerformances()) {
            result.append(String.format("<tr><td> %s: </td> <td> $%d </td> <td> %d석 </td></tr>\n",
                playFor(statementData.getPlays(), performance).getName(),
                amountFor(performance, playFor(statementData.getPlays(), performance)) / 100,
                performance.getAudience()));
        }
        result.append("</table>\n");

        result.append(String.format("총액: $%d\n", totalAmount(statementData) / 100));
        result.append(String.format("적립 포인트: %d점", totalVolumeCredits(statementData)));
        return result.toString();
    }

    private static int amountFor(Performance performance, Play play) throws Exception {
        return new PerformanceCalculator(performance, play).amount();
    }

    private static Play playFor(HashMap<String, Play> plays, Performance performance) {
        return plays.get(performance.getPlayID());
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

    private static int volumeCreditsFor(HashMap<String, Play> plays, Performance performance) {
        int result = 0;
        result += Math.max(performance.getAudience() - 30, 0);
        if ("comedy".equals(playFor(plays, performance).getType())) {
            result += Math.floor(performance.getAudience() / 5);
        }
        return result;
    }
}
