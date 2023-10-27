package com.root_developers.calculador.services.impl;

import com.root_developers.calculador.dtos.IncomeTaxDto;
import com.root_developers.calculador.dtos.IncomeTaxReturnDto;
import com.root_developers.calculador.exceptions.IncomeTaxException;
import com.root_developers.calculador.services.IncomeTaxService;
import com.root_developers.calculador.utils.Format;
import org.springframework.stereotype.Service;
@Service
public class IncomeTaxServiceImpl implements IncomeTaxService {

    @Override
    public IncomeTaxReturnDto calculate(IncomeTaxDto incomeTaxDto) {
        if(incomeTaxDto.getMonthlyEducationExpense() > 3561.50) {
            throw new IncomeTaxException(" Gasto com educação não pode passar de 3.561,50");
        }

        double inss;
        double income = incomeTaxDto.getMonthlyIncome();

        if(income > 0.0 && income <= 1320.00) {
            inss = income * 0.075;
        } else if(income > 1320.00 && income <= 2571.29) {
            inss = income * 0.09;
        } else if(income > 2571.29 && income <= 3856.94) {
            inss = income * 0.12;
        } else if(income > 3856.94 && income <= 7507.49) {
            inss = income * 0.14;
        } else {
            inss = income * 0.14;
        }

        double totalIncomeAfterInss = incomeTaxDto.getMonthlyIncome() - inss;
        double totalExpenses = (incomeTaxDto.getTotalDependents() * 189.59) + incomeTaxDto.getMonthlyAlimonyExpense() +
                incomeTaxDto.getMonthlyEducationExpense() + incomeTaxDto.getMonthlyMedicalExpense() + incomeTaxDto.getOtherDeductions();
        double totalAfterDeductions = totalIncomeAfterInss - totalExpenses;
        double totalPayable;

        if(totalAfterDeductions > 0.0 && totalAfterDeductions <= 2112.00) {
            totalPayable = 0.0;
        } else if (totalAfterDeductions > 2112.00 && totalAfterDeductions <= 2826.65) {
            totalPayable = (totalAfterDeductions * 0.075) - 142.80;
        } else if (totalAfterDeductions > 2826.65 && totalAfterDeductions <= 3751.05) {
            totalPayable = (totalAfterDeductions * 0.15) - 354.80;
        } else if (totalAfterDeductions > 3751.05 && totalAfterDeductions <= 4664.68) {
            totalPayable = (totalAfterDeductions * 0.225) - 636.13;
        } else {
            totalPayable = (totalAfterDeductions * 0.275) - 869.36;
        }

        double totalPayableFormat = Format.formatar(totalPayable);

        return new IncomeTaxReturnDto(totalPayableFormat);
    }

}
