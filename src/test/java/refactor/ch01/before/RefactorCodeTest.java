package refactor.ch01.before;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.Test;
import refactor.ch01.refactored.Plays;
import refactor.ch01.refactored.refoctoredCode.RefactoredCode01;
import refactor.ch01.refactored.refoctoredCode.RefactoredCode02;
import refactor.ch01.refactored.refoctoredCode.RefactoredCode03;
import refactor.ch01.refactored.refoctoredCode.RefactoredCode04;
import refactor.ch01.refactored.refoctoredCode.RefactoredCode05;
import refactor.ch01.refactored.refoctoredCode.RefactoredCode06;
import refactor.ch01.refactored.refoctoredCode.RefactoredCode07;
import refactor.ch01.refactored.refoctoredCode.RefactoredCode08;

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
        RefactoredCode06 refactoredCode06 = new RefactoredCode06();
        RefactoredCode07 refactoredCode07 = new RefactoredCode07();
        RefactoredCode08 refactoredCode08 = new RefactoredCode08();

        List<Performance> performances = new ArrayList<>();
        performances.add(new Performance("hamlet", 55));
        performances.add(new Performance("as-like", 35));
        performances.add(new Performance("othello", 40));
        Invoice invoice = new Invoice("BigCo", performances);

        HashMap<String, Play> plays = new HashMap<>();
        plays.put("hamlet", new Play("hamlet", "tragedy"));
        plays.put("as-like", new Play("As You Like It", "comedy"));
        plays.put("othello", new Play("Othello", "tragedy"));

        Plays playsObject = new Plays(plays);

        String answer = "?????? ?????? (?????????: BigCo)\n" +
            "hamlet: $650 55???\n" +
            "As You Like It: $580 35???\n" +
            "Othello: $500 40???\n" +
            "??????: $1730\n" +
            "?????? ?????????: 47???";
        //when
        String beforeCoderesult = beforeCode.statement(invoice, plays);
        String refactor01Result = refactoredCode01.statement(invoice, plays);
        String refactor02Result = refactoredCode02.statement(invoice, plays);
        String refactor03Result = refactoredCode03.statement(invoice, plays);
        String refactor04Result = refactoredCode04.statement(invoice, plays);
        String refactor05Result = refactoredCode05.statement(invoice, plays);
        String refactor06Result = refactoredCode06.statement(invoice, plays);
        String refactor07Result = refactoredCode07.statement(invoice, playsObject);
        String refactor08Result = refactoredCode08.statement(invoice, playsObject);

        //then
        assertEquals(answer, beforeCoderesult);
        assertEquals(answer, refactor01Result);
        assertEquals(answer, refactor02Result);
        assertEquals(answer, refactor03Result);
        assertEquals(answer, refactor04Result);
        assertEquals(answer, refactor05Result);
        assertEquals(answer, refactor06Result);
        assertEquals(answer, refactor07Result);
        assertEquals(answer, refactor08Result);
    }
}