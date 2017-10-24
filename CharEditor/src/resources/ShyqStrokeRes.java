package resources;

import java.util.*;

public class ShyqStrokeRes extends ListResourceBundle { 
	
	public Object[][] getContents() { 
		return contents;
	}
	static final Object[][] contents = { 
// # Resource strings for ShyqMicaps example

        {"Title",       "CharEditor"},
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
        {"file",        "new open save print - exit"},
        {"file.text",   "File"},
        {"open.text",   "Open"},
        {"open.image",   "resources/open.gif"},
        {"new.text",    "New"},
        {"new.image",    "resources/new.gif"},
        {"save.text",   "Save"},
        {"save.image",   "resources/save.gif"},
        {"print.text",  "Print"},
        {"print.image",  "resources/print.gif"},
        {"exit.text",   "Exit"},

//#
//# view Menu definition
//#
//# zoomout   
//# zoomin  ->
        {"view",        "toolbox statusbar dataList - zoomout zoomin alterProject"},
        {"view.text",   "View"},
        {"toolbox.text","ToolBox"},
        {"toolbox.type","CheckBox"},
        {"toolbox.value","Selected"},
        {"toolbox.image","resources/toolbox.gif"},
        {"statusbar.text","Statusbar"},
        {"dataList.text","Data List"},
        {"dataList.value","Unselected"},
//        {"fileList.type","CheckBox"},
        {"zoomout.text","Zoom-out"},
        {"zoomout.image","resources/zoomout.gif"},
        {"zoomin.text","Zoom-in"},
        {"zoomin.image","resources/zoomin.gif"},
        {"alterProject.text","Map Project"},
        {"alterProjectAction",  "alterProject"},
//#
//# edit Menu definition
//#
//# cut   -> JTextComponent.cutAction
//# copy  -> JTextComponent.copyAction
//# paste -> JTextComponent.pasteAction
        {"edit",        "undo redo - cut copy paste"},
        {"edit.text",   "Edit"},
        {"cut.text",    "Cut"},
        {"cutAction",   "cut-to-clipboard"},
        {"cut.image",    "resources/cut.gif"},
        {"copy.text",   "Copy"},
        {"copyAction",  "copy-to-clipboard"},
        {"copy.image",   "resources/copy.gif"},
        {"paste.text",  "Paste"},
        {"pasteAction", "paste-from-clipboard"},
        {"paste.image",  "resources/paste.gif"},
        {"undo.text",   "Undo"},
        {"undoAction",  "Undo"},
        {"redo.text",   "Redo"},
        {"redoAction",  "Redo"},

//#
//# debug Menu definition
//#
        {"debug",       "dump showElementTree"},
        {"debug.text",  "Debug"},
        {"dump.text",   "Dump model to System.err"},
        {"dumpAction",  "dump-model"},
        {"showElementTree.text",    "Show Elements"},

//# toolbar definition
//#
//# Each of the strings that follow form a key to be
//# used as the basis of the tool definition.  Actions
//# are of course sharable, and in this case are shared
//# with the menu items.
        {"toolbar",     "new open save print - cut copy paste - toolbox zoomout zoomin"},
        {"new.tooltip",  "Create a new file"},
        {"open.tooltip", "Open a file"},
        {"save.tooltip", "Save to a file"},
        {"print.tooltip",    "Print..."},
        {"cut.tooltip",  "Move selection to clipboard"},
        {"copy.tooltip", "Copy selection to clipboard"},
        {"paste.tooltip",    "Paste clipboard to selection"},
        {"toolbox.tooltip",  "Tool box"},
        {"zoomout.tooltip",  "Zoom out"},
        {"zoomin.tooltip",   "Zoom in"},

//# toolbox definition
//#
        {"pointer.text",  "Pointer"},
        {"pointer.image",  "resources/pointer.gif"},
        {"newCurve.text",  "NewCurve"},
        {"newCurve.image",  "resources/newCurve.gif"},
        {"closedCurve.text",  "ClosedCurve"},
        {"closedCurve.image",  "resources/closedCurve.gif"},
        {"modifyCurve.text",  "ModifyCurve"},
        {"modifyCurve.image",  "resources/modifyCurve.gif"},
        {"centerGD.text",  "CenterGD"},
        {"centerGD.image",  "resources/CenterGD.gif"},
        {"centerNL.text",  "CenterNL"},
        {"centerNL.image",  "resources/CenterNL.gif"},

        {"delete.text",  "Delete"},
        {"delete.image",  "resources/delete.gif"},
        {"text.text",  "Text"},
        {"text.image",  "resources/text.gif"},

        {"rain1.text",  "Rain1"},
        {"rain1.image",  "resources/rain1.gif"},
        {"rain2.text",  "Rain2"},
        {"rain2.image",  "resources/rain2.gif"},
        {"rain3.text",  "Rain3"},
        {"rain3.image",  "resources/rain3.gif"},
        {"rain4.text",  "Rain4"},
        {"rain4.image",  "resources/rain4.gif"},
        {"snow1.text",  "Snow1"},
        {"snow1.image",  "resources/Snow1.gif"},
        {"snow2.text",  "Snow2"},
        {"snow2.image",  "resources/Snow2.gif"},
        {"snow3.text",  "Snow3"},
        {"snow3.image",  "resources/Snow3.gif"},
        {"snow4.text",  "Snow4"},
        {"snow4.image",  "resources/Snow4.gif"},
        {"snow5.text",  "Snow5"},
        {"snow5.image",  "resources/Snow5.gif"},

//        {"toolbox",  "pointer newCurve modifyCurve closedCurve delete text  rain1 rain2 rain3 rain4       snow1 snow2 snow3 snow4 snow5        centerGD centerNL"},
        {"pointer.tooltip",  "Pointer"},
        {"newCurve.tooltip",  "New Curve"},
        {"modifyCurve.tooltip",  "Modify Curve"},
        {"closedCurve.tooltip",  "Add Closed Curve"},
        {"delete.tooltip",  "delete (Double click)"},
        {"text.tooltip",  "Text"},
        {"centerGD.tooltip",  "CenterGD"},
        {"centerNL.tooltip",  "CenterNL"},
        {"rain1.tooltip",  "Small Rain"},
        {"rain2.tooltip",  "Middle Rain"},
        {"rain3.tooltip",  "Big Rain"},
        {"rain4.tooltip",  "Heavy Rain "},
        {"snow1.tooltip",  "Small Snow"},
        {"snow2.tooltip",  "Middle Snow"},
        {"snow3.tooltip",  "Big Snow"},
        {"snow4.tooltip",  "Heavy Snow"},
        {"snow5.tooltip",  "Rain & Snow"},

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
