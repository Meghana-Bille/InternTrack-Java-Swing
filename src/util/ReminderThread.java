package util;

import model.Application;

import java.util.ArrayList;

public class ReminderThread extends Thread {

    private ArrayList<Application> applications;
    private boolean running;

    public ReminderThread(
            ArrayList<Application> applications) {

        this.applications = applications;
        this.running = true;

        // Allows the application to close normally
        // even if this thread is still running.
        setDaemon(true);
    }

    @Override
    public void run() {

        while (running) {

            checkInterviewReminders();

            try {

                Thread.sleep(10000);

            } catch (InterruptedException e) {

                running = false;
            }
        }
    }

    private void checkInterviewReminders() {

        for (
                Application application :
                applications
        ) {

            if (
                    application
                            .getStatus()
                            .equalsIgnoreCase(
                                    "Interview"
                            )
            ) {

                System.out.println(
                        "[CAREER REMINDER] "
                        + "Interview stage reached for "
                        + application.getRole()
                        + " at "
                        + application.getCompanyName()
                );
            }
        }
    }

    public void stopReminder() {

        running = false;

        interrupt();
    }
}