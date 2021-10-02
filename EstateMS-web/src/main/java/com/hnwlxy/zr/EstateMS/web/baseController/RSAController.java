package com.hnwlxy.zr.EstateMS.web.baseController;



import com.hnwlxy.zr.EstateMS.common.rsa.RSAJS;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

@Controller
@RequestMapping("rsa")
@Scope("prototype")
public class RSAController {
    @ResponseBody
    @RequestMapping("generateRSAJsKey")
    public String generateRSAJsKey(HttpServletRequest request) throws Exception {
        Map<String, Object> keyMap = RSAJS.createKey();// 生成公钥和密钥
        RSAPublicKey publicKey = (RSAPublicKey) keyMap.get(RSAJS.PUBLIC_KEY);
        RSAPrivateKey privateKey = (RSAPrivateKey) keyMap.get(RSAJS.PRIVATE_KEY);
        HttpSession session = request.getSession();
        session.setAttribute(RSAJS.PRIVATE_KEY, privateKey);// 将私钥保存在session中
        // java中的模和私钥指数不需要转16进制，但是js中的需要转换为16进制
        // js通过模和公钥指数获取公钥对字符串进行加密，注意必须转为16进制
        // 模
        String Modulus = publicKey.getModulus().toString(16);
        // 公钥指数
        String Exponent = publicKey.getPublicExponent().toString(16);
        String strSet = Modulus + ";" + Exponent;
        return strSet;
    }
}
