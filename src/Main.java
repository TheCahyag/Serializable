/**
 * File: Main.java
 * Main: TODO
 */

/**
 * Main TODO
 * @author Brandon Bires-Navel (brn5915@rit.edu)
 */
public class Main {
    public static void main(String[] args) {
        RunGUI runGUI = new RunGUI();
        Thread GUI = new Thread(runGUI);
        GUI.start();
    }
}
