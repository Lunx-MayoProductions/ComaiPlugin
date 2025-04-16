package de.nms.util;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

public class Licenser {
    public static LicenseState check(String license , String customerID){
        HttpResponse<String> response = Unirest.post("https://app.lukittu.com/api/v1/client/teams/$TEAMID/verification/verify".replace("$TEAMID", Constants.TEAM_ID.toString()))
                .header("Content-Type", "application/json")
                .body("{\n  \"licenseKey\": \"$LICENSE\",\n  \"customerId\": \"$CUSTOMER_ID\",\n  \"productId\": \"6dd63983-62ef-4d89-bf86-fbd7541bf4ed\"}".replace("$LICENSE", license).replace("$CUSTOMER_ID",  customerID))
                .asString();
        JSONObject json = new JSONObject(response.getBody());
        switch (json.getJSONObject("result").getString("code")){
            case "VALID":
                return LicenseState.VALID;
            default:
                return LicenseState.FAIL;
        }
    }


    public enum LicenseState {
        EXPIRED("Sorry, but your license expired! Renew it at: https://comai.com/discord"),
        FAIL("Sorry, but that license is invalid, or does not exist! Buy one at: https://comai.com/discord"),
        VALID("You are good to go! Your license is valid!");

        private final String message;

        LicenseState(String message) {
            this.message = message;
        }

        public String msg() {
            return this.message;
        }
    }
}


