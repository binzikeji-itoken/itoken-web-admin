package com.binzikeji.itoken.web.admin.service;

import com.binzikeji.itoken.common.web.service.BaseClientService;
import com.binzikeji.itoken.web.admin.service.fallback.AdminServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "itoken-service-admin", fallback = AdminServiceFallback.class)
public interface AdminService extends BaseClientService {

    @RequestMapping(value = "/v1/admin/page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    String page(
            @PathVariable(value = "pageNum") int pageNum,
            @PathVariable(value = "pageSize") int pageSize,
            @RequestParam(required = false, value = "tbPostsPostJson") String tbAdminJson);

    @RequestMapping(value = "/v1/admin/{userCode}", method = RequestMethod.GET)
    String get(@PathVariable(value = "userCode") String userCode);

    @RequestMapping(value = "/v1/admin/save", method = RequestMethod.POST)
    String save(
            @RequestParam(required = true, value = "tbAdminJson") String tbAdminJson,
            @RequestParam(required = true, value = "optsBy") String optsBy);
}
