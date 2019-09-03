package gs.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

// import gs.bll.Beantest;
import gs.bll.DataBaseDo;


@Controller
public class HelloController {
    
    //注入Beantest实例（测试）
    // @Autowired
    // private  Beantest beantest;

    //注入DataSource（传递给JdbcTemplate）
    @Autowired 
    private DataSource oracleDataSource;

    //注入EntityManagerFactory（用于获取SessionFactory）
    @PersistenceUnit
    private EntityManagerFactory emf;

    
    // 输出文本
    @ResponseBody
    @RequestMapping("/text")
    public String text(Model model) {
        return "text";
    }

    // 返回json数据(JPA-Hibernate)
    @ResponseBody
    @RequestMapping("/data")
    public Map<Object,Object> data() {
        Map<Object,Object> map = new HashMap<Object,Object>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        //return map;
        DataBaseDo tst=new DataBaseDo();
        SessionFactory sessionFactory= emf.unwrap(SessionFactory.class);
        return tst.HibernateDo(sessionFactory);
    }

    // 返回json数据(JDBC)
    @ResponseBody
    @RequestMapping("/data1")
    public List<Map<String, Object>> data1() {
        DataBaseDo tst=new DataBaseDo();
        return tst.Jdbc_queryForList(oracleDataSource);
    }

    // 访问templates文件夹的页面方法1,static文件夹的没有限制随便访问
    @RequestMapping("/dpage")
    public String dpage(Model model) {
        model.addAttribute("hello", "world");
        return "dpage";
    }

    //访问templates文件夹的页面方法2,static文件夹的没有限制随便访问
    @RequestMapping("/dpage2")
    public ModelAndView dpage2() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dpage");
        modelAndView.addObject("hello", "world");
        return modelAndView;
    }

    // 重定向到static文件夹的页面
    @RequestMapping("/dpage3")
    public String dpage3(Model model) {
        model.addAttribute("hello", "world");
        return "redirect:login.html";
    }

    //重定向到templates文件夹的页面
    @RequestMapping("/dpage4")
    public String dpage4(Model model) {
        model.addAttribute("hello", "world");
        return "redirect:/dpage";
    }

}
