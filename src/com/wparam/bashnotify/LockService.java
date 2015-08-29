package com.wparam.bashnotify;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.app.PendingIntent;
import android.app.Service;
import android.os.IBinder;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;
import android.app.NotificationManager;
import android.app.Notification;

public class LockService extends Service
{
	@Override
	public int onStartCommand (Intent intent, int flags, int startid)
	{
		/*Q q = new Q (getApplicationContext ());
		q.i ("onStartCommand()");*/

		// Just disable the screen lock and display the notify dealie
		screenlock (false);
		showNotification ();

		return START_STICKY;
	}

	public void onDestroy ()
	{
		/* Q q = new Q (getApplicationContext ());
		q.i ("onDestroy()"); */

		// Opposite of start
		screenlock (true);
		hideNotification ();
	}

	@Override
	public IBinder onBind (Intent intent) 
	{
		//We do not work this way.
		return null;
	}

	private void screenlock (boolean b)
	{
		KeyguardManager km = (KeyguardManager)getSystemService(
				Activity.KEYGUARD_SERVICE);
		Q q = new Q (getApplicationContext ());

		if (km == null)
		{
			q.tp ("Fail: Couldn't get key manager");
			return;
		}

		KeyguardLock lock = km.newKeyguardLock("LockSuppressor");
		if (lock == null)
		{
			q.tp ("Fail: Couldn't get keyguard lock");
			return;
		}

		if (b)
		{
			lock.disableKeyguard();
			lock.reenableKeyguard();
			q.i ("Lock re-enabled");
		}
		else
		{
			lock.disableKeyguard();
			q.i ("Lock disabled");
		}

		lock = null;
		km = null;
	}


	private void showNotification() 
	{
		CharSequence text = getText (R.string.enabled_msg);

		Notification notification = new Notification (R.drawable.icon,
			text,
			System.currentTimeMillis());

		PendingIntent contentIntent = PendingIntent.getActivity (this, 
			0,
			new Intent(this, LockStop.class), 
			0);

		notification.flags |= Notification.FLAG_ONGOING_EVENT;
		notification.flags |= Notification.FLAG_NO_CLEAR;

		notification.setLatestEventInfo(this, text,
				getText(R.string.enabled_msg2),
				contentIntent);

		// Send the notification.
		// We use a layout id because it is a unique number.
		// We use it later to cancel.
		String ns = Context.NOTIFICATION_SERVICE;
		NotificationManager nm =
			(NotificationManager)getSystemService(ns);

		nm.notify(R.string.notify_id, notification);
	}

	private void hideNotification ()
	{
		String ns = Context.NOTIFICATION_SERVICE;
		NotificationManager nm =
			(NotificationManager)getSystemService(ns);
		nm.cancel (R.string.notify_id);
	}
}

