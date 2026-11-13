---
name: lam-slide-thuyet-trinh
description: Lập dàn ý và soạn nội dung từng slide cho bài thuyết trình bằng tiếng Việt, dùng được cho mọi môn học và mọi chủ đề (báo cáo đồ án, bảo vệ đề tài, thuyết trình nhóm, pitch ý tưởng). Hãy dùng skill này mỗi khi người dùng cần làm slide, soạn nội dung trình chiếu, chuẩn bị bài thuyết trình hoặc báo cáo trước lớp/hội đồng, kể cả khi họ không nhắc chữ "slide", ví dụ "mai em phải báo cáo đồ án trước lớp", "giúp em chuẩn bị bài nói 10 phút về X", "em cần dàn ý các trang để bảo vệ đề tài". KHÔNG dùng cho viết báo cáo/bài luận dạng văn bản, tóm tắt tài liệu, giải thích kiến thức, viết email, viết code, hay sửa lỗi trong file .pptx có sẵn.
---

# Làm slide thuyết trình

Tạo bộ slide gọn, đúng thời lượng, mỗi slide một ý, kèm lời nói cho người trình bày. Skill dùng chung cho mọi môn: không gắn với chủ đề cụ thể.

## Quy trình

1. **Hỏi thông tin** (một lần, gọn, bỏ qua mục đã biết):
   - **Đề tài** và môn học/mục đích (báo cáo môn, bảo vệ đồ án, pitch...).
   - **Thời lượng** nói (phút).
   - Người nghe là ai (bạn cùng lớp, giảng viên, hội đồng) và mức độ họ đã biết.
   - Nếu người dùng có tài liệu hoặc ý chính thì xin họ cung cấp.
   Thiếu thông tin mà người dùng muốn có bản nháp ngay: nêu giả định ở một dòng rồi làm tiếp.
2. **Tính số slide**: khoảng 1-2 phút cho một slide, không tính slide bìa và slide cảm ơn. Ví dụ 10 phút thì 6-8 slide nội dung.
3. **Lập dàn ý**: chọn khung phù hợp trong `assets/dan-y-mau.md`. Liệt kê tiêu đề từng slide dưới dạng một câu thông điệp ngắn. Hỏi người dùng duyệt dàn ý trước khi viết chi tiết; nếu họ bảo làm luôn thì viết luôn.
4. **Viết từng slide** theo mẫu trong `assets/dan-y-mau.md`: tiêu đề, 3-5 ý ngắn, gợi ý hình/biểu đồ, và lời nói cho người trình bày.
5. **Kiểm tra** với `references/quy-tac-trinh-bay.md`, sửa slide vi phạm trước khi gửi.

## Đầu ra

- Mặc định: in từng slide trong cuộc trò chuyện.
- Khi người dùng muốn file, hoặc công cụ ghi được file: lưu `slides.md` theo định dạng Marp (đầu file có `marp: true`, các slide ngăn bằng `---`) để xuất sang PPTX/PDF bằng tiện ích Marp trong VS Code. Nói rõ cách xuất ở một dòng.

## Nguyên tắc

- **Không bịa**: số liệu, trích dẫn, tên nguồn phải do người dùng cung cấp hoặc đánh dấu `[cần kiểm chứng]`.
- Giữ giọng văn theo ngữ cảnh: bài trước lớp thì giản dị; bài bảo vệ hội đồng thì trang trọng hơn.
- Không nhồi chữ vào slide; phần giải thích dài đưa vào lời nói.
- Chủ đề lạ: dựa vào thông tin người dùng đưa, đừng giả vờ chuyên gia; ghi rõ chỗ cần họ bổ sung.
