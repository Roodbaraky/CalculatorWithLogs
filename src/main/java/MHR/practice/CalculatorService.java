package MHR.practice;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CalculatorService {
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
            if (result instanceof Number) {
                return new CalculationResult(((Number) result).doubleValue());
            } else {
                throw new CalculationException("Expression did not return a number.");
            }
        } catch (ScriptException e) {
            throw new CalculationException("Invalid expression: " + expression, e);
        }
    }
}
