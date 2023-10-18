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
        System.out.println("1. Thông tin tài khoản");
        System.out.println("2. Thông tin cá nhân");
        System.out.println("3. Thông tin ngày phép");
        System.out.println("4. Thông tin ngày công");
        System.out.println("5. Thông tin lương");
        System.out.println("6. Yêu cầu cài đặt lại mật khẩu");
        System.out.println("7. Đăng xuất");
        System.out.println("8. Thoát hệ thống");
    }

    public void printStaffMenuLevel2Sub1() {
        System.out.println("------ THÔNG TIN TÀI KHOẢN ------");
        System.out.println("1. Đổi mật khẩu tài khoản");
        System.out.println("2. Quay lại màn hình chính");
        System.out.println("3. Đăng xuất");
        System.out.println("4. Thoát hệ thống");
    }

    public void printStaffMenuLevel2Sub2() {
        System.out.println("------ THÔNG TIN CÁ NHÂN ------");
        System.out.println("1. Cập nhật thông tin cá nhân");
        System.out.println("2. Xem thông tin cá nhân nhân viên");
        System.out.println("3. Quay lại màn hình chính");
        System.out.println("4. Đăng xuất");
        System.out.println("5. Thoát hệ thống");
    }

    public void printStaffMenuLevel2Sub3() {
        System.out.println("------ THÔNG TIN NGÀY PHÉP ------");
        System.out.println("1. Đăng ký nghỉ phép");
        System.out.println("2. Xem thông tin ngày phép");
        System.out.println("3. Quay lại màn hình chính");
        System.out.println("4. Đăng xuất");
        System.out.println("5. Thoát hệ thống");
    }

    public void printStaffMenuLevel2Sub4() {
        System.out.println("------ THÔNG TIN NGÀY CÔNG ------");
        System.out.println("1. Nhập số ngày công");
        System.out.println("2. Hiển thị số ngày công");
        System.out.println("3. Quay lại màn hình chính");
        System.out.println("4. Đăng xuất");
        System.out.println("5. Thoát hệ thống");
    }

    public void printStaffMenuLevel2Sub5() {
        System.out.println("------ THÔNG TIN LƯƠNG ------");
        System.out.println("1. Hiển thị bảng lương nhân viên");
        System.out.println("2. Quay lại màn hình chính");
        System.out.println("3. Đăng xuất");
        System.out.println("4. Thoát hệ thống");
    }
}
