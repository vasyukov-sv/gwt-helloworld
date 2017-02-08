package ru.gwtstudy.gwtApp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.gwtstudy.gwtApp.shared.FieldValidator;

/**
 * gwt_helloworld
 * Created by admin on 08.02.2017.
 * Analog public static void main() in usual java
 */

public class GwtApp implements EntryPoint {

    //   DEfine components GWT
    private final Button confirmButton = new Button("Confirm");
    private final TextBox nameField = new TextBox();
    private final Label errorLabel = new Label();
    private final Label helloLabel = new Label();
    private final DialogBox dialogBox = new DialogBox();
    private final HTML serverResponseHtml = new HTML();
    private final Label sendToServerLabel = new Label();
    private final Button closeButton = new Button("Close");
    /*create gwtAppService
     with him make async process data
     and we need realize 2 methods on success and failure
     In order to define your RPC interface, you need to write three components:

    Define an interface (GwtAppServiceIntf client side) for your service that extends RemoteService and lists all your RPC methods.
    Create a class (GwtAppServiceImpl  server side) that extends RemoteServiceServlet and implements the interface you created above.
    Define an asynchronous interface (GwtAppServiceIntfAsync client side) to your service to be called from the client-side code.

    see http://www.gwtproject.org/doc/latest/tutorial/RPC.html
    */
    private final GwtAppServiceIntfAsync gwtAppService = GWT.create(GwtAppServiceIntf.class);
    private VerticalPanel dialogVPanel = new VerticalPanel();

    //  This is the entry point method.
    public void onModuleLoad() {

        helloLabel.setText("GwtApp Application hello world");

        final Label usernameLabel = new Label();
        usernameLabel.setText("Username: ");

        /*Bind  id='' on  html components GWT
        RootPanel is default on load */

        RootPanel.get("helloId").add(helloLabel);

        RootPanel.get("usernameLabelId").add(usernameLabel);
        RootPanel.get("usernameId").add(nameField);

        RootPanel.get("confirmButtonId").add(confirmButton);
        RootPanel.get("errorLabelContainer").add(errorLabel);

        // Create the popup dialog box
        dialogBox.setText("Remote procedure call from server");
        dialogBox.setAnimationEnabled(true);

        closeButton.getElement().setId("closeButtonId");

        dialogVPanel.addStyleName("dialogVPanel");
        dialogVPanel.add(new HTML("<b>Sended field to server:</b>"));
        dialogVPanel.add(sendToServerLabel);
        dialogVPanel.add(new HTML("<br><b>Response Server:</b>"));
        dialogVPanel.add(serverResponseHtml);
        dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
        dialogVPanel.add(closeButton);
        dialogBox.setWidget(dialogVPanel);

        // Handlers
        confirmButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                confirmButton.setEnabled(false);
                sendInfoToServer();
            }
        });


        nameField.addKeyUpHandler(new KeyUpHandler() {
            public void onKeyUp(KeyUpEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    sendInfoToServer();
                }
            }
        });

        closeButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                dialogBox.hide();
                confirmButton.setEnabled(true);
                confirmButton.setFocus(true);
            }
        });

    }

    /**
     * Send Info To Server
     */
    private void sendInfoToServer() {
        //validate input text
        errorLabel.setText("");
        String nameToServer = nameField.getText();
        if (!FieldValidator.isValidData(nameToServer)) { //отобразить ошибку на html странице
            errorLabel.setText("More than 3 symbols");
            return;
        }
        sendToServerLabel.setText(nameToServer);
        confirmButton.setEnabled(false);
        serverResponseHtml.setText("");


        //  gwtAppCallServer we define in interface
        // we must define 2 methods
        gwtAppService.gwtAppCallServer(nameToServer, new AsyncCallback<String>() {
            public void onFailure(Throwable caught) {
                dialogBox.setText("Remote Procedure Call - Failure");
                serverResponseHtml.addStyleName("serverResponseLabelError");
                serverResponseHtml.setHTML("ERROR ON SERVER");
                dialogBox.center();
                closeButton.setFocus(true);
            }

            public void onSuccess(String result) {
                dialogBox.setText("Remote Procedure Call");
                serverResponseHtml.removeStyleName("serverResponseLabelError");
                serverResponseHtml.setHTML(result);
                dialogBox.center();
                closeButton.setFocus(true);
            }
        });
    }
}