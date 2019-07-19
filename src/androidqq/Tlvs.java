package androidqq;

public class Tlvs
{
	public static byte[] tlv1(QQUser user)
	{
		/*
		 * , 字节集, 公开.参数 user, 字节集. 参数 time, 字节集. 局部变量 pack,_Pack
		 */
		// 00 01//tlv1
		// 00 14//长度20
		// 00 01//ip_ver=1
		// 3F AA 67 F9 // get_rand_32()
		// 18 B4 A1 BC[QQ号码：414491068]
		// 54 09 99 7F//get_server_cur_time
		// 00 00 00 00//_ip_addr
		// 00 00//0
		// time＝Xbin.Flip(字节集左边 (到字节集 (Other.TimeStamp ()), 4))
		ByteBuilder bbBuilder = new ByteBuilder();
		bbBuilder.writebytes("00 01");
		bbBuilder.writebytes(Utils.GetRandomBytes(4));
		bbBuilder.writelong(user.uin);
		bbBuilder.writelong(user.time);
		bbBuilder.writebytes("00 00 00 00");
		bbBuilder.writebytes("00 00");
		bbBuilder.rewriteint(bbBuilder.getdata().length);
		bbBuilder.rewritebytes("00 01");
		return bbBuilder.getdata();
	}
	public static byte[] tlv8()
	{
		//00 08 //tlv-8
		//00 08
		//00 00 //0 
		//00 00 08 04 //request_global._local_id
		//00 00 //0 
		ByteBuilder bbBuilder = new ByteBuilder();
		bbBuilder.writebytes("00 00");
		bbBuilder.writebytes("00 00 08 04");
		bbBuilder.writebytes("00 00");
		bbBuilder.rewriteint(bbBuilder.getdata().length);
		bbBuilder.rewritebytes("00 08");
		return bbBuilder.getdata();
	}
	public static byte[] tlv18(QQUser user)
	{
		// 00 18 //tlv18
		// 00 16 //tlv长度22 如果太长不是tlv包
		// 00 01 //_ping_version=1
		// 00 00 06 00 //_sso_version=1536
		// 00 00 00 10 //_appid
		// 00 00 00 00 //_app_client_version
		// 18 B4 A1 BC //[QQ号码：414491068]
		// 00 00 //0
		// 00 00 //0
		ByteBuilder bbBuilder = new ByteBuilder();
		bbBuilder.writebytes("00 01");
		bbBuilder.writebytes("00 01");
		bbBuilder.writebytes("00 00 06 00");
		bbBuilder.writebytes("00 00 00 10");
		bbBuilder.writebytes("00 00 00 00");
		bbBuilder.writelong(user.uin);
		bbBuilder.writebytes("00 00");
		bbBuilder.writebytes("00 00");
		bbBuilder.rewriteint(bbBuilder.getdata().length);
		bbBuilder.rewritebytes("00 18");
		return bbBuilder.getdata();
	}

	public static byte[] tlv100()
	{
		//字节集, 公开 .参数 appId, 整数型 .局部变量 pack, _Pack
		//01 00 //tlv-100
		//00 16 
		//00 01 //_db_buf_ver=1
		//00 00 00 05 //_sso_ver=5
		//00 00 00 10 //appid 
		//20 02 92 13 //_sub_appid 
		//00 00 00 00 //_app_client_version
		//00 0E 10 E0 //_main_sigmap
		ByteBuilder bbBuilder=new  ByteBuilder();
		bbBuilder.writebytes("00 01");
		bbBuilder.writebytes("00 00 00 05");
		bbBuilder.writebytes("00 00 00 10");
		bbBuilder.writeint(QQGlobal.appId);
		bbBuilder.writebytes("00 00 00 00");
		bbBuilder.writebytes("00 0E 10 E0");
		bbBuilder.rewriteint(bbBuilder.getdata().length);
		bbBuilder.rewritebytes("01 00");
		return bbBuilder.getdata();
	}

	public static byte[] tlv106(QQUser user)
	{
		/*
		 * .版本 2
		 * 
		 * .子程序 tlv106, 字节集, 公开 .参数 user, 字节集 .参数 md5pass, 字节集 .参数 md52pass, 字节集 .参数
		 * _TGTKey, 字节集 .参数 imei_, 字节集 .参数 time, 字节集 .参数 appId, 整数型 .局部变量 pack, _Pack
		 */
		/*
		 * 01 06 ' 00 70 [md5(md5(pass)+0 0 0 0+hexQQ)E8 FD 5B 08 BF 42 3C B9 F8 D4 23
		 * 30 F2 E2 E3 59 ] ' 67 A4 4D 1D 59 08 97 15 92 03 BB E3 E8 7F 49 CC 65 A2 F6
		 * E3 4F 68 DA 9E A2 E9 DA 90 DB 26 2D F5 A4 BD C0 52 51 F0 40 77 B5 50 25 42 AC
		 * 68 1B 57 35 61 97 65 36 6B AA 35 C5 E1 E6 C8 91 3B 3E 30 84 AA 6F 6C 32 29 97
		 * FB DF 53 CA 3C B5 F8 F3 13 E4 FF AA 58 39 75 81 45 38 4A A2 BE CA 43 E0 7E 0A
		 * 83 71 17 5C 88 7C DE DE ED B8 12 E4 D5 C4 22 ' [ ' 00 03 //TGTGTVer=3 ' 29 A5
		 * 69 34 rand32 ' 00 00 00 05 ' 00 00 00 10 ' 00 00 00 00 ' 00 00 00 00 ' 18 B4
		 * A1 BC [QQ:414491068] ' 4D 1F C3 AC //时间 ' 00 00 00 00 ' 01 ' EB E0 80 63 34
		 * 8C 9E E1 FD 6B 5E 05 9A 72 84 C6 //MD5PASS ' C5 2E 0F 5D A6 20 B5 EE 0B 94 F2
		 * 6F C3 05 4A 02 //TGTKey ' 00 00 00 ' 00 01 ' 46 60 1E D3 C6 24 16 BF CA A2 9E
		 * 9E B8 9A D2 4E //imei_ ' 20 02 93 92 _sub_appid ' 00 00 00 01 00 00 ' ] '
		 * time ＝Xbin.Flip 取字节集左边 (到字节集 (Other.TimeStamp ()), 4))
		 */
		ByteBuilder bbBuilder = new ByteBuilder();
		bbBuilder.writebytes("00 03");
		bbBuilder.writebytes(Utils.GetRandomBytes(4));
		bbBuilder.writebytes("00 00 00 05");
		bbBuilder.writebytes("00 00 00 10");
		bbBuilder.writebytes("00 00 00 00");
		bbBuilder.writelong(user.uin);
		bbBuilder.writelong(user.time);
		bbBuilder.writebytes("00 00 00 00 01");
		bbBuilder.writebytes(user.pwdmd5);
		bbBuilder.writebytes(user.TGTKey);
		bbBuilder.writebytes("00 00 00 00");
		bbBuilder.writebytes("01");
		bbBuilder.writebytes(QQGlobal.imei_);
		bbBuilder.writeint(QQGlobal.appId);
		bbBuilder.writebytes("00 00 00 01");
		bbBuilder.writebytes("00 00");
		bbBuilder.rewriteint(bbBuilder.getdata().length);
		bbBuilder.rewritebytes("01 06");
		// 调试输出 ("01 06", "TGTKey", 字节集到十六进制文本 (_TGTKey), 字节集到十六进制文本 (pack.GetAll ()))
		byte data[] = new Crypter().encrypt(bbBuilder.getdata(), user.md52);
		bbBuilder.clean();
		bbBuilder.writebytes(data);
		bbBuilder.rewriteint(bbBuilder.getdata().length);
		bbBuilder.rewritebytes("01 06");
		return bbBuilder.getdata();
	}

	public static byte[] tlv107()
	{
		// 01 07 //tlv-107 
		//00 06
		//00 00 //_pic_type 
		//00 //0 
		//00 00 //0 
		//01 //1
		ByteBuilder bbBuilder=new  ByteBuilder();
		bbBuilder.writebytes("00 00");
		bbBuilder.writebytes("00");
		bbBuilder.writebytes("00 00");
		bbBuilder.writebytes("01");
		bbBuilder.rewriteint(bbBuilder.getdata().length);
		bbBuilder.rewritebytes("01 07");
		return bbBuilder.getdata();
	}
	public static byte[] tlv108(byte[] ksid)
	{
		//01 08 //tlv108 
		//00 10
		//93 33 4E AD B8 08 D3 42 82 55 B7 EF 28 E7 E8 F5
		ByteBuilder bbBuilder=new  ByteBuilder();
		bbBuilder.writebytes(ksid);
		bbBuilder.rewriteint(bbBuilder.getdata().length);
		bbBuilder.rewritebytes("01 08");
		return bbBuilder.getdata();
	}

	public static byte[] tlv116()
	{
		// 01 16 
		//00 0A 
		// 00 
		// 00 00 7F 7C /mMiscBitmap 
		// 00 01 04 00 mSubSigMap 
		// 00 _sub_appid_list.length 
		ByteBuilder bbBuilder=new  ByteBuilder();
		bbBuilder.writebytes("00");
		bbBuilder.writebytes("00 00 7F 7C");
		bbBuilder.writebytes("00 01 04 00");
		bbBuilder.writebytes("00");
		bbBuilder.rewriteint(bbBuilder.getdata().length);
		bbBuilder.rewritebytes("01 16");
		return bbBuilder.getdata();
	}
	public static byte[] tlv16b()
	{
		//01 6B
		//00 0F 
		//00 01
		//00 0B
		//67 61 6D 65 2E 71 71 2E 63 6F 6D//[game.qq.com]
		ByteBuilder bbBuilder=new  ByteBuilder();
		bbBuilder.writebytes("00 01");
		bbBuilder.writebytes("00 0B");
		bbBuilder.writebytes("67 61 6D 65 2E 71 71 2E 63 6F 6D");
		bbBuilder.writebytes("00");
		bbBuilder.rewriteint(bbBuilder.getdata().length);
		bbBuilder.rewritebytes("01 6B");
		return bbBuilder.getdata();
	}
	public static byte[] tlv177()
	{
		//01 77 
		//00 0E 
		//01 
		//53 FB 17 9B 
		//00 07 
		//35 2E 32 2E 33 2E 30 [5.2.3.0]
		ByteBuilder bbBuilder=new  ByteBuilder();
		bbBuilder.writebytes("01");
		bbBuilder.writebytes("53 FB 17 9B");
		bbBuilder.writebytes("00 07");
		bbBuilder.writebytes("35 2E 32 2E 33 2E 30");
		bbBuilder.rewriteint(bbBuilder.getdata().length);
		bbBuilder.rewritebytes("01 77");
		return bbBuilder.getdata();
	}
	/*
	 * .子程序 tlv2, 字节集, 公开 .参数 code, 文本型 .参数 VieryToken1, 字节集 .局部变量 pack, _Pack
	 * 
	 * ' 00 02 ' 00 0E ' 00 00 00 04 ' 74 77 79 76 ' 00 04 ' 4B 58 31 35 pack.Empty
	 * () pack.SetInt (取文本长度 (code)) pack.writebytes (到字节集 (code)) pack.SetShort
	 * (取字节集长度 (VieryToken1)) pack.writebytes (VieryToken1)
	 * 
	 * 返回 (tlv_pack ("00 02", pack.GetAll ()))
	 * 
	 * .子程序 tlv100, 字节集, 公开 .参数 appId, 整数型 .局部变量 pack, _Pack
	 * 
	 * ' 01 00 //tlv-100 ' 00 16 ' 00 01 //_db_buf_ver=1 ' 00 00 00 05 //_sso_ver=5
	 * ' 00 00 00 10 //appid ' 20 02 92 13 //_sub_appid ' 00 00 00 00
	 * //_app_client_version ' 00 0E 10 E0 //_main_sigmap
	 * 
	 * pack.Empty () pack.writebytes ("00 01") pack.writebytes ("00 00 00 05")
	 * pack.writebytes ("00 00 00 10") pack.SetInt (appId) pack.writebytes
	 * ("00 00 00 00") pack.writebytes ("00 0E 10 E0")
	 * 
	 * 返回 (tlv_pack ("01 00", pack.GetAll ()))
	 * 
	 * .子程序 tlv104, 字节集, 公开 .参数 VieryToken2, 字节集 .局部变量 pack, _Pack
	 * 
	 * ' 01 04 ' 00 14 ' 41 56 51 4F 77 56 78 48 58 78 54 64 43 6F 73 4A ' 4D 6D 57
	 * 53 //VieryToken2 pack.Empty () pack.writebytes (VieryToken2)
	 * 
	 * 返回 (tlv_pack ("01 04", pack.GetAll ()))
	 * 
	 * .子程序 tlv108, 字节集, 公开 .参数 _ksid, 字节集
	 * 
	 * ' 01 08 //tlv108 ' 00 10 ' 93 33 4E AD B8 08 D3 42 82 55 B7 EF 28 E7 E8 F5
	 * //request_global._ksid _ksid ＝ { } 返回 (tlv_pack ("01 08", _ksid))
	 * 
	 * 
	 * .子程序 tlv144, 字节集, 公开 .参数 TGTKey, 字节集 .参数 tlv109, 字节集 .参数 tlv124, 字节集 .参数
	 * tlv128, 字节集 .参数 tlv16e, 字节集 .局部变量 pack, _Pack
	 * 
	 * ' 01 44 ' 00 80 (////_tgtgt_key) ' 60 17 BF D3 F7 A4 7E C5 BC 07 47 98 B3 9B
	 * 12 C1 ' CC F6 87 13 7A 28 BB 62 18 3B 1A 43 F8 FE 07 87 ' CB CF 40 3D BD DB
	 * 93 0F A7 CC F4 71 67 67 70 9E ' 33 14 CD E6 D7 CA 62 B4 48 FB 32 21 47 8F 40
	 * B5 ' A0 8E CB 5E 31 70 26 44 EA 79 AD A7 76 00 2A 26 ' 56 92 38 EA 78 BB CC
	 * 4E E8 E3 F4 CD FE 19 AB 32 ' A6 BB 31 72 D7 25 93 94 4A EF A7 94 A9 59 B2 73
	 * ' 55 95 4C FC AD C4 1A C2 15 C6 8F A1 39 48 F8 1A ' [ ' 00 04
	 * //get_tlv_144四个参数byte[]都有数据 ' 01 09 //tlv-109 ' 00 10 ' 46 60 1E D3 C6 24 16
	 * BF CA A2 9E 9E B8 9A D2 4E //_IMEI ' 01 24 //tlv-124 ' 00 1C ' 00 07 61 6E 64
	 * 72 6F 69 64 00 05 34 2E 30 2E 34 ' 00 01 00 00 00 00 00 04 77 69 66 69 ' 01
	 * 28 //tlv-128 ' 00 2B ' 00 00 //0 ' 00 //request_global._new_install ' 01
	 * //request_global._read_guid ' 00 //request_global._guid_chg ' 01 00 00 00
	 * //request_global._dev_report ' 00 0C ' 48 55 41 57 45 49 20 55 39 35 30 38
	 * //request_global._device=HUAWEI U9508 ' 00 10 ' 46 60 1E D3 C6 24 16 BF CA A2
	 * 9E 9E B8 9A D2 4E //request_global._IMEI ' 00 00 //0 ' 01 6E //tlv-16e ' 00
	 * 0C ' 48 55 41 57 45 49 20 55 39 35 30 38 //request_global._device=HUAWEI
	 * U9508 ' ]
	 * 
	 * pack.Empty () pack.SetShort (4) pack.writebytes (tlv109) pack.writebytes
	 * (tlv124) pack.writebytes (tlv128) pack.writebytes (tlv16e)
	 * 
	 * ' 调试输出 ("01 44", "TGTKey", 字节集到十六进制文本 (TGTKey), 字节集到十六进制文本 (pack.GetAll ()))
	 * 返回 (tlv_pack ("01 44", Hash.QQTEA (pack.GetAll (), TGTKey)))
	 * 
	 * .子程序 tlv109, 字节集, 公开 .参数 imei_, 字节集
	 * 
	 * ' 01 09 //tlv-109 ' 00 10 ' 46 60 1E D3 C6 24 16 BF CA A2 9E 9E B8 9A D2 4E
	 * //_IMEI 返回 (tlv_pack ("01 09", imei_))
	 * 
	 * 
	 * .子程序 tlv124, 字节集, 公开 .参数 os_type, 文本型 .参数 os_version, 文本型 .参数 _network_type,
	 * 整数型 .参数 _apn, 文本型 .局部变量 pack, _Pack
	 * 
	 * ' 01 24 //tlv-124 ' 00 1C ' 00 07 //os_type ' 61 6E 64 72 6F 69 64 ' 00 05
	 * //os_version ' 34 2E 30 2E 34 ' 00 01 //_network_type ' 00 00
	 * //._sim_operator_name ' 00 00 //0 ' 00 04 //_apn ' 77 69 66 69 pack.SetShort
	 * (取文本长度 (os_type)) pack.SetStr (os_type) pack.SetShort (取文本长度 (os_version))
	 * pack.SetStr (os_version) pack.SetShort (_network_type) pack.writebytes
	 * ("00 00") pack.writebytes ("00 00") pack.SetShort (取文本长度 (_apn)) pack.SetStr
	 * (_apn)
	 * 
	 * 返回 (tlv_pack ("01 24", pack.GetAll ()))
	 * 
	 * .子程序 tlv128, 字节集, 公开 .参数 _device, 文本型 .参数 imei_, 字节集 .局部变量 pack, _Pack
	 * 
	 * ' 01 28 //tlv-128 ' 00 2B ' 00 00 //0 ' 00 //request_global._new_install ' 01
	 * //request_global._read_guid ' 00 //request_global._guid_chg ' 01 00 00 00
	 * //request_global._dev_report ' 00 0C ' 48 55 41 57 45 49 20 55 39 35 30 38
	 * //request_global._device=HUAWEI U9508 ' 00 10 ' 46 60 1E D3 C6 24 16 BF CA A2
	 * 9E 9E B8 9A D2 4E //request_global._IMEI ' 00 00 //0 pack.Empty ()
	 * pack.writebytes ("00 00") pack.writebytes ("00") pack.writebytes ("01")
	 * pack.writebytes ("01") pack.writebytes ("01 00 02 00") pack.SetShort (取文本长度
	 * (_device)) pack.SetStr (_device) pack.SetShort (取字节集长度 (imei_))
	 * pack.writebytes (imei_) pack.writebytes ("00 00")
	 * 
	 * 返回 (tlv_pack ("01 28", pack.GetAll ()))
	 * 
	 * .子程序 tlv16e, 字节集, 公开 .参数 _device, 文本型 .局部变量 pack, _Pack
	 * 
	 * ' 01 6E //tlv-16e ' 00 0C ' 48 55 41 57 45 49 20 55 39 35 30 38
	 * //request_global._device=HUAWEI U9508 pack.Empty () pack.writebytes (到字节集
	 * (_device))
	 * 
	 * 返回 (tlv_pack ("01 6E", pack.GetAll ()))
	 * 
	 * 
	 * .子程序 tlv142, 字节集, 公开 .参数 _apk_id, 文本型 .局部变量 pack, _Pack
	 * 
	 * ' 01 42 //tlv142 ' 00 16 //len ' 00 00 00 12 //len ' 63 6F 6D 2E 74 65 6E 63
	 * 65 6E 74 2E 71 71 6C 69 ' 74 65 //request_global._apk_id=com.tencent.qqlite
	 * pack.Empty () pack.SetInt (取字节集长度 (到字节集 (_apk_id))) pack.writebytes (到字节集
	 * (_apk_id))
	 * 
	 * 返回 (tlv_pack ("01 42", pack.GetAll ()))
	 * 
	 * .子程序 tlv154, 字节集, 公开 .参数 _sso_seq, 整数型 .局部变量 pack, _Pack
	 * 
	 * ' 01 54 //tlv-154 ' 00 04 ' 00 01 19 6A //this._g._sso_seq pack.Empty ()
	 * pack.SetInt (_sso_seq)
	 * 
	 * 返回 (tlv_pack ("01 54", pack.GetAll ()))
	 * 
	 * .子程序 tlv145, 字节集, 公开 .参数 imei_, 字节集 .局部变量 pack, _Pack
	 * 
	 * ' 01 45 //tlv-145 ' 00 10 ' 46 60 1E D3 C6 24 16 BF CA A2 9E 9E B8 9A D2 4E
	 * //request_global._IMEI pack.Empty () pack.writebytes (imei_)
	 * 
	 * 返回 (tlv_pack ("01 45", pack.GetAll ()))
	 * 
	 * 
	 * .子程序 tlv141, 字节集, 公开 .参数 _network_type, 整数型 .参数 _apn, 文本型 .局部变量 pack, _Pack
	 * 
	 * ' 01 41 //tlv-141 ' 00 0C ' 00 01 // this._version=1 ' 00 00
	 * //request_global._sim_operator_name ' 00 01 //request_global._network_type '
	 * 00 04 //len ' 77 69 66 69 // request_global._apn
	 * 
	 * pack.Empty () pack.writebytes ("00 01") pack.writebytes ("00 00")
	 * pack.SetShort (_network_type) pack.SetShort (取文本长度 (_apn)) pack.SetStr (_apn)
	 * 
	 * 返回 (tlv_pack ("01 41", pack.GetAll ()))
	 * 
	 * 

	 * .子程序 tlv147, 字节集, 公开 .参数 _apk_v, 文本型 .参数 _apk_sig, 字节集 .局部变量 pack, _Pack
	 * 
	 * ' 01 47//tlv-147 ' 00 1D ' 00 00 00 10 //appid ' 00 05 ' 33 2E 30 2E 30 //
	 * request_global._apk_v ' 00 10 ' A6 B7 45 BF 24 A2 C2 77 52 77 16 F6 F3 6E B6
	 * 8D //request_global._apk_sig pack.Empty () pack.writebytes ("00 00 00 10")
	 * pack.SetShort (取文本长度 (_apk_v)) pack.SetStr (_apk_v) pack.SetShort (取字节集长度
	 * (_apk_sig)) pack.writebytes (_apk_sig)
	 * 
	 * 返回 (tlv_pack ("01 47", pack.GetAll ()))
	 * 
	 * .子程序 tlv114_get0058, 字节集, 公开 .参数 bin, 字节集 .局部变量 unPack, _Unpack .局部变量 len
	 * 
	 * ' 00 01 54 09 99 81 ' 00 58 //下面的data ' D1 48 33 52 6C F5 AA 2C 59 2E 7E E0
	 * 85 55 39 9E ' 35 C9 93 1E 1E 71 3F B7 AF 0E 17 D1 C4 13 69 C4 ' A6 75 38 52
	 * BF 0F F1 DA B1 61 B9 A0 39 14 F9 8D ' 59 6A EB F2 B8 33 66 A1 9E A5 7C 61 0D
	 * 0F 28 B6 ' C2 7D 72 1B 5C A5 96 42 80 80 F1 22 DF 5A 8C B8 ' 4A 8E BB F3 56
	 * B4 F4 13 unPack.SetData (bin) unPack.GetBin (6) len ＝ unPack.GetShort () .如果真
	 * (len ≠ 88) 调试输出 ("error tlv114_get0058 len!=0058") .如果真结束 返回 (unPack.GetBin
	 * (len))
	 * 
	 * .子程序 tlv187, 字节集, 公开 .局部变量 pack, _Pack
	 * 
	 * ' 01 87 ' 00 10 ' F8 FF 12 23 6E 0D AF 24 97 CE 7E D6 A0 7B DD 68
	 * 
	 * pack.Empty () pack.writebytes
	 * ("F8 FF 12 23 6E 0D AF 24 97 CE 7E D6 A0 7B DD 68")
	 * 
	 * 返回 (tlv_pack ("01 87", pack.GetAll ()))
	 * 
	 * .子程序 tlv188, 字节集, 公开 .局部变量 pack, _Pack
	 * 
	 * ' 01 88 ' 00 10 ' 4D BF 65 33 D9 08 C2 73 63 6D E5 CD AE 83 C0 43
	 * 
	 * pack.Empty () pack.writebytes
	 * ("4D BF 65 33 D9 08 C2 73 63 6D E5 CD AE 83 C0 43")
	 * 
	 * 返回 (tlv_pack ("01 88", pack.GetAll ()))
	 * 
	 * .子程序 tlv191, 字节集, 公开 .局部变量 pack, _Pack
	 * 
	 * ' 01 91 ' 00 01 ' 00
	 * 
	 * pack.Empty () pack.writebytes ("00")
	 * 
	 * 返回 (tlv_pack ("01 91", pack.GetAll ()))
	 */

}
