package LiveChatAPI.LiveChatTest;

import LiveChatAPI.LiveChatTest.LiveChatParent.ChatParent;
import com.rocketchat.common.data.model.ErrorObject;
import com.rocketchat.common.listener.ConnectListener;
import com.rocketchat.livechat.callback.MessageListener;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.timeout;

/**
 * Created by sachin on 17/7/17.
 */
public class SendOfflineMessageTest extends ChatParent implements ConnectListener {

    @Mock
    MessageListener.OfflineMessageListener listener;

    @Captor
    ArgumentCaptor<Boolean> isSent;

    @Captor
    ArgumentCaptor<ErrorObject> errorObjectArgumentCaptor;


    @Override
    public void setUpBefore() {
        super.setUpBefore();
        api.connect(this);
    }

    @Override
    public void onConnect(String sessionID) {
        System.out.println("Connected to server");
        api.sendOfflineMessage("aditya", "aditya123@gmail.com", "This is a test message", listener);
    }

    @Override
    public void onDisconnect(boolean closedByServer) {
        System.out.println("Disconnected from server");
    }

    @Override
    public void onConnectError(Exception websocketException) {
        System.out.println("Connect error to server");
    }

    @Test
    public void sendOfflineTest() {
        Mockito.verify(listener, timeout(6000).atLeastOnce()).onOfflineMesssageSuccess(isSent.capture(), errorObjectArgumentCaptor.capture());
        Assert.assertTrue(errorObjectArgumentCaptor.getValue() == null);
        Assert.assertTrue(isSent.getValue() != null);
        System.out.println("Offline Message sent is " + isSent.getValue());
    }
}
