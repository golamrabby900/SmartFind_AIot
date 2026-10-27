package smartfind;

public class Claim {
    private final long claimId;
    private final long reportId;
    private final User claimant;
    private String verificationStatus = "PENDING";

    public Claim(long claimId, long reportId, User claimant) {
        this.claimId = claimId; this.reportId = reportId; this.claimant = claimant;
    }
    public long getClaimId() { return claimId; }
    public long getReportId() { return reportId; }
    public User getClaimant() { return claimant; }
    public String getVerificationStatus() { return verificationStatus; }
    public boolean markVerified(User officer) {
        if (officer == null || !officer.canVerifyClaims()) return false;
        verificationStatus = "VERIFIED";
        return true;
    }
}
