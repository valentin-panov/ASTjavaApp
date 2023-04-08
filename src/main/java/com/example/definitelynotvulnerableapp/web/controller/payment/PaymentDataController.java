package com.example.definitelynotvulnerableapp.web.controller.payment;

import com.example.definitelynotvulnerableapp.domain.model.PaymentData;
import com.example.definitelynotvulnerableapp.domain.model.UserData;
import com.example.definitelynotvulnerableapp.domain.repository.PaymentDataRepository;
import com.example.definitelynotvulnerableapp.domain.repository.UserRepository;
import com.example.definitelynotvulnerableapp.security.user.CurrentUserService;
import com.example.definitelynotvulnerableapp.web.controller.SignupForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Secured({"ROLE_user", "ROLE_admin"})
class PaymentDataController {

    @Autowired
    private PaymentDataRepository paymentDataRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CurrentUserService currentUserService;

    @RequestMapping(value = "payment/{username}", method = RequestMethod.GET)
    public String viewPayment(@PathVariable("username") String username, Model model) {
        UserData userData = userRepository.findUserByName(username);
        PaymentData paymentData = userData.getPaymentData();
        model.addAttribute("payment", paymentData == null ? new PaymentData() : paymentData);
        return "payment/payment-data";
    }

    @RequestMapping(value = "payment/edit", method = RequestMethod.GET)
    public String editPaymentForm(Model model) {
        PaymentData paymentData = currentUserService.getCurrentUserData().getPaymentData();
        model.addAttribute("payment", paymentData == null ? new PaymentData() : paymentData);
        return "payment/payment-data-edit";
    }

    @RequestMapping(value = "payment/edit", method = RequestMethod.POST)
    public String editPayment(@ModelAttribute PaymentData paymentData) {
        PaymentData saved = paymentDataRepository.save(paymentData);
        UserData userData = currentUserService.getCurrentUserData();
        userData.setPaymentData(saved);
        userRepository.save(userData);
        return "redirect:/payment/" + userData.getName();
    }
}
