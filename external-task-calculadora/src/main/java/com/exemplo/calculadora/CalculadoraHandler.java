package com.exemplo.calculadora;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.stereotype.Component;

@Component
@ExternalTaskSubscription("calculadora")
public class CalculadoraHandler implements ExternalTaskHandler {


    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        long resultado = 0L;

        long a = externalTask.getVariable("a");
        long b = externalTask.getVariable("b");

        String operacao = externalTask.getVariable("operacao");

        switch (operacao) {
            case "soma":
                resultado = a + b;
                break;
            case "substracao":
                resultado = a - b;
                break;
            case "multiplicacao":
                resultado = a * b;
                break;
            case "divisao":
                resultado = a / b;
                break;
        }

        VariableMap variableMap = Variables.createVariables();
        variableMap.put("resultado", resultado);
        externalTaskService.complete(externalTask, variableMap);

    }
}
