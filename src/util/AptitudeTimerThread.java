package util;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class AptitudeTimerThread extends Thread {

    private int remainingSeconds;

    private JLabel timerLabel;

    private Runnable timeUpAction;

    private boolean running;

    public AptitudeTimerThread(
            int totalSeconds,
            JLabel timerLabel,
            Runnable timeUpAction) {

        this.remainingSeconds = totalSeconds;
        this.timerLabel = timerLabel;
        this.timeUpAction = timeUpAction;
        this.running = true;

        // Allows the application to close normally
        // even if the timer thread is still running.
        setDaemon(true);
    }

    @Override
    public void run() {

        while (running && remainingSeconds >= 0) {

            int minutes =
                    remainingSeconds / 60;

            int seconds =
                    remainingSeconds % 60;

            String timeText =
                    String.format(
                            "Time Remaining: %02d:%02d",
                            minutes,
                            seconds
                    );

            // Swing components should be updated
            // on the Event Dispatch Thread.
            SwingUtilities.invokeLater(
                    () -> timerLabel.setText(timeText)
            );

            if (remainingSeconds == 0) {

                if (timeUpAction != null) {

                    SwingUtilities.invokeLater(
                            timeUpAction
                    );
                }

                break;
            }

            try {

                Thread.sleep(1000);

            } catch (InterruptedException e) {

                running = false;
            }

            remainingSeconds--;
        }
    }

    public void stopTimer() {

        running = false;

        interrupt();
    }

    public int getRemainingSeconds() {

        return remainingSeconds;
    }
}