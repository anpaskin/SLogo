package view.Animation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.ImmutableTurtle;
import view.ErrorWindow;
import view.Toolbar.TurtleImageOptionListener;

/**
 * Class to make the turtle viewable.
 *
 * @author DavidTran
 */
public class TurtleView implements TurtleListener, TurtleImageOptionListener {

	private static final double WIDTH = 35;
	private static final double HEIGHT = 35;
	private static final ResourceBundle myResources = ResourceBundle.getBundle("resources.view/choicebox");
	private static final ArrayList<String> imageNameList = new ArrayList<String>(new ArrayList<String>(
			Arrays.asList(myResources.getString("TurtleImages").replaceAll("\\s+", "").split(","))));
	public static final List<String> colorList = new ArrayList<String>(
			Arrays.asList(myResources.getString("PenColors").replaceAll("\\s+", "").split(",")));

	private ImmutableTurtle turtle;
	private ImageView myView;
	private int myPenColorIndex;
	private boolean myPenIsDown;
	private Pane myParent;
	private double myHeading;
	private boolean myIsActive;
	private int myID;
	private List<Image> imageList = new ArrayList<Image>();

	private double myOffsetX;
	private double myOffsetY;
	private double myPrevNewX;
	private double myPrevNewY;

	public TurtleView(Pane parent, Image image) {

		myView = setupImageView(image);
		myIsActive = true;
		myParent = parent;

		for (String s : imageNameList) {
			Image fileImage = new Image(getClass().getClassLoader().getResourceAsStream("resources/images/" + s));
			imageList.add(fileImage);
		}
	}

	/*************************** PUBLIC METHODS ********************************/

	@Override
	public void setTurtle(ImmutableTurtle turtle) {

		myID = turtle.getID();
		System.out.println("ID: " + myID);
		myHeading = turtle.getHeading();
		myPenColorIndex = turtle.getPenColorIndex();
		myPenIsDown = turtle.getPenDown();
		myView.setVisible(turtle.isVisible());

		this.turtle = turtle;

		myOffsetX = myParent.getLayoutX();
		myOffsetY = myParent.getLayoutY();
		myView.setX(turtle.getX() + myOffsetX);
		myView.setY(turtle.getY() + myOffsetY);
		myPrevNewX = turtle.getX() + myOffsetX;
		myPrevNewY = turtle.getY() + myOffsetY;

	}

	@Override
	public void locationChange(double newX, double newY) {
		if (myIsActive) {
			// compensate for center offset since center if not (0,0), returns value
			// referenced from center.
			double offsetNewX = newX + myOffsetX;
			double offsetNewY = newY + myOffsetY;
			System.out.println("Turtle ID: " + myID);
			System.out.println("prevX: " + myPrevNewX + " | prevY: " + myPrevNewY);
			System.out.println("offsetNewX: " + offsetNewX + " | offsetNewY: " + offsetNewY);

			double coordInsideX = offsetNewX % (myOffsetX * 2);
			double coordInsideY = offsetNewY % (myOffsetY * 2);

			boolean rightBound = offsetNewX - myPrevNewX + myView.getX() >= myOffsetX * 2;
			boolean upperBound = offsetNewY - myPrevNewY + myView.getY() >= myOffsetY * 2;
			boolean leftBound = offsetNewX - myPrevNewX + myView.getX() <= 0;
			boolean lowerBound = offsetNewY - myPrevNewY + myView.getY() <= 0;

			double prevX = myPrevNewX;
			double prevY = myPrevNewY;
			myPrevNewX = offsetNewX;
			myPrevNewY = offsetNewY;
			double distX = offsetNewX - prevX + myView.getX();
			double distY = offsetNewY - prevY + myView.getY();

			Line line;

			if (rightBound) {
				while (distX >= (myOffsetX * 2)) {

					if (distY > prevY + 0.05 || distY < prevY - 0.05)
						distY = myView.getY()
								+ (offsetNewY - prevY) * ((myOffsetX * 2 - myView.getX()) / (offsetNewX - prevX));
					if (myPenIsDown) {
						line = new Line(myView.getX(), myView.getY(), myOffsetX * 2, distY);
						line.setStroke(Color.valueOf(colorList.get(myPenColorIndex)));
						myParent.getChildren().add(line);
					}
					myView.setX(0);
					myView.setY(distY);

					offsetNewX = offsetNewX - myOffsetX * 2;
					distX = offsetNewX - prevX + myView.getX();
					System.out.println("OOB");
					System.out.println("Now x= " + myView.getX() + " | y=" + myView.getY());
					System.out.println("Now offsetnewx= " + offsetNewX + " | offsetnewy=" + offsetNewY);
				}
			}
			// else if (leftBound) {
			// while (distX <= 0) {
			//
			// if (distY > prevY+0.05 || distY < prevY - 0.05)
			// distY = myView.getY() + (offsetNewY - prevY)*((0 + myView.getX()) /
			// (offsetNewX - prevX));
			// if (myPenIsDown) {
			// line = new Line(myView.getX(), myView.getY(), 0, distY);
			// line.setStroke(Color.valueOf(colorList.get(myPenColorIndex)));
			// myParent.getChildren().add(line);
			// }
			// myView.setX(myOffsetX * 2);
			// myView.setY(distY);
			//
			// offsetNewX = offsetNewX + myOffsetX * 2;
			// distX = offsetNewX - prevX + myView.getX();
			// System.out.println("OOB");
			// System.out.println("Now x= " + myView.getX() + " | y=" + myView.getY());
			// System.out.println("Now offsetnewx= " + offsetNewX + " | offsetnewy=" +
			// offsetNewY);
			// }
			// }
			// else if (rightBound) {
			// line = new Line(myView.getX(), myView.getY(), myOffsetX * 2 - myPrevNewX,
			// coordInsideY);
			// line.setStroke(myPenColor);
			// myParent.getChildren().add(line);
			// myView.setX(0);
			// System.out.println("OOB");
			// }
			// else if (upperBound) {
			// line = new Line(myView.getX(), myView.getY(), coordInsideX, myOffsetY * 2);
			// line.setStroke(myPenColor);
			// myParent.getChildren().add(line);
			// myView.setY(0);
			// System.out.println("OOB");
			// }

			// line = new Line(myView.getX(), myView.getY(), coordInsideX, coordInsideY);
			// line.setStroke(myPenColor);
			// myParent.getChildren().add(line);
			// myView.setX(coordInsideX);
			// myView.setY(coordInsideY);

			if (myPenIsDown) {
				line = new Line(myView.getX(), myView.getY(), coordInsideX, coordInsideY);
				line.setStroke(Color.valueOf(colorList.get(myPenColorIndex)));
				myParent.getChildren().add(line);
			}

			myView.setX(coordInsideX);
			myView.setY(coordInsideY);

			System.out.println("OOB");
			System.out.println("Final x= " + myView.getX() + " | y=" + myView.getY());

			// System.out.println("LayoutX: " + myOffsetX + " LayoutY: " + myOffsetY);
			// System.out.println("myX: " + myView.getX() + " | myY: " + myView.getY());
			// System.out.println("newX: " + newX + " | newY: " + newY);
			// System.out.println("offsetNewX: " + offsetNewX + " | offsetNewY: " +
			// offsetNewY);

		}
	}

	@Override
	public void headingChange(double newHeading) {
		if (myIsActive) {
			myHeading = -newHeading;
			myView.setRotate(180 - myHeading);
		}
	}

	@Override
	public void penChange(boolean newState) {
		if (myIsActive) {
			myPenIsDown = newState;
		}
	}

	@Override
	public void penColorChange(int index) {
		try {
			if (myIsActive) {
				myPenColorIndex = index;
			}
		} catch (Exception e) {
			new ErrorWindow(e.getMessage());
		}
	}

	@Override
	public void visibilityChange(boolean visibility) {
		if (myIsActive) {
			myView.setVisible(visibility);
		}
	}

	@Override
	public void activeToggle(boolean active) {
		myIsActive = active;
	}

	@Override
	public void clearScreen() {
		if (myIsActive) {
			myParent.getChildren().remove(myView);
			myIsActive = false;
		}
	}

	@Override
	public void imageChange(int imageIndex) {
		if (myIsActive)
			myView.setImage(imageList.get(imageIndex));
	}

	/**
	 * Returns image associated with turtle.
	 */
	public ImageView getImageView() {
		return myView;
	}

	/*************************** PRIVATE METHODS ********************************/

	private ImageView setupImageView(Image image) {
		ImageView ret = new ImageView(image);
		ret.setFitWidth(WIDTH);
		ret.setFitHeight(HEIGHT);
		ret.setLayoutX(-WIDTH / 2);
		ret.setLayoutY(-HEIGHT / 2);
		ret.setRotate(180);
		ret.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 15, 0, 0, 0)");
		ret.setOnMouseClicked(e -> clicked());
		ret.setOnMouseEntered(e -> entered());
		ret.setOnMouseExited(e -> exited());
		return ret;
	}

	/**
	 * Make toggling viewable.
	 */
	private void clicked() {
		System.out.println("Clicked turtle");
		if (myIsActive)
			myView.setStyle("-fx-background-color:transparent");
		else {
			myView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 15, 0, 0, 0)");
		}
		activeToggle(!myIsActive);
		turtle.setActive(myIsActive);
		System.out.println(myIsActive);
	}

	/**
	 * Make mouse hovering noticeable.
	 */
	private void entered() {
		if (!myIsActive)
			myView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0)");
	}

	private void exited() {
		if (!myIsActive)
			myView.setStyle("-fx-background-color:transparent;");
	}
}
