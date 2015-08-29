package com.wparam.bashnotify;

import android.content.Context;
import android.widget.Toast;
import android.util.Log;

public class Q
{
	public static void test1 ()
	{
		System.out.printf ("blah %i\n");
	}

	public static void toastprint (Context c, String s)
	{
		Context context = c.getApplicationContext();
		CharSequence text = s;

		Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		toast.show();
	}

	private static String doformat (String f, Object... args)
	{
		String out;

		try
		{
			out = String.format (f, args);
		}
		catch (Exception w)
		{
			out = "FORMAT EXCEPTION";
		}

		return out;
	}


	public static void toastprintf (Context c, String f, Object... args)
	{
		toastprint (c, doformat (f, args));
	}

	public static void logprintf (Context c, int level, 
					String f, Object... args)
	{
		String s;
		if (c != null)
			s = c.getString (R.string.debug_tag);
		else
			s = "BAD CONTEXT";

		Log.println (level, s, doformat (f, args));
	}


	private Context thecontext;
	private Q() {}
	public Q(Context c) { thecontext = c ; }

	public void tprint (String s)
	{
		toastprint (thecontext, s);
	}

	public void tprintf (String f, Object... args)
	{
		toastprintf (thecontext, f, args);
	}

	public void tp (String f, Object... args)
	{
		toastprintf (thecontext, f, args);
	}

	public void i (String f, Object... args)
	{
		logprintf (thecontext, Log.INFO, f, args);
	}

	public void e (String f, Object... args)
	{
		logprintf (thecontext, Log.ERROR, f, args);
	}

	public void wtf (String f, Object... args)
	{
		logprintf (thecontext, Log.ASSERT, f, args);
	}

	public void v (String f, Object... args)
	{
		logprintf (thecontext, Log.VERBOSE, f, args);
	}

	public void d (String f, Object... args)
	{
		logprintf (thecontext, Log.DEBUG, f, args);
	}

	public void w (String f, Object... args)
	{
		logprintf (thecontext, Log.WARN, f, args);
	}


};
