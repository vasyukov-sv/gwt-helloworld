package ru.gwtstudy.gwtApp.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * gwt_helloworld
 * Created by admin on 08.02.2017.
 *
 *  The client-side stub for the RPC service.
 */

//This associates the service with a default path relative to the module base URL.
@RemoteServiceRelativePath("gwtAppService")

public interface GwtAppServiceIntf extends RemoteService {
    String gwtAppCallServer(String data) throws IllegalArgumentException;
}