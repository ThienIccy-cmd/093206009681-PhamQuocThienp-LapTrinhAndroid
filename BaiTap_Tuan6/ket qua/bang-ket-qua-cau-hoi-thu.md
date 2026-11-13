# Bảng kết quả câu hỏi thử - skill `lam-slide-thuyet-trinh`

- **Agent:** Antigravity IDE (model Gemini 3.8 Flash, mức Medium)
- **Cách chạy:** mỗi câu hỏi chạy trong một phiên chat mới, gõ nguyên văn, không dùng dấu `/`.
- **Dấu hiệu "có gọi skill":** agent đọc `SKILL.md` và các file `dan-y-mau.md`, `quy-tac-trinh-bay.md`, hỏi lại đề tài/thời lượng, đưa dàn ý có tiêu đề thông điệp, hoặc viết slide theo mẫu của skill.

## 5 câu NÊN gọi skill

| # | Câu hỏi | Mong đợi | Agent có gọi skill? | Bằng chứng / ghi chú | Ảnh |
|---|---|---|---|---|---|
| 1 | Làm slide thuyết trình về kiến trúc microservices, nói 10 phút | Gọi | **Có** | Đọc `SKILL.md`, `dan-y-mau.md`, `quy-tac-trinh-bay.md`; nêu giả định; đưa dàn ý 10 slide; tạo `slides.md` | `5 câu NÊN/câu hỏi 1/` |
| 2 | Mai em phải báo cáo đồ án môn Mạng máy tính trước lớp, giúp em dàn ý các trang | Gọi | **Có** | Đọc 3 file skill; dàn ý 9 slide có "Thông điệp"; hỏi lại tên đề tài, thời lượng, có xuất `slides.md` (Marp) không | `5 câu NÊN/câu hỏi 2/` |
| 3 | Chuẩn bị giúp em bài thuyết trình 15 phút về an toàn thông tin cho nhóm | Gọi | **Có** | Đọc 3 file skill; tính 8-10 slide nội dung (khoảng 1,5 phút/slide); nêu giả định; dàn ý 10 slide | `5 câu NÊN/câu hỏi 3/` |
| 4 | Em cần soạn nội dung để bảo vệ đề tài tốt nghiệp, thời gian 20 phút | Gọi | **Có (gián tiếp)** | Mục "Worked for" bị thu gọn nên không thấy dòng đọc file, nhưng nội dung khớp khung bảo vệ đề tài của skill: 14-16 slide, phân bổ thời gian, dàn ý 15 slide, hỏi thêm thông tin | `5 câu NÊN/câu hỏi 4/` |
| 5 | Giúp em soạn nội dung từng trang cho buổi pitch ý tưởng khởi nghiệp | Gọi | **Có** | Đọc 3 file skill; dùng khung pitch 7 slide, 5-7 phút | `5 câu NÊN/câu hỏi 5/` |

**Kết quả nhóm "nên": 5/5 gọi đúng.**

## 5 câu KHÔNG nên gọi skill

| # | Câu hỏi | Mong đợi | Agent có gọi skill? | Bằng chứng / ghi chú | Ảnh |
|---|---|---|---|---|---|
| 6 | Viết báo cáo 5 trang về kiến trúc microservices | Không gọi | **Không** | Chỉ liệt kê file trong workspace rồi tạo file báo cáo văn bản `.md`; không đọc file của skill | `5 câu không nên/câu 1/` |
| 7 | Tóm tắt giúp em chương 3 của giáo trình chủ nghĩa Mác - Lênin | Không gọi | **Không** | Trả lời tóm tắt nội dung chương, không hỏi về slide | `5 câu không nên/câu 2/` |
| 8 | Em muốn thuyết trình tự tin hơn, có mẹo gì không? | Không gọi | **Không** | Câu gần nghĩa (có chữ "thuyết trình"). Agent đưa mẹo kỹ năng nói, không làm dàn ý slide | `5 câu không nên/câu 3/` |
| 9 | Slide trước em làm bị lệch phông chữ, chỉ em cách đổi phông cho toàn bộ trong PowerPoint | Không gọi | **Không** | Câu gần nghĩa (có chữ "slide", "PowerPoint"). Agent hướng dẫn thao tác Replace Fonts và Slide Master | `5 câu không nên/câu 4/` |
| 10 | Làm báo cáo thực tập 20 trang về hệ thống quản lý kho | Không gọi | **Không** | Tạo file báo cáo văn bản `.md`; không đọc file của skill | `5 câu không nên/câu 5/` |

**Kết quả nhóm "không nên": 5/5 không gọi, đúng mong đợi.**

## Tổng kết

- **Đúng: 10/10.**
- Hai câu gần nghĩa (số 8 và 9) dễ bị gọi nhầm vì có chữ "thuyết trình", "slide", "PowerPoint" nhưng agent vẫn không gọi skill.

## Các lần sửa `description`

| Lần | Thay đổi | Kết quả |
|---|---|---|
| 1 (bản gốc) | Không sửa. Mô tả nêu rõ việc skill làm, các câu nói gián tiếp, và phần KHÔNG dùng cho (báo cáo văn bản, tóm tắt tài liệu, giải thích kiến thức, email, code, sửa file `.pptx` có sẵn) | 10/10 đúng, không cần sửa |

## Bộ slide làm bằng skill

- Đề tài: Kiến trúc Microservices, thuyết trình 10 phút (câu hỏi số 1).
- File: `5 câu NÊN/câu hỏi 1/slides.md` (định dạng Marp) và bản xuất `slides.pdf`.

## Lưu ý khi đánh giá

- Các phiên chat dùng chung một thư mục dự án. Ở câu 1, agent có đọc cả file `bang-ket-qua-cau-hoi-thu.md` trong workspace; ở câu 5 nó đọc `slides.md` tạo ở lần trước. Điều này có thể ảnh hưởng nhẹ đến kết quả.
- Thư mục skill nằm ngay trong dự án nên agent có thể thấy trực tiếp các file của skill.

## Dùng skill cho môn học khác (bước 4 của đề)

- Môn / đề tài: ...
- Chỗ cần sửa trong skill: ...
