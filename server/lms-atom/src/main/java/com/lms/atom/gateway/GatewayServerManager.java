package com.lms.atom.gateway;

import com.lms.configuration.cache.ConfigurationPropertyCodes;
import com.lms.configuration.properties.service.ConfigurationPropertyService;
import com.lms.configuration.properties.storage.model.ConfigurationProperty;
import com.lms.gateway.GatewayServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Timer;
import java.util.TimerTask;

@Service
@Transactional
public class GatewayServerManager {

    private Logger logger = LoggerFactory.getLogger(GatewayServerManager.class);

    private GatewayServer gatewayServer;

    private final DeviceMessageHandlerFactoryImpl deviceMessageHandlerFactory;
    private final ConfigurationPropertyService configurationPropertyService;

    private Integer gatewayServerStartDelay = 20;
    private Integer serverPort = 15000;
    private Integer groupThreadNumber = 25;
    private Integer executorThreadNumber = 25;
    private Integer idleStateTimeout = 60 * 5;

    @Autowired
    public GatewayServerManager(ConfigurationPropertyService configurationPropertyService, DeviceMessageHandlerFactoryImpl deviceMessageHandlerFactory) {
        this.configurationPropertyService = configurationPropertyService;
        this.deviceMessageHandlerFactory = deviceMessageHandlerFactory;
    }

    @PostConstruct
    public void postConstruct() {
        ConfigurationProperty gatewayServerStartDelayProperty = configurationPropertyService.get(ConfigurationPropertyCodes.GATEWAYS_SERVER_START_DELAY);
        if (gatewayServerStartDelayProperty != null) {
            gatewayServerStartDelay = gatewayServerStartDelayProperty.getNumberValue();
        }
        initGatewayServer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                logger.info("------------------------ Starting Gateway Server ------------------------");
                try {
                    gatewayServer.start();
                } catch (InterruptedException e) {
                    logger.info("------------------------ Unable to start gateway server ------------------------");
                }
                logger.info("------------------------ Finished Starting Gateway Server ------------------------");
            }
        };
        new Timer().schedule(timerTask, gatewayServerStartDelay * 1000);
    }

    private void initGatewayServer() {
        ConfigurationProperty serverPortProperty = configurationPropertyService.get(ConfigurationPropertyCodes.GATEWAYS_SERVER_PORT);
        if (serverPortProperty != null) {
            serverPort = serverPortProperty.getNumberValue();
        }
        ConfigurationProperty groupThreadNumberProperty = configurationPropertyService.get(ConfigurationPropertyCodes.GATEWAYS_GROUP_THREAD_NUMBER);
        if (groupThreadNumberProperty != null) {
            groupThreadNumber = groupThreadNumberProperty.getNumberValue();
        }
        ConfigurationProperty executorThreadNumberProperty = configurationPropertyService.get(ConfigurationPropertyCodes.GATEWAYS_EXECUTOR_THREAD_NUMBER);
        if (executorThreadNumberProperty != null) {
            executorThreadNumber = executorThreadNumberProperty.getNumberValue();
        }
        ConfigurationProperty idleStateTimeoutProperty = configurationPropertyService.get(ConfigurationPropertyCodes.GATEWAYS_IDLE_STATE_TIMEOUT);
        if (idleStateTimeoutProperty != null) {
            idleStateTimeout = idleStateTimeoutProperty.getNumberValue();
        }
        gatewayServer = new GatewayServer(serverPort, executorThreadNumber, groupThreadNumber, idleStateTimeout, deviceMessageHandlerFactory);
    }

    @PreDestroy
    public void shutdown() {
        logger.info("------------------------ Shutting down ------------------------");
        gatewayServer.shutdown();
        logger.info("------------------------ Shutting down finished ------------------------");
    }
}
