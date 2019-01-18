# Goblin-Search
一个简单的检索系统，使用了jsp，lucene等技术，实现的功能很简陋，但是代码还算清爽，适合初学者交流学习。

## 简述：
从web of knowledge上获取了500篇英文文献的题录，是关于马克思主义哲学领域的，检索词为：“Marxist philosophy”。
题录保存在一个文件中，在目录[/resouce/raw_file/](https://github.com/pkuimyy/Goblin-Search/tree/master/resource/raw_file)下可以看到，其名为en1.txt。

在目录[/resouce/](https://github.com/pkuimyy/Goblin-Search/tree/master/resource)下有一个jupyter-notebook文件，名为cut_file.ipynb。还有一个与之同名的python文件，名为cut_file.py。
这一部分使用正则表达式从en1.txt中提取出每一篇文章的题录并分别保存在各个文件中，保存位置在[/resouce/cutted_file](https://github.com/pkuimyy/Goblin-Search/tree/master/resource/cutted_file/en)中，显然这个数量小于500，这是因为作者选用了四个元数据作为题录，分别是author,title,journal,abstract。只有拥有全部四个元数据项的文章才会被正则表达式提取出来。

目录[/src/](https://github.com/pkuimyy/Goblin-Search/tree/master/src/widget)下有两个类，GenarateIndex.java类用于生成索引。
索引相关的文件保存在[/index/](https://github.com/pkuimyy/Goblin-Search/tree/master/index)下。

目录[/WebContent/](https://github.com/pkuimyy/Goblin-Search/tree/master/WebContent)下是jsp网页，网页比较清爽。
网页使用到的辅助类为SearchWidget.java，在目录[/src](https://github.com/pkuimyy/Goblin-Search/tree/master/src/widget)下。

## 运行：
项目使用eclipse，jupyter-notebook开发，使用到Lucene与IKAnalyzer工具包，版本分别为3.5.0与2012u6。服务器tomcat版本为9。
从git获取到源代码后，运行index.jsp即可。
