/**
 * 
 */
package com.topaiebiz.elasticsearch.search;

import com.topaiebiz.elasticsearch.dto.SearchPageDto;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Description： 搜索引擎Elastics 调用类
 * 
 * 
 * Author hxpeng
 * 
 * Date 2017年11月4日 下午3:11:14
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

public class ElasticSearchClient {

	private final static Logger logger = LoggerFactory.getLogger(ElasticSearchClient.class);
	
	// 搜索服务器ip
	private final static String TRANSPORT_IP = "106.14.1.178";
	// 搜索服务器端口
	private final static Integer TRANSPORT_PORT = 9300;
	// 搜索返回数据条数
//	private final static Integer RESULT_SIZE = 100;
	
	private static TransportClient client = null;
	
//	Settings settings = Settings.builder().put("cluster.name", "elasticsearch")
			//配置自动嗅集群
//			.put("client.transport.sniff",true)
//			.build();
	
	//TransportClient client = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(TRANSPORT_IP), TRANSPORT_PORT));
	
	/**
	 * 
	 * Description： 搜索调用方法
	 * 
	 * Author hxpeng   
	 * 
	 * @param params like:"婴儿"
	 * @throws UnknownHostException
	 */
	@SuppressWarnings("resource")
	public static List<Long> search(String params,SearchPageDto searchPageDto) throws UnknownHostException {
		if(StringUtils.isEmpty(params)){
			return null;
		}
		List<Long> resultList = new ArrayList<>();
		// 设置新集群名称
		
		if( client==null ) {
			Settings settings = Settings.builder().put("cluster.name", "elasticsearch")
					//配置自动嗅集群
//		.put("client.transport.sniff",true)
					.build();
			client = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(TRANSPORT_IP), TRANSPORT_PORT));
		}
		
		SearchResponse response = client.prepareSearch("goods")
				// .setTypes("goods")
				// 设置搜索算法/类型
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				// 设置搜索器类型，通过Strig类型参数搜索
				.setQuery(QueryBuilders.queryStringQuery(params))
				// 设置过滤器，age > 12 && < 18
				//.setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))
				// 设置搜索起始的索引,默认为0
				.setFrom(searchPageDto.getPageSize() * (searchPageDto.getCurrentPage() - 1))
				// 设置返回值大小条数，默认为10条
				.setSize(searchPageDto.getPageSize())
				.setExplain(true).get();

		logger.info("=====================================================================");
		logger.info("查寻结果response ：" + response.toString());
		logger.info("=====================================================================");
		
		SearchHit[] hitsArray = response.getHits().getHits();
		
		if(null != hitsArray && hitsArray.length > 0){
			for (SearchHit searchHit : hitsArray) {
				// 返回结果 ID主键
				String id = searchHit.getId();
				if(!StringUtils.isEmpty(id)){
					resultList.add(Long.valueOf(id));
				}
			}
		}
//		client.close();
		return resultList;
	}


//	public static void main(String[] args) {
//		SearchPageDto searchPageDto = new SearchPageDto();
//		searchPageDto.setCurrentPage(3);
//		searchPageDto.setPageSize(2);
//		try {
//			logger.info(ElasticSearchClient.search("婴儿", searchPageDto).toString());
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}
//
//	}

}
