package refactor.ch01.refactored.refoctoredCode;

import refactor.ch01.before.Invoice;
import refactor.ch01.before.Performance;
import refactor.ch01.before.Play;
import refactor.ch01.refactored.Plays;
import refactor.ch01.refactored.StatementData;
import refactor.ch01.refactored.performaceCalculator.PerformanceCalculator;

public class RefactoredCode08 {

    public String statement(Invoice invoice, Plays plays) throws Exception {
        return renderPlainText(new StatementData(invoice, plays));
    }

    public String htmlStatement(Invoice invoice, Plays plays) throws Exception {
        return renderHtml(new StatementData(invoice, plays));
    }

    private static String renderPlainText(StatementData statementData)
        throws Exception {
        StringBuilder result =
            new StringBuilder(String.format("청구 내역 (고객명: %s)\n", statementData.getCustomer()));

        for (Performance performance : statementData.getPerformances()) {
            result.append(String.format("%s: $%d %d석\n",
                statementData.playFor(performance).getName(),
                amountFor(performance, statementData.playFor(performance)) / 100,
                performance.getAudience())
            );
        }

        result.append(String.format("총액: $%d\n", statementData.totalAmount() / 100));
        result.append(String.format("적립 포인트: %d점", statementData.totalVolumeCredits()));

        return result.toString();
    }

    private String renderHtml(StatementData statementData) throws Exception {
        StringBuilder result = new StringBuilder(
            String.format("<h1> 청구내역 (고객명: %s)\n </h1>", statementData.getCustomer()));
        result.append("<table> \n");
        result.append("<tr><th> 연극 </th> <th>좌석 수</th> <th>금액</th>");
        for (Performance performance : statementData.getPerformances()) {
            result.append(String.format("<tr><td> %s: </td> <td> $%d </td> <td> %d석 </td></tr>\n",
                statementData.playFor(performance).getName(),
                amountFor(performance, statementData.playFor(performance)) / 100,
                performance.getAudience()));
        }
        result.append("</table>\n");

        result.append(String.format("총액: $%d\n", statementData.totalAmount() / 100));
        result.append(String.format("적립 포인트: %d점", statementData.totalVolumeCredits()));
        return result.toString();
    }

    private static int amountFor(Performance performance, Play play) throws Exception {
        return new PerformanceCalculator(performance, play).amount();
    }

}
