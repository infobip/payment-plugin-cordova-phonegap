Centili InApp Payments Plugin
=============================
*only for Android apps*

Installation
------------

To install plugin to your Cordova project use Cordova CLI Tool:
    
    $ cordova plugin add <github repo>

Basic Usage
-----------

Invoke payment action calling `startPayment` function:

	centili.startPayment(args, success, error);

`args` is JSONObject that contains next variables:

	var args = {
        "apiKey": "<YOUR-API-KEY>", 	//mandatory 
        "clientId": "<CLIENT-ID>",		// optional
        "info": "Some info text...",	// optional
        "languageCode": "EN",			// optional
        "packageIndex": 0,				// optional
        "price": 0,						// optional
        "offline": false,				// optional (default: false)
        "testMode": false				// optional (default: false)
    }

For more information about `args` fields check [this](https://www.centili.com/manual/android/android-instructions.pdf)

`success` and `error` are callback functions that need to catch eventActions from Centili Plugin

	
    var success = function(purchaseResponse) {
        if (purchaseResponse.status == "onPurchaseSuccess") {
            //TODO: payment success
        }
		if (purchaseResponse.status == "error") {
			//TODO: error occurs 
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

`purchaseResponse` has next fields:
	
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
	
	

Use Advance Features
--------------------

To enable debug mode in Android plugin set `setDebugModeEnabled` to true:

	centili.setDebugModeEnabled(true, success, error);

To disable Pending Transaction Handling set `setPandingTransactionHandlingEnabled` to false:
	
	centili.setPandingTransactionHandlingEnabled(false, success, error);
		
`success` and `error` are callback functions.	


Offline mode
------------

If offline mode is set to true, it is necessary to replace `libs/CentiliLib-2.jar` with library downloaded from [Centili Partner Panel](https://www.centili.com/partners) for a specific service, in your Android project.


Examples
--------

The example of Hello World project with Centili Plugin can be found [here](https://github.com/)

