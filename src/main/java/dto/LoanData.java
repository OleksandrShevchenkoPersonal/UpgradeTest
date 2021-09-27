package dto;

import lombok.Data;

@Data
public class LoanData {
    private String amount;
    private String monthlyPayment;
    private String term;
    private String interestRate;
    private String aprInfo;

}
