package org.kostenko.microprofile.quarkus.example.push;

/**
 *
 * @author kostenko
 */
public class PushMessage {

    public String to;
    public PushNotification notification;

    public static class PushNotification {

        public String title;
        public String body;
    }
}
