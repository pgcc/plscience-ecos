/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.interoperability;

/**
 *
 * @author Fran
 */
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
	
	/**
	 * Entidade auxiliar para manipulação de arquivo
	 * @param diretorioArquivo
	 * @param conteudo
	 * @param adicionar - true: adiciona ao final do arquivo;
	 * 					  false: adiciona no comeco do arquivo.
	 * @throws IOException
	 */
	public static void save(String diretorioArquivo, String conteudo,
			boolean adicionar) throws IOException {
		FileWriter fw = new FileWriter(diretorioArquivo, adicionar);
		if (adicionar) {
			fw.write(conteudo + "\r\n");
		} else {
			fw.write(conteudo);
		}
		fw.close();
	}
		
}
