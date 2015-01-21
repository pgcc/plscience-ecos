/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.interoperability;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;


public class WordNetHandler {

	private Vector<String> listaSinonimos = new Vector<String>();// lista de sinonimos
	private Vector<String> listaHiperonimos = new Vector<String>();// lista de hiperonimos
	//modifique esses atributos para o local da sua WordNet
	private final String unidade = "C:/";
	private final String diretorioWordNet = unidade
			+ "Program Files (x86)/WordNet/2.1/bin/wn ";

	/**
	 * Consulta pelo conceito, caso seja do tipo Noum
	 * @param conceitoInteroperar
	 * @return
	 */
	public String getWordNetConceptsNoun(String conceitoInteroperar) {
		try {
				String linha;// linha obtida atraves da consulta WordNet
				// cria um processo com a resposta da consulta WordNet
				Process p = Runtime.getRuntime().exec(
						diretorioWordNet + conceitoInteroperar + " -a -synsn");
				// armazena temporariamente toda a resposta da consulta WordNet
				BufferedReader input = new BufferedReader(
						new InputStreamReader(p.getInputStream()));
				while ((linha = input.readLine()) != null) {
					// armazena em arquivo o resultado da consulta WordNet,
					// linha a linha
					if (!linha.equalsIgnoreCase("")) {
						FileHandler.save(unidade + "wordnet" + conceitoInteroperar
								+ "Noun.txt", linha, true);
					}
				}
				FileHandler.save(
						unidade + "wordnet" + conceitoInteroperar + "Noun.txt", "EOF",
						true);
				input.close();
			} catch (Exception err) {
				err.printStackTrace();
			}
		return unidade + "wordnet" + conceitoInteroperar + "Noun.txt";
	}

	/**
	 * Consulta pelo conceito, caso seja do tipo Verb
	 * @param conceitoInteroperar
	 * @return
	 */
	public String getWordNetConceptsVerb(String conceitoInteroperar) {
		try {
			String linha;// linha obtida atraves da consulta WordNet
			// cria um processo com a resposta da consulta WordNet
			Process p = Runtime.getRuntime().exec(
					diretorioWordNet + conceitoInteroperar + " -a -synsv");
			// armazena temporariamente toda a resposta da consulta WordNet
			BufferedReader input = new BufferedReader(new InputStreamReader(p
					.getInputStream()));
			while ((linha = input.readLine()) != null) {
				// armazena em arquivo o resultado da consulta WordNet, linha a
				// linha
				if (!linha.equalsIgnoreCase("")) {
					FileHandler.save(unidade + "wordnet" + conceitoInteroperar
							+ "Verb.txt", linha, true);
				}
			}
			FileHandler.save(
					unidade + "wordnet" + conceitoInteroperar + "Verb.txt", "EOF",
					true);
			input.close();
		} catch (Exception err) {
			err.printStackTrace();
		}
		return unidade + "wordnet" + conceitoInteroperar + "Verb.txt";
	}

	/**
	 * Armazena os sinonimos e hiperonimos, gerados pela consulta WordNet, em estruturas mais simples para serem manipuladas
	 * @param diretorioArquivoWordNet
	 * @return
	 * @throws IOException
	 */
	public boolean handleWordNetConcepts(String diretorioArquivoWordNet)
			throws IOException {
		BufferedReader conteudoSinonimosWordNet = new BufferedReader(
				new FileReader(diretorioArquivoWordNet));// arquivo com consulta
															// WordNet
		String linhaAnalisada = conteudoSinonimosWordNet.readLine().trim();// linha
																			// a
																			// ser
																			// analisada
		String tagTipoPalavra = null;// armazena tipo (substantivo, verbo, etc)
										// palavra
		String auxLinhaAnalisada = null;// auxiliar para an�lise da linha
		int countSemantica = 0;// contador de sem�nticas (Sense) da palavra
		// enquanto arquivo n�o acabar
		while (!linhaAnalisada.equalsIgnoreCase("EOF")) {
			String sinonimo;
			String hipernonimo;
			if (linhaAnalisada.startsWith("<")) {
				auxLinhaAnalisada = linhaAnalisada.substring(linhaAnalisada
						.indexOf(">") + 2);// exclui a tag de sinonimos
				tagTipoPalavra = linhaAnalisada.substring(0, linhaAnalisada
						.indexOf(">"));
				countSemantica++;// incrementa o n�mero de Sense para a palavra
			} else if (linhaAnalisada.startsWith("=")) {
				auxLinhaAnalisada = linhaAnalisada.substring(tagTipoPalavra
						.length() + 5);// exclui a tag de hiperonimos
			}
			// analisa sinonimos e listas de sinonimos para um mesmo contexto
			// (<)
			// -1 indica que n�o h� lista de sinonimos
			if (linhaAnalisada.startsWith("<")
					&& linhaAnalisada.indexOf(",") != -1) {
				System.out.println("Linha analisada Todos Sinonimos: "
						+ auxLinhaAnalisada);
				int index = auxLinhaAnalisada.indexOf(",");// verifica se h� uma
															// lista de
															// sinonimos
				while (index != -1) {// enquanto houver elementos na lista
					sinonimo = auxLinhaAnalisada.substring(0, index);// retira o
																		// sinonimo
					listaSinonimos.addElement(sinonimo.replaceAll("\\s+", ""));// armanzena-o
																				// eliminando
																				// possiveis
																				// espacos
																				// em
																				// branco
					auxLinhaAnalisada = auxLinhaAnalisada.substring(index + 2);// gera
																				// uma
																				// nova
																				// lista
					System.out
							.println("Linha analisada2 Excluindo os Sinonimos: "
									+ auxLinhaAnalisada);
					index = auxLinhaAnalisada.indexOf(",");// volta a verificar
															// a lista
				}
				sinonimo = auxLinhaAnalisada;// �ltimo sinonimo da lista
				listaSinonimos.addElement(sinonimo.replaceAll("\\s+", ""));// armazena-o
																			// eliminando
																			// possiveis
																			// espacos
																			// em
																			// branco
				listaSinonimos.addElement("Sense " + countSemantica);// marca no
																		// vector
																		// o fim
																		// deste
																		// Sense
			} else if (linhaAnalisada.startsWith("<")
					&& linhaAnalisada.indexOf(",") == -1) {
				// idem ao if
				sinonimo = linhaAnalisada
						.substring(linhaAnalisada.indexOf(">") + 2);
				listaSinonimos.addElement(sinonimo.replaceAll("\\s+", ""));// elimina
																			// possiveis
																			// espacos
																			// em
																			// branco
				listaSinonimos.addElement("Sense " + countSemantica);// marca no
																		// vector
																		// o fim
																		// deste
																		// Sense
				System.out
						.println("Linha analisada Sem Sinonimos: " + sinonimo);
			}
			// analisa hiperonimos e listas de hiperonimos para um mesmo
			// contexto (=)
			// -1 indica que n�o h� lista de hiperonimos
			// segue a mesma l�gica de sin�nimos, por�m atenta para a diferen�a
			// da tag de hiperonimos
			if (linhaAnalisada.startsWith("=")
					&& linhaAnalisada.indexOf(",") != -1) {
				System.out.println("Linha analisada Todos Hiperonimos: "
						+ auxLinhaAnalisada);
				int index = auxLinhaAnalisada.indexOf(",");
				while (index != -1) {
					hipernonimo = auxLinhaAnalisada.substring(0, index);
					listaHiperonimos.addElement(hipernonimo.replaceAll("\\s+",
							""));
					auxLinhaAnalisada = auxLinhaAnalisada.substring(index + 2);
					System.out
							.println("Linha analisada2 Excluindo os Hiperonimos: "
									+ auxLinhaAnalisada);
					index = auxLinhaAnalisada.indexOf(",");
				}
				hipernonimo = auxLinhaAnalisada;
				listaHiperonimos
						.addElement(hipernonimo.replaceAll("\\s+", ""));
				listaHiperonimos.addElement("Sense " + countSemantica);// marca
																		// no
																		// vector
																		// o fim
																		// deste
																		// Sense
			} else if (linhaAnalisada.startsWith("=")
					&& linhaAnalisada.indexOf(",") == -1) {
				hipernonimo = auxLinhaAnalisada;
				listaHiperonimos
						.addElement(hipernonimo.replaceAll("\\s+", ""));
				listaHiperonimos.addElement("Sense " + countSemantica);// marca
																		// no
																		// vector
																		// o fim
																		// deste
																		// Sense
				System.out.println("Linha analisada Sem hiperonimos: "
						+ hipernonimo);
			}
			linhaAnalisada = conteudoSinonimosWordNet.readLine().trim();
		}
		return true;
	}

	/**
	 * 
	 * @return - todos os sinonimos obtidos
	 */
	public Vector<String> getListaSinonimos() {
		return listaSinonimos;
	}

	/**
	 * 
	 * @return - todos os hiperonimos obtidos
	 */
	public Vector<String> getListaHiperonimos() {
		return listaHiperonimos;
	}
        
         public static void main(String[] args) throws IOException {
         WordNetHandler wh = new WordNetHandler();
      
         
         //wh.getWordNetConceptsVerb("chat");
         //wh.handleWordNetConcepts(wh.getWordNetConceptsNoun("chat"));
         wh.handleWordNetConcepts(wh.getWordNetConceptsVerb("chat"));
         wh.getListaHiperonimos();
         wh.getListaSinonimos();
         
        
        }

}
