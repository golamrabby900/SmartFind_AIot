package smartfind;

public class CollectionPoint {
    private final long id;
    private final String name;
    private final String deviceId;
    private boolean active;

    public CollectionPoint(long id, String name, String deviceId, boolean active) {
        this.id = id; this.name = name; this.deviceId = deviceId; this.active = active;
    }
    public long getId() { return id; }
    public String getName() { return name; }
    public String getDeviceId() { return deviceId; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
