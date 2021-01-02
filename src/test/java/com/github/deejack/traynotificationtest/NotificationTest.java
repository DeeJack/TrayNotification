package com.github.deejack.traynotificationtest;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import org.junit.*;

import com.github.deejack.traynotification.animations.Animations;
import com.github.deejack.traynotification.notification.Notification;
import com.github.deejack.traynotification.notification.Notifications;
import com.github.deejack.traynotification.notification.TrayNotification;

import java.util.concurrent.CountDownLatch;

import javax.swing.SwingUtilities;

public final class NotificationTest {

	@BeforeClass
	public static void initializeJavaFX() throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(1);
		SwingUtilities.invokeLater(() -> {
			new JFXPanel(); // initializes JavaFX environment
			latch.countDown();
		});

		latch.await();
	}

	@AfterClass
	public static void shutdownJavaFX() {
		System.out.println("Exiting");
		Platform.exit();
	}

	@Before
	public void initializeTray() {
	}

	@Test
	public void creatingANewTrayNotification() {
		String title = "Congratulations sir";
		String message = "You've successfully created your first Tray Notification";
		Notification notification = Notifications.SUCCESS;

		Platform.runLater(() -> {
			var tray = new TrayNotification();
			tray.setTitle(title);
			tray.setMessage(message);
			tray.setNotification(notification);
			tray.showAndWait();
		});
		pause(2000);
	}

	@Test
	public void usingDifferentAnimationsAndNotifications() {
		String title = "Download quota reached";
		String message = "Your download quota has been reached. Panic.";
		Notification notification = Notifications.NOTICE;

		Platform.runLater(() -> {
			var tray = new TrayNotification();
			tray.setTitle(title);
			tray.setMessage(message);
			tray.setNotification(notification);
			tray.setAnimation(Animations.FADE);
			tray.showAndWait();
		});
		pause(2000);
	}

	@Test
	public void creatingACustomTrayNotification() {
		Image whatsAppImg = new Image("https://cdn4.iconfinder.com/data/icons/iconsimple-logotypes/512/whatsapp-128.png");

		Platform.runLater(() -> {
			var tray = new TrayNotification();
			tray.setTitle("New WhatsApp Message");
			tray.setMessage("Github - I like your new notification release. Nice one.");
			tray.setRectangleFill(Paint.valueOf("#2A9A84"));
			tray.setAnimation(Animations.POPUP);
			tray.setImage(whatsAppImg);
			tray.showAndDismiss(Duration.seconds(2));
		});
		pause(2000);
	}

	private void pause(int millis) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
