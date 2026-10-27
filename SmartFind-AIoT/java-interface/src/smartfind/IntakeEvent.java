package smartfind;

import java.time.Instant;

public class IntakeEvent {
    private final String eventId;
    private final String deviceId;
    private final long reportId;
    private final String eventType;
    private final Instant receivedAt;

    public IntakeEvent(String eventId, String deviceId, long reportId, String eventType) {
        this.eventId = eventId; this.deviceId = deviceId; this.reportId = reportId;
        this.eventType = eventType; this.receivedAt = Instant.now();
    }
    public String getEventId() { return eventId; }
    public String getDeviceId() { return deviceId; }
    public long getReportId() { return reportId; }
    public String getEventType() { return eventType; }
    public Instant getReceivedAt() { return receivedAt; }
    @Override public String toString() {
        return eventId + " | " + deviceId + " | report #" + reportId + " | " + eventType + " | " + receivedAt;
    }
}
