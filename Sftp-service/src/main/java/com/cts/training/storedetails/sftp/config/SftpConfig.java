package com.cts.training.storedetails.sftp.config;

import java.io.File;import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.integration.annotation.Poller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileNameGenerator;
import org.springframework.integration.file.filters.AcceptOnceFileListFilter;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.file.remote.synchronizer.AbstractInboundFileSynchronizer;
import org.springframework.integration.sftp.filters.SftpSimplePatternFileListFilter;
import org.springframework.integration.sftp.inbound.SftpInboundFileSynchronizer;
import org.springframework.integration.sftp.inbound.SftpInboundFileSynchronizingMessageSource;
import org.springframework.integration.sftp.outbound.SftpMessageHandler;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.scheduling.annotation.Scheduled;

import com.cts.training.storedetails.sftp.model.StoreDetails;
import com.cts.training.storedetails.sftp.repository.StoreRepository;
import com.cts.training.storedetails.sftp.service.StoreDetailsService;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.jcraft.jsch.ChannelSftp.LsEntry;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class SftpConfig {
      
	@Autowired
	public StoreDetailsService service;

	@Value("${sftp.ip}")
	private String sftpHostIp;

	@Value("${sftp.port}")
	private int sftphostPort;

	@Value("${sftp.user}")
	private String sftpHostUser;

	@Value("${sftp.password}")
	private String sftpHostPassword;

	@Value("${sftp.remote.directorypath}")
	private String sftpRemoteDirectoryDownloadHost;
	
	
	
	@Bean
	public SessionFactory<LsEntry> sftpSessionFactory() {
		DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory(true);
		factory.setHost(sftpHostIp);
		factory.setPort(sftphostPort);
		factory.setUser(sftpHostUser);
		factory.setPassword(sftpHostPassword);
		factory.setAllowUnknownKeys(true);
		return new CachingSessionFactory<LsEntry>(factory);
	}
	@Bean
	public SftpInboundFileSynchronizer sftpInboundFileSynchronizer() {
		SftpInboundFileSynchronizer fileSynchronizer = new SftpInboundFileSynchronizer(sftpSessionFactory());
		fileSynchronizer.setDeleteRemoteFiles(false);
		fileSynchronizer.setRemoteDirectory(sftpRemoteDirectoryDownloadHost);
	    fileSynchronizer.setFilter(new SftpSimplePatternFileListFilter("*.csv"));
		return fileSynchronizer;
	}

	@Bean
	@InboundChannelAdapter(channel = "sftpChannel", poller = @Poller(fixedDelay = "5000"))
	public MessageSource<File> sftpMessageSource() {
		SftpInboundFileSynchronizingMessageSource source = new SftpInboundFileSynchronizingMessageSource(sftpInboundFileSynchronizer());
	    
		source.setLocalDirectory(new File("tmp"));
		source.setAutoCreateLocalDirectory(true);
		
		source.setLocalFilter(new AcceptOnceFileListFilter<File>());
		return source;
	}
	@Bean
	@ServiceActivator(inputChannel = "sftpChannel")
	public MessageHandler handler() {
		return new MessageHandler() {

			@Override
			public void handleMessage(Message<?> message) throws MessagingException {
       try {
			log.info("file recived"+ "  "+((File) message.getPayload()).getName());
			
				File file = (File)message.getPayload();
                       service.getall(file);
                       service.saveStoreData(file);
                     log.info("file process"+"  "+file);
           file.delete();
			}catch(IOException e) {
				log.error("ERROR "+e);
				
			}

		}
		};
	}

}
