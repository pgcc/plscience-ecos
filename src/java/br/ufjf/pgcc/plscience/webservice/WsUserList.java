/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.webservice;

import br.ufjf.pgcc.plscience.dao.AgentDAO;
import br.ufjf.pgcc.plscience.model.Agent;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Guilherme Martins
 */
@WebService(serviceName = "WsUserList")
public class WsUserList {
    
    @WebMethod(operationName = "getUserList")
    public List<Agent> getUserList() {
        return new AgentDAO().buscarTodas();
    }
    
    @WebMethod(operationName = "getUserListByStatusName")
    public List<Agent> getUserListByStatusName(@WebParam(name = "statusName") String statusName) {
        return new AgentDAO().findByStatusName(statusName);
    }
    
    @WebMethod(operationName = "getUserListByStatusID")
    public List<Agent> getUserListByStatusID(@WebParam(name = "statusID") int statusID) {
        return new AgentDAO().findByStatusID(statusID);
    }
    
    @WebMethod(operationName = "getUserListByRoleName")
    public List<Agent> getUserListByRoleName(@WebParam(name = "roleName") String roleName) {
        return new AgentDAO().findByRoleName(roleName);
    }
    
    @WebMethod(operationName = "getUserListByRoleID")
    public List<Agent> getUserListByRoleID(@WebParam(name = "roleID") int roleID) {
        return new AgentDAO().findByRoleID(roleID);
    }
    
    @WebMethod(operationName = "getUserListByCompetenceName")
    public List<Agent> getUserListByCompetenceName(@WebParam(name = "competenceName") String competenceName) {
        return new AgentDAO().findByCompetenceName(competenceName);
    }
    
    @WebMethod(operationName = "getUserListByCompetenceID")
    public List<Agent> getUserListByCompetenceID(@WebParam(name = "competenceID") int competenceID) {
        return new AgentDAO().findByCompetenceID(competenceID);
    }
    
    @WebMethod(operationName = "getUserListByCompetenceAndRole")
    public List<Agent> getUserListByCompetenceAndRole(@WebParam(name = "competenceName") String competenceName,
            @WebParam(name = "roleName") String roleName) {
        return new AgentDAO().findByCompetenceAndRole(competenceName, roleName);
    }
}
