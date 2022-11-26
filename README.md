# Ứng dụng quản lý học sinh!

<img src="/RESOURCE/UTT logo 169.png" align="left"
width="200" hspace="10" vspace="10">

UTT SM là ứng dụng quản lý điểm cho học sinh trung học phổ thông. Ứng dụng tập trung vào quản lý thông tin của từng học sinh và điểm của các bạn ấy.

UTT SM có giao diện đơn giản, hiện đại, dễ dàng sử dụng!

Đây là ứng dụng tạo cho nhóm sinh viên đến từ UTT! 
  
# Contributors

# Authors
 - [@Hoàng Văn Giang](https://github.com/HVgiang86/student-management-utt)
 
 <a href="https://www.facebook.com/HVGiang86">Hoàng Giang</a>
<img alt="Facebook icon" height="20"
        src="https://cdn-icons-png.flaticon.com/512/124/124010.png"/>
<p align="left">
    
<a href="https://github.com/HVgiang86">HVGiang86</a>
<img alt="Facebook icon" height="20"
        src="https://cdn-icons-png.flaticon.com/512/25/25231.png"/>
<p align="left">
 
# Problem
**Mục tiêu**
Trong project này, chúng ta sẽ xây dựng một ứng dụng quản lý các học sinh của một trường THPT.
**Hình thức**
Cần xây dựng một ứng dụng Android có các tính năng đăng nhập, đăng xuất cho người quản trị, lưu trữ được các thông tin của học sinh, có khả năng hiển thị, CRUD (thêm, sửa, xoá, cập nhật) thông tin của các học sinh, có giao diện thân thiện, dễ sử dụng.

# Data & Database
## Cấu trúc dữ liệu lưu trữ
**Các thông tin của học sinh sẽ bao gồm:**
 - *Họ tên*
 - *Mã học sinh*
 - *Giới tính*
 - *Lớp*
 - *Khối*
 - *Ngày sinh*
 - *Quê quán*
 - *Bảng điểm: Toán, Văn, Anh, Sinh, Sử, Địa, Lý, Hoá*

**Người làm công tác quản trị thông tin sinh viên (hay người quản trị) như thầy cô phải được đăng nhập vào và quản lý các thông tin trên một cách dễ dàng. Do vậy, ta cần các tài khoản đăng nhập. Thông tin của các tài khoản sẽ bao gồm:**
 - *Username*
 - *Password*
 - *Gender*
 - *Email*
 - *Phone Number*
 - *Address*
## Cơ sở dữ liệu
Để đơn giản ta sử dụng cơ sở dữ liệu **Realm.io** để lưu trữ thông tin của học sinh. Cấu trúc bảng sẽ tương tự như các thuộc tính của lớp **Student**
Bên cạnh đó, để lưu trữ các tài khoản, ta đơn giản là sử dụng **Shared Preferences** với mật khẩu đã được mã hoá
## Application Architecture
Ứng dụng xây dựng với mô hình MVC (Model - Controller - View)
Trong đó phân tách:
- Model: Cơ sở dữ liệu, các model: **Student**, **Account** 
## Feature
Ứng dụng này cho phép bạn:

 - Tạo tài khoản quản trị
 - Đăng nhập
 - Xem danh sách học sinh
 - Thêm học sinh vào danh sách
 - Xoá học sinh từ danh sách
 - Sửa thông tin của một học sinh
 - Tự động tính điểm trung bình thông qua điểm nhập vào
 - Floating button để thêm học sinh
 - Context menu để sửa hoặc xoá học sinh
 - Đảm bảo độ mạnh mật khẩu thông qua ReGex
 - Mã hoá mật khẩu
 - Xây dựng với mô hình MVC

# Permission
Ứng dụng không đòi thêm bất kỳ quyền đặc biệt nào

## UML diagram
![UML diagram](https://i.imgur.com/Lxj6Dsh.png)
# Screenshot

<img src="https://i.imgur.com/fDw3bGP.jpeg" align="left"
width="200" hspace="10" vspace="10">
<img src="https://i.imgur.com/6Lv6y8x.jpeg" align="left"
width="200" hspace="10" vspace="10">
<img src="https://i.imgur.com/xWXJWVI.jpeg" align="left"
width="200" hspace="10" vspace="10">
<img src="https://i.imgur.com/cJ43S3e.jpeg" align="left"
width="200" hspace="10" vspace="10">
<img src="https://i.imgur.com/gGBu1mh.jpeg" align="left"
width="200" hspace="10" vspace="10">
<img src="https://i.imgur.com/Ufo1qUo.jpeg" align="left"
width="200" hspace="10" vspace="10">
<img src="https://i.imgur.com/JHPp1gw.jpeg" align="left"
width="200" hspace="10" vspace="10">
<img src="https://i.imgur.com/jmMx4Us.jpeg" align="left"
width="200" hspace="10" vspace="10">
<img src="https://i.imgur.com/oJnf4Xg.jpeg" align="left"
width="200" hspace="10" vspace="10">
