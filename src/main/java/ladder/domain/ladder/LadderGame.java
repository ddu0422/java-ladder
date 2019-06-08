package ladder.domain.ladder;

import ladder.domain.participant.ParticipantGroup;
import ladder.domain.result.Result;
import ladder.domain.result.ResultGroup;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LadderGame {
    private final ParticipantGroup participantGroup;
    private final Ladder ladder;
    private final ResultGroup resultGroup;

    public LadderGame(ParticipantGroup participantGroup, Ladder ladder, ResultGroup resultGroup) {
        this.participantGroup = participantGroup;
        this.ladder = ladder;
        this.resultGroup = resultGroup;
    }

    public void play() {
        List<Line> lines = ladder.getLines();
        for (Line line : lines) {
            participantGroup.move(line.getDirections());
        }
    }

    public Map<String, Result> result() {
        List<String> names = participantGroup.createNames();
        List<Result> results = resultGroup.changeResult();

        return IntStream.range(0, participantGroup.getSize())
                .boxed()
                .collect(Collectors.toMap(names::get, results::get));
    }
}
