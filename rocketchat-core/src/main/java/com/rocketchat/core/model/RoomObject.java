package com.rocketchat.core.model;

import com.rocketchat.common.data.model.Room;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sachin on 19/7/17.
 */

public class RoomObject extends Room {

    private String topic;
    private JSONArray mutedUsers;
    private Date jitsiTimeout;
    private Boolean readOnly;

    public RoomObject(JSONObject object) {
        super(object);
        try {
            topic = object.optString("topic");
            mutedUsers = object.optJSONArray("muted");
            if (object.optJSONObject("jitsiTimeout") != null) {
                jitsiTimeout = new Date(object.getJSONObject("jitsiTimeout").getLong("$date"));
            }
            readOnly = object.optBoolean("ro");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getTopic() {
        return topic;
    }

    public JSONArray getMutedUsers() {
        return mutedUsers;
    }

    public Date getJitsiTimeout() {
        return jitsiTimeout;
    }

    public Boolean getReadOnly() {
        return readOnly;
    }
}
