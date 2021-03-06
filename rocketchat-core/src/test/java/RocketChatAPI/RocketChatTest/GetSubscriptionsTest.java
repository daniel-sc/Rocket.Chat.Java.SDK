package RocketChatAPI.RocketChatTest;

import RocketChatAPI.RocketChatTest.ChatParent.RocketChatParent;
import com.rocketchat.core.model.SubscriptionObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by sachin on 3/8/17.
 */
public class GetSubscriptionsTest extends RocketChatParent {

    String username = "testuserrocks";
    String password = "testuserrocks";

    @Before
    public void setUp() {
        super.setUpBefore();
    }

    @Test(timeout = 12000)
    public void getSubscriptionsTest() throws Exception {
        List<SubscriptionObject> subscriptions = api.singleConnect()
                .thenCompose(v -> api.login(username, password))
                .thenCompose(token -> api.getSubscriptions())
                .get();
        Assert.assertNotNull(subscriptions);
        Assert.assertTrue(subscriptions.size() > 0);
    }

    @After
    public void logout() {
        api.logout();
    }
}
