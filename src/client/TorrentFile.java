package client;


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class TorrentFile {
	
	private RandomAccessFile torrentReader;
	
	public TorrentFile(String filePath) {
		
		try {
			File file = new File(filePath);
		    this.torrentReader = new RandomAccessFile(file,"r");	    
		} 
		catch(IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n",e.getMessage());
		}
	}
	
	public String getNameTorrent() {
		
		String name = null;
		
		try {
			this.torrentReader.seek(0);
			name =  this.torrentReader.readLine();
		}
		catch(IOException e) {
			System.err.printf("Erro ao fechar arquivo: %s.\n",e.getMessage());
		}
		
		return name;
	}
	
	public ArrayList<Integer> getAllPartsFromTorrent() {
		
		String line = null;
		ArrayList<Integer> parts = new ArrayList<Integer>();
		
		try {
			this.torrentReader.seek(0);
			
			while(this.torrentReader.readLine() != null) {
				
				line = this.torrentReader.readLine();
				
				parts.add(this.getIdFromLine(line));
			}
		}
		catch(IOException e) {
			System.err.printf("Erro ao ler arquivo: %s.\n",e.getMessage());
		}	
		
		return parts;
	}
	
	private int getIdFromLine(String line) {
		
		String split[] = new String[2];
		
		split = line.split(line, ' ');
		
		return Integer.parseInt(split[0]) ;
	}
	
	private String getHashFromLine(String line) {
		String split[] = new String[2];
		
		split = line.split(line, ' ');
		
		return split[1];
	}
	
	public void closeFile() {
		try {
			this.torrentReader.close();
		}
		catch(IOException e) {
			System.err.printf("Erro ao fechar arquivo: %s.\n",e.getMessage());
		}
		
	};
}
