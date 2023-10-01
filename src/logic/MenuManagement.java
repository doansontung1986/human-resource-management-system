package logic;

import entity.Account;
import entity.Person;
import statics.Role;
import utility.PrintMessageUtility;
import utility.ScannerUtility;

import java.util.InputMismatchException;

public class MenuManagement {
    private final AccountManagement accountManagement = new AccountManagement();
    private final AuthenticateManagement authenticateManagement = new AuthenticateManagement(accountManagement);
    private final UserManagement userManagement = new UserManagement(accountManagement);

    public void run() {
        while (true) {
            printLoginMenu();
            int loginChoice = handleLoginMenuChoice();
            switch (loginChoice) {
                case 1:
                    Account account = authenticateManagement.loginToSystem(accountManagement.getAccountList());
                    Person user = userManagement.getUserInfoByAccount(account);
                    if (user != null) {
                        Role role = account.getRole();
                        switch (role) {
                            case ADMIN:

                                break;
                            case HRAGENT:
                                break;
                            case STAFF:
                                break;
                            default:
                                throw new IllegalStateException("Không có loại tài khoản " + role + " trong hệ thống");
                        }
                    } else {
                        System.out.println("Người dùng không tồn tại");
                    }
                    break;
                case 2:
                    System.out.println(accountManagement.getAccountList().get(0).getUserName() + "-" + accountManagement.getAccountList().get(0).getPassword());
                    break;
                case 3:
                    System.out.println("Hẹn gặp lại");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
                    break;
            }

        }
    }

    private int chooseFunction() {
        System.out.print("Xin mời lựa chọn chức năng (1-9): ");
        int functionChoice;
        do {
            try {
                functionChoice = ScannerUtility.inputValidInteger();
                if (functionChoice > 0 && functionChoice < 10) {
                    break;
                }
            } catch (InputMismatchException e) {
                PrintMessageUtility.printErrorMessageForInteger();
            }

            System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại");
        } while (true);

        return functionChoice;
    }

    private int chooseSubmenuChoice() {
        int choice;

        do {
            System.out.print("Xin mời lựa chọn chức năng (1 hoặc 2): ");

            try {
                choice = ScannerUtility.inputValidInteger();

                if (choice > 0 && choice < 3) {
                    break;
                }
            } catch (InputMismatchException e) {
                PrintMessageUtility.printErrorMessageForInteger();
            }

            System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại");
        } while (true);

        return choice;
    }

    private void printLoginMenu() {
        System.out.println("------ HỆ THỐNG QUẢN LÝ NGUỒN NHÂN LỰC ------");
        System.out.println("1. Đăng nhập vào hệ thống");
        System.out.println("2. Yêu cầu đổi mật khẩu");
        System.out.println("3. Thoát hệ thống");
    }

    private void printAdminMenu() {
        System.out.println("------ HỆ THỐNG QUẢN LÝ NGUỒN NHÂN LỰC ------");
        System.out.println("1. Quản lý tài khoản hệ thống");
        System.out.println("2. Quản lý thông tin cá nhân nhân viên");
        System.out.println("3. Quản lý thông tin các phòng ban");
        System.out.println("7. Đăng xuất");
        System.out.println("8. Thoát hệ thống");
    }

    private void printHRAgentMenu() {
        System.out.println("------ HỆ THỐNG QUẢN LÝ NGUỒN NHÂN LỰC ------");
        System.out.println("1. Quản lý tài khoản hệ thống");
        System.out.println("2. Quản lý thông tin cá nhân nhân viên");
        System.out.println("3. Quản lý thông tin các phòng ban");
        System.out.println("4. Quản lý thời gian làm việc");
        System.out.println("5. Quản lý nghỉ phép");
        System.out.println("6. Quản lý lương");
        System.out.println("7. Đăng xuất");
        System.out.println("8. Thoát hệ thống");
    }

    private void printStaffMenu() {
        System.out.println("------ HỆ THỐNG QUẢN LÝ NGUỒN NHÂN LỰC ------");
        System.out.println("1. Quản lý tài khoản hệ thống");
        System.out.println("2. Quản lý thông tin cá nhân nhân viên");
        System.out.println("3. Quản lý thông tin các phòng ban");
        System.out.println("4. Quản lý thời gian làm việc");
        System.out.println("5. Quản lý nghỉ phép");
        System.out.println("6. Quản lý lương");
        System.out.println("7. Đăng xuất");
        System.out.println("8. Thoát hệ thống");
    }

    private int handleLoginMenuChoice() {
        int choice;

        do {
            System.out.print("Xin mời lựa chọn chức năng: ");

            try {
                choice = ScannerUtility.inputValidNumberInRange(1, 3);
                break;
            } catch (InputMismatchException e) {
                PrintMessageUtility.printErrorMessageForInteger();
            }

            System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại");
        } while (true);

        return choice;
    }

    private void printMenu() {
        System.out.println("------PHẦN MỀM QUẢN LÝ HÀNG HÓA");
        System.out.println("1. Nhập danh sách khách hàng mới");
        System.out.println("2. In ra danh sách khách hàng đã có");
        System.out.println("3. Nhập danh sách dịch vụ mới");
        System.out.println("4. In ra danh sách dịch vụ đã có");
        System.out.println("5. Nhập hóa đơn cho mỗi khách hàng");
        System.out.println("6. In danh sách hóa đơn");
        System.out.println("7. Sắp xếp danh sách hóa đơn");
        System.out.println("8. Lập bảng kê số tiền phải trả cho mỗi khách hàng.");
        System.out.println("9. Thoát");
    }

    private void printSubmenu() {
        System.out.println("1. Theo tên nhân viên");
        System.out.println("2. Theo số lượng sử dụng (giảm dần)");
    }
}
