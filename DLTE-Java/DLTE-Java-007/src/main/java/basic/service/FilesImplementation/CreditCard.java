package basic.service.FilesImplementation;

import java.io.Serializable;
import java.util.Date;

public class CreditCard implements Serializable {
    private Long creditCardNumber;
    private String creditCardHolder;
    private Date creditCardExpiry;
    private Integer creditCardCvv;
    private Long creditCardLimit;
    private Integer creditCardPin;

    @Override
    public String toString() {
        return "CreditCard{" +
                "creditCardNumber=" + creditCardNumber +
                ", creditCardHolder='" + creditCardHolder + '\'' +
                ", creditCardExpiry=" + creditCardExpiry +
                ", creditCardCvv=" + creditCardCvv +
                ", creditCardLimit=" + creditCardLimit +
                ", creditCardPin=" + creditCardPin +
                '}';
    }

    public Long getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(Long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardHolder() {
        return creditCardHolder;
    }

    public void setCreditCardHolder(String creditCardHolder) {
        this.creditCardHolder = creditCardHolder;
    }

    public Date getCreditCardExpiry() {
        return creditCardExpiry;
    }

    public void setCreditCardExpiry(Date creditCardExpiry) {
        this.creditCardExpiry = creditCardExpiry;
    }

    public Integer getCreditCardCvv() {
        return creditCardCvv;
    }

    public void setCreditCardCvv(Integer creditCardCvv) {
        this.creditCardCvv = creditCardCvv;
    }

    public Long getCreditCardLimit() {
        return creditCardLimit;
    }

    public void setCreditCardLimit(Long creditCardLimit) {
        this.creditCardLimit = creditCardLimit;
    }

    public Integer getCreditCardPin() {
        return creditCardPin;
    }

    public void setCreditCardPin(Integer creditCardPin) {
        this.creditCardPin = creditCardPin;
    }

    public CreditCard(Long creditCardNumber, String creditCardHolder, Date creditCardExpiry, Integer creditCardCvv, Long creditCardLimit, Integer creditCardPin) {
        this.creditCardNumber = creditCardNumber;
        this.creditCardHolder = creditCardHolder;
        this.creditCardExpiry = creditCardExpiry;
        this.creditCardCvv = creditCardCvv;
        this.creditCardLimit = creditCardLimit;
        this.creditCardPin = creditCardPin;
    }
}
