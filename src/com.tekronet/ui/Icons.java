package com.tekronet.ui;

import javafx.scene.layout.Region;
import javafx.scene.shape.SVGPath;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class Icons {

    //svg paths for icons
    public static final String PLAY = "M405.284,201.188L130.804,13.28C118.128,4.596,105.356,0,94.74,0C74.216,0,61.52,16.472,61.52,44.044v406.124c0,27.54,12.68,43.98,33.156,43.98c10.632,0,23.2-4.6,35.904-13.308l274.608-187.904c17.66-12.104,27.44-28.392,27.44-45.884C432.632,229.572,422.964,213.288,405.284,201.188z";
    public static final String PAUSE = "m 74.5,0 h 73 v 365 h -73 z m 217.5,0 h 73 v 365 h -73 z";
    public static final String NEXT = "M355.938,200.956L81.414,12.128c-11.28-7.776-23.012-11.88-33.056-11.88c-22.052,0-36.672,18.496-36.672,48.26v397.036c0,14.54,4.228,26.688,10.496,35.144c6.364,8.572,16.32,13.108,27.076,13.108c10.04,0,21.308-4.112,32.584-11.876l274.276-188.828c17.632-12.152,27.3-28.508,27.296-46.076C383.414,229.456,373.594,213.1,355.938,200.956z M456.446,493.672l-0.293-0.004c-0.048,0-0.095,0.004-0.143,0.004H456.446z M455.638,0L444.29,0.032c-14.86,0-27.724,12.112-27.724,26.992v439.368c0,14.896,12.652,27.124,27.532,27.124l12.055,0.152c14.805-0.079,25.957-12.412,25.957-27.252V26.996C482.11,12.116,470.51,0,455.638,0z";
    public static final String PREVIOUS = "M447.126,0.236c-10.056,0-20.884,4.12-32.148,11.884L140.882,200.952c-17.644,12.152-27.252,28.504-27.252,46.06c-0.004,17.56,9.78,33.924,27.428,46.076L415.39,481.784c11.284,7.768,22.568,11.736,32.604,11.736h0.012c10.76,0,18.916-4.404,25.276-12.972c6.268-8.46,8.688-20.476,8.688-35.012V48.508C481.974,18.74,469.186,0.236,447.126,0.236z M53.106,0.036L39.894,0C25.018,0,11.55,12.112,11.55,26.996v439.42c0,14.884,13.024,27.1,27.908,27.1h0.456l12.948-0.072c14.88,0,28.092-12.164,28.092-27.048V27.028C80.958,12.144,67.97,0.036,53.106,0.036z";
    public static final String STOP = "M35,0H1C0.448,0,0,0.447,0,1v34c0,0.553,0.448,1,1,1h34c0.552,0,1-0.447,1-1V1C36,0.447,35.552,0,35,0z";

    //function that returns region from svgpath
    public static Region createRegion(SVGPath p, int w, int h) {
        final Region svgShape = new Region();
        svgShape.setShape(p);
		svgShape.setMinSize(w, h);
		svgShape.setMaxSize(w, h);
		svgShape.setPrefSize(w, h);
		svgShape.setStyle("-fx-background-color: black;");
        
        svgShape.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                svgShape.setStyle("-fx-background-color: #192d5c");
            }
        });
        svgShape.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                svgShape.setStyle("-fx-background-color: black");
            }
        });
        return svgShape;
    }

    public static SVGPath createPath(String path) {
        SVGPath s = new SVGPath();
        s.setContent(path);
        return s;
    }
}