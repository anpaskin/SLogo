package view.SidePane;

import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import model.ImmutableTurtle;
import view.API.SubcomponentViewAPI;
import view.API.CommandIOAPI.TurtleListener;
import view.CommandIO.TurtleView;

/**
 * Class allowing users to see attributes of current turtle.
 * 
 * @author DavidTran
 *
 */
public class TurtleStateView implements SubcomponentViewAPI, TurtleListener{

	private TextArea ta;
	private ResourceBundle myResources = ResourceBundle.getBundle("resources.view/view");
	private ImmutableTurtle turtle;
	
	public TurtleStateView(double height) {

		ta = new TextArea();
		ta.setMinHeight(height);
		ta.setWrapText(true);
		ta.setEditable(false);
		ta.appendText(myResources.getString("TurtleStateView"));
	}

	private void update() {
		ta.clear();
		
		ta.appendText(myResources.getString("TurtleStateView") + "\n\n");
		ta.appendText("ID: " + Integer.toString(turtle.getID()) + "\n");
		ta.appendText("X: " + Double.toString(turtle.getX()) + "\n");
		ta.appendText("Y: " + Double.toString(turtle.getY()) + "\n");
		ta.appendText("Heading: " + Double.toString(turtle.getHeading()) + "\n");
		ta.appendText("Pen Down: " + Boolean.toString(turtle.getPenDown()) + "\n");
		ta.appendText("Pen Color: " + TurtleView.colorList.get(turtle.getPenColorIndex()) + "\n");
		ta.appendText("Visibility: " + Boolean.toString(turtle.isVisible()) + "\n");
		
	}

	@Override
	public Parent getParent() {
		return ta;
	}

	@Override
	public void imageChange(int imageIndex) {
		update();
	}

	@Override
	public void setTurtle(ImmutableTurtle turtle) {
		this.turtle = turtle;
	}

	@Override
	public void locationChange(double newX, double newY) {
		update();
	}

	@Override
	public void headingChange(double newAngle) {
		update();
	}

	@Override
	public void penChange(boolean down) {
		update();
	}

	@Override
	public void visibilityChange(boolean isVisible) {
		update();
	}

	@Override
	public void penColorChange(int colorIndex) {
		update();
	}

	@Override
	public void clearScreen() {
		update();
	}

	@Override
	public void addTurtleStateListener(TurtleStateView l) {
		update();
	}

}
