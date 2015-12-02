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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vitorfs
 */
public class StringUtil {
    
    public static String InputStreamToString(InputStream inputStream) {
        try {
            InputStream responseStream = new BufferedInputStream(inputStream);
            BufferedReader responseStreamReader = new BufferedReader(new InputStreamReader(responseStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = responseStreamReader.readLine()) != null)
            {
                stringBuilder.append(line);
            }
            responseStreamReader.close();
            responseStream.close();   
            return stringBuilder.toString();
        } catch (Exception e) {
        }
        return "";
    }
    
    public static void removeList(List<String> l) {
        for(int i = 0; i < l.size(); i++) {
            
            String s = l.get(i);            
            char c = s.charAt(s.length()-1);
            
            if(c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || 
                    c == '6' || c == '7' || c == '8' || c == '9' || c == '0'){
                l.remove(i);
                i = 0;
            }
            
        }
    }
    
    public static void main(String[] args) throws IOException {
         
        List<String> l = new ArrayList<String>();
        
        l.add("casa");
        l.add("comida");
        l.add("casa1");
        l.add("comida1");
        l.add("bola");
        
        System.out.println("\n\nLista 1:");
        for(int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i));
        }
        
        removeList(l);
        
        System.out.println("\n\nLista 2:");
        for(int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i));
        }
    }
}
