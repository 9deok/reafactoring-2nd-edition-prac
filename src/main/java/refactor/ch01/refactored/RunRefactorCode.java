package refactor.ch01.refactored;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import refactor.ch01.before.Invoice;
import refactor.ch01.before.Play;
import refactor.ch01.refactored.refoctoredCode.RefactoredCode01;

public class RunRefactorCode {

    static ObjectMapper mapper = new ObjectMapper();

    public static void main(String... args) throws Exception {
        Invoice invoice = getInvoice();
        HashMap<String, Play> plays = getPlays();

        RefactoredCode01 refactoredCode01 = new RefactoredCode01();
        String statement = refactoredCode01.statement(invoice, plays);
        System.out.println("statement = " + statement);
    }

    private static Invoice getInvoice() throws IOException {
        InputStream invoicesJson = Invoice.class.getResourceAsStream("/invoices.json");
        List<Invoice> invoices = mapper.readValue(invoicesJson, List.class);
        Invoice invoice = mapper.convertValue(invoices.get(0), Invoice.class);
        return invoice;
    }

    private static HashMap<String, Play> getPlays() throws IOException {
        InputStream playsJson = Play.class.getResourceAsStream("/plays.json");
        HashMap<String, Play> plays
            = mapper.readValue(playsJson, new TypeReference<HashMap<String, Play>>() {
        });
        return plays;
    }
}
