package cn.soaryang.elasticsearch.data;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;

import java.io.IOException;
import java.util.Date;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class ElasticsearchDataInsertUtil {


    public static void createData(TransportClient transportClient) throws IOException {
        for(int i=0; i<200; i++){
            IndexResponse response = transportClient.prepareIndex("twitter", "tweet",String.valueOf(i))
                    .setSource(jsonBuilder()
                            .startObject()
                            .field("user", "kimchy"+i)
                            .field("postDate", new Date())
                            .field("message", "trying out Elasticsearch")
                            .endObject()
                    )
                    .get();
        }
    }

    public static void bulkAddData(TransportClient transportClient) throws IOException {
        BulkRequestBuilder bulkRequest = transportClient.prepareBulk();

        for(int i=300; i<500; i++){
            IndexRequestBuilder indexRequestBuilder =transportClient.prepareIndex("twitter", "tweet", String.valueOf(i))
                    .setSource(jsonBuilder()
                            .startObject()
                            .field("user", "kimchy"+i)
                            .field("postDate", new Date())
                            .field("message", "trying out Elasticsearch")
                            .endObject()
                    );
            bulkRequest.add(indexRequestBuilder);
        }
        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            // process failures by iterating through each bulk response item
        }
    }

}
