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
package org.vaadin.hene.splitbutton;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.vaadin.hene.popupbutton.PopupButton.PopupVisibilityEvent;
import org.vaadin.hene.popupbutton.PopupButton.PopupVisibilityListener;
import org.vaadin.hene.splitbutton.widgetset.client.ui.VSplitButton;

import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.terminal.Paintable;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ClientWidget;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;

/**
 * @author Henri Kerola
 */
@ClientWidget(VSplitButton.class)
@SuppressWarnings("serial")
public class SplitButton extends AbstractComponent implements
		ComponentContainer, ClickListener, PopupVisibilityListener {

	public static final String STYLE_CHAMELEON = "chameleon";

	private static class PopupButton extends
			org.vaadin.hene.popupbutton.PopupButton {
		public PopupButton(Paintable popupPositionPaintable) {
			this.popupPositionPaintable = popupPositionPaintable;
		}
	}

	private final List<SplitButtonClickListener> clickListeners = new LinkedList<SplitButtonClickListener>();
	private final List<SplitButtonPopupVisibilityListener> popupVisibilityListeners = new LinkedList<SplitButtonPopupVisibilityListener>();

	private final Button button;
	private final PopupButton popupButton;

	public SplitButton() {
		button = new Button();
		button.setHeight("100%");
		button.setParent(this);
		button.addListener(this);

		popupButton = new PopupButton(this);
		popupButton.setHeight("100%");
		popupButton.setParent(this);
		popupButton.addPopupVisibilityListener(this);
	}

	public SplitButton(String caption) {
		this();
		setCaption(caption);
	}

	@Override
	public void paintContent(PaintTarget target) throws PaintException {
		super.paintContent(target);
		button.paint(target);
		popupButton.paint(target);
	}

	/**
	 * Shows or hides popup.
	 * 
	 * @param popupVisible
	 *            if true, popup is set to visible, otherwise popup is hidden.
	 */
	public void setPopupVisible(boolean popupVisible) {
		popupButton.setPopupVisible(popupVisible);
	}

	/**
	 * Checks if the popup is visible.
	 * 
	 * @return true, if popup is visible, false otherwise.
	 */
	public boolean isPopupVisible() {
		return popupButton.isPopupVisible();
	}

	@Override
	public String getCaption() {
		return button.getCaption();
	}

	@Override
	public void setCaption(String caption) {
		button.setCaption(caption);
	}

	@Override
	public Resource getIcon() {
		return button.getIcon();
	}

	@Override
	public void setIcon(Resource icon) {
		button.setIcon(icon);
	}

	/**
	 * Returns the description (tooltip) of the primary button.
	 */
	@Override
	public String getDescription() {
		return button.getDescription();
	}

	/**
	 * Sets description (tooltip) for both the primary and popup buttons.
	 */
	@Override
	public void setDescription(String description) {
		button.setDescription(description);
		popupButton.setDescription(description);
	}

	/**
	 * Returns the description (tooltip) of the primary button.
	 */
	public String getButtonDescription() {
		return button.getDescription();
	}

	/**
	 * Sets description (tooltip) for the primary button.
	 */
	public void setButtonDescription(String description) {
		button.setDescription(description);
	}

	/**
	 * Returns the description (tooltip) of the popup button.
	 */
	public String getPopupButtonDescription() {
		return popupButton.getDescription();
	}

	/**
	 * Sets description (tooltip) for the popup button.
	 */
	public void setPopupButtonDescription(String description) {
		popupButton.setDescription(description);
	}

	@Override
	public void setStyleName(String style) {
		super.setStyleName(style);
		button.setStyleName(style);
		popupButton.setStyleName(style);
	}

	@Override
	public void addStyleName(String style) {
		super.addStyleName(style);
		button.addStyleName(style);
		popupButton.addStyleName(style);
	}

	@Override
	public void removeStyleName(String style) {
		super.removeStyleName(style);
		button.removeStyleName(style);
		popupButton.removeStyleName(style);
	}

	/**
	 * Set the content component of the popup.
	 * 
	 * @param component
	 *            the component to be displayed in the popup.
	 */
	public void setComponent(Component component) {
		popupButton.setComponent(component);
	}

	public void addComponent(Component c) {
		popupButton.addComponent(c);
	}

	public void removeComponent(Component c) {
		popupButton.removeComponent(c);
	}

	public void removeAllComponents() {
		popupButton.removeAllComponents();
	}

	public void replaceComponent(Component oldComponent, Component newComponent) {
		popupButton.replaceComponent(oldComponent, newComponent);
	}

	public Iterator<Component> getComponentIterator() {
		return popupButton.getComponentIterator();
	}

	public void requestRepaintAll() {
		popupButton.requestRepaintAll();
	}

	public void moveComponentsFrom(ComponentContainer source) {
		popupButton.moveComponentsFrom(source);
	}

	public void addListener(ComponentAttachListener listener) {
		popupButton.addListener(listener);
	}

	public void removeListener(ComponentAttachListener listener) {
		popupButton.removeListener(listener);
	}

	public void addListener(ComponentDetachListener listener) {
		popupButton.addListener(listener);
	}

	public void removeListener(ComponentDetachListener listener) {
		popupButton.removeListener(listener);
	}

	public interface SplitButtonClickListener extends Serializable {

		/**
		 * Called when a {@link Button} has been clicked. A reference to the
		 * button is given by {@link ClickEvent#getButton()}.
		 * 
		 * @param event
		 *            An event containing information about the click.
		 */
		public void splitButtonClick(SplitButtonClickEvent event);

	}

	public class SplitButtonClickEvent extends Component.Event {

		private ClickEvent originalClickEvent;

		public SplitButtonClickEvent(Component source,
				ClickEvent originalClickEvent) {
			super(source);
			this.originalClickEvent = originalClickEvent;
		}

		/**
		 * Gets the SplitButton where the event occurred.
		 * 
		 * @return the Source of the event.
		 */
		public SplitButton getSplitButton() {
			return (SplitButton) getSource();
		}

		/**
		 * Returns the mouse position (x coordinate) when the click took place.
		 * The position is relative to the browser client area.
		 * 
		 * @return The mouse cursor x position or -1 if unknown
		 */
		public int getClientX() {
			return originalClickEvent.getClientX();
		}

		/**
		 * Returns the mouse position (y coordinate) when the click took place.
		 * The position is relative to the browser client area.
		 * 
		 * @return The mouse cursor y position or -1 if unknown
		 */
		public int getClientY() {
			return originalClickEvent.getClientY();
		}

		/**
		 * Returns the relative mouse position (x coordinate) when the click
		 * took place. The position is relative to the clicked component.
		 * 
		 * @return The mouse cursor x position relative to the clicked layout
		 *         component or -1 if no x coordinate available
		 */
		public int getRelativeX() {
			return originalClickEvent.getRelativeX();
		}

		/**
		 * Returns the relative mouse position (y coordinate) when the click
		 * took place. The position is relative to the clicked component.
		 * 
		 * @return The mouse cursor y position relative to the clicked layout
		 *         component or -1 if no y coordinate available
		 */
		public int getRelativeY() {
			return originalClickEvent.getRelativeY();
		}

		/**
		 * Checks if the Alt key was down when the mouse event took place.
		 * 
		 * @return true if Alt was down when the event occured, false otherwise
		 *         or if unknown
		 */
		public boolean isAltKey() {
			return originalClickEvent.isAltKey();
		}

		/**
		 * Checks if the Ctrl key was down when the mouse event took place.
		 * 
		 * @return true if Ctrl was pressed when the event occured, false
		 *         otherwise or if unknown
		 */
		public boolean isCtrlKey() {
			return originalClickEvent.isCtrlKey();
		}

		/**
		 * Checks if the Meta key was down when the mouse event took place.
		 * 
		 * @return true if Meta was pressed when the event occured, false
		 *         otherwise or if unknown
		 */
		public boolean isMetaKey() {
			return originalClickEvent.isMetaKey();
		}

		/**
		 * Checks if the Shift key was down when the mouse event took place.
		 * 
		 * @return true if Shift was pressed when the event occured, false
		 *         otherwise or if unknown
		 */
		public boolean isShiftKey() {
			return originalClickEvent.isShiftKey();
		}
	}

	public void addClickListener(SplitButtonClickListener listener) {
		if (listener != null) {
			clickListeners.add(listener);
		}
	}

	public void removeClickListener(SplitButtonClickListener listener) {
		if (listener != null) {
			clickListeners.remove(listener);
		}
	}

	public void buttonClick(ClickEvent event) {
		SplitButtonClickEvent splitButtonClickEvent = new SplitButtonClickEvent(
				this, event);
		for (SplitButtonClickListener listener : clickListeners) {
			listener.splitButtonClick(splitButtonClickEvent);
		}
	}

	/**
	 * This event is received by the PopupVisibilityListeners when the
	 * visibility of the popup changes. You can get the new visibility directly
	 * with {@link #isPopupVisible()}, or get the PopupButton that produced the
	 * event with {@link #getPopupButton()}.
	 * 
	 */
	public class SplitButtonPopupVisibilityEvent extends Event {

		private static final long serialVersionUID = 3170716121022820317L;

		public SplitButtonPopupVisibilityEvent(SplitButton source) {
			super(source);
		}

		/**
		 * Get the PopupButton instance that is the source of this event.
		 * 
		 * @return the source PopupButton
		 */
		public SplitButton getSplitButton() {
			return (SplitButton) getSource();
		}

		/**
		 * Returns the current visibility of the popup.
		 * 
		 * @return true if the popup is visible
		 */
		public boolean isPopupVisible() {
			return getSplitButton().isPopupVisible();
		}
	}

	/**
	 * Defines a listener that can receive a SplitButtonPopupVisibilityEvent
	 * when the visibility of the popup changes.
	 * 
	 */
	public interface SplitButtonPopupVisibilityListener extends Serializable {
		/**
		 * Pass to {@link SplitButton#SplitButtonPopupVisibilityEvent} to start
		 * listening for popup visibility changes.
		 * 
		 * @param event
		 *            the event
		 * 
		 * @see {@link SplitButtonPopupVisibilityEvent}
		 * @see {@link SplitButton#addSplitButtonPopupVisibilityListener(SplitButtonPopupVisibilityListener)}
		 */
		public void splitButtonPopupVisibilityChange(
				SplitButtonPopupVisibilityEvent event);
	}

	/**
	 * Add a listener that is called whenever the visibility of the popup is
	 * changed.
	 * 
	 * @param listener
	 *            the listener to add
	 * @see SplitButtonPopupVisibilityListener
	 * @see SplitButtonPopupVisibilityEvent
	 * @see #removePopupVisibilityListener(SplitButtonPopupVisibilityListener)
	 * 
	 */
	public void addPopupVisibilityListener(
			SplitButtonPopupVisibilityListener listener) {
		if (listener != null) {
			popupVisibilityListeners.add(listener);
		}
	}

	/**
	 * Removes a previously added listener, so that it no longer receives events
	 * when the visibility of the popup changes.
	 * 
	 * @param listener
	 *            the listener to remove
	 * @see SplitButtonPopupVisibilityListener
	 * @see #addPopupVisibilityListener(SplitButtonPopupVisibilityListener)
	 */
	public void removePopupVisibilityListener(
			SplitButtonPopupVisibilityListener listener) {
		if (listener != null) {
			popupVisibilityListeners.remove(listener);
		}
	}

	public void popupVisibilityChange(PopupVisibilityEvent event) {
		SplitButtonPopupVisibilityEvent newEvent = new SplitButtonPopupVisibilityEvent(
				this);
		for (SplitButtonPopupVisibilityListener listener : popupVisibilityListeners) {
			listener.splitButtonPopupVisibilityChange(newEvent);
		}
	}
}
