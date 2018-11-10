package com.ycx11.springboot;

import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.util.HashMap;

public class TestEs {

    public static Client client;

    @Before
    public  void conn() throws Exception{

        Settings settings = Settings.settingsBuilder()
                .put("cluster.name", "usst").build();
        client = TransportClient
                .builder()
                .settings(settings)
                .build()
                .addTransportAddress(
                        new InetSocketTransportAddress(InetAddress
                                .getByName("cdh01"), 9300))
                .addTransportAddress(
                        new InetSocketTransportAddress(InetAddress
                                .getByName("cdh02"), 9300))
                .addTransportAddress(
                        new InetSocketTransportAddress(InetAddress
                                .getByName("cdh03"), 9300));


    }

    @Test
    public void createIndex(){
        IndicesExistsResponse resp = client.admin().indices().prepareExists("searchdemo1").execute().actionGet();
        if(resp.isExists()){
            client.admin().indices().prepareDelete("searchdemo1").execute();
        }
        HashMap<String,Object> sets= new HashMap<>();
        sets.put("number_of_replicas",2);

        client.admin().indices().prepareCreate("searchdemo1").setSettings(sets).execute();
    }

    @Test
    public void test11(){
        System.out.println(LocalTime.now().toString("yyyyMMddhhmmss"));
    }
}
