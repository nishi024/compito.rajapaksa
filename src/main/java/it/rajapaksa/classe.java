package it.rajapaksa;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class classe {
    String nomeServer = "localhost";
    int portaServer = 6789;
    Socket miosocket;
    BufferedReader tastiera;
    String stringNomeUtente;
    String stringaRicevutaDalServer;// stringa ricevuta dal server
    DataOutputStream outVersoServer;// stream output
    BufferedReader inDalServer;// stream input

    public Socket connetti() {
        System.out.println(" client in esecuzione...");
        try {
            tastiera = new BufferedReader(new InputStreamReader(System.in)); // input tastiera
            miosocket = new Socket(nomeServer, portaServer); // creazione socket con indirizzo e porta

            outVersoServer = new DataOutputStream(miosocket.getOutputStream()); // istanza per output verso al server
            inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream())); // istanza per input
        } catch (Exception e) {

        }
        return miosocket;
    }


    public void comunica() {
        try {
            System.out.println("" + '\n');
            String stringUtente = tastiera.readLine();
            System.out.println("" + '\n');
            outVersoServer.writeBytes(stringUtente + '\n');

            String stringRicevutaDalServer = inDalServer.readLine();
            System.out.println("" + '\n' + stringRicevutaDalServer);
            System.out.println("");// chiudo la connessione
            miosocket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("errore connessione");
            System.exit(1);
        }
    }



    public static void main(String[] args) throws ParserConfigurationException, SAXException {
        try {
            File file = new File("classe.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);
            document.getDocumentElement().normalize();
            System.out.println("Root Element :" + document.getDocumentElement().getNodeName());
            NodeList nList = document.getElementsByTagName("persone");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("nome : " + eElement.getElementsByTagName("nome").item(0).getTextContent());
                    System.out
                            .println("cognome : " + eElement.getElementsByTagName("cognome").item(0).getTextContent());
                    System.out.println("nazioneDiResidenza : "
                            + eElement.getElementsByTagName("nazioneDiResidenza").item(0).getTextContent());
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
    
    

