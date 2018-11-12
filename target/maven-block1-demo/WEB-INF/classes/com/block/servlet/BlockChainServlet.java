package com.block.servlet;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.bcos.goldtoken.app.BcosApp;
//import com.block.service.app.BcosApp;
import com.block.service.FiscoBcosService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.block.response.ResponseUtil;
import com.block.response.RespResult;
import com.block.response.BalanceRespResult;
/**
 * Servlet implementation class blockChain
 */
//@WebServlet("/blockChainServlet")
public class BlockChainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(BlockChainServlet.class.getName());
	private ResponseUtil responseUtil;
	private FiscoBcosService fiscoBcosService = null;
	//BcosApp app = new BcosApp();
	public void init() throws ServletException {
		ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());

		responseUtil = (ResponseUtil) appContext.getBean("responseUtil");
		fiscoBcosService = (FiscoBcosService) appContext.getBean("fiscoBcosService");
		/*try {
			fiscoBcosService = new FiscoBcosService();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		logger.info("111service init successfully");
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlockChainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String headers = request.getHeader("Authorization");
		logger.info("serviceRequest-Authorization:" + headers);
	}*/

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String headers = request.getHeader("Authorization");
		logger.info("GetRequest-Authorization:" + headers);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// 消息头鉴权
			String headers = req.getHeader("Authorization");
			logger.info("POSTRequest-Authorization:" + headers);
			if (headers == null || "".equals(headers)) {
				logger.info("请求头参数Authorization为空");
				responseUtil.sendResponse(resp, RespResult.Common.APP_PERMISSION_DENYED);
				return;
			}
			/*String appkey = null;
			String timestamp = null;
			String signature = null;
			String[] header = headers.split(",");
			for (String h : header) {
				if (h != null && h.contains("appkey")) {
					appkey = h.substring(h.lastIndexOf("=") + 1, h.length());
				} else if (h != null && h.contains("timestamp")) {
					timestamp = h.substring(h.lastIndexOf("=") + 1, h.length());
				} else if (h != null && h.contains("signature")) {
					signature = h.substring(h.lastIndexOf("=") + 1, h.length());
				} else {
					logger.info("请求头鉴权失败,未获取到相关数据~~~");
					responseUtil.sendResponse(resp, RespResult.Common.APP_PERMISSION_DENYED);
					return;
				}
			}

			logger.info("appkey=[" + appkey + "]");
			logger.info("timestamp=[" + timestamp + "]");
			logger.info("signature=[" + signature + "]");

			if (!SignatureUtil.checkHeader(appkey, timestamp, signature)) {
				logger.info("请求头鉴权失败");
				responseUtil.sendResponse(resp, RespResult.Common.APP_PERMISSION_DENYED);
				return;
			}*/

			// 消息内容判空
			String jsonText = this.getReqMsg(req);
			logger.debug("Body:" + jsonText);
			if (jsonText == null || "".equals(jsonText)) {
				logger.info("请求消息内容为空");
				responseUtil.sendResponse(resp, RespResult.Common.PARAMFORMAT_ERROR);
				return;
			}

			RespResult result = null;
			BalanceRespResult balanceRespResult = null;
			String method = req.getPathInfo().toString().substring(1);
			logger.info("Received-method:" + method);
			if ("user_openaccount".equals(method)) {
				logger.debug("fiscoBcosService Body:" + jsonText);
				result = fiscoBcosService.useropenaccount(jsonText);
				logger.debug("user_openaccount result:" + result);
			} else if ("user_freezeAccount".equals(method)) {
				result = fiscoBcosService.userfreezeAccount(jsonText);
			} else if ("user_buy".equals(method)) {
				result = fiscoBcosService.userbuy(jsonText);
			} else if ("user_sell".equals(method)) {
				result = fiscoBcosService.usersell(jsonText);
			} else if ("user_getAccountBalance".equals(method)) {
				balanceRespResult = fiscoBcosService.usergetAccountBalance(jsonText);
			} else if ("user_getBankBalance".equals(method)) {
				balanceRespResult = fiscoBcosService.usergetBankBalance(jsonText);
			} else if ("deployContract".equals(method)) {
				result = fiscoBcosService.deployContract(jsonText);
				logger.debug("deployContract result:" + result);
			}
			else {
				result = RespResult.Common.URL_ERROR;
			}
			if(result != null)
			{
				responseUtil.sendResponse(resp, result);
			}
			else if(balanceRespResult != null)
			{
				responseUtil.sendResponse(resp, balanceRespResult);
			}
			return;

		} catch (Exception e) {
				
		}
		//doGet(request, response);
	}
	// 读取请求内容
	public String getReqMsg(HttpServletRequest request) {
		try {
			StringBuffer jsonTextBuf = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));

			String jsonLine = null;
			while ((jsonLine = br.readLine()) != null) {
				logger.debug(jsonLine);
				jsonTextBuf.append(jsonLine);
			}
			br.close();
			return jsonTextBuf.toString();
		} catch (Exception e) {
			logger.error("IO getReqMsg failed", e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String headers = request.getHeader("Authorization");
		logger.info("putRequest-Authorization:" + headers);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String headers = request.getHeader("Authorization");
		logger.info("DeleteRequest-Authorization:" + headers);
	}

	/**
	 * @see HttpServlet#doHead(HttpServletRequest, HttpServletResponse)
	 */
	protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String headers = request.getHeader("Authorization");
		logger.info("HeadRequest-Authorization:" + headers);
	}

}
