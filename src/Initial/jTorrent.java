package Initial;

import client.Client;
import client.JJorgeClient;
import common.ListenJJorge;
import common.ListenerTorrent;
import server.JJorgeServer;
import server.Server;

import java.util.Scanner;

public class jTorrent {

  public static void main(String args[]){

    //Inicializando Operadores
    Client client = Client.getInstance();
    Server server = Server.getInstance();


    //Essa deve ser a entrada de uma arquivo .torrent
    Scanner leitor = new Scanner(System.in);
    System.out.print("Digite uma String: ");
    String arquivo = leitor.nextLine();
    System.out.println(arquivo);

    /* *
     * Como só tem uma opção pula pra busca
     *
     * caso contrario aqui entraria um if/switch
     * */

    //Passa o .torrent para o cliente. (como não tem, vai a string)
    client.searchMachines(arquivo);

  }


}
