package ru.gwtstudy.gwtApp.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * gwt_helloworld
 * Created by admin on 08.02.2017.
 */

/**
 * The async counterpart of <code>GwtAppServiceIntf</code>
 */
public interface GwtAppServiceIntfAsync {
    void gwtAppCallServer(String data, AsyncCallback<String> async) throws IllegalArgumentException;
}