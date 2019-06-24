package client;

import java.io.File;
import java.security.MessageDigest;

public class TorrentMaker {
	
	private File file;
	
	public TorrentMaker(String path) {
		this.file = new File(path);
	}
	
	public void makeTorrent() {
		
		try {
			String[] splitFileName = this.file.getName().split(".");
			File f = new File("/home/brenno/Documentos/"+splitFileName[0]+".torrent");



		}
		catch(Exception e) {
			
		}
	}

	public byte[] createHash(byte[] bytes) {

		byte messageDigest[] = null;

		try
		{
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			messageDigest = algorithm.digest(bytes);

			System.out.println(messageDigest);

		}
		catch (Exception e)
		{

		}
		return messageDigest;

	}
}
