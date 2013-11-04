package com.infobip.mpay;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import c.mpayments.android.PurchaseListener;
import c.mpayments.android.PurchaseManager;
import c.mpayments.android.PurchaseRequest;
import c.mpayments.android.PurchaseResponse;
import c.mpayments.android.util.Logger;

public class Centili extends CordovaPlugin {

	public CallbackContext clbContext;

	public static final String ACTION_START_PAYMENT = "startPayment";
	public static final String ACTION_DEBUG_MODE = "setDebugMode";
	public static final String ACTION_PENDING_TRANSACTION = "setPandingTransactionEnabled";

	@Override
	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {
		try {
			this.clbContext = callbackContext;
			JSONObject arg_object = args.getJSONObject(0);

			if (ACTION_START_PAYMENT.equals(action)) {
				PurchaseRequest pr = this.setPurchaseParams(arg_object);
				PurchaseManager.attachPurchaseListener(this.listener);
				PurchaseManager.startPurchase(pr, this.cordova.getActivity());
				return true;
			}

			if (ACTION_DEBUG_MODE.equals(action)) {
				this.setDebugMode(arg_object.getBoolean("ind"));
				clbContext.success();
				return true;
			}

			if (ACTION_PENDING_TRANSACTION.equals(action)) {
				this.setPendingTransaction(arg_object.getBoolean("ind"));
				clbContext.success();
				return true;
			}

			return false;
		} catch (Exception e) {

			// TODO: convert error to JSON object
			System.err.println("Exception: " + e.getMessage());
			callbackContext.error(this.makeErrorObject(e));
			return false;
		}
	}

	public PurchaseListener listener = new PurchaseListener() {
		@Override
		public void onPurchaseSuccess(PurchaseResponse paramPurchaseResponse) {
			Log.i("mPay", "onPurchaseSuccess");
			Centili.this.clbContext.success(Centili.this.serialize(
					paramPurchaseResponse, "onPurchaseSuccess"));
		}

		@Override
		public void onPurchasePending(PurchaseResponse paramPurchaseResponse) {
			Log.i("mPay", "onPurchasePending");
			// Centili.this.clbContext.success(Centili.this.serialize(paramPurchaseResponse,
			// "onPurchasePending"));
		}

		@Override
		public void onPurchaseFailed(PurchaseResponse paramPurchaseResponse) {
			Log.i("mPay", "onPurchaseFailed");
			Centili.this.clbContext.error(Centili.this.serialize(
					paramPurchaseResponse, "onPurchaseFailed"));
		}

		@Override
		public void onPurchaseCanceled(PurchaseResponse paramPurchaseResponse) {
			Log.i("mPay", "onPurchaseCanceled");
			Centili.this.clbContext.error(Centili.this.serialize(
					paramPurchaseResponse, "onPurchaseCanceled"));
		}
	};

	private JSONObject serialize(PurchaseResponse pr, String status) {
		JSONObject json = new JSONObject();
		try {
			json.put("status", status);
			json.put("interval", pr.getInterval());
			json.put("itemAmount", pr.getItemAmount());
			json.put("price", pr.getPrice());
			json.put("apiKey", pr.getApiKey());
			json.put("clientId", pr.getClientId());
			json.put("currency", pr.getCurrency());
			json.put("errorMessage", pr.getErrorMessage());
			json.put("itemName", pr.getItemName());
			json.put("transactionId", pr.getTransactionId());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json;
	}
	
	private JSONObject makeErrorObject(Exception e){
		JSONObject json = new JSONObject();
		try{
			json.put("status", "error");
			json.put("message", e.getMessage());
			json.put("stackTrace", e.toString());
		} catch (JSONException ex){
			ex.printStackTrace();
		}
		return null;
	}

	private void setDebugMode(boolean ind) {
		Logger.setDebugModeEnabled(ind);
	}

	private void setPendingTransaction(boolean ind) {
		PurchaseManager.setPendingTransactionHandlingEnabled(ind);
	}

	private PurchaseRequest setPurchaseParams(JSONObject args)
			throws JSONException {
		PurchaseRequest pr = new PurchaseRequest(args.getString("apiKey"));
		if (args.has("clientId")) {
			pr.setClientId(args.getString("clientId"));
		}
		if (args.has("info")) {
			pr.setInfo(args.getString("info"));
		}
		if (args.has("languageCode")) {
			pr.setLanguageCode(args.getString("languageCode")); 
		}
		if (args.has("offline")) {
			pr.setOfflineModeEnabled(args.getBoolean("offline")); 
		}
		if (args.has("packageIndex")) {
			pr.setPackageIndex(args.getInt("packageIndex"));
		}
		if (args.has("price")) {
			pr.setPrice(args.getDouble("price"));
		}
		if (args.has("testMode")) {
			pr.setTestModeEnabled(args.getBoolean("testMode"));
		}
		return pr;
	}

}
