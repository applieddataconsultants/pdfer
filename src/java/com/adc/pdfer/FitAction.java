package com.adc.pdfer;

import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import com.adc.pdfer.stripes.BaseAction;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;
import net.sourceforge.stripes.action.StreamingResolution;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Logger;

/*
 * reverted to using file output. streaming output was causing errors
 * for larger filestreams perhaps this is due to our implementation,
 * but it seems to be an error in the wkhtmltopdf executable.
 */
@UrlBinding(value = "/fit")
public class FitAction extends BaseAction {

    private static final Logger log = Logger.getLogger(FitAction.class);
    
    private String url;
    private String html_head = "<html><head></head><body>";
    private String html_foot = "</body></html>";
    private String html_inner;
    private String file = "report";
    private String format = "pdf"; //pdf|png
    private String options = "--encoding UTF-8";
    private String errormessage = "";
    private boolean debug = false;

    @DefaultHandler
    public Resolution pdf() {
        String exe;
        String mimetype;
        String extension;
        if(getFormat().equalsIgnoreCase("png")){
            exe = "wkhtmltoimage";
            mimetype = "image/png";
            extension = "png";
            if(options == null)options = "--javascript-delay 5000 --no-stop-slow-scripts";
        }else{
            exe = "wkhtmltopdf";
            mimetype = "application/pdf";
            extension = "pdf";
            if(options == null)options = "--encoding UTF-8";
        }

        String tempfile = "/tmp/report_"+ String.valueOf(new Date().getTime()) +"."+extension;

        ArrayList<String> cmds = new ArrayList();
        cmds.add("/bin/sh");
        cmds.add("-c");
        Process p = null;

        log.debug("URL : " + url);
        log.debug("html_head : " + html_head);
        log.debug("html_inner : " + html_inner);
        log.debug("html_foot : " + html_foot);

        if(html_head == null)html_head = "<html><head></head><body>";
        if(html_foot == null)html_foot = "</body></html>";
        if(file == null)file = "output";

        try {
            if(options.contains(";") || options.contains("&&")){
                throw new RuntimeException("Options may contain hack attempt, check your options for ';' or '&&'");
            }

            if (getHtml_inner() != null && getHtml_inner().length() > 0) {
                cmds.add( exe  + " " + getOptions() + " - " + tempfile );
                
            } else if (getUrl() != null && getUrl().length() > 0) {
                String t = getUrl();
                try {
                    t = URLDecoder.decode(t, "UTF-8");
                    t = t.replaceFirst("http://", "");
                } catch (Exception e) {
                    log.error("Could not decode url", e);
                }
                log.info("Sending this url to wkhtmltopdf: " + t);
                log.info("with these options: " + getOptions());

                cmds.add( exe + " " + getOptions() + " \"" + t + "\" " + tempfile );
            } else {
                return new ForwardResolution("/index.html");
            }

            String[] cmd = new String[cmds.size()];
            cmd = cmds.toArray(cmd);

            for (String s : cmd) {
                log.debug("CMD : " + s);
                if(debug) errormessage += "\n\t"+ s;
            }

            p = Runtime.getRuntime().exec(cmd);

            if (getHtml_inner() != null && getHtml_inner().length() > 0) {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
                writer.write(getHtml_head());
                /* hack to filter odd chars generated in eagli_engine reports
                 */
                writer.write(getHtml_inner()
                        .replaceAll("â", "")
                        .replaceAll("Â","")
                        .replaceAll("â","-")
                        );
                writer.write(getHtml_foot());
                writer.flush();
                writer.close();
            }

            InputStream stderr = p.getErrorStream();

            String line;
            BufferedReader brCleanUp = new BufferedReader(new InputStreamReader(stderr));
            while ((line = brCleanUp.readLine()) != null) {
                log.debug(line);
                if(debug) errormessage += "\n\t"+ line;
            }
            brCleanUp.close();

            File tmpfile = new File(tempfile);
            InputStream is = new FileInputStream(tmpfile);
            tmpfile.delete();
            return new StreamingResolution(mimetype, is).setFilename(getFile() + "."+extension);

        } catch (IOException e) {
            log.error(e.getMessage());
            errormessage += "\n\t"+ e.getMessage();
            return new ForwardResolution("/error.jsp");
        }

    }

// <editor-fold defaultstate="collapsed" desc="getters and setter">
    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the html_head
     */
    public String getHtml_head() {
        return html_head;
    }

    /**
     * @param html_head the html_head to set
     */
    public void setHtml_head(String html_head) {
        this.html_head = html_head;
    }

    /**
     * @return the html_inner
     */
    public String getHtml_inner() {
        return html_inner;
    }

    /**
     * @param html_inner the html_inner to set
     */
    public void setHtml_inner(String html_inner) {
        this.html_inner = html_inner;
    }

    /**
     * @return the html_foot
     */
    public String getHtml_foot() {
        return html_foot;
    }

    /**
     * @param html_foot the html_foot to set
     */
    public void setHtml_foot(String html_foot) {
        this.html_foot = html_foot;
    }

    /**
     * @return the options
     */
    public String getOptions() {
        try{
            return URLDecoder.decode(options, "UTF-8");
        }catch(IOException e){
            return options;
        }
    }

    /**
     * @param options the options to set
     */
    public void setOptions(String options) {
        this.options = options;
    }

    /**
     * @return the output
     */
    public String getFile() {
        return file;
    }

    /**
     * @param output the output to set
     */
    public void setFile(String file) {
        this.file = file;
    }
    

    /**
     * @return the format
     */
    public String getFormat() {
        return format;
    }

    /**
     * @param format the format to set
     */
    public void setFormat(String format) {
        this.format = format;
    }
    

    /**
     * @return the errormessage
     */
    public String getErrormessage() {
        return errormessage;
    }

    /**
     * @param errormessage the errormessage to set
     */
    public void setErrormessage(String errormessage) {
        this.errormessage = errormessage;
    }

    /**
     * @return the debug
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * @param debug the debug to set
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }
    //</editor-fold>
}
