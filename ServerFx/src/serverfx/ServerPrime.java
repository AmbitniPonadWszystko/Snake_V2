package serverfx;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ServerPrime {

    private final static int sizeWidth = 120;
    private final static int sizeHeight = 60;
    static Server server;
    static BarrierType[][] mask = new BarrierType[sizeWidth][sizeHeight];
    TextField portTextField;
            
    public ServerPrime(TextField portTextField, Label serverLabel, Button okButton) {
        this.portTextField = portTextField;
        server = new Server();
        registerPackets();
        server.addListener(new NetworkListener());
        try {
            //To bind the server to the ports, 
            //we simply call the bind method within our server object.
            //The order of ports is TCP, followed by UDP. 
            server.bind(Integer.parseInt(portTextField.getText()), Integer.parseInt(portTextField.getText()));
            server.start();
            serverLabel.setText("Serwer działa....");
            serverLabel.setVisible(true);
            okButton.setDisable(true);

        } catch (IOException ex) {
            serverLabel.setText("   Port zajęty");
            serverLabel.setVisible(true);
            System.out.println("Wrong Port");
        }
        initBoard();

    }

    //init our mask Board with values giving in Enum BarrierType
    public static void initBoard() {
        for (int x = 0; x < sizeWidth; x++) {
            for (int y = 0; y < sizeHeight; y++) {
                if (x == 0 || x == sizeWidth - 1 || y == 0 || y == sizeHeight - 1) {
                    mask[x][y] = BarrierType.WALL;
                } else {
                    mask[x][y] = BarrierType.EMPTY;
                }
            }
        }
    }

    private void registerPackets() {
        // Some type of Serializer which encodes info to readable thing
        // something that can be send over network
        // all packets needs to be registered
        Kryo kryo = server.getKryo();
        kryo.register(Packet.PacketLoginRequested.class);
        kryo.register(Packet.PacketLoginAccepted.class);
        kryo.register(Packet.PacketMessage.class);
        kryo.register(Packet.PacketPoint.class);
        kryo.register(Packet.PacketPointAccepted.class);
        kryo.register(Packet.PacketPointRefused.class);
        kryo.register(Packet.PacketNames.class);
        kryo.register(Packet.PacketDead.class);
        kryo.register(Packet.PacketAddPlayer.class);
        kryo.register(Packet.PacketHead.class);
        kryo.register(Packet.PacketNewTour.class);
        kryo.register(Packet.PacketReadyPlayer.class);
        kryo.register(Packet.PacketStart.class);
        kryo.register(Packet.PacketEndGame.class);
        kryo.register(Packet.PacketWantAgain.class);
        kryo.register(Packet.PacketNotWantAgain.class);
        kryo.register(Packet.PacketExit.class);
    }



}
