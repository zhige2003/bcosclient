package com.block.response;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.block.response.RespResult;
import com.block.response.BalanceRespResult;
import com.google.gson.Gson;

public class ResponseUtil {
	protected static Logger logger = LogManager.getLogger(ResponseUtil.class);

	public void sendResponse(HttpServletResponse resp, RespResult result) {
		try {
			CommonResponse response = new CommonResponse();
			String code = result.getCode();
			String description = result.getDescription();
			logger.info("..code:" + code + " , ..description:" + description);
			response.setCode(code);
			response.setDescription(description);

			Gson gson = new Gson();
			String jsonResp = gson.toJson(response);
			resp.setContentType("application/json;charset=UTF-8");
			resp.setStatus(200);
			PrintWriter pw = resp.getWriter();
			pw.print(jsonResp);
			pw.flush();
			pw.close();
			logger.info("MSG-RESP:" + jsonResp);
		} catch (IOException e) {
			logger.info("response error");
			e.printStackTrace();
		}
	}
	
	public void sendResponse(HttpServletResponse resp, BalanceRespResult result) {
		try {
			logger.info("..code:" + result.getCode() + " , ..description:" + result.getDescription() + " , ..amount:" + result.getAmount());
			
			Gson gson = new Gson();
			String jsonResp = gson.toJson(result);
			resp.setContentType("application/json;charset=UTF-8");
			resp.setStatus(200);
			PrintWriter pw = resp.getWriter();
			pw.print(jsonResp);
			pw.flush();
			pw.close();
			logger.info("MSG-RESP:" + jsonResp);
		} catch (IOException e) {
			logger.info("response error");
			e.printStackTrace();
		}
	}

}
