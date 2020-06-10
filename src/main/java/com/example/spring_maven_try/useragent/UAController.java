package com.example.spring_maven_try.useragent;

//import java.util.Arrays;
//import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


// Main controller class for useragent
@RestController
public class UAController {

    @RequestMapping(method = RequestMethod.GET, value = "/useragent")
    public UserAgent sayUA(@RequestParam(required = false) String useragent,HttpServletRequest request) {
        String ua=useragent;
        String browser;
        String os;
        String browser_version;
        String os_version;
        String uaself = request.getHeader("User-Agent");
        
        if(ua == null) {
            ua=uaself;
        }
        try{
            browser=findbrowser(ua);
        }
        catch(Exception e){
            browser="Unknown";
        }
        try{
            os=findos(ua);
        }
        catch(Exception e){
            os="Unknown";
        }
        try{
            browser_version=findBrowserVer(ua,browser);
        }
        catch(Exception e){
            browser_version="Unknown";
        }
        try{
        os_version=findOSVer(ua,os);
        }
        catch(Exception e){
            os_version="Unknown";
        }
        UserAgent ans = new UserAgent(ua, browser, browser_version,os,os_version);
        return ans;
    }

    //Function for detecting browser name
    public String findbrowser(String userAgentString) {
        String b ="Unknown";

        // Detect Chrome
        boolean chromeAgent = userAgentString.indexOf("Chrome") > -1;
        // Detect Internet Explorer
        boolean IExplorerAgent = userAgentString.indexOf("MSIE") > -1 || userAgentString.indexOf("rv:") > -1;
        // Detect Firefox 
        boolean firefoxAgent = userAgentString.indexOf("Firefox") > -1;
        // Discard Internet Explorer since it also matches Firefox 
        if ((IExplorerAgent) && (firefoxAgent)) IExplorerAgent = false;
        // Detect Safari
        boolean safariAgent = userAgentString.indexOf("Safari") > -1;
        // Discard Safari since it also matches Chrome 
        if ((chromeAgent) && (safariAgent)) safariAgent = false;
        // Detect Opera 
        boolean operaAgent = userAgentString.indexOf("OP") > -1;
        // Discard Chrome since it also matches Opera         
        if ((chromeAgent) && (operaAgent)) chromeAgent = false;
        //Detect Microsoft Edge
        boolean edgeAgent = userAgentString.indexOf("Edge") > -1;
        // Discard Chrome since it also matches Edge         
        if ((chromeAgent) && (edgeAgent)) chromeAgent = false;
        
        if (chromeAgent) b = "Chrome";
        else if (IExplorerAgent) b = "Internet Explorer";
        else if (firefoxAgent) b = "Firefox";
        else if (safariAgent) b = "Safari";
        else if (operaAgent) b = "Opera";
        else if (edgeAgent) b = "Edge";

        return b;
    }
    //Function for detecting operating system name
    public String findos(String userAgentString){
        String o ="Unknown";

        if (userAgentString.indexOf("Win") != -1) o = "Windows";
        else if (userAgentString.indexOf("Android")!=-1) o = "Android";
        else if (userAgentString.indexOf("Mac") != -1) o = "MacOS";
        else if (userAgentString.indexOf("Linux") != -1) o = "Linux";
        else if (userAgentString.indexOf("X11") != -1) o = "UNIX";

        if(o=="MacOS" && userAgentString.indexOf("iPhone") != -1) o="iPhone OS";

        return o;
    }
    //Function for detecting browser version
    public String findBrowserVer(String userAgentString,String br){
        String bver="Unknown";
        // Internet Explorer and Opera have different search criteria
        if(br=="Internet Explorer"){
            if(userAgentString.indexOf("MSIE")>-1) br="MSIE";
            else   br="rv:";
        }
        else if(br=="Opera")
        {
            br="OP";
        }

        if(br=="rv:"){
            int s=userAgentString.indexOf(br);
            int newstart = userAgentString.indexOf(":",s);
            int end = userAgentString.indexOf(")",newstart);
            bver=userAgentString.substring(newstart+1,end);
        }
        else if(br=="MSIE"){
            int s=userAgentString.indexOf(br);
            int newstart = userAgentString.indexOf(" ",s);
            int end = userAgentString.indexOf(";",newstart);
            int end1 = userAgentString.indexOf(",",newstart);
            if(end==-1 || end>end1 && end1!=-1) end=end1;
            bver=userAgentString.substring(newstart+1,end);
        }
        else{
            try{
                int s=userAgentString.indexOf(br);
                if(s!=-1)
                {
                    int start = userAgentString.indexOf("/",s);
                    if(start!=-1){
                        try{
                            int end = userAgentString.indexOf(" ",start);
                            bver=userAgentString.substring(start+1,end);
                        }
                        catch(Exception e){
                            try{
                                int end = userAgentString.indexOf("\"",start);
                                bver=userAgentString.substring(start+1,end);
                            }
                            catch(Exception e1){
                                bver=userAgentString.substring(start+1);
                            }
                        }
                    }      
                }
            }
            catch(Exception e){
                bver="Unknown";
            }
        }
        
        
        return bver;
    }
    //Function for detecting operating system version
    public String findOSVer(String userAgentString, String os) {
        String osver="Unknown";

        if(os=="Windows"){
            if(userAgentString.indexOf("Windows 98")!=-1) osver="98";
            else{
                int s = userAgentString.indexOf("NT");
                int start = userAgentString.indexOf(" ",s);
                int end = userAgentString.indexOf(";",s);
                int end1 = userAgentString.indexOf(")",s);
                if(end>end1 || end==-1) end=end1;
                try{
                    osver=userAgentString.substring(start+1,end);
                }
                catch(Exception e){
                    osver="Unknown";
                }
            }
        }
        else if(os=="MacOS"){
            os="Mac OS X";
                int s = userAgentString.indexOf(os);
                int start = userAgentString.indexOf(" ",s+7);
                int end1 = userAgentString.indexOf(";",s);
                int end2 = userAgentString.indexOf(")",s);
                int end =end1;
                if(end1==-1 || end2<end1) end=end2;
                try{
                    osver=userAgentString.substring(start+1,end);
                }
                catch(Exception e){
                    osver="Unknown";
                }
        }
        else if(os=="iPhone OS"){
            int s = userAgentString.indexOf("iPhone OS");
            int end1 = userAgentString.indexOf(";",s+10);
            int end2 = userAgentString.indexOf(" ",s+10);
            int end3 = userAgentString.indexOf(")",s+10);
            int end =end1;
            if(end1==-1 || end2<end1) end=end2;
            if(end2==-1 || end3<end2) end=end3;
            try{
                osver=userAgentString.substring(s+10,end);
            }
            catch(Exception e){
                osver="Unknown";
            }
        }
        
        else if(os=="Linux"){
            int s = userAgentString.indexOf(os);
            int end1 = userAgentString.indexOf(";",s);
            int end2 = userAgentString.indexOf(")",s);
            int end =end1;
            if(end1==-1 || end2<end1) end=end2;
            try{
                osver=userAgentString.substring(s+7,end);
            }
            catch(Exception e){
                osver="Unknown";
            }
        }
        else if(os=="UNIX"){
            osver="NA";
        }
        else if(os=="Android"){
            int s = userAgentString.indexOf(os);
            int end = userAgentString.indexOf(";",s+8);
            int end1 = userAgentString.indexOf(")",s+8);
            if(end==-1 || end1<end) end=end1;
            try{
                osver=userAgentString.substring(s+8,end);
            }
            catch(Exception e){
                osver="Unknown";
            }
        }

        return osver;
    }
    
}