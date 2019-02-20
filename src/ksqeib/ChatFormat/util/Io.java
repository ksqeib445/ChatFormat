package ksqeib.ChatFormat.util;

import ksqeib.ChatFormat.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Io {
	//ע����Ҫ�Ĳ���
	private final Main plugin;
	public FileConfiguration mY=null;
	// ���캯��
	public Io(Main main) {
		this.plugin=main;
	}
	
	//������Դ�ķ���
	public void CreateResouce(String path) {
		File f=new File(path);
		if(!f.exists()) {
		plugin.saveResource(path,false);
		}
	}
	
	//������Դ(û��Ĭ�ϴӲ���ڲ�����)
	public FileConfiguration LoadYamlFile(String str) {
		 CreateResouce(str);
		File mF=new File(plugin.getDataFolder(),str);
		FileConfiguration mY = YamlConfiguration.loadConfiguration(mF);
		return mY;
		
	}
	
	//����ȫ����Ҫ��ʼ����
	public void LoadDefaultFile() {	
		this.mY=LoadYamlFile("message.yml");
	}
}
