package com.yhn.webappcyberattacksdemoapp.xsrf.page;

import com.yhn.webappcyberattacksdemoapp.xsrf.page.model.request.ChangeEmailRequestModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/xsrf/email/change")
public class EmailController {

    @GetMapping
    public String getChangeEmailPage() {
        return "change-email";
    }

    @PostMapping
    public String changeEmail(@ModelAttribute ChangeEmailRequestModel model) {
        return "banking-main";
    }
}
