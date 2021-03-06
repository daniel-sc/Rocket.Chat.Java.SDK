package com.rocketchat.core.uploader;

import com.rocketchat.common.data.model.ErrorObject;
import com.rocketchat.common.listener.Listener;
import com.rocketchat.core.model.FileObject;

/**
 * Created by sachin on 17/8/17.
 */
public class IFileUpload {

    public interface UfsCreateListener extends Listener {
        void onUfsCreate(FileUploadToken token, ErrorObject error);
    }

    public interface UfsCompleteListener extends Listener {
        void onUfsComplete(FileObject file, ErrorObject error);
    }
}
