/*
 * Copyright 2007 The Kuali Foundation Licensed under the Educational Community License, Version 1.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.opensource.org/licenses/ecl1.php Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package org.kuali.student.lum.lu.ui.course.server.gwt;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.rice.kew.dto.ActionRequestDTO;
import org.kuali.rice.kew.dto.DocumentContentDTO;
import org.kuali.rice.kew.dto.DocumentDetailDTO;
import org.kuali.rice.kew.dto.RouteNodeInstanceDTO;
import org.kuali.rice.kew.service.WorkflowUtility;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kew.webservice.DocumentResponse;
import org.kuali.rice.kew.webservice.SimpleDocumentActionsWebService;
import org.kuali.rice.kew.webservice.StandardResponse;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;
import org.kuali.rice.kim.service.PermissionService;
import org.kuali.student.common.ui.client.mvc.dto.ModelDTO;
import org.kuali.student.common.ui.client.mvc.dto.ModelDTOValue;
import org.kuali.student.common.ui.client.mvc.dto.ModelDTOValue.ModelDTOType;
//import org.kuali.student.common.ui.server.applicationstate.ApplicationStateManager;
import org.kuali.student.common.ui.server.gwt.BaseRpcGwtServletAbstract;
import org.kuali.student.common.ui.server.mvc.dto.MapContext;
import org.kuali.student.core.organization.service.OrganizationService;
import org.kuali.student.core.proposal.dto.ProposalInfo;
import org.kuali.student.core.proposal.service.ProposalService;
import org.kuali.student.lum.lu.dto.CluInfo;
import org.kuali.student.lum.lu.dto.workflow.CluProposalCollabRequestDocInfo;
import org.kuali.student.lum.lu.dto.workflow.CluProposalDocInfo;
import org.kuali.student.lum.lu.dto.workflow.PrincipalIdRoleAttribute;
import org.kuali.student.lum.lu.service.LuService;
import org.kuali.student.lum.lu.ui.course.client.configuration.mvc.CluProposalModelDTO;
import org.kuali.student.lum.lu.ui.course.client.service.CluProposalRpcService;
import org.springframework.security.Authentication;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.userdetails.User;
import org.springframework.security.userdetails.UserDetails;

/**
 * GWT service orchestration code for creating and modifying clu proposals.
 *
 * @author Kuali Student Team
 */
public class CluProposalRpcGwtServlet extends BaseRpcGwtServletAbstract<LuService> implements CluProposalRpcService {

    final Logger logger = Logger.getLogger(CluProposalRpcGwtServlet.class);

    private static final long serialVersionUID = 1L;
    private static final String DEFAULT_USER_ID = "user1";
    private static final String WF_TYPE_CLU_DOCUMENT = "CluDocument";
    private static final String WF_TYPE_CLU_COLLABORATOR_DOCUMENT =  "CluCollaboratorDocument";
    private static final String PROPOSAL_REFERENCE_TYPE = "kuali.proposal.referenceType.clu";
    
    private static final String CLU_INFO_KEY = "cluInfo";
    private static final String PROPOSAL_INFO_KEY = "proposalInfo";
    
    //Rice Services
    private SimpleDocumentActionsWebService simpleDocService;
    private WorkflowUtility workflowUtilityService;
    private PermissionService permissionService;
    
    //Core Services
    private OrganizationService orgService;
    private ProposalService proposalService;

    //private ApplicationStateManager applicationStateManager;

	@Override
	public CluProposalModelDTO getCluProposalFromWorkflowId(String docId) {
        if(simpleDocService==null){
        	throw new RuntimeException("Workflow Service is unavailable");
        }

        //get a user name
        String username = getCurrentUser();

        DocumentResponse docResponse = simpleDocService.getDocument(docId, username);
        if(docResponse==null||StringUtils.isNotBlank(docResponse.getErrorMessage())){
        	throw new RuntimeException("Error found gettting document: " + docResponse.getErrorMessage());
        }

        CluProposalModelDTO proposal = getProposal(docResponse.getAppDocId());

		return proposal;
	}
	
	@Override
	public String getWorkflowIdFromProposalId(String proposalId) {
        if(null==simpleDocService){
        	throw new RuntimeException("Workflow Service is unavailable");
        }

        DocumentDetailDTO docDetail;
		try {
			docDetail = workflowUtilityService.getDocumentDetailFromAppId(WF_TYPE_CLU_DOCUMENT, proposalId);
	        if(null==docDetail){
	        	return null;
	        }
	        Long workflowId=docDetail.getRouteHeaderId();
	        return null==workflowId?null:workflowId.toString();
		} catch (Exception e) {
			logger.info("Call Failed getting workflowId for proposalId: "+proposalId, e);
		}
		return null;
    }

	@Override
	public String getActionsRequested(String cluProposalId) {
        try{
		if(workflowUtilityService==null){
        	throw new RuntimeException("Workflow Service is unavailable");
        }

		if(null==cluProposalId){
			logger.info("No Clu Id was set for the proposal");
			return "";
		}

        //get a user name
        String username = getCurrentUser();

        //Lookup the workflowId from the cluId
        String workflowDocTypeId = "CluDocument";
        DocumentDetailDTO docDetail = workflowUtilityService.getDocumentDetailFromAppId(workflowDocTypeId, cluProposalId);
        if(docDetail==null){
        	throw new RuntimeException("Error found gettting document. " );
        }
        
		//Build up a string of actions requested from the attribute set.  The actions can be S, F,A,C,K. examples are "A" "AF" "FCK" "SCA"
        logger.debug("Calling action requested with user:"+username+" and docId:"+docDetail.getRouteHeaderId());

        Map<String,String> results = new HashMap<String,String>();
        for(ActionRequestDTO request:docDetail.getActionRequests()){
    		if(request.getPrincipalId()!=null&&request.getPrincipalId().equals(username)){
    			results.put(request.getActionRequested(), "true");
    		}
        }
        
        String actionsRequested = "";

        String documentStatus = workflowUtilityService.getDocumentStatus(docDetail.getRouteHeaderId());
        
        for(Map.Entry<String,String> entry:results.entrySet()){
        	// if saved or initiated status... must show only 'complete' button
        	if (KEWConstants.ROUTE_HEADER_SAVED_CD.equals(documentStatus) || KEWConstants.ROUTE_HEADER_INITIATED_CD.equals(documentStatus)) {
        		// show only complete button if complete or approve code in this doc status
        		if ( (KEWConstants.ACTION_REQUEST_COMPLETE_REQ.equals(entry.getKey()) || KEWConstants.ACTION_REQUEST_APPROVE_REQ.equals(entry.getKey())) && ("true".equals(entry.getValue())) ) {
        			actionsRequested+="S";
        		}
        		// if not Complete or Approve code then show the standard buttons
        		else {
	            	if("true".equals(entry.getValue())){
	            		actionsRequested+=entry.getKey();
	            	}
        		}
        	}
        	else {
            	if("true".equals(entry.getValue())){
            		actionsRequested+=entry.getKey();
            	}
        	}
        }
        	return actionsRequested;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error getting actions Requested",e);
        }
	}

	@Override
	public Boolean approveDocument(String requestDocId){
        if(simpleDocService==null){
        	logger.error("Workflow Service is unavailable");
        	return Boolean.FALSE;
        }

		try{
			DocumentContentDTO docContent = workflowUtilityService.getDocumentContent(Long.parseLong(requestDocId));
			DocumentDetailDTO docDetail = workflowUtilityService.getDocumentDetail(Long.parseLong(requestDocId));
            //get a user name
            String username = getCurrentUser();
	        String approveComment = "Approved";

	        StandardResponse stdResp = simpleDocService.approve(requestDocId.toString(), username, docDetail.getDocTitle(), docContent.getApplicationContent(), approveComment);
            if(stdResp==null||StringUtils.isNotBlank(stdResp.getErrorMessage())){
            	logger.error("Error found approving document: " + stdResp.getErrorMessage());
            	return Boolean.FALSE;
        	}
		}catch(Exception e){
            logger.error("Error approving document",e);
            return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	@Override
	public Boolean disapproveDocument(String requestDocId){
        if(simpleDocService==null){
        	logger.error("Workflow Service is unavailable");
        	return Boolean.FALSE;
        }

		try{
			DocumentDetailDTO docDetail = workflowUtilityService.getDocumentDetail(Long.parseLong(requestDocId));
            //get a user name
            String username = getCurrentUser();
	        String disapproveComment = "Disapproved";

	        //String docId, String principalId, String docTitle, String docContent, String annotation
	        StandardResponse stdResp = simpleDocService.disapprove(docDetail.getRouteHeaderId().toString(), username, disapproveComment);
	        if(stdResp==null||StringUtils.isNotBlank(stdResp.getErrorMessage())){
        		logger.error("Error found approving document: " + stdResp.getErrorMessage());
        		return Boolean.FALSE;
        	}
		}catch(Exception e){
            logger.error("Error approving document",e);
            return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	
	@Override
	public Boolean approveProposal(CluProposalModelDTO cluProposal) {
        if(simpleDocService==null){
        	throw new RuntimeException("Workflow Service is unavailable");
        }

		try{
            //get a user name
            String username = getCurrentUser();
            
	        ProposalInfo proposalInfo = getProposalInfo(cluProposal);
	        CluInfo cluInfo = getCluInfo(cluProposal);

            //Lookup the workflowId from the cluId
            DocumentDetailDTO docDetail = workflowUtilityService.getDocumentDetailFromAppId(WF_TYPE_CLU_DOCUMENT, proposalInfo.getId());
            if(docDetail==null){
            	throw new RuntimeException("Error found gettting document. " );
            }
            
	        String approveComment = "Approved by CluProposalService";
	        
	        StandardResponse stdResp = simpleDocService.approve(docDetail.getRouteHeaderId().toString(), username, docDetail.getDocTitle(), getCluProposalDocContent(cluInfo,proposalInfo), approveComment);
            if(stdResp==null||StringUtils.isNotBlank(stdResp.getErrorMessage())){
        		throw new RuntimeException("Error found approving document: " + stdResp.getErrorMessage());
        	}

		}catch(Exception e){
            e.printStackTrace();
		}
        return new Boolean(true);
	}

	@Override
	public Boolean disapproveProposal(CluProposalModelDTO cluProposal) {
        if(simpleDocService==null){
        	throw new RuntimeException("Workflow Service is unavailable");
        }

		try{
            //get a user name
            String username = getCurrentUser();
            
	        ProposalInfo proposalInfo = getProposalInfo(cluProposal);
            
	        //Lookup the workflowId from the cluId
            DocumentDetailDTO docDetail = workflowUtilityService.getDocumentDetailFromAppId(WF_TYPE_CLU_DOCUMENT, proposalInfo.getId());
            if(docDetail==null){
            	throw new RuntimeException("Error found gettting document. " );
            }
            
	        String disapproveComment = "Disapproved by CluProposalService";

	        //String docId, String principalId, String docTitle, String docContent, String annotation
	        StandardResponse stdResp = simpleDocService.disapprove(docDetail.getRouteHeaderId().toString(), username, disapproveComment);

	        if(stdResp==null||StringUtils.isNotBlank(stdResp.getErrorMessage())){
        		throw new RuntimeException("Error found disapproving document: " + stdResp.getErrorMessage());
        	}

		}catch(Exception e){
            e.printStackTrace();
		}
        return new Boolean(true);
	}


	@Override
	public Boolean acknowledgeProposal(CluProposalModelDTO cluProposal) {
        if(simpleDocService==null){
        	throw new RuntimeException("Workflow Service is unavailable");
        }

		try{
			//get a user name
            String username=getCurrentUser();
	        
	        ProposalInfo proposalInfo = getProposalInfo(cluProposal);
            
            //Lookup the workflowId from the cluId
            DocumentDetailDTO docDetail = workflowUtilityService.getDocumentDetailFromAppId(WF_TYPE_CLU_DOCUMENT, proposalInfo.getId());
            if(docDetail==null){
            	throw new RuntimeException("Error found gettting document. " );
            }
            
	        String acknowledgeComment = "Acknowledged by CluProposalService";

	        //String docId, String principalId, String docTitle, String docContent, String annotation
	        StandardResponse stdResp = simpleDocService.acknowledge(docDetail.getRouteHeaderId().toString(), username, acknowledgeComment);

	        if(stdResp==null||StringUtils.isNotBlank(stdResp.getErrorMessage())){
        		throw new RuntimeException("Error found acknowledging document: " + stdResp.getErrorMessage());
        	}

		}catch(Exception e){
            e.printStackTrace();
		}
        return new Boolean(true);
	}

	private CluInfo getCluInfo(CluProposalModelDTO cluProposal){
		try {
			MapContext ctx = new MapContext();//TODO should/can this be reused?
			ModelDTO cluInfoModelDTO = ((ModelDTOType)cluProposal.get("cluInfo")).get();
			CluInfo cluInfo;
			cluInfo = (CluInfo)ctx.fromModelDTO(cluInfoModelDTO);
			return cluInfo;
		} catch (Exception e) {
			logger.warn("Error converting CluModelDTO to cluInfo",e);
		}
		return null;
	}
	
	private ProposalInfo getProposalInfo(CluProposalModelDTO cluProposal){
		try {
			MapContext ctx = new MapContext();//TODO should/can this be reused?
			ModelDTO proposalInfoModelDTO = ((ModelDTOType)cluProposal.get("proposalInfo")).get();
			ProposalInfo proposalInfo;
			proposalInfo = (ProposalInfo)ctx.fromModelDTO(proposalInfoModelDTO);
			return proposalInfo;
		} catch (Exception e) {
			logger.warn("Error converting ProposalModelDTO to proposalInfo",e);
		}
		return null;
	}
	
	@Override
    public Boolean addCollaborator(String docId, String recipientPrincipalId, String collabType, boolean participationRequired, String respondBy){
        if(simpleDocService==null){
        	throw new RuntimeException("Workflow Service is unavailable");
        }

		try{
			//get a user name
            String username=getCurrentUser();

	        String collaborateComment = "Collaborate by CluProposalService";

	        //create and route a Collaborate workflow
	        //Get the document app Id
	        CluProposalModelDTO cluProposal = getCluProposalFromWorkflowId(docId);

	        ProposalInfo proposalInfo = getProposalInfo(cluProposal);
	        CluInfo cluInfo = getCluInfo(cluProposal);
	        
            String title = proposalInfo.getName()==null?"NoNameSet":proposalInfo.getName();
            
            DocumentResponse docResponse = simpleDocService.create(username, docId, WF_TYPE_CLU_COLLABORATOR_DOCUMENT, title);
            if (StringUtils.isNotBlank(docResponse.getErrorMessage())) {
            	throw new RuntimeException("Error found creating document: " + docResponse.getErrorMessage());
            }

            //Get the current routeNodeName
            String routeNodeName="";
            RouteNodeInstanceDTO[] routeNodes = workflowUtilityService.getDocumentRouteNodeInstances(Long.parseLong(docId));
            if(null!=routeNodes){
            	for(RouteNodeInstanceDTO routeNode:routeNodes){
            		if(routeNode.isActive()){
            			routeNodeName=routeNode.getName();
            		}
            	}
            }
            
            //Get the document xml
    		CluProposalCollabRequestDocInfo docContent = new CluProposalCollabRequestDocInfo();

    		docContent.setCluId(cluInfo.getId());
    		docContent.setPrincipalIdRoleAttribute(new PrincipalIdRoleAttribute());
    		docContent.getPrincipalIdRoleAttribute().setRecipientPrincipalId(recipientPrincipalId);
    		docContent.setPrincipalId(username);
    		docContent.setDocId(docId);
    		docContent.setCollaboratorType(collabType);
    		docContent.setParticipationRequired(participationRequired);
    		docContent.setRespondBy(respondBy);
    		docContent.setRouteNodeName(routeNodeName);

    		JAXBContext context = JAXBContext.newInstance(docContent.getClass());
    		Marshaller marshaller = context.createMarshaller();
            StringWriter writer = new StringWriter();
    		marshaller.marshal(docContent, writer);

            String docContentString = writer.toString();

            //Do the routing
            StandardResponse stdResp = simpleDocService.route(docResponse.getDocId(), username, docResponse.getTitle(), docContentString, collaborateComment);

            if(stdResp==null||StringUtils.isNotBlank(stdResp.getErrorMessage())){
        		throw new RuntimeException("Error found routing document: " + stdResp.getErrorMessage());
        	}

		}catch(Exception e){
            e.printStackTrace();
		}
        return new Boolean(true);
    }

	@Override
    public HashMap<String, ArrayList<String>> getCollaborators(String docId){
		try{
        if(workflowUtilityService==null){
        	throw new RuntimeException("Workflow Service is unavailable");
        }

		HashMap<String, ArrayList<String>> results = new HashMap<String, ArrayList<String>>();

		ArrayList<String> coAuthors = new ArrayList<String>();
		ArrayList<String> commentors= new ArrayList<String>();
		ArrayList<String> viewers = new ArrayList<String>();
		ArrayList<String> delegates = new ArrayList<String>();

		ActionRequestDTO[] items= workflowUtilityService.getAllActionRequests(Long.parseLong(docId));
        if(items!=null){
        	for(ActionRequestDTO item:items){
        		if (item.isActivated() && (!item.isDone())) {
	        		if(KEWConstants.ACTION_REQUEST_FYI_REQ.equals(item.getActionRequested())&&item.getRequestLabel()!=null){
	        			if(item.getRequestLabel().startsWith("Co-Author")){
		        			coAuthors.add(item.getPrincipalId());
		        		}
		        		else if(item.getRequestLabel().startsWith("Commentor")){
		        			commentors.add(item.getPrincipalId());
		        		}
		        		else if(item.getRequestLabel().startsWith("Viewer")){
		        			viewers.add(item.getPrincipalId());
		        		}
		        		else if(item.getRequestLabel().startsWith("Delegate")){
		        			delegates.add(item.getPrincipalId());
		        		}
	        		}
        		}
        	}
        }

        results.put("Co-Authors", coAuthors);
        results.put("Commentor", commentors);
        results.put("Viewer", viewers);
        results.put("Delegate", delegates);
        return results;
		}catch(Exception e){
            throw new RuntimeException("Error getting actions Requested",e);
		}
    }
 
	@Override
	public Boolean loginBackdoor(String backdoorId) {
		try{
			//Set spring security principal to the new backdoorId
		    Object credentials = SecurityContextHolder.getContext().getAuthentication().getCredentials();

		    GrantedAuthority[] authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

		    User u = new User(backdoorId, backdoorId, true, true, true, true, authorities);

		    Authentication auth = new UsernamePasswordAuthenticationToken(u, credentials, authorities);

		    SecurityContextHolder.getContext().setAuthentication(auth);
		}catch(Exception e){
			//Try to put the username in a threadloacal for testing
			this.getThreadLocalRequest().getSession().setAttribute("backdoorId", backdoorId);
			logger.error("Error with backdoor login, saving to a session attribute", e);
		}
		return Boolean.TRUE;
	}

    /**
     * @see org.kuali.student.lum.lu.ui.course.client.service.CluProposalRpcService#createProposal(org.kuali.student.lum.lu.ui.course.client.configuration.mvc.CluProposalModelDTO)
     */
    @Override
    public CluProposalModelDTO createProposal(CluProposalModelDTO cluProposalDTO) {        
        MapContext ctx = new MapContext();

        logger.info("Creating proposal");
        try{                    
            //add application state data
           // applicationStateManager.createOrUpdateApplicationState(cluProposalDTO);

            //Convert cluInfo model dto to cluInfo object
            ModelDTO cluInfoModelDTO = ((ModelDTOType)cluProposalDTO.get(CLU_INFO_KEY)).get();
            CluInfo cluInfo = (CluInfo)ctx.fromModelDTO(cluInfoModelDTO);

            //Create clu in LuService
            cluInfo = service.createClu(cluInfo.getType(), cluInfo);
            ModelDTO cluModelDTO = (ModelDTO)ctx.fromBean(cluInfo);
            cluInfoModelDTO.copyFrom(cluModelDTO);
            
           // applicationStateManager.getApplicationState(cluProposalDTO);

            saveCourseFormats(cluProposalDTO);
            
            //Convert proposalInfo model dto to proposalInfo
            ModelDTO proposalInfoModelDTO = ((ModelDTOType)cluProposalDTO.get(PROPOSAL_INFO_KEY)).get();
            ProposalInfo proposalInfo = (ProposalInfo)ctx.fromModelDTO(proposalInfoModelDTO);

            //TODO: Should effective/expiration date be copied from cluInfo or new fields added to screen

            //Add reference to clu in the proposal
            List<String> proposalReferences = new ArrayList<String>();
            proposalReferences.add(cluInfo.getId());
            proposalInfo.setProposalReferenceType(PROPOSAL_REFERENCE_TYPE);
            proposalInfo.setProposalReference(proposalReferences);            
            
            //Create proposal in proposalService
            proposalInfo = proposalService.createProposal(proposalInfo.getType(), proposalInfo);
            ModelDTO proposalModelDTO = (ModelDTO)ctx.fromBean(proposalInfo);
            proposalInfoModelDTO.copyFrom(proposalModelDTO);            

            
            //Do Workflow Create and save docContent
            //get a user name
            String username=getCurrentUser();

            String title = proposalInfo.getName()==null?"NoLongNameSet":proposalInfo.getName();
            title = title==null?"NoLongNameSet":title;
            
            logger.info("Creating proposal Workflow Document.");
            DocumentResponse docResponse = simpleDocService.create(username, proposalInfo.getId(), WF_TYPE_CLU_DOCUMENT, title);
            if (StringUtils.isNotBlank(docResponse.getErrorMessage())) {
            	throw new RuntimeException("Error found creating document: " + docResponse.getErrorMessage());
            }
            
            if(null!=cluInfo.getAdminOrg()){
	            String saveComment = "Created By CluProposalService";
	            StandardResponse stdResp = simpleDocService.save(docResponse.getDocId(), username, title,getCluProposalDocContent(cluInfo,proposalInfo), saveComment);
	            if(stdResp==null||StringUtils.isNotBlank(stdResp.getErrorMessage())){
	            	throw new RuntimeException("Error found saving document: " + stdResp.getErrorMessage());
	            }
            }
        }
        catch(Exception e){
            if (e.getMessage()!=null&&!e.getMessage().contains("No remote services available")){
                throw new RuntimeException("Error saving Proposal. ",e);
            }
            logger.error("Error saving Proposal",e);
        }

        return cluProposalDTO;
    }

    private void saveCourseFormats(CluProposalModelDTO cluProposalDTO) throws Exception{
        ModelDTOValue.ListType courseFormatListType = (ModelDTOValue.ListType)cluProposalDTO.get("courseFormats");
        MapContext ctx = new MapContext();
        
        if (courseFormatListType != null){
            List<ModelDTOValue> courseFormatList = courseFormatListType.get();
            
            for (ModelDTOValue value:courseFormatList){
                ModelDTO courseFormatModelDTO = ((ModelDTOValue.ModelDTOType)value).get();
                
                CluInfo courseFormatShell = (CluInfo)ctx.fromModelDTO(courseFormatModelDTO);
                
                logger.debug(courseFormatShell.getType());                                
            }
        }
        
    }
    
    /**
     * @see org.kuali.student.lum.lu.ui.course.client.service.CluProposalRpcService#saveProposal(org.kuali.student.lum.lu.ui.course.client.configuration.mvc.CluProposalModelDTO)
     */
    @Override
    public CluProposalModelDTO saveProposal(CluProposalModelDTO cluProposalDTO) {
        MapContext ctx = new MapContext();
        try{
            logger.debug("Updating proposal");

            //Convert proposalInfo model dto to proposalInfo
            ModelDTO proposalInfoModelDTO = ((ModelDTOType)cluProposalDTO.get(PROPOSAL_INFO_KEY)).get();
            ProposalInfo proposalInfo = (ProposalInfo)ctx.fromModelDTO(proposalInfoModelDTO);
            
            //Create proposal in proposalService
            proposalInfo = proposalService.updateProposal(proposalInfo.getId(), proposalInfo);
            ModelDTO proposalModelDTO = (ModelDTO)ctx.fromBean(proposalInfo);
            proposalInfoModelDTO.copyFrom(proposalModelDTO);            
            
            //Convert cluInfo model dto to cluInfo object
            ModelDTO cluInfoModelDTO = ((ModelDTOType)cluProposalDTO.get(CLU_INFO_KEY)).get();
            CluInfo cluInfo = (CluInfo)ctx.fromModelDTO(cluInfoModelDTO);

            //Create clu in LuService
            cluInfo = service.updateClu(cluInfo.getId(), cluInfo);
            ModelDTO cluModelDTO = (ModelDTO)ctx.fromBean(cluInfo);
            cluInfoModelDTO.copyFrom(cluModelDTO);
            
            saveCourseFormats(cluProposalDTO);
            
            //get a user name
            String username = getCurrentUser();
            
            //Lookup the workflowId from the cluId
            DocumentDetailDTO docDetail = workflowUtilityService.getDocumentDetailFromAppId(WF_TYPE_CLU_DOCUMENT, proposalInfo.getId());
            
            //Check that the call was successful
            if(docDetail==null){
            	throw new RuntimeException("Error found gettting document: ");
            }
            
            String title = proposalInfo.getName()==null?"NoNameSet":proposalInfo.getName();
            title = title==null?"NoLongNameSet":title;
            
            if ( (KEWConstants.ROUTE_HEADER_INITIATED_CD.equals(docDetail.getDocRouteStatus())) ||
            	 (KEWConstants.ROUTE_HEADER_SAVED_CD.equals(docDetail.getDocRouteStatus())) ) {
                StandardResponse stdResp = simpleDocService.save(docDetail.getRouteHeaderId().toString(), username, title, getCluProposalDocContent(cluInfo,proposalInfo), "");
                if(stdResp==null||StringUtils.isNotBlank(stdResp.getErrorMessage())){
                	throw new RuntimeException("Error found saving document: " + stdResp.getErrorMessage());
                }
            }
            else {
            	StandardResponse stdResp = simpleDocService.saveDocumentContent(docDetail.getRouteHeaderId().toString(), username, title, getCluProposalDocContent(cluInfo,proposalInfo));
            	if(stdResp==null||StringUtils.isNotBlank(stdResp.getErrorMessage())){
            		throw new RuntimeException("Error found updating document: " + stdResp.getErrorMessage());
            	}
            }
            
        }
        catch(Exception e){
            if (e.getMessage()!=null&&!e.getMessage().contains("No remote services available")){
                throw new RuntimeException("Error saving Proposal. ",e);
            }
            logger.error("Error saving Proposal",e);
        }

        return cluProposalDTO;
    }
    


    /**
     * @see org.kuali.student.lum.lu.ui.course.client.service.CluProposalRpcService#deleteProposal(java.lang.String)
     */
    @Override
    public Boolean deleteProposal(String id) {
        // TODO Will Gomes - THIS METHOD NEEDS JAVADOCS
        return Boolean.FALSE;
    }
    
	@Override
	public Boolean submitProposal(CluProposalModelDTO cluProposalDTO) {
		try {
            if(simpleDocService==null){
            	throw new RuntimeException("Workflow Service is unavailable");
            }

            //get a user name
            String username = getCurrentUser();

	        //Get the cluInfo
	        CluInfo cluInfo = getCluInfo(cluProposalDTO);
	        ProposalInfo proposalInfo = getProposalInfo(cluProposalDTO);
	        
            //Get the workflow ID
            DocumentDetailDTO docDetail = workflowUtilityService.getDocumentDetailFromAppId(WF_TYPE_CLU_DOCUMENT, proposalInfo.getId());

            if(docDetail==null){
            	throw new RuntimeException("Error found gettting document. " );
            }

            String routeComment = "Routed By CluProposalService";

            StandardResponse stdResp = simpleDocService.route(docDetail.getRouteHeaderId().toString(), username, docDetail.getDocTitle(), getCluProposalDocContent(cluInfo,proposalInfo), routeComment);

            if(stdResp==null||StringUtils.isNotBlank(stdResp.getErrorMessage())){
        		throw new RuntimeException("Error found routing document: " + stdResp.getErrorMessage());
        	}


        } catch (Exception e) {
            e.printStackTrace();
        }

        return Boolean.TRUE;
	}

    /**
     * Retreives a CluProposalMoelDTO given a proposal id.
     */
    public CluProposalModelDTO getProposal(String proposalId){
        MapContext ctx = new MapContext();
        CluProposalModelDTO cluProposalDTO = new CluProposalModelDTO();

    	boolean authorized=true;
        try {
            logger.debug("Retreiving clu proposal with proposal id " + proposalId);
            
            //Get proposal
            ProposalInfo proposalInfo = proposalService.getProposal(proposalId);
            ModelDTO proposalModelDTO = ((ModelDTOType)cluProposalDTO.get(PROPOSAL_INFO_KEY)).get(); 
            proposalModelDTO.copyFrom((ModelDTO)ctx.fromBean(proposalInfo));
            
            //Get proposal's clu reference id
            List<String> references = proposalInfo.getProposalReference();
            //FIXME: Better error handling if more than one reference or no reference
            assert(references.size() == 1);
            String cluId = references.get(0);

        	try{
	        	String QUALIFICATION_PROPOSAL_ID = "proposalId";
	        	String DOCUMENT_TYPE_NAME = "documentTypeName";
	        	AttributeSet qualification = new AttributeSet();
	        	qualification.put(QUALIFICATION_PROPOSAL_ID, proposalInfo.getId());
	  			qualification.put(DOCUMENT_TYPE_NAME, "CluDocument");
	  			authorized = permissionService.isAuthorized(getCurrentUser(), "KS-LUM", "Open Document", null, qualification);
        	}catch(Exception e){
        		logger.info("Error calling permission service. ", e);
        	}
            
            //Get clu
            CluInfo cluInfo = service.getClu(cluId);
            ModelDTO cluModelDTO = ((ModelDTOType)cluProposalDTO.get(CLU_INFO_KEY)).get(); 
            cluModelDTO.copyFrom((ModelDTO)ctx.fromBean(cluInfo));
            
            getCourseFormats(cluProposalDTO);

        } catch (Exception e) {
            logger.error("Error getting Proposal. ",e);
        }

    	if(!authorized){
    		throw new RuntimeException("User is not authorized to open this document");
    	}
    	
        return cluProposalDTO;
    }
    
    private void getCourseFormats(CluProposalModelDTO cluProposalDTO){
        //TODO: Find all clu relations and load them
    }
    

	private String getCurrentUser() {
        String username=DEFAULT_USER_ID;//FIXME this is bad, need to find some kind of mock security context
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null){
        	Object obj = auth.getPrincipal();
        	if (obj instanceof UserDetails) {
            	username = ((UserDetails)obj).getUsername();
            } else {
            	username = obj.toString();
            }
        }else{
        	username=(String)this.getThreadLocalRequest().getSession().getAttribute("backdoorId");
        }
		return username;
	}
	
	private String getCluProposalDocContent(CluInfo cluInfo, ProposalInfo proposalInfo){
    	try{

    		CluProposalDocInfo docContent = new CluProposalDocInfo();
    		
    		if(null == cluInfo){
    			throw new RuntimeException("CluInfo must be set.");
    		}
    		
    		String cluId = cluInfo.getId()==null?"":cluInfo.getId(); 
    		String adminOrg = cluInfo.getAdminOrg()==null?"":cluInfo.getAdminOrg(); 
    		String proposalId = proposalInfo.getId()==null?"":proposalInfo.getId();
    		
    		docContent.setCluId(cluId);
            docContent.setOrgId(adminOrg);
            docContent.setProposalId(proposalId);
            
    		JAXBContext context = JAXBContext.newInstance(docContent.getClass());
    		Marshaller marshaller = context.createMarshaller();
            StringWriter writer = new StringWriter();
    		marshaller.marshal(docContent, writer);
    		return writer.toString();

    	}catch(Exception e){
    		throw new RuntimeException("Error creating Document content for Clu. ", e);
    	}
	}
    
	@Override
    public Boolean adhocRequest(String docId, String recipientPrincipalId, String requestType, String annotation) {
        if (simpleDocService == null) {
            throw new RuntimeException("Workflow Service is unavailable");
        }

        try {
            // get a user name
            String username = getCurrentUser();

            String fyiAnnotation = "FYI by CluProposalService";
            String approveAnnotation = "Approve by CluProposalService";
            String ackAnnotation = "Ack by CluProposalService";
            // create and route a Collaborate workflow
            // Get the document app Id
            /*
             * CluProposal cluProposal = getCluProposalFromWorkflowId(docId); CluInfo cluInfo = cluProposal.getCluInfo();
             * String workflowDocTypeId = "CluCollaboratorDocument";// TODO make // sure this // name is // correct
             * DocumentResponse docResponse = simpleDocService.create(username, docId, workflowDocTypeId,
             * cluInfo.getOfficialIdentifier() .getLongName()); if (StringUtils.isNotBlank(docResponse.getErrorMessage())) {
             * throw new RuntimeException("Error found creating document: " + docResponse.getErrorMessage()); }
             */
            // Do the adHoc
            // Error with the simpleDocService.requestAdHocXXXToPrincipal method. the workflow document is getting routed to the wrong user.
            // Please change the order once this has been fixed in the rice api. 
            if (requestType.equals("FYI")) {
//                StandardResponse stdResp = simpleDocService.requestAdHocFyiToPrincipal(docId, username, recipientPrincipalId, fyiAnnotation);
                StandardResponse stdResp = simpleDocService.requestAdHocFyiToPrincipal(docId,recipientPrincipalId, username, fyiAnnotation);
                if (stdResp == null || StringUtils.isNotBlank(stdResp.getErrorMessage())) {
                    throw new RuntimeException("Error found in Adhoc FYI: " + stdResp.getErrorMessage());
                }
            }
            if (requestType.equals("Approve")) {
//                StandardResponse stdResp = simpleDocService.requestAdHocApproveToPrincipal(docId, username, recipientPrincipalId, approveAnnotation);
                StandardResponse stdResp = simpleDocService.requestAdHocApproveToPrincipal(docId, recipientPrincipalId,username, approveAnnotation);
                if (stdResp == null || StringUtils.isNotBlank(stdResp.getErrorMessage())) {
                    throw new RuntimeException("Error found in Adhoc Approve: " + stdResp.getErrorMessage());
                }
            }
            if (requestType.equals("Acknowledge")) {
//                StandardResponse stdResp = simpleDocService.requestAdHocAckToPrincipal(docId, username, recipientPrincipalId, ackAnnotation);
                StandardResponse stdResp = simpleDocService.requestAdHocAckToPrincipal(docId,recipientPrincipalId,username, ackAnnotation);
                if (stdResp == null || StringUtils.isNotBlank(stdResp.getErrorMessage())) {
                    throw new RuntimeException("Error found in Adhoc Ack: " + stdResp.getErrorMessage());
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
            return new Boolean(false);
        }
        return new Boolean(true);
    }
    
	public void setSimpleDocService(SimpleDocumentActionsWebService simpleDocService) {
		this.simpleDocService = simpleDocService;
	}

	public void setWorkflowUtilityService(WorkflowUtility workflowUtilityService) {
		this.workflowUtilityService = workflowUtilityService;
	}

	public OrganizationService getOrgService() {
		return orgService;
	}

	public void setOrgService(OrganizationService orgService) {
		this.orgService = orgService;
	}

    public ProposalService getProposalService() {
        return proposalService;
    }

    public void setProposalService(ProposalService proposalService) {
        this.proposalService = proposalService;
    }

	/**
	 * @param permissionService the permissionService to set
	 */
	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}
	
//    public void setApplicationStateManager(ApplicationStateManager applicationStateManager) {
//        this.applicationStateManager = applicationStateManager;
//    }    
}
