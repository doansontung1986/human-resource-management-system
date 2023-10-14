package logic;

public class HRAgentLogic {
    private static HRAgentLogic hrAgentLogic;

    private HRAgentLogic() {
    }

    public static HRAgentLogic getInstance() {
        if (hrAgentLogic == null) {
            hrAgentLogic = new HRAgentLogic();
        }
        return hrAgentLogic;
    }

    public void printHRAgentMenuLevel1() {
        System.out.println("------ HỆ THỐNG QUẢN LÝ NGUỒN NHÂN LỰC ------");
        System.out.println("1. Quản lý thông tin cá nhân nhân viên");
        System.out.println("2. Quản lý thông tin các phòng ban");
        System.out.println("3. Quản lý thời gian làm việc");
        System.out.println("4. Quản lý nghỉ phép");
        System.out.println("5. Quản lý lương");
        System.out.println("6. Đăng xuất");
        System.out.println("7. Thoát hệ thống");
    }

    public void printHRAgentMenuLevel2Sub1() {
        System.out.println("------ QUẢN LÝ THÔNG TIN CÁ NHÂN NHÂN VIÊN ------");
        System.out.println("1. Xem thông tin cá nhân nhân viên");
        System.out.println("2. Hiển thị toàn bộ nhân viên hiện có trong hệ thống");
        System.out.println("3. Lọc và tìm kiếm nhân viên theo từng bộ phận");
        System.out.println("4. Lọc và tìm kiếm các nhân viên theo tên");
        System.out.println("5. Tìm kiếm thông tin nhân viên theo số điện thoại");
        System.out.println("6. Tìm kiếm thông tin nhân viên theo số chứng minh nhân dân");
        System.out.println("7. Quay lại màn hình chính");
        System.out.println("8. Đăng xuất");
        System.out.println("9. Thoát hệ thống");
    }

    public void printHRAgentMenuLevel2Sub2() {
        System.out.println("------ QUẢN LÝ THÔNG TIN CÁC PHÒNG BAN ------");
        System.out.println("1. Hiển thị thông tin phòng ban");
        System.out.println("2. Hiển thị số lượng nhân viên mỗi phòng ban");
        System.out.println("3. Quay lại màn hình chính");
        System.out.println("4. Đăng xuất");
        System.out.println("5. Thoát hệ thống");
    }

    public void printHRAgentMenuLevel2Sub3() {
        System.out.println("------ QUẢN LÝ THỜI GIAN LÀM VIỆC ------");
        System.out.println("1. Hiển thị danh sách ngày công của nhân viên");
        System.out.println("2. Lọc và hiển thị danh sách ngày công của nhân viên có dưới số ngày công đã nhập");
        System.out.println("3. Sắp xếp và hiển thị danh sách ngày công của nhân viên theo thứ tự giảm dần");
        System.out.println("4. Quay lại màn hình chính");
        System.out.println("5. Đăng xuất");
        System.out.println("6. Thoát hệ thống");
    }

    public void printHRAgentMenuLevel2Sub4() {
        System.out.println("------ QUẢN LÝ NGHỈ PHÉP ------");
        System.out.println("1. Hiển thị danh nhân viên có đăng ký nghỉ phép");
        System.out.println("2. Duyệt đăng ký nghỉ phép");
        System.out.println("3. Quay lại màn hình chính");
        System.out.println("4. Đăng xuất");
        System.out.println("5. Thoát hệ thống");
    }

    public void printHRAgentMenuLevel2Sub5() {
        System.out.println("------ QUẢN LÝ LƯƠNG ------");
        System.out.println("1. Hiển thị danh sách bảng lương nhân viên");
        System.out.println("2. Sắp xếp và hiển thị danh sách bảng lương nhân viên theo thứ tự giảm dần");
        System.out.println("3. Quay lại màn hình chính");
        System.out.println("4. Đăng xuất");
        System.out.println("5. Thoát hệ thống");
    }
}
