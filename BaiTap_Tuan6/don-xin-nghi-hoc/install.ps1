# Cài skill don-xin-nghi-hoc vào các agent hỗ trợ Agent Skills (toàn cục).
# Dùng: .\install.ps1          -> cài vào ~\.agents\skills + agent nào đã có trên máy
#       .\install.ps1 -All     -> cài vào tất cả thư mục bên dưới
param([switch]$All)
$ErrorActionPreference = "Stop"
$Name = "don-xin-nghi-hoc"
$Src = $PSScriptRoot

$Targets = @(
  @{ Cfg = "$HOME\.agents"; Dest = "$HOME\.agents\skills" },
  @{ Cfg = "$HOME\.claude"; Dest = "$HOME\.claude\skills" },
  @{ Cfg = "$HOME\.codex";  Dest = "$HOME\.codex\skills" },
  @{ Cfg = "$HOME\.cursor"; Dest = "$HOME\.cursor\skills" },
  @{ Cfg = "$HOME\.gemini"; Dest = "$HOME\.gemini\skills" },
  @{ Cfg = "$HOME\.gemini"; Dest = "$HOME\.gemini\config\skills" },
  @{ Cfg = "$HOME\.copilot"; Dest = "$HOME\.copilot\skills" }
)

foreach ($t in $Targets) {
  # luôn cài .agents\skills; các nơi khác chỉ cài nếu agent đã có (hoặc -All)
  if ($All -or (Test-Path $t.Cfg) -or $t.Cfg -eq "$HOME\.agents") {
    $dest = Join-Path $t.Dest $Name
    New-Item -ItemType Directory -Force $dest | Out-Null
    Copy-Item -Recurse -Force "$Src\SKILL.md", "$Src\references", "$Src\assets" $dest
    Write-Host "Đã cài: $dest"
  }
}
Write-Host "Xong. Hãy mở lại phiên agent để nó nạp skill."
