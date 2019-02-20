package ksqeib.ChatFormat.event;

import ksqeib.ChatFormat.Main;
import org.bukkit.configuration.MemorySection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Hashtable;
import java.util.Set;

public class Events implements Listener {
	//ע����Ҫ�Ĳ���
	private final Main plugin;
	String[] strs=null;
	public Hashtable<String,String> Hash=new Hashtable<String,String>();
	// ���캯��
	public Events(Main main) {
		this.plugin=main;
	}
	public void Load() {
		//��ȡ����
		Set<String> lis=getConfig().getKeys(false);
		for (String string : lis) {
			Hash.put(string, getConfig().getString(string).replace("&", "��"));
		}

	}
	//˵��ʱ
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent evt) {
		Player p=evt.getPlayer();
		String playername=null;
		//���������������Ƿ���Ч
		if(getConfig().getBoolean("isdisplayname")) {
		playername =	Hash.get("playername").replace("%playername%",p.getDisplayName() ) ;
		}else {
			playername =	Hash.get("playername").replace("%playername%",p.getName() ) ;
		}
		
		evt.setFormat(
				Hash.get("serverprefix")+ 
				Hash.get("prefix")+
				playername+
				Hash.get("suffix")+
				Hash.get("speaklore")+
				evt.getMessage()+
	        	Hash.get("last"));
	}
	private MemorySection getConfig() {
		return plugin.getConfig();
	}
	}
