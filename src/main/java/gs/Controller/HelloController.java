package gs.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import gs.bll.test;


@Controller
public class HelloController {
    
    // 输出文本
    @ResponseBody
    @RequestMapping("/text")
    public String text(Model model) {
        return "text";
    }

    // 返回json数据
    @ResponseBody
    @RequestMapping("/data")
    public Map<Object,Object> data() {
        System.out.println("data");
        Map<Object,Object> map = new HashMap<Object,Object>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        //return map;
        test tst=new test();
        return tst.DoTest();
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
