/*
 * Copyright 2011 Henri Kerola
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

/**
 * @author Henri Kerola
 */
public class VSplitButton extends Composite implements Paintable, Container {

	public static final String CLASSNAME = "v-splitbutton";

	private final Panel panel;

	private Widget buttonWidget;
	private Widget popupButtonWidget;

	private boolean initDone = false;

	private RenderSpace renderSpace = new RenderSpace();
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
			setHeight();
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
		renderSpace.setWidth(getOffsetWidth());
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

	private void setHeight() {
		if (this.height == null) {
			buttonWidget.setHeight("");
			popupButtonWidget.setHeight("");
			int buttonHeight = Util.getRequiredHeight(buttonWidget);
			int popupButtonHeight = Util.getRequiredHeight(popupButtonWidget);
			if (buttonHeight > popupButtonHeight) {
				buttonWidget.setHeight(buttonHeight + "px");
				popupButtonWidget.setHeight(buttonHeight + "px");
				renderSpace.setHeight(buttonHeight);
			} else {
				buttonWidget.setHeight(popupButtonHeight + "px");
				popupButtonWidget.setHeight(popupButtonHeight + "px");
				renderSpace.setHeight(popupButtonHeight);
			}
		} else {
			buttonWidget.setHeight(this.height);
			popupButtonWidget.setHeight(this.height);
			renderSpace.setHeight(Integer.parseInt(this.height.substring(0,
					this.height.length() - 2)));
		}
	}

	@Override
	public void setHeight(String height) {
		super.setHeight(height);
		if (height == null || "".equals(height)) {
			this.height = null;
		} else {
			this.height = height;
		}
		if (initDone) {
			setHeight();
		}
	}

	public void replaceChildComponent(Widget oldComponent, Widget newComponent) {
		// TODO Auto-generated method stub

	}

	public boolean hasChildComponent(Widget component) {
		return component.getParent() == panel;
	}

	public void updateCaption(Paintable component, UIDL uidl) {
		// TODO Auto-generated method stub

	}

	public boolean requestLayout(Set<Paintable> children) {
		if (initDone) {
			setButtonWidth();
			setHeight();
		}
		return false;
	}

	public RenderSpace getAllocatedSpace(Widget child) {
		return renderSpace;
	}
}
