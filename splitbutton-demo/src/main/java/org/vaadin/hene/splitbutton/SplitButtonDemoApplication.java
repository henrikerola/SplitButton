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
import com.vaadin.ui.Layout;
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

		splitButton = new SplitButton("New Document");
		splitButton.setIcon(new ThemeResource(
				"../runo/icons/16/document-add.png"));
		splitButton.setComponent(createSplitButtonPopupContent());
		mainWindow.addComponent(splitButton);

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
	}

	private Layout createSplitButtonPopupContent() {
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setSizeUndefined();

		layout.addComponent(createButton("New Word Document",
				"document-doc.png"));
		layout.addComponent(createButton("New Excel Document",
				"document-xsl.png"));
		layout.addComponent(createButton("New PowerPoint Document",
				"document-ppt.png"));
		layout.addComponent(createButton("New PDF Document", "document-pdf.png"));

		return layout;
	}

	private Button createButton(String caption, String icon) {
		Button button = new Button(caption, new ClickListener() {
			public void buttonClick(ClickEvent event) {
				splitButton.setPopupVisible(false);
			}
		});
		button.setStyleName(Reindeer.BUTTON_LINK);
		button.setIcon(new ThemeResource("../runo/icons/16/" + icon));
		return button;
	}

}
