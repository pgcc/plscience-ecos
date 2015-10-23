/*
 * The MIT License
 *
 * Copyright 2014 Pós-Graduação em Ciência da Computação UFJF.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.ufjf.pgcc.plscience.controller;

import br.ufjf.pgcc.plscience.dao.AgentDAO;
import br.ufjf.pgcc.plscience.model.Agent;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Guilherme Martins
 */

@ManagedBean(name = "userLoginBean")
//@SessionScoped
@ViewScoped
public class UserLoginBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    // VERDADEIRO se o cientista está logado, FALSO em caso contrário.
    private boolean isLogin;
    
    // Usuário e senha do cientista.
    private String loginUser;  
    private String password;
    
    // Armazena cientista logado.
    private Agent agentLog;
    
    //Realiza o login caso de tudo certo 
    public String login() throws IOException{

        //Verifica se o usuário e senha existem e se o usuario pode logar 
        AgentDAO agentDAO = new AgentDAO(); 
        Agent a = agentDAO.validityLogin(loginUser, password);
        
        //Caso não tenha retornado nenhum usuario, então mostramos um erro 
        //e redirecionamos ele para a página login.xhtml 
        //para ele realiza-lo novamente 
        if (a == null){ 
            //addErrorMessage("Email ou Senha errado, tente novamente !"); 
            FacesContext.getCurrentInstance().validationFailed(); 
            return "/faces/login.xhtml?faces-redirect=true"; 
        } else { 
            //caso tenha retornado um usuario, setamos a variável loggedIn 
            //como true e guardamos o usuario encontrado na variável 
            //usuarioLogado. Depois de tudo, mandamos o usuário 
            //para a página index.xhtml 
            setIsLogin(true); 
            setAgentLog(a); 
            return "restrict/index.xhtml?faces-redirect=true"; 
        } 
    } 

    //Realiza o logout do usuário logado 
    public String logout() throws IOException{ 
    
        //Setamos a variável usuarioLogado como nulo, ou seja, limpamos 
        //os dados do usuário que estava logado e depois setamos a variável 
        //loggedIn como false para sinalizar que o usuário não está mais logado
        setAgentLog(null); 
        setIsLogin(false); 
        
        //Mostramos um mensagem ao usuário e redirecionamos ele para a página de login
        //addInfoMessage("Logout realizado com sucesso !"); 
        return "/faces/login.xhtml?faces-redirect=true"; 
    }

    /**
     * @return the loginUser
     */
    public String getLoginUser() {
        return loginUser;
    }

    /**
     * @param loginUser the login to set
     */
    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the agentLog
     */
    public Agent getAgentLog() {
        return agentLog;
    }

    /**
     * @param agentLog the agentLog to set
     */
    public void setAgentLog(Agent agentLog) {
        this.agentLog = agentLog;
    }

    /**
     * @return the isLogin
     */
    public boolean isLogin() {
        return isLogin;
    }

    /**
     * @param isLogin the isLogin to set
     */
    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }
   
}
