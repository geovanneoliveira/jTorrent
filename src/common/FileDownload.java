package common;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileDownload {
	
	private RandomAccessFile file;
	private String path;
	private String name;
	private String nameWithoutExtension;
	
	public FileDownload(String path) {
		this.path = path;
	}
	
	public void openFile() {
		try {
			File f = new File(this.path);
			this.file = new RandomAccessFile(f,"r");
			
			setName(f.getName());
			setNameWithoutExtension(f.getName());
		}
		catch(IOException e) {
			System.err.printf("Erro ao abrir arquivo a ser baixado: %s.\n",e.getMessage());
		}
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setNameWithoutExtension(String name) {
		this.nameWithoutExtension = name.split("\\.")[0];
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getNameWithoutExtension() {
		return this.nameWithoutExtension;
	}

	public void openToWrite() {
		try {
			File f = new File(this.path);
			this.file = new RandomAccessFile(f,"rw");
		}
		catch(IOException e) {
			System.err.printf("Erro ao abrir arquivo a ser escrito: %s.\n",e.getMessage());
		}
	}

	
	public void closeFile() {
		try {			
			this.file.close();
		}
		catch(IOException e) {
			System.err.printf("Erro ao fechar arquivo que está sendo baixado baixado: %s.\n",e.getMessage());
		}
	}
	
	public byte[] getPecaById(int id) {
		
		byte[] peca = new byte[256];
		
		try {
			if(this.file.getFD().valid()) {
				
				this.resetFilePointer();
				
				this.setFilePointer(id);
				
				this.file.read(peca);
			}
		}
		catch(IOException e) {
			System.err.printf("Erro ao fechar arquivo que está sendo baixado baixado: %s.\n",e.getMessage());
		}
		
		return peca;
	}
	
	
	private void setFilePointer(int id) {
		try {
			if(this.file.getFD().valid()) {
				
				int sizeOfPeca = 256;
				
				int position =  sizeOfPeca * id;
						
				this.file.seek(position);
			}
		}
		catch(IOException e) {
			System.err.printf("Erro ao setar ponteiro do arquivo que está sendo baixado baixado: %s.\n",e.getMessage());
		}
	}
	
	private void resetFilePointer() {
		try {
			if(this.file.getFD().valid()) {
				
				this.file.seek(0);
			}
		}
		catch(IOException e) {
			System.err.printf("Erro ao resetar ponteiro do arquivo que está sendo baixado baixado: %s.\n",e.getMessage());
		}
	}

	public void write(int id, byte[] peca) {

		try
		{
			if(this.file.getFD().valid()) {

				setFilePointer(id);
				this.file.write(peca);
			}
		}
		catch(IOException e) {
			System.err.printf("Erro ao escrever no arquivo: %s.\n",e.getMessage());
		}
	}
}
