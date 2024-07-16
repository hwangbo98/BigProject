package com.project.dstj.controller;

import com.project.dstj.dto.MemberInfoInquiryDto;
import com.project.dstj.service.MemInfoInquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/members")
public class MemInfoInquiryController {

    @Autowired
    private MemInfoInquiryService memInfoInquiryService;

    @GetMapping("/{userPK}")
    public MemberInfoInquiryDto getMemberDetails(@PathVariable Long userPK) {
        return memInfoInquiryService.getMemberDetails(userPK);
    }
}
