package androidqq;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.nio.*;

public class Utils {
	public static byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
		byte[] byte_3 = new byte[byte_1.length + byte_2.length];
		System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
		System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
		return byte_3;
	}

	public static byte[] MD5(byte[] arg) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(arg);
			return bytes;
		} catch (NoSuchAlgorithmException e) {
		}
		return null;
	}

	public static byte[] hexstr2bytes(String str) {
		String replaceAll = str.replaceAll(" ", "");
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(replaceAll.length() >> 1);
		for (int i = 0; i <= replaceAll.length() - 2; i += 2) {
			byteArrayOutputStream.write(Integer.parseInt(replaceAll.substring(i, i + 2), 16) & 255);
		}
		return byteArrayOutputStream.toByteArray();
	}

	public static void writelong(long to_write) {
		// byte[] test = Utils.subByte(Util.ToByte(to_write),4,4);
	}

	public static byte[] subByte(byte[] b, int off, int length) {

		if (b.length != 0 && b != null) {
			byte[] b1 = new byte[length];
			System.arraycopy(b, off, b1, 0, length);
			return b1;
		}
		return new byte[1];
	}

	public static byte[] ToByte(long x) {
		ByteBuffer buffer = ByteBuffer.allocate(8);
		buffer.putLong(x);
		return buffer.array();
	}

	public static byte[] ToByte(int x) {
		ByteBuffer buffer = ByteBuffer.allocate(8);
		buffer.putInt(x);
		return buffer.array();
	}

	public static byte[] ToByte(short x) {
		ByteBuffer buffer = ByteBuffer.allocate(8);
		buffer.putShort(x);
		return buffer.array();
	}

	public static byte[] GetRandomBytes(int i) {
		// TODO Auto-generated method stub
		byte[] bytes=new byte[i];
		new Random().nextBytes(bytes);
		return bytes;
	}

}
