package Initial;

import client.Client;
import client.JJorgeClient;
import client.TorrentFile;
import common.ListenJJorge;
import common.ListenerTorrent;
import server.JJorgeServer;
import server.Server;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class jTorrent {

  public static void main(String args[]){

    //Inicializando Operadores
    Client client = Client.getInstance();
    Server server = Server.getInstance();


    //Essa deve ser a entrada de uma arquivo .torrent
//    Scanner leitor = new Scanner(System.in);
//    System.out.print("Digite uma String: ");
//    String arquivo = leitor.nextLine();
//    System.out.println(arquivo);
    
    try {
        JFileChooser abrirTorrent = new JFileChooser();
        abrirTorrent.showOpenDialog(null);
        File file = abrirTorrent.getSelectedFile();
//        System.out.println("Arquivo selecionado: "+file.getPath());
        
        TorrentFile f = new TorrentFile("/home/geovanne/Documents/jtorrent/torrent");
        
        /*ArrayList<Integer> parts = server.findOrNullParts(f.getNameTorrent());
        
        for (Integer integer : parts) {
			System.out.println(integer);
		}*/
        
        //Passa o caminho do .torrent para o cliente
        client.searchMachines(file.getPath());

    } 
    catch (Exception ex) {
        ex.printStackTrace();
        System.out.println(ex.getMessage());
    }


    

  }


}
