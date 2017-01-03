/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.serviceCompositionGraph;

/**
 *
 * @author phillipe
 */
public class InteroperatesWithEdge {
    private SimpleEdge interoperatesWith;
    private String source;
    private String target;    
    private String label;
    private String color;
    private String hoverColor;
    private String type;
    private String size;

    public InteroperatesWithEdge(){
    interoperatesWith = new SimpleEdge();
    label = "Interoperates with";
    color = "#000";
    hoverColor = "#FC0";
    type = "dashed";
    size = "1";        
    }
    
    /**
     * @return the interoperatesWith
     */
    public SimpleEdge getInteroperatesWith() {
        return interoperatesWith;
    }

    /**
     * @param interoperatesWith the interoperatesWith to set
     */
    public void setInteroperatesWith(SimpleEdge interoperatesWith) {
        this.interoperatesWith = interoperatesWith;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the target
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param target the target to set
     */
    public void setTarget(String target) {
        this.target = target;
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
     * @return the hoverColor
     */
    public String getHoverColor() {
        return hoverColor;
    }

    /**
     * @param hoverColor the hoverColor to set
     */
    public void setHoverColor(String hoverColor) {
        this.hoverColor = hoverColor;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
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
}
