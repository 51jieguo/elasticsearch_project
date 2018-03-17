package cn.soaryang.elasticsearch.util;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ElasticsearchUtil {

    public static ElasticsearchUtil elasticsearchUtil = null;

    public static ElasticsearchUtil getInstance(){
        if(elasticsearchUtil==null){
            synchronized (ElasticsearchUtil.class){
                if(elasticsearchUtil==null){
                    elasticsearchUtil = new ElasticsearchUtil();
                }
            }
        }
        return elasticsearchUtil;
    }

    public TransportClient getClient() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name", "my-application").put("client.transport.sniff", true).build();
        //你可以设置client.transport.sniff为true来使客户端去嗅探整个集群的状态，把集群中其它机器的ip地址加到客户端中，
        // 这样做的好处是一般你不用手动设置集群里所有集群的ip到连接客户端，它会自动帮你添加，并且自动发现新加入集群的机器

        //client.transport.ignore_cluster_name：设置为true时忽略集群名验证；
        //client.transport.ping_timeout：等待ping命令返回结果时间，默认为5秒；
        //client.transport.nodes_sampler_interval：节点之间互相ping，互连检测时间间隔；
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.8.103"), 9300));
        return client;
    }

    public static void close(TransportClient transportClient){
        if(transportClient!=null){
            transportClient.close();
        }
    }
}
