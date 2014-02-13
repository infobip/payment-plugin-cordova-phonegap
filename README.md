Centili in-app Payment Plugin for Cordova/PhoneGap 
=============================
*only for Android platform*

Installation
------------

To install the plugin to your Cordova project use the Cordova CLI Tool:
    
    $ cordova plugin add com.infobip.mpay

Basic usage
-----------

Invoke the payment action by calling `startPayment` function:

	centili.startPayment(args, success, error);

`args` is a JSONObject that contains the following variables:

	var args = {
        "apiKey": "<YOUR-API-KEY>", 	// mandatory 
        "clientId": "<CLIENT-ID>",		// optional
        "info": "Some info text...",	// optional
        "languageCode": "EN",			// optional
        "packageIndex": 0,				// optional
        "price": 0,						// optional
        "offline": false,				// optional (default: false)
        "testMode": false				// optional (default: false)
    }

For more information about `args` fields check [this](https://www.centili.com/manual/android/android-instructions.pdf)

`success` and `error` are call-back functions that need to catch eventActions from the Centili Plugin

	
    var success = function(purchaseResponse) {
        if (purchaseResponse.status == "onPurchaseSuccess") {
            //TODO: payment success
        }
		if (purchaseResponse.status == "onPurchasePending") {
            //TODO: payment pending
            //avoid blocking calls like alert(); 
        }
    };


    var error = function(purchaseResponse) {
        if (purchaseResponse.status == "onPurchaseFailed") {
            //TODO: payment faild
        }
        if (purchaseResponse.status == "onPurchaseCanceled") {
            //TODO: payment canceled            
        }
		if (purchaseResponse.status == "error") {
            //TODO: error occurs 
        }
    };

`purchaseResponse` has the following fields:
	
	purchaseResponse.status;
	purchaseResponse.interval;
	purchaseResponse.itemAmount;
	purchaseResponse.price;
	purchaseResponse.apiKey;
	purchaseResponse.clientId;
	purchaseResponse.currency;
	purchaseResponse.errorMessage;
	purchaseResponse.itemName;
	purchaseResponse.transactionId;

If `purchaseResponse` has status `error` than it contains the following fields:

	purchaseResponse.status;
	purchaseResponse.message;
	purchaseResponse.stackTrace;
	
	

Advanced features usage
--------------------

To enable debug mode in Android plugin set `setDebugModeEnabled` to true:

	centili.setDebugModeEnabled(true, success, error);

To disable Pending Transaction Handling set `setPandingTransactionHandlingEnabled` to false:
	
	centili.setPandingTransactionHandlingEnabled(false, success, error);
		
`success` and `error` are call-back functions.	


Offline mode
------------

If offline mode is set to true, it is necessary to replace `libs/CentiliLib-2.jar` with a library downloaded from [Centili Partner Panel](https://www.centili.com/partners) for the specific service - in your Android project.


Examples
--------

The example of the 'Hello World' project with the Centili Plugin can be found [here](https://github.com/infobip/). To be done soon.

Owners
------

Framework Integration Team @ Infobip Ltd.