package ladder.controller;

import ladder.domain.ladder.Ladder;
import ladder.domain.ladder.LadderGame;
import ladder.domain.ladder.LadderGenerator;
import ladder.domain.participant.Participant;
import ladder.domain.participant.ParticipantGroup;
import ladder.domain.result.ResultGenerator;
import ladder.domain.result.ResultGroup;
import ladder.view.input.InputView;
import ladder.view.output.OutputView;

public class Main {
    public static void main(String[] args) {
        ParticipantGroup participantGroup = createParticipantGroup();
        ResultGroup resultGroup = createResultGroup(participantGroup);
        Ladder ladder = createLadder(participantGroup);

        OutputView.outputParticipants(participantGroup);
        OutputView.outputLadder(ladder);
        OutputView.outputResults(resultGroup);
        LadderGame ladderGame = createLadderGame(participantGroup, ladder, resultGroup);
        ladderGame.play();

        outputResult(ladderGame);
    }

    private static void outputResult(LadderGame ladderGame) {
        String name;
        do {
            name = InputView.inputParticipant();
            OutputView.outputResult(name, ladderGame.result());
        } while (!name.equals(Participant.ALL_PLAYER));
    }

    private static ParticipantGroup createParticipantGroup() {
        try {
            return new ParticipantGroup(InputView.inputParticipants());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createParticipantGroup();
        }
    }

    private static ResultGroup createResultGroup(ParticipantGroup participantGroup) {
        try {
            return new ResultGroup(participantGroup, ResultGenerator.generate(InputView.inputResult()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createResultGroup(participantGroup);
        }
    }

    private static Ladder createLadder(ParticipantGroup participantGroup) {
        try {
            return LadderGenerator.generate(participantGroup, InputView.inputLadderHeight());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createLadder(participantGroup);
        }
    }

    private static LadderGame createLadderGame(ParticipantGroup participantGroup, Ladder ladder, ResultGroup resultGroup) {
        return new LadderGame(participantGroup, ladder, resultGroup);
    }
}
