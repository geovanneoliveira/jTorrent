package client;

import java.io.File;

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
}
