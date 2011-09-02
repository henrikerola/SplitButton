package org.vaadin.hene.splitbutton;

import org.vaadin.hene.popupbutton.PopupButton;

import com.vaadin.Application;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ChameleonTheme;
import com.vaadin.ui.themes.Reindeer;

public class ChameleonSplitButtonDemoApplication extends Application {

	private SplitButton splitButton1;
	private SplitButton splitButton2;

	@Override
	public void init() {
		Window mainWindow = new Window(
				"SplitButton with Chameleon Theme Demo Application");
		setMainWindow(mainWindow);

		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setMargin(true);
		mainLayout.setSizeFull();
		mainWindow.setContent(mainLayout);

		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		mainLayout.addComponent(layout);
		mainLayout.setExpandRatio(layout, 1);

		splitButton1 = new SplitButton();
		splitButton1.setIcon(new ThemeResource("icons/emotion_smile.png"));
		splitButton1.setComponent(createSplitButtonPopupContent1());
		splitButton1.setStyleName(ChameleonTheme.BUTTON_ICON_ON_TOP);
		splitButton1.addStyleName(SplitButton.STYLE_CHAMELEON);
		layout.addComponent(splitButton1);

		splitButton2 = new SplitButton("Accept");
		splitButton2.setIcon(new ThemeResource("icons/tick.png"));
		splitButton2.addStyleName(SplitButton.STYLE_CHAMELEON);
		splitButton2.setComponent(createSplitButtonPopupContent2());
		layout.addComponent(splitButton2);

		mainLayout.addComponent(createIcons());

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

	private Layout createSplitButtonPopupContent2() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100px");
		layout.addComponent(createButton("Decline", "icons/cross.png"));

		return layout;
	}

	private Layout createSplitButtonPopupContent3() {
		VerticalLayout layout = new VerticalLayout();
		layout.setWidth("200px");
		layout.setHeight("200px");

		Label label = new Label(
				"Popup can contain any Vaadin Component or ComponentContainer.");
		layout.addComponent(label);

		TextArea ta = new TextArea();
		ta.setSizeFull();
		layout.addComponent(ta);
		layout.setExpandRatio(ta, 1);

		return layout;
	}

	private Button createButton(String caption, String icon) {
		Button button = new Button(caption, new ClickListener() {
			public void buttonClick(ClickEvent event) {
				Component c = event.getButton().getParent().getParent();
				if (c instanceof PopupButton) {
					((PopupButton) c).setPopupVisible(false);
				}
			}
		});
		button.setStyleName(Reindeer.BUTTON_LINK);
		button.setIcon(new ThemeResource(icon));
		return button;
	}

	private Label createIcons() {
		Label label = new Label(
				"Icons from <a href=\"http://www.fatcow.com/free-icons\">FatCow.com</a>",
				Label.CONTENT_XHTML);
		label.setSizeUndefined();
		return label;
	}

}
