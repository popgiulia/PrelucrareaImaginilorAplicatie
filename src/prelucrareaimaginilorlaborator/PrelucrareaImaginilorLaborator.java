/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prelucrareaimaginilorlaborator;

import java.awt.Color;
import java.awt.Paint;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.FileWriter;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;

/**
 *
 * @bogdan
 *
 *
API-urile JavaFX au fost portate direct în Java, bazându-se mai mult pe standarde Internet, cum ar fi CSS pentru stilizarea controalelor sau ARIA pentru specificări referitoare la accesibilitate.
 
Graful de Scene
Implementarea unei aplicații JavaFX implică proiectarea și dezvoltarea unui graf de scene (eng. Scene Graph), 
structură ierarhică de noduri ce conţine elementele vizuale ale interfeţei grafice cu utilizatorul, 
care poate trata diferite evenimente legate de acestea şi care poate fi redată.

Un element din graful de scene (= un nod) este identificat în mod unic, fiind caracterizat printr-o clasă de stil şi 
un volum care îl delimitează. 
Fiecare nod are un părinte (cu excepția nodului rădăcină), putând avea nici unul, unul sau mai mulţi copii.
De asemenea, pentru un astfel de element pot fi definite efecte (estompări sau umbre), opacitate, transformări, 
mecanisme de tratare a diferitelor evenimente (care vizează interacţiunea cu utilizatorul) precum şi starea aplicaţiei.

Spre diferenţă de Swing sau AWT (Abstract Window Toolkit), JavaFX conţine pe lângă mecanisme de dispunere a conţinutului, controale, imagini sau obiecte multimedia şi 
primitive pentru elemente grafice (ca fi texte sau figuri geometice cu care se pot crea animaţii, folosind metodele puse la dispoziţie de API-urile javafx.animation).

API-ul javafx.scene permite construirea următoarelor conţinuturi:

noduri: forme 2D şi 3D, imagini, conţinut multimedia şi conţinut Internet, text, controale pentru interacţiunea cu utilizatorul, grafice, containere;
stări: transformări (poziţionări şi orientări ale nodurilor), efecte vizuale;
efecte: obiecte care modifică aspectul nodurilor (mecanisme de estompare, umbre, reglarea culorilor).
Mecanisme de Dispunere a Conţinutului
Controalele din graful de scene pot fi grupate în containere sau panouri în mod flexibil, folosind mai multe mecanisme de dispunere a conținutului (eng. layout).

API-ul JavaFX defineşte mai multe clase de tip container pentru dispunerea elementelor, în pachetul javafx.scene.layout:

BorderPane dispune nodurile conţinute în regiunile de sus, jos, dreapta, stânga sau centru;
HBox îşi aranjează conţinutul orizontal pe un singur rând;
VBox îşi aranjează conţinutul vertical pe o singură coloană;
StackPane utilizează o stivă de noduri afişând elementele unele peste altele, din spate către față;
GridPane permite utilizatorului să îşi definească un tabel (format din rânduri şi coloane) în care să poată fi încadrate elementele conţinute;
FlowPane dispune elementele fie orizontal, fie vertical, în funcţie de limitele specificate de programator (lungime pentru dispunere orizontală, respectiv înălţime pentru dispunere verticală);
TilePane plasează nodurile conţinute în celule de dimensiuni uniforme;
AnchorPane oferă programatorilor posibilitatea de a defini noduri ancoră (referinţă) în funcţie de colţurile de jos / sus, din stânga / dreapta sau raportat la centrul containerului sau panoului.
Diferitele moduri de dispunere pot fi imbricate în cadrul unei aplicaţii JavaFX pentru a se obţine funcţionalitatea dorită.
 
 */
public class PrelucrareaImaginilorLaborator extends Application {
    BufferedImage bufferedImag;
    Label name;
    BufferedImage finalImageT;
    boolean dilateBackgroundPixel = false;
    
    @Override
    public void start(Stage mainStage) {

        //Avem nevoie de o modalitate de a alege un fisier imagine
        //Vom folosi FileChooser
        //
        ImageView imageView = new ImageView();

        StackPane root = new StackPane();
        Scene scene = new Scene(root);
        
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("Fisiere");
        MenuItem menuItem_Open = new MenuItem("Afiseaza Imagine");
        MenuItem menuItem_OpenLZW = new MenuItem("Afiseaza Imagine LZW");
        MenuItem menuItem_OpenHUFF = new MenuItem("Afiseaza Imagine HUFF");
        MenuItem menuItem_OpenHaar = new MenuItem("Afiseaza Imagine Haar");
        MenuItem menuItem_Save = new MenuItem("Salveaza Imagine");
        MenuItem menuItem_SaveLZW = new MenuItem("Salveaza Imagine LZW");
        MenuItem menuItem_SaveHUFF = new MenuItem("Salveaza Imagine HUFF");
        MenuItem menuItem_SaveHaarCompress = new MenuItem("Salveaza Imagine Haar");
        
        
        SeparatorMenuItem sep = new SeparatorMenuItem();
        MenuItem menuItem_Exit = new MenuItem("Iesire");
        menuFile.getItems().addAll(menuItem_Open, menuItem_Save, menuItem_OpenLZW, menuItem_SaveLZW, menuItem_OpenHUFF, menuItem_SaveHUFF, menuItem_OpenHaar, menuItem_SaveHaarCompress, sep, menuItem_Exit);

        Menu menuChange = new Menu("Modificare");
        
        Menu menuFilter = new Menu("Filtre");
        
        MenuItem menuItem_RGB = new MenuItem("Canale RGB");
        menuItem_RGB.setDisable(true);
        
        MenuItem menuItem_Grey = new MenuItem("Imagine alb-negru");
        menuItem_Grey.setDisable(true);
        
        MenuItem menuItem_Threshold = new MenuItem("Threshold");
        menuItem_Threshold.setDisable(true);
        
        MenuItem menuItem_YUV = new MenuItem("Conversie RGB -> YUV");
        menuItem_YUV.setDisable(true);
        
        MenuItem menuItem_YCbCr = new MenuItem("Conversie RGB -> YCbCr");
        menuItem_YCbCr.setDisable(true);
  
        MenuItem menuItem_inversa = new MenuItem("Inversa imaginii");
        menuItem_inversa.setDisable(true);        

        MenuItem menuItem_HSV = new MenuItem("Conversie RGB -> HSV");
        menuItem_HSV.setDisable(true);          
        
        MenuItem menuItem_Grayscale2 = new MenuItem("Grayscale2");
        menuItem_Grayscale2.setDisable(true);   
        
        MenuItem menuItem_Grayscale3 = new MenuItem("Grayscale3");
        menuItem_Grayscale3.setDisable(true);    
        
        MenuItem menuItem_egalizareHistograma = new MenuItem("Egalizare Histograma");
        menuItem_egalizareHistograma.setDisable(true);  

        MenuItem menuItem_graficHistograma = new MenuItem("Grafic Histograma");
        menuItem_graficHistograma.setDisable(true);          

        MenuItem menuItem_filtruMediere3 = new MenuItem("Filtru Mediere (3x3)");
        menuItem_filtruMediere3.setDisable(true);
        
        MenuItem menuItem_filtruMediere5 = new MenuItem("Filtru Mediere (5x5)");
        menuItem_filtruMediere5.setDisable(true);
        
        MenuItem menuItem_filtruAccentuare = new MenuItem("Filtru Accentuare");
        menuItem_filtruAccentuare.setDisable(true);        
        
        MenuItem menuItem_filtruMedian = new MenuItem("Filtru Median");
        menuItem_filtruMedian.setDisable(true);

        MenuItem menuItem_filtruMinMax = new MenuItem("Filtru de Minim/Maxim");
        menuItem_filtruMinMax.setDisable(true);
        
        MenuItem menuItem_filtruLaplacian = new MenuItem("Filtru Laplacian");
        menuItem_filtruLaplacian.setDisable(true);  
 
        MenuItem menuItem_filtru_Dilatare_Eroziune = new MenuItem("Filtrul de dilatare/eroziune");
        menuItem_filtru_Dilatare_Eroziune.setDisable(true);

        MenuItem menuItem_filtru_Detectie = new MenuItem("Filtre de detectie a conturului");
        menuItem_filtru_Detectie.setDisable(true);                
        
        MenuItem menuItem_etichetare = new MenuItem("Etichetare");
        menuItem_etichetare.setDisable(true);
        
        menuChange.getItems().addAll(menuItem_RGB, menuItem_Grey, menuItem_Threshold, menuItem_YUV, menuItem_YCbCr, menuItem_inversa, menuItem_HSV,menuItem_Grayscale2, menuItem_Grayscale3, menuItem_egalizareHistograma, menuItem_graficHistograma);
        
        menuFilter.getItems().addAll(menuItem_filtruMediere3, menuItem_filtruMediere5, menuItem_filtruAccentuare, menuItem_filtruMedian, menuItem_filtruMinMax, menuItem_filtruLaplacian, menuItem_filtru_Dilatare_Eroziune, menuItem_filtru_Detectie, menuItem_etichetare);
        
        menuBar.getMenus().addAll(menuFile, menuChange, menuFilter);

        VBox vbox = new VBox(menuBar);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(0, 10, 0, 10));

        ScrollPane sp = new ScrollPane();
        vbox.getChildren().add(sp);
        
        
        menuItem_Open.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Afiseaza Imagine");
            
            File file = fileChooser.showOpenDialog(mainStage);
            if (file != null) {
                try {
                    VBox vbOpen = new VBox();
 
                    name = new Label(file.getAbsolutePath());
                    bufferedImag = ImageIO.read(file);

                    BufferedImage imageN = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_ARGB);

                    for (int y = 0; y < bufferedImag.getHeight(); y++) {
                        for (int x = 0; x < bufferedImag.getWidth(); x++) {
                           //Retrieving contents of a pixel
                           int pixel = bufferedImag.getRGB(x,y);
                           //Creating a Color object from pixel value
                           Color color = new Color(pixel, true);
                           //Retrieving the R G B values
                           int alpha = color.getAlpha();
                           int red = color.getRed();
                           int green = color.getGreen();
                           int blue = color.getBlue();
                           Color myWhite = new Color(red, green, blue, alpha);
                           imageN.setRGB(x, y, myWhite.getRGB());
                        }
                    }
        
                    Image image = SwingFXUtils.toFXImage(imageN, null);
                    
                    vbOpen.getChildren().addAll(name,imageView);

                    imageView.setFitHeight(400);
                    imageView.setPreserveRatio(true);
                    imageView.setImage(image);
                    imageView.setSmooth(true);
                    imageView.setCache(true);
                    
                    sp.setContent(vbOpen);
                    
                    for(int i = 0; i < menuBar.getMenus().get(1).getItems().size(); i++)
                        menuBar.getMenus().get(1).getItems().get(i).setDisable(false);

                    for(int i = 0; i < menuBar.getMenus().get(2).getItems().size(); i++)
                        menuBar.getMenus().get(2).getItems().get(i).setDisable(false);                    
                    
                } catch (IOException ex) {
                    Logger.getLogger(PrelucrareaImaginilorLaborator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        menuItem_Save.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Salvare Imagine");
            
            File file = fileChooser.showSaveDialog(mainStage);
            if (file != null) {
                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(imageView.getImage(),null), "png", file);
                } catch (IOException ex) {
                    Logger.getLogger(PrelucrareaImaginilorLaborator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });      
        
        
        menuItem_OpenLZW.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Afiseaza Imagine");

            File file = fileChooser.showOpenDialog(mainStage);
            if (file != null) {
                try {
                    VBox vbOpen = new VBox();
                    name = new Label(file.getAbsolutePath());
                    BufferedImage decompressedImage = LZW_decompress(file.getAbsolutePath());
                    
                    Image image = SwingFXUtils.toFXImage(decompressedImage, null);
                    vbOpen.getChildren().addAll(name, imageView);
                    imageView.setFitHeight(400);
                    imageView.setPreserveRatio(true);
                    imageView.setImage(image);
                    imageView.setSmooth(true);
                    imageView.setCache(true);
                    sp.setContent(vbOpen);
                } catch (Exception ex) {
                    Logger.getLogger(PrelucrareaImaginilorLaborator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        menuItem_SaveLZW.setOnAction((ActionEvent event) -> {
            try {
                String lzwImage = LZW_compress(bufferedImag);
            } catch (IOException ex) {
                Logger.getLogger(PrelucrareaImaginilorLaborator.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("EROARE COMPRESIE");
            }
        });
        
        
        menuItem_OpenHUFF.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Afiseaza Imagine");

            File file = fileChooser.showOpenDialog(mainStage);
            if (file != null) {
                try {
                    VBox vbOpen = new VBox();

                    name = new Label(file.getAbsolutePath());

                    BufferedImage imageN = HuffmanDecompress();
                    
                    Image image = SwingFXUtils.toFXImage(imageN, null);
                    imageView.setFitHeight(400);
                    imageView.setPreserveRatio(true);
                    imageView.setImage(image);
                    imageView.setSmooth(true);
                    imageView.setCache(true);
                    vbOpen.getChildren().addAll(name, imageView);
                    sp.setContent(vbOpen);
                    
                } catch (Exception ex) {
                    Logger.getLogger(PrelucrareaImaginilorLaborator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        menuItem_SaveHUFF.setOnAction((ActionEvent event) -> {
            HuffmanCompress(bufferedImag);
        });
       
        
        menuItem_OpenHaar.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Afiseaza Imagine");
            
            File file = fileChooser.showOpenDialog(mainStage);
            if (file != null) {
                try {
                    VBox vbOpen = new VBox();

                    name = new Label(file.getAbsolutePath());

                    // Loading the compressed Haar image from the file
                    BufferedImage compressedImage = ImageIO.read(file);

                    // Decompress the loaded image
                    BufferedImage decompressedImage = HaarDecompress(compressedImage);

                    // Convert the decompressed image to JavaFX Image
                    Image image = SwingFXUtils.toFXImage(decompressedImage, null);
                    imageView.setFitHeight(400);
                    imageView.setPreserveRatio(true);
                    imageView.setImage(image);
                    imageView.setSmooth(true);
                    imageView.setCache(true);
                    vbOpen.getChildren().addAll(name, imageView);
                    sp.setContent(vbOpen);
                    
                } catch (IOException ex) {
                    Logger.getLogger(PrelucrareaImaginilorLaborator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

                
        menuItem_SaveHaarCompress.setOnAction((ActionEvent event) -> {
            BufferedImage compressedImage = HaarCompress(bufferedImag);
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Compressed Image");
            File file = fileChooser.showSaveDialog(mainStage);
            if (file != null) {
                try {
                    ImageIO.write(compressedImage, "png", file);
                } catch (IOException e) {
                    System.out.println("Failed to save compressed image: " + e.getMessage());
                }
            }
        });

        
        menuItem_RGB.setOnAction((ActionEvent event) -> {
            ScrollPane spR = new ScrollPane();
            ScrollPane spG = new ScrollPane();
            ScrollPane spB = new ScrollPane();
            ScrollPane spRGB = new ScrollPane();

            BufferedImage imageR = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
            BufferedImage imageG = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
            BufferedImage imageB = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
            BufferedImage imageRGB = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);

            for (int y = 0; y < bufferedImag.getHeight(); y++) {
                for (int x = 0; x < bufferedImag.getWidth(); x++) {
                   //Retrieving contents of a pixel
                   int pixel = bufferedImag.getRGB(x,y);
                   //Creating a Color object from pixel value
                   Color color = new Color(pixel, true);
                   //Retrieving the R G B values
                   int red = color.getRed();
                   int green = color.getGreen();
                   int blue = color.getBlue();
                   Color myR = new Color(red, 0, 0);
                   Color myG = new Color(0, green, 0);
                   Color myB = new Color(0, 0, blue);
                   imageR.setRGB(x, y, myR.getRGB());
                   imageG.setRGB(x, y, myG.getRGB());
                   imageB.setRGB(x, y, myB.getRGB());
                   imageRGB.setRGB(x, y, pixel);
                }
            }

            Image imgR = SwingFXUtils.toFXImage(imageR, null);
            ImageView imageViewR = new ImageView();
            imageViewR.setFitHeight(400);
            imageViewR.setPreserveRatio(true);
            imageViewR.setImage(imgR);
            imageViewR.setSmooth(true);
            imageViewR.setCache(true);
            spR.setContent(imageViewR);
            
            Image imgG = SwingFXUtils.toFXImage(imageG, null);
            ImageView imageViewG = new ImageView();
            imageViewG.setFitHeight(400);
            imageViewG.setPreserveRatio(true);
            imageViewG.setImage(imgG);
            imageViewG.setSmooth(true);
            imageViewG.setCache(true);
            spG.setContent(imageViewG);
            
            Image imgB = SwingFXUtils.toFXImage(imageB, null);
            ImageView imageViewB = new ImageView();
            imageViewB.setFitHeight(400);
            imageViewB.setPreserveRatio(true);
            imageViewB.setImage(imgB);
            imageViewB.setSmooth(true);
            imageViewB.setCache(true);
            spB.setContent(imageViewB);

            VBox vbRGB = new VBox();
            
            Image imgRGB = SwingFXUtils.toFXImage(imageRGB, null);
            ImageView imageViewRGB = new ImageView();
            imageViewRGB.setFitHeight(400);
            imageViewRGB.setPreserveRatio(true);
            imageViewRGB.setImage(imgRGB);
            imageViewRGB.setSmooth(true);
            imageViewRGB.setCache(true);
            
            Slider sliderRGB = new Slider();
            sliderRGB.setMin(0);
            sliderRGB.setMax(255);
            sliderRGB.setValue(0);
            sliderRGB.setShowTickLabels(true);
            sliderRGB.setShowTickMarks(true);
            sliderRGB.setMajorTickUnit(50);
            sliderRGB.setMinorTickCount(5);
            sliderRGB.setBlockIncrement(1);
            sliderRGB.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                int dif = new_val.intValue();// - old_val.intValue();
                for (int y = 0; y < bufferedImag.getHeight(); y++) {
                    for (int x = 0; x < bufferedImag.getWidth(); x++) {
                        int pixel = bufferedImag.getRGB(x, y);
                        Color color = new Color(pixel, true);
                        //Retrieving the R G B values
                        int red = this.adjustColor( color.getRed(), dif);
                        int green  = this.adjustColor(color.getGreen(), dif);
                        int blue = this.adjustColor(color.getBlue(), dif);
                        
                        Color myR = new Color(red, 0, 0);
                        Color myG = new Color(0, green, 0);
                        Color myB = new Color(0, 0, blue);
                        Color myRGB = new Color(red, green, blue);
                        
                        imageRGB.setRGB(x, y, myRGB.getRGB());
                        imageR.setRGB(x, y, myR.getRGB());
                        imageG.setRGB(x, y, myG.getRGB());
                        imageB.setRGB(x, y, myB.getRGB());
                    }
                }
                imageViewR.setImage(SwingFXUtils.toFXImage(imageR, null));
                imageViewG.setImage(SwingFXUtils.toFXImage(imageG, null));
                imageViewB.setImage(SwingFXUtils.toFXImage(imageB, null));
                imageViewRGB.setImage(SwingFXUtils.toFXImage(imageRGB, null));
            });
            
            vbRGB.getChildren().addAll(imageViewRGB, sliderRGB);
            spRGB.setContent(vbRGB);

            Stage secondStage = new Stage();

            Scene sceneRGB = new Scene(new HBox(), 400, 350);
            ((HBox)sceneRGB.getRoot()).getChildren().addAll(spR, spG, spB, spRGB);

            secondStage.setTitle(name.getText());
            secondStage.setScene(sceneRGB);
            secondStage.show();                       
        });
        
        
        menuItem_Grey.setOnAction((ActionEvent event) -> {
            ScrollPane spG1 = new ScrollPane();
            ScrollPane spG2 = new ScrollPane();
            ScrollPane spG3 = new ScrollPane();

            BufferedImage imageG1 = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
            BufferedImage imageG2 = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
            BufferedImage imageG3 = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
            for (int y = 0; y < bufferedImag.getHeight(); y++) {
                for (int x = 0; x < bufferedImag.getWidth(); x++) {
                   //Retrieving contents of a pixel
                   int pixel = bufferedImag.getRGB(x,y);
                   //Creating a Color object from pixel value
                   Color color = new Color(pixel, true);
                   //Retrieving the R G B values
                   int red = color.getRed();
                   int green = color.getGreen();
                   int blue = color.getBlue();
       
                   int G1color = (red+green+blue)/3;
                   Color myG1 = new Color(G1color,G1color,G1color);
                   int G2color = (int)(0.299*red+0.587*green+0.114*blue);
                   Color myG2 = new Color(G2color,G2color,G2color);
                   double min = Math.min(Math.min(red,green),blue);
                   double max = Math.max(Math.max(red,green),blue);                   
                   int G3color = (int)(min/2+max/2);
                   Color myG3 = new Color(G3color,G3color,G3color);
                   imageG1.setRGB(x, y, myG1.getRGB());
                   imageG2.setRGB(x, y, myG2.getRGB());
                   imageG3.setRGB(x, y, myG3.getRGB());                                           
                }
            }    
                          
            Image image = SwingFXUtils.toFXImage(imageG1, null);
            ImageView imageViewG1 = new ImageView();
            imageViewG1.setFitHeight(400);
            imageViewG1.setPreserveRatio(true);
            imageViewG1.setImage(image);
            imageViewG1.setSmooth(true);
            imageViewG1.setCache(true);
            spG1.setContent(imageViewG1);
            
            Image image2 = SwingFXUtils.toFXImage(imageG2, null);
            ImageView imageViewG2 = new ImageView();
            imageViewG2.setFitHeight(400);
            imageViewG2.setPreserveRatio(true);
            imageViewG2.setImage(image2);
            imageViewG2.setSmooth(true);
            imageViewG2.setCache(true);
            spG2.setContent(imageViewG2);
            
            Image image3 = SwingFXUtils.toFXImage(imageG3, null);
            ImageView imageViewG3 = new ImageView();
            imageViewG3.setFitHeight(400);
            imageViewG3.setPreserveRatio(true);
            imageViewG3.setImage(image3);
            imageViewG3.setSmooth(true);
            imageViewG3.setCache(true);
            spG3.setContent(imageViewG3);
                   
            Stage secondStage2 = new Stage();        
            VBox vboxRGB = new VBox();
            vboxRGB.setAlignment(Pos.CENTER);
            vboxRGB.setSpacing(10);
            vboxRGB.setPadding(new Insets(0, 10, 0, 10));
            vboxRGB.getChildren().addAll(spG1);
            vboxRGB.getChildren().addAll(spG2);
            vboxRGB.getChildren().addAll(spG3);

            Scene sceneGrey = new Scene(new VBox(), 400, 350);
            ((VBox)sceneGrey.getRoot()).getChildren().addAll(vboxRGB);

            secondStage2.setTitle(name.getText());
            secondStage2.setScene(sceneGrey);
            secondStage2.show();                      
        });

        
        menuItem_Threshold.setOnAction((ActionEvent event) -> {
            ScrollPane spT = new ScrollPane();

            BufferedImage imageT = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);

            for (int y = 0; y < bufferedImag.getHeight(); y++) {
                for (int x = 0; x < bufferedImag.getWidth(); x++) {
                   //Retrieving contents of a pixel
                   int pixel = bufferedImag.getRGB(x,y);
                   //Creating a Color object from pixel value
                   Color color = new Color(pixel, true);
                   //Retrieving the R G B values
                   int red = color.getRed();
                   int green = color.getGreen();
                   int blue = color.getBlue();
                   int G1color = (red+green+blue)/3;
                   Color myT = new Color(G1color, G1color, G1color);
                   imageT.setRGB(x, y, myT.getRGB());                  
                }
            }

            Image imgT = SwingFXUtils.toFXImage(imageT, null);
            ImageView imageViewT = new ImageView();
            imageViewT.setFitHeight(400);
            imageViewT.setPreserveRatio(true);
            imageViewT.setImage(imgT);
            imageViewT.setSmooth(true);
            imageViewT.setCache(true);
            spT.setContent(imageViewT);
                    
            Slider sliderG = new Slider();
            sliderG.setMin(0);
            sliderG.setMax(255);
            sliderG.setValue(127);
            sliderG.setShowTickLabels(true);
            sliderG.setShowTickMarks(true);
            sliderG.setMajorTickUnit(50);
            sliderG.setMinorTickCount(5);
            sliderG.setBlockIncrement(1);
            sliderG.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                int dif = new_val.intValue();// - old_val.intValue();
                for (int y = 0; y < bufferedImag.getHeight(); y++) {
                    for (int x = 0; x < bufferedImag.getWidth(); x++) {
                        int pixel = bufferedImag.getRGB(x, y);
                        Color color = new Color(pixel, true);
                        //Retrieving the R G B values
                        int red = color.getRed();
                        int blue = color.getBlue();
                        int green = color.getGreen();
                        
                        int G1color = (red+green+blue)/3;
                        if (G1color <= dif) {
                            G1color = 0;
                        } else {
                            G1color = 255;
                        }
                        Color myT = new Color(G1color, G1color, G1color);
                        imageT.setRGB(x, y, myT.getRGB());
                    }
                }
                imageViewT.setImage(SwingFXUtils.toFXImage(imageT, null));
            });
            
            VBox vbRGB = new VBox();
            vbRGB.getChildren().addAll(imageViewT, sliderG);
            spT.setContent(vbRGB);

            Stage secondStage = new Stage();

            Scene sceneRGB = new Scene(new VBox(), 400, 350);
            ((VBox)sceneRGB.getRoot()).getChildren().addAll(spT);

            secondStage.setTitle(name.getText());
            secondStage.setScene(sceneRGB);
            secondStage.show();                    
        });
        
        
        menuItem_YUV.setOnAction((ActionEvent event) -> {
            ScrollPane s = new ScrollPane();
            BufferedImage imageN = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
            for (int y = 0; y < bufferedImag.getHeight(); y++) {
                for (int x = 0; x < bufferedImag.getWidth(); x++) {
                   //Retrieving contents of a pixel
                   int pixel = bufferedImag.getRGB(x,y);
                   //Creating a Color object from pixel value
                   Color color = new Color(pixel, true);
                   //Retrieving the R G B values
                   int red = color.getRed();
                   int green = color.getGreen();
                   int blue = color.getBlue();
                 //  Y = 0,3*R + 0,6*G  + 0,1*B 
                   double y1 = 0.3*red+0.6*green+0.1*blue;
                   if(y1<0)
                       y1=0;
                   if(y1>255)
                       y1=255;
                 //   U = 0,74*(R-Y) + 0,27*(B-Y) 
                   double u1 = 0.74*(red-y1)+0.27*(blue-y1);
                   if(u1<0)
                       u1=0;
                   if(u1>255)
                       u1=255;
                  //  V = 0,48*(R-Y) + 0,41 *(B-Y) 
                    double v1 = 0.48*(red-y1)+0.41*(blue-y1);
                    if(v1<0)
                       v1=0;
                   if(v1>255)
                       v1=255;                 
                   Color myYUV = new Color((int)y1,(int)u1,(int)v1);                  
                   imageN.setRGB(x, y, myYUV.getRGB());
                }               
            }                         
            Image image = SwingFXUtils.toFXImage(imageN, null);
            ImageView imageViewYUV = new ImageView();
            imageViewYUV.setFitHeight(400);
            imageViewYUV.setPreserveRatio(true);
            imageViewYUV.setImage(image);
            imageViewYUV.setSmooth(true);
            imageViewYUV.setCache(true);
            s.setContent(imageViewYUV);
            
            Stage secondStage = new Stage();        
            VBox vboxRGB = new VBox();
            vboxRGB.setAlignment(Pos.CENTER);
            vboxRGB.setSpacing(10);
            vboxRGB.setPadding(new Insets(0, 10, 0, 10));
            vboxRGB.getChildren().addAll(s);
            

            Scene sceneRGB = new Scene(new VBox(), 400, 350);
            ((VBox)sceneRGB.getRoot()).getChildren().addAll(vboxRGB);

            secondStage.setTitle(name.getText());
            secondStage.setScene(sceneRGB);
            secondStage.show();                  
        });
     
        
        menuItem_YCbCr.setOnAction((ActionEvent event) -> {
            ScrollPane s = new ScrollPane();
            BufferedImage imageN = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
            for (int y = 0; y < bufferedImag.getHeight(); y++) {
                for (int x = 0; x < bufferedImag.getWidth(); x++) {
                   //Retrieving contents of a pixel
                   int pixel = bufferedImag.getRGB(x,y);
                   //Creating a Color object from pixel value
                   Color color = new Color(pixel, true);
                   //Retrieving the R G B values
                   int red = color.getRed();
                   int green = color.getGreen();
                   int blue = color.getBlue();
                   //Y   =     0.299  R + 0.587  G + 0.114  B 
                   double Y = 0.299*red+0.587*green+0.114*blue;
                   //Cb  =   - 0.1687 R - 0.3313 G + 0.498 B + 128 
                   double Cb = -0.1687*red-0.3313*green+0.498*blue+128;
                   //Cr  =     0.498 R - 0.4187 G - 0.0813 B + 128 
                   double Cr = 0.498*red-0.4187*green-0.0813*blue+128;
                   if(Y<0)
                       Y=0;
                   if(Y>255)
                       Y=255;           
                   if(Cb<0)
                       Cb=0;
                   if(Cb>255)
                       Cb=255;
                    if(Cr<0)
                       Cr=0;
                   if(Cr>255)
                       Cr=255;                 
                   Color YCbCr = new Color((int)Y,(int)Cb,(int)Cr);                  
                   imageN.setRGB(x, y, YCbCr.getRGB());
                }               
            }                         
            Image image = SwingFXUtils.toFXImage(imageN, null);
            ImageView imageViewYCbCr = new ImageView();
            imageViewYCbCr.setFitHeight(400);
            imageViewYCbCr.setPreserveRatio(true);
            imageViewYCbCr.setImage(image);
            imageViewYCbCr.setSmooth(true);
            imageViewYCbCr.setCache(true);
            s.setContent(imageViewYCbCr);
            
            Stage secondStage = new Stage();        
            VBox vboxRGB = new VBox();
            vboxRGB.setAlignment(Pos.CENTER);
            vboxRGB.setSpacing(10);
            vboxRGB.setPadding(new Insets(0, 10, 0, 10));
            vboxRGB.getChildren().addAll(s);
            

            Scene sceneRGB = new Scene(new VBox(), 400, 350);
            ((VBox)sceneRGB.getRoot()).getChildren().addAll(vboxRGB);

            secondStage.setTitle(name.getText());
            secondStage.setScene(sceneRGB);
            secondStage.show();  
        });

        
        menuItem_inversa.setOnAction((ActionEvent event) -> {
            ScrollPane s = new ScrollPane();
            BufferedImage imageINV = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
            for (int y = 0; y < bufferedImag.getHeight(); y++) {
                for (int x = 0; x < bufferedImag.getWidth(); x++) {
                   //Retrieving contents of a pixel
                   int pixel = bufferedImag.getRGB(x,y);
                   //Creating a Color object from pixel value
                   Color color = new Color(pixel, true);
                   //Retrieving the R G B values
                   int red = color.getRed();
                   int green = color.getGreen();
                   int blue = color.getBlue();
                   Color myRGB = new Color(255-red,255-green,255-blue);                  
                   imageINV.setRGB(x, y, myRGB.getRGB());
                }               
            }                         
            Image image = SwingFXUtils.toFXImage(imageINV, null);
            ImageView imageViewINV = new ImageView();
            imageViewINV.setFitHeight(400);
            imageViewINV.setPreserveRatio(true);
            imageViewINV.setImage(image);
            imageViewINV.setSmooth(true);
            imageViewINV.setCache(true);
            s.setContent(imageViewINV);
            
            Stage secondStage = new Stage();        
            VBox vboxRGB = new VBox();
            vboxRGB.setAlignment(Pos.CENTER);
            vboxRGB.setSpacing(10);
            vboxRGB.setPadding(new Insets(0, 10, 0, 10));
            vboxRGB.getChildren().addAll(s);
            
            Scene sceneRGB = new Scene(new VBox(), 400, 350);
            ((VBox)sceneRGB.getRoot()).getChildren().addAll(vboxRGB);

            secondStage.setTitle(name.getText());
            secondStage.setScene(sceneRGB);
            secondStage.show();          
        });
        

        menuItem_HSV.setOnAction((ActionEvent event) -> {
            ScrollPane s = new ScrollPane();
            BufferedImage imageHSV = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
            for (int y = 0; y < bufferedImag.getHeight(); y++) {
                for (int x = 0; x < bufferedImag.getWidth(); x++) {
                   //Retrieving contents of a pixel
                   int pixel = bufferedImag.getRGB(x,y);
                   //Creating a Color object from pixel value
                   Color color = new Color(pixel, true);
                   //Retrieving the R G B values
                   double red = color.getRed();
                   double r=red/255;           
                   double green = color.getGreen();
                   double g = green/255;
                   double blue = color.getBlue();
                   double b = blue/255;  
               
                   double m = Math.min(Math.min(r,g),b);
                   double M = Math.max(Math.max(r,g),b);
                   
                   double C = M - m;
                   //Value:
                   double V = M;                  
                   //Saturation:
                   double S;
                   if(V != 0)
                       S = C / V;
                   else
                       S = 0;
                   //Hue      
                   double H = 0;
                   if(C != 0){
                       if (M == r)
                           H = 60 * (g - b) / C;
                       if (M == g)
                           H = 120 + 60 * (b - r) / C;
                       if (M == b)
                           H = 240 + 60 * (r - g) / C;
                   }
                   else
                       H = 0;
                   if(H < 0)
                       H = H + 360;
                   
                   int H_norm = (int)(H * 255 / 360);
                   int S_norm = (int)(S * 255);
                   int V_norm = (int)(V * 255);
                   
                   Color myHSV = new Color(H_norm, S_norm, V_norm);                  
                   imageHSV.setRGB(x, y, myHSV.getRGB());
                }               
            }                         
            Image image = SwingFXUtils.toFXImage(imageHSV, null);
            ImageView imageViewHSV = new ImageView();
            imageViewHSV.setFitHeight(400);
            imageViewHSV.setPreserveRatio(true);
            imageViewHSV.setImage(image);
            imageViewHSV.setSmooth(true);
            imageViewHSV.setCache(true);
            s.setContent(imageViewHSV);
            
            Stage secondStage = new Stage();        
            VBox vboxRGB = new VBox();
            vboxRGB.setAlignment(Pos.CENTER);
            vboxRGB.setSpacing(10);
            vboxRGB.setPadding(new Insets(0, 10, 0, 10));
            vboxRGB.getChildren().addAll(s);
            

            Scene sceneRGB = new Scene(new VBox(), 400, 350);
            ((VBox)sceneRGB.getRoot()).getChildren().addAll(vboxRGB);

            secondStage.setTitle(name.getText());
            secondStage.setScene(sceneRGB);
            secondStage.show();           
        });
        
        
        menuItem_Grayscale2.setOnAction((ActionEvent event) -> { 

            ScrollPane spH = new ScrollPane();         
            BufferedImage imageH = this.getGrayscaleImage(bufferedImag); 

            Image imgH = SwingFXUtils.toFXImage(imageH, null); 
            ImageView imageViewH = new ImageView(); 
            imageViewH.setFitHeight(400); 
            imageViewH.setPreserveRatio(true); 
            imageViewH.setImage(imgH); 
            imageViewH.setSmooth(true); 
            imageViewH.setCache(true); 
            spH.setContent(imageViewH); 

            VBox vbH = new VBox(); 
            vbH.getChildren().addAll(imageViewH); 
            spH.setContent(vbH); 

            Stage secondStage = new Stage(); 

            Scene sceneH = new Scene(new HBox(), 400, 350); 
            ((HBox)sceneH.getRoot()).getChildren().addAll(spH); 

            secondStage.setTitle(name.getText()); 
            secondStage.setScene(sceneH); 
            secondStage.show();             

        }); 
        
        
        menuItem_Grayscale3.setOnAction((ActionEvent event) -> { 

            ScrollPane spH = new ScrollPane(); 
            BufferedImage imageH = this.getGray(bufferedImag);

            Image imgH = SwingFXUtils.toFXImage(imageH, null); 
            ImageView imageViewH = new ImageView(); 
            imageViewH.setFitHeight(400); 
            imageViewH.setPreserveRatio(true); 
            imageViewH.setImage(imgH); 
            imageViewH.setSmooth(true); 
            imageViewH.setCache(true); 
            
            spH.setContent(imageViewH); 

            VBox vbH = new VBox(); 
            vbH.getChildren().addAll(imageViewH); 
            spH.setContent(vbH); 

            Stage secondStage = new Stage(); 

            Scene sceneH = new Scene(new HBox(), 400, 350); 
            ((HBox)sceneH.getRoot()).getChildren().addAll(spH); 

            secondStage.setTitle(name.getText()); 
            secondStage.setScene(sceneH); 
            secondStage.show();             

        }); 

    
        menuItem_egalizareHistograma.setOnAction((ActionEvent event) -> { 

            ScrollPane spH = new ScrollPane(); 
            BufferedImage imageH = this.equalize(bufferedImag);

            Image imgH = SwingFXUtils.toFXImage(imageH, null); 
            ImageView imageViewH = new ImageView(); 
            imageViewH.setFitHeight(400); 
            imageViewH.setPreserveRatio(true); 
            imageViewH.setImage(imgH); 
            imageViewH.setSmooth(true); 
            imageViewH.setCache(true); 
            spH.setContent(imageViewH); 
            
            VBox vbH = new VBox(); 
            vbH.getChildren().addAll(imageViewH); 
            spH.setContent(vbH); 

            Stage secondStage = new Stage(); 

            Scene sceneH = new Scene(new HBox(), 400, 350); 
            ((HBox)sceneH.getRoot()).getChildren().addAll(spH); 

            secondStage.setTitle(name.getText()); 
            secondStage.setScene(sceneH); 
            secondStage.show();             

        }); 
        
        
        menuItem_graficHistograma.setOnAction((ActionEvent event) -> { 
            ScrollPane spH = new ScrollPane();        
            Stage secondStage = new Stage(); 
            SwingNode sn = new SwingNode(); 

            ChartPanel cn = this.createChartPanel(bufferedImag); 
            sn.setContent(cn); 
            spH.setContent(sn); 

            Scene sceneH = new Scene(new HBox(), 400, 350); 
            HBox hb = (HBox) sceneH.getRoot(); 
            hb.getChildren().addAll(spH); 

            secondStage.setTitle(name.getText()); 
            secondStage.setScene(sceneH); 
            secondStage.show(); 

        }); 
        
        
        menuItem_filtruMediere3.setOnAction((ActionEvent event) -> { 
            ScrollPane spH = new ScrollPane(); 
            BufferedImage imageH = this.mediere3(bufferedImag);

            Image imgH = SwingFXUtils.toFXImage(imageH, null); 
            ImageView imageViewH = new ImageView(); 
            imageViewH.setFitHeight(400); 
            imageViewH.setPreserveRatio(true); 
            imageViewH.setImage(imgH); 
            imageViewH.setSmooth(true); 
            imageViewH.setCache(true); 
            spH.setContent(imageViewH); 

            VBox vbH = new VBox(); 
            vbH.getChildren().addAll(imageViewH); 
            spH.setContent(vbH); 

            Stage secondStage = new Stage(); 

            Scene sceneH = new Scene(new HBox(), 400, 350); 
            ((HBox)sceneH.getRoot()).getChildren().addAll(spH); 

            secondStage.setTitle(name.getText()); 
            secondStage.setScene(sceneH); 
            secondStage.show();        

        });
        
        
        menuItem_filtruMediere5.setOnAction((ActionEvent event) -> { 
            ScrollPane spH = new ScrollPane(); 
            BufferedImage imageH = this.mediere5(bufferedImag);

            Image imgH = SwingFXUtils.toFXImage(imageH, null); 
            ImageView imageViewH = new ImageView(); 
            imageViewH.setFitHeight(400); 
            imageViewH.setPreserveRatio(true); 
            imageViewH.setImage(imgH); 
            imageViewH.setSmooth(true); 
            imageViewH.setCache(true); 
            spH.setContent(imageViewH); 

            VBox vbH = new VBox(); 
            vbH.getChildren().addAll(imageViewH); 
            spH.setContent(vbH); 

            Stage secondStage = new Stage(); 

            Scene sceneH = new Scene(new HBox(), 400, 350); 
            ((HBox)sceneH.getRoot()).getChildren().addAll(spH); 

            secondStage.setTitle(name.getText()); 
            secondStage.setScene(sceneH); 
            secondStage.show();        

        });   
        
        
        menuItem_filtruAccentuare.setOnAction((ActionEvent event) -> { 
            ScrollPane spH = new ScrollPane(); 
            BufferedImage imageH = this.accentuare(bufferedImag);

            Image imgH = SwingFXUtils.toFXImage(imageH, null); 
            ImageView imageViewH = new ImageView(); 
            imageViewH.setFitHeight(400); 
            imageViewH.setPreserveRatio(true); 
            imageViewH.setImage(imgH); 
            imageViewH.setSmooth(true); 
            imageViewH.setCache(true); 
            spH.setContent(imageViewH); 

            VBox vbH = new VBox(); 
            vbH.getChildren().addAll(imageViewH); 
            spH.setContent(vbH); 

            Stage secondStage = new Stage(); 
            Scene sceneH = new Scene(new HBox(), 400, 350); 
            ((HBox)sceneH.getRoot()).getChildren().addAll(spH); 

            secondStage.setTitle(name.getText()); 
            secondStage.setScene(sceneH); 
            secondStage.show();        

        });
        
        
        menuItem_filtruMedian.setOnAction((ActionEvent event) -> { 
            ScrollPane spH = new ScrollPane(); 
            BufferedImage imageH = this.median(bufferedImag);

            Image imgH = SwingFXUtils.toFXImage(imageH, null); 
            ImageView imageViewH = new ImageView(); 
            imageViewH.setFitHeight(400); 
            imageViewH.setPreserveRatio(true); 
            imageViewH.setImage(imgH); 
            imageViewH.setSmooth(true); 
            imageViewH.setCache(true); 
            spH.setContent(imageViewH); 

            VBox vbH = new VBox(); 
            vbH.getChildren().addAll(imageViewH); 
            spH.setContent(vbH); 

            Stage secondStage = new Stage(); 
            Scene sceneH = new Scene(new HBox(), 400, 350); 
            ((HBox)sceneH.getRoot()).getChildren().addAll(spH); 

            secondStage.setTitle(name.getText()); 
            secondStage.setScene(sceneH); 
            secondStage.show();        

        });
        
        
        menuItem_filtruMinMax.setOnAction((ActionEvent event) -> { 
            ScrollPane spH = new ScrollPane(); 
            int a = 4;
            BufferedImage imageH = this.medianMinMax(bufferedImag, a);
            ImageView imageViewH = new ImageView(); 
            imageViewH.setImage(SwingFXUtils.toFXImage(imageH, null));
            imageViewH.setFitHeight(400);
            imageViewH.setPreserveRatio(true);
            imageViewH.setSmooth(true);
            imageViewH.setCache(true);
            spH.setContent(imageViewH);
            
            Slider sliderRGB = new Slider(0, 8, 4);  
            sliderRGB.setShowTickLabels(true);
            sliderRGB.setShowTickMarks(true);
            sliderRGB.setMajorTickUnit(1);
            sliderRGB.setMinorTickCount(0);
            sliderRGB.setBlockIncrement(1);
            sliderRGB.setSnapToTicks(true);

            sliderRGB.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                int d = new_val.intValue();
                BufferedImage imageModif = medianMinMax(bufferedImag, d);                  
                imageViewH.setImage(SwingFXUtils.toFXImage(imageModif, null));
                imageViewH.setFitHeight(400);
                imageViewH.setPreserveRatio(true);
                imageViewH.setSmooth(true);
                imageViewH.setCache(true);        
                });

            VBox vbRGB = new VBox();
            vbRGB.getChildren().addAll(imageViewH, sliderRGB);
            spH.setContent(vbRGB);

            Stage secondStage = new Stage();
            Scene sceneRGB = new Scene(new VBox(), 400, 350);
            ((VBox)sceneRGB.getRoot()).getChildren().addAll(spH);
     
            secondStage.setTitle(name.getText()); 
            secondStage.setScene(sceneRGB); 
            secondStage.show();        

        });
        
        
        menuItem_filtruLaplacian.setOnAction((ActionEvent event) -> { 
            ScrollPane spH = new ScrollPane(); 
            BufferedImage imageH = this.filterLaplacian(bufferedImag);

            Image imgH = SwingFXUtils.toFXImage(imageH, null); 
            ImageView imageViewH = new ImageView(); 
            imageViewH.setFitHeight(400); 
            imageViewH.setPreserveRatio(true); 
            imageViewH.setImage(imgH); 
            imageViewH.setSmooth(true); 
            imageViewH.setCache(true); 
            spH.setContent(imageViewH); 
            
            VBox vbH = new VBox(); 
            vbH.getChildren().addAll(imageViewH); 
            spH.setContent(vbH); 

            Stage secondStage = new Stage(); 
            Scene sceneH = new Scene(new HBox(), 400, 350); 
            ((HBox)sceneH.getRoot()).getChildren().addAll(spH); 

            secondStage.setTitle(name.getText()); 
            secondStage.setScene(sceneH); 
            secondStage.show();        

        });
        
        
        menuItem_filtru_Dilatare_Eroziune.setOnAction((ActionEvent event) -> { 
            
            ScrollPane spT = new ScrollPane();
            BufferedImage imageT = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
            CheckBox checkBox = new CheckBox("Eroziune");
            
            int dif = 127;
            for (int y = 0; y < bufferedImag.getHeight(); y++) {
                for (int x = 0; x < bufferedImag.getWidth(); x++) {
                    int pixel = bufferedImag.getRGB(x, y);
                    Color color = new Color(pixel, true);
                    //Retrieving the R G B values
                    int red = color.getRed();
                    int green  = color.getGreen();
                    int blue = color.getBlue();
                    int G = (red + blue + green) / 3;
                    if (G <= dif)
                    {
                        G = 0;
                    }
                    else
                    {
                        G = 255;
                    }
                    Color myG = new Color(G, G, G);
                    imageT.setRGB(x, y, myG.getRGB());      
                } 
            }
            
            this.finalImageT = imageT;
            int iteratie = 1;
            checkBox.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
                dilateBackgroundPixel = !dilateBackgroundPixel;
            });
            
            ImageView imageViewT = new ImageView();
            imageT = dilate(imageT, dilateBackgroundPixel, iteratie);
            imageViewT.setImage(SwingFXUtils.toFXImage(imageT, null));
            imageViewT.setFitHeight(400);
            imageViewT.setPreserveRatio(true);
            imageViewT.setSmooth(true);
            imageViewT.setCache(true);
            spT.setContent(imageViewT);
      
            Slider sliderRGB = new Slider(1, 10, 1);  
            sliderRGB.setShowTickLabels(true);
            sliderRGB.setShowTickMarks(true);
            sliderRGB.setMajorTickUnit(1);
            sliderRGB.setMinorTickCount(0);
            sliderRGB.setBlockIncrement(1);
            sliderRGB.setSnapToTicks(true);
            sliderRGB.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                int d = new_val.intValue();
                BufferedImage imageModif = dilate(this.finalImageT, dilateBackgroundPixel, d);                  
                imageViewT.setImage(SwingFXUtils.toFXImage(imageModif, null));
                imageViewT.setFitHeight(400);
                imageViewT.setPreserveRatio(true);
                imageViewT.setSmooth(true);
                imageViewT.setCache(true);  
                });
            
            VBox vbRGB = new VBox();
            vbRGB.getChildren().addAll(imageViewT, sliderRGB, checkBox);
            spT.setContent(vbRGB);

            Stage secondStage = new Stage();

            Scene sceneRGB = new Scene(new VBox(), 400, 350);
            ((VBox)sceneRGB.getRoot()).getChildren().addAll(spT);

            secondStage.setTitle(name.getText());
            secondStage.setScene(sceneRGB);
            secondStage.show();
            
        });
        
        
        menuItem_filtru_Detectie.setOnAction((ActionEvent event) -> { 
            ScrollPane spH = new ScrollPane();  
            BufferedImage imageH = bufferedImag;
            
            Image imgH = SwingFXUtils.toFXImage(imageH, null); 
            ImageView imageViewH = new ImageView(); 
            imageViewH.setFitHeight(400); 
            imageViewH.setPreserveRatio(true); 
            imageViewH.setImage(imgH); 
            imageViewH.setSmooth(true); 
            imageViewH.setCache(true); 
            spH.setContent(imageViewH);
            
            ComboBox comboBox = new ComboBox();
            comboBox.getItems().add("FILTER_VERTICAL");
            comboBox.getItems().add("FILTER_HORIZONTAL");
            comboBox.getItems().add("FILTER_SOBEL_V");
            comboBox.getItems().add("FILTER_SOBEL_H");
            comboBox.getItems().add("FILTER_SCHARR_V");
            comboBox.getItems().add("FILTER_SCHARR_H");
            comboBox.getItems().add("FILTER_H1");
            comboBox.getItems().add("FILTER_H2");
            comboBox.getItems().add("FILTER_H3");
            
            comboBox.setOnAction((event1) -> {
            int selectedIndex = comboBox.getSelectionModel().getSelectedIndex();
           
            BufferedImage imageModif = this.EdgeDetect(bufferedImag, selectedIndex);                  
                imageViewH.setImage(SwingFXUtils.toFXImage(imageModif, null));
                imageViewH.setFitHeight(400);
                imageViewH.setPreserveRatio(true);
                imageViewH.setSmooth(true);
                imageViewH.setCache(true);        
            });
            
            spH.setContent(imageViewH); 

            VBox vbH = new VBox(); 
            vbH.getChildren().addAll(imageViewH, comboBox); 
            spH.setContent(vbH); 
            
            Stage secondStage = new Stage(); 
            Scene sceneH = new Scene(new HBox(), 400, 350); 
            ((HBox)sceneH.getRoot()).getChildren().addAll(spH); 

            secondStage.setTitle(name.getText()); 
            secondStage.setScene(sceneH); 
            secondStage.show();        

        });
        
        
        menuItem_etichetare.setOnAction((ActionEvent event) -> { 
            ScrollPane spH = new ScrollPane(); 
            BufferedImage imageH = this.etichetare(bufferedImag);

            Image imgH = SwingFXUtils.toFXImage(imageH, null); 
            ImageView imageViewH = new ImageView(); 
            imageViewH.setFitHeight(400); 
            imageViewH.setPreserveRatio(true); 
            imageViewH.setImage(imgH); 
            imageViewH.setSmooth(true); 
            imageViewH.setCache(true); 
            spH.setContent(imageViewH); 

            VBox vbH = new VBox(); 
            vbH.getChildren().addAll(imageViewH); 
            spH.setContent(vbH); 

            Stage secondStage = new Stage(); 
            Scene sceneH = new Scene(new HBox(), 400, 350); 
            ((HBox)sceneH.getRoot()).getChildren().addAll(spH); 

            secondStage.setTitle(name.getText()); 
            secondStage.setScene(sceneH); 
            secondStage.show();        

        });
               
        
        menuItem_Exit.setOnAction((ActionEvent event) -> {
            Platform.exit();
            System.exit(0);            
        });
        
        ((StackPane)scene.getRoot()).getChildren().addAll(vbox);
        
        mainStage.setTitle("Prelucrarea Imaginilor");
        mainStage.setScene(scene);
        mainStage.show();

        mainStage.setMaximized(true);
        
        
//        // Sau varianta nr 2
//        // Get current screen of the stage
//        ObservableList<Screen> screens = Screen.getScreensForRectangle(new Rectangle2D(mainStage.getX(), mainStage.getY(), mainStage.getWidth(), mainStage.getHeight()));
//
//        // Change stage properties
//        Rectangle2D bounds = screens.get(0).getVisualBounds();
//        mainStage.setX(bounds.getMinX());
//        mainStage.setY(bounds.getMinY());
//        mainStage.setWidth(bounds.getWidth());
//        mainStage.setHeight(bounds.getHeight());


    }

    private int adjustColor(int rgb, int dif) {
        int result;
        if (dif>0) {
            if (rgb+dif>255) {
                result = 255; //rgb + dif - 255; 
            } else {
                result = rgb + dif;
            }
        } else {
            if (rgb+dif>0) {
                result = rgb + dif;
            } else {
                result = 0; //255 + rgb + dif;
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        launch(args);
    }

    private BufferedImage getGrayscaleImage(BufferedImage src) {
        BufferedImage gImg = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_BYTE_GRAY); 

        WritableRaster wr = src.getRaster(); 

        WritableRaster gr = gImg.getRaster(); 

        for (int i = 0; i < wr.getWidth(); i++) { 

            for (int j = 0; j < wr.getHeight(); j++) { 

                gr.setSample(i, j, 0, wr.getSample(i, j, 0)); 

            } 

        } 

        gImg.setData(gr); 

        return gImg; 
    }
    
    private BufferedImage getGray(BufferedImage image) { 

        BufferedImage g = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY); 

        ColorConvertOp op = new ColorConvertOp(image.getColorModel().getColorSpace(), ColorSpace.getInstance(ColorSpace.CS_GRAY), null); 

        op.filter(image, g); 

        return g; 

    } 

    private BufferedImage equalize(BufferedImage src) {
        BufferedImage nImg = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_BYTE_GRAY); 

        WritableRaster er = nImg.getRaster(); 

        int[] histogram = new int[256]; 

        for (int i = 0; i < 256; i++) { 

            histogram[i] = 0; 

        } 

        int width = src.getWidth(); 

        int height = src.getHeight(); 

        for (int i = 0; i < width; i++) { 

            for (int j = 0; j < height; j++) { 

                try { 

                    int pixel = src.getRGB(j, j); 

                    Color c = new Color(pixel, true); 

                    int nivel = c.getRed(); 

                    histogram[nivel]++; 

                } catch (Exception ex) { 

                } 

            } 

        } 

        double[] hc = new double[256]; 

        hc[0] = histogram[0]; 

        for (int i = 1; i < 256; i++) { 

            hc[i] = hc[i - 1] + histogram[i]; 

        } 

        for (int i = 0; i < width; i++) { 

            for (int j = 0; j < height; j++) { 

                int pixel = src.getRGB(i, j); 

                Color c = new Color(pixel, true); 

                int nivel = c.getRed(); 

                int nivel_nou = (int) ((hc[nivel] - hc[0]) * 255 / (width * height - hc[0])); 

                er.setSample(i, j, 0, nivel_nou); 

            } 

        } 

        nImg.setData(er); 

        return nImg; 
    }

    private ChartPanel createChartPanel(BufferedImage img) {
        // dataset 
        int BINS=256;
        XYBarRenderer renderer; 

        HistogramDataset dataset = new HistogramDataset(); 

        Raster raster = img.getRaster(); 

        final int w = img.getWidth(); 

        final int h = img.getHeight(); 

        double[] r = new double[w * h]; 

        r = raster.getSamples(0, 0, w, h, 0, r); 

        dataset.addSeries("Red", r, BINS); 

        try { 

            r = raster.getSamples(0, 0, w, h, 1, r); 

            dataset.addSeries("Green", r, BINS); 

        } catch (Exception ex) { 

        } 

        try { 

            r = raster.getSamples(0, 0, w, h, 2, r); 

            dataset.addSeries("Blue", r, BINS); 

        } catch (Exception ex) { 

        } 

        // chart 

        JFreeChart chart = ChartFactory.createHistogram("Histogram", "Value", "Count", dataset, PlotOrientation.VERTICAL, true, true, false); 

        XYPlot plot = (XYPlot) chart.getPlot(); 

        renderer = (XYBarRenderer) plot.getRenderer(); 

        renderer.setBarPainter(new StandardXYBarPainter()); 

        // translucent red, green & blue 

        Paint[] paintArray = { 

            new Color(0x80ff0000, true), 

            new Color(0x8000ff00, true), 

            new Color(0x800000ff, true) 

        }; 

        plot.setDrawingSupplier(new DefaultDrawingSupplier( 

            paintArray, 

            DefaultDrawingSupplier.DEFAULT_FILL_PAINT_SEQUENCE, 

            DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE, 

            DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE, 

            DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE, 

            DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE )); 

        ChartPanel panel = new ChartPanel(chart); 

        panel.setMouseWheelEnabled(true); 

        return panel; 
    }

    private double limite(double l) {
        if (l < 0)
            l = 0;
        if (l > 255)
            l = 255;
        return l;
    }
    
    private BufferedImage mediere5(BufferedImage src) {
        
        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB); 

        int i,j; 
        int k,l; 
        int w,h; 

        double[][] v = new double[5][5]; 

        //coeficientii mastii de filtrare  

        v[0][0]=1.0/9.0; v[0][1]=1.0/9.0; v[0][2]=1.0/9.0; v[0][3]=1.0/9.0; v[0][4]=1.0/9.0;

        v[1][0]=1.0/9.0; v[1][1]=1.0/9.0; v[1][2]=1.0/9.0; v[1][3]=1.0/9.0; v[1][4]=1.0/9.0;  

        v[2][0]=1.0/9.0; v[2][1]=1.0/9.0; v[2][2]=1.0/9.0; v[2][3]=1.0/9.0; v[2][4]=1.0/9.0;
        
        v[3][0]=1.0/9.0; v[3][1]=1.0/9.0; v[3][2]=1.0/9.0; v[3][3]=1.0/9.0; v[3][4]=1.0/9.0;

        v[4][0]=1.0/9.0; v[4][1]=1.0/9.0; v[4][2]=1.0/9.0; v[4][3]=1.0/9.0; v[4][4]=1.0/9.0;
        
        
        w=src.getWidth(); 

        h=src.getHeight(); 


        for(i=2;i<w-2;i++){ 

            for(j=2;j<h-2;j++){ 

                //suma ponderata  

                double sumr=0,sumg=0,sumb=0; 

                for(k=-2;k<3;k++){ 

                    for(l=-2;l<3;l++){ 

                        int pixel = src.getRGB(i + k, j + l); 

                        Color c = new Color(pixel, true); 

                        sumr += v[k + 2][l + 2] * c.getRed(); 
                        
                        sumg += v[k + 2][l + 2] * c.getGreen(); 

                        sumb += v[k + 2][l + 2] * c.getBlue(); 

                        sumr = limite(sumr);
                        
                        sumg = limite(sumg);
                        
                        sumb = limite(sumb);
                        
                        Color nc = new Color((int) sumr, (int) sumg, (int) sumb);
                        dst.setRGB(i, j, nc.getRGB()); 

                    } 
                } 
            } 
        } 
        return dst;         
    }

    private BufferedImage median(BufferedImage src) {
         BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB); 

        int i,j; 

        int w,h; 

        int k,aux; 

        int m,n; 

        int med; 

        int[] sir=new int[9]; 

        w=src.getWidth(); 

        h=src.getHeight(); 

        for(i=1;i<w-1;i++) { 

            for(j=1;j<h-1;j++){ 

                //formarea unui sir din elementele vecinatatii 3x3 

                k=0; 

                for(m=-1;m<2;m++) { 

                    for(n=-1;n<2;n++){ 

                        int pixel = src.getRGB(i+m,j+n); 

                        Color c = new Color(pixel, true); 

                        sir[k]=c.getRed(); 

                        k++; 

                    } 

                } 

                //ordonarea crescatoare a valorilor pixelilor 

                //metoda BUBBLESORT 

                k=0; 

                while(k==0){ 

                    k=1; 

                    for(m=0;m<8;m++){ 

                        if(sir[m]>sir[m+1]){ 

                            aux=sir[m]; 

                            sir[m]=sir[m+1]; 

                            sir[m+1]=aux; 

                            k=0; 

                        } 

                    } 

                } 

                //elementul median  

                med=sir[4]; 

                Color nc = new Color(med,med,med); 

                dst.setRGB(i, j, nc.getRGB());             

            } 

        } 

        return dst; 
    }

    private BufferedImage medianMinMax(BufferedImage src, int a) {
        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB); 

        int i,j; 

        int w,h; 

        int k,aux; 

        int m,n; 

        int med; 

        int[] sir=new int[9]; 

        w=src.getWidth(); 

        h=src.getHeight(); 

        for(i=1;i<w-1;i++) { 

            for(j=1;j<h-1;j++){ 

                //formarea unui sir din elementele vecinatatii 3x3 

                k=0; 

                for(m=-1;m<2;m++) { 

                    for(n=-1;n<2;n++){ 

                        int pixel = src.getRGB(i+m,j+n); 

                        Color c = new Color(pixel, true); 

                        sir[k]=c.getRed(); 

                        k++; 

                    } 

                } 

                //ordonarea crescatoare a valorilor pixelilor 

                //metoda BUBBLESORT 

                k=0; 

                while(k==0){ 

                    k=1; 

                    for(m=0;m<8;m++){ 

                        if(sir[m]>sir[m+1]){ 

                            aux=sir[m]; 

                            sir[m]=sir[m+1]; 

                            sir[m+1]=aux; 

                            k=0; 

                        } 

                    } 

                } 

                //elementul median  

                med=sir[a]; 

                Color nc = new Color(med,med,med); 

                dst.setRGB(i, j, nc.getRGB());             

            } 

        } 

        return dst;
    }

    private BufferedImage mediere3(BufferedImage src) {
        
        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB); 

        int i,j; 

        int k,l; 

        int w,h; 

        double[][] v = new double[3][3]; 

        //coeficientii mastii de filtrare  

        v[0][0]=1.0/9.0; v[0][1]=1.0/9.0; v[0][2]=1.0/9.0;  

        v[1][0]=1.0/9.0; v[1][1]=1.0/9.0; v[1][2]=1.0/9.0; 

        v[2][0]=1.0/9.0; v[2][1]=1.0/9.0; v[2][2]=1.0/9.0; 

         
        w=src.getWidth(); 

        h=src.getHeight();    

        
        for(i=1;i<w-1;i++){ 

            for(j=1;j<h-1;j++){ 

                //suma ponderata  

                double sumr=0,sumg=0,sumb=0; 

                for(k=-1;k<2;k++){ 

                    for(l=-1;l<2;l++){ 

                        int pixel = src.getRGB(i + k, j + l); 

                        Color c = new Color(pixel, true); 

                        sumr += v[k + 1][l + 1] * c.getRed(); 

                        sumg += v[k + 1][l + 1] * c.getGreen(); 

                        sumb += v[k + 1][l + 1] * c.getBlue(); 

                        Color nc = new Color((int) sumr, (int) sumg, (int) sumb); 

                        dst.setRGB(i, j, nc.getRGB()); 

                    } 

                } 

            } 

        } 
        return dst;         
    } 

    private BufferedImage accentuare(BufferedImage src) {
        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB); 

        int i,j; 

        int k,l; 

        int w,h; 

        double sumr,sumg,sumb; 

        double[][] v=new double[3][3]; 

        //coeficientii mastii  

        v[0][0]=0; v[0][1]=-1./4; v[0][2]=0; 

        v[1][0]=-1./4; v[1][1]=1; v[1][2]=-1./4; 

        v[2][0]=0; v[2][1]=-1./4; v[2][2]=0; 

        w=src.getWidth(); 

        h=src.getHeight(); 

        for(i=1;i<w-1;i++){ 

            for(j=1;j<h-1;j++){ 

                sumr=0; 

                sumg=0; 

                sumb=0; 

                for(k=-1;k<2;k++) { 

                    for(l=-1;l<2;l++) { 

                        int pixel = src.getRGB(i + k, j + l); 

                        Color c = new Color(pixel, true); 

                        sumr+=1.*v[k+1][l+1]*c.getRed(); 

                        sumg+=1.*v[k+1][l+1]*c.getGreen(); 

                        sumb+=1.*v[k+1][l+1]*c.getBlue(); 

                        int nivr=c.getRed(); 

                        //nivr=(int)(nivr+0.6*sumr); 

                        int nivg=c.getGreen(); 

                        //nivg=(int)(nivg+0.6*sumg); 

                        int nivb=c.getBlue(); 

                        //nivb=(int)(nivb+0.6*sumb); 

                        Color nc = new Color((int) this.adjustColor(nivr, (int) (0.6*sumr)),  

                            (int) this.adjustColor(nivg, (int) (0.6*sumg)),  

                            (int) this.adjustColor(nivb, (int) (0.6*sumb))); 

                        dst.setRGB(i, j, nc.getRGB());             

                    } 

                } 

            } 

        } 

        return dst; 
    }

    private BufferedImage filterLaplacian(BufferedImage src) {
        BufferedImage grayScale = this.getGrayscaleImage(src); 

        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB); 

        int width = src.getWidth(); 

        int height = src.getHeight(); 

        double[][] v = new double[3][3]; 

        //coeficientii mastii  

        v[0][0] = -1; 

        v[0][1] = -1; 

        v[0][2] = -1; 

        v[1][0] = -1; 

        v[1][1] = 8; 

        v[1][2] = -1; 

        v[2][0] = -1; 

        v[2][1] = -1; 

        v[2][2] = -1; 

        //3*3 Laplacian filter (-1,-1,-1), (-1,8,-1), (-1,-1,-1) 

        for (int y = 1; y < height - 1; y++) { 

            for (int x = 1; x < width - 1; x++) { 

                int sum = 0; 

                for (int m = -1; m < 2; m++) { 

                    for (int n = -1; n < 2; n++) { 

                        int pixel = src.getRGB(x + m, y + n); 

                        sum += v[m+1][n+1] * (pixel & 0xff); 

                    } 

                } 

                int a = ((grayScale.getRGB(x, y) >> 24) & 0xff); 

                dst.setRGB(x, y, ((a << 24) | (sum << 16) | (sum << 8) | (sum))); 

            } 

        } 

        return dst; 

    } 
    
    public BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(bi.getRaster().createCompatibleWritableRaster());
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    private BufferedImage dilate(BufferedImage src, boolean dilateBackgroundPixel, int iteratie) {
        BufferedImage tmp = this.deepCopy(src);
        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        /** 

         * Dimension of the image. 

         */ 
        int width = src.getWidth(); 
        int height = src.getHeight();
        
        /** 

         * This will hold the dilation result which will be copied to image. 

         */
        int output[] = new int[width * height]; 

        /** 

         * If dilation is to be performed on BLACK pixels then 

         * targetValue = 0 

         * else 

         * targetValue = 255;  //for WHITE pixels 

         */ 

        int targetValue = (dilateBackgroundPixel == true)?0:255; 

        /** 

         * If the target pixel value is WHITE (255) then the reverse pixel value will 

         * be BLACK (0) and vice-versa. 

         */ 

        int reverseValue = (targetValue == 255)?0:255; 


        //perform dilation 
        
        for (int i = 0; i < iteratie; i++) {
            
        for(int y = 0; y < height; y++){ 

            for(int x = 0; x < width; x++){ 
                      
                //For BLACK pixel RGB all are set to 0 and for WHITE pixel all are set to 255. 

                  if( (new Color(tmp.getRGB(x, y))).getRed() == targetValue){ 

                    /** 

                     * We are using a 3x3 kernel 

                     * [1, 1, 1 

                     *  1, 1, 1 

                     *  1, 1, 1] 

                     */ 

                    boolean flag = false;   //this will be set if a pixel of reverse value is found in the mask 

                    for(int ty = y - 1; ty <= y + 1 && flag == false; ty++){ 

                        for(int tx = x - 1; tx <= x + 1 && flag == false; tx++){ 

                            if(ty >= 0 && ty < height && tx >= 0 && tx < width){ 

                                //origin of the mask is on the image pixels 

                                if((new Color(tmp.getRGB(tx, ty))).getRed() != targetValue){ 

                                    flag = true; 

                                    output[x+y*width] = reverseValue; 

                                } 

                            } 

                        } 

                    } 

                    if(flag == false){ 

                    //all pixels inside the mask [i.e., kernel] were of targetValue 

                    output[x+y*width] = targetValue; 

                    } 

                }else{ 

                    output[x+y*width] = reverseValue; 

                    } 
       
                } 
            }
        
        // update src with the previous dilation result
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tmp.setRGB(x, y, new Color(output[x + y * width], output[x + y * width], output[x + y * width]).getRGB());
            }
        }
    }      

        /** 

         * Save the dilation value in image. 

         */ 
         
        dst.getRaster().setSamples(0, 0, width, height, 0, output); 
          
        return dst; 
    }

    private BufferedImage EdgeDetect(BufferedImage src, int filter_type) {
        String HORIZONTAL_FILTER = "Horizontal Filter"; 

        String VERTICAL_FILTER = "Vertical Filter"; 

        String SOBEL_FILTER_VERTICAL = "Sobel Vertical Filter"; 

        String SOBEL_FILTER_HORIZONTAL = "Sobel Horizontal Filter"; 

        String SCHARR_FILTER_VETICAL = "Scharr Vertical Filter"; 

        String SCHARR_FILTER_HORIZONTAL = "Scharr Horizontal Filter"; 
        
        String Filtru_trece_sus_H1 = "Filtru_trece_sus_H1"; 
        
        String Filtru_trece_sus_H2 = "Filtru_trece_sus_H2"; 
        
        String Filtru_trece_sus_H3 = "Filtru_trece_sus_H3"; 

        double[][] FILTER_VERTICAL = {{1, 0, -1}, {1, 0, -1}, {1, 0, -1}}; 

        double[][] FILTER_HORIZONTAL = {{1, 1, 1}, {0, 0, 0}, {-1, -1, -1}}; 

        double[][] FILTER_SOBEL_V = {{1, 0, -1}, {2, 0, -2}, {1, 0, -1}}; 

        double[][] FILTER_SOBEL_H = {{1, 2, 1}, {0, 0, 0}, {-1, -2, -1}}; 

        double[][] FILTER_SCHARR_V = {{3, 0, -3}, {10, 0, -10}, {3, 0, -3}}; 

        double[][] FILTER_SCHARR_H = {{3, 10, 3}, {0, 0, 0}, {-3, -10, -3}}; 
        
        double[][] Filter_trece_sus_H1 = {{0, -1, 0}, {-1, 5, -1}, {0, -1, 0}}; 

        double[][] Filter_trece_sus_H2 = {{-1, -1, -1}, {-1, 9, -1}, {-1, -1, -1}}; 
        
        double[][] Filter_trece_sus_H3 = {{1, -2, 1}, {-2, 5, -2}, {1, -2, 1}}; 

        HashMap<String, double[][]> filterMap; 
  

        int width = src.getWidth(); 

        int height = src.getHeight(); 

        double[][][] image = new double[3][height][width]; 

        for (int i = 0; i < height; i++) { 

            for (int j = 0; j < width; j++) { 

                Color color = new Color(src.getRGB(j, i)); 

                image[0][i][j] = color.getRed(); 

                image[1][i][j] = color.getGreen(); 

                image[2][i][j] = color.getBlue(); 

            } 

        } 

        double[][] filter = null; 

        switch (filter_type) { 

            case 0: 
                filter = FILTER_VERTICAL;
                break;

            case 1: 
                filter = FILTER_HORIZONTAL;
                break;

            case 2: 
                filter = FILTER_SOBEL_V;
                break;

            case 3: 
                filter = FILTER_SOBEL_H;
                break;

            case 4: 
                filter = FILTER_SCHARR_V;
                break;

            case 5: 
                filter = FILTER_SCHARR_H;
                break;
                
            case 6: 
                filter = Filter_trece_sus_H1;
                break;
                
            case 7: 
                filter = Filter_trece_sus_H2;
                break;
                
            case 8: 
                filter = Filter_trece_sus_H3;
                break;    
                
        } 
        
        BufferedImage writeBackImage = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB); 
        int numIterations = 5;
        for (int k = 0; k < numIterations; k++) {
            
        double[][] redConv = convolutionType2(image[0], height, width, filter, 3, 3, 1); 
        double[][] greenConv = convolutionType2(image[1], height, width, filter, 3, 3, 1); 
        double[][] blueConv = convolutionType2(image[2], height, width, filter, 3, 3, 1); 

        double[][] convolvedPixels = new double[redConv.length][redConv[0].length]; 
        
        
        for (int i = 0; i < redConv.length; i++) { 
            for (int j = 0; j < redConv[i].length; j++) { 
                convolvedPixels[i][j] = redConv[i][j] + greenConv[i][j] + blueConv[i][j]; 
            } 
        } 

        for (int i = 0; i < convolvedPixels.length; i++) { 
            for (int j = 0; j < convolvedPixels[i].length; j++) { 
                Color color = new Color(fixOutOfRangeRGBValues(convolvedPixels[i][j]), fixOutOfRangeRGBValues(convolvedPixels[i][j]), fixOutOfRangeRGBValues(convolvedPixels[i][j])); 
                writeBackImage.setRGB(j, i, color.getRGB()); 
            } 

        } 
        
        }
        return writeBackImage; 
    }
    
    
    public double[][] convolutionType2(double[][] input, int width, int height, double[][] kernel, int kernelWidth, int kernelHeight, int iterations) { 
        double[][] newInput = (double[][]) input.clone(); 
        double[][] output = (double[][]) input.clone(); 

        for (int i = 0; i < iterations; ++i) { 
            output = convolution2DPadded(newInput, width, height, kernel, kernelWidth, kernelHeight); 
            newInput = (double[][]) output.clone(); 
        } 
        return output; 
    }
    
    
    public double[][] convolution2DPadded(double[][] input, int width, int height, double[][] kernel, int kernelWidth, int kernelHeight) { 
        int smallWidth = width - kernelWidth + 1; 
        int smallHeight = height - kernelHeight + 1; 
        int top = kernelHeight / 2; 
        int left = kernelWidth / 2; 
        double small[][] = convolution2D(input, width, height, kernel, kernelWidth, kernelHeight); 
        double large[][] = new double[width][height]; 
        for (int j = 0; j < height; ++j) { 
            for (int i = 0; i < width; ++i) { 
                large[i][j] = 0; 
            } 
        } 
        for (int j = 0; j < smallHeight; ++j) { 
            for (int i = 0; i < smallWidth; ++i) { 
                large[i + left][j + top] = small[i][j]; 
            } 
        } 
        return large; 
    } 
   
    
    public double[][] convolution2D(double[][] input, int width, int height, double[][] kernel, int kernelWidth, int kernelHeight) { 
        int smallWidth = width - kernelWidth + 1; 
        int smallHeight = height - kernelHeight + 1; 
        double[][] output = new double[smallWidth][smallHeight]; 
        for (int i = 0; i < smallWidth; ++i) { 
            for (int j = 0; j < smallHeight; ++j) { 
                output[i][j] = 0; 
            } 
        } 
        for (int i = 0; i < smallWidth; ++i) { 
            for (int j = 0; j < smallHeight; ++j) { 
                output[i][j] = singlePixelConvolution(input, i, j, kernel, kernelWidth, kernelHeight); 
            } 
        } 
        return output; 
    } 


    public double singlePixelConvolution(double[][] input, int x, int y, double[][] k, int kernelWidth, int kernelHeight) { 
        double output = 0; 
        for (int i = 0; i < kernelWidth; ++i) { 
            for (int j = 0; j < kernelHeight; ++j) { 
                output = output + (input[x + i][y + j] * k[i][j]); 
            } 
        }
        return output; 
    } 
    
    
    private int fixOutOfRangeRGBValues(double value) { 
        if (value < 0.0) {
            value = -value; 
        }      
        if (value > 255) { 
            return 255; 
        } else { 
            return (int) value; 
        } 
    } 
    
    
    private BufferedImage etichetare(BufferedImage src) {
        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB); 
        
        src = this.thresholdWhite(this.getGray(src), 50); 
        
        int label = 0; 
        int[][] labels = new int[src.getHeight()][src.getWidth()]; 
        for (int i = 0; i < src.getHeight(); ++i) { 
            for (int j = 0; j < src.getWidth(); ++j) { 
                labels[i][j] = 0; 
            }    
        } 
        for (int i = 0; i < src.getHeight(); ++i) { 
            for (int j = 0; j < src.getWidth(); ++j) {               
                int pixel = src.getRGB(j, i); 
                Color c = new Color(pixel, true);                
                int col = c.getRed(); 
                if (col ==0 && labels[i][j]==0) { 
                    label++; 
                    Queue<Object> queue = new LinkedList<>(); 
                    labels[i][j] = label; 
                    queue.add(new int[]{i,j}); 
                    while (!queue.isEmpty()){ 
                        int[] q = (int[]) queue.remove(); 
                        int q0 = q[0], q1 = q[1]; 
                        for (int k=-1;k<=1;k++){ 
                            for (int m=-1;m<=1;m++) { 
                                if ((q0+k>=0 && q0+k<=src.getHeight()) && 
                                     (q1+m>=0 && q1+m<=src.getWidth())) { 
                                    try { 
                                        int npixel = src.getRGB(q1+m, q0+k);                                       
                                        Color nc = new Color(npixel, true);
                                              
                                        if (nc.getRed()==0 && labels[q0+k][q1+m]==0){ 
                                            labels[q0+k][q1+m] = label; 
                                            queue.add(new int[]{q0+k,q1+m}); 
                                        } 
                                    } catch(Exception ex){} 
                                } 
                            } 
                        } 
                    } 
                } 
            } 
        } 
        //alege culorile care sa apara
        Color[] colors = {Color.WHITE,Color.MAGENTA, Color.BLUE, Color.GREEN, Color.CYAN, Color.BLACK, Color.RED, Color.YELLOW, Color.ORANGE, Color.PINK, Color.GRAY,Color.MAGENTA};
        
        for (int i = 0; i < src.getHeight(); ++i) { 
            for (int j = 0; j < src.getWidth(); ++j) {  
                dst.setRGB(j, i, colors[labels[i][j] % colors.length].getRGB());               
            }    
        } 
        return dst; 
    }

    
    private BufferedImage thresholdWhite(BufferedImage src, int i) {
        int width = src.getWidth(); 
        int height = src.getHeight();
        for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                   //Retrieving contents of a pixel
                   int pixel = src.getRGB(x,y);  
                   //Creating a Color object from pixel value
                   Color color = new Color(pixel, true);
                   //Retrieving the R G B values
                   int red = color.getRed();
                   if(red<i) red = 0; else if(red>i) red = 255;
                   int green = color.getGreen();
                   if(green<i) green = 0; else if(green>i) green = 255;
                   int blue = color.getBlue();
                   if(blue<i) blue = 0; else if(blue>i) blue = 255;       
                   Color myT = new Color(red, green, blue);
                   src.setRGB(x, y, myT.getRGB());                  
                }
            }
        return src;
    }
    

    public String LZW_compress(BufferedImage src) throws IOException { 

        HashMap<String, Integer> dictionary = new HashMap<>(); 

        int dictSize = 256; 

        String P,fileName,BP; 

        String extension="bmp"; 

        byte[] buffer = new byte[3]; 

        boolean isLeft = true; 

        int i,byteToInt; 

        char C; 

        // Character dictionary  

        for(i=0;i<256;i++) { 

            dictionary.put(Character.toString((char)i),i); 

        }
        
        // Get file name 

        fileName = this.name.getText(); 

        // Read input file and output file 

        String[] getFileNameWOExtn = fileName.split("\\."); 

        RandomAccessFile outputFile = new RandomAccessFile(getFileNameWOExtn[0].concat(".lzw"),"rw"); 

        ByteArrayOutputStream baos = new ByteArrayOutputStream(); 

        ImageIO.write(src, extension, baos); 

        byte[] bytes = baos.toByteArray(); 

        try { 

            InputStream in = new ByteArrayInputStream(bytes); 

            // Read first byte to initialize P 

            byteToInt = in.read(); 

            if(byteToInt < 0) byteToInt += 256; 

            C = (char) byteToInt; 

            P = ""+C; 
            
            while(true) { 

                byteToInt = in.read(); 

                if (byteToInt==-1) { 

                    // End of file has been reached  

                    // We still have something in P 

                    BP = convertTo12Bit(dictionary.get(P)); 

                    if(isLeft) { 

                        buffer[0] = (byte) Integer.parseInt(BP.substring(0,8),2);   

                        buffer[1] = (byte) Integer.parseInt(BP.substring(8,12)+"0000",2); 

                        outputFile.writeByte(buffer[0]);   

                        outputFile.writeByte(buffer[1]);                 

                    } 

                    else { 

                        buffer[1] += (byte) Integer.parseInt(BP.substring(0,4),2);  

                        buffer[2] = (byte) Integer.parseInt(BP.substring(4,12),2); 

                        for(i=0;i<buffer.length;i++) { 

                             outputFile.writeByte(buffer[i]); 

                             buffer[i]=0; 

                        } 

                    } 

                    break; 

                } 
                if(byteToInt < 0) byteToInt += 256; 
                C = (char) byteToInt; 
  
                // if P+C is present in dictionary 
                if(dictionary.containsKey(P+C)) { 

                    P = P+C; 
                } 
                 
                /*  

                    if P+C is not in dictionary, we obtain the longest string in the dictionary  

                    so far which is stored in P. The value of this string is converted in binary.  

                    This binary number is then padded to make it 12 bits long (for standardization 

                    and avoing overflow or underflow caused using 8 bits). This is then converted  

                    into bytes and stored. 

                    

                    We write in the file every 2 characters. 

                */ 

                else { 
                    BP = convertTo12Bit(dictionary.get(P)); 
                    if(isLeft) { 

                        buffer[0] = (byte) Integer.parseInt(BP.substring(0,8),2);   

                        buffer[1] = (byte) Integer.parseInt(BP.substring(8,12)+"0000",2);                    
                    } 

                    else { 
                        buffer[1] += (byte) Integer.parseInt(BP.substring(0,4),2);  

                        buffer[2] = (byte) Integer.parseInt(BP.substring(4,12),2); 

                        for(i=0;i<buffer.length;i++) { 

                            outputFile.writeByte(buffer[i]); 

                            buffer[i]=0; 
                        } 
                    } 

                    isLeft = !isLeft; 
                    if(dictSize < 4096) dictionary.put(P+C,dictSize++); 
                    P=""+C; 
                }             
            } 
        } 

        /*   

            If isLeft is true, we store the current data in BP to buffer[0] and buffer[1]. Then these  

            buffers are written in the output file. 

            If isLeft is false, we already have data in the first and half of seccond byte of the  

            buffer. Hence, we store the current value of BP and write all the 3 bytes to the outputFile.  

             

            When the file input is complete, the while loop will still execute due to the condition. 

            This ensures that the file is read completely but it might throw an error if there is  

            no input left in the inputFile. So, when an error is thrown, we store the remaining contents 

            of the buffer. 

        */ 
        
        catch(IOException ie) { 
            ie.printStackTrace(); 
        } 
        finally{ 
            outputFile.close();         
        } 
        return getFileNameWOExtn[0].concat(".lzw"); 
    } 
     

    public String convertTo12Bit(int i) { 
        String to12Bit = Integer.toBinaryString(i); 
        while (to12Bit.length() < 12) to12Bit = "0" + to12Bit; 
        return to12Bit; 

    } 

    
    public BufferedImage LZW_decompress(String fileName) throws IOException {
        String[] arrayOfChar;
        String extension = "bmp";
        int dictSize = 256, currentword, previousword;
        byte[] buffer = new byte[3];
        boolean isLeft = true;
        arrayOfChar = new String[4096];
        int i;
        for (i = 0; i < 256; i++) {
            arrayOfChar[i] = Character.toString((char) i);
        }

        // Read input file and output file 
        RandomAccessFile inputFile = new RandomAccessFile(fileName, "r");
        RandomAccessFile outputFile = new RandomAccessFile("output_image.".concat(extension), "rw");
        try {

            buffer[0] = inputFile.readByte();
            buffer[1] = inputFile.readByte();
            previousword = getIntValue(buffer[0], buffer[1], isLeft);
            isLeft = !isLeft;
            outputFile.writeBytes(arrayOfChar[previousword]);

            // Reads three bytes and generates corresponding characters 
            while (true) {
                if (isLeft) {
                    buffer[0] = inputFile.readByte();
                    buffer[1] = inputFile.readByte();
                    currentword = getIntValue(buffer[0], buffer[1], isLeft);
                } else {
                    buffer[2] = inputFile.readByte();
                    currentword = getIntValue(buffer[1], buffer[2], isLeft);
                }
                isLeft = !isLeft;
                /* 
                 currentword not in dictionary, we just add the previousword in the entry. 
                 */
                if (currentword >= dictSize) {
                    if (dictSize < 4096) {
                        arrayOfChar[dictSize] = arrayOfChar[previousword] + arrayOfChar[previousword].charAt(0);
                    }
                    dictSize++;
                    outputFile.writeBytes(arrayOfChar[previousword] + arrayOfChar[previousword].charAt(0));
                } /* 
                 If word is present, we form a word with the previousword and the first character of the  
                 currentword and add it in a new entry 
                 */ else {
                    if (dictSize < 4096) {
                        arrayOfChar[dictSize] = arrayOfChar[previousword] + arrayOfChar[currentword].charAt(0);
                    }
                    dictSize++;
                    outputFile.writeBytes(arrayOfChar[currentword]);
                }
                previousword = currentword;
            }
        } catch (EOFException e) {
            inputFile.close();
        }
        
        byte[] bytes = new byte[(int) outputFile.length()];
        outputFile.seek(0);
        outputFile.readFully(bytes);
        outputFile.close();
        InputStream input = new ByteArrayInputStream(bytes);
        BufferedImage decompressedImage = ImageIO.read(input);
        return decompressedImage;
    }
    
    
    /* 

        Converting 2 bytes to 12-bit code. 

        Converting 12-bit code to integer value. 

    */  

    public int getIntValue(byte b1, byte b2, boolean isLeft) { 

        String t1 = Integer.toBinaryString(b1); 
        String t2 = Integer.toBinaryString(b2); 

        while (t1.length() < 8) t1 = "0" + t1; 
       
        if (t1.length() == 32) t1 = t1.substring(24, 32); 
         
        while (t2.length() < 8) t2 = "0" + t2; 

        if (t2.length() == 32) t2 = t2.substring(24, 32); 

        if (isLeft) return Integer.parseInt(t1 + t2.substring(0, 4), 2); 
        else return Integer.parseInt(t1.substring(4, 8) + t2, 2); 
    }

    
    class HuffmanNode { 
        int freq; 
        int pixel; 
        HuffmanNode left; 
        HuffmanNode right;      
        public HuffmanNode() {}      
        public HuffmanNode(int pixel, int freq) { 
            this.pixel = pixel; 
            this.freq = freq; 
        } 
    } 

    
    // For comparing the nodes 
    class HuffmanComparator implements Comparator<HuffmanNode> { 
        @Override 
        public int compare(HuffmanNode x, HuffmanNode y) { 
            return x.freq - y.freq; 
        } 
    } 

    
    // Implementing the huffman algorithm 
    public void buildHuffmanCode(HuffmanNode root, String s, HashMap<Integer, String> ht) { 
        if (root.left == null && root.right == null) { 
            ht.put(root.pixel, s); 
            return; 
        } 
        buildHuffmanCode(root.left, s + "0", ht); 
        buildHuffmanCode(root.right, s + "1", ht); 
    } 


    public void HuffmanCompress(BufferedImage src) { 
        int w = src.getWidth(); 
        int h = src.getHeight(); 

        // pixel, freq 
        Map<Integer, Integer> charOcurrence = new HashMap<>(); 

        // Get image 
        int[] image = src.getRGB(0, 0, w, h, null, 0, w); 

        //Pas 1 
        for (int i = 0; i < image.length; ++i) { 
            int pixel = image[i]; 
            if (charOcurrence.containsKey(pixel)) { 
                charOcurrence.put(pixel, charOcurrence.get(pixel) + 1); 
            } else { 
                charOcurrence.put(pixel, 1); 
            } 
        } 

        //Generam fisierul huff 
        try { 
            String[] f = this.name.getText().split("\\."); 
            FileWriter fileWriter = new FileWriter(f[0] + ".huff"); 
            try (BufferedWriter buff = new BufferedWriter(fileWriter)) { 
                Set<Map.Entry<Integer, Integer>> set = charOcurrence.entrySet(); 
                Iterator<Map.Entry<Integer, Integer>> iterator = set.iterator(); 
                while (iterator.hasNext()) { 
                    @SuppressWarnings("rawtypes") 
                    Map.Entry entry = (Map.Entry) iterator.next(); 
                    buff.write(entry.getKey() + " " + entry.getValue()); 
                    buff.newLine(); 
                } 
            } 
        } catch (Exception ex) {} 

        int n = charOcurrence.size(); 
        
        //Pas2 
        PriorityQueue<HuffmanNode> q = new PriorityQueue<>(n, new HuffmanComparator()); 
        for (int key : charOcurrence.keySet()) { 
            HuffmanNode hn = new HuffmanNode(); 
            hn.pixel = key; 
            hn.freq = charOcurrence.get(key); 
            hn.left = null; 
            hn.right = null; 
            q.add(hn); 
        } 

        //Pas 3,4 
        HuffmanNode root = null; 
        
        while (q.size() > 1) { 
            HuffmanNode x = q.peek(); 
            q.poll(); 
            HuffmanNode y = q.peek(); 
            q.poll(); 
            HuffmanNode f = new HuffmanNode(); 
            f.freq = x.freq + y.freq; 
            f.pixel = -1; 
            f.left = x; 
            f.right = y; 
            root = f; 
            q.add(f); 
        } 

        HashMap<Integer, String> htim = new HashMap<>(); 
        buildHuffmanCode(root, "", htim); 

        // Generam fisierul himg 
        generateHuffmanBitString(src, htim); 
    } 

  
 public void generateHuffmanBitString(BufferedImage img, HashMap<Integer, String> ht) { 

        String bit = ""; 
        String[] f = this.name.getText().split("\\."); 
        String filename = f[0] + ".himg"; 

        File hname = new File(filename); 
        try { 
            DataOutputStream out = new DataOutputStream(new FileOutputStream(hname)); 
           
            int w = img.getWidth(); 
            int h = img.getHeight(); 
            
            out.writeInt(w); 
            out.writeInt(h); 
            
            int[] dataBuffInt = img.getRGB(0, 0, w, h, null, 0, w); 

            for (int i = 0; i < dataBuffInt.length; i++) { 
                if (ht.containsKey(dataBuffInt[i])) { 
                    bit = bit + ht.get(dataBuffInt[i]); 
                    while (bit.length() >= 31) { 
                        out.writeInt(Integer.parseInt(bit.substring(0, 31), 2)); 
                        bit = bit.substring(31, bit.length()); 
                    } 
                } else { 
                    break; 
                } 
            } 

            if (bit.length() > 0) { 
                out.writeInt(Integer.parseInt(bit, 2)); 
                out.writeInt(bit.length()); 
            }

           out.close(); 

        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
    }
 
 
    public BufferedImage HuffmanDecompress() { 
        
        String[] fn = this.name.getText().split("\\."); 
        File huffFile = new File(fn[0] + ".huff"); 
        PriorityQueue<HuffmanNode> pqueue = new PriorityQueue<>(10, new HuffmanComparator()); 
        Scanner read; 

        try {          
            read = new Scanner(huffFile); 
       
            while (read.hasNext()) { 
                int key = read.nextInt(); 
                int freq = read.nextInt();
                HuffmanNode newNode = new HuffmanNode(key, freq); 
                pqueue.offer(newNode); 
            } 

            read.close(); 

            HuffmanNode root = null; 

            while (pqueue.size() > 1) { 
                HuffmanNode x = pqueue.peek(); 
                pqueue.poll(); 
                HuffmanNode y = pqueue.peek(); 
                pqueue.poll(); 
                HuffmanNode f = new HuffmanNode(); 
                f.freq = x.freq + y.freq; 
                f.pixel = -1; 
                f.left = x; 
                f.right = y; 
                root = f; 
                pqueue.add(f); 
            } 

            HashMap<String, Integer> ht = new HashMap<>(); 
            HashMap<Integer, String> htim = new HashMap<>(); 
            buildHuffmanCode(root, "", htim); 

            Set<Map.Entry<Integer, String>> set = htim.entrySet(); 
            Iterator<Map.Entry<Integer, String>> iterator = set.iterator(); 
            
            boolean tracked = false; 
            int minCodeLength = 0; 

            while (iterator.hasNext()) { 
                @SuppressWarnings("rawtypes") 
                Map.Entry entry = (Map.Entry) iterator.next(); 
                String val = (String) entry.getValue(); 

                if (tracked == false) { 
                    minCodeLength = val.length(); 
                    tracked = true; 
                } else { 
                    if (val.length() < minCodeLength) { 
                        minCodeLength = val.length(); 
                    } 
                } 
                ht.put((String) entry.getValue(), (Integer) entry.getKey()); 
            } 
            return loadHimgFile(ht); 

        } catch (FileNotFoundException e) { 
            e.printStackTrace(); 
        } 
        return null; 
    } 
  

    public BufferedImage loadHimgFile(HashMap<String, Integer> huffmanStringPixel) { 

        String[] fn = this.name.getText().split("\\."); 

        File himgFile = new File(fn[0] + ".himg"); 

        String bitstring = ""; 

        int w = 0, h = 0; 

        try { 

            DataInputStream in = new DataInputStream(new FileInputStream(himgFile)); 

            byte[] byteData = new byte[(int) himgFile.length()]; 

            in.readFully(byteData); 

            byte[] arrW = new byte[4]; 
            byte[] arrH = new byte[4]; 
            byte[] bitL = new byte[4]; 

            System.arraycopy(byteData, 0, arrW, 0, 4); 
            System.arraycopy(byteData, 4, arrH, 0, 4); 
            System.arraycopy(byteData, byteData.length - 4, bitL, 0, 4); 

            
            ByteBuffer buf = ByteBuffer.wrap(arrW); 
            w = buf.getInt(); 
            buf = ByteBuffer.wrap(arrH); 
            h = buf.getInt(); 
            buf = ByteBuffer.wrap(bitL); 

            int bitStringLength = buf.getInt(); 

            byte[] real = new byte[byteData.length - 12]; 

            System.arraycopy(byteData, 8, real, 0, byteData.length - 12); 

            BigInteger bigInt = new BigInteger(real); 

            bitstring = bigInt.toString(2); 

            int pad = 0; 
            if (bitstring.length() % (real.length * 8) != 0) { 
                pad = (real.length * 8) - (bitstring.length() % (real.length * 8)); 
            } 

            for (int a = 0; a < pad; a++) { 
                bitstring = '0' + bitstring; 
            } 

            String temp = bitstring.substring(bitstring.length() - (32 + bitStringLength), bitstring.length() - 32); 
            bitstring = bitstring.substring(0, bitstring.length() - 32); 
            bitstring = bitstring + temp; 

            in.close(); 
            
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return render(bitstring, huffmanStringPixel, w, h); 
    } 


    public BufferedImage render(String huffmanCode, HashMap<String, Integer> huffmanStringPixel, int width, int height) { 
        BufferedImage huffmanImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
        int x = 0; 
        int y = 0; 
        String code = ""; 
        for (int a = 0; a < huffmanCode.length(); a++) { 
            if (a % 32 != 0) { 
                code = code + huffmanCode.charAt(a); 
            } 
            if (huffmanStringPixel.containsKey(code)) { 
                huffmanImage.setRGB(x++, y, huffmanStringPixel.get(code)); 
                code = ""; 
                if (x % width == 0) { 
                    x = 0; 
                    y++; 
                } 
                if (y != 0 && y % height == 0) { 
                    break; 
                } 
            } 
        } 
        return huffmanImage; 
    }
    
    
    private BufferedImage HaarCompress(BufferedImage src) {
        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB); 

        int[][] arr = new int[src.getWidth()][src.getHeight()]; 

        for(int i = 0; i < src.getWidth(); i++) 
            for(int j = 0; j < src.getHeight(); j++) 
                arr[i][j] = ((new Color(src.getRGB(i, j))).getRed()+(new Color(src.getRGB(i, j))).getBlue()+(new Color(src.getRGB(i, j))).getGreen())/3; 

        double[][] w = this.doHaar2DFWTransform(arr, 1); 

        for(int x = 0; x < src.getWidth(); x++) { 
            for(int y = 0; y < src.getHeight(); y++) { 
                Color c = new Color(this.fixOutOfRangeRGBValues((int)w[x][y]),this.fixOutOfRangeRGBValues((int)w[x][y]),this.fixOutOfRangeRGBValues((int)w[x][y])); 
                dst.setRGB(x, y, c.getRGB()); 
            } 
        } 
        return dst; 
    } 

    
    private int getHaarMaxCycles(int hw) { 
        int cycles = 0; 
        while (hw > 1) { 
            cycles++; 
            hw /= 2; 
        } 
        return cycles; 
    } 

    
    private boolean isCycleAllowed(int maxCycle, int cycles) { 
        return cycles <= maxCycle; 
    } 

    
    public double[][] doHaar2DFWTransform(int[][] pixels, int cycles) { 

        int w = pixels[0].length; 
        int h = pixels.length; 

        int maxCycle = getHaarMaxCycles(w); 
        boolean isCycleAllowed = isCycleAllowed(maxCycle, cycles); 

        if (isCycleAllowed) { 
            double[][] ds = new double[h][w]; 
            double[][] tempds = new double[h][w]; 
            for (int i = 0; i < h; i++) { 
                for (int j = 0; j < w; j++) { 
                    ds[i][j] = pixels[i][j]; 
                    tempds[i][j] = 0; 
                } 
            } 
            for (int c = 0; c < cycles; c++) { 
                w /= 2; 
                for (int i = 0; i < h; i++) { 
                    for (int j = 0; j < w; j++) { 
                        double a = ds[i][2 * j]; 
                        double b = ds[i][2 * j + 1]; 
                        double add = a + b; 
                        double sub = a - b; 
                        double avgAdd = add / 2; 
                        double avgSub = sub / 2; 
                        tempds[i][j] = avgAdd; 
                        tempds[i][j + w] = avgSub <= 0 ? 0 : avgSub; 
                    } 
                } 

                for (int i = 0; i < h; i++) { 
                    for (int j = 0; j < w; j++) { 
                        ds[i][j] = tempds[i][j]; 
                        ds[i][j + w] = tempds[i][j + w]; 
                    } 
                } 
                
                h /= 2; 
                for (int j = 0; j < w; j++) { 
                    for (int i = 0; i < h; i++) { 
                        double a = ds[2 * i][j]; 
                        double b = ds[2 * i + 1][j]; 
                        double add = a + b; 
                        double sub = a - b; 
                        double avgAdd = add / 2; 
                        double avgSub = sub / 2; 
                        tempds[i][j] = avgAdd; 
                        tempds[i + h][j] = avgSub <= 0 ? 0 : avgSub; 
                    } 
                } 
                
                for (int j = 0; j < w; j++) { 
                    for (int i = 0; i < h; i++) { 
                        ds[i][j] = tempds[i][j]; 
                        ds[i + h][j] = tempds[i + h][j]; 
                    } 
                } 
            } 
            return tempds; 
        } 
        return null; 
    } 
    

    public double[][] doHaar2DInvTransform(double[][] pixels, int cycles) { 

        int w = pixels[0].length; 
        int h = pixels.length; 
        int maxCycle = getHaarMaxCycles(w); 
        boolean isCycleAllowed = isCycleAllowed(maxCycle, cycles); 

        if (isCycleAllowed) { 
            double[][] ds = new double[h][w]; 
            double[][] tempds = new double[h][w]; 
            for (int i = 0; i < h; i++) { 
                for (int j = 0; j < w; j++) { 
                    ds[i][j] = pixels[i][j]; 
                    tempds[i][j] = 0; 
                } 
            } 
            int hh = h / (int) Math.pow(2, cycles); 
            int ww = w / (int) Math.pow(2, cycles); 
            for (int c = cycles; c > 0; c--) { 
                for (int j = 0; j < ww; j++) { 
                    for (int i = 0; i < hh; i++) { 
                        double a = ds[i][j]; 
                        double b = ds[i + hh][j]; 
                        double add = a + b; 
                        double sub = a - b; 
                        tempds[2 * i][j] = add; 
                        tempds[2 * i + 1][j] = sub; 
                    }
                } 

            for (int j = 0; j < ww; j++) { 
                for (int i = 0; i < hh; i++) { 
                    ds[2 * i][j] = tempds[2 * i][j]; 
                    ds[2 * i + 1][j] = tempds[2 * i + 1][j]; 
                } 
            } 

            hh *= 2; 
            for (int j = 0; j < hh; j++) { 
                for (int i = 0; i < ww; i++) { 
                    double a = ds[j][i]; 
                    double b = ds[j][i + ww]; 
                    double add = a + b; 
                    double sub = a - b; 
                    tempds[j][2 * i] = add; 
                    tempds[j][2 * i + 1] = sub; 
                } 
            } 
                
            for (int j = 0; j < hh; j++) { 
                for (int i = 0; i < ww; i++) { 
                    ds[j][2 * i] = tempds[j][2 * i]; 
                    ds[j][2 * i + 1] = tempds[j][2 * i + 1]; 
                }
            } 
            ww *= 2; 
            } 
            return tempds; 
        } 
        return null; 
    }
    
     
    private BufferedImage HaarDecompress(BufferedImage src) {
        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
        double[][] arr = new double[src.getWidth()][src.getHeight()];
        for (int i = 0; i < src.getWidth(); i++) {
            for (int j = 0; j < src.getHeight(); j++) {
                arr[i][j] = (new Color(src.getRGB(i, j))).getRed();
            }
        }
        double[][] pixels = doHaar2DInvTransform(arr, 1);
        for (int x = 0; x < src.getWidth(); x++){
            for (int y = 0; y < src.getHeight(); y++) {
                Color c = new Color(fixOutOfRangeRGBValues((int) pixels[x][y]), fixOutOfRangeRGBValues((int) pixels[x][y]), fixOutOfRangeRGBValues((int) pixels[x][y]));
                dst.setRGB(x, y, c.getRGB());
            }
        }      
        return dst;
    }
 }
