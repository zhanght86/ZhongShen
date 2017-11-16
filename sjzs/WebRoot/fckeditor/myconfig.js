////设置为不自动语言检测
FCKConfig.AutoDetectLanguage = false ;
//定义默认的使用语言
FCKConfig.DefaultLanguage = 'zh-cn' ;
//一个TAB键代表的空格数
FCKConfig.TabSpaces = 4 ;
//强制粘贴为纯文本
//FCKConfig.ForcePasteAsPlainText = true ;
//定义使用的皮肤
FCKConfig.SkinPath = FCKConfig.BasePath + 'skins/default/' ;// default  silver office2003
//设置Enter模式
FCKConfig.EnterMode = 'p' ;			// p | div | br
//设置shift+Enter模式
FCKConfig.ShiftEnterMode = 'br' ;	// p | div | br
//工具栏的设置
FCKConfig.ToolbarSets["Myself"] = [
	['Source','NewPage','Preview','-','Templates'],
	['Cut','Copy','Paste','PasteText','PasteWord','-','Print'],
	['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
	['Bold','Italic','Underline','StrikeThrough','-','Subscript','Superscript'],
	['OrderedList','UnorderedList','-','Outdent','Indent','Blockquote','CreateDiv'],
	['JustifyLeft','JustifyCenter','JustifyRight','JustifyFull'],	
	'/',
	['Style','FontFormat','FontName','FontSize'],
	['TextColor','BGColor'],['Table','Rule','SpecialChar','PageBreak','Paragraph'],
	['Image','FitWindow','-','About']		// No comma for the last row.
] ;
FCKConfig.ToolbarSets["NoToolBarSets"] = [[]] ;
FCKConfig.ToolbarSets["NoTool"] =[];
FCKConfig.ToolbarSets["forum"] = [
	['OrderedList','Bold','Italic','Underline','StrikeThrough','JustifyLeft',
	 'JustifyCenter','JustifyRight','JustifyFull','FontName','FontSize','TextColor',
	 'BGColor','Smiley','Paragraph','FitWindow','About']
] ;
//字体列表字体
FCKConfig.FontNames	= 'Arial;Comic Sans MS;Courier New;Tahoma;Times New Roman;Verdana;宋体;隶书;楷体_GB2312;黑体;华文行楷' ;
//表情路径
FCKConfig.SmileyPath = FCKConfig.BasePath + 'images/smiley/wangwang/' ;
//表情文件
FCKConfig.SmileyImages = ['0.gif','1.gif','2.gif','3.gif','4.gif','5.gif','6.gif','7.gif','8.gif','9.gif','10.gif','11.gif','12.gif','13.gif','14.gif','15.gif','16.gif','17.gif','18.gif','19.gif','20.gif','21.gif','22.gif','23.gif','24.gif','25.gif','26.gif','27.gif','28.gif','29.gif','30.gif','31.gif','32.gif','33.gif','34.gif','35.gif','36.gif','37.gif','38.gif','39.gif','40.gif','41.gif','42.gif','43.gif','44.gif','45.gif','46.gif','47.gif','48.gif','49.gif','50.gif','51.gif','52.gif','53.gif','54.gif','55.gif','56.gif','57.gif','58.gif','59.gif','60.gif','61.gif','62.gif','63.gif','64.gif','65.gif','66.gif','67.gif','68.gif','69.gif','70.gif','71.gif','72.gif','73.gif','74.gif','75.gif','76.gif','77.gif','78.gif','79.gif','80.gif','81.gif','82.gif','83.gif'] ;
//表情列表的列数
FCKConfig.SmileyColumns = 8 ;
//表情列表对话框的宽度
FCKConfig.SmileyWindowWidth = 680 ;
//表情列表对话框的高度
FCKConfig.SmileyWindowHeight = 450 ;
//设置字体列表
FCKConfig.FontSizes	= '10px;12px;14px;15px;16px;18px;21px;smaller;larger;xx-small;x-small;small;medium;large;x-large;xx-large' ;