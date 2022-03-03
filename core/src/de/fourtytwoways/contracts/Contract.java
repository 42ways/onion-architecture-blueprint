package de.fourtytwoways.contracts;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.enums.types.Product;

import java.time.LocalDate;

public class Contract {
    private String contractNumber;
    private Product product;
    private LocalDate startDate;
    private LocalDate endDate;
    private double premium;

    protected Contract() {
    }

    public Contract(String contractNumber, Product product, LocalDate startDate, LocalDate endDate, double premium) {
        this.contractNumber = contractNumber;
        this.product = product;
        this.startDate = startDate;
        this.endDate = endDate;
        this.premium = premium;
    }

    public String toString() {
        return "CONTRACT [" + contractNumber + " - " + product + ", from " + startDate + " to " + endDate + " with premium " + premium + "]";
    }

    protected String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getPremium() {
        return premium;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }
}
