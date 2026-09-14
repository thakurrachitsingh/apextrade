package com.rachit.apextrade;

import com.rachit.apextrade.matching.MatchingEngine;
import com.rachit.apextrade.matching.OrderBook;
import com.rachit.apextrade.matching.OrderPool;
import com.rachit.apextrade.egress.EgressDisruptor;
import com.rachit.apextrade.ingress.IngressDisruptor;
import com.rachit.apextrade.ingress.OrderIngressHandler;
import com.rachit.apextrade.network.EngineServer;

public class EngineMain {

    static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("   ApexTrade Matching Engine Core (S2) Daemon    ");
        System.out.println("   Deterministic Low-Latency In-Memory Matcher   ");
        System.out.println("=================================================");

        OrderPool orderPool = new OrderPool();
        OrderBook orderBook = new OrderBook("BTC/USDT", orderPool);

        EgressDisruptor egressDisruptor = new EgressDisruptor();

        MatchingEngine matchingEngine = new MatchingEngine(orderBook, 50000L, egressDisruptor.getPublisher());

        IngressDisruptor ingressDisruptor = new IngressDisruptor(matchingEngine);
        OrderIngressHandler ingressHandler = new OrderIngressHandler(ingressDisruptor.getPublisher());

        int tcpPort = Integer.parseInt(System.getenv().getOrDefault("APEXTRADE_ENGINE_PORT", "9000"));
        EngineServer engineServer = new EngineServer(tcpPort, ingressHandler, egressDisruptor.getDispatcher());
        engineServer.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("[S2 Engine] Gracefully shutting down Disruptors...");
            ingressDisruptor.close();
            egressDisruptor.close();
            System.out.println("[S2 Engine] Shutdown complete.");
        }, "engine-shutdown-hook"));

        System.out.println("[S2 Engine] Ready for high-throughput order execution on port " + tcpPort);
    }
}
