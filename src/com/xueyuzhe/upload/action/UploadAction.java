/**
 * 
 */
package com.xueyuzhe.upload.action;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ideamoment.ideadp.appcontext.IdeaApplicationContext;
import com.ideamoment.ideadp.restful.json.JsonData;
import com.xueyuzhe.upload.FileMeta;
import com.xueyuzhe.upload.UploadException;
import com.xueyuzhe.upload.UploadExceptionCode;

/**
 * @author Chinakite
 *
 */
@Controller
public class UploadAction {
    /**
     * 上传图片
     * 
     * @return
     */
    @RequestMapping(value="/console/uploadPic", method=RequestMethod.POST)
    public JsonData uploadPic(HttpServletRequest request, HttpServletResponse response, @RequestParam("importFile") CommonsMultipartFile[] files) {
        LinkedList<FileMeta> filemetas = new LinkedList<FileMeta>();
        
        for(int i = 0;i<files.length;i++){  
            if(!files[i].isEmpty()){
                CommonsMultipartFile file = files[i];
                FileMeta filemeta = new FileMeta();
                
                filemeta.setFileName(file.getOriginalFilename());
                filemeta.setFileSize(file.getSize() / 1024 + " Kb");
                filemeta.setFileType(file.getContentType());
                String fileId = UUID.randomUUID().toString().replaceAll("\\-", "");
                filemeta.setFileId(fileId);
                
                try {  
                    String webRoot = IdeaApplicationContext.getInstance().getWebRoot();
                    if(!webRoot.endsWith("/") && !webRoot.endsWith("\\")) {
                        webRoot += File.separator;
                    }
                    
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    String dateStr = sdf.format(date);
                    
                    String outputDirStr = webRoot + "uploadTmp/pic" + File.separator + dateStr;
                    File outputDir = new File(outputDirStr);
                    if(!outputDir.exists()) {
                        outputDir.mkdirs();
                    }
                    
                    String outputPath = outputDirStr + File.separator + filemeta.getFileId() + "_" + file.getOriginalFilename();
                    
                    String fileUrl = "/uploadTmp/pic" + File.separator + dateStr + File.separator + filemeta.getFileId() + "_" + file.getOriginalFilename();
                    fileUrl = fileUrl.replaceAll("\\\\", "/");
                    filemeta.setFileUrl(fileUrl);
                    
                    FileCopyUtils.copy(file.getBytes(), new FileOutputStream(outputPath));
                
                    filemetas.add(filemeta);
                } catch (Exception e) {  
                    e.printStackTrace();  
                    throw new UploadException(UploadExceptionCode.UPLOAD_ERROR, "Upload error.", e);
                }  
            }
        }
        return JsonData.success(filemetas);  
    }
    
    /**
     * 上传音频
     * 
     * @return
     */
    @RequestMapping(value="/console/uploadAudio", method=RequestMethod.POST)
    public JsonData uploadAudio(HttpServletRequest request, HttpServletResponse response, @RequestParam("importFile") CommonsMultipartFile[] files) {
        LinkedList<FileMeta> filemetas = new LinkedList<FileMeta>();
        
        for(int i = 0;i<files.length;i++){  
            if(!files[i].isEmpty()){
                CommonsMultipartFile file = files[i];
                FileMeta filemeta = new FileMeta();
                
                filemeta.setFileName(file.getOriginalFilename());
                filemeta.setFileSize(file.getSize() / 1024 + " Kb");
                filemeta.setFileType(file.getContentType());
                String fileId = UUID.randomUUID().toString().replaceAll("\\-", "");
                filemeta.setFileId(fileId);
                
                try {  
                    String webRoot = IdeaApplicationContext.getInstance().getWebRoot();
                    if(!webRoot.endsWith("/") && !webRoot.endsWith("\\")) {
                        webRoot += File.separator;
                    }
                    
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    String dateStr = sdf.format(date);
                    
                    String outputDirStr = webRoot + "uploadTmp/audio" + File.separator + dateStr;
                    File outputDir = new File(outputDirStr);
                    if(!outputDir.exists()) {
                        outputDir.mkdirs();
                    }
                    
                    String outputPath = outputDirStr + File.separator + filemeta.getFileId() + "_" + file.getOriginalFilename();
                    
                    String fileUrl = "/uploadTmp/audio" + File.separator + dateStr + File.separator + filemeta.getFileId() + "_" + file.getOriginalFilename();
                    fileUrl = fileUrl.replaceAll("\\\\", "/");
                    filemeta.setFileUrl(fileUrl);
                    
                    FileCopyUtils.copy(file.getBytes(), new FileOutputStream(outputPath));
                
                    filemetas.add(filemeta);
                } catch (Exception e) {  
                    e.printStackTrace();  
                    throw new UploadException(UploadExceptionCode.UPLOAD_ERROR, "Upload error.", e);
                }  
            }
        }
        return JsonData.success(filemetas);  
    }
}
