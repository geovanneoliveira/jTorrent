package Initial;

import client.Client;
import client.TorrentMaker;
import server.Server;

import java.io.File;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class jTorrent {

  public static void main(String args[]){

    //Inicializando Operadores
	  Client client = Client.getInstance();
	  Server server = Server.getInstance();

    try {
    	userInterface();
    } 
    catch (Exception ex) {
        ex.printStackTrace();
        System.out.println(ex.getMessage());
    }
  }
  
  public static void userInterface() {
	  
	    Scanner scan = new Scanner(System.in);
	    
	    System.out.print("1 - Baixar arquivo\n");
	    System.out.print("2 - Gerar arquivo .torrent\n");
	    
	    try {
	    	int option = scan.nextInt();
	    	scan.close();
	    	switch (option) {
			case 1:
				downloadTorrent();
				break;
			case 2:
				makeTorrent();
				break;	
			default:
				throw new Exception("opção escolhida inválida");
			}
	    }
	    catch(Exception e) {
	    	System.out.println(e.getMessage());
	    }  
	  }
  
  public static void downloadTorrent() {
    JFileChooser abrirTorrent = new JFileChooser(); 
    abrirTorrent.showOpenDialog(null);
    File file = abrirTorrent.getSelectedFile();
    System.out.println("Arquivo selecionado para download: "+file.getPath());
    
    Client client = Client.getInstance();
    
    client.searchMachines(file.getPath());
  }
  
  public static void makeTorrent() {
  	JFileChooser abrirTorrent = new JFileChooser(); 
    abrirTorrent.showOpenDialog(null);
    File file = abrirTorrent.getSelectedFile();
    
    System.out.println("Arquivo selecionado para gerar torrent: "+file.getPath()+"\n");
    
    //int size = selectSizeOfPeca();  ta dando erro nessa função, resolver depois, n é urgente
    
    TorrentMaker torrentMaker = new TorrentMaker(file.getPath());
    
    torrentMaker.makeTorrent();
  }
  
	public static int selectSizeOfPeca() {
		System.out.println("Selecione o tamanho das peças do arquivo:\n");
		System.out.println("1 - 256kb\n2 - 512kb\n3 - 1mb\n");

		Scanner scanner = new Scanner(System.in);
		
		int size = 0;
		int option = scanner.nextInt();
		
		try {
			switch (option) {
			case 1:
				size =  256;
			case 2:
				size =  512;
			case 3:
				size =  1024;
			default:
				throw new Exception("Opção digitada inválida");
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return size;
	}


}
