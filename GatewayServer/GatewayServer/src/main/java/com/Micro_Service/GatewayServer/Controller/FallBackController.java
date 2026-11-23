package com.Micro_Service.GatewayServer.Controller;

import com.Micro_Service.GatewayServer.ApiWrapperClass.ApiWrapperClass;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {
@RequestMapping("/fallBackMethod")
 public ResponseEntity<ApiWrapperClass<String>> fallBackMethod(){
    return ResponseEntity.status(503).body(new ApiWrapperClass<>("Service Unavailable",false,"Sorry for the InConvenience,Please try again later ",null));
}
}
