/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.socialNetworkAnalysis;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author phillipe
 */
public class NodeSN {

    private String id;
    private String x;
    private String y;
    private String name;
    private String fullName;
    private String nameWithEnter;
    private String yearInNetwork;
    private String aggregator;
    private String level;
    private String image;
    private String localCentrality;
    private String globalCentrality;
    private String group;
    private String university;
    private String universityGroup;
    private String color;
    private String label;
    private String size;
    private NodeSNCoordinates coordinates;
    private boolean withoutConnection;

    public NodeSN() {
        x = "Math.random()";
        y = "Math.random()";
        aggregator = "";
        color = "#0F0";
        fullName = "";
        globalCentrality = "";
        group = "";
        id = "";
        image = "";
        label = "";
        level = "";
        localCentrality = "";
        name = "";
        nameWithEnter = "";
        size = "1";
        university = "";
        universityGroup = "";
        yearInNetwork = "";
        withoutConnection = true;
    }
    
//    public static void main(String argv[]) {
//        System.out.println("Value: " + randomHexadecimalColor(randomColor()));
//        System.out.println("Value: " + randomHexadecimalColor(randomColor()));
//        System.out.println("Value: " + randomHexadecimalColor(randomColor()));
//        System.out.println("Value: " + randomHexadecimalColor(randomColor()));
//        System.out.println("Value: " + randomHexadecimalColor(randomColor()));
//    }
    
    public static Integer randomValueRange(int minValue, int maxValue){
        Random rn = new Random();
        int range = maxValue - minValue + 1;
        int randomNum =  rn.nextInt(range) + minValue;
        return randomNum;
    }    
   
    public static String randomHexadecimalColor(Color color){
        return '#'+handlingHexString(Integer.toHexString(color.getRed()))+
                handlingHexString(Integer.toHexString(color.getGreen()))+
                handlingHexString(Integer.toHexString(color.getBlue()));
    }

    public static String handlingHexString(String hexString) {
        String hex = null;
        if (hexString.length() == 1) {
            hex = '0' + hexString;
        } else {
            hex = hexString;
        }
        return hex;
    }

    public static Color randomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        
        Color c = new Color(r,g,b);
        c.brighter();//add brightness
        return c;
    }

    public static Color randomLightColor() {
        Random rand = new Random();
        Double r = rand.nextFloat() / 2f + 0.5;//light color
        Double g = rand.nextFloat() / 2f + 0.5;//light color
        Double b = rand.nextFloat() / 2f + 0.5;//light color
        
        float red = r.floatValue();
        float green = g.floatValue();
        float blue = b.floatValue();
        Color c = new Color(red,green,blue);
        return c;
    }
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the nameWithEnter
     */
    public String getNameWithEnter() {
        return nameWithEnter;
    }

    /**
     * @param nameWithEnter the nameWithEnter to set
     */
    public void setNameWithEnter(String nameWithEnter) {
        this.nameWithEnter = nameWithEnter;
    }

    /**
     * @return the yearInNetwork
     */
    public String getYearInNetwork() {
        return yearInNetwork;
    }

    /**
     * @param yearInNetwork the yearInNetwork to set
     */
    public void setYearInNetwork(String yearInNetwork) {
        this.yearInNetwork = yearInNetwork;
    }

    /**
     * @return the aggregator
     */
    public String getAggregator() {
        return aggregator;
    }

    /**
     * @param aggregator the aggregator to set
     */
    public void setAggregator(String aggregator) {
        this.aggregator = aggregator;
    }

    /**
     * @return the level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the localCentrality
     */
    public String getLocalCentrality() {
        return localCentrality;
    }

    /**
     * @param localCentrality the localCentrality to set
     */
    public void setLocalCentrality(String localCentrality) {
        this.localCentrality = localCentrality;
    }

    /**
     * @return the globalCentrality
     */
    public String getGlobalCentrality() {
        return globalCentrality;
    }

    /**
     * @param globalCentrality the globalCentrality to set
     */
    public void setGlobalCentrality(String globalCentrality) {
        this.globalCentrality = globalCentrality;
    }

    /**
     * @return the group
     */
    public String getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * @return the university
     */
    public String getUniversity() {
        return university;
    }

    /**
     * @param university the university to set
     */
    public void setUniversity(String university) {
        this.university = university;
    }

    /**
     * @return the universityGroup
     */
    public String getUniversityGroup() {
        return universityGroup;
    }

    /**
     * @param universityGroup the universityGroup to set
     */
    public void setUniversityGroup(String universityGroup) {
        this.universityGroup = universityGroup;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the size
     */
    public String getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the withoutConnection
     */
    public boolean isWithoutConnection() {
        return withoutConnection;
    }

    /**
     * @param withoutConnection the withoutConnection to set
     */
    public void setWithoutConnection(boolean withoutConnection) {
        this.withoutConnection = withoutConnection;
    }

    /**
     * @return the coordinates
     */
    public NodeSNCoordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @param coordinates the coordinates to set
     */
    public void setCoordinates(NodeSNCoordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * @return the x
     */
    public String getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(String x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public String getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(String y) {
        this.y = y;
    }
}
