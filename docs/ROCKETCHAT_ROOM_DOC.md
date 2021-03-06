Following methods are provided by RocketChatAPI.ChatRoom API

- getChatHistory (getting history of messages from the server)
- send message to room (send text message string to given room)
- delete message (delete any sent message from the room)
- update message (update any sent message to new one)
- pin/unpin message (pin message for future use)
- star/unstar message (star important messages in the room)
- deleteGroup (if room is group type, then it can be deleted (should have delete permission))
- archiveRoom ( hides room from list of channels and make it read only)
- unarchiveRoom( shows room in the list of channels and removes read only setting)
- leave room (it is used to leave given room)
- hide room (hide room from the list of channels)
- open room (shows room on the left of list of channels)
- set favorite room ( it will set room to be )
- subscribe room for new messages (Room will receive messages in real-time)
- subscribe room for typing events (Room will receive messages regarding typing events in real-time)
- uploading file to the server (Update AUDIO, VIDEO, IMAGE file to the server)
 
**1. getChatHistory**

```java
/****
     * @param oldestMessageTimestamp Used to do pagination (null means latest timestamp)
     * @param count The message quantity, messages are loaded having timestamp older than @param oldestMessageTimestamp
     * @param lastTimestamp Date of the last time when client got data (Used to calculate unread)[unread count suggests number of unread messages having timestamp above @param lastTimestamp]
     **/
    room.getChatHistory(count,oldestMessageTimestamp,lastTimestamp, new LoadHistoryListener() {
                    @Override
                    public void onLoadHistory(ArrayList<RocketChatMessage> list, int unreadNotLoaded, ErrorObject error) {
                        
                    }
    });
    
    Hint: pass count=20, oldestMessageTimestamp=new Date(),lastTimestamp=null for getting latest 20 messages
```

**2. sendMessage**

```java
        //Without acknowledgement
        room.sendMessage("This is some random message");
        
        //With acknowledgement
        
        room.sendMessage("Hi there", new MessageListener.MessageAckListener() {
                @Override
                public void onMessageAck(RocketChatMessage message, ErrorObject error) {
                        if (error==null)
                        {
                            System.out.println("Got ack for sent message");
                        }
                }
        });

```


**3. deleteMessage**

```java
        String messageId= "xxxx";
        //without ack
        room.deleteMessage(messageId,null);

        //with ack
        room.deleteMessage(messageId, new SimpleListener() {
            @Override
            public void callback(Boolean success, ErrorObject error) {
                if (success){
                    System.out.println("Message deleted successfully");    
                }
            }
        });
```


**4. updateMessage**

```java

        //without ack
        String messageId="";
        room.updateMessage(messageId, "This is a updated message",null);
        
        //with ack
        room.updateMessage(messageId, "This is a updated message", new SimpleListener() {
            @Override
            public void callback(Boolean success, ErrorObject error) {
                if (success) {
                    System.out.println("Message updated successfully");
                }
            }
        });

```

**5. pinMessage**

```java

        RocketChatMessage message; //This is a message received from loading history
        
        //without ack
        room.pinMessage(message.getRawJsonObject(),null);
        
        //with ack
        room.pinMessage(message.getRawJsonObject(), new SimpleListener() {
            @Override
            public void callback(Boolean success, ErrorObject error) {
                if (success){
                    System.out.println("Pinned message successfully");
                }
            }
        });

```

**6. unPinMessage**

```java

        RocketChatMessage message; //This is a message received from loading history
        
        //without ack
        room.unpinMessage(message.getRawJsonObject(),null);
        
        //with ack
        room.unpinMessage(message.getRawJsonObject(), new SimpleListener() {
            @Override
            public void callback(Boolean success, ErrorObject error) {
                if (success){
                    System.out.println("Unpinned message successfully");
                }
            }
        });

```

**7. starMessage**

```java
        String messageId="";
        Boolean star;  //Enabled or disables star for message
        //without ack
        room.starMessage(messageId, star, null);
        
        //with ack
        room.starMessage(messageId, star, new SimpleListener() {
            @Override
            public void callback(Boolean success, ErrorObject error) {
                if (success){
                    System.out.println("executed successfully");
                }                
            }
        });

```


**8. deleteGroup**

```java
    
    //wihout ack
    room.deleteGroup(null);
    
    //with ack
    room.deleteGroup(new SimpleListener() {
            @Override
            public void callback(Boolean success, ErrorObject error) {
                    if (success){
                        System.out.println("group deleted successfully");
                    }
            }
    });
```

**9. archiveRoom**

```java

        //without ack
        room.archive(null);
        
        //with ack
        room.archive(new SimpleListener() {
            @Override
            public void callback(Boolean success, ErrorObject error) {
                if (success){
                    System.out.println("Archived successfully");
                }
            }
        });
```

**10. unArchiveRoom**

```java
        //without ack
        room.unarchive(null);
        
        //with ack
        room.unarchive(new SimpleListener() {
            @Override
            public void callback(Boolean success, ErrorObject error) {
                if (success){
                    System.out.println("Archived successfully");
                }
            }
        });

```

**11. leaveRoom**

```java
        //without ack
        room.leave(null);
        
        //with ack
        room.leave(new SimpleListener() {
            @Override
            public void callback(Boolean success, ErrorObject error) {
                if (success){
                    System.out.println("left room");
                }
            }
        });

```

**12. hideRoom**

```java
        //without ack
        room.hide(null);
        
        //with ack
        room.hide(new SimpleListener() {
            @Override
            public void callback(Boolean success, ErrorObject error) {
                if (success){
                    System.out.println("room is hidden");
                }
            }
        });
```

**13. openRoom**

```java
        //without ack
        room.open(null);
        
        //with ack
        room.open(new SimpleListener() {
            @Override
            public void callback(Boolean success, ErrorObject error) {
                if (success){
                    System.out.println("opened room successfully");
                }
            }
        });
```

**14. setFavouriteRoom**

```java

        Boolean isfavourite=true;
        //without ack
        room.setFavourite(isfavourite,null);
        
        //with ack
        room.setFavourite(isfavourite, new SimpleListener() {
            @Override
            public void callback(Boolean success, ErrorObject error) {
                if (success){
                    System.out.println("Room is now favourite");
                }
            }
        });
```

**15. subscribeRoomMessageEvent**

- Make parent class implement MessageListener.SubscriptionListener interface from core package.  

```java

        void subscribeMessage(){
            room.subscribeRoomMessageEvent(new SubscribeListener() {
                @Override
                public void onSubscribe(Boolean isSubscribed, String subId) {
                    if (isSubscribed) {
                        System.out.println("subscribed to room successfully");
                    }
                }
            },this);
        }
        
        @Override
        public void onMessage(String roomId, RocketChatMessage message) {
            System.out.println("will receive new message here");  
        }
       
        
```

- Messages can be of different types, with number of attachments.


```
    @Override
    public void onMessage(String roomId, RocketChatMessage message) {
        System.out.println("Got message " + message.getMessage());
        switch (message.getMsgType()) {
            case TEXT:
                System.out.println("This is a text message");
                break;
            case ATTACHMENT:
                List <TAttachment> attachments= message.getAttachments();
                for (TAttachment attachment : attachments) {
                    switch (attachment.getAttachmentType()) {
                        case TEXT_ATTACHMENT:
                            System.out.println("This is a reply or quote to a message");
                            break;
                        case IMAGE:
                            System.out.println("There is a image attachment");
                            break;
                        case AUDIO:
                            System.out.println("There is a audio attachment");
                            break;
                        case VIDEO:
                            System.out.println("There is a video attachment");
                            break;
                    }
                }
                break;
            case MESSAGE_EDITED:
                System.out.println("Message has been edited");
                break;
            case MESSAGE_STARRED:
                System.out.println("Message is starred now");
                break;
            case MESSAGE_REACTION:
                System.out.println("Got message reaction");
                break;
            case MESSAGE_REMOVED:
                System.out.println("Message is deleted");
                break;
            case ROOM_NAME_CHANGED:
                System.out.println("Room name changed");
                break;
            case ROOM_ARCHIVED:
                System.out.println("Room is archived");
                break;
            case ROOM_UNARCHIVED:
                System.out.println("Room is unarchieved");
                break;
            case USER_ADDED:
                System.out.println("User added to the room");
                break;
            case USER_REMOVED:
                System.out.println("User removed from the room");
                break;
            case USER_JOINED:
                System.out.println("User joined the room");
                break;
            case USER_LEFT:
                System.out.println("User left the room");
                break;
            case USER_MUTED:
                System.out.println("User muted now");
                break;
            case USER_UNMUTED:
                System.out.println("User un-muted now");
                break;
            case WELCOME:
                System.out.println("User welcomed");
                break;
            case SUBSCRIPTION_ROLE_ADDED:
                System.out.println("Subscription role added");
                break;
            case SUBSCRIPTION_ROLE_REMOVED:
                System.out.println("Subscription role removed");
                break;
            case OTHER:
                break;
        }

    }

```

**16. subscribeRoomTypingEvent**

- Make parent class implement TypingListener interface.

```java

        void subscribeTyping(){
                room.subscribeRoomTypingEvent(new SubscribeListener() {
                @Override
                public void onSubscribe(Boolean isSubscribed, String subId) {
                    if (isSubscribed){
                        System.out.println("subscribed to typing event successfylly");
                    }
                }
                },this);
        }
        
        
        @Override
        public void onTyping(String roomId, String user, Boolean istyping) {
            
        }

```

**16. upload file**

- Make file object from the file path, pass it along with filename and description.
- In callback, user will receive upload progress and callback when file is actually sent.

```java
        String file_path = "/home/sachin/Pictures/pain.jpg";
        File file = new File(file_path);
        room.uploadFile(file, file.getName()," This is a file attachment", new FileAdapter(){
            @Override
            public void onUploadStarted(String roomId, String fileName, String description) {
                super.onUploadStarted(roomId, fileName, description);
            }

            @Override
            public void onUploadProgress(int progress, String roomId, String fileName, String description) {
                super.onUploadProgress(progress, roomId, fileName, description);
            }

            @Override
            public void onUploadComplete(int statusCode, FileObject file, String roomId, String fileName, String description) {
                super.onUploadComplete(statusCode, file, roomId, fileName, description);
            }

            @Override
            public void onUploadError(ErrorObject error, IOException e) {
                super.onUploadError(error, e);
            }

            @Override
            public void onSendFile(RocketChatMessage message, ErrorObject error) {
                super.onSendFile(message, error);
            }
        });

```