package refactor.ch01.refactored;

import java.util.HashMap;
import refactor.ch01.before.Performance;
import refactor.ch01.before.Play;

public class Plays {

    private HashMap<String, Play> playMap = new HashMap<>();

    public Plays(HashMap<String, Play> playMap) {
        this.playMap = playMap;
    }

    public Play get(Performance performance) {
        return playMap.get(performance.getPlayID());
    }
}
