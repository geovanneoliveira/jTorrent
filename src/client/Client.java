package client;

import common.ListenJJorge;
import common.ListenerTorrent;
import common.TransmissionObject;

import java.util.ArrayList;

public class Client {

    //Singleton
    private static Client instance;
    private JJorgeClient jJorgeClient;

    public static synchronized Client getInstance() {
        return (instance == null) ? instance = new Client() : instance;
    }

    private Client() {
        ListenJJorge listenJJorge = ListenerTorrent.getInstance();
        this.jJorgeClient = new JJorgeClient(listenJJorge);
    }


    /*
     * Funções para funcionamento
     *
     * */

    public void send(TransmissionObject obj) {

        jJorgeClient.send(obj);
    }

    public void searchMachines(String arquivo) {

        TransmissionObject obj = arquivoParaObjeto(arquivo);

        this.jJorgeClient.discoverMachines(obj);
    }


    /*como é String não há conversão pesada.. se for o arquivo tem que abrir e ler para popular
    o objeto de transmissão*/
    private TransmissionObject arquivoParaObjeto(String nome) {

        TransmissionObject obj = new TransmissionObject();
        obj.setArchiveName(nome);
        obj.setOperation('1');
        return obj;
    }

    public ArrayList<Integer> avaliableParts(ArrayList<Integer> serverParts) {

        ArrayList<Integer> res = null;

        //Consultar em um array na memoria [array com Syncronized](que sera uma fog do arquivo com as partes)
        //procurar um bloco de pecas que esteja disponivel e o serverParts contenha

       /* Exemplo a usar

       serverParts.contains(1);

       ou

       serverParts.containsAll(); <- vide os paramentros necessarios
       */

        return res;
    }

    public boolean isPecaAuthentic(byte[] peca) {

        /*Fazer a hash (Mais isso é no futuro)
        * utilizar um bcrypt ou MD5 criando mais duas funcoes em algum lugar
        * pois tbm será usada pra gerar o torrent.*/
        //Para veiculo de teste não faz isso agora

        return true;
    }

    public void writeInDisk(int idPeca, byte[] peca) {

        //Função para leitura do arquivo (o arquivo já pode estar aberto fora desse escopo)
        //gravar vai precisar do (id * byte) = posição
        //e write no arquivo (feito já tinhamos feito um exemplo)

    }

}
