package it.rajapaksa;

public class App {
    public static void main(String[] args) {
        classe client = new classe();// istanza client
        client.connetti();// connessione al server
        client.comunica();
    }
}
