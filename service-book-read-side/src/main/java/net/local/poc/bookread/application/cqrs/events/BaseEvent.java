package net.local.poc.bookread.application.cqrs.events;

public abstract class BaseEvent {

    private Exception exception;
    private long startTime;
    private long stopTime;

    public abstract String toJson();

    public Exception getException() {
        return exception;
    }
    
    public void setException(Exception exception) {
        this.exception = exception;
    }

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void stopTimer() {
        stopTime = System.nanoTime();
    }

    public long getElapsedTimeInNano() {
        return stopTime - startTime;
    }

    public long getElapsedTimeInMilli() {
        return getElapsedTimeInNano() / 1_000_000L;
    }

    public boolean isSuccess() {
        return exception == null;
    }

    public boolean hasError() {
        return !isSuccess();
    }
}
