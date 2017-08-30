/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.serviceComposition;

import br.ufjf.pgcc.plscience.dao.AgentDAO;
import br.ufjf.pgcc.plscience.dao.CompositionVDAO;
import br.ufjf.pgcc.plscience.dao.CompositionVHasAgentDAO;
import br.ufjf.pgcc.plscience.dao.DiscussionVDAO;
import br.ufjf.pgcc.plscience.dao.MessageVDAO;
import br.ufjf.pgcc.plscience.model.Agent;
import br.ufjf.pgcc.plscience.model.CompositionV;
import br.ufjf.pgcc.plscience.model.CompositionVHasAgent;
import br.ufjf.pgcc.plscience.model.DiscussionV;
import br.ufjf.pgcc.plscience.model.MessageV;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

/**
 *
 * @author phillipe
 */
@ManagedBean()
@ViewScoped
public class ServiceCompositionEvaluation implements Serializable {

    private String message = "Texto";
    private String txtUserNameToCompValidation = "";
    private String compositionName = "";
    private String compositionDescription = "";
    private List<Agent> researchersSelected;
    private String[] selectedAgents;
    private List<String> agents;
    private List<Agent> agentList;
    private String selectedComposition = "Primeira Composição";
    private List<SelectItem> allCompositions;
    private List<MessageChat> messagesChat = new ArrayList<>();

    @PostConstruct
    public void init() {
        researchersSelected = new ArrayList<>();
        AgentDAO agentDAO = new AgentDAO();
        agentList = agentDAO.buscarTodas();
        agents = new ArrayList<>();
        for (int i = 0; i < agentList.size(); i++) {
            agents.add(agentList.get(i).getName());
        }
        getCompositionsDatabase();
    }

    public void getCompositionsDatabase() {
        SelectItemGroup g1 = new SelectItemGroup("Compositions");

        CompositionVDAO cDAO = new CompositionVDAO();
        List<CompositionV> compositions = cDAO.getAll();

        if (compositions != null) {
            SelectItem[] selectItems = new SelectItem[compositions.size()];

            for (int i = 0; i < compositions.size(); i++) {
                CompositionV c = compositions.get(i);
                SelectItem s = new SelectItem(c.getCompositionName(), c.getCompositionName());
                selectItems[i] = s;
            }

            g1.setSelectItems(selectItems);

            allCompositions = new ArrayList<>();
            allCompositions.add(g1);

//            if(compositions.size() > 0)
//                selectedComposition = compositions.get(compositions.size()-1).getCompositionName();
        }
    }

    public void fillMessagesChat(String message, boolean addNewMessage) {
        messagesChat = new ArrayList<>();
        DiscussionVDAO dDAO = new DiscussionVDAO();
        DiscussionV d = new DiscussionV();
        if (!selectedComposition.equals("")) {
            d = dDAO.getDiscussionByCompositionName(selectedComposition);
        }

        MessageV mV = new MessageV();
        mV.setDiscussion(d);
        mV.setDescription(message);

        Agent sender;
        AgentDAO aDAO = new AgentDAO();
        sender = aDAO.buscarTodas().get(4);//usuario logado aqui
        mV.setSender(sender);

        mV.setSendedAt(new Date());

        MessageVDAO mDAO = new MessageVDAO();
        if (addNewMessage) {
            mDAO.save(mV);
        }

        List<MessageV> allMessagesForDiscussion;
        if (d != null && d.getId() != null) {
            allMessagesForDiscussion = mDAO.getMessageByDiscussionId(d.getId());

            for (MessageV m : allMessagesForDiscussion) {
                MessageChat messageChat = new MessageChat();
                String senderName = m.getSender().getName();
                if (senderName != null && !senderName.equals("")) {
                    String[] fullName = senderName.split(" ");
                    senderName = fullName[0];
                }
                messageChat.setSenderName(senderName);
                messageChat.setSenderPicture(m.getSender().getPicture());
                messageChat.setText(m.getDescription());
                messagesChat.add(messageChat);
            }
            getCompositionsDatabase();
        }
    }

    /**
     * select researchers by form
     */
    public void selectedAgentsForm() {
        for (Agent a : agentList) {
            if (getSelectedAgents() != null) {
                for (int i = 0; i < getSelectedAgents().length; i++) {
                    if (a.getName().equals(getSelectedAgents()[i])) {
                        researchersSelected.add(a);
                    }
                }
            }
        }
    }

    /**
     * Complete text to a user that may validates a composition
     *
     * @param query
     * @return
     */
    public List<String> completeTextUserNameServiceV(String query) {
        AgentDAO agentDAO = new AgentDAO();
        List<Agent> agentList = agentDAO.buscarTodas();
        List<String> results = new ArrayList<>();
        for (int i = 0; i < agentList.size(); i++) {
            Agent agent = agentList.get(i);
            String agentName = agent.getName();
            if (agentName.toLowerCase().contains(query.toLowerCase())) {
                results.add(agentName);
            }
        }

        //sort array
        Collections.sort(results);
        return results;
    }

    /**
     * generates a discussion to validate a composition
     */
    public void createADiscussion() {
        selectedAgentsForm();

        DiscussionV discussionV = new DiscussionV();
        DiscussionVDAO discussionDAO = new DiscussionVDAO();

        CompositionV compositionV = new CompositionV();
        CompositionVDAO compositionDAO = new CompositionVDAO();

        MessageV messageV = new MessageV();
        MessageVDAO messageDAO = new MessageVDAO();

        CompositionVHasAgent cHasAgent = new CompositionVHasAgent();
        CompositionVHasAgentDAO cHasAgentDAO = new CompositionVHasAgentDAO();

        Agent sender;
        //UserLoginBean userLoginBean = new UserLoginBean();
        //sender = userLoginBean.getAgentLog();

        AgentDAO agentDao = new AgentDAO();
        List<Agent> allAgents = agentDao.buscarTodas();
        sender = allAgents.get(0);
        for (Agent a : allAgents) {
            if (a.getLoggedIn()) {
                sender = a;
            }
        }

        System.out.println("Nome da Composição " + compositionName);
        //setComposition
        if (!compositionName.equals("")) {
            compositionV.setCompositionName(compositionName);
        }
        if (!compositionDescription.equals("")) {
            compositionV.setCompositionDescription(compositionDescription);
        }

        //save composition
        if (!compositionName.equals("")) {
            compositionDAO.save(compositionV);
        }

        CompositionV compositionSaved;

        if (compositionV.getCompositionName() != null && !compositionV.getCompositionName().equals("")) {

            compositionSaved = compositionDAO.getCompositionByName(compositionV.getCompositionName());

            //setDiscussion
            discussionV.setCreatedAt(new Date());
            discussionV.setCreatedBy(sender);
            discussionV.setCompositionVId(compositionSaved);

            //save discussion
            discussionDAO.save(discussionV);

            DiscussionV discussionSaved = discussionDAO.getDiscussionByCompositionName(compositionV.getCompositionName());

            //setMessage
            if (!message.equals("")) {
                messageV.setDescription(message);
            }

            messageV.setSendedAt(new Date());

            if (sender != null) {
                messageV.setSender(sender);
            }

            messageV.setDiscussion(discussionSaved);

            //save message
            messageDAO.save(messageV);

            //save composition has agent
            cHasAgent.setAgentId(sender);
            cHasAgent.setCompositionId(compositionSaved);

            cHasAgentDAO.save(cHasAgent);

            for (Agent a : researchersSelected) {
                cHasAgent.setAgentId(a);
                cHasAgent.setCompositionId(compositionSaved);
                cHasAgentDAO.save(cHasAgent);
            }
            getCompositionsDatabase();
        }
    }

    /**
     * @return the txtUserNameToCompValidation
     */
    public String getTxtUserNameToCompValidation() {
        return txtUserNameToCompValidation;
    }

    /**
     * @param txtUserNameToCompValidation the txtUserNameToCompValidation to set
     */
    public void setTxtUserNameToCompValidation(String txtUserNameToCompValidation) {
        this.txtUserNameToCompValidation = txtUserNameToCompValidation;
    }

    /**
     * @return the compositionName
     */
    public String getCompositionName() {
        return compositionName;
    }

    /**
     * @param compositionName the compositionName to set
     */
    public void setCompositionName(String compositionName) {
        this.compositionName = compositionName;
    }

    /**
     * @return the compositionDescription
     */
    public String getCompositionDescription() {
        return compositionDescription;
    }

    /**
     * @param compositionDescription the compositionDescription to set
     */
    public void setCompositionDescription(String compositionDescription) {
        this.compositionDescription = compositionDescription;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the agents
     */
    public List<String> getAgents() {
        return agents;
    }

    /**
     * @param agents the agents to set
     */
    public void setAgents(List<String> agents) {
        this.agents = agents;
    }

    /**
     * @return the agentList
     */
    public List<Agent> getAgentList() {
        return agentList;
    }

    /**
     * @param agentList the agentList to set
     */
    public void setAgentList(List<Agent> agentList) {
        this.agentList = agentList;
    }

    /**
     * @return the researchersSelected
     */
    public List<Agent> getResearchersSelected() {
        return researchersSelected;
    }

    /**
     * @param researchersSelected the researchersSelected to set
     */
    public void setResearchersSelected(List<Agent> researchersSelected) {
        this.researchersSelected = researchersSelected;
    }

    /**
     * @return the selectedComposition
     */
    public String getSelectedComposition() {
        return selectedComposition;
    }

    /**
     * @param selectedComposition the selectedComposition to set
     */
    public void setSelectedComposition(String selectedComposition) {
        this.selectedComposition = selectedComposition;
    }

    /**
     * @return the allCompositions
     */
    public List<SelectItem> getAllCompositions() {
        return allCompositions;
    }

    /**
     * @param allCompositions the allCompositions to set
     */
    public void setAllCompositions(List<SelectItem> allCompositions) {
        this.allCompositions = allCompositions;
    }

    /**
     * @return the messagesChat
     */
    public List<MessageChat> getMessagesChat() {
        return messagesChat;
    }

    /**
     * @param messagesChat the messagesChat to set
     */
    public void setMessagesChat(List<MessageChat> messagesChat) {
        this.messagesChat = messagesChat;
    }

    /**
     * @return the selectedAgents
     */
    public String[] getSelectedAgents() {
        return selectedAgents;
    }

    /**
     * @param selectedAgents the selectedAgents to set
     */
    public void setSelectedAgents(String[] selectedAgents) {
        this.selectedAgents = selectedAgents;
    }

}
