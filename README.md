# HỆ THỐNG QUẢN LÝ NGUỒN NHÂN LỰC

## Giới thiệu

Do nhu cầu quản lý nhân sự tham gia vào công ty, nghỉ việc, tính lương nhân viên. Phần mềm quản lý nguồn nhân lực đáp
ứng những yêu cầu trên bằng việc số hóa các hoạt động giúp cho việc quản lý thông tin nhân viên theo từng bộ phận trong
công ty và tính toán lương nhân viên hiệu quả.

## Các chức năng của phần mềm

1. **Màn hình chính**

   ![main-menu](ui/main.png)


2. **Quản lý tài khoản hệ thống**
    - Đổi mật khẩu
    - Yêu cầu đổi mật khẩu
    - Xem danh sách tài khoản bị khóa
    - Gỡ khóa tài khoản

    ![account-menu](ui/account-menu.png)

3. **Quản lý thông tin cá nhân nhân viên**
    - Thêm nhân viên vào hệ thống
    - Cập nhật thông tin nhân viên
    - Xem thông tin cá nhân nhân viên
    - Hiển thị toàn bộ nhân viên hiện có trong hệ thống
    - Lọc và tìm kiếm các nhân viên theo tên, theo mã nhân viên hoặc số điện thoại

   ![account-menu](ui/user-menu.png)


4. **Quản lý thông tin các phòng ban**
    - Hiển thị thông tin phòng ban
    - Lọc và tìm kiếm nhân viên theo từng phòng ban

   ![account-menu](ui/department-menu.png)

5. **Quản lý thời gian làm việc**
    - Nhân viên nhập thông tin ngày công
    - Hiển thị danh sách ngày công của nhân viên
    - Tìm kiếm nhân viên theo tên, số điện thoại hoặc CMND

   ![account-menu](ui/working-day-menu.png)

6. **Quản lý nghỉ phép**
    - Nhân viên nhập ngày nghỉ phép
    - Xem thông tin đăng ký nghỉ phép
    - Duyệt đăng ký nghỉ phép

   ![account-menu](ui/leave-menu.png)

7. **Quản lý lương**
    - Xem thông tin lương cá nhân nhân viên
    - Hiển thị danh sách bảng lương nhân viên

   ![account-menu](ui/salary-menu.png)

8. **Đăng xuất khỏi hệ thống**
9. **Thoát chương trình**

### Use Case diagrams:

#### Use Case 1
![Use case 1](diagrams/usecase_diagrams/usecase_diagram_account_department_salary.jpg)

#### Use Case 2
![Use case 2](diagrams/usecase_diagrams/usecase_diagram_user_timeoff_workday.jpg)

### Class diagram:

#### Class diagram 1
![Class diagram_1](diagrams/class_diagrams/class_diagram_1.jpg)

#### Class diagram 2
![Class diagram_2](diagrams/class_diagrams/class_diagram_2.jpg)

#### Class diagram 3
![Class diagram_3](diagrams/class_diagrams/class_diagram_3.jpg)

### Sequence diagrams:

#### Account Management Flow

![Sequence diagram 1](diagrams/sequence_diagrams/account_management_flow.jpg)

#### Authenticate Management Flow

![Sequence diagram 2](diagrams/sequence_diagrams/authenticate_management_flow.jpg)

#### User Management Flow

![Sequence diagram 3](diagrams/sequence_diagrams/user_management_flow.jpg)

## Demo link
+ Youtube: https://www.youtube.com/watch?v=rsedhS_BGbo
+ Application info: https://docs.google.com/document/d/1y5ZdwpHFDiRvCgTkhYVYZ5Ho0sxRhhcSLrphcRqaBQY/edit?usp=sharing

## Hướng phát triển trong tương lai cho ứng dụng

1. Chuyển từ lưu trữ file sang lưu trữ trên cơ ở dữ liệu cục bộ
2. Chuyển từ Console application sang web application

## Kết quả thu được từ môn Java Core

1. Hiểu kiến thức nền tảng của Java
    + Các khai báo biến và phân loại các loại biến trong Java
    + Các biểu thức, toán tử, hằng số trong Java
    + Các biểu thức điều kiện, lặp.
    + Cấu trúc dữ liệu nền tảng: Mảng 1 chiều, mảng 2 chiều
    + Hiểu OOP và 4 tính chất của OOP
    + Ngoại lệ (checked exception và uncheck exception)
    + String, StringBuilder, StringBuffer và các phương thức
    + Java collections
2. Hiểu và áp dụng được cách xây dựng các ứng dụng Windows Console với OOP 