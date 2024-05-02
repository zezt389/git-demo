/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

/**
 *
 * @author 100ti
 */
import java.net.*;
import java.io.*;

public class TCPClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket();
            InetAddress serverIP = InetAddress.getByName("localhost");
            int serverPort = 8888;

            // Get input from user
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter two numbers separated by comma (e.g., 12,34): ");
            String input = userInput.readLine();

            byte[] sendData = input.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIP, serverPort);
            socket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            String[] result = response.split(",");
            int gcd = Integer.parseInt(result[0]);
            boolean isNum1Prime = Boolean.parseBoolean(result[1]);
            boolean isNum2Prime = Boolean.parseBoolean(result[2]);

            System.out.println("GCD: " + gcd);
            System.out.println("Number 1 is prime: " + isNum1Prime);
            System.out.println("Number 2 is prime: " + isNum2Prime);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}

