package com.binzikeji.itoken.web.admin.controller;

import com.binzikeji.itoken.common.domain.TbPostsPost;
import com.binzikeji.itoken.common.domain.TbSysUser;
import com.binzikeji.itoken.common.dto.BaseRestult;
import com.binzikeji.itoken.common.utils.MapperUtils;
import com.binzikeji.itoken.common.web.conatants.WebContants;
import com.binzikeji.itoken.common.web.controller.BaseController;
import com.binzikeji.itoken.web.admin.service.AdminService;
import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @Description
 * @Author Bin
 * @Date 2019/4/12 11:10
 **/
@Controller
public class AdminController  extends BaseController<TbPostsPost, AdminService> {

    @Autowired
    private AdminService adminService;

    @ModelAttribute
    public TbSysUser tbSysUser(String userCode){
        TbSysUser tbSysUser = null;
        if (StringUtils.isBlank(userCode)){
            tbSysUser = new TbSysUser();
        } else {
            String json = adminService.get(userCode);
            try {
                tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (tbSysUser == null){
                tbSysUser = new TbSysUser();
            }
        }
        return tbSysUser;
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = {"", "main"}, method = RequestMethod.GET)
    public String main(){
        return "main";
    }

    @RequestMapping(value = "from", method = RequestMethod.GET)
    public String from(){
        return "from";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public BaseRestult save(@RequestBody Map map, HttpServletRequest request) throws Exception {
        TbSysUser tbSysUser = new TbSysUser();
        tbSysUser.setLoginCode(map.get("loginCode").toString());
        tbSysUser.setUserName(map.get("loginCode").toString());
        tbSysUser.setPassword(DigestUtils.md5DigestAsHex(map.get("password").toString().getBytes()));
        tbSysUser.setUserType("0");
        tbSysUser.setMgrType("1");
        tbSysUser.setAvatar(map.get("avatar").toString());
        //tbSysUser.setTimePublished(new Date());
        tbSysUser.setStatus("0");
        String tbSysUserJson = MapperUtils.obj2json(tbSysUser);
        TbSysUser user = (TbSysUser) request.getSession().getAttribute(WebContants.SESSION_USER);
        String json = adminService.save(tbSysUserJson, user.getUserCode());
        BaseRestult baseRestult = MapperUtils.json2pojo(json, BaseRestult.class);
        if (baseRestult.getSuccess().endsWith("成功")){
            return BaseRestult.ok("保存成功!", "/index");
        }
        return BaseRestult.ok("保存失败!", "/from");
    }
}
