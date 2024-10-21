package control;

import view.ui.PlayerCustomizationPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Settings class manages player settings, including key bindings and player names.
 */
public class Settings {

    private PlayerCustomizationPanel p1;
    private  PlayerCustomizationPanel p2;
    private String player1Name;
    private String player2Name;
    private int[] keyBindings;
    private final String path = "src/main/resources/control.txt";
    boolean dummy=false;

    public Settings() {
        this.p1= new PlayerCustomizationPanel(1,this);
        this.p2=new PlayerCustomizationPanel(2,this);
        try {
            ImageIcon bombermanIcon2 = new ImageIcon(ImageIO.read(new File("src/main/resources/assets/menu/bomberman1.png")));
            Image image = bombermanIcon2.getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(image);
            p2.setPlayerImage(scaledIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        load();
    }

    /**
     * Converts settings from file to key events.
     * @param settings ArrayList containing settings from file
     * @return Array of key events
     */
    private int[] convertToEvent(ArrayList<String> settings) {
        int[] events=new int[12];
        int i=0;
        for (String setting : settings) {

            switch (setting){
                case "DW":
                    setting = "DOWN";
                    break;
                case "RG":
                    setting = "RIGHT";
                    break;
                case "LF":
                    setting = "LEFT";
                    break;
                default:
                    break;
            }
            String code = "VK_" + setting.toUpperCase();
            try {
                Field f = KeyEvent.class.getField(code);
                int keyEvent = f.getInt(null);
                events[i]=keyEvent;
                i++;
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        }
        return events;
    }
    /**
     * Loads settings from file.
     */
    public void load(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String[] line1=br.readLine().split(" ");
            String[] line2=br.readLine().split(" ");
            ArrayList<String> fileSettings=new ArrayList<>();
            for (int i=1;i<7;i++){
                fileSettings.add(line1[i]);
            }
            for (int i=1;i<7;i++){
                fileSettings.add(line2[i]);
            }

            player1Name=line2[0].replace("˘"," ");
            player2Name=line1[0].replace("˘"," ");
            keyBindings=convertToEvent(fileSettings);
            initialize(p1,line1);
            initialize(p2,line2);
            br.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void initialize(PlayerCustomizationPanel p,String[] words){
        p.setPlayerName(words[0]);
        p.setControls(words);
    }
    /**
     * Writes settings to file.
     * @param row Row number in file to write settings
     * @param settings Array containing player settings
     */
    public void write(int row,String[] settings) {
        try {
             dummy=true;
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line1= br.readLine();
            String line2= br.readLine();
            br.close();
            if(row==1){
                line1=settings[0].replace(" ","˘")+
                        " "+
                        settings[1]+
                        " "+
                        settings[2]+
                        " "+
                        settings[3]+
                        " "+
                        settings[4]+
                        " "+
                        settings[5]+
                        " "+
                        settings[6];
            }else{
                line2=settings[0].replace(" ","˘")+
                        " "+
                        settings[1]+
                        " "+
                        settings[2]+
                        " "+
                        settings[3]+
                        " "+
                        settings[4]+
                        " "+
                        settings[5]+
                        " "+
                        settings[6];
            }
            FileWriter writer=new FileWriter(path);
            writer.write(line1+'\n'+line2);
            writer.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }
    public boolean compare(){
        String[] p1Settings= p1.getControls();
        String[] p2Settings= p2.getControls();

        for (String p1Word:p1Settings) {
            int match=0;
            for(String p2Word:p2Settings){
                if(p1Word.equalsIgnoreCase(p2Word)) return true;
            }
            for (String p1Word2:p1Settings){
                if(p1Word.equalsIgnoreCase(p1Word2)){
                    match++;
                }
            }
            if (match>1) return true;
        }
        for (String p2Word:p2Settings){
            int match=0;
            for (String p2Word2:p2Settings){
                if(p2Word.equalsIgnoreCase(p2Word2)){
                    match++;
                }
            }
            if (match>1) return true;
        }
        return false;

    }

    // Getters
    public PlayerCustomizationPanel getP1() {
        return p1;
    }
    public PlayerCustomizationPanel getP2(){
        return p2;
    }
    public String getPlayer1Name() {
        return player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public int[] getKeyBindings() {
        return keyBindings;
    }
}
