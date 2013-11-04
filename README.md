Centili InApp Payments Plugin
=============================
*only for Android apps*

Installation 
------------
To install plugin to your Cordova project use Cordova CLI Tool:
    
    `$ cordova plugin add <github repo>`

Usage
-----
Invok payment action calling `startPayment` function:

	`centili.startPayment(args, success, error);`

`args` is JSONObject that contains next variables:

	`var args = {
        "apiKey": "<YOUR-API-KEY>", 	  //mandatory 
        "clientId": "<CLIENT-ID>",		// optional
        "info": "Some info text...",	  // optional
        "languageCode": "EN",			 // optional
        "packageIndex": 0,				// optional
        "price": 0,					   // optional
        "offline": false,				 // optional (default: false)
        "testMode": false				 // optional (default: false)
    }`
