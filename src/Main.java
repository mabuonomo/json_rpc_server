import java.net.InetAddress;
import java.util.LinkedList;
import java.util.List;

import com.googlecode.jsonrpc4j.JsonRpcServer;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Error;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.server.Dispatcher;
import com.thetransactioncompany.jsonrpc2.server.MessageContext;
import com.thetransactioncompany.jsonrpc2.server.RequestHandler;

public class Main {

	// Implements a handler for an "echo" JSON-RPC method
	public static class EchoHandler implements RequestHandler {

		// Reports the method names of the handled requests
		public String[] handledRequests() {
			
			return new String[] { "echo" };
		}

		// Processes the requests
		public JSONRPC2Response process(JSONRPC2Request req, MessageContext ctx) {

			if (req.getMethod().equals("echo")) {

				// Echo first parameter

				List params = (List) req.getParams();

				Object input = params.get(0);

				return new JSONRPC2Response(input, req.getID());
			} else {

				// Method name not supported

				return new JSONRPC2Response(JSONRPC2Error.METHOD_NOT_FOUND,
						req.getID());
			}
		}
	}

	public static void main(String[] args) {

		// Create a new JSON-RPC 2.0 request dispatcher
//		Dispatcher dispatcher = new Dispatcher();
//		
//		// Register the "echo", "getDate" and "getTime" handlers with it
//		dispatcher.register(new EchoHandler());
//				
//
//		// Simulate an "echo" JSON-RPC 2.0 request
//		List echoParam = new LinkedList();
//		echoParam.add("Hello world!");
//
//		JSONRPC2Request req = new JSONRPC2Request("echo", echoParam,
//				"req-id-01");
//		System.out.println("Request: \n" + req);
//
//		JSONRPC2Response resp = dispatcher.process(req, null);
//		System.out.println("Response: \n" + resp);

		JsonRpcServer jsonRpcServer = new JsonRpcServer(8080);

		// create the stream server
		int maxThreads = 50;
		int port = 1420;
		InetAddress bindAddress = InetAddress.getByName("...");
		StreamServer streamServer = new StreamServer(
		    jsonRpcServer, maxThreads, port, bindAddress);

		// start it, this method doesn't block
		streamServer.start();
	}
}
