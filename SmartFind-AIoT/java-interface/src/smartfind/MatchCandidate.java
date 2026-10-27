package smartfind;

public class MatchCandidate {
    private final long sourceReportId;
    private final long candidateReportId;
    private final double score;
    private final String summary;

    public MatchCandidate(long sourceReportId, long candidateReportId, double score, String summary) {
        this.sourceReportId = sourceReportId;
        this.candidateReportId = candidateReportId;
        this.score = Math.max(0.0, Math.min(1.0, score));
        this.summary = summary;
    }
    public long getSourceReportId() { return sourceReportId; }
    public long getCandidateReportId() { return candidateReportId; }
    public double getScore() { return score; }
    @Override public String toString() {
        return String.format("Report #%d ↔ #%d | similarity %.0f%% | %s",
            sourceReportId, candidateReportId, score * 100, summary);
    }
}
