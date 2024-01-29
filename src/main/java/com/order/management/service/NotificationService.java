package com.order.management.service;

import com.order.management.model.payment.PaymentEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Service
public class NotificationService {

    private final BlockingQueue<PaymentEvent> eventQueue = new LinkedBlockingQueue<>();

    /**
     * notificationService.addEvent(new PaymentEvent("RazorPay", success, callbackUrl));
     * notificationService.addEvent(new PaymentEvent("PayU", success, callbackUrl));
     */
    @Async
    public void processEvents() {
        try {
            while (true) {
                PaymentEvent event = eventQueue.take();
                // Simulate processing or send notifications as needed
//                System.out.println("Notification: Payment " + (event.success() ? "success" : "failure") +
//                        " for provider " + event.providerName());
//
//                // Notify the external system via callback
//                sendCallbackWithRetry(event.callbackUrl(), event);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void addEvent(PaymentEvent event) {
        eventQueue.offer(event);
    }

    private void sendCallback(String callbackUrl, PaymentEvent event) {
        // Add logic to send the callback to the external system
        System.out.println("Sending callback to: " + callbackUrl);
        // You may use WebClient, RestTemplate, or other HTTP clients to make the callback request
        // Sample code (using WebClient):
        /*
        WebClient.create(callbackUrl)
                .post()
                .body(BodyInserters.fromValue(event))
                .retrieve()
                .toBodilessEntity()
                .block();
        */
    }

    private void sendCallbackWithRetry(String callbackUrl, PaymentEvent event) {
        int maxRetries = 3;
        int retryDelaySeconds = 10;

        for (int retryCount = 0; retryCount < maxRetries; retryCount++) {
            try {
                sendCallback(callbackUrl, event);
                // If successful, break out of the loop
                break;
            } catch (Exception e) {
                System.out.println("Callback failed. Retrying in " + retryDelaySeconds + " seconds...");
                sleep(retryDelaySeconds);
            }
        }
    }

    private void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}

