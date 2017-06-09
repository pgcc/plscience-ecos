/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.socialNetworkAnalysis;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.ItemAction;
import prefuse.action.RepaintAction;
import prefuse.action.animate.ColorAnimator;
import prefuse.action.animate.PolarLocationAnimator;
import prefuse.action.animate.QualityControlAnimator;
import prefuse.action.animate.VisibilityAnimator;
import prefuse.action.assignment.ColorAction;
import prefuse.action.assignment.DataColorAction;
import prefuse.action.assignment.FontAction;
import prefuse.action.layout.Layout;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.action.layout.graph.RadialTreeLayout;
import prefuse.activity.Activity;
import prefuse.activity.SlowInSlowOutPacer;
import prefuse.components.BotaoPlay;
import prefuse.components.BoxSelecionaPesquisador;
import prefuse.components.CheckBoxCentralidade;
import prefuse.components.CheckBoxConfiguracao;
import prefuse.components.CheckBoxNivelVisualizacao;
import prefuse.components.CheckBoxTipoRelacionamento;
import prefuse.components.TabelaAgrupamento;
import prefuse.components.TabelaSugestaoRelacionamento;
import prefuse.controls.ControlAdapter;
import prefuse.controls.FocusControl;
import prefuse.controls.PanControl;
import prefuse.controls.ZoomControl;
import prefuse.controls.ZoomToFitControl;
import prefuse.core.cs.ServicosHandlerImpl;
import prefuse.core.eo.TipoRelacionamento;
import prefuse.core.vo.AgrupamentosVO;
import prefuse.core.vo.GrupoVO;
import prefuse.core.vo.ParametrosAgrupamentoVO;
import prefuse.data.Edge;
import prefuse.data.Graph;
import prefuse.data.Node;
import prefuse.data.Schema;
import prefuse.data.Table;
import prefuse.data.Tuple;
import prefuse.data.event.TupleSetListener;
import prefuse.data.io.GraphMLReader;
import prefuse.data.query.SearchQueryBinding;
import prefuse.data.search.PrefixSearchTupleSet;
import prefuse.data.search.SearchTupleSet;
import prefuse.data.tuple.DefaultTupleSet;
import prefuse.data.tuple.TableNode;
import prefuse.data.tuple.TableTuple;
import prefuse.data.tuple.TupleSet;
import prefuse.render.AbstractShapeRenderer;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.EdgeRenderer;
import prefuse.render.LabelRenderer;
import prefuse.render.PolygonRenderer;
import prefuse.util.ColorLib;
import prefuse.util.FontLib;
import prefuse.util.GraphLib;
import prefuse.util.GraphicsLib;
import prefuse.util.PrefuseLib;
import prefuse.util.ui.JFastLabel;
import prefuse.util.ui.JSearchPanel;
import prefuse.util.ui.JValueSlider;
import prefuse.util.ui.UILib;
import prefuse.visual.AggregateItem;
import prefuse.visual.AggregateTable;
import prefuse.visual.DecoratorItem;
import prefuse.visual.VisualGraph;
import prefuse.visual.VisualItem;
import prefuse.visual.VisualTable;
import prefuse.visual.expression.InGroupPredicate;
import prefuse.visual.expression.VisiblePredicate;
import prefuse.visual.sort.ItemSorter;
import prefuse.visual.tuple.TableEdgeItem;
import prefuse.visual.tuple.TableNodeItem;

public class SocialNetwork extends Display {

	public static final String DATA_FILE = "/home/phillipe/NetBeansProjects/plscience-ecos/web/files/socialNetwork/rede_social.xml";

	// Colocada como global
	private static JFrame frame;

	public static Integer anoInicial = 2010;

	public static Integer nivelVisualizacao = 1;

	public static TipoRelacionamento tipoRelacionamentoInicial = TipoRelacionamento.VAZIO; // Inicialmente

	// considera
	// todos
	// os
	// tipos
	// de
	// relacionamento

	private static final String tree = "tree";

	private static final String treeNodes = "tree.nodes";

	private static final String treeEdges = "tree.edges";

	private static final String linear = "linear";

	public static final String EDGE_DECORATORS = "edgeDeco";

	public static final String NODE_DECORATORS = "nodeDeco";

	public static final String AGGR_DECORATORS = "aggrDeco";

	public static final String NOME_COMPLETO = "nomeCompleto";

	public static final String NOME_UNIVERSIDADE = "universidade";

	public static final String NOME_GRUPO = "group";

	private static final String gruposUniversidade = "gruposUniversidade";

	private static final String universidade = "universidade";

	private static final HashMap<Integer, HashSet> controleAnualArestas = new HashMap<Integer, HashSet>();

	// Grupos das arestas
	public static final String PASSADO = "passado";

	public static final String PRESENTE = "presente";

	public static final String FUTURO = "futuro";

	/** Define o Ano dos relacionamentos */
	private String ANO_RELACIONAMENTO = "anoRelacionamento";

	/** Define o Tipo dos relacionamentos */
	private String TIPO_RELACIONAMENTO = "tipoRelacionamento";

	/** Define o Peso dos relacionamentos */
	private String PESO_RELACIONAMENTO = "weight";

	/** Define o Agregador do pesquisador */
	private String AGREGADOR_PESQUISADOR = "agregador";

	// Variavel para exibir as informacoes pessoais dos pesquisadores
	public static JTextPane infoPessoal;

	// Variavel para controlar a selecao do tipo de relacionamento
	public static CheckBoxTipoRelacionamento checkBoxTipoRel;
	
	// Variavel para controlar a selecao do tipo de relacionamento
	public static CheckBoxCentralidade checkBoxCentralidade;

	// Variavel para controlar a selecao do nivel de visualizacao
	public static CheckBoxNivelVisualizacao checkBoxNivelVisual;

	// Variavel para controlar as configuracoes do grafo
	public static CheckBoxConfiguracao checkBoxConfiguracao;

	// Variavel para controlar as selecao dos pesquisadores
	public static BoxSelecionaPesquisador boxBuscaPesq;
	
	//Armazena os nos dos agragadores
	public static HashMap<String, HashSet<Node>> agregadores;

	private static final HashMap<Integer, Collection> exibirNosPorNivel = new HashMap<Integer, Collection>();

	private static final Schema DECORATOR_SCHEMA = PrefuseLib
			.getVisualItemSchema();
	static {
		DECORATOR_SCHEMA.setDefault(VisualItem.INTERACTIVE, false);
		DECORATOR_SCHEMA.setDefault(VisualItem.TEXTCOLOR, ColorLib.gray(128));
		DECORATOR_SCHEMA.setDefault(VisualItem.FONT, FontLib.getFont("Tahoma", 16));
	}

	private static int numberOfGroups = 0;
	private static int numberOfGrupos = 0;
	private static int numberOfPessoas = 0;
	private static int numberOfUniversidades = 0;

	public static LabelRenderer m_nodeRenderer;

	private EdgeRenderer m_edgeRenderer;

	private PolygonRenderer poligonoRenderer;

	private String m_label = "label";

	// CRIACAO DA VARIAVEL TITLE
	static Graph g = null;

	final static JFastLabel title = new JFastLabel("                 ");

	// Grafo radial era final
	// boolean Inicializacao = CriaXML(1);
	static SocialNetwork gview = new SocialNetwork(g, "name", anoInicial,
			tipoRelacionamentoInicial);

	static Visualization vis = gview.getVisualization();

	static JPanel painelp = new JPanel(new BorderLayout());
	
	public static JValueSlider slider;
	
	public static JValueSlider sliderCentral;
	public static JValueSlider sliderGlobal;

	// N�o era global
	// static SearchQueryBinding sq;
	// static JSearchPanel search;

	// RESOLVER O PROBLEMA DO SEARCH QUANDO O COMBOBOX � ALTERADO

	public static String TiraAcento(String acentos) {
		// String acentos = "�,�,�,�,�,�,�,�,�,�,�,�,�,�,�,�,�,�,�,�,�,�";

		acentos = acentos.replaceAll("[����]", "e");
		acentos = acentos.replaceAll("[����]", "u");
		acentos = acentos.replaceAll("[���]", "i");
		acentos = acentos.replaceAll("[����]", "a");
		acentos = acentos.replaceAll("[���]", "o");
		acentos = acentos.replaceAll("�", "c");

		acentos = acentos.replaceAll("[����]", "E");
		acentos = acentos.replaceAll("[����]", "U");
		acentos = acentos.replaceAll("[���]", "I");
		acentos = acentos.replaceAll("[����]", "A");
		acentos = acentos.replaceAll("[���]", "O");
		acentos = acentos.replaceAll("�", "C");

		return acentos;
	}

	public SocialNetwork(Graph gr, String label, Integer ano,
			TipoRelacionamento tipoRelacionamento) { // extends JFrame {

		super(new Visualization());
		
		
		//Cria o arquivo com os grupos caso o arquivo nao exista
		try {
			
			File arquivoGrupos = new File("/home/phillipe/NetBeansProjects/plscience-ecos/web/files/socialNetwork/GruposVisualizacao.txt");			
			if(!arquivoGrupos.exists()) {			
				ServicosHandlerImpl servicos = new ServicosHandlerImpl();
				
				ParametrosAgrupamentoVO parametros = new ParametrosAgrupamentoVO();
				parametros.setNumGruposMax(37);
				parametros.setNumGruposMin(37);
				
				parametros.setNumIteracoes(50);
				
				parametros.setSementeMax(20);
				parametros.setSementeMin(20);		
				
				TreeMap<Integer, Collection<AgrupamentosVO>> agrupamentos = servicos.calculaGrupos(parametros);
				
				//Cria o arquivo de agrupamentos
				//Seleciona o agrupamento com o melhor fluxo interno para gerar o arquivo de grupos para a visualizacao
				Integer melhorSemente = 0;
				Integer numGrupos = 0;
				Double fluxoInterno = Double.MIN_VALUE;
				for(Iterator it = agrupamentos.keySet().iterator(); it.hasNext();) {
					Integer semente = (Integer)it.next();
					Collection<AgrupamentosVO> grupos = agrupamentos.get(semente);
					AgrupamentosVO grupoVO = (AgrupamentosVO)grupos.iterator().next();
					if(grupoVO.getTotalFluxoInterno() > fluxoInterno) {
						melhorSemente = grupoVO.getSemente();	
						numGrupos = grupoVO.getNumGrupos();
					}
				}
				
				//Gera o arquivo para o agrupamento de melhor semente
				Collection<AgrupamentosVO> grupos = agrupamentos.get(melhorSemente);
				
				//Gera o arquivo para visualizacao dos grupos
				PrintWriter arquivoGruposVisualizacao = new PrintWriter(new FileOutputStream("/home/phillipe/NetBeansProjects/plscience-ecos/web/files/socialNetwork/GruposVisualizacao.txt"));
				AgrupamentosVO agrupamentosVO = (AgrupamentosVO)grupos.iterator().next();
				arquivoGruposVisualizacao.println(numGrupos + "\t" + agrupamentosVO.getNumElementos()+"\t"+agrupamentosVO.getTotalFluxoInterno()+"\t"+agrupamentosVO.getTotalFluxoExterno());
				for(Iterator it = agrupamentosVO.getCollElementos().iterator(); it.hasNext();) {
					GrupoVO grupoVO = (GrupoVO)it.next();
					arquivoGruposVisualizacao.println(grupoVO.getNumDoGrupo() + "\t" + grupoVO.getNomePesquisador().trim() + "\t" + grupoVO.getInstituicaoPesquisador()
							+ "\t" + grupoVO.getFluxoInternoElemento() + "\t" + grupoVO.getFluxoMaximoAoMedoid() + "\t" + grupoVO.getDistAoMedoid());
				}
				arquivoGruposVisualizacao.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		try {
			CriaXML(1, ano, tipoRelacionamento);
			g = new GraphMLReader().readGraph(DATA_FILE);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		// Criar as colecoes dos nos agregadores.
		agregadores = new HashMap<String, HashSet<Node>>();
		HashMap<Integer, HashSet<Node>> nosPorNivel = new HashMap<Integer, HashSet<Node>>();
		for (Iterator itTuples = g.getNodeTable().tuples(); itTuples.hasNext();) {
			TableTuple tableTuple = ((TableTuple) itTuples.next());
			String strAgr = (String) tableTuple.get("agregador");
			Integer nivel = (Integer) tableTuple.get("nivel");
			Node node = g.getNode(tableTuple.getRow());
			if (strAgr != null) {
				if (agregadores.get(strAgr) == null)
					agregadores.put(strAgr, new HashSet<Node>());
				agregadores.get(strAgr).add(node);
			}
			if (nosPorNivel.get(nivel) == null)
				nosPorNivel.put(nivel, new HashSet<Node>());
			nosPorNivel.get(nivel).add(node);
		}

		Comparator comparador = new Comparator() {
			public int compare(Object arg0, Object arg1) {
				return ((Integer) arg1).compareTo((Integer) arg0);
			}
		};

		// Cria as arestas entre os agregadores para cada nivel
		Integer numNiveis = nosPorNivel.keySet().size();

		// para cada nivel prepara a estrutura para a arvore geradora minima
		for (int i = 1; i <= numNiveis; i++) {
			g.AGM_POR_NIVEL.put(i, new ArrayList<Edge>());
		}

		List<Integer> niveis = new ArrayList<Integer>();
		niveis.addAll(nosPorNivel.keySet());
		Collections.sort(niveis, comparador);
		for (Iterator<Integer> it = niveis.iterator(); it.hasNext();) {
			Integer nivel = it.next();
			HashSet<Node> nosNivel = nosPorNivel.get(nivel);
			if (nivel <= (numNiveis - 1)) { // o nivel mais baixo nao precisa de
				// ter as arestas recriadas
				// para cada no do nivel, criar os seus relacionamentos
				for (Iterator<Node> itNos = nosNivel.iterator(); itNos
						.hasNext();) {
					Node sourceNode = itNos.next();
					Collection<Edge> collEdgeTarget = getRelacionamentosNoAgregador(
							sourceNode, agregadores);
					for (Iterator<Edge> itDest = collEdgeTarget.iterator(); itDest
							.hasNext();) {
						Edge edgeTarget = itDest.next();
						Node targetNode = edgeTarget.getTargetNode();
						Edge newEdge = addEdge(sourceNode,
								getNodeAgregador(targetNode), edgeTarget);

						// se for uma aresta da arvore geradora minima,
						// adicionar na estrutura de armazenamento das arvores
						if (edgeTarget.get("tipoRelacionamento") != null
								&& edgeTarget.get("tipoRelacionamento").equals(
										"AGM")) {
							g.AGM_POR_NIVEL.get(nivel).add(newEdge);
						}
					}
				}
			} else {
				// se for o ultimo nivel, adicionar a AGM na estrutura
				if (Integer.valueOf(nivel).equals(Integer.valueOf(numNiveis))) {
					for (Iterator<Node> itNos = nosNivel.iterator(); itNos
							.hasNext();) {
						Node sourceNode = itNos.next();
						Iterator<Edge> itEdgeTarget = sourceNode.edges();
						while (itEdgeTarget.hasNext()) {
							Edge edgeTarget = itEdgeTarget.next();
							// se for uma aresta da arvore geradora minima,
							// adicionar na estrutura de armazenamento das
							// arvores
							if (edgeTarget.get("tipoRelacionamento") != null
									&& edgeTarget.get("tipoRelacionamento")
											.equals("AGM")) {
								g.AGM_POR_NIVEL.get(nivel).add(edgeTarget);
							}
						}
					}
				}
			}
		}
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

		anoInicial = ano;
		m_label = label;

		// -- set up visualization --
		VisualGraph vg = m_vis.addGraph(tree, g);
		// m_vis.setInteractive(treeEdges, null, false);

		AggregateTable atUniversidade = m_vis.addAggregates(universidade);
		atUniversidade.addColumn(VisualItem.POLYGON, float[].class);
		atUniversidade.addColumn(universidade, String.class);
		atUniversidade.addColumn("label", String.class);

		Iterator nodes = vg.nodes();

		HashMap map = new HashMap();

		DefaultRendererFactory rf = new DefaultRendererFactory(m_nodeRenderer);
		rf.add(new InGroupPredicate(treeEdges), m_edgeRenderer);

		while (nodes.hasNext()) {
			VisualItem node = (VisualItem) nodes.next();
			String universidade = node.getString("universidade");

			if (map.containsKey(universidade)) {
				AggregateItem aitem = (AggregateItem) map.get(universidade);
				aitem.addItem(node);
			} else {
				AggregateItem aitem = (AggregateItem) atUniversidade.addItem();
				;

				poligonoRenderer = new PolygonRenderer();
				poligonoRenderer.setCurveSlack(0.18f);

				aitem.setString("universidade", universidade);
				aitem.setString("label", universidade);
				aitem.addItem(node);
				map.put(universidade, aitem);
				poligonoRenderer.setBounds(aitem);
				poligonoRenderer.setPolyType(Constants.POLY_TYPE_STACK);
				rf.add(new InGroupPredicate("universidade"), poligonoRenderer);
			}
		}

		// *********************************************
		// Agrupamento dos grupos
		// *********************************************

		{// separando os professores
			AggregateTable atGrupos = m_vis.addAggregates(gruposUniversidade);
			atGrupos.addColumn(VisualItem.POLYGON, float[].class);
			atGrupos.addColumn(VisualItem.LABEL, String.class);
			atGrupos.addColumn(gruposUniversidade, String.class);

			Iterator nodes2 = vg.nodes();

			HashMap map2 = new HashMap();

			while (nodes2.hasNext()) {
				VisualItem node = (VisualItem) nodes2.next();
				String grUniversidade = node.getString(gruposUniversidade);

				if (map2.containsKey(grUniversidade)) {
					AggregateItem aitem = (AggregateItem) map2
							.get(grUniversidade);
					aitem.addItem(node);
				} else {
					AggregateItem aitem = (AggregateItem) atGrupos.addItem();
					aitem.setString(gruposUniversidade, grUniversidade);
					aitem.addItem(node);
					map2.put(grUniversidade, aitem);
				}
			}
		}

		m_vis.setValue(treeNodes, null, VisualItem.SHAPE, new Integer(
				Constants.SHAPE_ELLIPSE));

		// -- set up renderers --
		//m_nodeRenderer = new LabelRenderer(m_label, "image");
		m_nodeRenderer = new LabelRenderer(m_label);
		m_nodeRenderer.setRenderType(AbstractShapeRenderer.RENDER_TYPE_FILL);
		m_nodeRenderer.setHorizontalPadding(0);
		m_nodeRenderer.setVerticalPadding(0);
		m_nodeRenderer.setImagePosition(Constants.TOP);
		m_nodeRenderer.setRoundedCorner(8, 8);
		m_nodeRenderer.setMaxImageDimensions(10,10);
		
		m_edgeRenderer = new EdgeRenderer();
		m_edgeRenderer.setDefaultLineWidth(2);
		m_edgeRenderer.setTipoRelacionamento(TipoRelacionamento.VAZIO);
		m_edgeRenderer.setAnoRelacionamento(anoInicial);

		PolygonRenderer poligonoRenderer2 = new PolygonRenderer(
				Constants.POLY_TYPE_CURVE);
		poligonoRenderer2.setCurveSlack(0.30f);
		// rf.add(new InGroupPredicate(gruposUniversidade), poligonoRenderer2);

		rf.setDefaultRenderer(m_nodeRenderer);

		rf.add(new InGroupPredicate(EDGE_DECORATORS), new LabelRenderer(VisualItem.LABEL));
		rf.add(new InGroupPredicate(NODE_DECORATORS), new LabelRenderer(VisualItem.LABEL));
		rf.add(new InGroupPredicate(AGGR_DECORATORS), new LabelRenderer("label"));

		// adding decorators, one group for the nodes, one for the edges and one
		// for the aggregates
		DECORATOR_SCHEMA.setDefault(VisualItem.TEXTCOLOR, ColorLib.gray(0));
		m_vis.addDecorators(EDGE_DECORATORS, treeEdges, DECORATOR_SCHEMA);

		DECORATOR_SCHEMA.setDefault(VisualItem.TEXTCOLOR, ColorLib.gray(128));
		m_vis.addDecorators(NODE_DECORATORS, treeNodes, DECORATOR_SCHEMA);

		// the HoverPredicate makes this group of decorators to appear only on
		// mouseOver
		DECORATOR_SCHEMA.setDefault(VisualItem.TEXTCOLOR, ColorLib.gray(255,
				128));
		DECORATOR_SCHEMA.setDefault(VisualItem.FONT, FontLib.getFont("Tahoma",
				Font.BOLD, 48));
		// m_vis.addDecorators(AGGR_DECORATORS, universidade, new
		// HoverPredicate(), DECORATOR_SCHEMA);

		// HoverPredicate ==> Utilizado para aparecer apenas quando passa o
		// mouse por cima
		// VisiblePredicate ==> Exibe se o schema estiver definido VISIBLE como
		// true
		m_vis.addDecorators(AGGR_DECORATORS, universidade,
				new VisiblePredicate(), DECORATOR_SCHEMA);

		m_vis.setRendererFactory(rf);

		// -- set up processing actions --

		int paletaProfessores[] = new int[57];
		paletaProfessores[0] = ColorLib.rgb(192, 192, 192);
		paletaProfessores[1] = ColorLib.rgb(105, 105, 105); // grey41
		paletaProfessores[2] = ColorLib.rgb(112, 128, 144); // SlateGrey
		paletaProfessores[3] = ColorLib.rgb(47, 79, 79); // DarkSlateGray
		paletaProfessores[4] = ColorLib.rgb(240, 230, 140);
		paletaProfessores[5] = ColorLib.rgb(189, 183, 107); // DarkKhaki
		paletaProfessores[6] = ColorLib.rgb(255, 255, 0); // Yellow
		paletaProfessores[7] = ColorLib.rgb(255, 215, 0); // Gold
		paletaProfessores[8] = ColorLib.rgb(218, 165, 32); // goldenrod
		paletaProfessores[9] = ColorLib.rgb(184, 134, 11); // DarkGoldenrod
		paletaProfessores[10] = ColorLib.rgb(128, 128, 0);
		paletaProfessores[11] = ColorLib.rgb(0, 255, 0); // Green1
		paletaProfessores[12] = ColorLib.rgb(50, 205, 50); // LimeGreen
		paletaProfessores[13] = ColorLib.rgb(238, 232, 170); // PaleGoldenrod
		paletaProfessores[14] = ColorLib.rgb(127, 255, 212); // Aquamarine
		paletaProfessores[15] = ColorLib.rgb(46, 139, 87); // SeaGreen
		paletaProfessores[16] = ColorLib.rgb(60, 179, 113); // MediumSeaGreen
		paletaProfessores[17] = ColorLib.rgb(143, 188, 143); // DarkSeaGreen
		paletaProfessores[18] = ColorLib.rgb(152, 251, 152); // PaleGreen
		paletaProfessores[19] = ColorLib.rgb(102, 205, 170); // Aquamarine3
		paletaProfessores[20] = ColorLib.rgb(0, 128, 128); // LightCoral
		paletaProfessores[21] = ColorLib.rgb(0, 255, 255); // Cyan1
		paletaProfessores[22] = ColorLib.rgb(0, 191, 255); // DeepSkyBlue1
		paletaProfessores[23] = ColorLib.rgb(100, 149, 237); // CornflowerBlue
		paletaProfessores[24] = ColorLib.rgb(64, 105, 225);
		paletaProfessores[25] = ColorLib.rgb(70, 130, 180); // SteelBlue
		paletaProfessores[26] = ColorLib.rgb(30, 144, 255); // DodgerBlue1
		paletaProfessores[27] = ColorLib.rgb(0, 0, 255); // Blue
		paletaProfessores[28] = ColorLib.rgb(255, 140, 0); // DarkOrange
		paletaProfessores[29] = ColorLib.rgb(128, 0, 128);
		paletaProfessores[30] = ColorLib.rgb(100, 149, 237); // CornflowerBlue
		paletaProfessores[31] = ColorLib.rgb(147, 112, 219); // MediumPurple
		paletaProfessores[32] = ColorLib.rgb(128, 43, 226);
		paletaProfessores[33] = ColorLib.rgb(218, 112, 214); // Orchid
		paletaProfessores[34] = ColorLib.rgb(216, 191, 216); // Thistle
		paletaProfessores[35] = ColorLib.rgb(219, 112, 147); // PaleVioletRed
		paletaProfessores[36] = ColorLib.rgb(221, 160, 221); // Plum
		paletaProfessores[37] = ColorLib.rgb(255, 105, 180); // HotPink
		paletaProfessores[38] = ColorLib.rgb(255, 20, 147); // DeepPink
		paletaProfessores[39] = ColorLib.rgb(255, 0, 255); // Magenta
		paletaProfessores[40] = ColorLib.rgb(250, 128, 114); // Salmon
		paletaProfessores[41] = ColorLib.rgb(205, 92, 92); // IndianRed
		paletaProfessores[42] = ColorLib.rgb(188, 143, 143); // RosyBrown
		paletaProfessores[43] = ColorLib.rgb(210, 180, 140); // Tan
		paletaProfessores[44] = ColorLib.rgb(245, 222, 179); // Wheat
		paletaProfessores[45] = ColorLib.rgb(244, 164, 96); // SandyBrown
		paletaProfessores[46] = ColorLib.rgb(255, 165, 0); // Orange
		paletaProfessores[47] = ColorLib.rgb(255, 140, 0); // DarkOrange
		paletaProfessores[48] = ColorLib.rgb(210, 105, 30); // Chocolate
		paletaProfessores[49] = ColorLib.rgb(222, 184, 135); // Burlywood
		paletaProfessores[50] = ColorLib.rgb(233, 150, 122); // DarkSalmon 
		paletaProfessores[51] = ColorLib.rgb(30, 144, 255); // Coral ==> PUC-RJ 
		paletaProfessores[52] = ColorLib.rgb(255, 140, 0); // ==> UFMG
		paletaProfessores[53] = ColorLib.rgb(255, 0, 0); // Red
		paletaProfessores[54] = ColorLib.rgb(221, 160, 221); // ==>UFPE
		paletaProfessores[55] = ColorLib.rgb(245, 222, 179); // Wheat ==>UFRGS
		paletaProfessores[56] = ColorLib.rgb(255, 127, 80); // Coral ==>UFRJ

		DataColorAction dataColor = new DataColorAction(treeNodes, "group",
				Constants.NOMINAL, VisualItem.FILLCOLOR, paletaProfessores);
		ItemAction nodeColor = new NodeColorAction(treeNodes);
		ItemAction textColor = new TextColorAction(treeNodes);
		m_vis.putAction("textColor", textColor);

		int[] paletaInstituicoes = new int[5];
		paletaInstituicoes[0] = ColorLib.rgba(102, 205, 170, 150); //Aquamarine3
		paletaInstituicoes[1] = ColorLib.rgba(135, 206, 235, 150);
		paletaInstituicoes[2] = ColorLib.rgba(240, 230, 140, 150);
		paletaInstituicoes[3] = ColorLib.rgba(245, 222, 179, 150); //Wheat
		paletaInstituicoes[4] = ColorLib.rgba(205, 205, 180, 150); //LightYellow3

		ColorAction aStrokeUniversidade = new ColorAction(universidade,
				VisualItem.STROKECOLOR);
		aStrokeUniversidade.setDefaultColor(ColorLib.gray(200));
		aStrokeUniversidade.add("_hover", ColorLib.rgb(255, 100, 100));

		DataColorAction aFillUniversidade = new DataColorAction(universidade,
				universidade, Constants.NOMINAL, VisualItem.FILLCOLOR,
				paletaInstituicoes);

		ItemAction edgeColor = new ColorAction(treeEdges,
				VisualItem.STROKECOLOR, ColorLib.rgb(0, 0, 0)); // 200,200,200

		ItemAction edgeColorFUTURO = new ColorAction(FUTURO,
				VisualItem.STROKECOLOR, ColorLib.rgb(0, 0, 0)); // 200,200,200

		ItemAction edgeColorPRESENTE = new ColorAction(PRESENTE,
				VisualItem.STROKECOLOR, ColorLib.rgb(200, 200, 200)); // 200,200,200

		ItemAction edgeColorPASSADO = new ColorAction(PASSADO,
				VisualItem.STROKECOLOR, ColorLib.rgba(0, 0, 0, 0)); // 200,200,200

		FontAction fonts = new FontAction(treeNodes, FontLib.getFont("Tahoma",
				10));
		fonts.add("ingroup('_focus_')", FontLib.getFont("Tahoma", 11));

		ColorAction aStrokeSubGrupo = new ColorAction(gruposUniversidade,
				VisualItem.STROKECOLOR);
		aStrokeSubGrupo.setDefaultColor(ColorLib.alpha(200));
		aStrokeSubGrupo.add("_hover", ColorLib.rgb(255, 100, 100));

		// recolor
		ActionList colors = new ActionList();
		colors.add(dataColor);
		colors.add(textColor);
		colors.add(aFillUniversidade);
		colors.add(aStrokeUniversidade);
		colors.add(aStrokeSubGrupo);
		// colors.add(edgeColor);
		colors.add(edgeColorFUTURO);
		colors.add(edgeColorPRESENTE);
		colors.add(edgeColorPASSADO);

		RadialTreeLayout treeLayout = new RadialTreeLayout(tree);
		// treeLayout.setAngularBounds(-Math.PI/2, Math.PI);
		m_vis.putAction("treeLayout", treeLayout);

		// recolor
		ActionList recolor = new ActionList();
		recolor.add(nodeColor);
		recolor.add(textColor);
		m_vis.putAction("recolor", recolor);

		// repaint
		ActionList repaint = new ActionList();
		repaint.add(recolor);
		repaint.add(new RepaintAction());
		m_vis.putAction("repaint", repaint);

		// animate paint change
		ActionList animatePaint = new ActionList(400);
		animatePaint.add(new ColorAnimator(treeNodes));
		animatePaint.add(new RepaintAction());
		m_vis.putAction("animatePaint", animatePaint);

		// animated transition
		ActionList animate = new ActionList(1250);
		animate.setPacingFunction(new SlowInSlowOutPacer());
		animate.add(new QualityControlAnimator());
		animate.add(new VisibilityAnimator(tree));
		animate.add(new PolarLocationAnimator(treeNodes, linear));
		animate.add(new ColorAnimator(treeNodes));
		animate.add(new RepaintAction());
		m_vis.putAction("animate", animate);
		m_vis.alwaysRunAfter("layout", "animate");

		// now create the main layout routine
		ActionList layout = new ActionList(Activity.INFINITY);
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
		// O Force Layout SEMPRE precisa ser o primeiro a ser adicionado na
		// action, pois ele precisa ser recuperado
		// na criacao dos paineis
		layout.add(new GrafoRadialForceLayout(tree, true));
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
		layout.add(colors);
		layout.add(treeLayout);
		layout.add(new AggregateLayout3(universidade));
		layout.add(new LabelLayout3(EDGE_DECORATORS));
		layout.add(new LabelLayout3(NODE_DECORATORS));
		layout.add(new LabelLayout3(AGGR_DECORATORS));
		layout.add(new RepaintAction());
		m_vis.putAction("layout", layout);

		// ------------------------------------------------

		// initialize the display
		// PARA CONFIGURAR O TAMANHO DO DISPLAY
		setSize(1000, 690);
		pan(500, 300);
		setHighQuality(true);
		setItemSorter(new ItemSorter());
		setDamageRedraw(false); // disable due to Java2D image clipping errors
		addControlListener(new AggregateDragControl3());
		addControlListener(new ZoomControl());
		addControlListener(new PanControl());
		addControlListener(new ZoomToFitControl());
		addControlListener(new FocusControl(1, "filter"));

		// ------------------------------------------------

		// Ajusta as cores das arestas
		// Inicia a visualizacao apresentando apenas a arvore geradora minima
		for (Iterator itEdges = g.getAgmPoNivel().get(nivelVisualizacao)
				.iterator(); itEdges.hasNext();) {
			Edge edge = (Edge) itEdges.next();
			VisualItem arestaDoGrafo = ((VisualTable) m_vis.getGroup(treeEdges))
					.getItem(edge.getRow());
			Integer anoRelacionamento = (Integer) arestaDoGrafo
					.get("anoRelacionamento");
			// Relacionamentos do ANO atual
			if (Integer.valueOf(anoInicial).equals(anoRelacionamento)) {
				arestaDoGrafo.setStrokeColor(ColorLib.rgba(0, 0, 0, 150));
				arestaDoGrafo.setSize(3);
			} else if (anoRelacionamento < anoInicial) { // Relacionamento
				// PASSADO
				arestaDoGrafo.setStrokeColor(ColorLib.rgba(100, 100, 100, 100));
				setLarguraAresta((TableEdgeItem) arestaDoGrafo);
			} else { // Relacionamentos FUTUROS
				arestaDoGrafo
						.setStrokeColor(ColorLib.rgba(1000, 1000, 1000, 0));
			}
		}

		// Ajusta as cores dos Nos
		for (Iterator itNodes = m_vis.items(treeNodes); itNodes.hasNext();) {
			TableNodeItem noDoGrafo = (TableNodeItem) itNodes.next();
			Integer anoNaRede = (Integer) noDoGrafo.get("anoNaRede");
			Integer nivelNo = (Integer) noDoGrafo.get("nivel");

			boolean exibirNo = nivelNo.equals(nivelVisualizacao);

			if (exibirNo) {
				if (Integer.valueOf(anoInicial).equals(anoNaRede)) { // No
					// entrou na rede esse Ano
					noDoGrafo.setStrokeColor(ColorLib.rgba(100, 100, 100, 100));
					noDoGrafo.setSize(2);
					noDoGrafo.setHighlighted(true);
					noDoGrafo.setShape(2);
				} else if (anoNaRede < anoInicial) { // No antigo na rede
					noDoGrafo.setStrokeColor(ColorLib.rgb(0, 0, 0));
					if (nivelNo.equals(Integer.valueOf(1))) {
						noDoGrafo.setSize(5);
						setPosicao(noDoGrafo);
					} else {
						noDoGrafo.setSize(1);
					}
				} else { // Relacionamentos FUTUROS , transparente
					noDoGrafo
							.setStrokeColor(ColorLib.rgba(1000, 1000, 1000, 0));
					noDoGrafo.setSize(0);
				}
			} else {
				noDoGrafo.setVisible(false);
				for (Iterator itEdges = noDoGrafo.edges(); itEdges.hasNext();) {
					TableEdgeItem edge = (TableEdgeItem) itEdges.next();
					if (edge.get("tipoRelacionamento") != null
							&& !edge.get("tipoRelacionamento").equals("AGM")) {
						edge.setVisible(false);
					}
				}
			}
		}
		
		//Se o nivel de visualizacao for 1 (Universidades), nao exibir o agregador
		if(checkBoxNivelVisual == null || 
				Integer.valueOf(checkBoxNivelVisual.getNivelVisualizacaoAtual()).equals(Integer.valueOf(1))) {
			for (Iterator it = m_vis.items(universidade); it.hasNext();) {
				Object obj = it.next();
				if (obj instanceof AggregateItem) {
					AggregateItem agg = (AggregateItem) obj;
					agg.setStrokeColor(ColorLib.rgba(100, 100, 100, 0));
					agg.setFillColor(ColorLib.rgba(100, 100, 100, 0));
					agg.setVisible(false);
				}
			}
		}
		
		//pre-load images, otherwise they will be loaded asynchronously
        //m_nodeRenderer.getImageFactory().preloadImages(m_vis.items(treeNodes),"image");

		// filter graph and perform layout
		m_vis.run("layout");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		layout.remove(treeLayout);
		// m_vis.run("treelayout");

		// maintain a set of items that should be interpolated linearly
		// this isn't absolutely necessary, but makes the animations nicer
		// the PolarLocationAnimator should read this set and act accordingly
		m_vis.addFocusGroup(linear, new DefaultTupleSet());
		m_vis.getGroup(Visualization.FOCUS_ITEMS).addTupleSetListener(
				new TupleSetListener() {
					public void tupleSetChanged(TupleSet t, Tuple[] add,
							Tuple[] rem) {
						TupleSet linearInterp = m_vis.getGroup(linear);
						if (add.length < 1 || !(add[0] instanceof Node))
							return;
						linearInterp.clear();
						for (Node n = (Node) add[0]; n != null; n = n
								.getParent())
							linearInterp.addTuple(n);
					}
				});

		SearchTupleSet search = new PrefixSearchTupleSet();
		m_vis.addFocusGroup(Visualization.SEARCH_ITEMS, search);
		search.addTupleSetListener(new TupleSetListener() {
			public void tupleSetChanged(TupleSet t, Tuple[] add, Tuple[] rem) {
				if (((PrefixSearchTupleSet) t).getQuery() != null
						&& !((PrefixSearchTupleSet) t).getQuery().equals(""))
					m_vis.cancel("layout");
				else
					m_vis.run("layout");

				m_vis.cancel("animatePaint");
				m_vis.run("recolor");
				m_vis.run("animatePaint");
			}
		});

	}

	/**
	 * M�todo que far� a altera��o de um n� para ficar mais destacado em rela��o aos demais
	 */
public static void exibirRelacionamentos(Collection collTableItemNode) {
		
		try {
			//vis.cancel("layout");
			
			Collection collAux = new ArrayList();
			
			boolean fezFiltro = false;
	
			// Se a colecao estiver vazia, todos os nos devem ser exibidos
			if (collTableItemNode == null || collTableItemNode.isEmpty()) {
				for (Iterator itNodes = vis.items(treeNodes); itNodes.hasNext();)
					collAux.add(itNodes.next());
			} else {
				fezFiltro = true;
				collAux.addAll(collTableItemNode);
			}
	
			for (Iterator itNodes = vis.items(treeNodes); itNodes.hasNext();) {
				Object obj = itNodes.next();
				if (obj instanceof TableNodeItem) {
					TableNodeItem noDoGrafo = (TableNodeItem) obj;
					Integer anoNaRede = (Integer) noDoGrafo.get("anoNaRede");
					Integer nivelNo = (Integer) noDoGrafo.get("nivel");
	
					// Exibir o no se ele for do nivel de visualizacao selecionado e
					// (estiver na colecao de item a serem exibidos ou estiver
					// relacionado com algum no dessa colecao)
					boolean exibirNo = (nivelNo.equals(checkBoxNivelVisual.getNivelVisualizacaoAtual()) 
							&& (collAux.contains(noDoGrafo) 
									|| isVizinho(collAux, noDoGrafo)));
	
					if (exibirNo) {
						noDoGrafo.setVisible(true);
						
						for (Iterator itEdges = noDoGrafo.edges(); itEdges.hasNext();) {
							TableEdgeItem edge = (TableEdgeItem) itEdges.next();
	
							// Exibir a aresta apenas se o no de destino estiver na colecao de nos a serem exibidos
							TableNodeItem nodeItem = (TableNodeItem) ((VisualTable) vis.getGroup(treeNodes)).getItem(edge.getTargetNode().getRow());

							// Nao exibir a arvore geradora minima
							if (!SocialNetwork.checkBoxConfiguracao.exibirAGM) {
								Integer anoRelacionamento = (Integer) edge.get("anoRelacionamento");
								// Verifica se o tipo de relacionamento estah
								// marcado para exibicao
								if (edge.get("tipoRelacionamento") != null
										&& !edge.get("tipoRelacionamento").equals("AGM")
										&& collAux.contains(nodeItem)) {
									if (SocialNetwork.checkBoxTipoRel.getSituacaoCheckBox().get(
											TipoRelacionamento.valueOf((String) edge.get("tipoRelacionamento")))) {
										edge.setVisible(true);
										if (Integer.valueOf(SocialNetwork.anoInicial).equals(anoRelacionamento)) {
											edge.setStrokeColor(ColorLib.rgba(0, 0, 0, 150));
											setColor((TableEdgeItem) edge, TipoRelacionamento.valueOf((String) edge.get("tipoRelacionamento")), 100);
											edge.setSize(3);
										} else if (anoRelacionamento < SocialNetwork.anoInicial) { // Relacionamento PASSADO
											edge.setStrokeColor(ColorLib.rgba(100,100, 100, 100));
											setColor((TableEdgeItem) edge, TipoRelacionamento.valueOf((String) edge.get("tipoRelacionamento")), 100);
											setLarguraAresta((TableEdgeItem) edge);
										} else { // Relacionamentos FUTUROS
											edge.setStrokeColor(ColorLib.rgba(1000,	1000, 1000, 0));
											edge.setVisible(false);
										}
									} else
										edge.setStrokeColor(ColorLib.rgba(1000,	1000, 1000, 0));
								} else
									edge.setStrokeColor(ColorLib.rgba(1000, 1000, 1000, 0));
							} else {
								// Exibir apenas a arvore geradora minima se o no de
								// destino da aresta for visivel
								if (edge.get("tipoRelacionamento") != null
										&& edge.get("tipoRelacionamento").equals("AGM")
										&& collAux.contains(nodeItem)) {
									edge.setVisible(true);
									edge.setStrokeColor(ColorLib.rgba(100, 100, 100, 100));
									edge.setSize(3);
								} else {
									if (edge.get("tipoRelacionamento") != null)
										edge.setVisible(false);
								}
							}
						}
	
						if (Integer.valueOf(SocialNetwork.anoInicial).equals(
								anoNaRede)) { // No entrou na rede esse Ano
							noDoGrafo.setStrokeColor(ColorLib.rgba(100, 100, 100,
									100));
							noDoGrafo.setSize(2);
							noDoGrafo.setHighlighted(true);
							noDoGrafo.setShape(2);
						} else if (anoNaRede < SocialNetwork.anoInicial) { // Relacionamento
							// PASSADO
							// preto
							noDoGrafo.setStrokeColor(ColorLib.rgb(0, 0, 0));
							if (nivelNo.equals(Integer.valueOf(1))) {
								noDoGrafo.setSize(5);
							} else {
								int multi = 1;
								if(collTableItemNode != null && collTableItemNode.contains(noDoGrafo))
									multi = 2;
								
								double tam = getTamanhoNo(noDoGrafo, fezFiltro);
								noDoGrafo.setSize(tam*multi);
							}
						} else { // Relacionamentos FUTUROS , transparente
							noDoGrafo.setStrokeColor(ColorLib.rgba(1000, 1000,
									1000, 0));
							noDoGrafo.setSize(0);
							noDoGrafo.setVisible(false);
						}
					} else {
						noDoGrafo.setVisible(false);
						noDoGrafo.setSize(0);
						for (Iterator itEdges = noDoGrafo.edges(); itEdges
								.hasNext();) {
							TableEdgeItem edge = (TableEdgeItem) itEdges.next();
							if (edge.get("tipoRelacionamento") != null) {
								if (!edge.get("tipoRelacionamento").equals("AGM"))
									edge.setVisible(false);
	
								edge.setStrokeColor(ColorLib.rgba(1000, 1000, 1000,
										0));
							}
						}
					}
				}
			}
			
			for (Iterator it = vis.items(universidade); it.hasNext();) {
				Object obj = it.next();
				if (obj instanceof AggregateItem) {
					AggregateItem agg = (AggregateItem) obj;
					boolean visivel = false;
					if(checkBoxNivelVisual != null && 
							!Integer.valueOf(checkBoxNivelVisual.getNivelVisualizacaoAtual()).equals(Integer.valueOf(1))) {
						for (Iterator itItens = agg.items(); itItens.hasNext();) {
							Object objAgr = itItens.next();
							if (objAgr instanceof TableNodeItem
									&& ((TableNodeItem) objAgr).isVisible()) {
								visivel = true;
								break;
							}
						}
					}
					agg.setVisible(visivel);
				}
			}
			//vis.run("layout");
		} catch(Exception ex) {
			System.out.println("Tentando de novo!!!");
			exibirRelacionamentos(collTableItemNode);
		}
	}
	
	
	/**
	 * M�todo que exibe os relacionamentos para um conjunto de TableItemNodes.
	 * Caso a colecao seja vazia, todos os nos do nivel atual serao exibidos.
	 * 
	 */
	public static void destacarNosCentrais(Integer nivel, int quantidade, int tipo) {
		
		try{
			HashMap <String, Float> central = new HashMap<String, Float>();
			HashMap <String, Float> global = new HashMap<String, Float>();
			HashMap <String, TableNodeItem> nodes = new HashMap <String, TableNodeItem>();
			
			for (Iterator itNodes = vis.items(treeNodes); itNodes.hasNext();) {
				Object obj = itNodes.next();
				if (obj instanceof TableNodeItem) {
					TableNodeItem noDoGrafo = (TableNodeItem) obj;
					Integer nivelNo = (Integer) noDoGrafo.get("nivel");
					noDoGrafo.setSize(1);
					if(nivelNo.compareTo(nivel) == 0){
						String id = (String) noDoGrafo.get("name");
						Float centralidadeLocal = Float.parseFloat((String)noDoGrafo.get("centralidadeLocal"));
						Float centralidadeGlobal = Float.parseFloat((String)noDoGrafo.get("centralidadeGlobal"));
						nodes.put(id, noDoGrafo);
						central.put(id, centralidadeLocal);
						global.put(id, centralidadeGlobal);
					}
				}
			}
			
			//central = (HashMap<String, Float>) sortInverseValue(central);
			//global = (HashMap<String, Float>) sortByValue(global);
			
			central = (HashMap<String, Float>) sortByValue(central);
			global = (HashMap<String, Float>) sortInverseValue(global);
			
			HashMap<String, Float> result = tipo == 0 ? central : global; 
			
			List<Float> list = new ArrayList<Float>();
			for (Map.Entry<String, Float> entry : result.entrySet()) {
			if (list.size() >= quantidade || quantidade == 0) break;
			    list.add(entry.getValue());
			    TableNodeItem node = nodes.get(entry.getKey());
			    node.setSize(2);
			}					
		} catch(Exception ex) {
			System.out.println("Tentando de novo!!!");
			//destacarNosCentrais();
		}
	}
	
	public static <K,V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map){
		List<Map.Entry<K,V>> list = new LinkedList<Map.Entry<K,V>>( map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>(){
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2){
				return (o1.getValue()).compareTo( o2.getValue() );
			}
		});
		
		Map<K,V> result = new LinkedHashMap<K,V>();
		for (Map.Entry<K, V> entry : list){
			result.put( entry.getKey(), entry.getValue());
		}
			
		return result;
	}
	
	public static <K,V extends Comparable<? super V>> Map<K, V> sortInverseValue(Map<K, V> map){
		List<Map.Entry<K,V>> list = new LinkedList<Map.Entry<K,V>>( map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>(){
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2){
				return (o2.getValue()).compareTo( o1.getValue() );
			}
		});
		
		Map<K,V> result = new LinkedHashMap<K,V>();
		for (Map.Entry<K, V> entry : list){
			result.put( entry.getKey(), entry.getValue());
		}
			
		return result;
	}
	
	/**
	 * Define a cor da aresta baseando-se no tipo do relacionamento e na transparencia que a mesma deve ter
	 * @param tipoRel
	 * @param transparencia
	 */
	private static void setColor(TableEdgeItem edge, TipoRelacionamento tipoRel, int transparencia) {
		if(tipoRel.equals(TipoRelacionamento.PRODUCAO_BIBLIOGRAFICA))
			edge.setStrokeColor(ColorLib.rgba(0, 0, 255, transparencia)); //Azul
		else if(tipoRel.equals(TipoRelacionamento.ORIENTACAO_CONCLUIDA))
			edge.setStrokeColor(ColorLib.rgba(255, 215, 0, transparencia)); //Amarelo
		else if(tipoRel.equals(TipoRelacionamento.PRODUCAO_TECNICA))
			edge.setStrokeColor(ColorLib.rgba(255, 0, 255, transparencia));  //Magenta
		else if(tipoRel.equals(TipoRelacionamento.PROJETO))
			edge.setStrokeColor(ColorLib.rgba(255, 0, 0, transparencia)); //Vermelho
	}

	/**
	 * verifica se o no � vizinho de algum no da colecao
	 * 
	 * @param collItens
	 * @param noDoGrafo
	 * @return
	 */
	private static boolean isVizinho(Collection<TableNodeItem> collItens,
			TableNodeItem noDoGrafo) {
		for (Iterator<TableNodeItem> it = collItens.iterator(); it.hasNext();) {
			TableNodeItem node = it.next();
			Collection<TableNodeItem> collTemp = new ArrayList<TableNodeItem>();
			for (Iterator itEdges = node.edges(); itEdges.hasNext();) {
				// Se a aresta for ser exibida o no tb deve ser exibido
				boolean exibirAresta = false;
				Edge edge = (Edge) itEdges.next();
				if (edge.get("tipoRelacionamento") != null) {
					if (checkBoxConfiguracao.exibirAGM
							&& edge.get("tipoRelacionamento").equals("AGM"))
						exibirAresta = true;
					else if (!checkBoxConfiguracao.exibirAGM
							&& !edge.get("tipoRelacionamento").equals("AGM")
							&& SocialNetwork.checkBoxTipoRel
									.getSituacaoCheckBox()
									.get(
											TipoRelacionamento
													.valueOf((String) edge
															.get("tipoRelacionamento"))))
						exibirAresta = true;

					if (exibirAresta) {
						TableNodeItem nodeItem = (TableNodeItem) ((VisualTable) vis
								.getGroup(treeNodes)).getItem(edge
								.getTargetNode().getRow());
						if (nodeItem.equals(noDoGrafo)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public static void setNosPorNivel(Integer nivel, Collection nos) {
		exibirNosPorNivel.put(nivel, nos);
	}

	public static Collection getNosPorNivel(Integer nivel) {
		return exibirNosPorNivel.get(nivel);
	}

	public static void setLarguraAresta(TableEdgeItem edge) {
		if (edge.get("tipoRelacionamento").equals("AGM"))
			edge.setSize(3);
		else
			edge.setSize(1);
	}
	
	private static double getTamanhoNo(TableNodeItem item, boolean filtro) {		
		if(filtro && item.isVisible() && item instanceof TableNodeItem) {
        	Integer nivel = (Integer)item.get("nivel");
        	if(nivel.equals(Integer.valueOf(3))) {
	        	Collection collFiltroPesq = getNosPorNivel(nivel);
	        	
	        	if(collFiltroPesq != null && !collFiltroPesq.isEmpty()) {
		            return 0.7;
	        	}
        	}
    	}
		return 1.0;
	}

	protected Node getNodeAgregador(Node nodeAgregado) {
		String idAgregador = (String) nodeAgregado.get(AGREGADOR_PESQUISADOR);
		for (Iterator itTuples = g.getNodeTable().tuples(); itTuples.hasNext();) {
			TableTuple tableTuple = ((TableTuple) itTuples.next());
			String nomeNo = (String) tableTuple.get("name");
			Node node = g.getNode(tableTuple.getRow());
			if (nomeNo.equals(idAgregador)) {
				return node;
			}
		}
		return null;
	}

	/**
	 * retorna um Map com o Node de destino e o numero de relacionamento entre
	 * eles
	 */
	protected Collection<Edge> getRelacionamentosNoAgregador(Node aggSource,
			HashMap<String, HashSet<Node>> agregadores) {
		Collection<Edge> collArestasVizinhos = new ArrayList<Edge>();
		HashSet<Node> agregados = agregadores.get(aggSource.get("name"));
		for (Iterator<Node> it = agregados.iterator(); it.hasNext();) {
			Node nodeAgregado = it.next();
			collArestasVizinhos
					.addAll(getArestasVizinhosDeAgregadoresDiferentes(nodeAgregado));
		}
		return collArestasVizinhos;
	}

	/**
	 * Retorna uma colecao com todos os vizinhos do no passado como parametro.
	 * Retirando da colecao os vizinhos que estao sob o mesmo agregador. Por
	 * exemplo, dois vizinhos dentro da UFRJ nao estarao na colecao, mas
	 * vizinhos de instituicoes diferentes sim.
	 * 
	 * @param node
	 * @return
	 */
	protected Collection<Edge> getArestasVizinhosDeAgregadoresDiferentes(
			Node node) {
		Collection<Edge> collArestas = new ArrayList<Edge>();
		String aggNode = node.getString(AGREGADOR_PESQUISADOR);
		for (Iterator itEdges = node.outEdges(); itEdges.hasNext();) {
			Edge aresta = (Edge) itEdges.next();
			Node vizinho = aresta.getTargetNode();
			String aggVizinho = vizinho.getString(AGREGADOR_PESQUISADOR);
			if (!aggVizinho.equals(aggNode)) {
				collArestas.add(aresta);
			}
		}
		return collArestas;
	}

	/**
	 * Adiciona as arestas dos niveis abstratos do grafo considerando o ano e
	 * tipo do relacionamento
	 */
	protected Edge addEdge(Node source, Node target, Edge edgeExemplo) {
		Edge e;
		try {
			e = g.addEdge(source, target);
			e.set(ANO_RELACIONAMENTO, edgeExemplo.get(ANO_RELACIONAMENTO));
			e.set(TIPO_RELACIONAMENTO, edgeExemplo.get(TIPO_RELACIONAMENTO));
			e.set(PESO_RELACIONAMENTO, edgeExemplo.get(PESO_RELACIONAMENTO));
			return e;
		} catch (Exception ee) {
			System.out.println(ee.getMessage());
		}
		return null;
	}

	public static void setPosicao(TableNodeItem node) {
		if (node.get("name").equals("PUC-RJ")) {
			node.setEndX(0);
			node.setEndY(0);
		} else if (node.get("name").equals("UFRJ")) {
			node.setEndX(0);
			node.setEndY(200);
		} else if (node.get("name").equals("UFRGS")) {
			node.setEndX(200);
			node.setEndY(0);
		} else if (node.get("name").equals("UFPE")) {
			node.setEndX(200);
			node.setEndY(200);
		} else if (node.get("name").equals("UFMG")) {
			node.setEndX(100);
			node.setEndY(300);
		}
	}

	// ------------------------------------------------------------------------

	public static void main(String argv[]) {
		
		Collection<prefuse.core.cd.katz.model.Path> collPaths = new ArrayList<prefuse.core.cd.katz.model.Path>();
		int[][] vizinhos = new int[6][6];
		Arrays.fill(vizinhos[0], 0);
		Arrays.fill(vizinhos[1], 0);
		Arrays.fill(vizinhos[2], 0);
		Arrays.fill(vizinhos[3], 0);
		Arrays.fill(vizinhos[4], 0);
		Arrays.fill(vizinhos[5], 0);
		vizinhos[0][1]=1;
		vizinhos[0][2]=1;
		vizinhos[0][4]=1;
		
		vizinhos[1][0]=1;
		vizinhos[1][2]=1;
		vizinhos[1][3]=1;
		vizinhos[1][4]=1;
		
		vizinhos[2][0]=1;
		vizinhos[2][1]=1;
		vizinhos[2][3]=1;
		vizinhos[2][4]=1;
		
		vizinhos[3][1]=1;
		vizinhos[3][2]=1;
		vizinhos[3][5]=1;
		
		vizinhos[4][0]=1;
		vizinhos[4][1]=1;
		vizinhos[4][2]=1;
		vizinhos[4][5]=1;
		
		vizinhos[5][3]=1;
		vizinhos[5][4]=1;
		
		prefuse.core.cd.katz.model.Graph graph = new prefuse.core.cd.katz.model.VariableGraph(vizinhos);
		prefuse.core.cd.katz.control.YenTopKShortestPathsAlg yenAlg = new prefuse.core.cd.katz.control.YenTopKShortestPathsAlg(
				graph, graph.get_vertex(0), graph.get_vertex(5));

		boolean acabou = false;
		while(yenAlg.has_next())
		{
			prefuse.core.cd.katz.model.Path path = yenAlg.next();
			if(path != null)
				System.out.println(path.get_vertices() + ": " + path.get_weight() + ": " + (path.get_vertices().size()-1));
		}	
		
		
		//Calculando o valor do fluxo maximo para colocar no texto da tese
		ServicosHandlerImpl servico = new ServicosHandlerImpl();
		int[][] vizinhosFluxo = new int[7][7];
		Arrays.fill(vizinhosFluxo[0], 0);
		Arrays.fill(vizinhosFluxo[1], 0);
		Arrays.fill(vizinhosFluxo[2], 0);
		Arrays.fill(vizinhosFluxo[3], 0);
		Arrays.fill(vizinhosFluxo[4], 0);
		Arrays.fill(vizinhosFluxo[5], 0);
		Arrays.fill(vizinhosFluxo[6], 0);
		vizinhosFluxo[0][1]=1;
		vizinhosFluxo[0][3]=1;
		
		vizinhosFluxo[1][0]=1;
		vizinhosFluxo[1][2]=1;
		vizinhosFluxo[1][4]=1;
		
		vizinhosFluxo[2][1]=1;
		vizinhosFluxo[2][4]=1;
		vizinhosFluxo[2][6]=1;
		
		vizinhosFluxo[3][0]=1;
		vizinhosFluxo[3][5]=1;
		
		vizinhosFluxo[4][1]=1;
		vizinhosFluxo[4][2]=1;
		vizinhosFluxo[4][5]=1;
		
		vizinhosFluxo[5][3]=1;
		vizinhosFluxo[5][4]=1;
		vizinhosFluxo[5][6]=1;
		
		vizinhosFluxo[6][2]=1;
		vizinhosFluxo[6][5]=1;
		
		
		double[][] pesos = new double[7][7];
		Arrays.fill(pesos[0], 0);
		Arrays.fill(pesos[1], 0);
		Arrays.fill(pesos[2], 0);
		Arrays.fill(pesos[3], 0);
		Arrays.fill(pesos[4], 0);
		Arrays.fill(pesos[5], 0);
		Arrays.fill(pesos[6], 0);
		pesos[0][1]=3;
		pesos[0][3]=2;
		
		pesos[1][0]=3;
		pesos[1][2]=2;
		pesos[1][4]=1;
		
		pesos[2][1]=2;
		pesos[2][4]=0.5;
		pesos[2][6]=0.1;
		
		pesos[3][0]=2;
		pesos[3][5]=4;
		
		pesos[4][1]=1;
		pesos[4][2]=0.5;
		pesos[4][5]=3;
		
		pesos[5][3]=4;
		pesos[5][4]=3;
		pesos[5][6]=1.5;
		
		pesos[6][2]=0.1;
		pesos[6][5]=1.5;
		
		servico.edmondsKarp(vizinhosFluxo, pesos, 0, 6);
		
		String[] letras = {"A","B","C","D","E","F","G","H"};

		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				if(i != j)
					System.out.println(letras[i] + ";" + letras[j] + " = " + servico.edmondsKarp(vizinhosFluxo, pesos, i, j));
			}
		}
		
		String infile = DATA_FILE;
		String label = "name";

		try {
			if (argv.length > 1) {
				infile = argv[0];
				label = argv[1];
			}
			// Chama o m�todo para criar o XML de acordo com a tabela
			// selecionada
			/*
			 * 1 - Mesma Comunidade 2 - Mesmo Projeto 3 - Mesma Publica��o 4 -
			 * Reuni�o 5 - E-mail 6 - Forum 7 - Enquete 8 - Agenda 9 - Atividade
			 * 10 - Tarefa 11 - TODOS Daqui em diante s�o as pesquisas do Lattes
			 * 12 - Produ��o Bibliogr�fica 13 - Orienta��o/Co-Orienta��o 14 -
			 * Participa��o em Projeto de Pesquisa 15 - Produ��o T�cnica 16 -
			 * Participa��o em Bancas 17 - Comiss�es Julgadoras e Comit� de
			 * Eventos
			 */
			boolean arquivo = CriaXML(1, anoInicial, tipoRelacionamentoInicial);
			if (arquivo) {
				UILib.setPlatformLookAndFeel();

				frame = new JFrame("Grafo Radial");

				VisualTable edges = (VisualTable) vis.getGroup(treeEdges);
				gview.firePropertyChange(treeEdges, edges, controleAnualArestas
						.get(anoInicial));

				// Cria a area do grafo
				JPanel painelGrafo = painelp();
				JPanel painelConfiguracao = criaPainelConfiguracao(gview);

				// Cria o menu do Arquivo
				JMenuBar menubar = criaMenus();

				// create a new JSplitPane to present the interface
				JSplitPane split = new JSplitPane();
				split.setLeftComponent(painelGrafo);
				split.setRightComponent(painelConfiguracao);
				split.setOneTouchExpandable(true);
				split.setContinuousLayout(false);
				split.setAutoscrolls(true);
				split.setDividerLocation(950);
				split.setMinimumSize((new Dimension(960, 1000)));

				frame.setJMenuBar(menubar);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(split);
				frame.pack();
				frame.setVisible(true);

			} else {
				System.err.println("Nao foi possivel criar o arquivo!!!!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	public static JMenuBar criaMenus() {
		// Define os menus
		JMenu dataMenu = new JMenu("Arquivo");
		dataMenu.add(new GraphMenuAction("Recarregar", "ctrl 1", gview) {
			protected Graph getGraph() {
				return GraphLib.getGrid(15, 15);
			}
		});
		
		dataMenu.add(new PainelMenuAction("Agrupamentos", "ctrl 2", TabelaAgrupamento.class.getCanonicalName()) {});
		
		dataMenu.add(new PainelMenuAction("Sugest�es", "ctrl 3", TabelaSugestaoRelacionamento.class.getCanonicalName()) {});
		
		JMenuBar menubar = new JMenuBar();
		menubar.add(dataMenu);
		return menubar;
	}

	public static JPanel demo(final String label) {
		// create a new radial tree view
		// tirei o final
		JPanel cima = new JPanel(new BorderLayout());

		gview.addControlListener(new ControlAdapter() {
			public void itemEntered(VisualItem item, MouseEvent e) {
				if (item.canGetString(label))
					title.setText(item.getString(SocialNetwork.NOME_COMPLETO)
							+ " - "
							+ item.getString(SocialNetwork.NOME_UNIVERSIDADE)
							+ "(" + item.getString(SocialNetwork.NOME_GRUPO)
							+ ")");
			}

			public void itemClicked(VisualItem item, MouseEvent e) {
				try {
					//Define os estilos para a exibicao dos textos
					infoPessoal.setText("");
					StyledDocument doc = infoPessoal.getStyledDocument();
					
					Style styleTitulo = infoPessoal.addStyle("titulo", null);
					StyleConstants.setForeground(styleTitulo, Color.BLACK);
					StyleConstants.setBold(styleTitulo, true);
					StyleConstants.setFontSize(styleTitulo, 16);
					
					Style styleSubTitulo = infoPessoal.addStyle("subtitulo", null);
					StyleConstants.setForeground(styleSubTitulo, Color.BLACK);
					StyleConstants.setBold(styleSubTitulo, true);
					StyleConstants.setItalic(styleSubTitulo, true);
					StyleConstants.setFontSize(styleSubTitulo, 14);
					
					Style styleNormal = infoPessoal.addStyle("normal", null);
					StyleConstants.setForeground(styleSubTitulo, Color.BLACK);
					StyleConstants.setBold(styleNormal, false);
					StyleConstants.setItalic(styleNormal, false);
					StyleConstants.setFontSize(styleNormal, 12);
					
					String strElementos = "";
					if(agregadores.get(item.getString(SocialNetwork.NOME_COMPLETO)) != null) {
						Integer numElementos = agregadores.get(item.getString(SocialNetwork.NOME_COMPLETO)).size();
						if(((Integer)item.get("nivel")).equals(Integer.valueOf(1)))
							strElementos = "("+ numElementos +" grupos)";
						else if(((Integer)item.get("nivel")).equals(Integer.valueOf(2)))
							strElementos = "("+ numElementos +" elementos)";
					}
					
					String sourceAgr = item.getString("universidade");
					//Universidade, tipo de relacionamento e numero de relacionamentos
					HashMap<String, HashMap<TipoRelacionamento, Integer>> totalizadorRelacionamentos = new HashMap<String, HashMap<TipoRelacionamento, Integer>>();
					
					//Universidade e numero de relacionamentos
					HashMap<String, Integer> totalizadorAGM = new HashMap<String, Integer>();
					
					Integer totalAGM = 0;
					Integer totalRel = 0;
					
					Integer relInternoAGM = 0;
					Integer relExternoAGM = 0;
					
					Integer relInternoTodos = 0;
					Integer relExternoTodos = 0;
					
					for(Iterator itEdges = ((TableNodeItem)item).edges(); itEdges.hasNext();) {
						TableEdgeItem edge = (TableEdgeItem)itEdges.next();
						if(edge.get("tipoRelacionamento") != null) {
							String dest = (String)edge.getTargetNode().get(SocialNetwork.NOME_COMPLETO);
							String targetAgr = edge.getTargetNode().getString("universidade");
							if(!dest.equals(item.getString(SocialNetwork.NOME_COMPLETO))) {
								if(edge.get("tipoRelacionamento").equals("AGM")) {
									if(targetAgr != null && sourceAgr != null && targetAgr.equals(sourceAgr))
										relInternoAGM++;
									else
										relExternoAGM++;
									
									if(totalizadorAGM.get(dest) == null) totalizadorAGM.put(dest, 0);
									
									totalizadorAGM.put(dest, totalizadorAGM.get(dest)+1);
									totalAGM++;
								} else {
									
									if(targetAgr != null && sourceAgr != null && targetAgr.equals(sourceAgr))
										relInternoTodos++;
									else
										relExternoTodos++;
									
									String tipoRel = (String)edge.get("tipoRelacionamento");
									if(totalizadorRelacionamentos.get(dest) == null) {
										HashMap<TipoRelacionamento, Integer> hash = new HashMap<TipoRelacionamento, Integer>();
										hash.put(TipoRelacionamento.valueOf(tipoRel), 0);
										totalizadorRelacionamentos.put(dest, hash);
									} else if(totalizadorRelacionamentos.get(dest).get(TipoRelacionamento.valueOf(tipoRel)) == null) {
										totalizadorRelacionamentos.get(dest).put(TipoRelacionamento.valueOf(tipoRel), 0);
									}
									Integer total = totalizadorRelacionamentos.get(dest).get(TipoRelacionamento.valueOf(tipoRel));
									totalizadorRelacionamentos.get(dest).put(TipoRelacionamento.valueOf(tipoRel), total+1);
									totalRel++;
								}
							}
						}
					}
					
					String texto = "";
					if(item instanceof TableNodeItem) {
						if(((Integer)item.get("nivel")).equals(Integer.valueOf(1))) {
							//Nivel das Universidades
							//Escreve o nome da Universidade com estilo de titulo
							doc.insertString(doc.getLength(), (item.getString(SocialNetwork.NOME_COMPLETO)+ " " + strElementos + "\n"), styleTitulo);
							
							//Informa o total de relacionamentos pela AGM
							doc.insertString(doc.getLength(), ("Rel. AGM ("+ totalAGM +"): \n"), styleSubTitulo);
							for(Iterator itKey = totalizadorAGM.keySet().iterator(); itKey.hasNext();) {
								String uni = (String)itKey.next();
								texto += " - " + uni + ": " + totalizadorAGM.get(uni) + "; \n";
							}
							doc.insertString(doc.getLength(), texto, styleNormal);
							texto = "";
							
							//Informa o total de relacionamentos por tipo de relacionamento
							doc.insertString(doc.getLength(), ("Rel. por Tipo ("+ totalRel +"): \n"), styleSubTitulo);
							for(Iterator itKey = totalizadorRelacionamentos.keySet().iterator(); itKey.hasNext();) {
								String uni = (String)itKey.next();
								
								//Total por tipo
								Integer totalPorTipo = 0;
								for(Iterator it = totalizadorRelacionamentos.get(uni).keySet().iterator(); it.hasNext();) {
									totalPorTipo += totalizadorRelacionamentos.get(uni).get(it.next());
								}
								
								doc.insertString(doc.getLength(), (" - " + uni + " ("+ totalPorTipo +"): \n"), styleSubTitulo);
								//para cada tipo de relacionamento
								for(Iterator itTipo = totalizadorRelacionamentos.get(uni).keySet().iterator(); itTipo.hasNext();) {
									TipoRelacionamento tipoRel = (TipoRelacionamento)itTipo.next();
									doc.insertString(doc.getLength(), (" => " + tipoRel.name() + ": " + totalizadorRelacionamentos.get(uni).get(tipoRel) + "; \n"), styleNormal);
								}
							}
						} else if(((Integer)item.get("nivel")).equals(Integer.valueOf(2))) {
							//Nivel das Universidades
							//Escreve o nome da Universidade com estilo de titulo
							doc.insertString(doc.getLength(), (item.getString(SocialNetwork.NOME_COMPLETO)+ " " + strElementos + "\n"), styleTitulo);
							
							//Informa o total de relacionamentos pela AGM
							doc.insertString(doc.getLength(), ("Rel. AGM ("+ totalAGM +" [" + relInternoAGM + " Int. e "+ relExternoAGM +" Ext.]): \n"), styleSubTitulo);
							for(Iterator itKey = totalizadorAGM.keySet().iterator(); itKey.hasNext();) {
								String uni = (String)itKey.next();
								texto += " - " + uni + ": " + totalizadorAGM.get(uni) + "; \n";
							}
							doc.insertString(doc.getLength(), texto, styleNormal);
							texto = "";
							
							//Informa o total de relacionamentos por tipo de relacionamento
							doc.insertString(doc.getLength(), ("Rel. por Tipo ("+ totalRel + " [" + relInternoTodos + " Int. e "+ relExternoTodos +" Ext.]): \n"), styleSubTitulo);
							for(Iterator itKey = totalizadorRelacionamentos.keySet().iterator(); itKey.hasNext();) {
								String uni = (String)itKey.next();
								
								//Total por tipo
								Integer totalPorTipo = 0;
								for(Iterator it = totalizadorRelacionamentos.get(uni).keySet().iterator(); it.hasNext();) {
									totalPorTipo += totalizadorRelacionamentos.get(uni).get(it.next());
								}
								
								doc.insertString(doc.getLength(), (" - " + uni + " ("+ totalPorTipo +"): \n"), styleSubTitulo);
								//para cada tipo de relacionamento
								for(Iterator itTipo = totalizadorRelacionamentos.get(uni).keySet().iterator(); itTipo.hasNext();) {
									TipoRelacionamento tipoRel = (TipoRelacionamento)itTipo.next();
									doc.insertString(doc.getLength(), (" => " + tipoRel.name() + ": " + totalizadorRelacionamentos.get(uni).get(tipoRel) + "; \n"), styleNormal);
								}
							}
							
							//Imprime os elementos do grupo
							if(agregadores.get(item.getString(SocialNetwork.NOME_COMPLETO)) != null) {
								doc.insertString(doc.getLength(), (" - Ementos do Grupo: \n"), styleSubTitulo);
								
								for(Iterator itElem = agregadores.get(item.getString(SocialNetwork.NOME_COMPLETO)).iterator(); itElem.hasNext();) {
									TableNode node = (TableNode)itElem.next();
									doc.insertString(doc.getLength(), node.get("nomeCompleto") + "\n", styleNormal);
								}
							}
						} else {
							//Nivel dos Pesquisadores
							texto += item.getString(SocialNetwork.NOME_COMPLETO) + " - "
								  + item.getString(SocialNetwork.NOME_UNIVERSIDADE) + "("
								  + item.getString(SocialNetwork.NOME_GRUPO) + ")";
							doc.insertString(doc.getLength(), texto + "; \n", styleTitulo);
														
							//Informa o total de relacionamentos pela AGM
							texto = "";
							doc.insertString(doc.getLength(), ("Rel. AGM ("+ totalAGM +" [" + relInternoAGM + " Int. e "+ relExternoAGM +" Ext.]): \n"), styleSubTitulo);
							for(Iterator itKey = totalizadorAGM.keySet().iterator(); itKey.hasNext();) {
								String uni = (String)itKey.next();
								texto += " - " + uni + ": " + totalizadorAGM.get(uni) + "; \n";
							}
							doc.insertString(doc.getLength(), texto, styleNormal);
							texto = "";
							
							//Informa o total de relacionamentos por tipo de relacionamento
							doc.insertString(doc.getLength(), ("Rel. por Tipo ("+ totalRel + " [" + relInternoTodos + " Int. e "+ relExternoTodos +" Ext.]): \n"), styleSubTitulo);
							for(Iterator itKey = totalizadorRelacionamentos.keySet().iterator(); itKey.hasNext();) {
								String uni = (String)itKey.next();
								
								//Total por tipo
								Integer totalPorTipo = 0;
								for(Iterator it = totalizadorRelacionamentos.get(uni).keySet().iterator(); it.hasNext();) {
									totalPorTipo += totalizadorRelacionamentos.get(uni).get(it.next());
								}
								
								doc.insertString(doc.getLength(), (" - " + uni + " ("+ totalPorTipo +"): \n"), styleSubTitulo);
								//para cada tipo de relacionamento
								for(Iterator itTipo = totalizadorRelacionamentos.get(uni).keySet().iterator(); itTipo.hasNext();) {
									TipoRelacionamento tipoRel = (TipoRelacionamento)itTipo.next();
									doc.insertString(doc.getLength(), (" => " + tipoRel.name() + ": " + totalizadorRelacionamentos.get(uni).get(tipoRel) + "; \n"), styleNormal);
								}
							}
						}
						
						//Numero de relacionamentos internos e externos
					}
				} catch(Exception ex) {
					System.out.println(ex.getMessage());
				}
			}

			public void itemExited(VisualItem item, MouseEvent e) {
				title.setText(null);
			}
		});

		cima.add(gview);

		Color BACKGROUND = Color.WHITE;
		Color FOREGROUND = Color.DARK_GRAY;
		UILib.setColor(cima, BACKGROUND, FOREGROUND);

		return cima;
	}

	public static void setAno(Integer ano) {
		anoInicial = ano;
	}

	public static void setNivelVisualizacao(Integer nivel) {
		nivelVisualizacao = nivel;
	}

	public static JPanel criaPainelConfiguracao(final SocialNetwork gr) {

		// Cria o Painel para colocar as vairaveis de tempo e as opcoes de nivel
		// de visualizacao para usuario
		JPanel fpanel = new JPanel(new GridBagLayout());

		JPanel panelSlider = new JPanel();
		slider = new JValueSlider("Ano (1990 - 2010)",
				(Number) 1990, (Number) 2010, (Number) 2010);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(slider.getValue().intValue() != checkBoxNivelVisual.getNivelVisualizacaoAtual()) {
					setAno(slider.getValue().intValue());
					// Marca todos os relacionamentos para serem exibidos
					checkBoxConfiguracao.exibirAGM(false);	
					
					System.out.println("Ano Selecionado: " + (slider.getValue().intValue()));
	
					SocialNetwork.exibirRelacionamentos(getNosPorNivel(checkBoxNivelVisual
									.getNivelVisualizacaoAtual()));
				}
			}
		});
		slider.setBackground(Color.WHITE);
		slider.setPreferredSize(new Dimension(300, 30));
		slider.setMinimumSize(new Dimension(300, 30));
		
		BotaoPlay play = new BotaoPlay();
		
		panelSlider.add(slider);
		panelSlider.add(play);
		panelSlider.setPreferredSize(new Dimension(300, 70));
		panelSlider.setMinimumSize(new Dimension(300, 70));

		Box cf = new Box(BoxLayout.Y_AXIS);
		//cf.add(slider);
		cf.add(panelSlider);
		cf.setBorder(BorderFactory.createTitledBorder("An�lise Evolutiva"));
		Container contentPaneEvolutiva = frame.getContentPane();
		contentPaneEvolutiva.add(cf, BorderLayout.CENTER);

		// Define as restricoes para exibir o box da analise evolutiva
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0; // Coluna 1
		c.gridy = 0; // Linha 1
		fpanel.add(cf, c);

		// Cria a BOX de Configuracao
		Box cfCheckBoxConf = new Box(BoxLayout.Y_AXIS);
		checkBoxConfiguracao = new CheckBoxConfiguracao(vis, g);
		cfCheckBoxConf.add(checkBoxConfiguracao);
		Border borderConf = BorderFactory.createTitledBorder("Configura��es");
		cfCheckBoxConf.setBorder(borderConf);
		Container contentPaneConf = frame.getContentPane();
		contentPaneConf.add(cfCheckBoxConf, BorderLayout.CENTER);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0; // Coluna 1
		c.gridy = 1; // Linha 2
		fpanel.add(cfCheckBoxConf, c);

		// Cria a BOX para os TIPOS de RELACIONAMENTO
		Box cfCheckBox = new Box(BoxLayout.Y_AXIS);
		checkBoxTipoRel = new CheckBoxTipoRelacionamento(vis);
		cfCheckBox.add(checkBoxTipoRel);
		Border border = BorderFactory
				.createTitledBorder("Tipos de Relacionamentos");
		cfCheckBox.setBorder(border);
		Container contentPane = frame.getContentPane();
		contentPane.add(cfCheckBox, BorderLayout.CENTER);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0; // Coluna 1
		c.gridy = 2; // Linha 2
		fpanel.add(cfCheckBox, c);
		
		// Cria a BOX para as CENTRALIDADES
		/* -> Centralidades com checkbox
		Box centCheckBox = new Box(BoxLayout.Y_AXIS);
		checkBoxCentralidade = new CheckBoxCentralidade(vis);
		centCheckBox.add(checkBoxCentralidade);
		Border bord = BorderFactory
				.createTitledBorder("Tipos de Centralidade");
		centCheckBox.setBorder(bord);
		Container contentPane1 = frame.getContentPane();
		contentPane1.add(centCheckBox, BorderLayout.CENTER);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0; 
		c.gridy = 5; 
		fpanel.add(centCheckBox, c);*/

		// Cria a BOX para as CENTRALIDADES
		
		//1 - universidade; 2 - grupos; 3 - pesquisadores;
		
		
		JPanel panelSlider2 = new JPanel();
		sliderCentral = new JValueSlider("Local: ", (Number) 0, (Number)100, (Number)0);
		sliderCentral.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//if(sliderCentral.getValue().intValue() != checkBoxNivelVisual.getNivelVisualizacaoAtual()) {
					sliderGlobal.setValue(0);
					destacarNosCentrais(checkBoxNivelVisual.getNivelVisualizacaoAtual(), sliderCentral.getValue().intValue(),0);
					//setAno(sliderCentral.getValue().intValue());
					// Marca todos os relacionamentos para serem exibidos
					//checkBoxConfiguracao.exibirAGM(false);
					//switch (checkBoxNivelVisual.getNivelVisualizacaoAtual()){
					//	case 1: sliderCentral.setSize(SocialNetwork.numberOfUniversidades, SocialNetwork.numberOfUniversidades);
					//	case 2: sliderCentral.setSize(SocialNetwork.numberOfGrupos, SocialNetwork.numberOfGrupos);
					//	case 3: sliderCentral.setSize(SocialNetwork.numberOfPessoas, SocialNetwork.numberOfPessoas);
						//default: total = (Number)SocialNetwork.numberOfUniversidades;
					//	default: sliderCentral.setSize(100,100);
					//}
					//sliderCentral.setMaximumSize();
						
					//System.out.println("Total Selecionado: " + (sliderCentral.getValue().intValue()));
					
				//}
			}
		});
		sliderCentral.setBackground(Color.WHITE);
		sliderCentral.setPreferredSize(new Dimension(300, 30));
		sliderCentral.setMinimumSize(new Dimension(300, 30));
		
		//BotaoPlay play = new BotaoPlay();
		
		panelSlider2.add(sliderCentral);
		//panelSlider2.add(play);
		panelSlider2.setPreferredSize(new Dimension(300, 70));
		panelSlider2.setMinimumSize(new Dimension(300, 70));
		
		//sliderGlobal = new JValueSlider("Global (1 - "+SocialNetwork.numberOfGroups+")",
		sliderGlobal = new JValueSlider("Global ",
				(Number) 0, (Number) 100, (Number)0);
		sliderGlobal.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//if(sliderGlobal.getValue().intValue() != checkBoxNivelVisual.getNivelVisualizacaoAtual()) {
					sliderCentral.setValue(0);
					destacarNosCentrais(checkBoxNivelVisual.getNivelVisualizacaoAtual(),sliderGlobal.getValue().intValue(),1);	
						
					//System.out.println("Total Selecionado: " + (sliderGlobal.getValue().intValue()));
				//}
			}
		});
		sliderGlobal.setBackground(Color.WHITE);
		sliderGlobal.setPreferredSize(new Dimension(300, 30));
		sliderGlobal.setMinimumSize(new Dimension(300, 30));
		
		panelSlider2.add(sliderGlobal);
		panelSlider2.setPreferredSize(new Dimension(300, 70));
		panelSlider2.setMinimumSize(new Dimension(300, 70));

		Box central = new Box(BoxLayout.Y_AXIS);
		//cf.add(slider);
		central.add(panelSlider2);
		central.setBorder(BorderFactory.createTitledBorder("Centralidade"));
		Container contentPaneCentral = frame.getContentPane();
		contentPaneCentral.add(central, BorderLayout.CENTER);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0; 
		c.gridy = 5; 
		fpanel.add(central, c);
		
		// Cria a BOX para os NIVEIS de VISUALIZACAO
		Box cfCheckBoxVisual = new Box(BoxLayout.Y_AXIS);
		cfCheckBoxVisual.setPreferredSize(new Dimension(300, 60));
		cfCheckBoxVisual.setMinimumSize(new Dimension(300, 60));
		checkBoxNivelVisual = new CheckBoxNivelVisualizacao(vis);
		cfCheckBoxVisual.add(checkBoxNivelVisual);
		Border borderVisual = BorderFactory
				.createTitledBorder("N�vel de Visualiza��o");
		cfCheckBoxVisual.setBorder(borderVisual);
		Container contentPaneVisual = frame.getContentPane();
		contentPaneVisual.add(cfCheckBoxVisual, BorderLayout.CENTER);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0; // Coluna 1
		c.gridy = 3; // Linha 3
		fpanel.add(cfCheckBoxVisual, c);

		// Cria a BOX para a selecao de pesquisadores
		Box cfBoxBuscaPesq = new Box(BoxLayout.Y_AXIS);
		boxBuscaPesq = new BoxSelecionaPesquisador(vis, g);
		cfBoxBuscaPesq.add(boxBuscaPesq);
		Border borderBuscaPesq = BorderFactory
				.createTitledBorder("Seleciona Pesquisadores");
		cfBoxBuscaPesq.setBorder(borderBuscaPesq);
		Container contentPaneBuscaPesq = frame.getContentPane();
		contentPaneBuscaPesq.add(cfBoxBuscaPesq, BorderLayout.CENTER);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0; // Coluna 1
		c.gridy = 4; // Linha 3
		fpanel.add(cfBoxBuscaPesq, c);

		// Cria a BOX para exibir as informacoes dos Nos
		Box cfTextArea = new Box(BoxLayout.Y_AXIS);
		cfTextArea.setBorder(BorderFactory.createTitledBorder("Informa��es"));
		infoPessoal = new JTextPane();
		infoPessoal.setFont(new Font("Serif", Font.PLAIN, 12));
		infoPessoal.setEditable(false);
		infoPessoal.setAutoscrolls(true);
		cfTextArea.setAutoscrolls(true);
		JScrollPane scrollPane = new JScrollPane(infoPessoal);
		scrollPane.setAutoscrolls(true);
		scrollPane.setMaximumSize(new Dimension(300, 100));
		scrollPane.setMinimumSize(new Dimension(300, 100));
		scrollPane.setPreferredSize(new Dimension(300, 100));
		cfTextArea.add(scrollPane);
		Container contentPaneTexto = frame.getContentPane();
		contentPaneTexto.add(cfTextArea, BorderLayout.CENTER);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0; // Coluna 1
		c.gridy = 7; // Linha 4
		fpanel.add(cfTextArea, c);

		Color BACKGROUND = Color.WHITE;
		Color FOREGROUND = Color.DARK_GRAY;
		UILib.setColor(fpanel, BACKGROUND, FOREGROUND);

		return fpanel;
	}

	public static JPanel painelp() {
		painelp.add(demo("name"), BorderLayout.CENTER, 0);
		painelp.add(busca(), BorderLayout.SOUTH);

		Color BACKGROUND = Color.WHITE;
		Color FOREGROUND = Color.DARK_GRAY;
		UILib.setColor(painelp, BACKGROUND, FOREGROUND);

		return painelp;
	}

	/**
	 * Swing menu action that loads a graph into the graph viewer.
	 */
	public abstract static class GraphMenuAction extends AbstractAction {
		private SocialNetwork m_view;

		public GraphMenuAction(String name, String accel, SocialNetwork view) {
			m_view = view;
			this.putValue(AbstractAction.NAME, name);
			this.putValue(AbstractAction.ACCELERATOR_KEY, KeyStroke
					.getKeyStroke(accel));
		}

		public void actionPerformed(ActionEvent e) {
			m_view.setGraph(getGraph(), "label");
		}

		protected abstract Graph getGraph();
	}
	
	/**
	 * Swing menu action para carregar outros paineis.
	 */
	public abstract static class PainelMenuAction extends AbstractAction {
		private String obj;

		public PainelMenuAction(String name, String accel, String nomeClasse) {
			obj = nomeClasse;
			this.putValue(AbstractAction.NAME, name);
			this.putValue(AbstractAction.ACCELERATOR_KEY, KeyStroke
					.getKeyStroke(accel));
		}

		public void actionPerformed(ActionEvent e) {
			try {
				Class.forName(obj).newInstance();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void setGraph(Graph g, String label) {
		// update labeling
		DefaultRendererFactory drf = (DefaultRendererFactory) m_vis
				.getRendererFactory();
		((LabelRenderer) drf.getDefaultRenderer()).setTextField(label);

		// update graph
		m_vis.removeGroup(tree);
		VisualGraph vg = m_vis.addGraph(tree, g);
		m_vis.setValue(treeEdges, null, VisualItem.INTERACTIVE, Boolean.FALSE);
		VisualItem f = (VisualItem) vg.getNode(0);
		m_vis.getGroup(Visualization.FOCUS_ITEMS).setTuple(f);
		f.setFixed(false);
	}

	// Para o SearchBox
	public static JPanel busca() {
		String infile = DATA_FILE;
		String buscaPor = "nomeCompleto";

		JPanel busca = new JPanel(new BorderLayout());

		// sq e search n�o eram globais, mas agora s�o
		SearchQueryBinding sq = new SearchQueryBinding((Table) vis
				.getGroup(treeNodes), buscaPor, (SearchTupleSet) vis
				.getGroup(Visualization.SEARCH_ITEMS));
		JSearchPanel search = sq.createSearchPanel();
		search.setShowResultCount(true);
		search.setBorder(BorderFactory.createEmptyBorder(5, 5, 4, 0));
		search.setFont(FontLib.getFont("Tahoma", Font.PLAIN, 11));
		// Serve para colocar um espa�o logo ao lado do Combo que serve para
		// mostrar
		// o mouse over dos nomes do sociograma.
		// AQUI TINHA A CRIA��O DA VARI�VEL TITLE
		// A "Dimension" original era de 350, mas foi alterada para a janela
		// ficar
		// mais apresent�vel.
		title.setPreferredSize(new Dimension(250, 20));
		title.setVerticalAlignment(SwingConstants.BOTTOM);
		title.setBorder(BorderFactory.createEmptyBorder(3, 0, 0, 0));
		title.setFont(FontLib.getFont("Tahoma", Font.PLAIN, 16));

		Box caixa = new Box(BoxLayout.X_AXIS);
		caixa.add(title);
		caixa.add(Box.createHorizontalGlue());
		caixa.add(search);
		caixa.add(Box.createHorizontalStrut(3));

		busca.add(caixa, BorderLayout.SOUTH);

		Color BACKGROUND = Color.WHITE;
		Color FOREGROUND = Color.DARK_GRAY;
		UILib.setColor(busca, BACKGROUND, FOREGROUND);

		return busca;
	}

	// ------------------------------------------------------------------------
	/**
	 * Set node fill colors
	 */
	public static class NodeColorAction extends ColorAction {
		public NodeColorAction(String group) {
			super(group, VisualItem.FILLCOLOR, ColorLib.rgba(255, 255, 255, 0));
			add("_hover", ColorLib.gray(220, 230));
			add("ingroup('_search_')", ColorLib.rgb(255, 190, 190));
			add("ingroup('_focus_')", ColorLib.rgb(198, 229, 229));
		}

	} // end of inner class NodeColorAction

	/**
	 * Set node text colors
	 */
	public static class TextColorAction extends ColorAction {
		public TextColorAction(String group) {
			super(group, VisualItem.TEXTCOLOR, ColorLib.gray(0));
			add("_hover", ColorLib.rgb(255, 0, 0));
		}
	} // end of inner class TextColorAction

	// public static boolean CriaXML(int modo, int tabela) {
	public static boolean CriaXML(int modo, Integer filterAno,
			TipoRelacionamento tipoRelacionamento) {

		numberOfGroups = 0;
		//numberOfPessoas = 0;
		//numberOfUniversidades = 0;
		try {

			PrintWriter fo = new PrintWriter(new FileOutputStream(
					"/home/phillipe/NetBeansProjects/plscience-ecos/web/files/socialNetwork/rede_social.xml"));
			// Cria o arquivo XML

			FileReader reader = new FileReader("/home/phillipe/NetBeansProjects/plscience-ecos/web/files/socialNetwork/perfil_completo_centralidade.txt");
			BufferedReader buffReader = new BufferedReader(reader);
			String linha;

			Map pessoas = new HashMap();
			Map mapLinksFotos = new HashMap();
			Map pessoasAno = new HashMap();
			
			Map pessoaCentralidadeLocal = new HashMap();
			Map pessoaCentralidadeGlobal = new HashMap();
			
			Map<String, Integer> pessoasToID = new HashMap<String, Integer>();
			Collection<Integer> controlePessoas = new ArrayList<Integer>();

			while ((linha = buffReader.readLine()) != null) {

				StringTokenizer tokens = new StringTokenizer(linha, "\t");
				String id = tokens.nextToken();
				String nome = tokens.nextToken();
				String ano = tokens.nextToken();				
				String linkFoto = tokens.nextToken();
				
				String centralidadeLocal = tokens.nextToken();
				String centralidadeGlobal = tokens.nextToken();
				
				mapLinksFotos.put(id, linkFoto);
				pessoas.put(id, TiraAcento(nome));
				pessoasAno.put(id, ano);
				pessoasToID.put(TiraAcento(nome), Integer.valueOf(id));
				
				pessoaCentralidadeLocal.put(id, centralidadeLocal);
				pessoaCentralidadeGlobal.put(id, centralidadeGlobal);
			}

			buffReader.close();
			reader.close();

			reader = new FileReader("/home/phillipe/NetBeansProjects/plscience-ecos/web/files/socialNetwork/Grupos_centralidade.txt");
			//reader = new FileReader("Grupos.txt");
			buffReader = new BufferedReader(reader);
			linha = "";

			Map grupos = new HashMap();
			Map<Integer, Set> gruposPorPessoa = new HashMap<Integer, Set>();
			
			Map<Integer, String> centralidadeLocalGrupo = new HashMap<Integer, String>();
			Map<Integer, String> centralidadeGlobalGrupo = new HashMap<Integer, String>();

			linha = buffReader.readLine();

			numberOfGroups = Integer.valueOf(linha);

			while ((linha = buffReader.readLine()) != null) {

				StringTokenizer tokens = new StringTokenizer(linha, "\t");
				String grupo = tokens.nextToken();
				String pessoa = tokens.nextToken();
				
				String grupoCentralidadeLocal = tokens.nextToken();
				String grupoCentralidadeGlobal = tokens.nextToken();
				
				//System.out.println(grupo+"	"+pessoa+"	"+grupoCentralidadeLocal+"	"+grupoCentralidadeGlobal);
				//System.out.println(TiraAcento(pessoa));
				grupos.put(TiraAcento(pessoa), grupo);
				//System.out.println(Integer.valueOf(grupo));
				centralidadeLocalGrupo.put(Integer.valueOf(grupo), grupoCentralidadeLocal);
				centralidadeGlobalGrupo.put(Integer.valueOf(grupo), grupoCentralidadeGlobal);
				
				if (gruposPorPessoa.get(Integer.valueOf(grupo)) == null)
					gruposPorPessoa.put(Integer.valueOf(grupo), new TreeSet());
					gruposPorPessoa.get(Integer.valueOf(grupo)).add(TiraAcento(pessoa));	
			}

			//System.out.println(centralidadeLocalGrupo.get(2));
			buffReader.close();
			reader.close();

			/* UNIVERSIDADE */
			reader = new FileReader("/home/phillipe/NetBeansProjects/plscience-ecos/web/files/socialNetwork/universidade.txt");
			buffReader = new BufferedReader(reader);
			linha = "";

			Map universidade = new HashMap();
				
			while ((linha = buffReader.readLine()) != null) {

				StringTokenizer tokens = new StringTokenizer(linha, "\t");
				String pessoa = tokens.nextToken();
				String siglaUniversidade = tokens.nextToken();
				universidade.put(TiraAcento(pessoa), siglaUniversidade);
				
				//numberOfUniversidades++;
			}

			buffReader.close();
			reader.close();

			fo.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			fo.println("<!--  An excerpt of an egocentric social network  -->");
			fo.println("<graphml xmlns=\"http://graphml.graphdrawing.org/xmlns\">");
			fo.println("<graph edgedefault=\"undirected\">");
			fo.println("<!-- data schema -->");
			fo.println("<key id=\"name\" for=\"node\" attr.name=\"name\" attr.type=\"string\"/>");
			fo.println("<key id=\"nomeCompleto\" for=\"node\" attr.name=\"nomeCompleto\" attr.type=\"string\"/>");
			fo.println("<key id=\"nomeCompletoComEnter\" for=\"node\" attr.name=\"nomeCompletoComEnter\" attr.type=\"string\"/>");
			fo.println("<key id=\"anoNaRede\" for=\"node\" attr.name=\"anoNaRede\" attr.type=\"integer\"/>");
			fo.println("<key id=\"agregador\" for=\"node\" attr.name=\"agregador\" attr.type=\"string\"/>"); // indica quem eh o grupo agregador do no
			fo.println("<key id=\"nivel\" for=\"node\" attr.name=\"nivel\" attr.type=\"integer\"/>"); // indica qual o nivel de visualizacao do o
			fo.println("<key id=\"image\" for=\"node\" attr.name=\"image\" attr.type=\"string\"/>"); // indica a URL da imagem
			
			fo.println("<key id=\"centralidadeLocal\" for=\"node\" attr.name=\"centralidadeLocal\" attr.type=\"string\"/>");
			fo.println("<key id=\"centralidadeGlobal\" for=\"node\" attr.name=\"centralidadeGlobal\" attr.type=\"string\"/>");
			
			fo.println("<key id=\"group\" for=\"node\" attr.name=\"group\" attr.type=\"string\"/>");
			fo.println("<key id=\"universidade\" for=\"node\" attr.name=\"universidade\" attr.type=\"string\"/>");
			fo.println("<key id=\"gruposUniversidade\" for=\"node\" attr.name=\"gruposUniversidade\" attr.type=\"string\"/>");
			fo.println("<key id=\"weight\" for=\"edge\" attr.name=\"weight\" attr.type=\"double\"/>");
			fo.println("<key id=\"anoRelacionamento\" for=\"edge\" attr.name=\"anoRelacionamento\" attr.type=\"integer\"/>");
			fo.println("<key id=\"tipoRelacionamento\" for=\"edge\" attr.name=\"tipoRelacionamento\" attr.type=\"string\"/>");

			// EXIBIR SOMENTE OS PESQUISADORES QUE POSSUEM RELACIONAMENTOS COM
			// OUTROS PESQUISADORES
			TreeMap<String, Boolean> temRelacionamento = new TreeMap<String, Boolean>();
			reader = new FileReader("/home/phillipe/NetBeansProjects/plscience-ecos/web/files/socialNetwork/relacionamentos.txt");
			buffReader = new BufferedReader(reader);
			linha = "";

			while ((linha = buffReader.readLine()) != null) {

				StringTokenizer tokens = new StringTokenizer(linha, ";");

				String p1 = "";
				String p2 = "";
				int i = 0;

				while (tokens.hasMoreTokens()) {
					String str = tokens.nextToken();
					if (i == 0)
						p1 = str;
					if (i == 1)
						p2 = str;
					i++;
				}
			}

			TreeMap<String, String> aggGrupos = new TreeMap<String, String>();
			TreeSet<String> aggUniversidades = new TreeSet<String>();

			reader = new FileReader("/home/phillipe/NetBeansProjects/plscience-ecos/web/files/socialNetwork/perfil_completo_centralidade.txt");
			buffReader = new BufferedReader(reader);
			linha = "";

			while ((linha = buffReader.readLine()) != null) {

				StringTokenizer tokens = new StringTokenizer(linha, "\t");

				String id = tokens.nextToken();
			
				fo.println("<node id=\"" + id + "\">");
				fo.println("<data key=\"name\">" + id + " (" + grupos.get(pessoas.get(id)) + ")</data>");
				fo.println("<data key=\"nomeCompleto\">" + pessoas.get(id) + "</data>");
				
				String[] numNomes = ((String)pessoas.get(id)).split(" ");
				String nomeCompletoComEnter = ((String)pessoas.get(id));
				if(numNomes.length >= 2) {
					//primeiro + \n + ultimo					
					nomeCompletoComEnter = numNomes[0] + "\n" + numNomes[numNomes.length-1];
				}
				
				fo.println("<data key=\"nomeCompletoComEnter\">" + nomeCompletoComEnter + " (" + grupos.get(pessoas.get(id)) + ")</data>");
				fo.println("<data key=\"anoNaRede\">" + pessoasAno.get(id) + "</data>");
				fo.println("<data key=\"agregador\">" + universidade.get(pessoas.get(id)) + ";"	+ grupos.get(pessoas.get(id)) + "</data>");
				fo.println("<data key=\"nivel\">" + 3 + "</data>"); // nivel de exibicao dos pesquisadores
				
				fo.println("<data key=\"image\">" + mapLinksFotos.get(id) + "</data>"); // foto do pesquisador
				
				//C�digo Incluido - Pedro============
				fo.println("<data key=\"centralidadeLocal\">"+ pessoaCentralidadeLocal.get(id) + "</data>");
				fo.println("<data key=\"centralidadeGlobal\">"+ pessoaCentralidadeGlobal.get(id) + "</data>");
				//===================================
				fo.println("<data key=\"group\">" + grupos.get(pessoas.get(id)) + "</data>");
				fo.println("<data key=\"universidade\">" + universidade.get(pessoas.get(id)) + "</data>");
				fo.println("<data key=\"gruposUniversidade\">" + universidade.get(pessoas.get(id)) + grupos.get(pessoas.get(id)) + "</data>");
				fo.println("</node>");
				controlePessoas.add(Integer.valueOf(id));

				// Adiciona a chave dos agregadores dos grupos
				aggGrupos.put(String.valueOf((String) universidade.get(pessoas.get(id))	+ ";" + (String) grupos.get(pessoas.get(id))),
						(String) universidade.get(pessoas.get(id)));

				// Adiciona a chave dos agregadores das universidades
				aggUniversidades.add((String) universidade.get(pessoas.get(id)));
				// }
				// }
			}

			buffReader.close();
			reader.close();

			// Cria os nos para os agregadores dos grupos
			for (Iterator<String> it = aggGrupos.keySet().iterator(); it
					.hasNext();) {
				try {
					String idGrupo = it.next();
					String aggGrupo = aggGrupos.get(idGrupo);
					fo.println("<node id=\"" + idGrupo + "\">");
					fo.println("<data key=\"name\">" + idGrupo + "</data>");
					fo.println("<data key=\"nomeCompleto\">" + idGrupo + "</data>");
					fo.println("<data key=\"anoNaRede\">"
							+ getMenorAnoGrupo(idGrupo, pessoasToID, pessoasAno,
									grupos, universidade) + "</data>"); // Ano no qual o primeiro elemento aparece na rede
					fo.println("<data key=\"agregador\">" + aggGrupo + "</data>");
					fo.println("<data key=\"nivel\">" + 2 + "</data>"); // nivel de exibicao dos pesquisadores
					
					Integer id = Integer.valueOf(idGrupo.substring(idGrupo.indexOf(";")+1));
					//System.out.println(id);
					fo.println("<data key=\"centralidadeLocal\">"+ centralidadeLocalGrupo.get(id) + "</data>");
					fo.println("<data key=\"centralidadeGlobal\">"+ centralidadeGlobalGrupo.get(id) + "</data>");
					
					fo.println("<data key=\"group\">" + idGrupo.split(";")[1] + "</data>");
					fo.println("<data key=\"universidade\">" + aggGrupo + "</data>");
					fo.println("<data key=\"gruposUniversidade\">" + idGrupo + "</data>");
					fo.println("</node>");
				} catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}

			// Cria os nos para os agregadores das universidades
			for (Iterator<String> it = aggUniversidades.iterator(); it
					.hasNext();) {
				String idUni = it.next();
				fo.println("<node id=\"" + idUni + "\">");
				fo.println("<data key=\"name\">" + idUni + "</data>");
				fo.println("<data key=\"nomeCompleto\">" + idUni + "</data>");
				fo.println("<data key=\"anoNaRede\">" + 1950 + "</data>"); // Ano no qual o primeiro elemento aparece na rede
				fo.println("<data key=\"agregador\">" + "</data>");
				fo.println("<data key=\"nivel\">" + 1 + "</data>"); // nivel de exibicao dos pesquisadores
				fo.println("<data key=\"group\">" + idUni + "</data>");
				fo.println("<data key=\"universidade\">" + idUni + "</data>");
				fo.println("<data key=\"gruposUniversidade\">" + idUni + "</data>");
				fo.println("</node>");
			}

			reader = new FileReader("/home/phillipe/NetBeansProjects/plscience-ecos/web/files/socialNetwork/relacionamentos.txt");
			buffReader = new BufferedReader(reader);
			linha = "";

			while ((linha = buffReader.readLine()) != null) {

				StringTokenizer tokens = new StringTokenizer(linha, ";");

				String p1 = "";
				String p2 = "";
				int i = 0;
				String frequencia = "";
				String anoRelacionamento = "";
				String tipoRel = "";

				while (tokens.hasMoreTokens()) {
					String str = tokens.nextToken();
					if (i == 0)
						p1 = str;
					if (i == 1)
						p2 = str;
					if (i == 2)
						frequencia = str;
					if (i == 3)
						anoRelacionamento = str;
					if (i == 4)
						tipoRel = str;
					i++;
				}

				// Carrega a variavel de controle da evolucao temporal dos
				// relacionamentos
				if (!tipoRel.equals(TipoRelacionamento.VAZIO.name())) {
					
					fo.println("<edge source=\"" + p1 + "\" target=\"" + p2
							+ "\">");
					fo
							.println("<data key=\"weight\">" + frequencia
									+ "</data>");
					fo.println("<data key=\"anoRelacionamento\">"
							+ anoRelacionamento + "</data>");
					fo.println("<data key=\"tipoRelacionamento\">" + tipoRel
							+ "</data>");
					fo.println("</edge>");
				}

			}

			// Le o arquivo da arvore geradora minima para organizar o grafo
			// A informacao desse tipo de aresta eh utilizada no metodo
			// ForceDirectedLayout.getSpringCoefficient
			reader = new FileReader("/home/phillipe/NetBeansProjects/plscience-ecos/web/files/socialNetwork/relacionamentosArvoreMinima.txt");
			buffReader = new BufferedReader(reader);
			linha = "";

			Map<Integer, Set> relNoArquivo = new HashMap<Integer, Set>();

			while ((linha = buffReader.readLine()) != null) {

				StringTokenizer tokens = new StringTokenizer(linha, ";");

				String p1 = "";
				String p2 = "";
				int i = 0;
				String frequencia = "";
				String anoRelacionamento = "";
				String tipoRel = "";

				while (tokens.hasMoreTokens()) {
					String str = tokens.nextToken();
					if (i == 0)
						p1 = str;
					if (i == 1)
						p2 = str;
					if (i == 2)
						frequencia = str;
					if (i == 3)
						anoRelacionamento = str;
					if (i == 4)
						tipoRel = "AGM";
					i++;
				}

				if (relNoArquivo.get(Integer.valueOf(p1)) == null)
					relNoArquivo.put(Integer.valueOf(p1), new TreeSet());
				if (relNoArquivo.get(Integer.valueOf(p2)) == null)
					relNoArquivo.put(Integer.valueOf(p2), new TreeSet());
				relNoArquivo.get(Integer.valueOf(p1)).add(Integer.valueOf(p2));
				relNoArquivo.get(Integer.valueOf(p2)).add(Integer.valueOf(p1));

				// Verifica se as pessoas do relacionamento jah fazem parte da
				// rede
				if (controlePessoas.contains(Integer.valueOf(p1))
						&& controlePessoas.contains(Integer.valueOf(p2))) {
					fo.println("<edge source=\"" + p1 + "\" target=\"" + p2
							+ "\">");
					fo
							.println("<data key=\"weight\">" + frequencia
									+ "</data>");
					fo.println("<data key=\"anoRelacionamento\">"
							+ anoRelacionamento + "</data>");
					fo.println("<data key=\"tipoRelacionamento\">" + tipoRel
							+ "</data>");
					fo.println("</edge>");
				}
			}

			// Criar os relacionamentos entre totos os elementos dos grupos que
			// ainda nao estao relacionados
			for (Iterator it = gruposPorPessoa.keySet().iterator(); it
					.hasNext();) {
				Integer numGrupo = (Integer) it.next();
				Set setGrupos = gruposPorPessoa.get(numGrupo);
				// para cada par de elementos do grupo criar o relacionamento
				// caso esses pesquisadores nao estejam relacionados
				int relNoGrupo = 0;
				for (int i = 0; i < setGrupos.toArray().length - 1; i++) {
					for (int j = i; j < setGrupos.toArray().length; j++) {
						// Pessoas que fazem parte do mesmo grupo, estao na
						// mesma universidade e nao possuem o relacionamento no
						// arquivo de relacionamentos
						if (((relNoArquivo.get(pessoasToID.get(setGrupos
								.toArray()[i])) == null || !relNoArquivo
								.get(pessoasToID.get(setGrupos.toArray()[i]))
								.contains(
										pessoasToID.get(setGrupos.toArray()[j]))) || (relNoArquivo
								.get(pessoasToID.get(setGrupos.toArray()[j])) == null || !relNoArquivo
								.get(pessoasToID.get(setGrupos.toArray()[j]))
								.contains(
										pessoasToID.get(setGrupos.toArray()[i]))))
								&& universidade
										.get(
												String.valueOf(setGrupos
														.toArray()[j]))
										.equals(
												universidade.get(String
														.valueOf(setGrupos
																.toArray()[i])))
						/* && relNoGrupo < 80 */) {
							fo.println("<edge source=\""
									+ pessoasToID.get(setGrupos.toArray()[i])
									+ "\" target=\""
									+ pessoasToID.get(setGrupos.toArray()[j])
									+ "\">");
							fo.println("<data key=\"weight\">" + "-1"
									+ "</data>");
							fo.println("</edge>");
							relNoGrupo++;
						}
					}
				}
			}

			// Recuperar todas as universidades e seus respectivos grupos
			Map<String, HashSet<String>> gruposDaUniversidade = getGruposUniversidade(
					universidade, grupos);
			Set setNomeUniversidades = gruposDaUniversidade.keySet();
			// para cada universidade, criar um relacionamento entre cada um dos
			// seus grupos
			for (Iterator it = setNomeUniversidades.iterator(); it.hasNext();) {
				Map<String, TreeSet<String>> relEntreGrupos = new HashMap<String, TreeSet<String>>();
				String nomeUni = (String) it.next();
				HashSet<String> setGrupos = gruposDaUniversidade.get(nomeUni);
				// percorre cada um dos grupos
				for (Iterator itGrupos1 = setGrupos.iterator(); itGrupos1
						.hasNext();) {
					String numGrupo1 = (String) itGrupos1.next();
					for (Iterator itGrupos2 = setGrupos.iterator(); itGrupos2
							.hasNext();) {
						String numGrupo2 = (String) itGrupos2.next();

						if (relEntreGrupos.get(numGrupo1) == null)
							relEntreGrupos
									.put(numGrupo1, new TreeSet<String>());
						if (relEntreGrupos.get(numGrupo2) == null)
							relEntreGrupos
									.put(numGrupo2, new TreeSet<String>());

						if (!numGrupo1.equals(numGrupo2)
								&& relEntreGrupos.get(numGrupo1).size() <= 2
								&& relEntreGrupos.get(numGrupo2).size() <= 2) {

							relEntreGrupos.get(numGrupo1).add(numGrupo2);
							relEntreGrupos.get(numGrupo2).add(numGrupo1);

							String nomePessoa1 = getPessoaGrupo(numGrupo1,
									nomeUni, universidade, grupos);
							String nomePessoa2 = getPessoaGrupo(numGrupo2,
									nomeUni, universidade, grupos);
							fo.println("<edge source=\""
									+ pessoasToID.get(nomePessoa1)
									+ "\" target=\""
									+ pessoasToID.get(nomePessoa2) + "\">");
							fo.println("<data key=\"weight\">" + "-2"
									+ "</data>");
							fo.println("</edge>");
						}
					}
				}
			}
			// Recuperar os grupos de cada universidade
			// Criar um relacionamento entre cada um dos grupos

			fo.println("</graph>");
			fo.println("</graphml>");

			buffReader.close();
			reader.close();

			fo.flush();

			fo.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	private static Integer getMenorAnoGrupo(String idGrupo, Map pessoas,
			Map pessoasAno, Map grupos, Map universidades) {
		String numeroGrupo = idGrupo.split(";")[1];
		String nomeUni = idGrupo.split(";")[0];
		Integer menorAno = Integer.MAX_VALUE;
		for (Iterator it = grupos.keySet().iterator(); it.hasNext();) {
			String nomePessoa = (String) it.next();
			String grupoPessoa = (String) grupos.get(nomePessoa);
			if (grupoPessoa.equals(numeroGrupo)
					&& universidades.get(nomePessoa).equals(nomeUni)) {
				Integer idPessoa = (Integer) pessoas.get(nomePessoa);
				Integer ano = Integer.valueOf(String.valueOf(pessoasAno
						.get(String.valueOf(idPessoa))));
				if (ano < menorAno)
					menorAno = ano;
			}
		}
		return menorAno;
	}

	/**
	 * 
	 * @param nomeUni
	 * @param uni:
	 *            Map uni<Nome da Pessoa, Sigla da Universidade>
	 * @param grupos:
	 *            Map grupo<Nome da Pessoa, Numero do Grupo>
	 * @return
	 */
	private static Map<String, HashSet<String>> getGruposUniversidade(Map uni,
			Map grupos) {
		// percorre o map de grupos
		Map<String, HashSet<String>> gruposDaUniversidade = new HashMap<String, HashSet<String>>();
		Set setNomePessoas = grupos.keySet();
		for (Iterator it = setNomePessoas.iterator(); it.hasNext();) {
			String nomePessoa = (String) it.next();
			String numeroGrupo = (String) grupos.get(nomePessoa);
			String siglaUni = (String) uni.get(nomePessoa);

			if (gruposDaUniversidade.get(siglaUni) == null)
				gruposDaUniversidade.put(siglaUni, new HashSet<String>());

			gruposDaUniversidade.get(siglaUni).add(numeroGrupo);

		}
		return gruposDaUniversidade;
	}

	private static String getPessoaGrupo(String numeroGrupo, String nomeUni,
			Map uni, Map grupos) {
		Set setNomePessoas = grupos.keySet();
		for (Iterator it = setNomePessoas.iterator(); it.hasNext();) {
			String nomePessoa = (String) it.next();
			String numGrupo = (String) grupos.get(nomePessoa);
			String siglaUni = (String) uni.get(nomePessoa);
			if (numGrupo.equals(numeroGrupo) && siglaUni.equals(nomeUni))
				return nomePessoa;
		}
		return null;
	}
    
    public class GrafoRadialForceLayout extends ForceDirectedLayout {
        
        public GrafoRadialForceLayout(String data, boolean enforceBounds) {
            super(data, enforceBounds,false);
        }
        
        //Retorna a massa igual a 2 para que os objetos com a foto se separem
        protected float getMassValue(VisualItem item) {
        	if(item.isVisible() && item instanceof TableNodeItem) {
	        	Integer nivel = (Integer)item.get("nivel");
	        	if(nivel.equals(Integer.valueOf(3))) {
		        	Collection collFiltroPesq = getNosPorNivel(nivel);
		        	
		        	if(collFiltroPesq != null && !collFiltroPesq.isEmpty()) {
		        		if(item.getSize() > 1.0)
		        			return 10f;
		        		else
		        			return 4f;
		        	}
	        	}
        	}
            return super.getMassValue(item);
        }
    }

} // end of class RadialGraphView

/**
 * Layout algorithm that computes a convex hull surrounding aggregate items and
 * saves it in the "_polygon" field.
 */
class AggregateLayout3 extends Layout {

	private int m_margin = 5; // convex hull pixel margin

	private double[] m_pts; // buffer for computing convex hulls

	public AggregateLayout3(String aggrGroup) {
		super(aggrGroup);
	}

	/**
	 * @see edu.berkeley.guir.prefuse.action.Action#run(edu.berkeley.guir.prefuse.ItemRegistry,
	 *      double)
	 */

	public void run(double frac) {

		AggregateTable aggr = (AggregateTable) m_vis.getGroup(m_group);
		// do we have any to process?
		int num = aggr.getTupleCount();
		if (num == 0)
			return;
		// update buffers
		int maxsz = 0;
		for (Iterator aggrs = aggr.tuples(); aggrs.hasNext();)
			maxsz = Math.max(maxsz, 4 * 2 * ((AggregateItem) aggrs.next())
					.getAggregateSize());
		if (m_pts == null || maxsz > m_pts.length) {
			m_pts = new double[maxsz];
		}
		// compute and assign convex hull for each aggregate
		Iterator aggrs = m_vis.visibleItems(m_group);
		while (aggrs.hasNext()) {
			AggregateItem aitem = (AggregateItem) aggrs.next();

			int idx = 0;
			if (aitem.getAggregateSize() == 0)
				continue;
			VisualItem item = null;
			Iterator iter = aitem.items();
			int numItens = 0;
			while (iter.hasNext()) {
				item = (VisualItem) iter.next();
				if (item.isVisible()) {
					addPoint(m_pts, idx, item, m_margin);
					idx += 2 * 4;
					numItens++;
				}
			}
			
			if(numItens > 0 && numItens < 4 && 
					SocialNetwork.checkBoxNivelVisual != null && SocialNetwork.checkBoxNivelVisual.getNivelVisualizacaoAtual() != 1) {
				idx = 0;
				iter = aitem.items();
				while (iter.hasNext()) {
					item = (VisualItem) iter.next();
					if (item.isVisible()) {
						addPointRetangullo(m_pts, idx, item, m_margin);
						idx += 2 * 4;
					}
				}
			}
			// if no aggregates are visible, do nothing
			if (idx == 0)
				continue;
			// compute convex hull
			double[] nhull = GraphicsLib.convexHull(m_pts, idx);
			// prepare viz attribute array
			float[] fhull = (float[]) aitem.get(VisualItem.POLYGON);
			if (fhull == null || fhull.length < nhull.length)
				fhull = new float[nhull.length];
			else if (fhull.length > nhull.length)
				fhull[nhull.length] = Float.NaN;
			// copy hull values
			for (int j = 0; j < nhull.length; j++)
				fhull[j] = (float) nhull[j];
			aitem.set(VisualItem.POLYGON, fhull);
			aitem.setValidated(false); // force invalidation
		}
	}

	private static void addPoint(double[] pts, int idx, VisualItem item,
			int growth) {
		Rectangle2D b = item.getBounds();
		double minX = (b.getMinX()) - growth, minY = (b.getMinY()) - growth;
		double maxX = (b.getMaxX()) + growth, maxY = (b.getMaxY()) + growth;
		pts[idx] = minX;
		pts[idx + 1] = minY;
		pts[idx + 2] = minX;
		pts[idx + 3] = maxY;
		pts[idx + 4] = maxX;
		pts[idx + 5] = minY;
		pts[idx + 6] = maxX;
		pts[idx + 7] = maxY;
	}
	
	private static void addPointRetangullo(double[] pts, int idx, VisualItem item,
			int growth) {
		Rectangle2D b = item.getBounds();
		double minX = (b.getMinX()) - growth*15, minY = (b.getMinY()) - growth*5;
		double maxX = (b.getMaxX()) + growth*15, maxY = (b.getMaxY()) + growth*5;
		pts[idx] = minX;
		pts[idx + 1] = minY;
		pts[idx + 2] = minX;
		pts[idx + 3] = maxY;
		pts[idx + 4] = maxX;
		pts[idx + 5] = minY;
		pts[idx + 6] = maxX;
		pts[idx + 7] = maxY;
	}

} // end of class AggregateLayout

/**
 * Interactive drag control that is "aggregate-aware"
 */
class AggregateDragControl3 extends ControlAdapter {

	private VisualItem activeItem;

	protected Point2D down = new Point2D.Double();

	protected Point2D temp = new Point2D.Double();

	protected boolean dragged;

	/**
	 * Creates a new drag control that issues repaint requests as an item is
	 * dragged.
	 */
	public AggregateDragControl3() {
	}

	// Reconstruir o layout do Grafo
	public void mousePressed(MouseEvent e) {
		if (Integer.valueOf(e.getClickCount()).equals(Integer.valueOf(2))) {
			Display d = (Display) e.getSource();
			for (Iterator it = d.getVisualization().items("treeNodes"); it.hasNext();) {
				VisualItem visualItem = (VisualItem) it.next();
				setFixed(visualItem, false);
			}
			
			for (Iterator it = d.getVisualization().items("universidade"); it.hasNext();) {
				VisualItem visualItem = (VisualItem) it.next();
				setFixed(visualItem, false);
			}
		}
	}

	/**
	 * @see prefuse.controls.Control#itemEntered(prefuse.visual.VisualItem,
	 *      java.awt.event.MouseEvent)
	 */
	// Passar o mouse sobre os objetos do grafo
	public void itemEntered(VisualItem item, MouseEvent e) {
		Display d = (Display) e.getSource();
		d.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		activeItem = item;
		if (!(item instanceof AggregateItem))
			setFixed(item, true);
	}

	/**
	 * @see prefuse.controls.Control#itemExited(prefuse.visual.VisualItem,
	 *      java.awt.event.MouseEvent)
	 */
	// retirar o mouse dos objetos do grafo
	public void itemExited(VisualItem item, MouseEvent e) {
		if (activeItem == item) {
			activeItem = null;
			// setFixed(item, false);
		}
		Display d = (Display) e.getSource();
		d.setCursor(Cursor.getDefaultCursor());
	}

	/**
	 * @see prefuse.controls.Control#itemPressed(prefuse.visual.VisualItem,
	 *      java.awt.event.MouseEvent)
	 */
	public void itemPressed(VisualItem item, MouseEvent e) {
		if (!SwingUtilities.isLeftMouseButton(e))
			return;
		dragged = false;
		Display d = (Display) e.getComponent();
		d.getAbsoluteCoordinate(e.getPoint(), down);
		if (item instanceof AggregateItem)
			setFixed(item, true);
	}

	/**
	 * @see prefuse.controls.Control#itemReleased(prefuse.visual.VisualItem,
	 *      java.awt.event.MouseEvent)
	 */
	public void itemReleased(VisualItem item, MouseEvent e) {
		if (!SwingUtilities.isLeftMouseButton(e))
			return;
		if (dragged) {
			activeItem = null;
			// setFixed(item, false);
			dragged = false;
		}
	}

	/**
	 * @see prefuse.controls.Control#itemDragged(prefuse.visual.VisualItem,
	 *      java.awt.event.MouseEvent)
	 */
	public void itemDragged(VisualItem item, MouseEvent e) {
		if (!SwingUtilities.isLeftMouseButton(e))
			return;
		dragged = true;
		Display d = (Display) e.getComponent();
		d.getAbsoluteCoordinate(e.getPoint(), temp);
		double dx = temp.getX() - down.getX();
		double dy = temp.getY() - down.getY();

		move(item, dx, dy);

		down.setLocation(temp);
	}

	protected static void setFixed(VisualItem item, boolean fixed) {
		if (item instanceof AggregateItem) {
			Iterator items = ((AggregateItem) item).items();
			while (items.hasNext()) {
				setFixed((VisualItem) items.next(), fixed);
			}
		} else {
			item.setFixed(fixed);
		}
	}

	protected static void move(VisualItem item, double dx, double dy) {
		if (item instanceof AggregateItem) {
			Iterator items = ((AggregateItem) item).items();
			while (items.hasNext()) {
				move((VisualItem) items.next(), dx, dy);
			}
		} else {
			double x = item.getX();
			double y = item.getY();
			item.setStartX(x);
			item.setStartY(y);
			item.setX(x + dx);
			item.setY(y + dy);
			item.setEndX(x + dx);
			item.setEndY(y + dy);
		}
	}

} // end of class AggregateDragControl

/**
 * Set label positions. Labels are assumed to be DecoratorItem instances,
 * decorating their respective nodes. The layout simply gets the bounds of the
 * decorated node and assigns the label coordinates to the center of those
 * bounds.
 */
class LabelLayout3 extends Layout {
	public LabelLayout3(String group) {
		super(group);
	}

	public void run(double frac) {
		Iterator iter = m_vis.visibleItems(m_group);
		while (iter.hasNext()) {
			DecoratorItem decorator = (DecoratorItem) iter.next();
			VisualItem decoratedItem = decorator.getDecoratedItem();
			Rectangle2D bounds = decoratedItem.getBounds();

			double x = bounds.getCenterX();
			double y = bounds.getCenterY();

			/*
			 * modification to move edge labels more to the arrow head double x2 =
			 * 0, y2 = 0; if (decoratedItem instanceof EdgeItem){ VisualItem
			 * dest = ((EdgeItem)decoratedItem).getTargetItem(); x2 =
			 * dest.getX(); y2 = dest.getY(); x = (x + x2) / 2; y = (y + y2) /
			 * 2; }
			 */

			setX(decorator, null, x);
			setY(decorator, null, y);
		}
	}
} // end of inner class LabelLayout

