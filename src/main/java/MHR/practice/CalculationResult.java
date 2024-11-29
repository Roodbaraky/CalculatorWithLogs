package MHR.practice;

import java.io.PrintWriter;
import java.io.StringWriter;

import jakarta.json.bind.annotation.JsonbProperty;

public class CalculationResult {
    @JsonbProperty("result")
    private Double result;
    private String errorMessage;
    private String stackTrace;

    public CalculationResult(Double result) {
        this.result = result;
    }

    public CalculationResult(String errorMessage, Throwable exception) {
        this.errorMessage = errorMessage;
        this.stackTrace = getStackTraceAsString(exception);
    }

    private String getStackTraceAsString(Throwable exception) {
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    public Double getResult() {
        return result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getStackTrace() {
        return stackTrace;
    }
}
