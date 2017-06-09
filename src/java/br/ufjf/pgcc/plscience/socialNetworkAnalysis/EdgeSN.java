/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.socialNetworkAnalysis;

/**
 *
 * @author phillipe
 */
public class EdgeSN {
    private NodeSN from;
    private NodeSN to;
    private String source;
    private String target;
    private String weight;
    private String label;
    private String color;
    private String hoverColor;
    private String type;
    private String size;    
    private String relationshipYear;
    private String relationshipType;

    public EdgeSN(){
        color = "#000";
        hoverColor = "#FC0";
        size = "1";
        type = "line";
        weight = "";
        relationshipYear = "";
        relationshipType = "";
        label = "";
    }

    /**
     * return the hexadecimal value to an edge color
     * @param relationshipType
     * @return 
     */
    public static String getEdgeColorByRelationShipType(String relationshipType){
        if(relationshipType.contains("PRODUCAO_BIBLIOGRAFICA")){
            return "#00f";
        }else if(relationshipType.contains("ORIENTACAO_CONCLUIDA")){
            return "#0f0";
        }else if(relationshipType.contains("PROJETO")){
            return "#ffa500";
        }else if(relationshipType.contains("PRODUCAO_TECNICA")){
            return "#f00";
        }
        return "#fff";
    }
    /**
     * return the edge size
     * @param weight
     * @return 
     */
    public static String getEdgeSizeByWeight(String weight){
        Double w = Double.parseDouble(weight);
        if(w >= 0 && w < 0.33){
            return "1";
        }else if(w >= 0.33 && w < 0.66){
            return "3";
        }else if(w >= 0.66 && w < 1){
            return "5";
        }        
        return weight;
    }
    
    /**
     * @return the from
     */
    public NodeSN getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(NodeSN from) {
        this.from = from;
    }

    /**
     * @return the to
     */
    public NodeSN getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(NodeSN to) {
        this.to = to;
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
     * @return the weight
     */
    public String getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * @return the relationshipYear
     */
    public String getRelationshipYear() {
        return relationshipYear;
    }

    /**
     * @param relationshipYear the relationshipYear to set
     */
    public void setRelationshipYear(String relationshipYear) {
        this.relationshipYear = relationshipYear;
    }

    /**
     * @return the relationshipType
     */
    public String getRelationshipType() {
        return relationshipType;
    }

    /**
     * @param relationshipType the relationshipType to set
     */
    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
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
