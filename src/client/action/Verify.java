package client.action;

import client.Client;
import common.TransmissionObject;

public class Verify extends Thread {

    //constructor
    private TransmissionObject transmissionObject;

    public Verify(TransmissionObject transmissionObject) {
        this.transmissionObject = transmissionObject;
    }


    /**Nessa classe deve ser verificado a peca pela hash e gravado em disco*/

    @Override
    public void run() {
        Client client = Client.getInstance();

        if(client.isPecaAuthentic(this.transmissionObject.getPecas())){

            client.writeInDisk(this.transmissionObject.getIdPeca(), this.transmissionObject.getPecas());
        }


    }
}
