/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.serviceComposition;

/**
 *
 * @author phillipe
 */
public class MessageChat {
    private String text;
    private String senderName;
    private String senderPicture;

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the senderName
     */
    public String getSenderName() {
        return senderName;
    }

    /**
     * @param senderName the senderName to set
     */
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    /**
     * @return the senderPicture
     */
    public String getSenderPicture() {
        return senderPicture;
    }

    /**
     * @param senderPicture the senderPicture to set
     */
    public void setSenderPicture(String senderPicture) {
        this.senderPicture = senderPicture;
    }
}
