package com.ibanity.apis.client.sandbox.models;

import com.ibanity.apis.client.paging.IbanityPagingBehavior;
import io.crnk.core.resource.annotations.JsonApiResource;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonApiResource(type = "financialInstitutionUser", resourcePath = "financial-institution-users", pagingBehavior = IbanityPagingBehavior.class)
public class FinancialInstitutionUser extends AbstractTimestamps {

    private String password;
    private String firstName;
    private String lastName;
    private String login;

    public FinancialInstitutionUser(final String password, final String firstName, final String lastName, final String login) {
        super();
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;

    }

    public FinancialInstitutionUser() {
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof FinancialInstitutionUser)) {
            return false;
        }

        FinancialInstitutionUser that = (FinancialInstitutionUser) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getPassword(), that.getPassword())
                .append(getFirstName(), that.getFirstName())
                .append(getLastName(), that.getLastName())
                .append(getLogin(), that.getLogin())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getPassword())
                .append(getFirstName())
                .append(getLastName())
                .append(getLogin())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(super.toString())
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("login", login)
                .append("password", password)
                .toString();
    }
}
