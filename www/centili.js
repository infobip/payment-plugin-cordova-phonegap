var centili = {
    startPayment: function(args, successCallback, errorCallback) {
        cordova.exec(
            successCallback,    // success callback function
            errorCallback,      // error callback function
            'Centili',          // mapped to our native Java class called "Centili"
            'startPayment',     // with this action name
            [args]              // and this array of custom arguments
        ); 
    },
    setDebugModeEnabled: function(ind, successCallback, errorCallback){
        cordova.exec(
            successCallback,    
            errorCallback,      
            'Centili',          
            'setDebugMode',     
            [{                  
                "ind": ind
            }]
        ); 
    },
    setPandingTransactionHandlingEnabled: function(ind, successCallback, errorCallback){
        cordova.exec(
            successCallback,    
            errorCallback,      
            'Centili',          
            'setPandingTransactionEnabled',     
            [{                 
                "ind": ind
            }]
        ); 
    }
};

module.exports = centili;