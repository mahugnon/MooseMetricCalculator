/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grid;

import java.util.Optional;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Grid extends Application {

    private Stage primaryStage;
    private VBox pan;
    private AnchorPane g = new AnchorPane();
    private StackPane stsize;
    private StackPane stcolour;
    private StackPane stoption;
    private StackPane stcontinue;
    private StackPane stend;
    private StackPane stsaveandexit;
    private StackPane stdontsaveandexit;
    private StackPane[][] t;

    private TextField textp;
    private TextField texta;

    private CheckBox ch1;
    private CheckBox ch2;

    private ObservableList<Integer> listl;
    private ObservableList<Integer> listpl;
    private ObservableList<Integer> listc;
    private ObservableList<Integer> listpc;
    private ObservableList<HBox> listColor;

    private ComboBox<Integer> combol;
    private ComboBox<Integer> comboc;
    private ComboBox<Integer> combop = new ComboBox<Integer>(FXCollections.observableArrayList(4, 5));

    private Label lp;
    private Label la;
    private Label pname;
    private Label aname;

    private int l = 20;
    private int c = 20;
    private int nb = 0;
    private int pc = 0;
    private int ac = 0;
    private int np = 4;
    private int i0;
    private int i1;
    private int i2;
    private int i3;
    private int i4;

    private String colorgri = "555555";
    private String colorfond = "ffffff";

    private Color n = Color.CYAN;
    private Color r = Color.DEEPPINK;
    private Color b = Color.rgb(1, 0, 1);
    private Color p = Color.BLACK;

    private boolean ptv = true;
    private boolean gav = true;
    private boolean start = false;

    private Rectangle ptr = new Rectangle(10, 10, p);
    private ToggleColor tc = new ToggleColor();
    private Colorlist cl = new Colorlist();
    private Analyse tri;
    private Win win;

    private boolean isHow(int i, int j, Color c) {
        return t[i][j].getUserData() == c;
    }

    private void accueil() {
        nb = 0;
        start = false;
        lp.setText(String.valueOf(0));
        la.setText(String.valueOf(0));
        g.getChildren().clear();
        g.setStyle("-fx-background-color:#" + colorfond);
        t = new StackPane[l][c];
        double larg = 5;
        double haut = 0;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < c; j++) {
                t[i][j] = new StackPane();
                t[i][j].setPrefSize(28, 28);
                AnchorPane.setTopAnchor(t[i][j], haut);
                AnchorPane.setLeftAnchor(t[i][j], larg);
                t[i][j].setStyle("-fx-background-color:#" + colorgri);
                g.getChildren().add(t[i][j]);
                larg += 33;
            }
            haut += 33;
            larg = 5;
        }
        stend.setDisable(true);
        stsaveandexit.setDisable(true);
        stdontsaveandexit.setDisable(true);
        stoption.setDisable(false);
        stsize.setDisable(false);
        stcolour.setDisable(false);
    }

    private void play(int a, int d) {
        if (nb == 1) {
            if (a == 0) {
                t[a + 1][d].getChildren().addAll(new Circle(12, r), ptr);
                t[a + 1][d].setUserData(r);
            } else {
                t[a - 1][d].getChildren().addAll(new Circle(12, r), ptr);
                t[a - 1][d].setUserData(r);
            }
        }
        if (nb > 1) {
            tri = new Analyse();
            StackPane pane;
            for (int i = 0; i < l; i++) {
                for (int j = 0; j < c; j++) {
                    hr(i, j);
                }
            }
            for (int i = 0; i < l; i++) {
                for (int j = 0; j < c; j++) {
                    if (j - i <= c - l) {
                        if (j - i >= 0) {
                            or(0, j - i, i, j, l - 1, l - 1 + j - i);
                        } else {
                            or(i - j, 0, i, j, l - 1, l - 1 + j - i);
                        }
                    }
                    if (j - i > (c - l)) {
                        if (j - i >= 0) {
                            or(0, j - i, i, j, c - 1 - j + i, c - 1);
                        } else {
                            or(i - j, 0, i, j, c - 1 - j + i, c - 1);
                        }
                    }
                }
            }
            for (int i = 0; i < l; i++) {
                for (int j = 3; j < c; j++) {
                    if (j + i < l - 1) {
                        if (j + i <= c - 1) {
                            ar(0, j + i, i, j, j + i, 0);
                        } else {
                            ar(j + i - c + 1, c - 1, i, j, j + i, 0);
                        }
                    }
                    if (j + i >= l - 1) {
                        if (j + i <= c - 1) {
                            ar(0, j + i, i, j, l - 1, j + i - l + 1);
                        } else {
                            ar(j + i - c + 1, c + 1, i, j, l - 1, j + i - l + 1);
                        }
                    }
                }
            }
            if (tri.isempty()) {
                pane = tri.get();
                pane.getChildren().add(new Circle(12, r));
                pane.setUserData(r);
            } else {
                for (int i = 0; i < l; i++) {
                    for (int j = 0; j < c; j++) {
                        hp(i, j);
                    }
                }
                for (int i = 0; i < l; i++) {
                    for (int j = 0; j < c; j++) {
                        if (j - i <= c - l) {
                            if (j - i >= 0) {
                                op(0, j - i, i, j, l - 1, l - 1 + j - i);
                            } else {
                                op(i - j, 0, i, j, l - 1, l - 1 + j - i);
                            }
                        }
                        if (j - i > (c - l)) {
                            if (j - i >= 0) {
                                op(0, j - i, i, j, c - 1 - j + i, c - 1);
                            } else {
                                op(i - j, 0, i, j, c - 1 - j + i, c - 1);
                            }
                        }
                    }
                }
                for (int i = 0; i < l; i++) {
                    for (int j = 3; j < c; j++) {
                        if (j + i < l - 1) {
                            if (j + i <= c - 1) {
                                ap(0, j + i, i, j, j + i, 0);
                            } else {
                                ap(j + i - c + 1, c - 1, i, j, j + i, 0);
                            }
                        }
                        if (j + i >= l - 1) {
                            if (j + i <= c - 1) {
                                ap(0, j + i, i, j, l - 1, j + i - l + 1);
                            } else {
                                ap(j + i - c + 1, c + 1, i, j, l - 1, j + i - l + 1);
                            }
                        }
                    }
                }
                if (tri.isempty()) {
                    pane = tri.get();
                    pane.getChildren().add(new Circle(12, r));
                    pane.setUserData(r);
                } else {
                    int i = 0;
                    int bi = 0;
                    int bj = 0;
                    boolean bool = false;
                    while (i < l && !bool) {
                        int j = 0;
                        while (j < c && !bool) {
                            if (i < (l - 1) && j < (c - 1) && i > 0 && j > 0 && isHow(i, j, b) && (isHow(i - 1, j, r) || isHow(i + 1, j, r) || isHow(i, j - 1, r) || isHow(i, j + 1, r) || isHow(i - 1, j - 1, r) || isHow(i + 1, j + 1, r) || isHow(i - 1, j + 1, r) || isHow(i + 1, j - 1, r))) {
                                bool = true;
                                bi = i;
                                bj = j;
                            } else {
                                if (i == 0 && j == (c - 1) && isHow(i, j, b) && (isHow(i, j - 1, r) || isHow(i + 1, j - 1, r) || isHow(i + 1, j, r))) {
                                    bool = true;
                                    bi = i;
                                    bj = j;
                                } else {
                                    if (i == (l - 1) && j == 0 && isHow(i, j, b) && (isHow(i - 1, j, r) || isHow(i - 1, j + 1, r) || isHow(i, j + 1, r))) {
                                        bool = true;
                                        bi = i;
                                        bj = j;
                                    } else {
                                        if (i == (l - 1) && j == (c - 1) && isHow(i, j, b) && (isHow(i - 1, j, r) || isHow(i - 1, j - 1, r) || isHow(i, j - 1, r))) {
                                            bool = true;
                                            bi = i;
                                            bj = j;
                                        } else {
                                            if (i == 0 && j == 0 && isHow(i, j, b) && (isHow(i, j + 1, r) || isHow(i + 1, j + 1, r) || isHow(i + 1, j, r))) {
                                                bool = true;
                                                bi = i;
                                                bj = j;
                                            } else {
                                                bool = false;
                                            }
                                        }
                                    }
                                }
                            }
                            j++;
                        }
                        i++;
                    }
                    if (bool) {
                        pane = t[bi][bj];
                        pane.getChildren().add(new Circle(12, r));
                        pane.setUserData(r);
                    } else {
                        i = 0;
                        bi = 0;
                        bj = 0;
                        bool = false;
                        while (i < l && !bool) {
                            int j = 0;
                            while (j < c && !bool) {
                                if (isHow(i, j, b)) {
                                    bool = true;
                                    bi = i;
                                    bj = j;
                                }
                                j++;
                            }
                            i++;
                        }
                        pane = t[bi][bj];
                        pane.getChildren().add(new Circle(12, r));
                        pane.setUserData(r);
                    }
                }
            }
            win();
            pane.getChildren().add(ptr);
        }
        nb++;
        pc = 0;
        ac = 0;
        for (int i = 0; i < win.size(); i++) {
            if (win.getwin().get(i).get(0).getUserData() == n) {
                pc++;
            } else {
                ac++;
            }
        }
        lp.setText(String.valueOf(pc));
        la.setText(String.valueOf(ac));
    }

    private void win() {
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < c; j++) {
                HWIN(i, j);
            }
        }
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < c; j++) {
                if (j - i <= c - l) {
                    OWIN(i, j, l - 1);
                }
                if (j - i > c - l) {
                    OWIN(i, j, c - 1 - j + i);
                }
            }
        }
        for (int i = 0; i < l; i++) {
            for (int j = 3; j < c; j++) {
                if (j + i < l - 1) {
                    AWIN(i, j, j + i);
                }
                if (j + i >= l - 1) {
                    AWIN(i, j, l - 1);
                }
            }
        }
    }

    private void AWIN(int i, int j, int w) {
        if (np == 4) {
            if ((w - i) >= 3) {
                isWina(i, j, n);
                isWina(i, j, r);
            }
        } else {
            if ((w - i) >= 4) {
                isWina5(i, j, n);
                isWina5(i, j, r);
            }
        }
    }

    private void OWIN(int i, int j, int w) {
        if (np == 4) {
            if ((w - i) >= 3) {
                isWino(i, j, n);
                isWino(i, j, r);
            }
        } else {
            if ((w - i) >= 4) {
                isWino5(i, j, n);
                isWino5(i, j, r);
            }
        }
    }

    private void HWIN(int i, int j) {
        if (np == 4) {
            if (j >= 0 && j <= c - 4) {
                isWinl(i, j, n);
                isWinl(i, j, r);
            }
            if (i >= 0 && i <= l - 4) {
                isWinc(i, j, n);
                isWinc(i, j, r);
            }
        } else {
            if (j >= 0 && j <= c - 5) {
                isWinl5(i, j, n);
                isWinl5(i, j, r);
            }
            if (i >= 0 && i <= l - 5) {
                isWinc5(i, j, n);
                isWinc5(i, j, r);
            }
        }
    }

    private void ap(int a, int c, int i, int j, int w, int h) {
        if (np == 4) {
            if ((w - i) >= 3) {
                istruea(i, j, b, b, n, n);
                istruea(i, j, n, n, b, b);
            }
        }
        if (np == 5) {
            if ((w - i) >= 4) {

            }
        }
    }

    private void op(int a, int c, int i, int j, int w, int h) {
        if (np == 4) {
            if ((w - i) >= 3) {
                istrueo(i, j, b, b, n, n);
                istrueo(i, j, n, n, b, b);
            }
        }
        if (np == 5) {
            if ((w - i) >= 4) {

            }
        }
    }

    private void hp(int i, int j) {
        if (np == 4) {
            if (j >= 0 && j <= c - 4) {
                istruel(i, j, b, b, n, n);
                istruel(i, j, n, n, b, b);
            }
            if (i >= 0 && i <= l - 4) {
                istruec(i, j, b, b, n, n);
                istruec(i, j, n, n, b, b);
            }
        }
        if (np == 5) {
        }
    }

    private void ar(int a, int c, int i, int j, int w, int h) {
        if (np == 4) {
            if ((w - i) >= 3) {
                if ((i - 1 >= a) && (j + 1 <= c) && isHow(i - 1, j + 1, r)) {
                    istruea(i, j, n, n, n, b);
                }
                if ((i - 1 >= a) && (j + 1 <= c) && isHow(i - 1, j + 1, b)) {
                    istruea(i, j, b, n, n, b);
                }
                if ((i + 4 <= w) && (j - 4 >= h) && isHow(i + 4, j - 4, r)) {
                    istruea(i, j, b, n, n, n);
                }
                if ((i + 4 <= w) && (j - 4 >= h) && isHow(i + 4, j - 4, b)) {
                    istruea(i, j, b, n, n, b);
                }
                if ((i - 1 >= a) && (j + 1 <= c) && !isHow(i - 1, j + 1, r)) {
                    istruea(i, j, n, b, n, b);
                }
                if ((i + 4 <= w) && (j - 4 >= h) && !isHow(i + 4, j - 4, r)) {
                    istruea(i, j, b, n, b, n);
                }
                if ((i == a) && (j == c)) {
                    istruea(i, j, n, n, n, b);
                }
                if ((i + 3 == w) && (j - 3 == h)) {
                    istruea(i, j, b, n, n, n);
                }
                istruea(i, j, n, b, n, n);
                istruea(i, j, n, n, b, n);

                istruea(i, j, r, r, r, b);
                istruea(i, j, b, r, r, r);
                istruea(i, j, r, r, b, r);
                istruea(i, j, r, b, r, r);

                istruea(i, j, b, b, r, r);
                istruea(i, j, r, r, b, b);
                istruea(i, j, b, r, r, b);
                istruea(i, j, b, r, b, r);
                istruea(i, j, r, b, b, r);
                istruea(i, j, r, b, r, b);
            }
        }
        if (np == 5) {
            if ((w - i) >= 4) {
                if ((i - 1 >= a) && (j + 1 <= c) && isHow(i - 1, j + 1, r)) {
                    istruea5(i, j, n, n, n, n, b);
                }
                if ((i + 5 <= w) && (j - 5 >= h) && isHow(i + 5, j - 5, r)) {
                    istruea5(i, j, b, n, n, n, n);
                }
                if ((i == a) && (j == c)) {
                    istruea5(i, j, n, n, n, n, b);
                }
                if ((i + 4 == w) && (j - 4 == h)) {
                    istruea5(i, j, b, n, n, n, n);
                }
                istruea5(i, j, n, n, n, b, n);
                istruea5(i, j, n, n, b, n, n);
                istruea5(i, j, n, b, n, n, n);

                istruea5(i, j, b, n, n, n, b);
                istruea5(i, j, b, n, n, b, n);
                istruea5(i, j, b, n, b, n, n);

                istruea5(i, j, n, b, n, n, b);
                istruea5(i, j, n, n, b, n, b);
                istruea5(i, j, n, b, n, b, n);

                istruea5(i, j, n, b, b, n, n);
                istruea5(i, j, n, n, b, b, n);

                istruea5(i, j, r, r, r, r, b);
                istruea5(i, j, b, r, r, r, r);
                istruea5(i, j, r, r, r, b, r);
                istruea5(i, j, r, r, b, r, r);
                istruea5(i, j, r, b, r, r, r);

                istruea5(i, j, b, r, r, r, b);
                istruea5(i, j, b, r, r, b, r);
                istruea5(i, j, b, r, b, r, r);
                istruea5(i, j, b, b, r, r, r);

                istruea5(i, j, r, b, r, r, b);
                istruea5(i, j, r, r, b, r, b);
                istruea5(i, j, r, r, r, b, b);
                istruea5(i, j, r, b, r, b, r);

                istruea5(i, j, r, b, b, r, r);
                istruea5(i, j, r, r, b, b, r);
            }
        }
    }

    private void or(int a, int c, int i, int j, int w, int h) {
        if (np == 4) {
            if ((w - i) >= 3) {
                if ((i - 1 >= a) && (j - 1 >= c) && isHow(i - 1, j - 1, r)) {
                    istrueo(i, j, n, n, n, b);
                }
                if ((i - 1 >= a) && (j - 1 >= c) && isHow(i - 1, j - 1, b)) {
                    istrueo(i, j, b, n, n, b);
                }
                if ((i + 4 <= w) && (j + 4 <= h) && isHow(i + 4, j + 4, r)) {
                    istrueo(i, j, b, n, n, n);
                }
                if ((i + 4 <= w) && (j + 4 <= h) && isHow(i + 4, j + 4, b)) {
                    istrueo(i, j, b, n, n, b);
                }
                if ((i - 1 >= a) && (j - 1 >= c) && !isHow(i - 1, j - 1, r)) {
                    istrueo(i, j, n, b, n, b);
                }
                if ((i + 4 <= w) && (j + 4 <= h) && !isHow(i + 4, j + 4, r)) {
                    istrueo(i, j, b, n, b, n);
                }
                if ((i == a) && (j == c)) {
                    istrueo(i, j, n, n, n, b);
                }
                if ((i + 3 == w) && (j + 3 == h)) {
                    istrueo(i, j, b, n, n, n);
                }
                istrueo(i, j, n, n, b, n);
                istrueo(i, j, n, b, n, n);

                istrueo(i, j, r, r, r, b);
                istrueo(i, j, b, r, r, r);
                istrueo(i, j, r, r, b, r);
                istrueo(i, j, r, b, r, r);

                istrueo(i, j, b, b, r, r);
                istrueo(i, j, r, r, b, b);
                istrueo(i, j, b, r, r, b);
                istrueo(i, j, b, r, b, r);
                istrueo(i, j, r, b, b, r);
                istrueo(i, j, r, b, r, b);
            }
        }
        if (np == 5) {
            if ((w - i) >= 4) {
                if ((i - 1 >= a) && (j - 1 >= c) && isHow(i - 1, j - 1, r)) {
                    istrueo5(i, j, n, n, n, n, b);
                }
                if ((i + 5 <= w) && (j + 5 <= h) && isHow(i + 5, j + 5, r)) {
                    istrueo5(i, j, b, n, n, n, n);
                }
                if ((i == a) && (j == c)) {
                    istrueo5(i, j, n, n, n, n, b);
                }
                if ((i + 4 == w) && (j + 4 == h)) {
                    istrueo5(i, j, b, n, n, n, n);
                }
                istrueo5(i, j, n, n, n, b, n);
                istrueo5(i, j, n, n, b, n, n);
                istrueo5(i, j, n, b, n, n, n);

                istrueo5(i, j, b, n, n, n, b);
                istrueo5(i, j, b, n, n, b, n);
                istrueo5(i, j, b, n, b, n, n);

                istrueo5(i, j, n, b, n, n, b);
                istrueo5(i, j, n, n, b, n, b);
                istrueo5(i, j, n, b, n, b, n);

                istrueo5(i, j, n, b, b, n, n);
                istrueo5(i, j, n, n, b, b, n);

                istrueo5(i, j, r, r, r, r, b);
                istrueo5(i, j, b, r, r, r, r);
                istrueo5(i, j, r, r, r, b, r);
                istrueo5(i, j, r, r, b, r, r);
                istrueo5(i, j, r, b, r, r, r);

                istrueo5(i, j, b, r, r, r, b);
                istrueo5(i, j, b, r, r, b, r);
                istrueo5(i, j, b, r, b, r, r);
                istrueo5(i, j, b, b, r, r, r);

                istrueo5(i, j, r, b, r, r, b);
                istrueo5(i, j, r, r, b, r, b);
                istrueo5(i, j, r, r, r, b, b);
                istrueo5(i, j, r, b, r, b, r);

                istrueo5(i, j, r, b, b, r, r);
                istrueo5(i, j, r, r, b, b, r);
            }
        }
    }

    private void hr(int i, int j) {
        if (np == 4) {
            if (j >= 0 && j <= c - 4) {
                if ((j + 4 <= c - 1) && isHow(i, j + 4, r)) {
                    istruel(i, j, b, n, n, n);
                }
                if ((j + 4 <= c - 1) && isHow(i, j + 4, b)) {
                    istruel(i, j, b, n, n, b);
                }
                if ((j - 1 >= 0) && isHow(i, j - 1, r)) {
                    istruel(i, j, n, n, n, b);
                }
                if ((j - 1 >= 0) && isHow(i, j - 1, b)) {
                    istruel(i, j, b, n, n, b);
                }
                if ((j + 4 <= c - 1) && !isHow(i, j + 4, r)) {
                    istruel(i, j, b, n, b, n);
                }
                if ((j - 1 >= 0) && !isHow(i, j - 1, r)) {
                    istruel(i, j, n, b, n, b);
                }
                if ((j == 0)) {
                    istruel(i, j, n, n, n, b);
                }
                if ((j + 3 == c - 1)) {
                    istruel(i, j, b, n, n, n);
                }
                istruel(i, j, n, b, n, n);
                istruel(i, j, n, n, b, n);

                istruel(i, j, b, r, r, r);
                istruel(i, j, r, r, r, b);
                istruel(i, j, r, r, b, r);
                istruel(i, j, r, b, r, r);

                istruel(i, j, b, b, r, r);
                istruel(i, j, r, r, b, b);
                istruel(i, j, b, r, r, b);
                istruel(i, j, b, r, b, r);
                istruel(i, j, r, b, b, r);
                istruel(i, j, r, b, r, b);
            }
            if (i >= 0 && i <= l - 4) {

                if ((i + 4 <= l - 1) && isHow(i + 4, j, r)) {
                    istruec(i, j, b, n, n, n);
                }
                if ((i + 4 <= l - 1) && isHow(i + 4, j, b)) {
                    istruec(i, j, b, n, n, b);
                }
                if ((i - 1 >= 0) && isHow(i - 1, j, r)) {
                    istruec(i, j, n, n, n, b);
                }
                if ((i - 1 >= 0) && isHow(i - 1, j, b)) {
                    istruec(i, j, b, n, n, b);
                }
                if ((i + 4 <= l - 1) && !isHow(i + 4, j, r)) {
                    istruec(i, j, b, n, b, n);
                }
                if ((i - 1 >= 0) && !isHow(i - 1, j, r)) {
                    istruec(i, j, n, b, n, b);
                }
                if (i == 0) {
                    istruec(i, j, n, n, n, b);
                }
                if (i + 3 == l - 1) {
                    istruec(i, j, b, n, n, n);
                }
                istruec(i, j, n, n, b, n);
                istruec(i, j, n, b, n, n);

                istruec(i, j, b, r, r, r);
                istruec(i, j, r, r, r, b);
                istruec(i, j, r, r, b, r);
                istruec(i, j, r, b, r, r);

                istruec(i, j, b, b, r, r);
                istruec(i, j, r, r, b, b);
                istruec(i, j, b, r, r, b);
                istruec(i, j, b, r, b, r);
                istruec(i, j, r, b, b, r);
                istruec(i, j, r, b, r, b);
            }
        }
        if (np == 5) {
            if (j >= 0 && j <= c - 5) {
                if ((j + 5 <= c - 1) && isHow(i, j + 5, r)) {
                    istruel5(i, j, b, n, n, n, n);
                }
                if ((j - 1 >= 0) && isHow(i, j - 1, r)) {
                    istruel5(i, j, n, n, n, n, b);
                }
                if ((j + 5 <= c - 1) && !isHow(i, j + 5, r)) {
                    istruel5(i, j, b, r, r, r, r);
                }
                if ((j - 1 >= 0) && !isHow(i, j - 1, r)) {
                    istruel5(i, j, r, r, r, r, b);
                }
                if ((j == 0)) {
                    istruel5(i, j, n, n, n, n, b);
                }
                if ((j + 4 == c - 1)) {
                    istruel5(i, j, b, n, n, n, n);
                }
                istruel5(i, j, n, n, n, b, n);
                istruel5(i, j, n, n, b, n, n);
                istruel5(i, j, n, b, n, n, n);

                istruel5(i, j, b, n, n, n, b);
                istruel5(i, j, b, n, n, b, n);
                istruel5(i, j, b, n, b, n, n);

                istruel5(i, j, n, b, n, n, b);
                istruel5(i, j, n, n, b, n, b);
                istruel5(i, j, n, b, n, b, n);

                istruel5(i, j, n, b, b, n, n);
                istruel5(i, j, n, n, b, b, n);

                istruel5(i, j, b, r, r, r, r);
                istruel5(i, j, r, r, r, r, b);
                istruel5(i, j, r, r, r, b, r);
                istruel5(i, j, r, r, b, r, r);
                istruel5(i, j, r, b, r, r, r);

                istruel5(i, j, b, r, r, r, b);
                istruel5(i, j, b, r, r, b, r);
                istruel5(i, j, b, r, b, r, r);
                istruel5(i, j, b, b, r, r, r);

                istruel5(i, j, r, b, r, r, b);
                istruel5(i, j, r, r, b, r, b);
                istruel5(i, j, r, r, r, b, b);
                istruel5(i, j, r, b, r, b, r);

                istruel5(i, j, r, b, b, r, r);
                istruel5(i, j, r, r, b, b, r);
            }
            if (i >= 0 && i <= l - 5) {

                if ((i + 5 <= l - 1) && isHow(i + 5, j, r)) {
                    istruec5(i, j, b, n, n, n, n);
                }
                if ((i - 1 >= 0) && isHow(i - 1, j, r)) {
                    istruec5(i, j, n, n, n, n, b);
                }
                if (i == 0) {
                    istruec5(i, j, n, n, n, n, b);
                }
                if (i + 4 == l - 1) {
                    istruec5(i, j, b, n, n, n, n);
                }
                istruec5(i, j, n, n, n, b, n);
                istruec5(i, j, n, n, b, n, n);
                istruec5(i, j, n, b, n, n, n);

                istruec5(i, j, b, n, n, n, b);
                istruec5(i, j, b, n, n, b, n);
                istruec5(i, j, b, n, b, n, n);

                istruec5(i, j, n, b, n, n, b);
                istruec5(i, j, n, n, b, n, b);
                istruec5(i, j, n, b, n, b, n);
                istruec5(i, j, n, b, b, n, n);
                istruec5(i, j, n, n, b, b, n);

                istruec5(i, j, b, r, r, r, r);
                istruec5(i, j, r, r, r, r, b);
                istruec5(i, j, r, r, r, b, r);
                istruec5(i, j, r, r, b, r, r);
                istruec5(i, j, r, b, r, r, r);

                istruec5(i, j, b, r, r, r, b);
                istruec5(i, j, b, r, r, b, r);
                istruec5(i, j, b, r, b, r, r);
                istruec5(i, j, b, b, r, r, r);

                istruec5(i, j, r, b, r, r, b);
                istruec5(i, j, r, r, b, r, b);
                istruec5(i, j, r, r, r, b, b);
                istruec5(i, j, r, b, r, b, r);

                istruec5(i, j, r, b, b, r, r);
                istruec5(i, j, r, r, b, b, r);
            }
        }
    }

    private void isWina(int i, int j, Color a) {
        if (isHow(i, j, a) && isHow(i + 1, j - 1, a) && isHow(i + 2, j - 2, a) && isHow(i + 3, j - 3, a)) {
            if (ptv) {
                if (win.addPli(new Pli(t[i][j], t[i + 1][j - 1], t[i + 2][j - 2], t[i + 3][j - 3]))) {
                    Path path = new Path();
                    path.getElements().addAll(new MoveTo(AnchorPane.getLeftAnchor(t[i + np - 1][j - np + 1]) + 19, AnchorPane.getTopAnchor(t[i + np - 1][j - np + 1]) + 15), new LineTo(AnchorPane.getLeftAnchor(t[i + np - 1][j - np + 1]) + 28 * np + 5, AnchorPane.getTopAnchor(t[i + np - 1][j - np + 1]) - 28 * np + 28));
                    path.setStroke(a);
                    path.setStrokeWidth(8);
                    g.getChildren().add(path);
                }
            } else {
                if (!win.atravers(new Pli(t[i][j], t[i + 1][j - 1], t[i + 2][j - 2], t[i + 3][j - 3]))) {
                    if (win.addPli(new Pli(t[i][j], t[i + 1][j - 1], t[i + 2][j - 2], t[i + 3][j - 3]))) {
                        Path path = new Path();
                        path.getElements().addAll(new MoveTo(AnchorPane.getLeftAnchor(t[i + np - 1][j - np + 1]) + 19, AnchorPane.getTopAnchor(t[i + np - 1][j - np + 1]) + 14), new LineTo(AnchorPane.getLeftAnchor(t[i + np - 1][j - np + 1]) + 28 * np + 5, AnchorPane.getTopAnchor(t[i + np - 1][j - np + 1]) - 28 * np + 28));
                        path.setStroke(a);
                        path.setStrokeWidth(8);
                        g.getChildren().add(path);
                    }
                }
            }
        }
    }

    private void isWina5(int i, int j, Color a) {
        if (isHow(i, j, a) && isHow(i + 1, j - 1, a) && isHow(i + 2, j - 2, a) && isHow(i + 3, j - 3, a) && isHow(i + 4, j - 4, a)) {
            if (ptv) {
                if (win.addPli(new Pli(t[i][j], t[i + 1][j - 1], t[i + 2][j - 2], t[i + 3][j - 3], t[i + 4][j - 4]))) {
                    Path path = new Path();
                    path.getElements().addAll(new MoveTo(AnchorPane.getLeftAnchor(t[i + np - 1][j - np + 1]) + 19, AnchorPane.getTopAnchor(t[i + np - 1][j - np + 1]) + 14), new LineTo(AnchorPane.getLeftAnchor(t[i + np - 1][j - np + 1]) + 28 * np + 5, AnchorPane.getTopAnchor(t[i + np - 1][j - np + 1]) - 28 * np + 28));
                    path.setStroke(a);
                    path.setStrokeWidth(8);
                    g.getChildren().add(path);
                }
            } else {
                if (!win.atravers(new Pli(t[i][j], t[i + 1][j - 1], t[i + 2][j - 2], t[i + 3][j - 3], t[i + 4][j - 4]))) {
                    if (win.addPli(new Pli(t[i][j], t[i + 1][j - 1], t[i + 2][j - 2], t[i + 3][j - 3], t[i + 4][j - 4]))) {
                        Path path = new Path();
                        path.getElements().addAll(new MoveTo(AnchorPane.getLeftAnchor(t[i + np - 1][j - np + 1]) + 19, AnchorPane.getTopAnchor(t[i + np - 1][j - np + 1]) + 14), new LineTo(AnchorPane.getLeftAnchor(t[i + np - 1][j - np + 1]) + 28 * np + 5, AnchorPane.getTopAnchor(t[i + np - 1][j - np + 1]) - 28 * np + 28));
                        path.setStroke(a);
                        path.setStrokeWidth(8);
                        g.getChildren().add(path);
                    }
                }
            }
        }
    }

    private void isWino5(int i, int j, Color a) {
        if (isHow(i, j, a) && isHow(i + 1, j + 1, a) && isHow(i + 2, j + 2, a) && isHow(i + 3, j + 3, a) && isHow(i + 4, j + 4, a)) {
            if (ptv) {
                if (win.addPli(new Pli(t[i][j], t[i + 1][j + 1], t[i + 2][j + 2], t[i + 3][j + 3], t[i + 4][j + 4]))) {
                    Line line = new Line();
                    AnchorPane.setTopAnchor(line, AnchorPane.getTopAnchor(t[i][j]) + 5);
                    AnchorPane.setLeftAnchor(line, AnchorPane.getLeftAnchor(t[i][j]) + 5);
                    line.setEndX(28 * np - 10);
                    line.setEndY(28 * np - 10);
                    line.setStrokeWidth(8);
                    line.setStroke(a);
                    g.getChildren().add(line);
                }
            } else {
                if (!win.atravers(new Pli(t[i][j], t[i + 1][j + 1], t[i + 2][j + 2], t[i + 3][j + 3], t[i + 4][j + 4]))) {
                    if (win.addPli(new Pli(t[i][j], t[i + 1][j + 1], t[i + 2][j + 2], t[i + 3][j + 3], t[i + 4][j + 4]))) {
                        Line line = new Line();
                        AnchorPane.setTopAnchor(line, AnchorPane.getTopAnchor(t[i][j]) + 5);
                        AnchorPane.setLeftAnchor(line, AnchorPane.getLeftAnchor(t[i][j]) + 5);
                        line.setEndX(28 * np - 10);
                        line.setEndY(28 * np - 10);
                        line.setStrokeWidth(8);
                        line.setStroke(a);
                        g.getChildren().add(line);
                    }
                }
            }
        }
    }

    private void isWinl5(int i, int j, Color a) {
        if (isHow(i, j, a) && isHow(i, j + 1, a) && isHow(i, j + 2, a) && isHow(i, j + 3, a) && isHow(i, j + 4, a)) {
            if (win.addPli(new Pli(t[i][j], t[i][j + 1], t[i][j + 2], t[i][j + 3], t[i][j + 4]))) {
                Line line = new Line();
                AnchorPane.setTopAnchor(line, AnchorPane.getTopAnchor(t[i][j]) + 10);
                AnchorPane.setLeftAnchor(line, AnchorPane.getLeftAnchor(t[i][j]) + 5);
                line.setEndX(28 * np - 5);
                line.setStrokeWidth(8);
                line.setStroke(a);
                g.getChildren().add(line);
            }
        }
    }

    private void isWinc5(int i, int j, Color a) {
        if (isHow(i, j, a) && isHow(i + 1, j, a) && isHow(i + 2, j, a) && isHow(i + 3, j, a) && isHow(i + 4, j, a)) {
            if (win.addPli(new Pli(t[i][j], t[i + 1][j], t[i + 2][j], t[i + 3][j], t[i + 4][j]))) {
                Line line = new Line();
                AnchorPane.setTopAnchor(line, AnchorPane.getTopAnchor(t[i][j]) + 5);
                AnchorPane.setLeftAnchor(line, AnchorPane.getLeftAnchor(t[i][j]) + 10);
                line.setEndY(28 * np - 5);
                line.setStrokeWidth(8);
                line.setStroke(a);
                g.getChildren().add(line);
            }
        }
    }

    private void istruea(int i, int j, Color a, Color b, Color c, Color d) {
        if (isHow(i, j, a) && isHow(i + 1, j - 1, b) && isHow(i + 2, j - 2, c) && isHow(i + 3, j - 3, d)) {
            if (ptv) {
                if (win.getVerifPion(new Pli(t[i][j], t[i + 1][j - 1], t[i + 2][j - 2], t[i + 3][j - 3]))) {
                    tri.addPli(new Pli(t[i][j], t[i + 1][j - 1], t[i + 2][j - 2], t[i + 3][j - 3]));
                }
            } else {
                if (!win.atravers(new Pli(t[i][j], t[i + 1][j - 1], t[i + 2][j - 2], t[i + 3][j - 3]))) {
                    if (win.getVerifPion(new Pli(t[i][j], t[i + 1][j - 1], t[i + 2][j - 2], t[i + 3][j - 3]))) {
                        tri.addPli(new Pli(t[i][j], t[i + 1][j - 1], t[i + 2][j - 2], t[i + 3][j - 3]));
                    }
                }
            }
        }
    }

    private void istruea5(int i, int j, Color a, Color b, Color c, Color d, Color e) {
        if (isHow(i, j, a) && isHow(i + 1, j - 1, b) && isHow(i + 2, j - 2, c) && isHow(i + 3, j - 3, d) && isHow(i + 4, j - 4, e)) {
            if (ptv) {
                if (win.getVerifPion(new Pli(t[i][j], t[i + 1][j - 1], t[i + 2][j - 2], t[i + 3][j - 3], t[i + 4][j - 4]))) {
                    tri.addPli(new Pli(t[i][j], t[i + 1][j - 1], t[i + 2][j - 2], t[i + 3][j - 3], t[i + 4][j - 4]));
                }
            } else {
                if (!win.atravers(new Pli(t[i][j], t[i + 1][j - 1], t[i + 2][j - 2], t[i + 3][j - 3], t[i + 4][j - 4]))) {
                    if (win.getVerifPion(new Pli(t[i][j], t[i + 1][j - 1], t[i + 2][j - 2], t[i + 3][j - 3], t[i + 4][j - 4]))) {
                        tri.addPli(new Pli(t[i][j], t[i + 1][j - 1], t[i + 2][j - 2], t[i + 3][j - 3], t[i + 4][j - 4]));
                    }
                }
            }
        }
    }

    private void isWino(int i, int j, Color a) {
        if (isHow(i, j, a) && isHow(i + 1, j + 1, a) && isHow(i + 2, j + 2, a) && isHow(i + 3, j + 3, a)) {
            if (ptv) {
                if (win.addPli(new Pli(t[i][j], t[i + 1][j + 1], t[i + 2][j + 2], t[i + 3][j + 3]))) {
                    Line line = new Line();
                    AnchorPane.setTopAnchor(line, AnchorPane.getTopAnchor(t[i][j]) + 5);
                    AnchorPane.setLeftAnchor(line, AnchorPane.getLeftAnchor(t[i][j]) + 5);
                    line.setEndX(28 * np - 10);
                    line.setEndY(28 * np - 10);
                    line.setStrokeWidth(8);
                    line.setStroke(a);
                    g.getChildren().add(line);
                }
            } else {
                if (!win.atravers(new Pli(t[i][j], t[i + 1][j + 1], t[i + 2][j + 2], t[i + 3][j + 3]))) {
                    if (win.addPli(new Pli(t[i][j], t[i + 1][j + 1], t[i + 2][j + 2], t[i + 3][j + 3]))) {
                        Line line = new Line();
                        AnchorPane.setTopAnchor(line, AnchorPane.getTopAnchor(t[i][j]) + 5);
                        AnchorPane.setLeftAnchor(line, AnchorPane.getLeftAnchor(t[i][j]) + 5);
                        line.setEndX(28 * np - 10);
                        line.setEndY(28 * np - 10);
                        line.setStrokeWidth(8);
                        line.setStroke(a);
                        g.getChildren().add(line);
                    }
                }
            }
        }
    }

    private void istrueo(int i, int j, Color a, Color b, Color c, Color d) {
        if (isHow(i, j, a) && isHow(i + 1, j + 1, b) && isHow(i + 2, j + 2, c) && isHow(i + 3, j + 3, d)) {
            if (ptv) {
                if (win.getVerifPion(new Pli(t[i][j], t[i + 1][j + 1], t[i + 2][j + 2], t[i + 3][j + 3]))) {
                    tri.addPli(new Pli(t[i][j], t[i + 1][j + 1], t[i + 2][j + 2], t[i + 3][j + 3]));
                }
            } else {
                if (!win.atravers(new Pli(t[i][j], t[i + 1][j + 1], t[i + 2][j + 2], t[i + 3][j + 3]))) {
                    if (win.getVerifPion(new Pli(t[i][j], t[i + 1][j + 1], t[i + 2][j + 2], t[i + 3][j + 3]))) {
                        tri.addPli(new Pli(t[i][j], t[i + 1][j + 1], t[i + 2][j + 2], t[i + 3][j + 3]));
                    }
                }
            }
        }
    }

    private void istrueo5(int i, int j, Color a, Color b, Color c, Color d, Color e) {
        if (isHow(i, j, a) && isHow(i + 1, j + 1, b) && isHow(i + 2, j + 2, c) && isHow(i + 3, j + 3, d) && isHow(i + 4, j + 4, e)) {
            if (ptv) {
                if (win.getVerifPion(new Pli(t[i][j], t[i + 1][j + 1], t[i + 2][j + 2], t[i + 3][j + 3], t[i + 4][j + 4]))) {
                    tri.addPli(new Pli(t[i][j], t[i + 1][j + 1], t[i + 2][j + 2], t[i + 3][j + 3], t[i + 4][j + 4]));
                }
            } else {
                if (!win.atravers(new Pli(t[i][j], t[i + 1][j + 1], t[i + 2][j + 2], t[i + 3][j + 3], t[i + 4][j + 4]))) {
                    if (win.getVerifPion(new Pli(t[i][j], t[i + 1][j + 1], t[i + 2][j + 2], t[i + 3][j + 3], t[i + 4][j + 4]))) {
                        tri.addPli(new Pli(t[i][j], t[i + 1][j + 1], t[i + 2][j + 2], t[i + 3][j + 3], t[i + 4][j + 4]));
                    }
                }
            }
        }
    }

    private void isWinl(int i, int j, Color a) {
        if (isHow(i, j, a) && isHow(i, j + 1, a) && isHow(i, j + 2, a) && isHow(i, j + 3, a)) {
            if (win.addPli(new Pli(t[i][j], t[i][j + 1], t[i][j + 2], t[i][j + 3]))) {
                Line line = new Line();
                AnchorPane.setTopAnchor(line, AnchorPane.getTopAnchor(t[i][j]) + 10);
                AnchorPane.setLeftAnchor(line, AnchorPane.getLeftAnchor(t[i][j]) + 5);
                line.setEndX(28 * np - 5);
                line.setStrokeWidth(8);
                line.setStroke(a);
                g.getChildren().add(line);
            }
        }
    }

    private void istruel(int i, int j, Color a, Color b, Color c, Color d) {
        if (isHow(i, j, a) && isHow(i, j + 1, b) && isHow(i, j + 2, c) && isHow(i, j + 3, d)) {
            if (win.getVerifPion(new Pli(t[i][j], t[i][j + 1], t[i][j + 2], t[i][j + 3]))) {
                tri.addPli(new Pli(t[i][j], t[i][j + 1], t[i][j + 2], t[i][j + 3]));
            }
        }
    }

    private void istruel5(int i, int j, Color a, Color b, Color c, Color d, Color e) {
        if (isHow(i, j, a) && isHow(i, j + 1, b) && isHow(i, j + 2, c) && isHow(i, j + 3, d) && isHow(i, j + 4, e)) {
            if (win.getVerifPion(new Pli(t[i][j], t[i][j + 1], t[i][j + 2], t[i][j + 3], t[i][j + 4]))) {
                tri.addPli(new Pli(t[i][j], t[i][j + 1], t[i][j + 2], t[i][j + 3], t[i][j + 4]));
            }
        }
    }

    private void isWinc(int i, int j, Color a) {
        if (isHow(i, j, a) && isHow(i + 1, j, a) && isHow(i + 2, j, a) && isHow(i + 3, j, a)) {
            if (win.addPli(new Pli(t[i][j], t[i + 1][j], t[i + 2][j], t[i + 3][j]))) {
                Line line = new Line();
                AnchorPane.setTopAnchor(line, AnchorPane.getTopAnchor(t[i][j]) + 5);
                AnchorPane.setLeftAnchor(line, AnchorPane.getLeftAnchor(t[i][j]) + 10);
                line.setEndY(28 * np - 5);
                line.setStrokeWidth(8);
                line.setStroke(a);
                g.getChildren().add(line);
            }
        }
    }

    private void istruec(int i, int j, Color a, Color b, Color c, Color d) {
        if (isHow(i, j, a) && isHow(i + 1, j, b) && isHow(i + 2, j, c) && isHow(i + 3, j, d)) {
            if (win.getVerifPion(new Pli(t[i][j], t[i + 1][j], t[i + 2][j], t[i + 3][j]))) {
                tri.addPli(new Pli(t[i][j], t[i + 1][j], t[i + 2][j], t[i + 3][j]));
            }
        }
    }

    private void istruec5(int i, int j, Color a, Color b, Color c, Color d, Color e) {
        if (isHow(i, j, a) && isHow(i + 1, j, b) && isHow(i + 2, j, c) && isHow(i + 3, j, d) && isHow(i + 4, j, e)) {
            if (win.getVerifPion(new Pli(t[i][j], t[i + 1][j], t[i + 2][j], t[i + 3][j], t[i + 4][j]))) {
                tri.addPli(new Pli(t[i][j], t[i + 1][j], t[i + 2][j], t[i + 3][j], t[i + 4][j]));
            }
        }
    }

    private Accordion menu() {

        StackPane stnew = new StackPane();
        stnew.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                g.getChildren().clear();
                demarrer();
            }
        });
        stnew.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                ((StackPane) e.getSource()).setStyle("-fx-background-color:lightblue");
            }
        });
        stnew.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                ((StackPane) e.getSource()).setStyle("");
            }
        });
        Label labelnew = new Label("New");
        labelnew.setFont(new Font(14));
        stnew.getChildren().add(labelnew);

        stcontinue = new StackPane();
        stcontinue.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                ((StackPane) e.getSource()).setStyle("-fx-background-color:lightblue");
            }
        });
        stcontinue.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                ((StackPane) e.getSource()).setStyle("");
            }
        });
        Label labelcontinue = new Label("Continue");
        labelcontinue.setFont(new Font(14));
        stcontinue.getChildren().add(labelcontinue);

        stend = new StackPane();
        stend.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                g.getChildren().clear();
                accueil();
            }
        });
        stend.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                ((StackPane) e.getSource()).setStyle("-fx-background-color:lightblue");
            }
        });
        stend.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                ((StackPane) e.getSource()).setStyle("");
            }
        });
        Label labelend = new Label("End");
        labelend.setFont(new Font(14));
        stend.getChildren().add(labelend);

        VBox vbg = new VBox();
        vbg.getChildren().addAll(stnew, stcontinue, stend);

        TitledPane game = new TitledPane("Game", vbg);
        game.setFont(new Font(14));

        stcolour = new StackPane();
        stcolour.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                Alert alert_Color = new Alert(AlertType.CONFIRMATION);
                alert_Color.setTitle("Change game colour");
                alert_Color.setHeaderText("Change game colour or use default name");

                alert_Color.getButtonTypes().setAll(new ButtonType("Default", ButtonData.OK_DONE), new ButtonType("Save", ButtonData.OK_DONE), new ButtonType("Cancel", ButtonData.CANCEL_CLOSE));

                listColor = FXCollections.observableArrayList();

                for (int i = 0; i < cl.size(); i++) {
                    listColor.add(tc.getToggles(i));
                }
                ListView<HBox> listview = new ListView<HBox>();
                listview.setItems(listColor);

                for (int i = 0; i < cl.size(); i++) {
                    if (cl.get(i).getColor() == r) {
                        ((RadioButton) listColor.get(i).getChildren().get(4)).setSelected(true);
                        ((RadioButton) listColor.get(i).getChildren().get(3)).setDisable(true);
                        ((RadioButton) listColor.get(i).getChildren().get(2)).setDisable(true);
                        ((RadioButton) listColor.get(i).getChildren().get(1)).setDisable(true);
                        i4 = i;
                    }
                    if (cl.get(i).getColor() == n) {
                        ((RadioButton) listColor.get(i).getChildren().get(3)).setSelected(true);
                        ((RadioButton) listColor.get(i).getChildren().get(4)).setDisable(true);
                        ((RadioButton) listColor.get(i).getChildren().get(2)).setDisable(true);
                        ((RadioButton) listColor.get(i).getChildren().get(1)).setDisable(true);
                        i3 = i;
                    }
                    if (cl.get(i).getColor() == p) {
                        ((RadioButton) listColor.get(i).getChildren().get(2)).setSelected(true);
                        ((RadioButton) listColor.get(i).getChildren().get(3)).setDisable(true);
                        ((RadioButton) listColor.get(i).getChildren().get(4)).setDisable(true);
                        i2 = i;
                    }
                    if (cl.get(i).getString().compareTo(colorgri) == 0) {
                        ((RadioButton) listColor.get(i).getChildren().get(1)).setSelected(true);
                        ((RadioButton) listColor.get(i).getChildren().get(3)).setDisable(true);
                        ((RadioButton) listColor.get(i).getChildren().get(4)).setDisable(true);
                        ((RadioButton) listColor.get(i).getChildren().get(0)).setDisable(true);
                        i1 = i;
                    }
                    if (cl.get(i).getString().compareTo(colorfond) == 0) {
                        ((RadioButton) listColor.get(i).getChildren().get(0)).setSelected(true);
                        ((RadioButton) listColor.get(i).getChildren().get(1)).setDisable(true);
                        i0 = i;
                    }
                    int k = i;
                    ((RadioButton) listColor.get(i).getChildren().get(0)).setOnMouseClicked(new EventHandler<MouseEvent>() {
                        public void handle(MouseEvent e) {
                            if (!((RadioButton) e.getSource()).isDisable()) {
                                ((RadioButton) listColor.get(k).getChildren().get(1)).setDisable(true);

                                ((RadioButton) listColor.get(i0).getChildren().get(1)).setDisable(false);
                                i0 = k;
                            }
                        }
                    });
                    ((RadioButton) listColor.get(i).getChildren().get(1)).setOnMouseClicked(new EventHandler<MouseEvent>() {
                        public void handle(MouseEvent e) {
                            if (!((RadioButton) e.getSource()).isDisable()) {
                                ((RadioButton) listColor.get(k).getChildren().get(0)).setDisable(true);
                                ((RadioButton) listColor.get(k).getChildren().get(3)).setDisable(true);
                                ((RadioButton) listColor.get(k).getChildren().get(4)).setDisable(true);

                                ((RadioButton) listColor.get(i1).getChildren().get(0)).setDisable(false);
                                ((RadioButton) listColor.get(i1).getChildren().get(3)).setDisable(false);
                                ((RadioButton) listColor.get(i1).getChildren().get(4)).setDisable(false);
                                i1 = k;
                            }
                        }
                    });
                    ((RadioButton) listColor.get(i).getChildren().get(2)).setOnMouseClicked(new EventHandler<MouseEvent>() {
                        public void handle(MouseEvent e) {
                            if (!((RadioButton) e.getSource()).isDisable()) {
                                ((RadioButton) listColor.get(k).getChildren().get(3)).setDisable(true);
                                ((RadioButton) listColor.get(k).getChildren().get(4)).setDisable(true);

                                ((RadioButton) listColor.get(i2).getChildren().get(3)).setDisable(false);
                                ((RadioButton) listColor.get(i2).getChildren().get(4)).setDisable(false);
                                i2 = k;
                            }
                        }
                    });
                    ((RadioButton) listColor.get(i).getChildren().get(3)).setOnMouseClicked(new EventHandler<MouseEvent>() {
                        public void handle(MouseEvent e) {
                            if (!((RadioButton) e.getSource()).isDisable()) {
                                ((RadioButton) listColor.get(k).getChildren().get(1)).setDisable(true);
                                ((RadioButton) listColor.get(k).getChildren().get(2)).setDisable(true);
                                ((RadioButton) listColor.get(k).getChildren().get(4)).setDisable(true);

                                ((RadioButton) listColor.get(i3).getChildren().get(1)).setDisable(false);
                                ((RadioButton) listColor.get(i3).getChildren().get(2)).setDisable(false);
                                ((RadioButton) listColor.get(i3).getChildren().get(4)).setDisable(false);
                                i3 = k;
                            }
                        }
                    });
                    ((RadioButton) listColor.get(i).getChildren().get(4)).setOnMouseClicked(new EventHandler<MouseEvent>() {
                        public void handle(MouseEvent e) {
                            if (!((RadioButton) e.getSource()).isDisable()) {
                                ((RadioButton) listColor.get(k).getChildren().get(1)).setDisable(true);
                                ((RadioButton) listColor.get(k).getChildren().get(2)).setDisable(true);
                                ((RadioButton) listColor.get(k).getChildren().get(3)).setDisable(true);

                                ((RadioButton) listColor.get(i4).getChildren().get(1)).setDisable(false);
                                ((RadioButton) listColor.get(i4).getChildren().get(2)).setDisable(false);
                                ((RadioButton) listColor.get(i4).getChildren().get(3)).setDisable(false);
                                i4 = k;
                            }
                        }
                    });
                }

                FlowPane fp = new FlowPane();
                fp.setHgap(2);

                Label labelf = new Label("Background");
                labelf.setFont(new Font(14));
                labelf.setTextFill(Color.WHITE);
                labelf.setStyle("-fx-background-color:#555555");

                Label labelgri = new Label("Grid");
                labelgri.setFont(new Font(14));
                labelgri.setTextFill(Color.WHITE);
                labelgri.setStyle("-fx-background-color:#555555");

                Label labelp = new Label("Pointer");
                labelp.setFont(new Font(14));
                labelp.setTextFill(Color.WHITE);
                labelp.setStyle("-fx-background-color:#555555");

                Label labelpl = new Label("Player");
                labelpl.setFont(new Font(14));
                labelpl.setTextFill(Color.WHITE);
                labelpl.setStyle("-fx-background-color:#555555");

                Label labelad = new Label("Adversary");
                labelad.setFont(new Font(14));
                labelad.setTextFill(Color.WHITE);
                labelad.setStyle("-fx-background-color:#555555");

                Label labeln = new Label("        Color Name       ");
                labeln.setFont(new Font(14));
                labeln.setTextFill(Color.WHITE);
                labeln.setStyle("-fx-background-color:#555555");

                fp.getChildren().addAll(labelf, labelgri, labelp, labelpl, labelad, labeln);

                VBox vb = new VBox();
                vb.getChildren().addAll(fp, listview);

                alert_Color.getDialogPane().setContent(vb);

                Optional<ButtonType> result = alert_Color.showAndWait();
                if (result.get().getText() == "Save") {
                    for (int i = 0; i < cl.size(); i++) {
                        if (((RadioButton) listColor.get(i).getChildren().get(0)).isSelected()) {
                            colorfond = cl.get(i).getString();
                        }
                        if (((RadioButton) listColor.get(i).getChildren().get(1)).isSelected()) {
                            colorgri = cl.get(i).getString();
                        }
                        if (((RadioButton) listColor.get(i).getChildren().get(2)).isSelected()) {
                            p = cl.get(i).getColor();
                        }
                        if (((RadioButton) listColor.get(i).getChildren().get(3)).isSelected()) {
                            n = cl.get(i).getColor();
                        }
                        if (((RadioButton) listColor.get(i).getChildren().get(4)).isSelected()) {
                            r = cl.get(i).getColor();
                        }
                        g.getChildren().clear();
                        if (start) {
                            demarrer();
                        } else {
                            accueil();
                        }
                    }
                } else {
                    if (result.get().getText() == "Default") {
                        r = Color.DEEPPINK;
                        n = Color.CYAN;
                        p = Color.BLACK;
                        colorgri = "555555";
                        colorfond = "ffffff";
                        g.getChildren().clear();
                        if (start) {
                            demarrer();
                        } else {
                            accueil();
                        }
                    }
                }
            }
        });
        stcolour.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                ((StackPane) e.getSource()).setStyle("-fx-background-color:lightblue");
            }
        });
        stcolour.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                ((StackPane) e.getSource()).setStyle("");
            }
        });
        Label labelcolour = new Label("Colour");
        labelcolour.setFont(new Font(14));
        stcolour.getChildren().add(labelcolour);

        StackPane stname = new StackPane();
        stname.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                Alert alert_Name = new Alert(AlertType.CONFIRMATION);
                alert_Name.setTitle("Change game name");
                alert_Name.setHeaderText("Change game name or use default name");

                ButtonType saveButton = new ButtonType("Save", ButtonData.OK_DONE);
                alert_Name.getButtonTypes().setAll(new ButtonType("Default", ButtonData.OK_DONE), saveButton, new ButtonType("Cancel", ButtonData.CANCEL_CLOSE));

                Node sb = alert_Name.getDialogPane().lookupButton(saveButton);
                sb.setDisable(true);

                textp = new TextField();
                textp.textProperty().addListener(new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> arg0, String arg1, String e) {
                        if (e.trim().isEmpty() || texta.getText().trim().isEmpty()) {
                            sb.setDisable(true);
                        } else {
                            sb.setDisable(false);
                        }
                    }
                });
                Label label1 = new Label("Player name:       ");
                label1.setPadding(new Insets(2, 0, 0, 0));
                label1.setFont(new Font(14));

                HBox hb1 = new HBox();
                hb1.getChildren().addAll(label1, textp);

                texta = new TextField();
                texta.textProperty().addListener(new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> arg0, String arg1, String e) {
                        if (e.trim().isEmpty() || textp.getText().trim().isEmpty()) {
                            sb.setDisable(true);
                        } else {
                            sb.setDisable(false);
                        }
                    }
                });
                Label label2 = new Label("Adversary name: ");
                label2.setPadding(new Insets(2, 0, 0, 0));
                label2.setFont(new Font(14));

                HBox hb2 = new HBox();
                hb2.getChildren().addAll(label2, texta);

                VBox vb = new VBox();
                vb.setSpacing(10);
                vb.getChildren().addAll(hb1, hb2);
                alert_Name.getDialogPane().setContent(vb);

                Optional<ButtonType> result = alert_Name.showAndWait();
                if (result.get().getText() == "Save") {
                    pname.setText(textp.getText());
                    aname.setText(texta.getText());
                } else {
                    if (result.get().getText() == "Default") {
                        pname.setText(System.getProperty("user.name"));
                        aname.setText("Adversary");
                    }
                }
            }
        });
        stname.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                ((StackPane) e.getSource()).setStyle("-fx-background-color:lightblue");
            }
        });
        stname.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                ((StackPane) e.getSource()).setStyle("");
            }
        });
        Label labelname = new Label("Name");
        labelname.setFont(new Font(14));
        stname.getChildren().add(labelname);

        stoption = new StackPane();
        stoption.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                Alert alert_Option = new Alert(AlertType.CONFIRMATION);
                alert_Option.setTitle("Change game options");
                alert_Option.setHeaderText("Change game options or use default options");

                alert_Option.getButtonTypes().setAll(new ButtonType("Default", ButtonData.OK_DONE), new ButtonType("Save", ButtonData.OK_DONE), new ButtonType("Cancel", ButtonData.CANCEL_CLOSE));

                combop.getSelectionModel().select(np - 4);
                combop.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
                    public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer e) {
                        ch1.setText("Marquer un point avec une boule qui a servi à aligner " + e + " autres boules alignés");
                        ch2.setText("Marquer un point à travers " + e + " autres boules alignés");
                    }
                });

                Label label1 = new Label("Number points to aligne: ");
                label1.setPadding(new Insets(2, 0, 0, 0));
                label1.setFont(new Font(14));

                HBox hb = new HBox();
                hb.getChildren().addAll(label1, combop);

                ch1 = new CheckBox();
                ch1.setSelected(gav);
                ch1.setText("Marquer un point avec une boule qui a servi à aligner " + np + " autres boules alignés");

                ch2 = new CheckBox();
                ch2.setSelected(ptv);
                ch2.setText("Marquer un point à travers " + np + " autres boules alignés");

                VBox vb = new VBox();
                vb.getChildren().addAll(hb, ch1, ch2);

                alert_Option.getDialogPane().setContent(vb);

                Optional<ButtonType> result = alert_Option.showAndWait();
                if (result.get().getText() == "Save") {
                    np = combop.getSelectionModel().getSelectedItem();
                    gav = ch1.isSelected();
                    ptv = ch2.isSelected();
                } else {
                    if (result.get().getText() == "Default") {
                        np = 4;
                        ptv = true;
                        gav = true;
                    }
                }
            }
        });
        stoption.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                ((StackPane) e.getSource()).setStyle("-fx-background-color:lightblue");
            }
        });
        stoption.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                ((StackPane) e.getSource()).setStyle("");
            }
        });
        Label labeloption = new Label("Option");
        labeloption.setFont(new Font(14));
        stoption.getChildren().add(labeloption);

        stsize = new StackPane();
        stsize.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                Alert alert_Size = new Alert(AlertType.CONFIRMATION);
                alert_Size.setTitle("Change grid size");
                alert_Size.setHeaderText("Change grid size or use default size");

                ButtonType saveButton = new ButtonType("Save", ButtonData.OK_DONE);
                alert_Size.getButtonTypes().setAll(new ButtonType("Default", ButtonData.OK_DONE), saveButton, new ButtonType("Cancel", ButtonData.CANCEL_CLOSE));

                Node sb = alert_Size.getDialogPane().lookupButton(saveButton);
                sb.setDisable(true);

                listc = FXCollections.observableArrayList();
                listpc = FXCollections.observableArrayList();

                for (int i = 10; i < 37; i++) {
                    listc.add(i);
                    if (i % 2 == 0) {
                        listpc.add(i);
                    }
                }
                listl = FXCollections.observableArrayList();
                listpl = FXCollections.observableArrayList();
                for (int i = 10; i < 21; i++) {
                    listl.add(i);
                    if (i % 2 == 0) {
                        listpl.add(i);
                    }
                }
                combol = new ComboBox<Integer>(listl);
                combol.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
                    public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer e) {
                        if (e % 2 == 1) {
                            if (comboc.getSelectionModel().isEmpty()) {
                                comboc.setItems(listpc);
                            }
                        } else {
                            if (e % 2 == 0) {
                                if (comboc.getSelectionModel().isEmpty()) {
                                    comboc.setItems(listc);
                                }
                            }
                        }
                        if (comboc.getSelectionModel().isEmpty()) {
                            sb.setDisable(true);
                        } else {
                            sb.setDisable(false);
                        }
                    }
                });
                Label label1 = new Label("Line: ");
                label1.setPadding(new Insets(2, 0, 0, 0));
                label1.setFont(new Font(14));

                comboc = new ComboBox<Integer>(listc);
                comboc.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
                    public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer e) {
                        if (e % 2 == 1) {
                            if (combol.getSelectionModel().isEmpty()) {
                                combol.setItems(listpl);
                            }
                        } else {
                            if (e % 2 == 0) {
                                if (combol.getSelectionModel().isEmpty()) {
                                    combol.setItems(listl);
                                }
                            }
                        }
                        if (combol.getSelectionModel().isEmpty()) {
                            sb.setDisable(true);
                        } else {
                            sb.setDisable(false);
                        }
                    }
                });
                Label label2 = new Label("Column: ");
                label2.setPadding(new Insets(2, 0, 0, 0));
                label2.setFont(new Font(14));

                GridPane gp = new GridPane();
                gp.add(label1, 0, 0);
                gp.add(label2, 0, 1);
                gp.add(combol, 1, 0);
                gp.add(comboc, 1, 1);

                alert_Size.getDialogPane().setContent(gp);

                Optional<ButtonType> result = alert_Size.showAndWait();
                if (result.get().getText() == "Save") {
                    l = combol.getSelectionModel().getSelectedItem();
                    c = comboc.getSelectionModel().getSelectedItem();
                    primaryStage.setHeight(28 * l + 5 * l + 43);
                    primaryStage.setWidth(28 * c + 5 * c + 10 + pan.getWidth());
                    g.getChildren().clear();
                    if (start) {
                        demarrer();
                    } else {
                        accueil();
                    }
                    primaryStage.centerOnScreen();
                } else {
                    if (result.get().getText() == "Default") {
                        l = 20;
                        c = 20;
                        primaryStage.setHeight(28 * l + 5 * l + 43);
                        primaryStage.setWidth(28 * c + 5 * c + 10 + pan.getWidth());
                        g.getChildren().clear();
                        if (start) {
                            demarrer();
                        } else {
                            accueil();
                        }
                        primaryStage.centerOnScreen();
                    }
                }
            }
        });
        stsize.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                ((StackPane) e.getSource()).setStyle("-fx-background-color:lightblue");
            }
        });
        stsize.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                ((StackPane) e.getSource()).setStyle("");
            }
        });
        Label labelsize = new Label("Size");
        labelsize.setFont(new Font(14));
        stsize.getChildren().add(labelsize);

        StackPane stscore = new StackPane();
        stscore.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {

            }
        });
        stscore.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                ((StackPane) e.getSource()).setStyle("-fx-background-color:lightblue");
            }
        });
        stscore.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                ((StackPane) e.getSource()).setStyle("");
            }
        });
        Label labelscore = new Label("Scores");
        labelscore.setFont(new Font(14));
        stscore.getChildren().add(labelscore);

        VBox vbs = new VBox();
        vbs.getChildren().addAll(stcolour, stname, stoption, stsize, stscore);

        TitledPane setting = new TitledPane("Settings", vbs);
        setting.setFont(new Font(14));

        VBox vbh = new VBox();

        TitledPane help = new TitledPane("help", vbh);
        help.setFont(new Font(14));

        stsaveandexit = new StackPane();
        stsaveandexit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                System.exit(0);
            }
        });
        stsaveandexit.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                ((StackPane) e.getSource()).setStyle("-fx-background-color:lightblue");
            }
        });
        stsaveandexit.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                ((StackPane) e.getSource()).setStyle("");
            }
        });
        Label labelsaveandexit = new Label("Save and exit");
        labelsaveandexit.setFont(new Font(14));
        stsaveandexit.getChildren().add(labelsaveandexit);

        stdontsaveandexit = new StackPane();
        stdontsaveandexit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                System.exit(0);
            }
        });
        stdontsaveandexit.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                ((StackPane) e.getSource()).setStyle("-fx-background-color:lightblue");
            }
        });
        stdontsaveandexit.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                ((StackPane) e.getSource()).setStyle("");
            }
        });
        Label labeldontsaveandexit = new Label("Don't Save and exit");
        labeldontsaveandexit.setFont(new Font(14));
        stdontsaveandexit.getChildren().add(labeldontsaveandexit);

        StackPane stexit = new StackPane();
        stexit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                System.exit(0);
            }
        });
        stexit.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                ((StackPane) e.getSource()).setStyle("-fx-background-color:lightblue");
            }
        });
        stexit.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                ((StackPane) e.getSource()).setStyle("");
            }
        });
        Label labelexit = new Label("Exit");
        labelexit.setFont(new Font(14));
        stexit.getChildren().add(labelexit);

        VBox vbe = new VBox();
        vbe.getChildren().addAll(stsaveandexit, stdontsaveandexit, stexit);

        TitledPane exit = new TitledPane("Exit", vbe);
        exit.setFont(new Font(14));

        Accordion menu = new Accordion();
        menu.getPanes().addAll(game, setting, help, exit);

        return menu;
    }

    private void demarrer() {
        win = new Win(gav);
        t = new StackPane[l][c];
        nb = 0;
        start = true;
        double larg = 5;
        double haut = 0;
        lp.setText(String.valueOf(0));
        la.setText(String.valueOf(0));
        g.setStyle("-fx-background-color:#" + colorfond);
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < c; j++) {
                t[i][j] = new StackPane();
                t[i][j].setPrefSize(28, 28);
                t[i][j].setStyle("-fx-background-color:#" + colorgri);
                t[i][j].setCursor(Cursor.HAND);
                AnchorPane.setTopAnchor(t[i][j], haut);
                AnchorPane.setLeftAnchor(t[i][j], larg);
                t[i][j].setUserData(b);
                int k = i;
                int z = j;
                t[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent e) {
                        if (((StackPane) e.getSource()).getChildren().size() == 0) {
                            ((StackPane) e.getSource()).getChildren().add(new Circle(12, n));
                            ((StackPane) e.getSource()).setUserData(n);
                            nb++;
                            play(k, z);
                            if (nb >= l * c) {
                                nb = 0;
                                lp.setText(String.valueOf(0));
                                la.setText(String.valueOf(0));
                                accueil();
                            }
                        }
                    }
                });
                g.getChildren().add(t[i][j]);
                larg += 33;
            }
            haut += 33;
            larg = 5;
        }
        stoption.setDisable(true);
        stsize.setDisable(true);
        stcolour.setDisable(true);
        stend.setDisable(false);
        stsaveandexit.setDisable(false);
        stdontsaveandexit.setDisable(false);
    }

    public HBox compter() {

        pname = new Label(System.getProperty("user.name"));
        pname.setTextFill(Color.WHITE);
        pname.setFont(new Font(20));

        aname = new Label("Adversary");
        aname.setTextFill(Color.WHITE);
        aname.setFont(new Font(20));

        VBox vbn = new VBox();
        vbn.setSpacing(10);
        vbn.getChildren().addAll(pname, aname);

        lp = new Label(String.valueOf(pc));
        lp.setTextFill(Color.WHITE);
        lp.setFont(new Font(20));

        la = new Label(String.valueOf(ac));
        la.setTextFill(Color.WHITE);
        la.setFont(new Font(20));

        VBox vbc = new VBox();
        vbc.setSpacing(10);
        vbc.getChildren().addAll(lp, la);

        HBox hb = new HBox();
        hb.setSpacing(20);
        hb.getChildren().addAll(vbn, vbc);

        return hb;
    }

    /**
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            this.primaryStage = primaryStage;
            pan = new VBox();
            pan.setStyle("-fx-background-color:#555555");
            pan.setSpacing(20);
            pan.getChildren().addAll(compter(), menu());

            g.setPadding(new Insets(5, 5, 5, 0));
            accueil();
            HBox root = new HBox();
            root.getChildren().addAll(g, pan);
            root.setStyle("");

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
