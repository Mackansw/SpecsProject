package mackansw.tool;

import oshi.SystemInfo;

public class Main {

    /**
     * Main method
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        SystemInfo systemInfo = new SystemInfo();
        Specs specs = new Specs(systemInfo);

        if(args.length > 0 && args[0].equals("nogui")) {
            new CliSpecs(specs);
            return;
        }

        new GuiSpecs(specs);
    }
}