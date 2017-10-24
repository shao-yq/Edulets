package resources;

import java.util.*;

public class ShyqStrokeRes_zh extends ListResourceBundle { 
	
	public Object[][] getContents() { 
		return contents;
	}
	static final Object[][] contents = { 
// # Resource strings for ShyqMicaps example

        {"Title",       "CharEditor字编辑系统"},
        {"ElementTreeFrameTitle","Elements"},
        {"ViewportBackingStore","false"},
// # menubar definition
// #
// # Each of the strings that follow form a key to be 
// # used to the actual menu definition.
        {"menubar","file view edit"},

//# file Menu definition
//#
//# Each of the strings that follow form a key to be
//# used as the basis of a menu item definition.
//#
//# open ->  ShyqMicaps.openAction
//# new  ->  ShyqMicaps.newAction
//# save ->  ShyqMicaps.saveAction
//# print->  ShyqMicaps.printAction
//# exit ->  ShyqMicaps.exitAction
        {"file",        "open save print - exit"},
        {"file.text",   "文件"},
        {"open.text",   "打开"},
        {"open.image",   "resources/open.gif"},
        {"new.text",    "新建"},
        {"new.image",    "resources/new.gif"},
        {"save.text",   "保存"},
        {"save.image",   "resources/save.gif"},
        {"print.text",  "打印"},
        {"print.image",  "resources/print.gif"},
        {"exit.text",   "退出"},

//#
//# view Menu definition
//#
//# zoomout   
//# zoomin  ->
        {"view",        "toolbox statusbar dataList - zoomout zoomin - alterProject"},
        {"view.text",   "视图"},
        {"toolbox.text","工具箱"},
        {"toolbox.image","resources/toolbox.gif"},
        {"statusbar.text","状态栏"},
        {"zoomout.text","放大"},
        {"dataList.text","数据表"},
        {"zoomout.image","resources/zoomout.gif"},
        {"zoomin.text","缩小"},
        {"zoomin.image","resources/zoomin.gif"},
		{"alterProjectAction","alterProject"},
//#
//# edit Menu definition
//#
//# cut   -> JTextComponent.cutAction
//# copy  -> JTextComponent.copyAction
//# paste -> JTextComponent.pasteAction
        {"edit",        "undo redo - cut copy paste"},
        {"edit.text",   "编辑"},
        {"cut.text",    "剪切"},
        {"cutAction",   "cut-to-clipboard"},
        {"cut.image",    "resources/cut.gif"},
        {"copy.text",   "复制"},
        {"copyAction",  "copy-to-clipboard"},
        {"copy.image",   "resources/copy.gif"},
        {"paste.text",  "粘贴"},
        {"pasteAction", "paste-from-clipboard"},
        {"paste.image",  "resources/paste.gif"},
        {"undo.text",   "撤消"},
        {"undoAction",  "Undo"},
        {"redo.text",   "重复"},
        {"redoAction",  "Redo"},

//#
//# debug Menu definition
//#
        {"debug",       "dump showElementTree"},
        {"debug.text",  "调试Debug"},
        {"dump.text",   "Dump model to System.err"},
        {"dumpAction",  "dump-model"},
        {"showElementTree.text",    "Show Elements"},

//# toolbar definition
//#
//# Each of the strings that follow form a key to be
//# used as the basis of the tool definition.  Actions
//# are of course sharable, and in this case are shared
//# with the menu items.
        {"toolbar",     "open save print - cut copy paste - toolbox zoomout zoomin"},
        {"newTooltip",  "创建新文件"},
        {"openTooltip", "打开文件"},
        {"saveTooltip", "保存文件"},
        {"printTooltip",    "打印..."},
        {"cutTooltip",  "Move selection to clipboard"},
        {"copyTooltip", "Copy selection to clipboard"},
        {"pasteTooltip",    "Paste clipboard to selection"},
        {"toolboxTooltip",  "工具箱"},
        {"zoomoutTooltip",  "放大"},
        {"zoominTooltip",   "缩小"},

//# toolbox definition
//#
        {"pointer.text",  "指针Pointer"},
        {"pointer.image",  "resources/pointer.gif"},
        {"newCurve.text",  "新线条NewCurve"},
        {"newCurve.image",  "resources/newCurve.gif"},
        {"closedCurve.text",  "闭合线条ClosedCurve"},
        {"closedCurve.image",  "resources/closedCurve.gif"},
        {"modifyCurve.text",  "修改线条ModifyCurve"},
        {"modifyCurve.image",  "resources/modifyCurve.gif"},
        {"centerGD.text",  "高低中心CenterGD"},
        {"centerGD.image",  "resources/CenterGD.gif"},
        {"centerNL.text",  "冷暖中心CenterNL"},
        {"centerNL.image",  "resources/CenterNL.gif"},

        {"delete.text",  "删除Delete"},
        {"delete.image",  "resources/delete.gif"},
        {"text.text",  "文本Text"},
        {"text.image",  "resources/text.gif"},

        {"rain1.text",  "小雨Rain1"},
        {"rain1.image",  "resources/rain1.gif"},
        {"rain2.text",  "中雨Rain2"},
        {"rain2.image",  "resources/rain2.gif"},
        {"rain3.text",  "大雨Rain3"},
        {"rain3.image",  "resources/rain3.gif"},
        {"rain4.text",  "暴雨Rain4"},
        {"rain4.image",  "resources/rain4.gif"},
        {"snow1.text",  "小雪Snow1"},
        {"snow1.image",  "resources/Snow1.gif"},
        {"snow2.text",  "中雪Snow2"},
        {"snow2.image",  "resources/Snow2.gif"},
        {"snow3.text",  "大雪Snow3"},
        {"snow3.image",  "resources/Snow3.gif"},
        {"snow4.text",  "暴雪Snow4"},
        {"snow4.image",  "resources/Snow4.gif"},
        {"snow5.text",  "雨加雪Snow5"},
        {"snow5.image",  "resources/Snow5.gif"},

        //{"toolbox",  "pointer newCurve modifyCurve closedCurve delete text  rain1 rain2 rain3 rain4       snow1 snow2 snow3 snow4 snow5        centerGD centerNL"},
        {"pointerTooltip",  "指针"},
        {"newCurveTooltip",  "新加线条"},
        {"modifyCurveTooltip",  "修改已有线条"},
        {"closedCurveTooltip",  "增加闭合曲线"},
        {"deleteTooltip",  "删除（双击鼠标左键）"},
        {"textTooltip",  "文本"},
        {"centerGDTooltip",  "高低中心"},
        {"centerNLTooltip",  "冷暖中心"},
        {"rain1Tooltip",  "小雨"},
        {"rain2Tooltip",  "中雨"},
        {"rain3Tooltip",  "大雨"},
        {"rain4Tooltip",  "暴雨"},
        {"snow1Tooltip",  "小雪"},
        {"snow2Tooltip",  "中雪"},
        {"snow3Tooltip",  "大雪"},
        {"snow4Tooltip",  "暴雪"},
        {"snow5Tooltip",  "雨加雪"},
        
        {"toolbox",  "pointer newCurve modifyCurve closedCurve delete text  Heng Shu Dian Ti Pie Na HengZhe HengPie HengGou HengZheZhe HengZheGou HengZheZheZheGou HengZheWan HengPieWanGou HengZheTi HengZheWanGou HengZheZhePie  ShuGou ShuZhe ShuTi ShuWan ShuWanGou ShuZhePie ShuZheZhe ShuZheZheGou PieDian PieZhe XieGou WoGou"},
        {"Dian.text",  "Dian"},
        {"Dian.image", "resources/Dian.png"},
        {"Dian.tooltip",  "Dian"},
        {"Heng.text",  "Heng"},
        {"Heng.image", "resources/Heng.png"},
        {"Heng.tooltip",  "Heng"},
        {"Shu.text",  "Shu"},
        {"Shu.image",  "resources/Shu.png"},
        {"Shu.tooltip",  "Shu"},
        {"Pie.text",  "Pie"},
        {"Pie.image",  "resources/Pie.png"},
        {"Pie.tooltip",  "Pie "},
        {"Na.text",  "Na"},
        {"Na.image",   "resources/Na.png"},
        {"Na.tooltip",  "Na "},
        {"Ti.text",  "Ti"},
        {"Ti.image",   "resources/Ti.png"},
        {"Ti.tooltip",  "Ti"},

        {"PieDian.text",  "PieDian"},
        {"PieDian.image",  "resources/PieDian.png"},
        {"PieDian.tooltip",  "PieDian"},
        
        {"PieZhe.text",  "PieZhe"},
        {"PieZhe.image",  "resources/PieZhe.png"},
        {"PieZhe.tooltip",  "PieZhe"},
        
        {"HengGou.text",  "HengGou"},
        {"HengGou.image",  "resources/HengGou.png"},
        {"HengGou.tooltip",  "HengGou"},
        
        {"HengPie.text",  "HengPie"},
        {"HengPie.image",  "resources/HengPie.png"},
        {"HengPie.tooltip",  "HengPie"},
        
        {"HengPieWanGou.text",  "HengPieWanGou"},
        {"HengPieWanGou.image",  "resources/HengPieWanGou.png"},
        {"HengPieWanGou.tooltip",  "HengPieWanGou"},

        {"HengZhe.text",  "HengZhe"},
        {"HengZhe.image",  "resources/HengZhe.png"},
        {"HengZhe.tooltip",  "HengZhe"},
        
        {"HengZheGou.text",  "HengZheGou"},
        {"HengZheGou.image",  "resources/HengZheGou.png"},
        {"HengZheGou.tooltip",  "HengZheGou"},

        {"HengZheTi.text",  "HengZheTi"},
        {"HengZheTi.image",  "resources/HengZheTi.png"},
        {"HengZheTi.tooltip",  "HengZheTi"},
        
        {"HengZheWan.text",  "HengZheWan"},
        {"HengZheWan.image",  "resources/HengZheWan.png"},
        {"HengZheWan.tooltip",  "HengZheWan"},
        
        {"HengZheZhe.text",  "HengZheZhe"},
        {"HengZheZhe.image",  "resources/HengZheZhe.png"},
        {"HengZheZhe.tooltip",  "HengZheZhe"},
        
        {"HengZheWanGou.text",  "HengZheWanGou"},
        {"HengZheWanGou.image",  "resources/HengZheWanGou.png"},
        {"HengZheWanGou.tooltip",  "HengZheWanGou"},
        
        {"HengZheZhePie.text",  "HengZheZhePie"},
        {"HengZheZhePie.image",  "resources/HengZheZhePie.png"},
        {"HengZheZhePie.tooltip",  "HengZheZhePie"},
        
        {"HengZheZheZheGou.text",  "HengZheZheZheGou"},
        {"HengZheZheZheGou.image",  "resources/HengZheZheZheGou.png"},
        {"HengZheZheZheGou.tooltip",  "HengZheZheZheGou"},

        {"ShuGou.shutitext",  "ShuGou"},
        {"ShuGou.image",  "resources/ShuGou.png"},
        {"ShuGou.tooltip",  "ShuGou"},

        {"ShuTi.shutitext",  "ShuTi"},
        {"ShuTi.image",  "resources/ShuTi.png"},
        {"ShuTi.tooltip",  "ShuTi"},
        
        {"ShuWan.text",  "ShuWan"},
        {"ShuWan.image",   "resources/ShuWan.png"},
        {"ShuWan.tooltip",  "ShuWan"},
        {"ShuZhe.text",  "ShuZhe"},
        {"ShuZhe.image",   "resources/ShuZhe.png"},
        {"ShuZhe.tooltip",  "ShuZhe"},
        {"ShuWanGou.text",  "ShuWanGou"},
        {"ShuWanGou.image",   "resources/ShuWanGou.png"},
        {"ShuWanGou.tooltip",  "ShuWanGou"},
        {"ShuZhePie.text",  "ShuZhePie"},
        {"ShuZhePie.image",   "resources/ShuZhePie.png"},
        {"ShuZhePie.tooltip",  "ShuZhePie"},
        {"ShuZheZhe.text",  "ShuZheZhe"},
        {"ShuZheZhe.image",   "resources/ShuZheZhe.png"},
        {"ShuZheZhe.tooltip",  "ShuZheZhe"},
        {"ShuZheZheGou.text",  "ShuZheZheGou"},
        {"ShuZheZheGou.image",   "resources/ShuZheZheGou.png"},
        {"ShuZheZheGou.tooltip",  "ShuZheZheGou"},
        {"XieGou.text",  "XieGou"},
        {"XieGou.image",   "resources/XieGou.png"},
        {"XieGou.tooltip",  "XieGou"},
        {"WoGou.text",  "WoGou"},
        {"WoGou.image",   "resources/WoGou.png"},
        {"WoGou.tooltip",  "WoGou"},
        
	}; 
} 
