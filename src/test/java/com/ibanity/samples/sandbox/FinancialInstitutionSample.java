package com.ibanity.samples.sandbox;

import com.ibanity.apis.client.models.FinancialInstitution;
import com.ibanity.apis.client.models.factory.read.FinancialInstitutionReadQuery;
import com.ibanity.apis.client.sandbox.models.factory.create.FinancialInstitutionCreationQuery;
import com.ibanity.apis.client.sandbox.models.factory.delete.FinancialInstitutionDeleteQuery;
import com.ibanity.apis.client.sandbox.models.factory.update.FinancialInstitutionUpdateQuery;
import com.ibanity.apis.client.sandbox.services.SandboxFinancialInstitutionsService;
import com.ibanity.apis.client.sandbox.services.impl.SandboxFinancialInstitutionsServiceImpl;

import java.util.UUID;

public class FinancialInstitutionSample {
    private final SandboxFinancialInstitutionsService sandBoxFinancialInstitutionsService = new SandboxFinancialInstitutionsServiceImpl();

    public FinancialInstitution create(){
        FinancialInstitutionCreationQuery financialInstitutionCreationQuery =
                FinancialInstitutionCreationQuery.builder()
                        .name("HSBC")
                        .build();

        return sandBoxFinancialInstitutionsService.create(financialInstitutionCreationQuery);
    }

    public FinancialInstitution update(FinancialInstitution financialInstitution) {
        FinancialInstitutionUpdateQuery financialInstitutionUpdateQuery =
                FinancialInstitutionUpdateQuery.from(financialInstitution)
                        .name("HSBC Group")
                        .build();

        return sandBoxFinancialInstitutionsService.update(financialInstitutionUpdateQuery);
    }

    public FinancialInstitution find(UUID financialInstitutionId) {
        FinancialInstitutionReadQuery financialInstitutionReadQuery =
                FinancialInstitutionReadQuery.builder()
                        .financialInstitutionId(financialInstitutionId)
                        .build();

        return sandBoxFinancialInstitutionsService.find(financialInstitutionReadQuery);
    }

    public void delete(FinancialInstitution financialInstitution){
        FinancialInstitutionDeleteQuery financialInstitutionDeleteQuery =
                FinancialInstitutionDeleteQuery.builder()
                        .financialInstitutionId(financialInstitution.getId())
                        .build();

        sandBoxFinancialInstitutionsService.delete(financialInstitutionDeleteQuery);
    }
}
