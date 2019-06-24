package client.action;

import client.Client;
import common.ListenerTorrent;
import common.TransmissionObject;

import java.util.ArrayList;

public class Analyze extends Thread{

    //constructor
    private TransmissionObject transmissionObject;

    public Analyze(TransmissionObject transmissionObject) {
        this.transmissionObject = transmissionObject;
    }


    /**Nessa classe deve ser analisado quais são as partes de interresse e pedir pra
     * cliente solicitar*/


    @Override
    public void run() {
        Client client = Client.getInstance();

        ArrayList<Integer> parts = client.avaliableParts(this.transmissionObject.getParts());

        if(parts != null){
            this.transmissionObject.setOperation(ListenerTorrent.TRANSMISSION);
            this.transmissionObject.setParts(parts);

            client.send(transmissionObject);
        }
    }
}
