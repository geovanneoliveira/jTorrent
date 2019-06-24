package server;


import common.ListenJJorge;
import common.ListenerTorrent;
import common.TransmissionObject;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

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

    public ArrayList<Integer> findOrNullParts(String nome) {

        ArrayList<Integer> res = null;
        String linha = null;

        try {
            File f = new File("/home/geovanne/Documents/jtorrent/myTorrents");
            RandomAccessFile file = new RandomAccessFile(f,"r");
                        
            while((linha = file.readLine()) != null) {
            	if(nome.equals(linha)) {
            		linha = file.readLine();
            		break;
            	}		
            }
            file.close();
        }
        catch (Exception e) {
            System.out.println("Error Server::Class->findOrNullParts");
            e.printStackTrace();
        }
      
        res = splitParts(linha);

        return res;
    }

    private ArrayList<Integer> splitParts(String str)
    {
        ArrayList<Integer> res = new ArrayList<Integer>();

        if(!(str == null)){
            for (String s : str.split(",")) {
               res.add(Integer.parseInt(s));
            }
        }

        return (res.isEmpty()) ? null : res ;
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