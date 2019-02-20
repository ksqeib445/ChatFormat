package ksqeib.ChatFormat.command;

import ksqeib.ChatFormat.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.MemorySection;

import java.util.List;

public class Cmd implements CommandExecutor{

	private final Main plugin;
	//ע����Ҫ�Ĳ���
	public Cmd(Main main) {
		this.plugin=main;
	}
	//��ָ��ʱ
	@Override
	public boolean onCommand(CommandSender cms, Command cmd, String label, String[] args) {
			if ((cmd).getLabel().equalsIgnoreCase("ChatFormat")) {
				if (args.length == 0) {
					//ʲôҲû����
					cms.sendMessage(plugin.getMessage("help.head"));
					cms.sendMessage(plugin.getMessage("help./") + label + plugin.getMessage("help.help"));
					cms.sendMessage(plugin.getMessage("help.last"));
				} else if (args.length > 0) {
					switch (args[0]) {
					case"about":
						cms.sendMessage("BY KSQEIB");
						break;
					default:
					case "help":
						//����help
						HelpPage(cms, label, args);
						break;
					case "playername":
						//���������ǰ׺�޸�ָ��
					case "severprefix":
						//���������ǰ׺�޸�ָ��
					case "prefix":
						//����ǰ׺�޸�ָ��
					case "suffix":
						//�����׺�޸�ָ��
					case "last":
						//������Ϣ���޸�ָ��
					case "speaklore":
						//����˵�������޸�ָ��
						if (args.length == 1) {
							if (cms.isOp()||cms.hasPermission("chatformat.view")) {
								//����Ȩ�޲���
								//Ԥ��
								cms.sendMessage(plugin.getMessage("message.head") + args[0]+plugin.getMessage("commands.view")+ getConfig().get(args[0]).toString().replace("&", "��"));
							}else {
								//��Ȩ����
								cms.sendMessage(plugin.getMessage("commands.nopermission"));
							}
						}
						
						else if (args.length > 1) {
												
							if (cms.isOp()||cms.hasPermission("chatformat.change")) {
								//����Ȩ�޲���
								String message = args[1];
								String mes = message.replace("&", "��");
								//��ȡ������config
								getConfig().set(args[0], mes);
								saveConfig();
								//Ԥ��
								cms.sendMessage(plugin.getMessage("message.head") + plugin.getMessage("commands."+args[0])+ mes);
								plugin.ev.Load();
							}else {
								//��Ȩ����
								cms.sendMessage(plugin.getMessage("commands.nopermission"));
							}
						} else {
							cms.sendMessage(
									//�������󣬸�����
									plugin.getMessage("message.head") + plugin.getMessage("commands.subwrong") + label +"  "+args[0] 
											+ plugin.getMessage("usage." + args[0]) );
						}
						
						break;
					case"showall":
						if (args.length == 1) {
							//ʲôҲû����
							cms.sendMessage(plugin.getMessage("help.head"));
							cms.sendMessage(plugin.getMessage("help./") + label + plugin.getMessage("help.showall"));
							cms.sendMessage(plugin.getMessage("help.last"));
						}
						break;
					case "show":
						//����showʱ
						if (cms.isOp()||cms.hasPermission("chatformat.show")) {
							//Ȩ�޼��
						String show=
						        plugin.ev.Hash.get("serverprefix")+ 
						        plugin.ev.Hash.get("prefix")+
								plugin.getMessage("commands.testplayername")+
								plugin.ev.Hash.get("suffix")+
								plugin.ev.Hash.get("speaklore")+
								plugin.getMessage("commands.testplayermessage")+
								plugin.ev.Hash.get("last");
						cms.sendMessage(show);
						}else {
							//��Ȩ����
							cms.sendMessage(plugin.getMessage("commands.nopermission"));
						}
						break;
					case "reload":
						plugin.io.LoadDefaultFile();
						plugin.reloadConfig();
						plugin.ev.Load();
						cms.sendMessage(plugin.getMessage("message.head") + plugin.getMessage("commands.reload") );
						break;
					}
				}
			}
			return true;
		}

	private void saveConfig() {
		plugin.saveConfig();
		
	}

	private MemorySection getConfig() {
		return plugin.getConfig();
	}

	//��ӡ��������
	public void SendHelp(List<String> hsl,CommandSender cms,String label) {
		for (int i = 0; i < hsl.size(); i++) {
			//��һ��һ��
			if(!((i%2)==0)) {
				//��������б��е�ȫ��
				//����ʹ��˵��
				cms.sendMessage(hsl.get(i));
				}else {
					//������ʲôָ��
					cms.sendMessage(plugin.getMessage("help./")+label+hsl.get(i));
				}
		}
	}
	//��ӡҳ��
	public void PageSend(int page,int leng,CommandSender cms) {
		String str=plugin.getMessage("help.page");
		String bstr=str.replace("[pagenow]", page+"").replace("[maxpage]", leng+"");
		cms.sendMessage(bstr);
		
	}
	//��ӡ����ҳ
	public void HelpPage(CommandSender cms,String label,String[] Strings) {
		
		cms.sendMessage(plugin.getMessage("help.head"));
		
		
		//��ȡҳ��ǩ
		List<String> oppages = plugin.getMessageList("help.oppages");
		List<String> compages = plugin.getMessageList("help.pages");
		//�ж��ٸ�ҳ��ǩ
		int opleng= oppages.size();
        int comleng= compages.size();
        int allleng=opleng+comleng;
		
		if (Strings.length > 1) {
			int page = 1;
			try {
				page = Integer.parseInt(Strings[1]);
			} catch (NumberFormatException e) {
				cms.sendMessage(plugin.getMessage("error.notnum"));
			}
			
			if (page > 0 && page <= comleng) {
				//����������һҳ
				
				//��ȡ��ҳ
				List<String>nowPage = plugin.getMessageList("help.page" +page);
				//���Ͱ�����Ϣ
				SendHelp(nowPage, cms, label);
				//����ҳ��
				
				if(!(cms.isOp()||cms.hasPermission("chatformat.oppage"))) {
					//�������op
				PageSend(page, comleng, cms);
				}else{
					PageSend(page, allleng, cms);
				}
			} else if (page > comleng) {
				//���������ֶ������һҳ����ӡ���һҳ

				
				if(!(cms.isOp()||cms.hasPermission("chatformat.oppage"))) {
					//�������op
					List<String> nowPage = plugin.getMessageList("help.page"+comleng);
					SendHelp(nowPage, cms, label);
					
				PageSend(page, comleng, cms);
				}else{
					//����op
					List<String> nowPage= plugin.getMessageList("help.oppage"+(allleng-comleng));;
					if(page<=allleng) {
					nowPage = plugin.getMessageList("help.oppage"+(page-comleng));
					}
					SendHelp(nowPage, cms, label);
					PageSend(page, allleng, cms);
				}
			}else {
				//����������������
		        //��ȡ��һҳ
				List<String> comPage = plugin.getMessageList("help."+compages.get(0));
				//����
				SendHelp(comPage, cms, label);
			}
		} else {
			//��������ʲôҲ����
	        //��ȡ��һҳ
			List<String> comPage = plugin.getMessageList("help."+compages.get(0));
			//����
			SendHelp(comPage, cms, label);
		}
		cms.sendMessage(plugin.getMessage("help.last"));
	}

}
