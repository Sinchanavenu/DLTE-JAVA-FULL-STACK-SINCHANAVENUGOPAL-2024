package basic.service.ExceptionCreditCard;

/*
Create "MyBankCreditCardException"

Analysis following in individual methods:

Filter based on the given limit, if nothing found raise an MyBankCreditCardException, do not handle it in the filter method rather handle it in main
Filter based on the date of bill payment,if nothing found raise an MyBankCreditCardException, do not handle it in the filter method rather handle it in main
 */


import java.util.Date;

public class CreditCard {
    public CreditCard(Long creditCardNumber, String creditCardHolder, Date creditCardExpiry, Integer creditCardCvv, Integer creditCardLimit, Date dateOfBillGeneration, Date dateOfBillPayment, Integer creditCardPin) {
        this.creditCardNumber  = creditCardNumber;
        this.creditCardHolder = creditCardHolder;
        this.creditCardExpiry = creditCardExpiry;
        this.creditCardCvv = creditCardCvv;
        this.creditCardLimit = creditCardLimit;
        this.dateOfBillGeneration = dateOfBillGeneration;
        this.dateOfBillPayment = dateOfBillPayment;
        this.creditCardPin = creditCardPin;
   }


/*
        @Override
        public String toString() {
            return "CreditCard{" +
                    "creditCardNumber=" + creditCardNumber +
                    ", creditCardHolder='" + creditCardHolder + '\'' +
                    ", creditCardExpiry=" + creditCardExpiry +
                    ", creditCardCvv=" + creditCardCvv +
                    ", creditCardLimit=" + creditCardLimit +
                    ", dateOfBillGeneration=" + dateOfBillGeneration +
                    ", dateOfBillPayment=" + dateOfBillPayment +
                    ", creditCardPin=" + creditCardPin +
                    '}';
        }

 */

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

        public Integer getCreditCardLimit() {
            return creditCardLimit;
        }

        public void setCreditCardLimit(Integer creditCardLimit) {
            this.creditCardLimit = creditCardLimit;
        }

        public Date getDateOfBillGeneration() {
            return dateOfBillGeneration;
        }

        public void setDateOfBillGeneration(Date dateOfBillGeneration) {
            this.dateOfBillGeneration = dateOfBillGeneration;
        }

        public Date getDateOfBillPayment() {
            return dateOfBillPayment;
        }

        public void setDateOfBillPayment(Date dateOfBillPayment) {
            this.dateOfBillPayment = dateOfBillPayment;
        }

        public Integer getCreditCardPin() {
            return creditCardPin;
        }

        public void setCreditCardPin(Integer creditCardPin) {
            this.creditCardPin = creditCardPin;
        }
        private Long creditCardNumber;
        private String creditCardHolder;
        private Date creditCardExpiry;
        private Integer creditCardCvv;
        private Integer creditCardLimit;
        private Date dateOfBillGeneration;
        private Date dateOfBillPayment;
        private Integer creditCardPin;
    }
