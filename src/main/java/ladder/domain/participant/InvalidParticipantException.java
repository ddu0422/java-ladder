package ladder.domain.participant;

public class InvalidParticipantException extends RuntimeException {
    public InvalidParticipantException(String message) {
        super(message);
    }
}
