package refactor.ch01.before;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.Test;
import refactor.ch01.refactored.RefactoredCode01;
import refactor.ch01.refactored.RefactoredCode02;
import refactor.ch01.refactored.RefactoredCode03;
import refactor.ch01.refactored.RefactoredCode04;
import refactor.ch01.refactored.RefactoredCode05;

class RefactorCodeTest {

    @Test
    void statementTest() throws Exception {
        //given
        BeforeCode beforeCode = new BeforeCode();
        RefactoredCode01 refactoredCode01 = new RefactoredCode01();
        RefactoredCode02 refactoredCode02 = new RefactoredCode02();
        RefactoredCode03 refactoredCode03 = new RefactoredCode03();
        RefactoredCode04 refactoredCode04 = new RefactoredCode04();
        RefactoredCode05 refactoredCode05 = new RefactoredCode05();

        List<Performance> performances = new ArrayList<>();
        performances.add(new Performance("hamlet", 55));
        performances.add(new Performance("as-like", 35));
        performances.add(new Performance("othello", 40));
        Invoice invoice = new Invoice("BigCo", performances);

        HashMap<String, Play> plays = new HashMap<>();
        plays.put("hamlet", new Play("hamlet", "tragedy"));
        plays.put("as-like", new Play("As You Like It", "comedy"));
        plays.put("othello", new Play("Othello", "tragedy"));

        String answer = "청구 내역 (고객명: BigCo)\n" +
            "hamlet: $650 55석\n" +
            "As You Like It: $580 35석\n" +
            "Othello: $500 40석\n" +
            "총액: $1730\n" +
            "적립 포인트: 47점";
        //when
        String beforeCoderesult = beforeCode.statement(invoice, plays);
        String refactor01Result = refactoredCode01.statement(invoice, plays);
        String refactor02Result = refactoredCode02.statement(invoice, plays);
        String refactor03Result = refactoredCode03.statement(invoice, plays);
        String refactor04Result = refactoredCode04.statement(invoice, plays);
        String refactor05Result = refactoredCode05.statement(invoice, plays);
        //then
        assertEquals(answer, beforeCoderesult);
        assertEquals(answer, refactor01Result);
        assertEquals(answer, refactor02Result);
        assertEquals(answer, refactor03Result);
        assertEquals(answer, refactor04Result);
        assertEquals(answer, refactor05Result);
    }
}