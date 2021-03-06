package com.ibanity.samples.helper;

import com.ibanity.apis.client.models.AccountInformationAccessRequest;
import com.ibanity.apis.client.models.CustomerAccessToken;
import com.ibanity.apis.client.models.FinancialInstitution;
import com.ibanity.apis.client.models.factory.create.PaymentInitiationRequestCreationQuery;
import com.ibanity.apis.client.sandbox.models.FinancialInstitutionAccount;
import com.ibanity.apis.client.sandbox.models.FinancialInstitutionUser;
import com.ibanity.apis.client.sandbox.models.factory.create.FinancialInstitutionTransactionCreationQuery;
import org.apache.commons.math3.util.Precision;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.iban4j.CountryCode;
import org.iban4j.Iban;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

public class SampleHelper {
    private static final Logger LOGGER = LogManager.getLogger(SampleHelper.class);

    public static double generateRandomAmount() {
        Random random = new Random();

        int randomDebitCreditSign = random.nextBoolean() ? -1 : 1;

        return Precision.round(
                random.doubles(10,100)
                        .findFirst().getAsDouble() * randomDebitCreditSign, 2);
    }

    public static void waitForAuthorizationWebFlow(AccountInformationAccessRequest accountInformationAccessRequest) {
        LOGGER.info("Open the following URL in your browser and follow the web flow:");
        LOGGER.info("   " + accountInformationAccessRequest.getLinks().getRedirect());
        LOGGER.info("Once the authorization is done, press ENTER to continue...");

        new Scanner(System.in).nextLine();
    }

    public static FinancialInstitutionTransactionCreationQuery generateRandomTransactionCreationQuery(
            FinancialInstitution financialInstitution,
            FinancialInstitutionUser financialInstitutionUser,
            FinancialInstitutionAccount financialInstitutionAccount) {

        return FinancialInstitutionTransactionCreationQuery.builder()
                .financialInstitutionId(financialInstitution.getId())
                .financialInstitutionUserId(financialInstitutionUser.getId())
                .financialInstitutionAccountId(financialInstitutionAccount.getId())

                .amount(generateRandomAmount())
                .currency("EUR")

                .description("Car rental")

                .counterpartName("Stroman, Hettinger and Swift")
                .counterpartReference(Iban.random(CountryCode.BE).getAccountNumber())

                .executionDate(Instant.now().plus(3, ChronoUnit.DAYS))
                .valueDate(Instant.now().minus(1, ChronoUnit.DAYS))

                .remittanceInformation("Aspernatur et quibusdam.")
                .remittanceInformationType("unstructured")

                .build();
    }

    public static PaymentInitiationRequestCreationQuery generateRandomPaymentInitiationRequestCreationQuery(
            FinancialInstitution financialInstitution, CustomerAccessToken customerAccessToken,
            String redirectUrl) {

        return PaymentInitiationRequestCreationQuery.builder()
                .customerAccessToken(customerAccessToken.getToken())
                .financialInstitutionId(financialInstitution.getId())

                .redirectUri(redirectUrl)
                .consentReference(UUID.randomUUID().toString())
                .endToEndId(generateRandomEnd2EndId())
                .productType("sepa-credit-transfer")

                .amount(generateRandomAmount())
                .currency("EUR")

                .creditorName("Fake Creditor Name")
                .creditorAccountReference(Iban.random(CountryCode.BE).toString())
                .creditorAccountReferenceType("IBAN")

                .remittanceInformationType("unstructured")
                .remittanceInformation("Payment initiation sample")
                .build();
    }

    public static String generateRandomEnd2EndId() {
        // End-2-End-Id length must be maximum 35 chars
        return UUID.randomUUID().toString().replace("-", "");
    }

}
