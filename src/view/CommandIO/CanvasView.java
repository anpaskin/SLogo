package view.CommandIO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import view.API.CommandIOAPI.CanvasDisplay;
import view.API.ToolbarAPI.BackgroundOptionListener;

/**
 * Class for setting up the canvas where turtles are placed.
 * 
 * @author DavidTran
 *
 */
public class CanvasView extends Pane implements CanvasDisplay, BackgroundOptionListener {

	private ResourceBundle myResources = ResourceBundle.getBundle("resources.view/choicebox");
	private final List<String> colorList = new ArrayList<String>(
			Arrays.asList(myResources.getString("BackgroundColors").replaceAll("\\s+", "").split(",")));

	/**
	 * Constructor
	 */
	public CanvasView(double width, double height) {
		this.setBackgroundColor(Color.valueOf(colorList.get(0)));
		this.setScaleY(-1);
		this.setMaxWidth(width);
		this.setMaxHeight(height);
	}

	@Override
	public void setBackgroundColor(Color c) {
		this.setBackground(new Background(new BackgroundFill(c, CornerRadii.EMPTY, Insets.EMPTY)));
	}

	@Override
	public void backgroundColorChange(int colorIndex) {
		this.setBackgroundColor(Color.valueOf(colorList.get(colorIndex)));
	}

}
