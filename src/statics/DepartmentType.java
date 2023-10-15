package statics;

public enum DepartmentType {
    INFORMATION_TECHNOLOGY("Công nghệ thông tin"),
    ACCOUNTING("Kế toán"),
    OPERATION("Khối sản xuất"),
    SALE("Phòng kinh doanh"),
    MARKETING("Phòng marketing"),
    SUPPORT("Phòng hậu cần"),
    HR("Phòng hành chính"),
    STAFF("Phòng nguồn lực dự bị");

    public final String name;

    DepartmentType(String name) {
        this.name = name;
    }
}
