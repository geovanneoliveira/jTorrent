package client;

import common.ListenJJorge;
import common.ListenerTorrent;
import common.TransmissionObject;

import java.util.ArrayList;
import java.util.Collection;

public class Client {

    //Singleton
    private static Client instance;
    private JJorgeClient jJorgeClient;
    private TorrentFile torrentFile;
    private ArrayList<Integer> avaliablePecas = null;
    private int count = 0;
    private int range = 0;

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

    public void searchMachines(String filePath) {
    	
    	this.torrentFile = new TorrentFile(filePath);
    	
    	String name = this.torrentFile.getNameTorrent();
    	
        TransmissionObject obj = arquivoParaObjeto(name);

        this.jJorgeClient.discoverMachines(obj);
    }


    /*como é String não há conversão pesada.. se for o arquivo tem que abrir e ler para popular
    o objeto de transmissão*/
    private TransmissionObject arquivoParaObjeto(String nome) {

        TransmissionObject obj = new TransmissionObject();
        obj.setArchiveName(nome);
        obj.setOperation(ListenerTorrent.DISCOVER);
        return obj;
    }

    public synchronized ArrayList<Integer> avaliableParts(ArrayList<Integer> serverParts) {

        ArrayList<Integer> res = null;

        try
        {
            ArrayList<Integer> collection = new ArrayList<>();

            int total = torrentFile.getTotal();
            range = (range == 0) ? (total / 100) : range ;
            initArrayAvaliablePecas(total);

            for(int i = 0; i < range; i++) {

                collection.add(this.avaliablePecas.get(i));
            }

            if(serverParts.containsAll(collection)) {
                res = collection;
                this.avaliablePecas.removeAll(res);
            }

            count += range;

        }
        catch (Exception e)
        {
            System.out.println("Error Client::class->avaliableParts");
        }

       return res;
    }

    private void initArrayAvaliablePecas(int total) {

        if(this.avaliablePecas == null) {

            this.avaliablePecas = new ArrayList<Integer>();

            for(int i = 0; i < total; i++) {
                this.avaliablePecas.add(i);
            }
        }
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