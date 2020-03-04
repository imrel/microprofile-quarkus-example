package org.kostenko.microprofile.quarkus.example.restclient.push;

/**
 *
 * @author kostenko
 */
public class PushMessage {

    public String to;
    public Notification notification;

    public PushMessage() {
    }

    public PushMessage(String to, String title, String body) {
        Notification notification = new Notification();
        notification.title = title;
        notification.body = body;
        this.to = to;
        this.notification = notification;
    }

    public static class Notification {
        public String title;
        public String body;
    }
}
