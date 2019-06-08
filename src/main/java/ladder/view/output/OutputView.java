package ladder.view.output;

import ladder.domain.ladder.Ladder;
import ladder.domain.participant.Participant;
import ladder.domain.participant.ParticipantGroup;
import ladder.domain.result.Result;
import ladder.domain.result.ResultGroup;

import java.util.Map;

public class OutputView {
    public static void outputParticipants(ParticipantGroup participantGroup) {
        participantGroup.getParticipants().forEach(participant -> System.out.printf("%-6s", participant));
        emptyLine();
    }

    public static void outputLadder(Ladder ladder) {
        ladder.getLines().forEach(System.out::println);
    }

    public static void outputResults(ResultGroup resultGroup) {
        resultGroup.getResults().forEach(result -> System.out.printf("%-6s", result));
        emptyLine();
    }

    public static void outputResult(String name, Map<String, Result> result) {
        System.out.println("실행 결과");
        if (result.containsKey(name)) {
            System.out.println(result.get(name));
            return;
        }
        if (name.equals(Participant.ALL_PLAYER)) {
            outputResultAll(result);
            return;
        }
        System.out.println("존재하지 않습니다.");
    }

    private static void outputResultAll(Map<String, Result> result) {
        result.forEach((key, value) -> System.out.println(key + " : " + value));
    }

    private static void emptyLine() {
        System.out.println();
    }
}
