package com.yhn.webappcyberattacksdemoapp.xsrf.page;

import com.yhn.webappcyberattacksdemoapp.xsrf.page.model.request.WireTransferRequestModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/xsrf/online-banking")
public class OnlineBankingController {

    private final OnlineBankingService bankingService;

    @GetMapping
    public String getMainBankingPage() {
        return "banking-main";
    }

    @GetMapping("/wire-transfer")
    public String getWireTransferPage() {
        return "wire-transfer";
    }

    @PostMapping("/wire-transfer")
    public String submitWireTransfer(@ModelAttribute WireTransferRequestModel request) {
        bankingService.makeMoneyWireTransferTransaction(request);
        return "created-wire-transfer";
    }
}
