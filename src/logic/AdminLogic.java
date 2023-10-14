package logic;

public class AdminLogic {
    private static AdminLogic adminLogic;

    private AdminLogic() {
    }

    public static AdminLogic getInstance() {
        if (adminLogic == null) {
            adminLogic = new AdminLogic();
        }
        return adminLogic;
    }

    public void printAdminMenuLevel1() {
        System.out.println("------ HỆ THỐNG QUẢN LÝ NGUỒN NHÂN LỰC ------");
        System.out.println("1. Quản lý tài khoản hệ thống");
        System.out.println("2. Quản lý thông tin cá nhân nhân viên");
        System.out.println("3. Quản lý thông tin các phòng ban");
        System.out.println("4. Đăng xuất");
        System.out.println("5. Thoát hệ thống");
    }

    public void printAdminMenuLevel2Sub1() {
        System.out.println("------ QUẢN LÝ TÀI KHOẢN HỆ THỐNG ------");
        System.out.println("1. Cài đặt lại mật khẩu");
        System.out.println("2. Xem danh sách tài khoản yêu cầu đổi mật khẩu");
        System.out.println("3. Xem danh sách tài khoản bị khóa");
        System.out.println("4. Gỡ khóa tài khoản");
        System.out.println("5. Quay lại màn hình chính");
        System.out.println("6. Đăng xuất");
        System.out.println("7. Thoát hệ thống");
    }

    public void printAdminMenuLevel2Sub2() {
        System.out.println("------ QUẢN LÝ THÔNG TIN CÁ NHÂN NHÂN VIÊN ------");
        System.out.println("1. Thêm nhân viên vào hệ thống");
        System.out.println("2. Cập nhật thông tin nhân viên");
        System.out.println("3. Xem thông tin cá nhân nhân viên");
        System.out.println("4. Hiển thị toàn bộ nhân viên hiện có trong hệ thống");
        System.out.println("5. Lọc và tìm kiếm nhân viên theo từng bộ phận");
        System.out.println("6. Lọc và tìm kiếm các nhân viên theo tên");
        System.out.println("7. Tìm kiếm thông tin nhân viên theo số điện thoại");
        System.out.println("8. Tìm kiếm thông tin nhân viên theo số chứng minh nhân dân");
        System.out.println("9. Quay lại màn hình chính");
        System.out.println("10. Đăng xuất");
        System.out.println("11. Thoát hệ thống");
    }

    public void printAdminMenuLevel2Sub3() {
        System.out.println("------ QUẢN LÝ THÔNG TIN CÁC PHÒNG BAN ------");
        System.out.println("1. Hiển thị thông tin phòng ban");
        System.out.println("2. Hiển thị số lượng nhân viên mỗi phòng ban");
        System.out.println("3. Quay lại màn hình chính");
        System.out.println("4. Đăng xuất");
        System.out.println("5. Thoát hệ thống");
    }
}
