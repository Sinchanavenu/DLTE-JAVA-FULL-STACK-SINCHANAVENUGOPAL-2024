package basic.service.Review1;

public class Details {
    private String employeeFirstName;
    private String employeeMiddleName;
    private String employeeLastName;
    private Long employeePhone;
    private Integer employeeId;
    private String employeeEmail;

    public Details() {

    }


    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeMiddleName() {
        return employeeMiddleName;
    }

    public void setEmployeeMiddleName(String employeeMiddleName) {
        this.employeeMiddleName = employeeMiddleName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public Long getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(Long employeePhone) {
        this.employeePhone = employeePhone;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public Details( String employeeFirstName, String employeeMiddleName, String employeeLastName, Long employeePhone, Integer employeeId, String employeeEmail) {
        this.employeeFirstName = employeeFirstName;
        this.employeeMiddleName = employeeMiddleName;
        this.employeeLastName = employeeLastName;
        this.employeePhone = employeePhone;
        this.employeeId = employeeId;
        this.employeeEmail = employeeEmail;
    }

}

class CurrentAddress {
   // CurrentAddress address = new CurrentAddress();
    private String houseName;
    private String streetName;
    private String cityName;
    private String stateName;
    private Integer pinCode;

    public CurrentAddress() {

    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Integer getPinCode() {
        return pinCode;
    }

    public void setPinCode(Integer pinCode) {
        this.pinCode = pinCode;
    }

    public CurrentAddress(String houseName, String streetName, String cityName, String stateName, Integer pinCode) {
        this.houseName = houseName;
        this.streetName = streetName;
        this.cityName = cityName;
        this.stateName = stateName;
        this.pinCode = pinCode;
    }
}

    class PermanentAddress {
        //PermanentAddress address1=new PermanentAddress();
        private String houseName1;
        private String streetName1;
        private String cityName1;
        private String stateName1;
        private Integer pinCode1;

        public PermanentAddress() {

        }

        public String getHouseName1() {
            return houseName1;
        }

        public void setHouseName1(String houseName1) {
            this.houseName1 = houseName1;
        }

        public String getStreetName1() {
            return streetName1;
        }

        public void setStreetName1(String streetName1) {
            this.streetName1 = streetName1;
        }

        public String getCityName1() {
            return cityName1;
        }

        public void setCityName1(String cityName1) {
            this.cityName1 = cityName1;
        }

        public String getStateName1() {
            return stateName1;
        }

        public void setStateName1(String stateName1) {
            this.stateName1 = stateName1;
        }

        public Integer getPinCode1() {
            return pinCode1;
        }

        public void setPinCode1(Integer pinCode1) {
            this.pinCode1 = pinCode1;
        }

        public PermanentAddress(String houseName1, String streetName1, String cityName1, String stateName1, Integer pinCode1) {
            this.houseName1 = houseName1;
            this.streetName1 = streetName1;
            this.cityName1 = cityName1;
            this.stateName1 = stateName1;
            this.pinCode1 = pinCode1;
        }
    }