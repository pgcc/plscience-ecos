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
package br.ufjf.pgcc.plscience.util;

import br.ufjf.pgcc.plscience.controller.UserLoginBean;
import br.ufjf.pgcc.plscience.model.Agent;
import javax.el.ELContext;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author vitorfs
 */
public class BeanUtil {
    
    public static Object getManagedBean(String managedBean) {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        Object object = FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, managedBean);
        return object;
    }
    
    public static Agent getUserLogin() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();  
        HttpSession session = (HttpSession) externalContext.getSession(true);  
        UserLoginBean userLoginBean = (UserLoginBean) session.getAttribute("userLoginBean");
        
        return userLoginBean.getAgentLog();
    }
}
