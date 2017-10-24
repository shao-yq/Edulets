package net.edulet.common;

import java.util.*;

public class ShyqUIRes extends ListResourceBundle { 
	
	public Object[][] getContents() { 
		return contents;
	}
	static final Object[][] contents = { 
// # Resource strings for ShyqMicaps example

        {"Author",       "Shao Yong Qing"},
        {"Version",       "1.0"},

		{"BaseDialog.okButton.text","Ok"},
		{"BaseDialog.okButton.mnemonic","O"},
		{"BaseDialog.applyButton.text","Apply"},
		{"BaseDialog.applyButton.mnemonic","A"},
		{"BaseDialog.cancelButton.text","Cancel"},
		{"BaseDialog.cancelButton.mnemonic","C"},
		{"BaseDialog.helpButton.text","Help"},
		{"BaseDialog.helpButton.mnemonic","H"},
		{"BaseDialog.closeButton.text","Close"},
		{"BaseDialog.closeButton.mnemonic","l"},
    };
}