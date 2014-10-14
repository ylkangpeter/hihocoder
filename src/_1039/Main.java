package _1039;

import java.nio.ByteBuffer;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// System.out.println(new String(erase(-1, "abbcca".getBytes())));
		Scanner in = new Scanner(System.in);
		int total = in.nextInt();
		for (int counter = 0; counter < total; counter++) {
			String input = in.next();
			byte[] src = input.getBytes();
			int result = 0;
			for (int i = 0; i <= src.length; i++) {
				for (int j = 65; j < 68; j++) {
					byte[] dest = new byte[src.length + 1];
					System.arraycopy(src, 0, dest, 0, i);
					if (i < src.length) {
						System.arraycopy(src, i, dest, i + 1, src.length - i);
					}
					dest[i] = (byte) j;
					// System.out.println(new String(dest));
					byte[] done = erase(-1, dest);
					if (result < (dest.length - done.length)) {
						result = dest.length - done.length;
					}
				}
			}
			System.out.println(result);
		}
	}

	private static byte[] erase(int range, byte[] bytes) {

		byte pre = -1;
		short counter = 0;
		ByteBuffer bb = ByteBuffer.allocate(bytes.length);
		if (range == -1) {
			// all same will be erased
			for (byte b : bytes) {
				if (b == pre) {
					counter++;
					continue;
				} else {

					if (counter == 1) {
						bb.put(pre);
					}
					pre = b;
					counter = 1;
				}
			}
			if (counter == 1) {
				bb.put(pre);
			}
		} else {

		}
		if (bb.position() == 0) {
			return new byte[0];
		}
		if (bb.position() == bytes.length) {
			return bytes;
		} else {
			bytes = new byte[bb.position()];
			bb.flip();
			bb.get(bytes);
			return erase(range, bytes);
		}
	}

}
