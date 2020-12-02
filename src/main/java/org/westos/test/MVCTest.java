package org.westos.test;

import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.westos.bean.Employee;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
/*注意这里要加载spring和springmvc的两个配置文件*/
@ContextConfiguration(value = {"classpath:applicationContext.xml", "classpath:springmvc.xml"})
@WebAppConfiguration  //这个注解是为了把WebApplicationContext这个容器的对象放到容器中
public class MVCTest {
    @Autowired
    WebApplicationContext context;
    private MockMvc mockMvc;
    @Before
    public void init() {
        //构建MockMvc
        // 把 WebApplicationContext 对象传入
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    public void testRequest() throws Exception {
        //构建get请求
        MockHttpServletRequestBuilder mb = MockMvcRequestBuilders.get("/emps").param("pageNum", "1");
        //执行get请求，并获取返回的结果
        MvcResult mvcResult = mockMvc.perform(mb).andReturn();
//那么返回的结果在request请求域中存着呢，我们把请求对象获取到，再从请求对象中取出数据展示
        MockHttpServletRequest request = mvcResult.getRequest();//获取请求对象
        PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");//获取请求域中的数据
        //获取当前页码
        int pageNum = pageInfo.getPageNum();
        //获取总页码
        int pages = pageInfo.getPages();
        //获取总条数
        long total = pageInfo.getTotal();
        //获取连续显示的页页码
        int[] navigatepageNums = pageInfo.getNavigatepageNums();
        //获取员工数据
        List<Employee> list = pageInfo.getList();
        System.out.println(pageNum);
        System.out.println(pages);
        System.out.println(total);
        System.out.println(Arrays.toString(navigatepageNums));
        System.out.println(list);
        for (Employee employee : list) {
            System.out.println(employee);
        }

    }
}
