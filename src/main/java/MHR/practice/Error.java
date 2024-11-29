package MHR.practice;

import java.util.Date;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Entity
@Table(name = "T_ERROR_LOG")
@Schema(name = "Error", description = "POJO that represents an error")
public class Error {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String message;


    private String cause;

    @NotNull
    private Date dateTime;

    @NotNull
    @JsonbProperty("expression")
    private String expression;
    public Error(Exception exception, String expression){
        this.message = exception.getMessage();
        this.cause = exception.getCause()==null ? null : exception.getCause().toString();
        this.dateTime = new Date();
        this.expression = expression;

    }

    public Error() {

    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getCause() {
        return cause;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public String getExpression() {
        return expression;
    }
}
