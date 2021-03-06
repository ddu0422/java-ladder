package ladder.domain.participant;

import ladder.domain.ladder.Direction;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParticipantGroup {
    private static final int MINIMUM_PARTICIPANT_NUM = 2;
    private static final String MINIMUM_PARTICIPANT_NUM_ERROR = "참가자는 2명 이상이어야 합니다.";
    private static final String DUPLICATE_PARTICIPANT_NAME = "중복되는 참가자 명이 있습니다.";

    private final List<Participant> participants;

    public ParticipantGroup(List<String> names) {
        checkMinimumParticipants(names);
        checkDuplicatedParticipant(names);
        this.participants = generate(names);
    }

    private void checkMinimumParticipants(List<String> names) {
        if (names.size() < MINIMUM_PARTICIPANT_NUM) {
            throw new InvalidParticipantGroupException(MINIMUM_PARTICIPANT_NUM_ERROR);
        }
    }

    private void checkDuplicatedParticipant(List<String> names) {
        if (names.size() != new HashSet<>(names).size()) {
            throw new InvalidParticipantGroupException(DUPLICATE_PARTICIPANT_NAME);
        }
    }

    private List<Participant> generate(List<String> names) {
        return IntStream.range(0, names.size())
                .mapToObj(i -> new Participant(names.get(i), new Position(i)))
                .collect(Collectors.toList());
    }

    public void move(List<Direction> directions) {
        for (Participant participant : participants) {
            participant.move(directions.get(participant.getPosition()));
        }
    }

    public List<Integer> createResultPositions() {
        return participants.stream()
                .map(Participant::getPosition)
                .collect(Collectors.toList());
    }

    public List<String> createNames() {
        return participants.stream()
                .map(Participant::getName)
                .collect(Collectors.toList());
    }

    public int getSize() {
        return participants.size();
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParticipantGroup that = (ParticipantGroup) o;
        return Objects.equals(participants, that.participants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(participants);
    }
}
