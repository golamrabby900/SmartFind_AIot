package smartfind;

import java.util.*;

public class SmartFindService {
    private final List<ItemReport> reports = new ArrayList<>();
    private final List<IntakeEvent> intakeEvents = new ArrayList<>();
    private long nextReportId = 1001;

    public ItemReport createReport(String type, String category, String description, String location) {
        ItemReport report = new ItemReport(nextReportId++, type, category, description, location,
                java.time.LocalDate.now());
        if (!report.validate()) throw new IllegalArgumentException("Type, category and description are required.");
        reports.add(report);
        return report;
    }
    public List<ItemReport> getReports() { return Collections.unmodifiableList(reports); }

    public List<MatchCandidate> findMatches(ItemReport source) {
        List<MatchCandidate> result = new ArrayList<>();
        for (ItemReport other : reports) {
            if (source.getReportId() == other.getReportId()) continue;
            if (source.getReportType().equals(other.getReportType())) continue;
            String a = (source.getCategory() + " " + source.getDescription()).toLowerCase();
            String b = (other.getCategory() + " " + other.getDescription()).toLowerCase();
            Set<String> wordsA = new HashSet<>(Arrays.asList(a.split("\\W+")));
            Set<String> wordsB = new HashSet<>(Arrays.asList(b.split("\\W+")));
            wordsA.remove(""); wordsB.remove("");
            Set<String> intersection = new HashSet<>(wordsA); intersection.retainAll(wordsB);
            Set<String> union = new HashSet<>(wordsA); union.addAll(wordsB);
            double score = union.isEmpty() ? 0 : (double) intersection.size() / union.size();
            if (score > 0) result.add(new MatchCandidate(source.getReportId(), other.getReportId(), score,
                    "Prototype word-overlap baseline; staff must verify"));
        }
        result.sort(Comparator.comparingDouble(MatchCandidate::getScore).reversed());
        return result;
    }
    public boolean recordIntake(IntakeEvent event) {
        if (event == null || event.getEventId() == null || event.getEventId().isBlank()) return false;
        if (intakeEvents.stream().anyMatch(e -> e.getEventId().equals(event.getEventId()))) return false;
        intakeEvents.add(event);
        return true;
    }
    public List<IntakeEvent> getIntakeEvents() { return Collections.unmodifiableList(intakeEvents); }
}
