package cn.soaryang.elasticsearch;

import cn.soaryang.elasticsearch.data.ElasticsearchDataInsertUtil;
import cn.soaryang.elasticsearch.data.spider.DoubanSourceDataUtil;
import cn.soaryang.elasticsearch.util.ElasticsearchUtil;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.rest.RestStatus;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.util.Date;

import static org.elasticsearch.common.xcontent.XContentFactory.*;


public class ElasticsearchTest {


    public static void main(String[] args) throws IOException {
        TransportClient transportClient = ElasticsearchUtil.getInstance().getClient();
        //createData(transportClient);
        //getMultiIndex(transportClient);
        //ElasticsearchDataInsertUtil.bulkAddData(transportClient);

        //DoubanSourceDataUtil.daobanData();

        ElasticsearchUtil.close(transportClient);
    }
}
