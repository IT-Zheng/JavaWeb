package cn.xiaozheng.web.servlet;

import cn.xiaozheng.service.ProvinceService;
import cn.xiaozheng.service.impl.ProvinceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ProvinceServlet")
public class ProvinceServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*//1.调用service查询
		ProvinceService provinceService = new ProvinceServiceImpl();
		List<Province> provinceServiceAllList = provinceService.findAll();
		//2.实例化list为json
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(provinceServiceAllList);*/
		//1.调用service查询ice查询
		ProvinceService provinceService = new ProvinceServiceImpl();
		String json = provinceService.findAllJson();
		System.out.println(json);
		//3.响应结果
		//设置格式，编码
		response.setContentType("application/json;charset=utf-8");
		//输出流，写出去
		response.getWriter().write(json);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
}
