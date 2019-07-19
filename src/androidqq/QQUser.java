package androidqq;

import java.net.Socket;

public class QQUser {
	public long uin;
	public byte[] pwdmd5;
	public byte[] ksid=Utils.hexstr2bytes("93 AC 68 93 96 D5 7E 5F 94 96 B8 15 36 AA FE 91");
	public byte[] md52;
	byte[] caption;
	Socket socket;
	public byte[] shareKey;
	public byte[] pub_key;
	public byte[] TGTKey=new byte[16];
	public byte[] randKey=new byte[16];
	public long time;
	public QQUser(long uin,String pwd)
	{
		this.uin=uin;
		pwdmd5=Utils.MD5(pwd.getBytes());
		caption=(uin+"").getBytes();
		byte[] tmp=Utils.byteMerger(pwdmd5,new byte[]{0,0,0,0});
		md52=Utils.MD5(Utils.byteMerger(tmp, Utils.ToByte(uin)));// qq.md5 гл { 0, 0, 0, 0 } гл qq.user)
	}
	public void login() throws Exception
	{
		socket=new Socket("msfwifi.3g.qq.com", 8080);
		
	}
}
