package persistence;

import org.json.JSONObject;

// Represents interface class that useful for saving objects to file
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
