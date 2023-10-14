package logic;

public class StaffLogic {
    private static StaffLogic staffLogic;

    private StaffLogic() {
    }

    public static StaffLogic getInstance() {
        if (staffLogic == null) {
            staffLogic = new StaffLogic();
        }
        return staffLogic;
    }

    public void printStaffMenuLevel1() {
        System.out.println("------ HỆ THỐNG QUẢN LÝ NGUỒN NHÂN LỰC ------");
        System.out.println("1. Xem thông tin cá nhân nhân viên");
        System.out.println("2. Đăng ký nghỉ phép");
        System.out.println("3. Hiển thị bảng lương nhân viên");
        System.out.println("4. Hiển thị danh sách ngày công của nhân viên");
        System.out.println("5. Quay lại màn hình chính");
        System.out.println("6. Đăng xuất");
        System.out.println("7. Thoát hệ thống");
    }
}
