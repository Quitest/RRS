package ru.pel.rrs.exceptions;

//@Deprecated(forRemoval = true)
public class ExceptionBody {
    public final String url;
    public final String ex;

    public ExceptionBody(String url, Exception ex) {
        this.url = url;
        this.ex = ex.getLocalizedMessage();
    }
}
