package com.phonegap.plugin;

import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.phonegap.api.Plugin;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class WifiInfoPlugin extends Plugin { 

	@Override 
	public PluginResult execute(String action, JSONArray data, String callback) {
		
		Context context = ctx.getApplicationContext();
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		/*
		
		if(action.equalsIgnoreCase("BSSID"))
			return new PluginResult(Status.OK, wifiInfo.getBSSID());
		else if(action.equalsIgnoreCase("HiddenSSID"))
			return new PluginResult(Status.OK, wifiInfo.getHiddenSSID());
		else if(action.equalsIgnoreCase("SSID"))
			return new PluginResult(Status.OK, wifiInfo.getSSID());
		else if(action.equalsIgnoreCase("MacAddress"))
			return new PluginResult(Status.OK, wifiInfo.getMacAddress());
		else if(action.equalsIgnoreCase("IpAddress"))
			return new PluginResult(Status.OK, wifiInfo.getIpAddress());
		else if(action.equalsIgnoreCase("NetworkId"))
			return new PluginResult(Status.OK, wifiInfo.getNetworkId());
		else if(action.equalsIgnoreCase("RSSI"))
			return new PluginResult(Status.OK, wifiInfo.getRssi());
		else if(action.equalsIgnoreCase("LinkSpeed"))
			return new PluginResult(Status.OK, wifiInfo.getLinkSpeed());
		*/
		
		JSONObject obj = new JSONObject();
		try {
			obj.put("BSSID", wifiInfo.getBSSID());
			obj.put("HiddenSSID", wifiInfo.getHiddenSSID());
			obj.put("SSID", wifiInfo.getSSID());
			obj.put("MacAddress", wifiInfo.getMacAddress());
			obj.put("IpAddress", wifiInfo.getIpAddress());
			obj.put("NetworkId", wifiInfo.getNetworkId());
			obj.put("RSSI", wifiInfo.getRssi());
			obj.put("LinkSpeed", wifiInfo.getLinkSpeed());
			
		} catch (JSONException e) {
			
			e.printStackTrace();
			return new PluginResult(Status.JSON_EXCEPTION);
		}
		
		return new PluginResult(Status.OK, obj);
		
		
		
	}

}
