package refactor.ch01.before;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class Demo {

    static ObjectMapper mapper = new ObjectMapper();

    public static void main(String... args) throws Exception {
        Invoice invoice = getInvoice();
        HashMap<String, Play> plays = getPlays();

        BeforeCode beforeCode = new BeforeCode();
        String statement = beforeCode.statement(invoice, plays);
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
