package smartfind;

public class User {
    private final long userId;
    private final String displayName;
    private final String role;

    public User(long userId, String displayName, String role) {
        this.userId = userId;
        this.displayName = displayName;
        this.role = role;
    }
    public long getUserId() { return userId; }
    public String getDisplayName() { return displayName; }
    public String getRole() { return role; }
    public boolean canVerifyClaims() { return "STAFF".equals(role) || "ADMIN".equals(role); }
    @Override public String toString() { return displayName + " (" + role + ")"; }
}
