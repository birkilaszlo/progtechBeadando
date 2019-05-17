package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.Model;
import model.ModelDAO;
import model.szavak;

import java.net.URL;
import java.util.*;

/**
 * @author Birki László
 */

public class Controller implements Initializable {
    /**
     * szavak adatbázistáblából kiolvasott összes szót tartalmazó lista
     */
    private List<szavak> beolvasottSzavak = new ArrayList<szavak>();
    /**
     * ModelDAO osztály példányosítása
     */
    private ModelDAO md;
    /**
     * Modell osztály példányosítása
     */
    private Model m = new Model();
    /**
     * A helyes megoldás pozíciójának tárolása
     */
    private int correctNumber;
    /**
     * Gomb feliratának tárolása
     */
    private String testButton = null;
    /**
     * Tanulás felület aktuálisan kiírt szavainak sorrendjének tárolása
     */
    private int actualLearn = 1;

    /**
     * Üdvözlő/kezdő képernyő
     */
    @FXML
    private Pane udvPane;
    /**
     * Teszt képernyő
     */
    @FXML
    private Pane tesztPane;
    /**
     * Új szavak rögzítése felület
     */
    @FXML
    private Pane ujSzoPane;
    /**
     * Tanuláshoz szükséges felület
     */
    @FXML
    private Pane tanulasPane;
    /**
     * popup ablak a hiba jelzésére
     */
    @FXML
    private Pane alertPane;
    /**
     * Angol -> magyar teszt gomb
     */
    @FXML
    private Button angolrolMagyarraButton;
    /**
     * Magyar -> angol teszt gomb
     */
    @FXML
    private Button magyarrolAngolraButton;
    /**
     * Tanulás gomb
     */
    @FXML
    private Button tanulasButton;
    /**
     * Új szó rögzítése gomb
     */
    @FXML
    private Button ujSzoButton;
    /**
     * Kilépés a programból
     */
    @FXML
    private Button kilepesButton;
    /**
     * Folyamat indítása gomb
     */
    @FXML
    private Button mehetButton;
    /**
     * Aktuális felület elhagyása, visszatérés a kezdőképernyőre
     */
    @FXML
    private Button visszaButton;
    /**
     * popUp ablak OK gombja
     */
    @FXML
    private Button backButton2;
    /**
     * Következő szó megjelenítése a tanulás felületen
     */
    @FXML
    private Button next1Button;
    /**
     * Az aktuális szó utáni 10.ik szó megjelenítése a tanulás felületen
     */
    @FXML
    private Button next10Button;
    /**
     * Előző szó megjelenítése a tanulás felületen
     */
    @FXML
    private Button prev1Button;
    /**
     * Az aktuális szó előtti 10.ik szó megjelenítése a tanulás felületen
     */
    @FXML
    private Button prev10Button;
    /**
     * Adott betűvel kezdődő első angol szóra ugrás a tanulás felületen
     */
    @FXML
    private Button runButton;
    /**
     * Teszt felület kérdése
     */
    @FXML
    private Label questionLabel;
    /**
     * Megjelenített szavaka sorszámainak kiírása
     * és a listában szereplő szavak db számainak kijelzése
     */
    @FXML
    private Label actualLabel;
    /**
     * Angol szó a tanulás felületen
     */
    @FXML
    private Label englishLabel;
    /**
     * Magyar szó a tanulás felületen
     */
    @FXML
    private Label hunLabel;
    @FXML
    /**
     * PopUp ablak hibeüzenete
     */
    private Label alertLabel;
    /**
     * Rögzítendő angol szó az új szó felületen
     */
    @FXML
    private TextField englishWordField;
    /**
     * Rögzítendő magyar szó az új szó felületen
     */
    @FXML
    private TextField hunWordField;
    /**
     * Első lehetséges válasz a tesztnél
     */
    @FXML
    private RadioButton answer1Radio;
    /**
     * Második lehetséges válasz a tesztnél
     */
    @FXML
    private RadioButton answer2Radio;
    /**
     * Harmadik lehetséges válasz a tesztnél
     */
    @FXML
    private RadioButton answer3Radio;
    /**
     * Negyedik lehetséges válasz a tesztnél
     */
    @FXML
    private RadioButton answer4Radio;
    /**
     * Helyes volt-e a válasz, grafikus visszajelzés
     */
    @FXML
    private ImageView isCorrectImageView;
    /**
     * Tanulás felület kereséshez használt lenyíló listája
     */
    @FXML
    private ChoiceBox<Character> searchChoiseBox;

    /**
     * Teszt felületre navigálás
     * @param e a megnyomott gomb eseménykezelője
     */
    @FXML
    private void tesztButtonAction(ActionEvent e) {
        setPaneVisible(e);
        testButton = ((Button) e.getSource()).getText();
        setRadioText(testButton);
    }

    /**
     * Funciót elindító gomb
     * @param e a megnyomott gomb eseménykezelője
     */
    @FXML
    void mehetButtonAction(ActionEvent e) {
        String buttonText = ((Button) e.getSource()).getText();
        setPaintRadioButton(buttonText);
    }

    /**
     * Aktuális képernyő elhagyása
     * Főképernyőre navigálás
     * @param e a megnyomott gomb eseménykezelője
     */
    @FXML
    private void visszaButtonAction(ActionEvent e) {
        setPaneVisible(e);
        setDefaultScreen();
    }
    /**
     * Új szó rögzítése
     * @param e a megnyomott gomb eseménykezelője
     */
    @FXML
    void ujSzoButtonActon(ActionEvent e) {
        testButton = ((Button) e.getSource()).getText();
        setPaneVisible(e);
    }
    /**
     * Tanulás képernyőre navigálás
      * @param e a megnyomott gomb eseménykezelője
     */
    @FXML
    void learnButtonAction(ActionEvent e) {
        testButton = ((Button) e.getSource()).getText();
        actualLearn = 1;
        setPaneVisible(e);
        learnScroll();
        setSearchChoiseBox();
    }

    /**
     * Előző szó megjelenítése a tanulás felületen
     * @param event a megnyomott gomb eseménykezelője
     */
    @FXML
    void prev1ButtonAction(ActionEvent event) {
        actualLearn--;
        learnScroll();
    }

    /**
     * Következő szó megjelenítése a tanulás felületen
     * @param event a megnyomott gomb eseménykezelője
     */
    @FXML
    void next1ButtonAction(ActionEvent event) {
        actualLearn++;
        learnScroll();
    }

    /**
     * Az aktuális szó utáni 10.ik szó megjelenítése a tanulás felületen
     * @param event a megnyomott gomb eseménykezelője
     */
    @FXML
    void next10ButtonAction(ActionEvent event) {
        actualLearn += 10;
        learnScroll();
    }

    /**
     * Az aktuális szó előtti 10.ik szó megjelenítése a tanulás felületen
     * @param event a megnyomott gomb eseménykezelője
     */
    @FXML
    void prev10ButtonAction(ActionEvent event) {
        actualLearn -= 10;
        learnScroll();
    }

    /**
     * A lenyíló listából választott kezőbetű első angol előfordulásához ugrás a tanulás felületen
     * @param event a megnyomott gomb eseménykezelője
     */
    @FXML
    void runButtonAction(ActionEvent event) {
        if(searchChoiseBox.getValue() == null){
            setAlertPaneVisible("Kérlek válassz betűt a lenyíló listából");
            setActualPane("learn");
            return;
        }
        char c = searchChoiseBox.getValue();
        for (int i = 0; i < beolvasottSzavak.size(); i++) {
            if(beolvasottSzavak.get(i).getAngol().toUpperCase().charAt(0) == c){
                actualLearn = i;
                learnScroll();
                return;
            }
        }
    }

    /**
     * Az új szó rögzítése felületen megadott angol és magyar szavak vizsgálata
     * Ha a vizsgálat true értékeket adott vissza, akkor a szavak rögzítése a szavak adatbázistáblába
     * Ha az adatbázis tábla beszúrása sikeres volt, akkor a lokálisan hasznáét lista bövítése az új szavakkal
     * Felhasználó értesítése az esetleges hibákról, vagy a sikeres rögzítésről egy popUp ablakban
     * @param event a megnyomott gomb eseménykezelője
     */
    @FXML
    void createNewWordsButtonAction(ActionEvent event) {
        if(m.checkNewWordsString(englishWordField.getText()) == false){
            setActualPane("new word");
            setAlertPaneVisible("Nem megfelelő a beírt angol szó");
            return;
        }
        if(m.checkNewWordsString(hunWordField.getText()) == false){
            setActualPane("new word");
            setAlertPaneVisible("Nem megfelelő a beírt magyar szó");
            return;
        }
        if(m.searchWordInList("angol", englishWordField.getText(), beolvasottSzavak)){
            setActualPane("new word");
            setAlertPaneVisible("Ez az angol szó már létezik az adatbázisban");
            return;
        }
        if(m.searchWordInList("magyar", hunWordField.getText(), beolvasottSzavak)){
            setActualPane("new word");
            setAlertPaneVisible("Ez a magyar szó már létezik az adatbázisban");
            return;
        }
        int beforeCount = 0;
        beforeCount = md.tableCount();

        String angol = englishWordField.getText();
        String magyar = hunWordField.getText();
        md.createSzavak(angol, magyar);

        int afterCount = 0;
        afterCount = md.tableCount();

        if(beforeCount != afterCount){
            beolvasottSzavak.add(new szavak(angol, magyar));
            setActualPane("new word");
            setAlertPaneVisible("Sikeres rögzítés");
        }
    }

    /**
     * Felületek közti navigacíót biztosító metódus
     * @param event a megnyomott gomb eseménykezelője
     */
    private void setPaneVisible(ActionEvent event){
        tesztPane.setVisible(false);
        ujSzoPane.setVisible(false);
        udvPane.setVisible(false);
        tanulasPane.setVisible(false);
        alertPane.setVisible(false);

        String buttonText = ((Button) event.getSource()).getText();

        if(buttonText.equals("Angolról magyarra") || buttonText.equals("Magyarról angolra")){
            tesztPane.setVisible(true);
        }else if(buttonText.equals("Tanulás")){
            tanulasPane.setVisible(true);
        }else if(buttonText.equals("Új szó rögzítése")){
            ujSzoPane.setVisible(true);
        }else if(buttonText.equals("Vissza")){
            udvPane.setVisible(true);
        }
    }

    /**
     * Aktív popUp ablak OK gombja, popUp ablak eltütetése és a szükséges felület újra aktiválása
     * @param event a megnyomott gomb eseménykezelője
     */
    @FXML
    public void backButtonAction(ActionEvent event){
        alertPane.setVisible(false);
        if(testButton.equals("Angolról magyarra") || testButton.equals("Magyarról angolra")){
            tesztPane.setOpacity(1);
            tesztPane.setDisable(false);
        }else if(testButton.equals("Új szó rögzítése")){
            ujSzoPane.setOpacity(1);
            ujSzoPane.setDisable(false);
        }else if(testButton.equals("Tanulás")){
            tanulasPane.setOpacity(1);
            tanulasPane.setDisable(false);
        }
    }

    /**
     * Kilpés a programból
     * @param event a megnyomott gomb eseménykezelője
     */
    @FXML
    void kilepesButtonAction(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Teszt felület válaszok szövegeinek beállítása
     * @param buttonText A használt gomb feliratát tartalmazó String,
     *                   annak eldöntésére hogy angol -> magyar, vagy magyar -> angol felületen vagyunk-e
     */
    public void setRadioText(String buttonText){
        List<Integer> randomNumber = m.generateRandomNumbers(1, beolvasottSzavak.size(), 4);
        correctNumber = m.generateCorrentAnswerPlace(1, 4);

        if(buttonText.equals("Angolról magyarra")){
            questionLabel.setText(beolvasottSzavak.get(randomNumber.get(0)).getAngol());
            switch(correctNumber){
                case 1:
                    answer1Radio.setText(beolvasottSzavak.get(randomNumber.get(0)).getMagyar());
                    answer2Radio.setText(beolvasottSzavak.get(randomNumber.get(1)).getMagyar());
                    answer3Radio.setText(beolvasottSzavak.get(randomNumber.get(2)).getMagyar());
                    answer4Radio.setText(beolvasottSzavak.get(randomNumber.get(3)).getMagyar());
                    break;
                case 2:
                    answer2Radio.setText(beolvasottSzavak.get(randomNumber.get(0)).getMagyar());
                    answer1Radio.setText(beolvasottSzavak.get(randomNumber.get(1)).getMagyar());
                    answer3Radio.setText(beolvasottSzavak.get(randomNumber.get(2)).getMagyar());
                    answer4Radio.setText(beolvasottSzavak.get(randomNumber.get(3)).getMagyar());
                    break;
                case 3:
                    answer3Radio.setText(beolvasottSzavak.get(randomNumber.get(0)).getMagyar());
                    answer1Radio.setText(beolvasottSzavak.get(randomNumber.get(1)).getMagyar());
                    answer2Radio.setText(beolvasottSzavak.get(randomNumber.get(2)).getMagyar());
                    answer4Radio.setText(beolvasottSzavak.get(randomNumber.get(3)).getMagyar());
                    break;
                case 4:
                    answer4Radio.setText(beolvasottSzavak.get(randomNumber.get(0)).getMagyar());
                    answer1Radio.setText(beolvasottSzavak.get(randomNumber.get(1)).getMagyar());
                    answer2Radio.setText(beolvasottSzavak.get(randomNumber.get(2)).getMagyar());
                    answer3Radio.setText(beolvasottSzavak.get(randomNumber.get(3)).getMagyar());
                    break;
            }
        }else if(buttonText.equals("Magyarról angolra")){
            questionLabel.setText(beolvasottSzavak.get(randomNumber.get(0)).getMagyar());
            switch(correctNumber){
                case 1:
                    answer1Radio.setText(beolvasottSzavak.get(randomNumber.get(0)).getAngol());
                    answer2Radio.setText(beolvasottSzavak.get(randomNumber.get(1)).getAngol());
                    answer3Radio.setText(beolvasottSzavak.get(randomNumber.get(2)).getAngol());
                    answer4Radio.setText(beolvasottSzavak.get(randomNumber.get(3)).getAngol());
                    break;
                case 2:
                    answer2Radio.setText(beolvasottSzavak.get(randomNumber.get(0)).getAngol());
                    answer1Radio.setText(beolvasottSzavak.get(randomNumber.get(1)).getAngol());
                    answer3Radio.setText(beolvasottSzavak.get(randomNumber.get(2)).getAngol());
                    answer4Radio.setText(beolvasottSzavak.get(randomNumber.get(3)).getAngol());
                    break;
                case 3:
                    answer3Radio.setText(beolvasottSzavak.get(randomNumber.get(0)).getAngol());
                    answer1Radio.setText(beolvasottSzavak.get(randomNumber.get(1)).getAngol());
                    answer2Radio.setText(beolvasottSzavak.get(randomNumber.get(2)).getAngol());
                    answer4Radio.setText(beolvasottSzavak.get(randomNumber.get(3)).getAngol());
                    break;
                case 4:
                    answer4Radio.setText(beolvasottSzavak.get(randomNumber.get(0)).getAngol());
                    answer1Radio.setText(beolvasottSzavak.get(randomNumber.get(1)).getAngol());
                    answer2Radio.setText(beolvasottSzavak.get(randomNumber.get(2)).getAngol());
                    answer3Radio.setText(beolvasottSzavak.get(randomNumber.get(3)).getAngol());
                    break;
            }
        }
    }

    /**
     * A teszt felületen adott válasz alapján a lehetséges válaszok kinézeték módosítása
     * Zölddel jelezve a helyes választ és pirossal a helytelen választ
     * Felhasználó válaszának lekérése és a megfelelő image megjelenítése
     * @param buttonText A használt gomb feliratát tartalmazó String,
     *      *                   annak eldöntésére, hogy a metódus melyik része hajtódjon végre
     */
    public void setPaintRadioButton(String buttonText){
        if (buttonText.equals("Mehet")){
            if(isRadioSelected()){
                return;
            }
            answer1Radio.setDisable(true);
            answer2Radio.setDisable(true);
            answer3Radio.setDisable(true);
            answer4Radio.setDisable(true);
            answer1Radio.setTextFill((Color.web("#990000")));
            answer2Radio.setTextFill((Color.web("#990000")));
            answer3Radio.setTextFill((Color.web("#990000")));
            answer4Radio.setTextFill((Color.web("#990000")));
            switch(correctNumber){
                case 1: answer1Radio.setTextFill((Color.web("#009900"))); break;
                case 2: answer2Radio.setTextFill((Color.web("#009900"))); break;
                case 3: answer3Radio.setTextFill((Color.web("#009900"))); break;
                case 4: answer4Radio.setTextFill((Color.web("#009900"))); break;
            }
            mehetButton.setText("Következő kérdés");
            int answer = 0;
            if(answer1Radio.isSelected() == true)
                answer = 1;
            if(answer2Radio.isSelected() == true)
                answer = 2;
            if(answer3Radio.isSelected() == true)
                answer = 3;
            if(answer4Radio.isSelected() == true)
                answer = 4;

            isCorrectImageView.setVisible(true);
            if(answer == correctNumber){
                Image image1 = new Image("/pics/correct.png");
                isCorrectImageView.setImage(image1);
            }else{
                Image image2 = new Image("/pics/incorrect.png");
                isCorrectImageView.setImage(image2);
            }

        }else if(buttonText.equals("Következő kérdés")){
            setRadioText(testButton);
            setDefaultScreen();
        }
    }


    /**
     * Ellenőrzés hogy a felhasználó jelölt-e meg a teszt felületen lehetőséget,
     * ha nem akkor popUp ablak aktiválása, hibaüzenet beállítása
     * @return true ha nem jelölt be a felhasználó semmit, false ha minden rendben van
     */
    private boolean isRadioSelected(){
        if(answer1Radio.isSelected() == false && answer2Radio.isSelected() == false &&
           answer3Radio.isSelected() == false && answer4Radio.isSelected() == false){
            tesztPane.setOpacity(0.3);
            tesztPane.setDisable(true);
            setAlertPaneVisible("Nem jelölél be egy lehetőséget se");
            return true;
        }
        return false;
    }

    /**
     * Tanulás felületen történő lapozás kezelése
     */
    private void learnScroll(){
        englishLabel.setText(beolvasottSzavak.get(actualLearn).getAngol());
        hunLabel.setText(beolvasottSzavak.get(actualLearn).getMagyar());
        actualLabel.setText(actualLearn + " / " + beolvasottSzavak.size());
        setLearnButtonDisable();
        searchChoiseBox.getSelectionModel().clearSelection();
    }

    /**
     * Képernyők alaphelyzetbe állítása
     */
    private void setDefaultScreen(){
        answer1Radio.setSelected(false);
        answer2Radio.setSelected(false);
        answer3Radio.setSelected(false);
        answer4Radio.setSelected(false);
        answer1Radio.setDisable(false);
        answer2Radio.setDisable(false);
        answer3Radio.setDisable(false);
        answer4Radio.setDisable(false);
        answer1Radio.setTextFill((Color.web("#000000")));
        answer2Radio.setTextFill((Color.web("#000000")));
        answer3Radio.setTextFill((Color.web("#000000")));
        answer4Radio.setTextFill((Color.web("#000000")));
        isCorrectImageView.setVisible(false);
        mehetButton.setText("Mehet");
        englishWordField.setText("");
        hunWordField.setText("");
    }

    /**
     * PopUp ablak aktiválása
     * @param alertText
     */
    private void setAlertPaneVisible(String alertText){
        alertPane.setVisible(true);
        alertLabel.setText(alertText);
    }

    /**
     * Tanulás felületen lévő lenyíló lista feltötése a szavak lista angol szavainak kezdőbetűivel
     */
    private void setSearchChoiseBox(){
        Set<Character> chars = new HashSet<Character>();
        Collections.sort(beolvasottSzavak);
        for (int i = 0; i < beolvasottSzavak.size(); i++) {
            chars.add(beolvasottSzavak.get(i).getAngol().toUpperCase().charAt(0));
        }
        ArrayList<Character> ch = new ArrayList<>(chars);
        ObservableList<Character> olist = FXCollections.observableArrayList(ch);
        searchChoiseBox.setItems(olist);
    }
    /**
     * Aktuális pane inaktiválása
     */
    private void setActualPane(String actual){
        if(actual.equals("new word")){
            ujSzoPane.setOpacity(0.3);
            ujSzoPane.setDisable(true);
        }
        if(actual.equals("learn")){
            tanulasPane.setOpacity(0.3);
            tanulasPane.setDisable(true);
        }
    }

    /**
     * Tanulás felületen lévő gombok aktivitásának állítása
     * a megjelenített szavak sorszámától függően
     */
    private void setLearnButtonDisable(){
        if (actualLearn == 1){
            prev10Button.setDisable(true);
            prev1Button.setDisable(true);
            next1Button.setDisable(false);
            next10Button.setDisable(false);
        }
        if (actualLearn > 1 && actualLearn < 10){
            prev10Button.setDisable(true);
            prev1Button.setDisable(false);
            next1Button.setDisable(false);
            next10Button.setDisable(false);
        }
        if (actualLearn > 10){
            prev10Button.setDisable(false);
            prev1Button.setDisable(false);
            next1Button.setDisable(false);
            next10Button.setDisable(false);
        }
        if (actualLearn > beolvasottSzavak.size() - 10){
            prev10Button.setDisable(false);
            prev1Button.setDisable(false);
            next1Button.setDisable(false);
            next10Button.setDisable(true);
        }
        if (actualLearn == beolvasottSzavak.size() - 1){
            prev10Button.setDisable(false);
            prev1Button.setDisable(false);
            next1Button.setDisable(true);
            next10Button.setDisable(true);
        }
    }

    /**
     * Gombokon lévő képek megjelenítésénk beállítása
     */
    private void setButtonsImage(){
        Node nextNode1 = new ImageView(new Image(getClass().getResourceAsStream("/pics/Actions-go-next-icon.png")));
        next1Button.setGraphic(nextNode1);
        Node nextNode10 = new ImageView(new Image(getClass().getResourceAsStream("/pics/Actions-go-next-10-icon.png")));
        next10Button.setGraphic(nextNode10);
        Node prevNode1 = new ImageView(new Image(getClass().getResourceAsStream("/pics/Actions-go-previous-Icon.png")));
        prev1Button.setGraphic(prevNode1);
        Node prevNode10 = new ImageView(new Image(getClass().getResourceAsStream("/pics/Actions-go-prevous-10-icon.png")));
        prev10Button.setGraphic(prevNode10);
    }

    /**
     * Felület inicializálása
     * @param location Location url
     * @param resources Resources
     */
    public void initialize(URL location, ResourceBundle resources) {
        md = new ModelDAO();
        beolvasottSzavak = md.osszesSzo();
        setButtonsImage();
    }
}



