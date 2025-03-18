# 定义目标文件夹路径
$folderPath = "C:\path\to\your\files"  # 替换为你的文件夹路径

# 获取文件夹中的所有文件（排除子文件夹）
$files = Get-ChildItem -Path $folderPath -File | Sort-Object LastWriteTime

# 初始化计数器
$count = 1

# 遍历每个文件并重命名
foreach ($file in $files) {
    # 获取文件扩展名
    $extension = $file.Extension

    # 构造新的文件名
    $newName = "$count$extension"

    # 构造完整的文件路径
    $newFilePath = Join-Path -Path $folderPath -ChildPath $newName

    # 重命名文件
    Rename-Item -Path $file.FullName -NewName $newFilePath

    # 输出重命名信息（可选）
    Write-Host ("Renamed: {0} -> {1}" -f $file.Name, $newName)

    # 增加计数器
    $count++
}

Write-Host "All files have been renamed successfully!"