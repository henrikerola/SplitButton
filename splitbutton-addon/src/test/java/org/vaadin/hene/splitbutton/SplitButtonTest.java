package org.vaadin.hene.splitbutton;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.vaadin.hene.popupbutton.PopupButton.PopupVisibilityEvent;
import org.vaadin.hene.splitbutton.SplitButton.SplitButtonPopupVisibilityEvent;
import org.vaadin.hene.splitbutton.SplitButton.SplitButtonPopupVisibilityListener;

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

}
