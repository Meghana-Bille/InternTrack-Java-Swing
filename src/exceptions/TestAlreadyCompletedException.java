package exceptions;

public class TestAlreadyCompletedException
        extends Exception {

    public TestAlreadyCompletedException(
            String message) {

        super(message);
    }
}