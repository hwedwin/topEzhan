//package com.topaiebiz.elasticsearch;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.action.search.SearchType;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//
//public class App {
//    @SuppressWarnings({ "unchecked", "resource" })
//    public static void main(String[] args) {
//        // on startup
//
//        try {
//            //设置新集群名称
//            Settings settings = Settings.builder()
//                .put("cluster.name", "elasticsearch")
//                .build();
//
//            TransportClient client = new PreBuiltTransportClient(settings)
//                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("106.14.1.178"), 9300));
//
//
//
//            SearchResponse response = client.prepareSearch("goods")
//                    //.setTypes("goods")
//                    .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//                    .setQuery(QueryBuilders.queryStringQuery("婴儿"))
//                    //.setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
//                    .setFrom(0)
//                    .setSize(100)
//
//                    .setExplain(true)
//                    .get();
//            SearchHit[] hits = response.getHits().getHits();
//            for (SearchHit searchHit : hits) {
//            	String id = searchHit.getId();
//            	//item表查商品
//			}
//
//            System.out.println(response);
//            // on shutdown
//            client.close();
//        } catch (UnknownHostException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//    }
//}