package org.vaadin.hene.splitbutton;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.vaadin.hene.splitbutton.SplitButton.PopupButton;
import org.vaadin.hene.splitbutton.SplitButton.SplitButtonPopupVisibilityEvent;
import org.vaadin.hene.splitbutton.SplitButton.SplitButtonPopupVisibilityListener;

import com.vaadin.ui.Button;

public class SplitButtonTest {

	private SplitButton splitButton;

	@Before
	public void setUp() throws Exception {
		splitButton = new SplitButton();
	}

	@Test
	public void testRemoveVisibilityListenerDuringVisibilityChange() {
		splitButton
		.addPopupVisibilityListener(new SplitButtonPopupVisibilityListener() {
			public void splitButtonPopupVisibilityChange(
					SplitButtonPopupVisibilityEvent event) {
				splitButton.removePopupVisibilityListener(this);
			}
		});
		splitButton.popupVisibilityChange(null);
	}
	
	@Test
	public void testSetReadOnlyShouldRequestRepaintAllComponents() {
		Button button = mock(Button.class);
		PopupButton popupButton = mock(PopupButton.class);
		
		SplitButton splitButton = new SplitButton(button, popupButton);
		SplitButton spy = spy(splitButton);
		
		spy.setEnabled(true);

		verify(spy).requestRepaint();
		verify(button).requestRepaint();
		verify(popupButton).requestRepaintAll();
	}

}
