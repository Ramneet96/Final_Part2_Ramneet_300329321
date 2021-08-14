public class Savings {


    //declaration of variables
    private String custNum;
    private String custName;
    private String custDeposit;
    private String noOfYears;
    private String savings;

    //constructor
    public Savings(String custNum, String custName, String custDeposit, String noOfYears, String savings) {
        this.custNum = custNum;
        this.custName = custName;
        this.custDeposit = custDeposit;
        this.noOfYears = noOfYears;
        this.savings = savings;
    }

    //getters and setters

    public String getCustNum() {
        return custNum;
    }

    public void setCustNum(String custNum) {
        this.custNum = custNum;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustDeposit() {
        return custDeposit;
    }

    public void setCustDeposit(String custDeposit) {
        this.custDeposit = custDeposit;
    }

    public String getNoOfYears() {
        return noOfYears;
    }

    public void setNoOfYears(String noOfYears) {
        this.noOfYears = noOfYears;
    }

    public String getSavings() {
        return savings;
    }

    public void setSavings(String savings) {
        this.savings = savings;
    }
}
