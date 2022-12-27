package com.yhn.webappcyberattacksdemoapp.xxe;

import com.yhn.webappcyberattacksdemoapp.xxe.model.XXEProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/xxe")
@RequiredArgsConstructor
public class XXEController {
    private final XXEService xxeService;

    @PutMapping("/products/{productId}")
    public ResponseEntity<XXEProductResponse> searchProductWIthXXEVulnerability(@PathVariable Long productId,
                                                                                @RequestBody String searchXml) {
        XXEProductResponse response = xxeService.updateXXEVulnerableProductWithXML(productId, searchXml);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products")
    public ResponseEntity<List<XXEProductResponse>> getAllXXEVulnerableProducts() {
        List<XXEProductResponse> responses = xxeService.getAllXXEVulnerableProducts();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<XXEProductResponse> getXXEVulnerableProduct(@PathVariable Long productId) {
        XXEProductResponse response = xxeService.getXXEVulnerableProduct(productId);
        return ResponseEntity.ok(response);
    }
}
