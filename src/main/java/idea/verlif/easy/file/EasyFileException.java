package idea.verlif.easy.file;

public class EasyFileException extends RuntimeException {
    public EasyFileException() {
    }

    public EasyFileException(String message) {
        super(message);
    }

    public EasyFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public EasyFileException(Throwable cause) {
        super(cause);
    }

    public EasyFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
