package refactor.ch01.before;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Invoice {

    private String customer;
    private List<Performance> performances;
}
