---
marp: true
theme: default
paginate: true
header: "Kiến trúc Microservices | Thuyết trình 10 phút"
footer: "Báo cáo môn học"
---

# Kiến trúc Microservices
## Từ kiến trúc nguyên khối đến hệ thống phân tán hiện đại
**Người trình bày:** Sinh viên thực hiện  
**Thời lượng:** 10 phút

---

## 1. Monolith bộc lộ giới hạn khi hệ thống mở rộng
- Toàn bộ tính năng gói trong một codebase
- Một module lỗi khiến toàn hệ thống sập
- Khó mở rộng linh hoạt từng tính năng
- Triển khai chậm do phụ thuộc chéo lớn

<!-- Lời nói (khoảng 1 phút):
Xin chào thầy cô và các bạn. Đa số các dự án khởi đầu đều theo mô hình Monolith vì dễ xây dựng ban đầu. Tuy nhiên, khi sản phẩm và đội ngũ mở rộng, việc cập nhật một dòng code nhỏ cũng đòi hỏi build và deploy lại toàn bộ khối ứng dụng khổng lồ. Điều này tạo ra nút thắt cổ chai lớn trong vận hành, thôi thúc sự ra đời của một hướng tiếp cận mới. -->

---

## 2. Microservices chia nhỏ ứng dụng theo miền nghiệp vụ
- Tách nhỏ thành nhiều dịch vụ độc lập
- Mỗi dịch vụ phụ trách một nghiệp vụ riêng
- Có thể dùng công nghệ và ngôn ngữ khác nhau
- Phát triển và đóng gói triển khai độc lập

<!-- Lời nói (khoảng 1 phút 15 giây):
Nối tiếp vấn đề của Monolith, kiến trúc Microservices giải quyết triệt để bằng cách chia nhỏ hệ thống theo từng miền nghiệp vụ cụ thể, ví dụ như dịch vụ thanh toán, người dùng hay đơn hàng. Mỗi dịch vụ là một tiến trình riêng biệt, giúp các đội phát triển có thể tự chủ hoàn toàn về công nghệ lẫn chu kỳ release. Vậy các dịch vụ này lưu trữ dữ liệu thế nào để không dẫm chân lên nhau? -->

---

## 3. Mỗi dịch vụ tự quản lý cơ sở dữ liệu riêng
- Áp dụng nguyên tắc "Database per Service"
- Tránh việc chia sẻ trực tiếp database chung
- Đảm bảo tính đóng gói dữ liệu tuyệt đối
- Dữ liệu trao đổi qua API có kiểm soát

<!-- Lời nói (khoảng 1 phút):
Như chúng ta thấy trên sơ đồ, nguyên tắc sống còn của Microservices là không bao giờ dùng chung cơ sở dữ liệu. Dịch vụ đặt hàng không được truy vấn thẳng vào bảng khách hàng mà bắt buộc phải gọi API. Điều này giúp ngăn chặn sự phụ thuộc ngầm và giữ cho cấu trúc dữ liệu của từng dịch vụ luôn độc lập. Khi đã độc lập rồi, các dịch vụ sẽ liên lạc với nhau qua những con đường nào? -->

---

## 4. Giao tiếp qua API Gateway và Event-Driven Messaging
- API Gateway làm điểm tiếp nhận duy nhất
- Giao tiếp đồng bộ qua RESTful API hoặc gRPC
- Giao tiếp bất đồng bộ qua Message Broker (Kafka/RabbitMQ)
- Giúp giảm độ trễ và tránh phụ thuộc chặt

<!-- Lời nói (khoảng 1 phút 15 giây):
Để kết nối các thành phần rời rạc này, khách hàng bên ngoài sẽ đi qua một API Gateway đóng vai trò điều phối và bảo mật. Ở bên trong, các dịch vụ liên lạc đồng bộ bằng gRPC cho tác vụ nhanh, hoặc bắn event bất đồng bộ qua Kafka để xử lý ngầm. Nhờ kiến trúc này, hệ thống đem lại những lợi ích vô cùng vượt trội. -->

---

## 5. Đẩy mạnh tốc độ phát hành và khả năng chịu lỗi
- Cho phép release tính năng liên tục hàng ngày
- Lỗi dịch vụ này không làm sập dịch vụ khác
- Tự động scale đúng thành phần quá tải
- Tối ưu chi phí hạ tầng máy chủ

<!-- Lời nói (khoảng 1 phút 15 giây):
Lợi thế rõ nhất là tốc độ và sự linh hoạt: nếu dịch vụ gợi ý sản phẩm bị nghẽn, các bạn chỉ cần scale riêng dịch vụ đó trên Kubernetes mà không tốn tài nguyên cho toàn bộ hệ thống. Đặc biệt, nếu một dịch vụ gặp sự cố, hệ thống vẫn duy trì hoạt động cốt lõi cho người dùng. Tuy nhiên, kiến trúc phân tán không chỉ toàn màu hồng. -->

---

## 6. Đánh đổi bằng độ phức tạp quản lý và dữ liệu
- Mất tính nhất quán dữ liệu tức thời (ACID)
- Phải áp dụng tính nhất quán sau cùng (Eventual Consistency)
- Khó debug và theo dõi lỗi phân tán
- Đòi hỏi năng lực DevOps và CI/CD cao

<!-- Lời nói (khoảng 1 phút 15 giây):
Thách thức lớn nhất khi bước sang Microservices là chúng ta mất đi transaction ACID quen thuộc giữa các bảng. Thay vào đó, lập trình viên phải chấp nhận mô hình nhất quán sau cùng với các pattern phức tạp như Saga. Việc tracing lỗi qua hàng chục service độc lập cũng đòi hỏi hệ thống giám sát tập trung hiện đại. Vậy khi nào chúng ta mới nên áp dụng? -->

---

## 7. Microservices không phải "viên đạn bạc" cho mọi dự án
- Dự án nhỏ nên bắt đầu từ Monolith sạch
- Chuyển đổi khi hệ sinh thái đủ lớn
- Đội ngũ cần đủ nhân lực vận hành hạ tầng
- Độ phức tạp bài toán phải đủ lớn

<!-- Lời nói (khoảng 1 phút):
Nhiều chuyên gia công nghệ như Martin Fowler luôn nhấn mạnh: đừng vội làm Microservices nếu ứng dụng của bạn chưa thực sự cần. Với các startup hoặc dự án giai đoạn đầu, kiến trúc nguyên khối được thiết kế tốt (Modular Monolith) sẽ giúp tiết kiệm chi phí và ra mắt tính năng nhanh hơn rất nhiều so với gánh nặng vận hành phân tán. -->

---

## 8. Tóm tắt 3 bài học cốt lõi cần nhớ
- Microservices giải quyết bài toán quy mô và độc lập
- Đánh đổi tính tiện lợi lấy năng lực mở rộng
- Luôn cân nhắc giữa bài toán kinh doanh và chi phí

<!-- Lời nói (khoảng 45 giây):
Tóm lại bài thuyết trình hôm nay, em muốn gửi gắm ba thông điệp chính: Microservices là công cụ đắc lực để mở rộng quy mô tổ chức và hệ thống; nó đòi hỏi sự đánh đổi nghiêm túc về độ phức tạp kỹ thuật; và quan trọng nhất, kiến trúc tốt là kiến trúc giải quyết đúng bài toán hiện tại chứ không phải kiến trúc thời thượng nhất. -->

---

## 9. Cảm ơn và Hỏi đáp (Q&A)
- Tài liệu tham khảo: *Building Microservices* (Sam Newman)
- Bài viết kiến trúc: *martinfowler.com/microservices*
- Sẵn sàng tiếp nhận câu hỏi thảo luận!

<!-- Lời nói (khoảng 30 giây):
Em xin chân thành cảm ơn thầy cô và các bạn đã chú ý lắng nghe. Sau đây em xin mời thầy cô và các bạn đặt câu hỏi hoặc cùng đóng góp ý kiến để bài thảo luận thêm sâu sắc ạ! -->

