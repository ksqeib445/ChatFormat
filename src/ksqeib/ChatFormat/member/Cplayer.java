package ksqeib.ChatFormat.member;

import org.bukkit.entity.Player;

public class Cplayer {
	String pn=null;
	int move=1;
	public Cplayer(String pn) {
		// TODO 自动生成的构造函数存根
	}
	public Cplayer(Player player) {
		// TODO 自动生成的构造函数存根
		this.pn=player.getName();
	}
	int getMove() {
		return move;
	}
	void setMove(int move) {
		this.move = move;
	}
	String getPrefix() {
		return prefix;
	}
	void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	String getSufffix() {
		return sufffix;
	}
	void setSufffix(String sufffix) {
		this.sufffix = sufffix;
	}
	String getSpeaklore() {
		return speaklore;
	}
	void setSpeaklore(String speaklore) {
		this.speaklore = speaklore;
	}
	String getLast() {
		return last;
	}
	void setLast(String last) {
		this.last = last;
	}
	String getPlayername() {
		return playername;
	}
	void setPlayername(String playername) {
		this.playername = playername;
	}
	String getServerprefix() {
		return serverprefix;
	}
	void setServerprefix(String serverprefix) {
		this.serverprefix = serverprefix;
	}
	String prefix=null;
	String sufffix=null;
	String speaklore=null;
	String last=null;
	String playername=null;
	String serverprefix=null;
}
