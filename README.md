# badword.diff

这个工具用来把网监敏感词Excel转成SQL,方便将数据录入数据库

敏感词更新的时候网监会给一份新的Excel,这个时候人工去筛选工作量就太大了,
所以添加了新旧数据去重的功能,能直接生成新数据的sql

## 使用

在config.yml配置文件中配置好旧文件路径,新文件路径,目的文件路径,
然后就可以启动项目了

- `oldFilePath` 是指网监给的旧Excel文件的路径,文件请放到项目的resources目录中
- `newFilePath` 是指网监给的新Excel文件的路径,文件请放到项目的resources目录中
- `destFilePath` 是指生成的sql文件的路径