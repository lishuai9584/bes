package com.example.demo.camunda1;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * 贷款流程demo
 * */
public class LoanDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Double yearWages = (Double) delegateExecution.getVariable("yearWages");
        Double houseAssets = (Double) delegateExecution.getVariable("houseAssets");
        double sum = yearWages + houseAssets;
        if (sum<0){
            delegateExecution.setVariable("creditRating","C");
            delegateExecution.setVariable("loanLimit",0);
        }else if (sum>0 && sum<=100){
            delegateExecution.setVariable("creditRating","B");
            delegateExecution.setVariable("loanLimit",sum*0.8);
        }else if (sum>100){
            delegateExecution.setVariable("creditRating","A");
            delegateExecution.setVariable("loanLimit",sum*1.2);
        }
    }
}
