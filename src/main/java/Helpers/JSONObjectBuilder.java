package Helpers;

import org.json.JSONObject;

public class JSONObjectBuilder {
    private JSONObject jsonObject;

    // Constructor to initialize an empty JSON object
    public JSONObjectBuilder() {
        this.jsonObject = new JSONObject();
    }

    // Method to add parameters to the JSON object
    public JSONObjectBuilder addParameter(String key, Object value) {
        this.jsonObject.put(key, value);
        return this;
    }

    // Method to return the built JSON object as a string
    public String build() {
        return this.jsonObject.toString();
    }
}
