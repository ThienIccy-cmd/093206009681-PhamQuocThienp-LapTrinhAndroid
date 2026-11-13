# don-xin-nghi-hoc

Agent Skill giúp soạn **đơn xin nghỉ học** bằng tiếng Việt đúng thể thức. Viết theo chuẩn mở [Agent Skills](https://agentskills.io) (một thư mục chứa `SKILL.md`), nên dùng được với các agent hỗ trợ chuẩn này như Claude Code, Codex, Cursor, Gemini CLI, GitHub Copilot.

Câu có thể kích hoạt skill: "xin nghỉ học", "mai em bị sốt", "báo vắng thứ Năm".
Skill **không** kích hoạt với: "viết đơn xin việc".

## Cài đặt nhanh (mọi agent)

```bash
git clone https://github.com/<TEN-GITHUB>/don-xin-nghi-hoc.git
cd don-xin-nghi-hoc
```

macOS / Linux:

```bash
./install.sh          # cài vào ~/.agents/skills + agent nào đã có trên máy
./install.sh --all    # cài vào tất cả thư mục bên dưới
```

Windows (PowerShell):

```powershell
.\install.ps1         # cài vào ~\.agents\skills + agent nào đã có trên máy
.\install.ps1 -All    # cài vào tất cả thư mục bên dưới
```

Sau khi cài, **mở lại phiên agent** để nó nạp skill.

## Thư mục skill của từng agent (toàn cục)

| Agent | Thư mục |
|---|---|
| Dùng chung (Codex, Copilot, Cursor, Gemini CLI... đọc được) | `~/.agents/skills/` |
| Claude Code | `~/.claude/skills/` |
| Codex | `~/.codex/skills/` |
| Cursor | `~/.cursor/skills/` |
| Gemini CLI | `~/.gemini/skills/` (hoặc `~/.agents/skills/`) |
| Antigravity (thay Gemini CLI cho gói miễn phí) | `~/.gemini/config/skills/` (hoặc `.agents/skills/` trong dự án) |
| GitHub Copilot | `~/.copilot/skills/` |

Các agent có thể thay đổi đường dẫn theo phiên bản. Nếu agent của bạn không nhận skill, xem tài liệu của agent đó để biết thư mục đúng và chép thư mục `don-xin-nghi-hoc` vào.

Cài thủ công cũng được: chép cả thư mục này (đổi tên thành `don-xin-nghi-hoc`) vào thư mục skills của agent.

## Cấu trúc

```
don-xin-nghi-hoc/
├── SKILL.md                           # bắt buộc: mô tả + quy trình
├── references/huong-dan-viet-don.md   # cách viết lý do, trường hợp đặc biệt
├── assets/mau-don.md                  # mẫu đơn và mẫu tin nhắn
├── install.sh / install.ps1           # script cài vào nhiều agent
└── LICENSE
```

## Giấy phép

MIT
