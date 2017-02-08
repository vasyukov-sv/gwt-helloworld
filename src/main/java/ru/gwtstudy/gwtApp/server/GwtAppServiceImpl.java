package ru.gwtstudy.gwtApp.server;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ru.gwtstudy.gwtApp.client.GwtAppServiceIntf;
import ru.gwtstudy.gwtApp.shared.FieldValidator;

/**
 * The server-side implementation of the RPC service.
 */
public class GwtAppServiceImpl extends RemoteServiceServlet implements GwtAppServiceIntf {

    public String gwtAppCallServer(String data) throws IllegalArgumentException {
        if (!FieldValidator.isValidData(data)) {
            throw new IllegalArgumentException("More than 3 symbols");
        }

        // Returns the name and version of the servlet container on which the servlet is running.
        String serverInfo = getServletContext().getServerInfo();
        String userAgent = getThreadLocalRequest().getHeader("User-Agent");

        data = escapeHtml(data);
        userAgent = escapeHtml(userAgent);

        return "Hello, " + data + "!<br> Server Info: " + serverInfo + ".<br> You use:" +
                "<br>" + userAgent;
    }

    private String escapeHtml(String html) {
        return html == null ? null : html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
                ">", "&gt;");
    }
}

