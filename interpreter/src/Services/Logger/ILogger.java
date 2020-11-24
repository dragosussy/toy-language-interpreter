package Services.Logger;

public interface ILogger {
    void append(String message);

    void log();

    void flushBuffer();
}