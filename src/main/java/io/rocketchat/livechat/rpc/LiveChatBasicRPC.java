package io.rocketchat.livechat.rpc;

import org.json.JSONException;
import org.json.JSONObject;

import io.rocketchat.common.data.rpc.RPC;
import io.rocketchat.common.utils.Utils;

/**
 * Created by sachin on 8/6/17.
 */

public class LiveChatBasicRPC extends RPC {

    public static String visitorToken = Utils.generateRandomHexToken(16);

    private static final String GET_INITIAL_DATA = "livechat:getInitialData";
    private static final String REGISTER_GUEST = "livechat:registerGuest";
    private static final String LOGIN = "login";
    private static final String GET_AGENT_DATA = "livechat:getAgentData";
    private static final String CLOSE_CONVERSATION = "livechat:closeByVisitor";
    private static final String SEND_OFFLINE_MESSAGE = "livechat:sendOfflineMessage";

    /**
     * Tested
     *
     * @param integer
     * @return
     */

    public static String getInitialData(int integer) {
        return getRemoteMethodObject(integer, GET_INITIAL_DATA, visitorToken).toString();
    }

    /**
     * Tested
     *
     * @param integer
     * @param name
     * @param email
     * @param dept
     * @return
     */

    public static String registerGuest(int integer, String name, String email, String dept) {

        JSONObject object = new JSONObject();
        try {
            object.put("token", visitorToken);
            object.put("name", name);
            object.put("email", email);
            object.put("department", dept);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getRemoteMethodObject(integer, REGISTER_GUEST, object).toString();
    }

    /**
     * Tested
     *
     * @param integer
     * @param token
     * @return
     */
    public static String login(int integer, String token) {
        JSONObject object = new JSONObject();
        try {
            object.put("resume", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getRemoteMethodObject(integer, LOGIN, object).toString();

    }

    /**
     * Tested
     *
     * @param integer
     * @param roomId
     * @return
     */
    public static String getAgentData(int integer, String roomId) {
        return getRemoteMethodObject(integer, GET_AGENT_DATA, roomId).toString();
    }

    /**
     * Tested
     *
     * @param integer
     * @param roomId
     * @return
     */

    public static String closeConversation(int integer, String roomId) {
        return getRemoteMethodObject(integer, CLOSE_CONVERSATION, roomId).toString();
    }

    public static String sendOfflineMessage(int integer, String name, String email, String message) {
        JSONObject object = new JSONObject();
        try {
            object.put("name", name);
            object.put("email", email);
            object.put("message", message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getRemoteMethodObject(integer, SEND_OFFLINE_MESSAGE, object).toString();
    }

}
