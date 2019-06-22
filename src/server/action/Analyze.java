package server.action;

import common.ListenerTorrent;
import common.TransmissionObject;
import server.Server;

import java.util.ArrayList;

public class Analyze extends Thread{

    //constructor
    private TransmissionObject transmissionObject;

    public Analyze(TransmissionObject transmissionObject) {
        this.transmissionObject = transmissionObject;
    }


    /**Nessa classe deve ser verificado se no PC existe o filme e quais partes estão disponiveis*/


    @Override
    public void run() {

        Server server = Server.getInstance();

        ArrayList<Integer> parts =  server.findOrNullPecas(this.transmissionObject.getArchiveName());

        if(parts != null){
            this.transmissionObject.setOperation(ListenerTorrent.DISCOVER);
            this.transmissionObject.setParts(parts);

            server.send(this.transmissionObject);
        }

    }
}
