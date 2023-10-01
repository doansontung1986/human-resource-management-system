package entity;

import statics.Role;
import utility.ScannerUtility;
import utility.ValidateUserInput;

import java.io.Serializable;

public class Account implements Inputable, Serializable {
    private static final long serialVersionUID = -6500665823330706018L;
    private String userName;
    private String password;
    private String repeatPassword;
    private Role role;

    public Account() {
    }

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public void inputInfo() {
        System.out.println("Nhập tài khoản có độ dài 6 - 20 ký tự (chữ hoa, chữ thường, chữ số): ");
        this.setUserName(inputValidUserName());
        System.out.println("Nhập mật khẩu có độ dài 6 - 15 ký tự (chữ hoa, chữ thường, chữ số hoặc ký tự đặc biệt): ");
        this.setPassword(inputValidPassword());
        System.out.println("Xác nhận mật khẩu: ");
        String repeatPassword;
        do {
            repeatPassword = inputValidPassword();
            if (repeatPassword.equals(this.password)) {
                break;
            }
            System.out.println("mật khẩu xác nhân phải giống mật khẩu đã nhập");
        } while (true);
        this.setRepeatPassword(repeatPassword);
    }

    public void displayAccountInfo() {
        System.out.printf("%-21s | %-16s | %-16s |\n", this.userName, this.role, this.password);
    }

    private String inputValidUserName() {
        String userName;
        do {
            userName = ScannerUtility.inputStringInRange(6, 20);

            if (ValidateUserInput.checkValidUserName(userName)) {
                break;
            }

            System.out.print("Tên tài khoản phải có độ dài 6 - 20 ký tự. Nhập lại tên tài khoản: ");
        } while (true);

        return userName;
    }

    private String inputValidPassword() {
        String password;
        do {
            password = ScannerUtility.inputStringInRange(6, 15);

            if (ValidateUserInput.checkValidPassword(password)) {
                break;
            }

            System.out.print("Mật khẩu phải có độ dài 6 - 15 ký tự (chữ hoa, chữ thường hoặc ký tự đặc biệt). Nhập lại mật khẩu: ");
        } while (true);

        return password;
    }
}
