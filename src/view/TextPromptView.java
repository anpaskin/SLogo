package view;

import java.util.function.Consumer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.API.TextPromptDisplay;

public class TextPromptView extends HBox implements TextPromptDisplay {
	private TextArea tp;
	private Button runButton;
	private Button clearButton;
	private VBox buttonPanel;

	private Consumer<String> commandConsumer;

	public TextPromptView(double width, double height, Consumer<String> commandConsumer) {
		this.setMaxWidth(width);
		this.setMaxHeight(height);

		tp = new TextArea(); 
		tp.setPrefWidth(width * .75);
		tp.setPrefHeight(height);

		this.commandConsumer = commandConsumer;

		this.getChildren().add(tp);

		addButtons(width - tp.getPrefWidth(), height / 2);

	}

	private void addButtons(double width, double height) {
		buttonPanel = new VBox();

		runButton = makeButton("Run", e -> enter());
		runButton.setPrefWidth(width);
		runButton.setPrefHeight(height);
		clearButton = makeButton("Clear", e -> clear());
		clearButton.setPrefWidth(width);
		clearButton.setPrefHeight(height);

		buttonPanel.getChildren().addAll(runButton, clearButton);
		this.getChildren().add(buttonPanel);
	}

	private Button makeButton(String label, EventHandler<ActionEvent> handler) {
		Button ret = new Button(label);
		ret.setOnAction(handler);
		return ret;
	}

	private void enter() {
		String code = tp.getText();
		commandConsumer.accept(code);
		clear();
	}

	private void clear() {
		tp.clear();
	}

	/*************************** PUBLIC METHODS ********************************/

}
