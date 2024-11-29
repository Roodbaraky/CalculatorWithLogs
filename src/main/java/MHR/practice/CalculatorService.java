package MHR.practice;

import static java.lang.Double.isInfinite;

import static jakarta.transaction.Transactional.TxType.REQUIRED;
import static jakarta.transaction.Transactional.TxType.SUPPORTS;

import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Named
@Transactional(SUPPORTS)
@ApplicationScoped
public class CalculatorService {
    @Inject
    EntityManager em;
    private ScriptEngine scriptEngine;

    @PostConstruct
    public void init() {
        this.scriptEngine = new ScriptEngineManager().getEngineByName("JavaScript");
        if (this.scriptEngine == null) {
            throw new IllegalStateException("JavaScript engine not found.");
        }
    }

    public CalculationResult calculate(String expression) throws CalculationException {

        try {
            Object result = scriptEngine.eval(expression);
            System.out.println(result);
            if (result instanceof Number && !isInfinite((Double) result)) {
                return new CalculationResult(((Number) result).doubleValue());
            } else {
                result = 0.0;
                throw new CalculationException("Expression did not return a number.");

            }
        } catch (ScriptException e) {

            throw new CalculationException("Invalid expression: " + expression, e);
        }
    }
@Transactional(REQUIRED)
    public Error create(Exception exception, String expression) {
        Error error = new Error(exception, expression);
        em.persist(error);
        return error;
    }

    public List<Error> getAllErrors() {
        TypedQuery<Error> query = em.createQuery("SELECT e FROM Error e", Error.class);
        return query.getResultList();
    }

}
