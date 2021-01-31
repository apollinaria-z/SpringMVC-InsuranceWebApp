package by.zolotaya.app.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class Policy {
    private int id;
    private Client client;

   @NotEmpty(message = "Property should not be empty")
    private String property;

    private Coverage coverage;

    @NotEmpty(message = "Price should not be empty")
    @Min(value = 0, message = "Prise should be greater than 0")
    private int price;

    public Policy() {
    }

    public Policy(int id, Client client, String property, Coverage coverage, int price) {
        this.id = id;
        this.client = client;
        this.property = property;
        this.coverage = coverage;
        this.price = price;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Coverage getCoverage() {
        return coverage;
    }

    public void setCoverage(Coverage coverage) {
        this.coverage = coverage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Policy policy = (Policy) o;
        return id == policy.id &&
                price == policy.price &&
                Objects.equals(client, policy.client) &&
                Objects.equals(property, policy.property) &&
                coverage == policy.coverage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, property, coverage, price);
    }
}
