package smartfind;

import java.time.LocalDate;

public class ItemReport {
    private final long reportId;
    private final String reportType;
    private final String category;
    private final String description;
    private final String location;
    private final LocalDate incidentDate;
    private String status;

    public ItemReport(long reportId, String reportType, String category,
                      String description, String location, LocalDate incidentDate) {
        this.reportId = reportId;
        this.reportType = reportType;
        this.category = category;
        this.description = description;
        this.location = location;
        this.incidentDate = incidentDate;
        this.status = "OPEN";
    }
    public long getReportId() { return reportId; }
    public String getReportType() { return reportType; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public LocalDate getIncidentDate() { return incidentDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public boolean validate() {
        return ("LOST".equals(reportType) || "FOUND".equals(reportType))
            && category != null && !category.isBlank()
            && description != null && !description.isBlank();
    }
    @Override public String toString() {
        return "#" + reportId + " [" + reportType + "] " + category + " — "
            + description + " | " + location + " | " + status;
    }
}
