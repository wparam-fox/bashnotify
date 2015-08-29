package com.wparam.bashnotify;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.app.Service;
import com.wparam.bashnotify.Q;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class LockSuppress extends Activity implements OnClickListener
{
	@Override
	public void onCreate (Bundle savedInstanceState)
	{
		setTitle (getText (R.string.activity_title));
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button button;
		button = (Button)findViewById(R.id.serviceon);
		button.setOnClickListener(this);
		button = (Button)findViewById(R.id.serviceoff);
		button.setOnClickListener(this);

		// Took this out because it results in an odd looking
		// zoom-flash thing.  Just have a basic activity with two
		// buttons on it for starting/stopping
		//startService (new Intent (this, LockService.class));
		//finish ();
	}

	private boolean unlockkey = false;
	@Override
	public void onStop ()
	{
		super.onStop ();
		if (unlockkey)
		{
			// I probably don't need to set this to false.  But
			// I *DO* need to call system.exit.  For some reason
			// android won't release my keyguard unlock until
			// my process is completely dead.  I dunno if it's a
			// bug in android or if I'm just dumb and don't get
			// it (probably the latter), but I'm sick of messing
			// with it and just want it to work.  This does the
			// job.
			unlockkey = false;
			System.exit (0);
		}
	}

	private void service_go ()
	{
		startService (new Intent (this, LockService.class));
		finish ();
	}

	private void service_stop ()
	{
		unlockkey = true;
		stopService (new Intent (this, LockService.class));
		finish ();
	}


	public void onClick (View v)
	{
		switch (v.getId ())
		{
		case R.id.serviceon:
			service_go ();
			break;

		case R.id.serviceoff:
			service_stop ();
			break;
		}
	}
}


