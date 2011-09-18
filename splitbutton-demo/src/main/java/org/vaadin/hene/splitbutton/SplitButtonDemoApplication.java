package org.vaadin.hene.splitbutton;

import org.vaadin.hene.splitbutton.SplitButton.SplitButtonClickEvent;
import org.vaadin.hene.splitbutton.SplitButton.SplitButtonClickListener;
import org.vaadin.hene.splitbutton.SplitButton.SplitButtonPopupVisibilityEvent;
import org.vaadin.hene.splitbutton.SplitButton.SplitButtonPopupVisibilityListener;

import com.vaadin.Application;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
public class SplitButtonDemoApplication extends Application {

	private SplitButton splitButton;

	@Override
	public void init() {
		Window mainWindow = new Window("SplitButton Demo Application");
		setMainWindow(mainWindow);
		
		setTheme("splitbuttondemo-reindeer");

		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setSizeFull();
		mainLayout.setMargin(true);
		mainWindow.setContent(mainLayout);

		Label headerLabel = new Label("SplitButton");
		headerLabel.setStyleName(Reindeer.LABEL_H1);
		mainLayout.addComponent(headerLabel);

		TabSheet tabSheet = new TabSheet();
		tabSheet.setSizeFull();
		mainLayout.addComponent(tabSheet);
		mainLayout.setExpandRatio(tabSheet, 1);

		tabSheet.addTab(createChameleonTab(), "Chameleon", null);
		tabSheet.addTab(createReindeerTab(), "Reindeer", null);
	}

	private Component createChameleonTab() {
		Embedded embedded = new Embedded();
		embedded.setSource(new ExternalResource(
				"/splitbutton/chameleon?restartApplication"));
		embedded.setType(Embedded.TYPE_BROWSER);
		embedded.setSizeFull();
		return embedded;
	}

	private Component createReindeerTab() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setMargin(true);

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
