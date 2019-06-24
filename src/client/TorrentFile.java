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
	
	public int getTotal(){

		int res = 0;

		try {
			this.torrentReader.seek(1);
			res = Integer.parseInt(this.torrentReader.readLine());
		}
		catch (Exception e){
			e.printStackTrace();
		}

		return res;
	}
	
	public ArrayList<Integer> getAllPartsFromTorrent() {
		
		String line = null;
		ArrayList<Integer> parts = new ArrayList<Integer>();
		int count = 0;
		
		try {
			this.torrentReader.seek(0);
			
			while((line = this.torrentReader.readLine()) != null) {
				
				if(count > 1) {						
					parts.add(this.getIdFromLine(line));
				}
				count++;
			}
		}
		catch(IOException e) {
			System.err.printf("Erro ao ler arquivo: %s.\n",e.getMessage());
			return null;
		}	
		
		return parts;
	}
	
	public String getHashById(int id) {
		String line = this.findLineById(id);
		return this.getHashFromLine(line);
	}
	
	private String findLineById(int id) {
		int count = 0;
		String line = null;
		
		try {
			this.torrentReader.seek(0);
			
			while((line = this.torrentReader.readLine()) != null) {
				
				if(count > 1) {						
					if(this.getIdFromLine(line) == id)
						return line;
				}
				count++;
			}
		}
		catch(IOException e) {
			System.err.printf("Erro ao ler arquivo: %s.\n",e.getMessage());
			return null;
		}
		return null;
	}
	
	private int getIdFromLine(String line) {
		String split[] = new String[2];
		
		split = line.split(" ");
		
		return Integer.parseInt(split[0]);
	}
	
	
	private String getHashFromLine(String line) {
		String split[] = new String[2];
		
		split = line.split(" ");
		
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
