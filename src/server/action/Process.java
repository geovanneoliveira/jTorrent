package server.action;

import common.ListenerTorrent;
import common.TransmissionObject;
import server.Server;

public class Process extends Thread{

    //constructor
    private TransmissionObject transmissionObject;

    public Process(TransmissionObject transmissionObject) {
        this.transmissionObject = transmissionObject;
    }

    /**Nessa classe deve ser feito o processamento e envio de peças*/


    @Override
    public void run() {
        Server server = Server.getInstance();

        this.transmissionObject.setOperation(ListenerTorrent.TRANSMISSION);

        for(int idPeca : this.transmissionObject.getParts()){

            byte[] res = server.getPecaById(idPeca);

            if(res != null){
                this.transmissionObject.setPecas(res);
                this.transmissionObject.setIdPeca(idPeca);
                server.send(this.transmissionObject);
            }
        }
    }
}
