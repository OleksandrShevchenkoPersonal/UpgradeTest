package dto;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class LoanRequestInfoDTO {
    private LoanAppResumptionInfo loanAppResumptionInfo;
    private List<String> offers;
    private String selectedOffer;
    private List<String> requiredAgreements;
    private List<String> resetOptions;
    private String originalLoanApp;

    @Getter
    public class LoanAppResumptionInfo {
        private long loanAppId;
        private String loanAppUuid;
        private String referrer;
        private String status;
        private String productType;
        private String sourceSystem;
        private double desiredAmount;
        private BorrowerInfo borrowerResumptionInfo;
        private BorrowerInfo coBorrowerResumptionInfo;
        private boolean turnDown;
        private boolean hasLogin;
        private String availableAppImprovements;
        private double cashOutAmount;
        private boolean canAddCollateral;
        private String rewardProgramId;
        private long rewardProgramCode;
        private String addon;
        private boolean isMobileDiscountApplied;
        private boolean checkingDiscountAvailable;
    }

    @Getter
    public class BorrowerInfo {
        private String firstName;
        private String maskedEmail;
        private boolean ssnRequired;
        private String state;
        private String email;
    }
}
