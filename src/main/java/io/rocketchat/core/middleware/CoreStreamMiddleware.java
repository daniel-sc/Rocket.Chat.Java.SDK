package io.rocketchat.core.middleware;

import io.rocketchat.core.callback.MessageListener;
import io.rocketchat.core.callback.SubscribeListener;
import io.rocketchat.core.model.RocketChatMessage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by sachin on 21/7/17.
 */

public class CoreStreamMiddleware {


    public enum SubType {
        SUBSCRIBEROOM,
        OTHER
    }

    private MessageListener.SubscriptionListener subscriptionListener;

    public static CoreStreamMiddleware middleware=new CoreStreamMiddleware();

    ConcurrentHashMap<String,Object[]> subcallbacks;

    private CoreStreamMiddleware(){
        subcallbacks=new ConcurrentHashMap<>();
    }

    public static CoreStreamMiddleware getInstance(){
        return middleware;
    }

    public void subscribeRoom(MessageListener.SubscriptionListener subscription) {
        this.subscriptionListener = subscription;
    }

    public void createSubCallback(String id, SubscribeListener callback, SubType subscription){
        subcallbacks.put(id,new Object[]{callback,subscription});
    }

    public void processCallback(JSONObject object){
        String s = object.optString("collection");
        JSONArray array=object.optJSONObject("fields").optJSONArray("args");

        switch (parse(s)) {
            case SUBSCRIBEROOM:
                RocketChatMessage message=new RocketChatMessage(array.optJSONObject(0));
                String roomId = object.optJSONObject("fields").optString("eventName");
                subscriptionListener.onMessage(roomId,message);
                break;
            case OTHER:
                break;
        }

    }

    public void processSubSuccess(JSONObject subObj){
        if (subObj.optJSONArray("subs")!=null) {
            String id = subObj.optJSONArray("subs").optString(0);
            if (subcallbacks.containsKey(id)) {
                Object object[] = subcallbacks.remove(id);
                SubscribeListener subscribeListener = (SubscribeListener) object[0];
                subscribeListener.onSubscribe((SubType) object[1], id);
            }
        }
    }

    public static SubType parse(String s){
        if (s.equals("stream-room-messages")) {
            return SubType.SUBSCRIBEROOM;
        }
        return SubType.OTHER;
    }
}