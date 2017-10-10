package edu.sla;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// This sample code is explained at http://docs.oracle.com/javafx/2/layout/builtin_layouts.htm

public class LayoutExample extends Application {

    // Adds two buttons to the HBox for the top region
    private void addCurrentAndProjectedButtons(HBox hbox) {
        // Create the 2 buttons
        Button buttonCurrent = new Button("Current");
        buttonCurrent.setPrefSize(100, 20);

        Button buttonProjected = new Button("Projected");
        buttonProjected.setPrefSize(100, 20);

        // Add the 2 buttons to the HBox
        // Since using HBox, the controls will be displayed in a row (horizontally)
        hbox.getChildren().addAll(buttonCurrent, buttonProjected);
    }

    // Adds help icon to the right side of the HBox for the top region
    private void addHelpIconRectangle(HBox hbox) {

        // Use StackPane to be able to layer Rectangle under help ? icon
        StackPane stack = new StackPane();

        // Create Rectangle that is under the help ? icon
        Rectangle helpIcon = new Rectangle(30.0, 25.0);
        helpIcon.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
                new Stop[]{
                        new Stop(0,Color.web("#4977A3")),
                        new Stop(0.5, Color.web("#B0C6DA")),
                        new Stop(1,Color.web("#9CB6CF")),}));
        helpIcon.setStroke(Color.web("#D0E6FA"));
        helpIcon.setArcHeight(3.5);
        helpIcon.setArcWidth(3.5);

        // Create the help ? icon which is layered on top of Rectangle
        Text helpText = new Text("?");
        helpText.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        helpText.setFill(Color.WHITE);
        helpText.setStroke(Color.web("#7080A0"));

        // Add the Rectangle, then the help ? icon next to the StackPane
        stack.getChildren().addAll(helpIcon, helpText);

        // Align all of the controls and shapes to the right of the HBox
        stack.setAlignment(Pos.CENTER_RIGHT);
        // Add offset to right for question mark to compensate for RIGHT alignment of all nodes
        StackPane.setMargin(helpText, new Insets(0, 10, 0, 0));

        // Add the StackPane (which has the Rectangle and help ? icon) to the HBox
        hbox.getChildren().add(stack);
        HBox.setHgrow(stack, Priority.ALWAYS);
    }

    // Adds a list of links to the VBox for the left region
    private void addVerticalListOfLinks(VBox vbox) {

        Text title = new Text("Data");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vbox.getChildren().add(title);

        Hyperlink options[] = new Hyperlink[] {
                new Hyperlink("Sales"),
                new Hyperlink("Marketing"),
                new Hyperlink("Distribution"),
                new Hyperlink("Costs")};

        for (int i=0; i < options.length; i++) {
            // Add offset to left side to indent from title
            VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
            vbox.getChildren().add(options[i]);
        }
    }

    // Add text and images to a 4 columns x 3 rows GridPane for the center region
    // NOTICE: 4x3 grid size is not specified, instead adding to grid tells grid its size
    private void addTextAndImagesToGrid(GridPane grid) {
        // "Sales:" category in column 2, row 1
        Text category = new Text("Sales:");
        category.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        grid.add(category, 1, 0);

        // "Current Year" title in column 3, row 1
        Text chartTitle = new Text("Current Year");
        chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        grid.add(chartTitle, 2, 0);

        // "Goods and Services" subtitle in columns 2-3, row 2
        Text chartSubtitle = new Text("Goods and Services");
        grid.add(chartSubtitle, 1, 1, 2, 1);

        // "House" icon in column 1, rows 1-2
        ImageView imageHouse = new ImageView(new Image("house.png"));
        grid.add(imageHouse, 0, 0, 1, 2);

        // "Goods 80%" left label in column 1 (bottom), row 3
        Text goodsPercent = new Text("Goods\n80%");
        GridPane.setValignment(goodsPercent, VPos.BOTTOM);
        grid.add(goodsPercent, 0, 2);

        // "Pie Chart" icon in columns 2-3, row 3
        ImageView imageChart = new ImageView(new Image("piechart.png"));
        grid.add(imageChart, 1, 2, 2, 1);

        // "Services 20%" right label in column 4 (top), row 3
        Text servicesPercent = new Text("Services\n20%");
        GridPane.setValignment(servicesPercent, VPos.TOP);
        grid.add(servicesPercent, 3, 2);
    }

    // Adds Save/Cancel buttons to the hbox
    private void addSaveCancelButtons(HBox hb) {
        Button buttonSave = new Button("Save");
        Button buttonCancel = new Button("Cancel");
        hb.getChildren().addAll(buttonSave, buttonCancel);
    }

    // Adds 8 icons to a tile pane
    private void addChartTypeIcons(TilePane tile) {
        for (int i=0; i < 8; i++) {
            tile.getChildren().add(new ImageView(new Image("chart_"+(i+1)+".png")));
        }
    }

    @Override
    public void start(Stage stage) {

        // Use a BorderPane as the root for scene
        // A border pane has 5 regions to put controls: Top, Bottom, Left, Right, and Center
        BorderPane border = new BorderPane();

        //////// HBox in Top Region ///// BEGIN ///////////////////////////////////////////////
        // HBox lays out controls in a row (horizontally) in order added from left to right
        // Create an HBox for the buttons and help icon in the Top region of the BorderPane
        HBox topHBox = new HBox();
        // Configure padding and spacing of controls in this HBox
        topHBox.setPadding(new Insets(15, 12, 15, 12)); // Set all sides' padding
        topHBox.setSpacing(10);                                                // Gap between nodes
        topHBox.setStyle("-fx-background-color: #336699;");                    // Change background color

        // Add the buttons and help icon to the HBox
        addCurrentAndProjectedButtons(topHBox);
        addHelpIconRectangle(topHBox);

        // Set the Top region of the BorderPane to be this HBox
        border.setTop(topHBox);
        //////// HBox in Top Region ///// END ///////////////////////////////////////////////

        //////// VBox in Left Region ///// BEGIN ///////////////////////////////////////////////
        // VBox lays out controls in a column (vertically) in order added from top to bottom
        // Create a VBox for the 5 links in the Left region of the BorderPane
        VBox leftVBox = new VBox();
        // Configure padding and spacing of controls in this VBox
        leftVBox.setPadding(new Insets(10)); // Set all sides' padding to 10
        leftVBox.setSpacing(8);                               // Gap between nodes

        // Add the 5 links to the VBox
        addVerticalListOfLinks(leftVBox);

        // Set the Left region of the BorderPane to be this VBox
        border.setLeft(leftVBox);
        //////// VBox in Left Region ///// END  ///////////////////////////////////////////////

        //////// AnchorPane in Center Region /////// BEGIN  ///////////////////////////////////////////////
        // AnchorPane lays out controls in both top/bottom/right/left regions, while anchoring them in place
        // Create AnchorPane that will contain labels, pie charts and save/cancel buttons
        AnchorPane centerAnchorPane = new AnchorPane();

        // GridPane lays out controls in a grid where controls can take up 1 or multiple cells in grid
        GridPane centerTopGrid = new GridPane();
        // Configure the gaps/padding between cells in grid
        centerTopGrid.setHgap(10);
        centerTopGrid.setVgap(10);
        centerTopGrid.setPadding(new Insets(0, 10, 0, 10));
        // Add the labels and icons to the grid
        addTextAndImagesToGrid(centerTopGrid);
        // Tell the grid to draw its grid lines for educational purposes
        centerTopGrid.setGridLinesVisible(true);

        // HBox will layout Save/Cancel buttons horizontally
        HBox saveCancelButtonsHBox = new HBox();
        // Configure the spacing/padding between controls in HBox
        saveCancelButtonsHBox.setPadding(new Insets(0, 10, 10, 10));
        saveCancelButtonsHBox.setSpacing(10);
        // Add the Save/Cancel buttons to the hbox
        addSaveCancelButtons(saveCancelButtonsHBox);

        // Add the grid and the hbox to AnchorPane
        centerAnchorPane.getChildren().addAll(centerTopGrid, saveCancelButtonsHBox);
        // Anchor grid to top
        AnchorPane.setTopAnchor(centerTopGrid, 10.0);
        // Anchor buttons to bottom right anchor
        AnchorPane.setBottomAnchor(saveCancelButtonsHBox, 8.0);
        AnchorPane.setRightAnchor(saveCancelButtonsHBox, 5.0);

        // Set the Center region of the BorderPane to be this AnchorPane
        border.setCenter(centerAnchorPane);
        //////// AnchorPane in Center Region /////// END ///////////////////////////////////////////////


        //////// TilePane in Right Region /////// BEGIN ////////////////////////////////////////////////
        // Create TilePane that lays out controls in simple grid
        TilePane rightTilePane = new TilePane();
        // Indicate to TilePane that it will have 2 columns per row
        rightTilePane.setPrefColumns(2);
        // Configure the gaps/padding between tiles in grid
        rightTilePane.setPadding(new Insets(5, 0, 5, 0));
        rightTilePane.setVgap(4);
        rightTilePane.setHgap(4);
        // Change background color of tile grid
        rightTilePane.setStyle("-fx-background-color: DAE6F3;");

        // Add 8 icons to TilePane; since column preference is 2, the 8 icons will take 4 rows
        addChartTypeIcons(rightTilePane);

        // Set the Right region of the BorderPane to be this TilePane
        border.setRight(rightTilePane);
        //////// TilePane in Right Region /////// END ///////////////////////////////////////////////

        //////// Display the Scene ///// BEGIN  ///////////////////////////////////////////////
        // Make a Scene from the BorderPane
        Scene scene = new Scene(border);
        stage.setScene(scene);
        stage.setTitle("Layout Example");
        stage.show();
        //////// Display the Scene ///// END  ///////////////////////////////////////////////
    }

    public static void main(String[] args) {
        launch(args);
    }
}
