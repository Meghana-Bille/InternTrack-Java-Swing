package app;

import javax.swing.SwingUtilities;
import ui.LoginFrame;

public class InternTrackApp {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> new LoginFrame()
        );
    }
}