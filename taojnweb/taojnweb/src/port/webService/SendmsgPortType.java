package webService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * 
 */
@WebService(name = "sendmsgPortType", targetNamespace = "http://DefaultNamespace")
public interface SendmsgPortType {

	/**
	 * 
	 * @param in1
	 * @param in0
	 * @return returns java.lang.String
	 */
	@WebMethod
	@WebResult(name = "out", targetNamespace = "http://DefaultNamespace")
	@RequestWrapper(localName = "uploadFile", targetNamespace = "http://DefaultNamespace", className = "webService.UploadFile")
	@ResponseWrapper(localName = "uploadFileResponse", targetNamespace = "http://DefaultNamespace", className = "webService.UploadFileResponse")
	public String uploadFile(
			@WebParam(name = "in0", targetNamespace = "http://DefaultNamespace") byte[] in0,
			@WebParam(name = "in1", targetNamespace = "http://DefaultNamespace") String in1);

	/**
	 * 
	 * @param in1
	 * @param in0
	 * @return returns java.lang.String
	 */
	@WebMethod(operationName = "sendmsg_st")
	@WebResult(name = "out", targetNamespace = "http://DefaultNamespace")
	@RequestWrapper(localName = "sendmsg_st", targetNamespace = "http://DefaultNamespace", className = "webService.SendmsgSt")
	@ResponseWrapper(localName = "sendmsg_stResponse", targetNamespace = "http://DefaultNamespace", className = "webService.SendmsgStResponse")
	public String sendmsgSt(
			@WebParam(name = "in0", targetNamespace = "http://DefaultNamespace") String in0,
			@WebParam(name = "in1", targetNamespace = "http://DefaultNamespace") String in1);

	/**
	 * 
	 * @param in1
	 * @param in0
	 * @return returns java.lang.String
	 */
	@WebMethod(operationName = "sendmsg_add")
	@WebResult(name = "out", targetNamespace = "http://DefaultNamespace")
	@RequestWrapper(localName = "sendmsg_add", targetNamespace = "http://DefaultNamespace", className = "webService.SendmsgAdd")
	@ResponseWrapper(localName = "sendmsg_addResponse", targetNamespace = "http://DefaultNamespace", className = "webService.SendmsgAddResponse")
	public String sendmsgAdd(
			@WebParam(name = "in0", targetNamespace = "http://DefaultNamespace") String in0,
			@WebParam(name = "in1", targetNamespace = "http://DefaultNamespace") String in1);

	/**
	 * 
	 * @return returns java.lang.String
	 */
	@WebMethod(operationName = "server_st")
	@WebResult(name = "out", targetNamespace = "http://DefaultNamespace")
	@RequestWrapper(localName = "server_st", targetNamespace = "http://DefaultNamespace", className = "webService.ServerSt")
	@ResponseWrapper(localName = "server_stResponse", targetNamespace = "http://DefaultNamespace", className = "webService.ServerStResponse")
	public String serverSt();

	/**
	 * 
	 * @param in1
	 * @param in0
	 * @return returns java.lang.String
	 */
	@WebMethod
	@WebResult(name = "out", targetNamespace = "http://DefaultNamespace")
	@RequestWrapper(localName = "sendmsg", targetNamespace = "http://DefaultNamespace", className = "webService.Sendmsg")
	@ResponseWrapper(localName = "sendmsgResponse", targetNamespace = "http://DefaultNamespace", className = "webService.SendmsgResponse")
	public String sendmsg(
			@WebParam(name = "in0", targetNamespace = "http://DefaultNamespace") String in0,
			@WebParam(name = "in1", targetNamespace = "http://DefaultNamespace") String in1);

}
