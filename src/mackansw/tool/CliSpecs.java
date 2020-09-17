package mackansw.tool;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.Scanner;

public class CliSpecs {

    private Specs specs;

    private String specsOutput = "\n";

    /**
     * Constructor with Specs parameter
     * @param specs the specs object
     */
    public CliSpecs(Specs specs){
        this.specs = specs;
        setupCli();
    }

    //Creates and shows the CLI
    private void setupCli() {
        appendSpecs(specs.getOSInformation() + "\n \n");
        appendSpecs(specs.getCPUInformation() + "\n \n");
        appendSpecs(specs.getGPUInformation() + "\n");
        appendSpecs(specs.getRAMInformation() + "\n");
        appendSpecs(specs.getStorageInformation());

        //Prints specs to console
        System.out.println(this.specsOutput);

        Scanner input = new Scanner(System.in);
        String command = "";
        boolean running = true;

        System.out.println("Type 'help' for available commands");
        while(running) {
            command = input.next();
            switch(command) {
                case "copy":
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(this.specsOutput), null);
                    System.out.println("Copied specs to clipboard!");
                    break;
                case "help":
                    System.out.println("Type 'copy' to copy specs to clipboard");
                    System.out.println("Type 'exit' to exit program");
                    break;
                case "exit":
                    running = false;
                    break;
                default:
                    System.out.println("Type 'help' for available commands");
            }
        }
    }

    /**
     * Appends text to specs
     * @param text the text to append
     */
    private void appendSpecs(String text) {
        this.specsOutput = this.specsOutput + text;
    }
}