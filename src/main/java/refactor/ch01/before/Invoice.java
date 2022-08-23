package refactor.ch01.before;

import java.util.List;
import lombok.Getter;

@Getter
public class Invoice {

    private String customer;
    private List<Performance> performances;
}
