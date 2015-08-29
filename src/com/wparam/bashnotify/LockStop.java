package com.wparam.bashnotify;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.app.Service;
import com.wparam.bashnotify.Q;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class LockStop extends Activity
{
	private boolean unlockkey = false;

	@Override
	public void onStart () //(Bundle savedInstanceState)
	{
		super.onStart ();

		unlockkey = true;
		stopService (new Intent (this, LockService.class));
		finish ();
	}

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

}


