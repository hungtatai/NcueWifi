package com.phonegap.plugin;

import org.apache.cordova.DroidGap;
import org.apache.cordova.api.CordovaInterface;
import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import com.phonegap.api.Plugin;

import android.app.ProgressDialog;
import android.widget.Toast;

public class ToastPlugin extends Plugin {

	@Override 
	public PluginResult execute(String action, JSONArray data, String callback) {
		
		int tmpDur;
		String tmpText = "none";
		
		try {
			tmpText = data.getString(0);
		} catch (JSONException e) {
			return new PluginResult(Status.ERROR, "Parsing Text Exception");
		} 
		try {
			tmpDur = data.getInt(1);
		} catch (JSONException e) {
			return new PluginResult(Status.ERROR, "Parsing Duration Exception");
		}
		
		final String Text = tmpText;
		final int Duration = tmpDur;
		final CordovaInterface currentCtx = ctx;
		 
		
		Runnable runnable = new Runnable() { 
			public void run() {
				Toast.makeText(currentCtx.getApplicationContext() ,Text, Duration).show();
				
			 }
		}; 
		
		ctx.runOnUiThread(runnable);
		
		return new PluginResult(Status.OK);
	}

}
