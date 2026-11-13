#!/usr/bin/env bash
# Cài skill don-xin-nghi-hoc vào các agent hỗ trợ Agent Skills (toàn cục).
# Dùng: ./install.sh          -> cài vào ~/.agents/skills + agent nào đã có trên máy
#       ./install.sh --all    -> cài vào tất cả thư mục bên dưới
set -e
NAME="don-xin-nghi-hoc"
SRC="$(cd "$(dirname "$0")" && pwd)"
ALL=0; [ "$1" = "--all" ] && ALL=1

# "thư mục cấu hình agent|thư mục skills"
TARGETS=(
  "$HOME/.agents|$HOME/.agents/skills"
  "$HOME/.claude|$HOME/.claude/skills"
  "$HOME/.codex|$HOME/.codex/skills"
  "$HOME/.cursor|$HOME/.cursor/skills"
  "$HOME/.gemini|$HOME/.gemini/skills"
  "$HOME/.gemini|$HOME/.gemini/config/skills"
  "$HOME/.copilot|$HOME/.copilot/skills"
)

for t in "${TARGETS[@]}"; do
  cfg="${t%%|*}"; dest="${t##*|}/$NAME"
  # luôn cài ~/.agents/skills; các nơi khác chỉ cài nếu agent đã có (hoặc --all)
  if [ "$ALL" = 1 ] || [ -d "$cfg" ] || [ "$cfg" = "$HOME/.agents" ]; then
    mkdir -p "$dest"
    cp -r "$SRC/SKILL.md" "$SRC/references" "$SRC/assets" "$dest/"
    echo "Đã cài: $dest"
  fi
done
echo "Xong. Hãy mở lại phiên agent để nó nạp skill."
