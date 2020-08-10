package com.spkitty.frame;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class SettingManager {
	
	public static final String VERSION = "REL-1-0-0";
	
	public static final String DEFAULT_STRING = "25000\r\n" + 
												"10\r\n" + 
												"C:\\\r\n" + 
												"C:\\ProjectIgnis\\";
	
	public static String path = "C:\\ProjectIgnis\\";
	public static String deckName = "";
	
	public static int count = 25000;
	public static int distribSize = 10;
	
	public static String getPath() {
		return path;
	}
	
	public static String buildDeckPath() {
		return getPath() + "deck\\";
	}
	
	public static String buildImagePath() {
		return getPath() + "pics\\";
	}
	
	public static void saveSettings() {
		File f = new File("setting.txt");
		try(Scanner read = new Scanner(f)){
			try(FileWriter out = new FileWriter(f)){
				if(f.exists())
					f.delete();
				f.createNewFile();
				out.write(count + "\n" + distribSize + "\n" + "unused" + "\n" + path);
			}catch(Exception e1) {System.out.println("big oopsie happen :(");}
			
		}catch(Exception e0) {
			JOptionPane.showMessageDialog(null, "Error saving sim settings.\n" + e0.toString());
			try(FileWriter out = new FileWriter(f)){
				if(f.exists())
					f.delete();
				f.createNewFile();
				out.write(DEFAULT_STRING);
			}catch(Exception e1) {System.out.println("big oopsie happen :(");}
		}
	}
	
	public static void loadSettingsFromFile() {
		File f = new File("setting.txt");
		String raw = "";
		String[] preParse = new String[6];
		try(Scanner read = new Scanner(f)){
			while(read.hasNextLine())
				raw += read.nextLine() + ";";
			preParse = raw.split(";");
			count = Integer.valueOf(preParse[0]);
			distribSize = Integer.valueOf(preParse[1]);
			//drive = preParse[2];
			path = preParse[3];
		}catch(Exception e0) {
			JOptionPane.showMessageDialog(null, "Settings file missing/corrupted. Creating new settings file and starting.\n" + e0.toString());
			try(FileWriter out = new FileWriter(f)){
				if(f.exists())
					f.delete();
				f.createNewFile();
				out.write(DEFAULT_STRING);
			}catch(Exception e1) {System.out.println("big oopsie happen :(");}
		}
	}
}
