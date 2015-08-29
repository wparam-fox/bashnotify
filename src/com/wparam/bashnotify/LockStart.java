package com.wparam.bashnotify;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.app.Service;
import com.wparam.bashnotify.Q;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class LockStart extends Activity
{
	@Override
	public void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		startService (new Intent (this, LockService.class));
		finish ();
	}

}


