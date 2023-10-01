package statics;

public enum Role {
    ADMIN("Quản trị viên"),
    HRAGENT("Nhân viên nhân sự"),
    STAFF("Nhân viên");

    public final String role;

    Role(String role) {
        this.role = role;
    }
}
