package refactor.ch01;

import lombok.AllArgsConstructor;
import refactor.ch01.before.Performance;
import refactor.ch01.before.Play;

@AllArgsConstructor
public class PerformanceCalculator {

    private Performance performance;
    private Play play;

    public int amount() throws Exception {
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
}
