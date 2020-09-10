package mackansw.tool;

import javax.swing.*;
import java.awt.*;

public class GuiSpecs {

    private JFrame window;
    private JPanel basePanel;
    private JTextArea output;
    private JButton copy;

    private Color foreground = Color.white;
    private Color background = Color.darkGray;

    private Specs specs;

    private int fontSize = 15;

    /**
     * Constructor with Specs parameter
     * @param specs the specs object
     */
    public GuiSpecs(Specs specs) {
        this.specs = specs;
        setupGui();
    }

    /**
     * Creates and shows the GUI
     */
    private void setupGui() {
        window = new JFrame("Awesome specs tool!");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        basePanel = new JPanel();
        window.add(basePanel);
        basePanel.setBackground(this.background);
        basePanel.setLayout(new BorderLayout());

        output = new JTextArea();
        basePanel.add(output, BorderLayout.CENTER);
        output.setFont(new Font("Monospaced", Font.PLAIN, fontSize));
        output.setForeground(this.foreground);
        output.setBackground(this.background);
        output.setEditable(false);

        output.append(specs.getOSInformation() + "\n \n");
        output.append(specs.getCPUInformation() + "\n \n");
        output.append(specs.getGPUInformation() + "\n");
        output.append(specs.getRAMInformation() + "\n");
        output.append(specs.getStorageInformation());

        copy = new JButton("Copy");
        basePanel.add(copy, BorderLayout.SOUTH);
        copy.setForeground(this.foreground);
        copy.setBackground(this.background.brighter().brighter());
        copy.setPreferredSize(new Dimension(100,40));
        copy.addActionListener(e -> {
            output.selectAll();
            output.copy();
            showMessageDialog("Copied specs to clipboard!");
        });

        //Packs the window after specs are appended to center the GUI correctly
        window.pack();
        window.setSize(window.getWidth() + 15, window.getHeight());
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    /**
     * Creates and shows a themed dialog message
     * @param message the message to display
     */
    private void showMessageDialog(String message) {
        UIManager.put("Panel.background", this.background);
        UIManager.put("Button.background", this.background.brighter().brighter());
        UIManager.put("Button.foreground", this.foreground);
        UIManager.put("OptionPane.messageForeground", this.foreground);
        UIManager.put("OptionPane.background", this.background);
        JOptionPane.showMessageDialog(this.window, message);
    }
}