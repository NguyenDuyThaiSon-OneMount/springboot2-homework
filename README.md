# springboot2-homework1- Nguyễn Duy Thái Sơn

Thưa thầy,

Đây là bài nộp của em, Nguyễn Duy Thái Sơn, cho bài tập về nhà đầu tiên lớp SpringBoot2 của khóa Fresh Geeks 3 tại công ty One Mount

Các tính năng em đã làm được: Tất cả 8 phần đã được giao

## Screenshot của các tính năng

### Tính năng 1: Sinh ra 1000 đầu sách

Sau khi sinh xong 1000 đầu sách thì hệ thống sẽ trả lại trạng thái thành công. 

![Function 1 image](/assets/function_1.png)


### Tính năng 2: Liệt kê các đầu sách sắp xếp theo tiêu chí title từ A-Z

Hệ thống sẽ trả về danh sách toàn bộ đầu sách, đã được sắp xếp theo tên đầu sách từ A-Z.

![Function 2 image](/assets/function_2.png)


### Tính năng 3: Tìm đầu sách có title chứa keyword nào đó

Hệ thống sẽ trả về danh sách toàn bộ đầu sách có chứa keyword trong tên đầu sách

![Function 3 image](/assets/function_3.png)

### Tính năng 4: Liệt kê các đầu sách đang hết

Hệ thống sẽ trả về danh sách toàn bộ sách có chứa keyword trong tên đầu sách

![Function 4 image](/assets/function_4.png)

### Tính năng 5 và 6: Mua sách, nếu đơn hàng mua lớn thì thực hiện event nhập lại sách

Khi thực hiện lệnh mua sách, nếu số lượng sách tồn còn đủ thì trừ số lượng sách trong kho và trả lại tin nhắn thành công

![Function 5 image](/assets/function_5.png)

Nếu số lượng sách **không đủ** thì hệ thống sẽ báo thất bại. 

![Function 5_2 image](/assets/function_5_2.png)

Nếu số lượng sách mua **lớn hơn 100** thì hệ thống sẽ trả lại tin nhắn như sau: *Đơn hàng của bạn quá lớn. Chúng tôi sẽ nhập <lượng mua> sách với id <id sách> trong thời gian sớm nhất. Thực ra, trong lúc chúng ta đang nói chuyện thì hàng đã về rồi. Bạn có thể thử mua hàng với số lượng bé hơn 100. Lần này chắc chắn sẽ thành công.*

![Function 5_3 image](/assets/function_5_3.png)

Và nếu thử mua lại 99 sách lần nữa thì sẽ thành công vì lượng sách vừa nhập lại ít nhất là 100.

![Function 5_4 image](/assets/function_5_4.png)

Tất nhiên, nếu sách không tồn tại thì hệ thống sẽ báo không tìm thấy sách và không thực hiện thao tác gì cả.

![Function 5_5 image](/assets/function_5_5.png)

### Tính năng 7 và 8: Đặt lệnh nhập lại sách sắp hết hàng mỗi phút & Dùng Spring AOP để benchmark thời gian xử lý thời gian thực hiện mỗi lệnh REST

Đây là phần implementation của việc nhập lại sách từng phút:

![Function 7 image](/assets/function_7.png)

Còn đây là phần hiển thị logging đã thực hiện nhập lại sách và thời gian xử lý các request:

![Function 7_2 image](/assets/function_7_2.png)

Để thuận lợi cho việc chấm bài, Collection Postman của em **đã được đính kèm trong /postman**. 

## Em cảm ơn thầy đã chấm bài ạ.
