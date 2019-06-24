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

			File f = new File("/home/brenno/Documentos/"+getNameOnTorrentFormat());
			RandomAccessFile torrent = new RandomAccessFile(f,"w");
			
			RandomAccessFile file = new RandomAccessFile(this.file,"r");
			
			torrent.writeBytes(getNameWithoutExtension());

		}	
		catch(Exception e) {
			System.out.println("Erro ao gerar arquivo .torrent: "+e.getMessage());
		}
	}
	
	private int getNumberOfPeças(int size) {
		long bytes = this.file.length();
		
		if((bytes%size) == 0) {
			return 0;
		}
		else {
			return 0;
		}
	}

	public byte[] createHash(byte[] bytes) {

		byte messageDigest[] = null;

		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			messageDigest = algorithm.digest(bytes);

			System.out.println(messageDigest);

		} catch (Exception e) {

		}
		return messageDigest;
	}
	
	
	private String getNameWithoutExtension() {
		return this.file.getName().split(".")[0];
	}
	
	private String getNameOnTorrentFormat() {
		String name = this.file.getName().split(".")[0];	
		return name+".torrent";
	}
}
