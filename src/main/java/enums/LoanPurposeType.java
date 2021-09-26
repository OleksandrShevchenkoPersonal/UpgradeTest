package enums;

public enum LoanPurposeType {

    CREDIT_CARD("Pay off Credit Cards"),
    DEBT_CONSOLIDATION("Debt Consolidation"),
    SMALL_BUSINESS("Business"),
    HOME_IMPROVEMENT("Home Improvement"),
    LARGE_PURCHASE("Large Purchase"),
    OTHER("Other");

    private final String purpose;

    LoanPurposeType(final String purpose) {
        this.purpose = purpose;
    }

    @Override
    public String toString() {
        return purpose;
    }
}
