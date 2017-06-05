package com.wqz.ds.thrift.core;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wqz.ds.thrift.core.PostFace.Iface;
import com.wqz.ds.thrift.core.PostFace.Processor;

@Service
public class RpcService
{
	@Autowired
	PostFaceImpl postFaceImpl;
	RpcThread rpcThread = new RpcThread();
	
	@PostConstruct
	public void initRpc()
	{
		rpcThread.start();
	}
	
	public class RpcThread extends Thread 
	{
		@Override
		public void run()
		{
			try
			{
				TServerSocket serverTransport = new TServerSocket(1234);

				Processor<Iface> process = new Processor<Iface>(postFaceImpl);
				Factory portFactory = new Factory(true, true);
				Args args = new Args(serverTransport);
				args.processor(process);
				args.protocolFactory(portFactory);

				TServer server = new TThreadPoolServer(args);
				server.serve();
			} 
			catch (TTransportException e)
			{
				e.printStackTrace();
			}
		}
	}

	@PreDestroy
	@SuppressWarnings("deprecation")
	public void exitRpc()
	{
		rpcThread.destroy();
	}
}
