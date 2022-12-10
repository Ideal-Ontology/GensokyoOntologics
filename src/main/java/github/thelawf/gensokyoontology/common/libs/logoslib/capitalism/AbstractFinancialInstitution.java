package github.thelawf.gensokyoontology.common.libs.logoslib.capitalism;

public class AbstractFinancialInstitution implements IFinancial{

    double profit;
    double deficit;
    double budget;
    double revenue;
    double expenditure;
    double tax;
    double doubt;

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getDeficit() {
        return deficit;
    }

    public void setDeficit(double deficit) {
        this.deficit = deficit;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public double getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(double expenditure) {
        this.expenditure = expenditure;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getDoubt() {
        return doubt;
    }

    public void setDoubt(double doubt) {
        this.doubt = doubt;
    }
}
