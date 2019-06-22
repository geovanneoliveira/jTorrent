package common;

import Initial.jTorrent;
import client.action.Verify;
import server.action.Process;

public class ListenerTorrent implements ListenJJorge {

    public static final char DISCOVER = '1';
    public static final char TRANSMISSION = '2';

    //Singleton
    private static ListenerTorrent instance;

    public static synchronized ListenerTorrent getInstance(){
        return (instance == null)? instance = new ListenerTorrent() : instance;
    }

    private ListenerTorrent() {}


    /* obs.:
    * Possa ser que dps um teste para que a mesma maquina não receba requesição dela mesma durante
    * o UDP send, mas caso bloquei o IP não dá pra fazer teste na mesma maquina
    * */


    @Override
    public void clientReceive(TransmissionObject transmissionObject) {

        System.out.println( "\nRecebendo Req.Cliente de: " + transmissionObject.getIPServer());

        if(transmissionObject.getOperation() == ListenerTorrent.DISCOVER) {

            new client.action.Analyze(transmissionObject).start();


        } else if (transmissionObject.getOperation() == ListenerTorrent.TRANSMISSION) {

            new Verify(transmissionObject).start();
        }

    }

    @Override
    public void serverReceive(TransmissionObject transmissionObject) {

        System.out.println("\nRecebendo Req.server.Server de: " + transmissionObject.getIPClient());

        if (transmissionObject.getOperation() == ListenerTorrent.DISCOVER) {

            new server.action.Analyze(transmissionObject).start();

        } else if (transmissionObject.getOperation() == ListenerTorrent.TRANSMISSION) {

            new Process(transmissionObject).start();
        }
    }
}
