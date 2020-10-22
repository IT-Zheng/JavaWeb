package cn.xiaozheng.Servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Package: ${PACKAGE_NAME}
 * @ClassName: ${NAME}
 * @Author: Bad Body
 * @CreateTime: 2020/7/25 19:26
 * @Description:
 */
@WebServlet("/findUserServlet")
public class findUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        //1.��ȡ�û���
        String username = request.getParameter("username");
        //2.����service���ж��û����Ƿ����
        Map<String, Object> map = new HashMap<String, Object>();

        if("tom".equals(username)){
            //����
            map.put("userExist", "true");
            map.put("msg", "���û����ظ����뻻һ��");
        }else {
            //������
            map.put("userExist", "false");
            map.put("msg", "�û�������");
        }
        //��MaPתΪjson��
        ObjectMapper mapper = new ObjectMapper();
        //�����ݸ��ͻ���
        mapper.writeValue(response.getWriter(),map);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
