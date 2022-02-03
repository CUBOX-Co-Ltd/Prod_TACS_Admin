package cubox.admin.cmmn.util;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.view.AbstractView;
 
//@Component > @Service
//            : 스프링 프레임워크가 관리하는 컴포넌트의 일반적 타입 
//            : 개발자가 직접 조작이 가능한 클래스의 경우 해당 어노테이션을 붙임
//            : ( <=> @Bean : 개발자가 조작이 불가능한 외부 라이브러리를 Bean으로 등록시 사용)
@Component
// AbstractView : 스프링 MVC 사용시 DispatcherServlet 기능
//                : requestURI에 따라 컨트롤러로 분기하고 로직 처리 후 Resolver를 사용하여
//                : 해당 jsp 파일을 찾아 응답하게 되는데 그 사이 시점을 잡아 처리하는 부분의 기능
public class FileDownloadView extends AbstractView {
	
	public void Download() {        
        setContentType("application/download; utf-8");         
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                        HttpServletResponse response) throws Exception {
    	
        File file = (File) model.get("downloadFile");
        String fileOrigin = (String)model.get("fileOrigin");
        
        if(file != null && file.exists()) {
        	FileInputStream fis = null;  
        	OutputStream out = null;
        	try {
	        	setContentType("application/download; utf-8");
	        	
	        	response.setContentType(getContentType());
	            response.setContentLength((int) file.length()); 
	            
	            String header = request.getHeader("User-Agent");
	            boolean b = header.indexOf("MSIE") > -1;
	            String fileName = null;
	            
	            if (b) {
	                fileName = URLEncoder.encode(fileOrigin, "utf-8").replaceAll( "\\+", "%20" );
	            } else {
	            	fileName = URLEncoder.encode(fileOrigin, "utf-8").replaceAll( "\\+", "%20" );
	            }            
	            response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\";");
	            response.setHeader("Content-Transter-Encoding", "binary");        
	            out = response.getOutputStream(); 
                fis = new FileInputStream(file);            
                FileCopyUtils.copy(fis, out);
            } catch (Exception e) {
                e.printStackTrace();
                response.setContentType("text/html; charset=UTF-8");
    			PrintWriter writer = response.getWriter();
    			writer.println("<script type='text/javascript'>");
    			writer.println("alert('파일 다운로드 중 오류가 발생되었습니다.');");
    			writer.println("history.back();");
    			writer.println("</script>");
    			writer.flush();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }            
                if (out !=null) out.flush();
            }
        } else {
        	response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script type='text/javascript'>");
			writer.println("alert('파일정보를 찾을 수 없습니다.');");
			writer.println("history.back();");
			writer.println("</script>");
			writer.flush();
        }        
    }   
}