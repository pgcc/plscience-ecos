/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.controller;

import java.io.IOException; 

import javax.servlet.Filter; 
import javax.servlet.FilterChain; 
import javax.servlet.FilterConfig; 
import javax.servlet.ServletException; 
import javax.servlet.ServletRequest; 
import javax.servlet.ServletResponse; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Guilherme Martins
 */
public class LoginFilterBean implements Filter {
    
    @Override
    public void destroy() { 
        // TODO Auto-generated method stub 
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        //Captura o ManagedBean chamado “usuarioMB” 
        UserLoginBean userLogin = (UserLoginBean) ((HttpServletRequest) request).getSession().getAttribute("userLoginBean");
        //Verifica se nosso ManagedBean ainda não 
        //foi instanciado ou caso a 
        //variável loggedIn seja false, assim saberemos que 
        // o usuário não está logado 
        if (userLogin == null || !userLogin.isLogin()) { 
            String contextPath = ((HttpServletRequest) request) .getContextPath();
            //Redirecionamos o usuário imediatamente 
            //para a página de login.xhtml 
            ((HttpServletResponse) response).sendRedirect(contextPath + "/faces/login.xhtml");
        } else { 
            //Caso ele esteja logado, apenas deixamos 
            //que o fluxo continue 
            chain.doFilter(request, response); 
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException { 
        // TODO Auto-generated method stub 
    }
}
