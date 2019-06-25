package client;

import java.io.File;
import java.security.MessageDigest;
import java.io.RandomAccessFile;

public class TorrentMaker {

	private File file;

	public TorrentMaker(String path) {
		this.file = new File(path);
	}

	public void makeTorrent() {

		try {
			File f = new File("/home/brenno/Documentos/" + getNameOnTorrentFormat());
			RandomAccessFile torrent = new RandomAccessFile(f, "rw");

			RandomAccessFile file = new RandomAccessFile(this.file, "r");

			torrent.writeBytes(getNameWithoutExtension());
			torrent.writeBytes(getNumberOfPecas(256));
			
			byte buffer[] = new byte[256];
			Integer count = 0;
			int seek = 256;

			while (file.read(buffer) != -1) {

				String line = count.toString()+" "+createHash(buffer)+"\n";

				torrent.writeBytes(line);
				
				count++;

				buffer = new byte[256];
				
				file.seek(seek * count);
			}
			
			file.close();
			torrent.close();

		} catch (Exception e) {
			System.out.println("Erro ao gerar arquivo .torrent: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private String getNumberOfPecas(int size) {
		int bytes = (int) this.file.length();

		if ((bytes % size) == 0) {
			Integer result = bytes / size;
			return result.toString() + "\n";
		} else {
			Integer result = bytes / size;
			result++;
			return result.toString() + "\n";
		}
	}


	public byte[] createHash(byte[] bytes) {

		byte messageDigest[] = null;

		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			messageDigest = algorithm.digest(bytes);

		} catch (Exception e) {

		}
		return messageDigest;
	}
	

	private String getNameWithoutExtension() {
		return this.file.getName().split("\\.")[0] + "\n";
	}

	private String getNameOnTorrentFormat() {

		String name = this.file.getName();

		String[] split = new String[2];

		split = name.split("\\.");

		return split[0]+".torrent";
	}
}
