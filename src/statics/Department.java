package statics;

public enum Department {
    INFORMATION_TECHNOLOGY("Công nghệ thông tin"),
    ACCOUNTING("Kế toán"),
    OPERATION("Khối sản xuất"),
    SALE("Phòng kinh doanh"),
    MARKETING("Phòng marketing"),
    SUPPORT("Phòng hậu cần"),
    HR("Phòng hành chính");

    public final String departmentType;

    Department(String departmentType) {
        this.departmentType = departmentType;
    }
}
