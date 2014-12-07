/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.workflow;

/**
 *
 * @author vitorfs
 */
public class TavernaException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    public TavernaException() {
        super();
    }

    public TavernaException(final String message) {
        super(message);
    }

    public TavernaException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public TavernaException(final Throwable cause) {
        super(cause);
    }
}
