package server;


import common.ListenJJorge;
import common.ListenerTorrent;
import common.TransmissionObject;

import java.util.ArrayList;

public class Server {

    //Singleton
    private static Server instance;
    private JJorgeServer jJorgeServer;

    public static synchronized Server getInstance(){
        return (instance == null)? instance = new Server() : instance;
    }

    private Server() {
        ListenJJorge listenJJorge = ListenerTorrent.getInstance();
        this.jJorgeServer = new JJorgeServer(listenJJorge);
    }


    /*
     * Funções para funcionamento
     *
     * */

    public void send(TransmissionObject obj) {

        jJorgeServer.send(obj);
    }

    public ArrayList<Integer> findOrNullPecas(String nome) {

        ArrayList<Integer> res = null;

        //Função para leitura do arquivo (o arquivo já pode estar aberto fora desse escopo)
        //Se existir o arquivo pegue as pecas

        return res;
    }

    public byte[] getPecaById(int idPeca) {

        byte[] res = null;

        //Função para leitura do arquivo (o arquivo já pode estar aberto fora desse escopo)
        //e retornar a peca da posição.
        //Dica. idPeca é o numero  ====> ex.: peça numero 3.
        //Achar a posição sera 3 * default do tamanho do byte
        //Criar uma const pra isso.
        //e retornar.
        //ps. Já que disse que tinha não é pra voltar Null, pois vai ficar faltando no cliente


        return res;
    }

}
