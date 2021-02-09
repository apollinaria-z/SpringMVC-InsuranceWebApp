package by.zolotaya.app.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class PolicyRequestModel {
    private int policyid;
    @NotEmpty(message = "Clientid should not be empty")
    private int clientid;
    @NotEmpty(message = "Property should not be empty")
    private String property;
    private String coverage;
    @NotEmpty(message = "Price should not be empty")
    @Min(value = 0, message = "Prise should be greater than 0")
    private int price;

    public PolicyRequestModel(int policyid, int clientid, String property, String coverage, int price) {
        this.policyid = policyid;
        this.clientid = clientid;
        this.property = property;
        this.coverage = coverage;
        this.price = price;
    }

    public int getPolicyid() {
        return policyid;
    }

    public void setPolicyid(int policyid) {
        this.policyid = policyid;
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getCoverage() {
        return coverage;
    }

    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
