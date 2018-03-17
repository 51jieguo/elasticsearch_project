package cn.soaryang.elasticsearch.data;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.client.transport.TransportClient;

public class ElasticsearchDataFindUtil {

    public static void getMultiIndex(TransportClient transportClient){
        MultiGetResponse multiGetItemResponses = transportClient.prepareMultiGet()
                .add("twitter", "tweet", "1")
                .add("twitter", "tweet", "2", "3", "4")
                .add("another", "type", "foo")
                .get();

        for (MultiGetItemResponse itemResponse : multiGetItemResponses) {
            GetResponse response = itemResponse.getResponse();
            if (response!=null && response.isExists()) {
                String json = response.getSourceAsString();
                System.out.println(json);
            }
        }
    }

    public static void getIndex(TransportClient transportClient){
        GetResponse response = transportClient.prepareGet("twitter", "tweet", "1").get();
        // Index name
        String _index = response.getIndex();
        System.out.println(_index);
        // Type name
        String _type = response.getType();
        System.out.println(_type);
        // Document ID (generated or not)
        String _id = response.getId();
        System.out.println(_id);
        // Version (if it's the first time you index this document, you will get: 1)
        long _version = response.getVersion();
        System.out.println(_version);
    }
}
