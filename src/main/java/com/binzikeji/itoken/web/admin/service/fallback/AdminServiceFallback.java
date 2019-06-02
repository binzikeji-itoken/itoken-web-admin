package com.binzikeji.itoken.web.admin.service.fallback;

import com.binzikeji.itoken.common.conatants.HttpStatusConstants;
import com.binzikeji.itoken.common.dto.BaseRestult;
import com.binzikeji.itoken.common.hystrix.Fallback;
import com.binzikeji.itoken.common.utils.MapperUtils;
import com.binzikeji.itoken.web.admin.service.AdminService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author Bin
 * @Date 2019/4/12 11:18
 **/
@Component
public class AdminServiceFallback implements AdminService {

    @Override
    public String page(int pageNum, int pageSize, String tbAdminJson) {
        return Fallback.badGateway();
    }

    @Override
    public String get(String userCode) {
        return null;
    }

    @Override
    public String save(String tbAdminJson, String optsBy) {
        return Fallback.badGateway();
    }
}
