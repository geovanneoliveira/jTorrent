package server;


import common.FileDownload;
import common.ListenJJorge;
import common.ListenerTorrent;
import common.TransmissionObject;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Server {

    //Singleton
    private static Server instance;
    private JJorgeServer jJorgeServer;
    private FileDownload fileDownload;

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

    public ArrayList<Integer> findOrNullParts(String name) {

        ArrayList<Integer> res = new ArrayList<Integer>();
        String linha = null;

        try {
        	
        	String nameInfo = name.split("\\.")[0]+".info";
        	
            File f = new File("/home/geovanne/Documents/jtorrent/"+nameInfo);
                     
            if(f.exists()) {
            	RandomAccessFile file = new RandomAccessFile(f,"r");
            	
            	this.initFileDownload(name);
            	
            	while((linha = file.readLine()) != null) {
            		linha.replace("\n","");
            		res.add(Integer.parseInt(linha));
            	}
            	file.close();
            }
 
        }
        catch (Exception e) {
            System.out.println("Error Server::Class->findOrNullParts");
            e.printStackTrace();
        }

        return res;
    }
    

//    private ArrayList<Integer> splitParts(String str)
//    {
//        ArrayList<Integer> res = new ArrayList<Integer>();
//
//        if(!(str == null)){
//            for (String s : str.split(",")) {
//               res.add(Integer.parseInt(s));
//            }
//        }
//
//        return (res.isEmpty()) ? null : res ;
//    }
     
    public byte[] getPecaById(int idPeca) {   
        return this.fileDownload.getPecaById(idPeca);
    }

    private void initFileDownload(String name) {
    	String path = "/home/geovanne/Documents/jtorrent/"+name;
    	this.fileDownload = new FileDownload(path);
    }
    
    public void openFileDownload() {
    	this.fileDownload.openFile();
    }
    
    public void closeFileDownload() {
    	this.fileDownload.closeFile();
    }
}