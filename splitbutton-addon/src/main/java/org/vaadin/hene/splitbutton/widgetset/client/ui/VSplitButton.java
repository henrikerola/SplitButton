package org.vaadin.hene.splitbutton.widgetset.client.ui;

import java.util.Set;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.Container;
import com.vaadin.terminal.gwt.client.Paintable;
import com.vaadin.terminal.gwt.client.RenderSpace;
import com.vaadin.terminal.gwt.client.UIDL;
import com.vaadin.terminal.gwt.client.Util;

public class VSplitButton extends Composite implements Paintable, Container {

	public static final String CLASSNAME = "v-splitbutton";

	private final Panel panel;

	private Widget buttonWidget;
	private Widget popupButtonWidget;

	private boolean initDone = false;
	private String width;
	private String height;

	public VSplitButton() {
		panel = new FlowPanel();
		initWidget(panel);

		setStyleName(CLASSNAME);
	}

	public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
		if (client.updateComponent(this, uidl, false)) {
			return;
		}

		UIDL buttonUidl = uidl.getChildUIDL(0);
		Paintable buttonPaintable = client.getPaintable(buttonUidl);
		panel.add((Widget) buttonPaintable);
		buttonPaintable.updateFromUIDL(buttonUidl, client);
		buttonWidget = (Widget) buttonPaintable;

		UIDL popupButtonUidl = uidl.getChildUIDL(1);
		Paintable popupButtonPaintable = client.getPaintable(popupButtonUidl);
		panel.add((Widget) popupButtonPaintable);
		popupButtonPaintable.updateFromUIDL(popupButtonUidl, client);
		popupButtonWidget = (Widget) popupButtonPaintable;

		if (!initDone) {
			setButtonWidth();
		}

		initDone = true;
	}

	private void setButtonWidth() {
		if (width == null) {
			buttonWidget.setWidth("");
		} else {
			int popupButtonWidth = Util.getRequiredWidth(popupButtonWidget
					.getElement());
			buttonWidget.setWidth((getOffsetWidth() - popupButtonWidth) + "px");
		}

	}

	@Override
	public void setWidth(String width) {
		if (width == null || "".equals(width)) {
			this.width = null;
		} else {
			this.width = width;
		}

		Util.setWidthExcludingPaddingAndBorder(this, width, 0);

		if (initDone) {
			setButtonWidth();
		}
	}

	@Override
	public void setHeight(String height) {
		// TODO Auto-generated method stub
		super.setHeight(height);
	}

	public void replaceChildComponent(Widget oldComponent, Widget newComponent) {
		// TODO Auto-generated method stub

	}

	public boolean hasChildComponent(Widget component) {
		return component.getParent() == this;
	}

	public void updateCaption(Paintable component, UIDL uidl) {
		// TODO Auto-generated method stub

	}

	public boolean requestLayout(Set<Paintable> children) {
		// TODO Auto-generated method stub
		return false;
	}

	public RenderSpace getAllocatedSpace(Widget child) {
		// TODO Auto-generated method stub
		return null;
	}

}
