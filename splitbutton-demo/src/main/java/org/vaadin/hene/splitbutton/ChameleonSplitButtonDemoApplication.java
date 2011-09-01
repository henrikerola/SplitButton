package org.vaadin.hene.splitbutton;

import com.vaadin.Application;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ChameleonTheme;
import com.vaadin.ui.themes.Reindeer;

public class ChameleonSplitButtonDemoApplication extends Application {
	
	private SplitButton splitButton;

	@Override
	public void init() {
		Window mainWindow = new Window("SplitButton with Chameleon Theme Demo Application");
		setMainWindow(mainWindow);
		
		HorizontalLayout layout = new HorizontalLayout();
		layout.setMargin(true);
		mainWindow.setContent(layout);
		
		splitButton = new SplitButton();
		splitButton.setIcon(new ThemeResource("icons/emotion_smile.png"));
		splitButton.setComponent(createSplitButtonPopupContent1());
		splitButton.setStyleName(ChameleonTheme.BUTTON_ICON_ON_TOP);
		splitButton.addStyleName(SplitButton.STYLE_CHAMELEON);
		layout.addComponent(splitButton);
		
		setTheme("splitbuttondemo-chameleon");

	}
	
	private Layout createSplitButtonPopupContent1() {
		GridLayout layout = new GridLayout(3, 3);
		layout.setSpacing(true);
		layout.setSizeUndefined();

		layout.addComponent(createButton(null, "icons/emotion_evilgrin.png"));
		layout.addComponent(createButton(null, "icons/emotion_grin.png"));
		layout.addComponent(createButton(null, "icons/emotion_happy.png"));
		layout.addComponent(createButton(null, "icons/emotion_suprised.png"));
		layout.addComponent(createButton(null, "icons/emotion_tongue.png"));
		layout.addComponent(createButton(null, "icons/emotion_unhappy.png"));
		layout.addComponent(createButton(null, "icons/emotion_waii.png"));
		layout.addComponent(createButton(null, "icons/emotion_wink.png"));

		return layout;
	}
	
	private Button createButton(String caption, String icon) {
		Button button = new Button(caption, new ClickListener() {
			public void buttonClick(ClickEvent event) {
				splitButton.setPopupVisible(false);
			}
		});
		button.setStyleName(Reindeer.BUTTON_LINK);
		button.setIcon(new ThemeResource(icon));
		return button;
	}

}
