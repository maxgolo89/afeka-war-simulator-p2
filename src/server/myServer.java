package server;

import bl.WarModel;
import program.IConstants;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class myServer extends Thread{

    public void run(){
        try {
            ServerSocket server = new ServerSocket(4000);
            System.out.println("Starting server on port 4000....");

            while(true){
                final Socket socket = server.accept();

                new Thread(() -> {
                    WarModel warModel = WarModel.getInstance();
                    System.out.println("Client connected from " + socket.getInetAddress() + ":" + socket.getPort());
                    try {
                        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                        PrintStream outputStream = new PrintStream(socket.getOutputStream());
                        outputStream.println("Welcome to server!");
                        String line = "";
                        while (!line.equals("goodbye")){
                            line = inputStream.readUTF();
                            switch (line){
                                case "addMissileLauncher":
                                    String launcherId = inputStream.readUTF();
                                    boolean isHidden = inputStream.readBoolean();
                                    warModel.addMissileLauncher(launcherId,isHidden);
                                    System.out.println("Missile Launcher id: " + launcherId + ", isHidden?: " + isHidden);
                                    break;
                                case "addLauncherDestructor":
                                    String destructorId = inputStream.readUTF();
                                    String destType = inputStream.readUTF();
                                    IConstants.LauncherDestructorType type;
                                    if(destType.equals("Ship"))
                                        type = IConstants.LauncherDestructorType.SHIP;
                                    else
                                        type = IConstants.LauncherDestructorType.PLANE;
                                    warModel.addLauncherDestructor(destructorId,type);
                                    System.out.println("Launcher Destructor id: " + destructorId + ", type: " + type);
                                    break;
                                case "addMissileDestructor":
                                    String missileDestructorId = inputStream.readUTF();
                                    warModel.addMissileDestructor(missileDestructorId);
                                    System.out.println("Missile Destructor id: " + missileDestructorId);
                                    break;
                                case "launchMissile":
                                    String missileId = inputStream.readUTF();
                                    String launchId = inputStream.readUTF();
                                    String destination = inputStream.readUTF();
                                    int flyTime = Integer.parseInt(inputStream.readUTF());
                                    int damage = Integer.parseInt(inputStream.readUTF());
                                    warModel.addMissileLaunch(launchId,missileId,damage,destination,flyTime);
                                    break;
                                case "destructMissile":
                                    String destMissileId = inputStream.readUTF();
                                    String missDestId = inputStream.readUTF();
                                    warModel.addMissileDestruct(missDestId,destMissileId);
                                    break;
                                case "destructLauncher":
                                    String destLauncherId = inputStream.readUTF();
                                    String launchDestId = inputStream.readUTF();
                                    warModel.addLauncherDestruct(launchDestId,destLauncherId);
                                    break;
                            }
                        }
                        System.out.println("Client " + socket.getInetAddress() + ":" + socket.getPort() + " disconnected");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
