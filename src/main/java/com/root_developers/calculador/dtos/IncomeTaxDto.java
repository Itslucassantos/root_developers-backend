package com.root_developers.calculador.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IncomeTaxDto {

    @NotNull(message = "Não pode ser null, precisa ter um número")
    private double monthlyIncome;

    @NotNull(message = "Não pode ser null, precisa ter um número")
    private int totalDependents;

    @NotNull(message = "Não pode ser null, precisa ter um número")
    private double monthlyAlimonyExpense;

    @NotNull(message = "Não pode ser null, precisa ter um número")
    private double monthlyEducationExpense;

    @NotNull(message = "Não pode ser null, precisa ter um número")
    private double monthlyMedicalExpense;

    @NotNull(message = "Não pode ser null, precisa ter um número")
    private double otherDeductions;
}
