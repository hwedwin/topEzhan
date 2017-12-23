package com.topaiebiz.transport.expressage.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.nebulapaas.web.util.IllegalParamValidationUtils;
import com.topaiebiz.transport.expressage.dto.ExpressageDto;
import com.topaiebiz.transport.expressage.entity.ExpressageInfoEntity;
import com.topaiebiz.transport.expressage.entity.ExpressageSubscriptionLogEntity;
import com.topaiebiz.transport.expressage.entity.NoticeRequest;
import com.topaiebiz.transport.expressage.entity.NoticeResponse;
import com.topaiebiz.transport.expressage.entity.Result;
import com.topaiebiz.transport.expressage.entity.ResultItem;
import com.topaiebiz.transport.expressage.entity.TaskRequest;
import com.topaiebiz.transport.expressage.entity.TaskResponse;
import com.topaiebiz.transport.expressage.exception.ExpressageExceptionEnum;
import com.topaiebiz.transport.expressage.service.ExpressageService;
import com.topaiebiz.transport.expressage.util.HttpRequest;
import com.topaiebiz.transport.expressage.util.JacksonHelper;

/**
 * Description 处理快递100的处理器 
 * 
 * Author Aaron.Xue 
 *    
 * Date 2017年10月17日 下午8:56:51 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@RestController
@RequestMapping("/transport/expressage")
public class ExpressageController {
	
	/**访问路径，随时更改。*/
	private String urlPath = "http://101.132.186.242";
	
	@Autowired
	private ExpressageService expressageService;

	/**
	 * Description 添加快递信息。订阅成功后将数据存入表中，以便查询。
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param response
	 * @param param
	 * @throws Exception
	 */
	@RequestMapping(path = "/addExpressageInfo", method = RequestMethod.POST)
	@ResponseBody
	public String addExpressageInfo(String param) throws Exception {
		NoticeResponse resp = new NoticeResponse();
		try {
			NoticeRequest nReq = JacksonHelper.fromJSON(param, NoticeRequest.class);
			Result result = nReq.getLastResult();
			// 处理快递结果
			ExpressageInfoEntity entity = new ExpressageInfoEntity();
			//拷贝到entity
			BeanUtils.copyProperties(result, entity);
			ArrayList<ResultItem> dataArray = result.getData();
			StringBuffer sb = new StringBuffer();
			for (ResultItem resultItem : dataArray) {
				sb.append(resultItem.getFtime());
				sb.append("#");
				sb.append(resultItem.getContext());
				sb.append("$");
			}
			//isCheck字段ischeck字段对应，手动放
			entity.setIsCheck(result.getIscheck());
			//讲data放入entity
			entity.setData(sb.substring(0, sb.length()-1));
			expressageService.saveExpressageInfo(entity);
			resp.setResult(true);
			resp.setMessage("保存成功");
			resp.setReturnCode("200");
			return JacksonHelper.toJSON(resp); //这里必须返回，否则认为失败，过30分钟又会重复推送。
		} catch (Exception e) {
			resp.setResult(false);
			resp.setReturnCode("500");
			resp.setMessage("保存失败");
			resp.setMessage("保存失败" + e.getMessage());
			return JacksonHelper.toJSON(resp);//保存失败，服务端等30分钟会重复推送。
		}
	}
	
	/**
	 * Description 订阅快递信息,发快递的时候调这个方法。
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param response
	 * @param param
	 * @throws Exception
	 */
	@RequestMapping(path = "/subscriptionExpressage", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo subscriptionExpressage(@Valid ExpressageDto expressageDto, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			/**初始化非法参数的提示信息。*/
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			/**获取非法参数异常信息对象，并抛出异常。*/
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		TaskRequest req = new TaskRequest();
		req.setCompany(expressageDto.getCompany());
		req.setFrom(expressageDto.getFrom());
		req.setTo(expressageDto.getTo());
		req.setNumber(expressageDto.getNumber());
		//订阅成功的回调接口
		req.getParameters().put("callbackurl", urlPath + "/transport/expressage/addExpressageInfo");
		req.setKey("bqemwxUu9358");  //快递100给的key
		
		HashMap<String, String> p = new HashMap<String, String>(); 
		p.put("schema", "json");
		p.put("param", JacksonHelper.toJSON(req));
		
		//httpclent 调用快递100的接口
		String ret = HttpRequest.postData("http://www.kuaidi100.com/poll", p, "UTF-8");
		//返回结果
		TaskResponse resp = JacksonHelper.fromJSON(ret, TaskResponse.class);
		ExpressageSubscriptionLogEntity entity = new ExpressageSubscriptionLogEntity();
		entity.setCom(expressageDto.getCompany());
		entity.setNu(expressageDto.getNumber());
		entity.setIsSuccess(resp.getResult()?"1":"0");//1成功 0失败
		entity.setReturnCode(resp.getReturnCode());
		//保存订阅日志
		expressageService.saveExpressageSubscriptionLog(entity);
		if(resp.getResult()) {
			throw new GlobalException(ExpressageExceptionEnum.SUBSCRIBE_TO_THE_FAILURE);
		}
		//订阅成功
		return new ResponseInfo();
	}
	
	/**
	 * Description 查询快递信息，查询快递的时候调这个信息。
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param response
	 * @param param
	 * @throws Exception
	 */
	@RequestMapping(path = "/getExpressageInfo", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getExpressageInfo(@Valid ExpressageDto expressageDto, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			/**初始化非法参数的提示信息。*/
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			/**获取非法参数异常信息对象，并抛出异常。*/
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		expressageDto = expressageService.getExpressageInfo(expressageDto);
		return new ResponseInfo(expressageDto);
	}
	
	/**
	 * Description 获取所有快递公司信息
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(path = "/getLogisticsCompany", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getLogisticsCompany() throws Exception {
		return new ResponseInfo(expressageService.getListLogisticsCompany());
	}
	
	
}
