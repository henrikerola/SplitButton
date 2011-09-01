package org.vaadin.hene.splitbutton;

import org.vaadin.hene.splitbutton.SplitButton.SplitButtonClickEvent;
import org.vaadin.hene.splitbutton.SplitButton.SplitButtonClickListener;
import org.vaadin.hene.splitbutton.SplitButton.SplitButtonPopupVisibilityEvent;
import org.vaadin.hene.splitbutton.SplitButton.SplitButtonPopupVisibilityListener;

import com.vaadin.Application;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ChameleonTheme;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
public class SplitButtonDemoApplication extends Application {

	private SplitButton splitButton;

	private Component reindeerTab;

	private Component chameleonTab;

	@Override
	public void init() {
		Window mainWindow = new Window("SplitButton Demo Application");
		setMainWindow(mainWindow);

		TabSheet tabSheet = new TabSheet();
		tabSheet.addListener(new SelectedTabChangeListener() {
			public void selectedTabChange(SelectedTabChangeEvent event) {
				Component selectedTab = event.getTabSheet().getSelectedTab();
				if (reindeerTab == selectedTab) {
					setTheme("reindeer");
				} else if (chameleonTab == selectedTab) {
					setTheme("splitbuttondemo-chameleon");
				}
			}
		});
		tabSheet.setSizeFull();
		mainWindow.setContent(tabSheet);

		tabSheet.addTab(createChameleonTab(), "Chameleon", null);
		tabSheet.addTab(createReindeerTab(), "Reindeer", null);
	}

	private Component createChameleonTab() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setMargin(true);
		chameleonTab = layout;
		
		SplitButton splitButton = new SplitButton();
		splitButton.setIcon(new ThemeResource("icons/emotion_smile.png"));
		splitButton.setComponent(createSplitButtonPopupContent1());
		splitButton.setStyleName(ChameleonTheme.BUTTON_ICON_ON_TOP);
		splitButton.addStyleName(SplitButton.STYLE_CHAMELEON);
		layout.addComponent(splitButton);

		return chameleonTab;
	}

	private Component createReindeerTab() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setMargin(true);
		reindeerTab = layout;

		splitButton = new SplitButton("New Document");
		splitButton.setIcon(new ThemeResource(
				"../runo/icons/16/document-add.png"));
		splitButton.setComponent(createSplitButtonPopupContent());

		splitButton.addClickListener(new SplitButtonClickListener() {
			public void splitButtonClick(SplitButtonClickEvent event) {
				getMainWindow().showNotification("Button clicked!");
			}
		});

		splitButton
				.addPopupVisibilityListener(new SplitButtonPopupVisibilityListener() {
					public void splitButtonPopupVisibilityChange(
							SplitButtonPopupVisibilityEvent event) {
						String msg = "Popup closed";
						if (event.getSplitButton().isPopupVisible()) {
							msg = "Popup opened";
						}
						getMainWindow().showNotification(msg);
					}
				});
		layout.addComponent(splitButton);

		return layout;
	}

	private Layout createSplitButtonPopupContent() {
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setSizeUndefined();

		layout.addComponent(createButton("New Word Document",
				"../runo/icons/16/document-doc.png"));
		layout.addComponent(createButton("New Excel Document",
				"../runo/icons/16/document-xsl.png"));
		layout.addComponent(createButton("New PowerPoint Document",
				"../runo/icons/16/document-ppt.png"));
		layout.addComponent(createButton("New PDF Document",
				"../runo/icons/16/document-pdf.png"));

		return layout;
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
