//package com.ds.firealarmrest;
//
//import java.io.IOException;
//
//import javax.ws.rs.container.ContainerRequestContext;
//import javax.ws.rs.container.ContainerResponseContext;
//import javax.ws.rs.container.ContainerResponseFilter;
//
//import org.glassfish.jersey.server.ContainerRequest;
//import org.glassfish.jersey.server.ContainerResponse;
//
//
//
//public class CORSFilter implements ContainerResponseFilter{
//
//	
//	public ContainerResponse filter(ContainerRequest requestContext, ContainerResponse responseContext)
//		{
//		
//		responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
//		responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
//		responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
//		responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
//		
//		return responseContext;
//		
//	}
////
////
////	@Override
////	public void filter(ContainerRequest requestContext, ContainerResponse responseContext)
////			throws IOException {
//// 
////		responseContext.getHttpHeader.add("Access-Control-Allow-Origin", "*");
////		
////		
////	} 
//
//	@Override
//	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
//			throws IOException {
//		// TODO Auto-generated method stub
//		
//	}
//	
//	
//
//}
