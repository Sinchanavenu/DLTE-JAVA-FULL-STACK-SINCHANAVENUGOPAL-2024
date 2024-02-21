package basic.service;

public class Bond {
    public Bond(Integer maturityAmount, Double rateOfInterest, String taxStatus, String bondHolder, Integer tenure){
        this.maturityAmount=maturityAmount;
        this.rateOfInterest=rateOfInterest;
        this.taxStatus=taxStatus;
        this.bondHolder=bondHolder;
        this.tenure=tenure;
    }
    private Integer maturityAmount;
    private Double rateOfInterest;
    private String taxStatus;
    private String bondHolder;

    public Integer getMaturityAmount() {
        return maturityAmount;
    }

    public void setMaturityAmount(Integer maturityAmount) {
        this.maturityAmount = maturityAmount;
    }

    public Double getRateOfInterest() {
        return rateOfInterest;
    }

    public void setRateOfInterest(Double rateOfInterest) {
        this.rateOfInterest = rateOfInterest;
    }

    public String getTaxStatus() {
        return taxStatus;
    }

    public void setTaxStatus(String taxStatus) {
        this.taxStatus = taxStatus;
    }

    public String getBondHolder() {
        return bondHolder;
    }

    public void setBondHolder(String bondHolder) {
        this.bondHolder = bondHolder;
    }

    public Integer getTenure() {
        return tenure;
    }

    public void setTenure(Integer tenure) {
        this.tenure = tenure;
    }

    private Integer tenure;

}
