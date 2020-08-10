package com.spkitty.core;
import com.spkitty.frame.MainFrame;
import com.spkitty.frame.SettingManager;

/**
 * eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
 * @author SpearKitty
 *
 */

public class Start {
	public static void main(String[] args){
		SettingManager.loadSettingsFromFile();
		MainFrame obj = new MainFrame();
		obj.run();
	}
}
