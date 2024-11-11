package sio.tp7;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class TP7Controller implements Initializable {

    private ImageView firstClicked;
    int nbCartesRetournees;
    ArrayList<Carte> lesCartes;
    int nbCoups;
    int nbCartesTrouvees;
    @FXML
    private Label lblTitre;
    @FXML
    private TextField txtNbCoups;
    @FXML
    private GridPane grdPlateau;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img4;
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img5;
    @FXML
    private ImageView img6;
    @FXML
    private ImageView img7;
    @FXML
    private ImageView img8;
    @FXML
    private ImageView img9;
    @FXML
    private ImageView img10;
    @FXML
    private ImageView img11;
    @FXML
    private ImageView img12;
    @FXML
    private ImageView img13;
    @FXML
    private ImageView img14;
    @FXML
    private ImageView img15;
    @FXML
    private ImageView img16;
    @FXML
    private AnchorPane apMemory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initJeu();
    }

    public void initJeu()
    {
        nbCartesRetournees = 0;
        nbCoups = 0;
        nbCartesTrouvees = 0;
        txtNbCoups.setText(String.valueOf(nbCoups));
        lesCartes = new ArrayList<>();

        Carte carte1 = new Carte("Image1.png");
        Carte carte2 = new Carte("Image2.png");
        Carte carte3 = new Carte("Image3.png");
        Carte carte4 = new Carte("Image4.png");
        Carte carte5 = new Carte("Image5.png");
        Carte carte6 = new Carte("Image6.png");
        Carte carte7 = new Carte("Image7.png");
        Carte carte8 = new Carte("Image8.png");

        lesCartes.add(carte1);lesCartes.add(carte2);lesCartes.add(carte3);lesCartes.add(carte4);
        lesCartes.add(carte5);lesCartes.add(carte6);lesCartes.add(carte7);lesCartes.add(carte8);
        lesCartes.add(carte1);lesCartes.add(carte2);lesCartes.add(carte3);lesCartes.add(carte4);
        lesCartes.add(carte5);lesCartes.add(carte6);lesCartes.add(carte7);lesCartes.add(carte8);

        Collections.shuffle(lesCartes);

        img1.setImage(new Image(getClass().getResource("/Images/Image0.png").toString()));
        img2.setImage(new Image(getClass().getResource("/Images/Image0.png").toString()));
        img3.setImage(new Image(getClass().getResource("/Images/Image0.png").toString()));
        img4.setImage(new Image(getClass().getResource("/Images/Image0.png").toString()));
        img5.setImage(new Image(getClass().getResource("/Images/Image0.png").toString()));
        img6.setImage(new Image(getClass().getResource("/Images/Image0.png").toString()));
        img7.setImage(new Image(getClass().getResource("/Images/Image0.png").toString()));
        img8.setImage(new Image(getClass().getResource("/Images/Image0.png").toString()));
        img9.setImage(new Image(getClass().getResource("/Images/Image0.png").toString()));
        img10.setImage(new Image(getClass().getResource("/Images/Image0.png").toString()));
        img11.setImage(new Image(getClass().getResource("/Images/Image0.png").toString()));
        img12.setImage(new Image(getClass().getResource("/Images/Image0.png").toString()));
        img13.setImage(new Image(getClass().getResource("/Images/Image0.png").toString()));
        img14.setImage(new Image(getClass().getResource("/Images/Image0.png").toString()));
        img15.setImage(new Image(getClass().getResource("/Images/Image0.png").toString()));
        img16.setImage(new Image(getClass().getResource("/Images/Image0.png").toString()));
    }

    @FXML
    public void imageClicked(Event event) throws InterruptedException {

        // On récupère l'image cliquée
        ImageView clickedImageView = (ImageView) event.getSource();

        // Si l'image est déjà retournée : ne rien faire
        if (clickedImageView.getImage().getUrl().substring(clickedImageView.getImage().getUrl().length()-10).equals("Image0.png")) {
            nbCartesRetournees++;
            // Vérifiez si c'est la première ou la deuxième image cliquée
            if (firstClicked == null) {
                firstClicked = clickedImageView;
                // Retournez la première image
                retournerImage(firstClicked);
            } else
            {
                // Retournez la 2ème image
                retournerImage(clickedImageView);
                // Vérifiez si les images sont identiques
                if (!firstClicked.getImage().getUrl().equals(clickedImageView.getImage().getUrl())) {
                    new Thread(() ->
                    {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        } finally {
                            firstClicked.setImage(new Image(getClass().getResource("/Images/Image0.png").toString()));
                            clickedImageView.setImage(new Image(getClass().getResource("/Images/Image0.png").toString()));
                            nbCoups++;
                            txtNbCoups.setText(String.valueOf(nbCoups));
                            firstClicked = null;
                        }
                    }).start();
                } else {
                    // Les images sont identiques
                    nbCoups++;
                    txtNbCoups.setText(String.valueOf(nbCoups));
                    nbCartesTrouvees++;
                    firstClicked = null;
                }
                nbCartesRetournees = 0;
                if (nbCartesTrouvees == 8) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Fin de la partie");
                    alert.setHeaderText("");
                    alert.setContentText("Partie terminée");
                    alert.showAndWait();
                    initJeu();
                }
            }
        }
    }
    private void retournerImage(ImageView imageView)
    {
        if (imageView == img1) {
            img1.setImage(new Image(getClass().getResource("/Images/" + lesCartes.get(0).getNom()).toString()));
        } else if (imageView == img2) {
            img2.setImage(new Image(getClass().getResource("/Images/" + lesCartes.get(1).getNom()).toString()));
        } else if (imageView == img3) {
            img3.setImage(new Image(getClass().getResource("/Images/" + lesCartes.get(2).getNom()).toString()));
        } else if (imageView == img4) {
            img4.setImage(new Image(getClass().getResource("/Images/" + lesCartes.get(3).getNom()).toString()));
        } else if (imageView == img5) {
            img5.setImage(new Image(getClass().getResource("/Images/" + lesCartes.get(4).getNom()).toString()));
        }else if (imageView == img6) {
            img6.setImage(new Image(getClass().getResource("/Images/" + lesCartes.get(5).getNom()).toString()));
        }else if (imageView == img7) {
            img7.setImage(new Image(getClass().getResource("/Images/" + lesCartes.get(6).getNom()).toString()));
        }else if (imageView == img8) {
            img8.setImage(new Image(getClass().getResource("/Images/" + lesCartes.get(7).getNom()).toString()));
        }else if (imageView == img9) {
            img9.setImage(new Image(getClass().getResource("/Images/" + lesCartes.get(8).getNom()).toString()));
        }else if (imageView == img10) {
            img10.setImage(new Image(getClass().getResource("/Images/" + lesCartes.get(9).getNom()).toString()));
        }else if (imageView == img11) {
            img11.setImage(new Image(getClass().getResource("/Images/" + lesCartes.get(10).getNom()).toString()));
        }else if (imageView == img12) {
            img12.setImage(new Image(getClass().getResource("/Images/" + lesCartes.get(11).getNom()).toString()));
        }else if (imageView == img13) {
            img13.setImage(new Image(getClass().getResource("/Images/" + lesCartes.get(12).getNom()).toString()));
        }else if (imageView == img14) {
            img14.setImage(new Image(getClass().getResource("/Images/" + lesCartes.get(13).getNom()).toString()));
        }else if (imageView == img15) {
            img15.setImage(new Image(getClass().getResource("/Images/" + lesCartes.get(14).getNom()).toString()));
        }else if (imageView == img16) {
            img16.setImage(new Image(getClass().getResource("/Images/" + lesCartes.get(15).getNom()).toString()));
        }
    }
}