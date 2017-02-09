package ru.gwtstudy.gwtApp.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;

/**
 * gwt_helloworld
 * Created by admin on 09.02.2017.
 */
public class LastHelloWorldBinder {
    private static LastHelloWorldBinderUiBinder ourUiBinder = GWT.create(LastHelloWorldBinderUiBinder.class);
    @UiField
    SpanElement nameSpan;
    private DivElement root;

    public LastHelloWorldBinder() {
        root = ourUiBinder.createAndBindUi(this);
    }

    public Element getElement() {
        return root;
    }

    public void setName(String name) {
        nameSpan.setInnerText(name);
    }

    interface LastHelloWorldBinderUiBinder extends UiBinder<DivElement, LastHelloWorldBinder> {
    }
}