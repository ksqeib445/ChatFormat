package ksqeib.ChatFormat;

import ksqeib.ChatFormat.command.Cmd;
import ksqeib.ChatFormat.event.Events;
import ksqeib.ChatFormat.util.Io;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

//����
public class Main extends JavaPlugin {

	// ���캯��
	 public Events ev=null;
	 Cmd cm=null;
	 public Io io=null;
	 
	//����
@Override
public void onEnable() {
	//�����ഫ����
	io=new Io(this);
	//����
	io.LoadDefaultFile();
	//����
	saveDefaultConfig();
	ev=new Events(this);
	ev.Load();
	cm=new Cmd(this);
	sendMessage(io.mY.getString("message.head") + io.mY.getString("message.enable"));
	//ע��
	Bukkit.getPluginManager().registerEvents(ev, this);
	getCommand("ChatFormat").setExecutor(cm);
}
//�ر�

@Override
public void onDisable() {

	saveConfig();
	Bukkit.getConsoleSender().sendMessage(getMessage("message.head") + getMessage("message.disable"));
}



//������һЩ�Ż�
//�򵥷�����Ϣ
public void sendMessage(String str) {
	Bukkit.getConsoleSender().sendMessage(str);
}

//���ٻ��message�е���Ϣ
public String getMessage(String str) {
	return io.mY.getString(str);
}
//��ȡlist
public List<String> getMessageList(String str){
	return io.mY.getStringList(str);
}
}