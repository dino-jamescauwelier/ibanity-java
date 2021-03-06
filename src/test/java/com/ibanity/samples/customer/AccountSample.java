package com.ibanity.samples.customer;

import com.ibanity.apis.client.models.Account;
import com.ibanity.apis.client.models.CustomerAccessToken;
import com.ibanity.apis.client.models.FinancialInstitution;
import com.ibanity.apis.client.models.factory.read.AccountReadQuery;
import com.ibanity.apis.client.models.factory.read.AccountsReadQuery;
import com.ibanity.apis.client.services.AccountsService;
import com.ibanity.apis.client.services.impl.AccountsServiceImpl;

import java.util.List;
import java.util.UUID;

public class AccountSample {
    private final AccountsService accountsService = new AccountsServiceImpl();

    public List<Account> list(CustomerAccessToken customerAccessToken, FinancialInstitution financialInstitution) {
        AccountsReadQuery accountsReadQuery = AccountsReadQuery.builder()
                .customerAccessToken(customerAccessToken.getToken())
                .financialInstitutionId(financialInstitution.getId())
                .build();

        return accountsService.list(accountsReadQuery);
    }

    public Account get(CustomerAccessToken customerAccessToken, FinancialInstitution financialInstitution, UUID accountId) {
        AccountReadQuery accountReadQuery = AccountReadQuery.builder()
                .customerAccessToken(customerAccessToken.getToken())
                .financialInstitutionId(financialInstitution.getId())
                .accountId(accountId)
                .build();

        return accountsService.find(accountReadQuery);
    }
}
